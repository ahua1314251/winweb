package org.winker.spider;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author ：tom
 * @date ：Created in 2021/8/26 5:12 下午
 * @description：chromeDriver是非线程安全的 需要自己写一个简单地driver池，最大实例数10
 * @modified By：
 * @version: $
 */
public class ChromeDriverPool {
    private static List<ChromeDriverIns> chromeDriverInsList = null;;
    public static ThreadLocal<ChromeDriverIns> chromeDriverInsHolder = new ThreadLocal<>();
    private final static Logger logger = LoggerFactory.getLogger(SpiderService.class);

    public static synchronized ChromeDriverIns getChromeDriverIns(){
        //没有被初始化先初始化再取对象 测试使用
        if(CollectionUtils.isEmpty(chromeDriverInsList)){
            chromeDriverInsList = initChromeDriverPool(2);
        }
        while (true){
            for(ChromeDriverIns chromeDriverIns : chromeDriverInsList){
                if(chromeDriverIns.available){
                    chromeDriverIns.setAvailable(false);
                    ChromeDriverPool.chromeDriverInsHolder.set(chromeDriverIns);
                    return chromeDriverIns;
                }
            }
        }
    }

    private static List<ChromeDriverIns>  initChromeDriverPool(int maxPoolSize){
        logger.info("initChromeDriverPool start.");
        List<ChromeDriverIns> chromeDriverInss = new ArrayList<>();
        try {
            for (int i = maxPoolSize; i > 0; i--) {
                chromeDriverInss.add(newChromeDriver());
            }
            return chromeDriverInss;
        }catch (Exception e){
            logger.error("initChromeDriverPool error msg={}",e.getMessage(),e);
        }
        logger.info("initChromeDriverPool end.");
        return chromeDriverInss;
    }

    public static ChromeDriverIns newChromeDriver() {
        //本地运行需要自行下载webDrive和本地浏览器的型号一致
        //https://sites.google.com/a/chromium.org/chromedriver/downloads?spm=ata.21736010.0.0.226e41988Eonmf
        ///Users/tom/Documents/chrome/chromedriver
        String driverPath = "";
        String localPath = "/Users/tom/Documents/chrome/chromedriver";
        String serverPath = "/home/admin/tools/chromedriver";
        File driver = new File(localPath);
        if(driver.exists()){
            driverPath = localPath;
        }else{
            driverPath = serverPath;
        }
        System.setProperty("webdriver.chrome.driver", driverPath);
        ChromeOptions options = new ChromeOptions();
        //浏览器不提供可视化页面. linux下如果系统不支持可视化不加这条会启动失败
//        options.addArguments("headless");
        // 不加载图片, 提升速度
        options.addArguments("blink-settings=imagesEnabled=false");
        // 谷歌文档提到需要加上这个属性来规避bug
        options.addArguments("disable-gpu");
        options.addArguments("disable-plugins");
        options.addArguments("lang=zh_CN.UTF-8");
        //忽略提醒弹窗
        options.addArguments("disable-notifications");
        // 禁用java
        options.addArguments("disable-java");
        // 以最高权限运行
        options.addArguments("no-sandbox");
        options.addArguments("allow-running-insecure-content");
        Map<String, Object> prefs = new HashMap<String, Object>();
        Map<String, String> langs = new HashMap<String, String>();

        langs.put("en", "zh-CN");

        prefs.put("translate.enabled", true);
//        prefs.put("translate_allowlists",langs);
        prefs.put("translate_whitelists",langs);
        options.setExperimentalOption("prefs", prefs);


//        options.setExperimentalOption("mobileEmulation", mobileEmulation);
        ChromeDriverIns webDriver = new ChromeDriverIns(options);

        //2.隐试等待 10秒
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //3.显示等待时间10s,默认每隔0.5s检测一次当前的页面class=item-text这个元素是否存在
//        WebDriverWait w=new WebDriverWait(webDriver,10);
//        w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("body")));
        return webDriver;
    }

    public static void releaseCurrent(){
        ChromeDriverPool.chromeDriverInsHolder.remove();
    }

    public static ChromeDriverIns getCurrent(){
        return ChromeDriverPool.chromeDriverInsHolder.get();
    }
}
