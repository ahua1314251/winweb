package org.winker.spider;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.winker.winweb.common.enums.BizTypeEnum;
import org.winker.winweb.dao.mysql.entity.SpiderDO;
import org.winker.winweb.dao.mysql.mapper.SpiderMapper;
import org.winker.winweb.utils.database.JacksonUtil;
import org.winker.winweb.utils.database.MsgUtils;

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

    private static AtomicInteger count = new AtomicInteger(0);
    private static Map<String, String> allUrlMap = new HashMap();
    private static Map<Integer, Set<String>> levelUrl = new HashMap<>();

    private ExecutorService executorService = new ThreadPoolExecutor(4, 40,
            60L, TimeUnit.SECONDS, new LinkedBlockingQueue(2048));
    private int version = 0;
    private String cachePrefix = "";

    public void run(String seed, Integer deep) throws InterruptedException {
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
                if (MsgUtils.filterUrl(url1, new ArrayList<>()) && !allUrlMap.containsKey(url1)) {
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
        document.getElementsByTag("textarea").remove();
        document.getElementsByTag("input").remove();
        document.getElementsByTag("select").remove();
        document.getElementsByTag("button").remove();
        document.getElementsByTag("option").remove();
        String outBizId = MsgUtils.cleanUkMark(url);
        String uk = MsgUtils.genUk(BizTypeEnum.OVERFLOW.getValue(), "java", outBizId);
        String content = document.text();
        SpiderDO spiderDO = new SpiderDO();
        String nodeInfo = "[{\"nodeLevel\": 0, \"nodeId\": \"0\", \"nodeName\": \"网站\"}]";
        spiderDO.setNodesInfo(JacksonUtil.toJsonString(nodesInfo.entrySet()));
        spiderDO.setNodesInfo(nodeInfo);
        spiderDO.setUk(uk);
        spiderDO.setOutBizId(outBizId);
        spiderDO.setBizType(BizTypeEnum.OVERFLOW.getValue());
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




}
