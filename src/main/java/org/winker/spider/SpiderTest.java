package org.winker.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author ：tom
 * @date ：Created in 2021/8/25 2:02 下午
 * @description：
 * @modified By：
 * @version: $
 */
public class SpiderTest {
    static {
        // 通过命令行参数指定配置文件的地址
//        System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "/Users/tom/git/search-service-aliyun/aliyun-search-start/src/test/resources/test_logback.xml");
    }


    public static void main(String[] args) throws InterruptedException {
        ChromeDriverIns webDriver = ChromeDriverPool.getChromeDriverIns();
        webDriver.get("https://stackoverflow.com/questions/11227809/why-is-processing-a-sorted-array-faster-than-processing-an-unsorted-array");
        for(int i =0 ;i<100;i++) {
            test(webDriver);
            Thread.sleep(5000);
        }

    }



    public static void test(ChromeDriverIns webDriver) throws InterruptedException {
        Thread.sleep(400);
        String currentUrl =webDriver.getCurrentUrl();
        System.out.println("currentUrl="+ currentUrl);
//        WebElement element = webDriver.findElement(By.className("js-post-body"));
        System.out.println( webDriver.getPageSource());
//        String sourceHtml = webDriver.getPageSource();
//        Document document = Jsoup.parse(sourceHtml);
//        String title = document.getElementsByTag("title").first().text();
//        System.out.println("title="+title);
//        Elements elements =document.getElementsByTag("meta");
//        elements.forEach(ele->{
//            if("keywords".equals(ele.attr("name"))){
//                System.out.println("kewords="+ele.attr("content"));
//            }
//            if("description".equals(ele.attr("name"))){
//                System.out.println("description="+ele.attr("content"));
//            }
//        });

//        System.out.println(document.text());
//        List<String> deleteStyles = Arrays.asList("ace-homepage-2020-topbar","ace-homepage-2020-hmod-footer","right-floating-layer-container ");
//        for(String deleteStyle : deleteStyles){
//            document.getElementsByClass(deleteStyle).remove();
//        }
//        System.out.println(document.text());
        webDriver.release();
    }

}
