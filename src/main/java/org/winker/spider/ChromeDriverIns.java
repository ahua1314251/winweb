package org.winker.spider;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * @author ：tom
 * @date ：Created in 2021/8/26 5:14 下午
 * @description：chrome driver 线程池对象 封装可用状态 由于生产只会使用chrome 直接叫ChromeDriver 非 webDriver
 * @modified By：
 * @version: $
 */
public class ChromeDriverIns extends ChromeDriver {
    //默认状态可用，被线程占用为不可用
    Boolean available = true ;

    public ChromeDriverIns(ChromeOptions options) {
        super(options);
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public void release () {
        ChromeDriverIns chromeDriverIns = ChromeDriverPool.getCurrent();
        if(chromeDriverIns!=null){
            chromeDriverIns.setAvailable(true);
            ChromeDriverPool.releaseCurrent();
        }
    }
}
