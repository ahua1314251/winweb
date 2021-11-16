package org.winker.spider;

import com.alibaba.fastjson.JSON;
import com.aliyun.search.api.enums.BizTypeEnum;
import com.aliyun.search.common.constants.Constants;
import com.aliyun.search.common.middleware.diamond.DiamondConfigBean;
import com.aliyun.search.common.utils.MsgUtils;
import com.aliyun.search.dao.mysql.dataobj.SpiderDO;
import com.aliyun.search.dao.mysql.mapper.ProductMapper;
import com.aliyun.search.dao.mysql.mapper.SpiderMapper;
import com.taobao.tair.DataEntry;
import com.taobao.tair.Result;
import com.taobao.tair.ResultCode;
import com.taobao.tair.TairManager;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author ：tom
 * @date ：Created in 2021/8/30 4:19 下午
 * @description：
 * @modified By：
 * @version: $
 */
@Component
public class SpiderService {
    private final static Logger logger = LoggerFactory.getLogger(SpiderService.class);
    @Autowired
    SpiderMapper spiderMapper;
    @Autowired
    DiamondConfigBean diamondConfigBean;
    @Autowired
    TairManager tairManager;
    @Autowired
    ProductMapper productMapper;

    private static AtomicInteger count = new AtomicInteger(0);
    private static Map<String, String> allUrlMap = new HashMap();
    private static Map<Integer, Set<String>> levelUrl = new HashMap<>();

    private ExecutorService executorService = new ThreadPoolExecutor(4, 40,
            60L, TimeUnit.SECONDS, new LinkedBlockingQueue(2048));
    private int version = 0;
    private String cachePrefix = "";

    public void run(String seed, Integer deep) throws InterruptedException {
        this.init();
        this.seedRun(seed, deep);
    }

    public void seedRun(String url, int deep) throws InterruptedException {
        levelUrl.put(0, new HashSet<String>(Arrays.asList(url)));
        for (int i = 0; i < deep; i++) {
            Set<String> levelurlSet = levelUrl.get(i);
            if (levelurlSet == null || levelurlSet.size() <= 0) {
                continue;
            }
            Set<String> nextLevelUrlSet = new HashSet<>();
            Iterator<String> it = levelurlSet.iterator();
            CountDownLatch latch = new CountDownLatch(levelurlSet.size());
            while (it.hasNext()) {
                String runUrl = it.next();
                int finalI = i;
                //如果已经被其他机器爬过 跳过不爬
                if (!StringUtils.isEmpty(this.get(runUrl))) {
                    latch.countDown();
                    continue;
                }
                //如果没有被爬过，直接加入tair 代表已经被爬，可能会有意外情况，爬虫没那么严格，重点是效率。
                put(runUrl, runUrl);
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            nextLevelUrlSet.addAll(chromeExecute(finalI, runUrl));
                        } catch (Exception e) {
                            logger.error("chromeExecute error level={},runUrl={},errorMsg={}", finalI, runUrl, e.getMessage(), e);
                        } finally {
                            latch.countDown();
                        }
                    }
                });
            }
            latch.await();
            levelUrl.put(i + 1, nextLevelUrlSet);
        }
    }

    public Set<String> chromeExecute(int level, String url) throws InterruptedException {
        Set<String> urlSets = new HashSet<>();
        ChromeDriverIns webDriver = ChromeDriverPool.getChromeDriverIns();
        String currentUrl = null;
        try {
            webDriver.get(url);
            Thread.currentThread().sleep(200);
            webDriver.findElement(By.tagName("body"));
            String sourceHtml = webDriver.getPageSource();
            Document document = Jsoup.parse(sourceHtml);
            currentUrl = webDriver.getCurrentUrl();
            if (currentUrl.contains(url)) {
                //没有发生跳转的才会保存结果
                saveResult(url, level, document);
            }else{
                logger.warn("currentUrl is not equals currentUrl level={},runUrl={},currentUrl={}", level, url,currentUrl);
            }
            Elements elements = document.getElementsByTag("a");
            for (Element element : elements) {
                String url1 = MsgUtils.cleanUrlParam(element.attr("href"));
                if (MsgUtils.filterUrl(url1, diamondConfigBean.getSpiderBlackUrlList()) && !allUrlMap.containsKey(url1)) {
                    urlSets.add(url1);
                    allUrlMap.put(url1, element.text());
                }
            }
            webDriver.release();
        } catch (Exception e) {
            logger.error("chromeExecute error level={},runUrl={},currentUrl={},errorMsg={}", level, url,currentUrl, e.getMessage(), e);
            if(e.getMessage().contains("unexpected alert open")){
                webDriver.switchTo().alert().accept();
            }
            throw e;
        } finally {
            webDriver.release();
        }
        return urlSets;
    }

    private void saveResult(String url, int level, Document document) {
        if (spiderMapper == null) {
            return;
        }
        Elements titleElements = document.getElementsByTag("title");
        String title = titleElements.first() == null ? "" : titleElements.first().text();
        Map<String, String> nodesInfo = new HashMap<>();
        String keywords = new String();
        String description = null;
        Elements elements = document.getElementsByTag("meta");
        if (elements != null) {
            List<Element> elements1 = elements.stream().collect(Collectors.toList());
            for (Element ele : elements1) {
                if ("keywords".equals(ele.attr("name"))) {
                    keywords = ele.attr("content");
                }
                if ("description".equals(ele.attr("name"))) {
                    description = ele.attr("content");
                }
            }
        }
        //删除不需要样式
        diamondConfigBean.getSpiderDeleteStyles().forEach(item ->{
            document.getElementsByClass(item).remove();
        });
        document.getElementsByTag("textarea").remove();
        document.getElementsByTag("input").remove();
        document.getElementsByTag("select").remove();
        document.getElementsByTag("button").remove();
        document.getElementsByTag("option").remove();
        String outBizId = MsgUtils.cleanUkMark(url);
        String uk = MsgUtils.genUk(BizTypeEnum.OTHER.getValue(), "网站", outBizId);
        String content = document.text();
        SpiderDO spiderDO = new SpiderDO();
        String nodeInfo = "[{\"nodeLevel\": 0, \"nodeId\": \"0\", \"nodeName\": \"网站\"}]";
        spiderDO.setNodesInfo(JSON.toJSONString(nodesInfo.entrySet()));
        spiderDO.setNodesInfo(nodeInfo);
        spiderDO.setUk(uk);
        spiderDO.setOutBizId(outBizId);
        spiderDO.setBizType(BizTypeEnum.OTHER.getValue());
        spiderDO.setSubBizType("网站");
        spiderDO.setUrl(url);
        spiderDO.setContent(content);
        spiderDO.setGmtModifiedOrigin(new Date());
        spiderDO.setLevel(level);
        spiderDO.setTitle(title);
        spiderDO.setKeywords(keywords);
        spiderDO.setDescription(description);
        spiderDO.setTagText(allUrlMap.get(url));
        logger.info("count={},thread={},saveResult={}", count.getAndAdd(1), Thread.currentThread().getId(), spiderDO.getUrl());
        if (spiderMapper.existByUk(uk) != null) {
            spiderMapper.updateByUk(spiderDO);
        } else {
            spiderDO.setGmtCreateOrigin(new Date());
            spiderMapper.insert(spiderDO);
        }
    }

    public void put(String key, String value) {
        try {
            //爬虫缓存4小时过期
            ResultCode resultCode = tairManager.prefixPut(Constants.TAIR_NAMESPASE, cachePrefix, key, value, 0, 10000);
            if (!resultCode.isSuccess()) {
                logger.error("put cache failed prefix={},key={},value={},errorInfo={}", cachePrefix, key, value, JSON.toJSONString(resultCode));
            }
        } catch (Exception e) {
            logger.error("put cache failed prefix={},key={},value={}", cachePrefix, key, value, e);
        }
    }

    public String get(String key) {
        try {
            Result<DataEntry> result = tairManager.prefixGet(Constants.TAIR_NAMESPASE, cachePrefix, key);
            if (result.isSuccess() && result.getValue() != null && result.getValue().getValue() != null) {
                return (String) result.getValue().getValue();
            }
        } catch (Exception e) {
            logger.error("get cache failed prefix={},key={}", cachePrefix, key, e);
        }
        return null;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
        this.cachePrefix = diamondConfigBean.getTairEnv() + version;
    }

    public void init() {
        count.set(0);
        allUrlMap.clear();
        levelUrl.clear();
        List<String> productUrls = productMapper.queryUrlForSpider();
        for (String productUrl : productUrls) {
            put(MsgUtils.cleanUrlParam(productUrl), productUrl);
        }
    }

}
