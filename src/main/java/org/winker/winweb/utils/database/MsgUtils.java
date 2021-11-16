package org.winker.winweb.utils.database;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author ：tom
 * @date ：Created in 2021/6/10 9:55 上午
 * @description： 消息处理工具类
 * @modified By：
 * @version: 1
 */
public class MsgUtils {

    public static String genUk(String bizType, String subBizType, String outBizId) {
        return bizType + Constants.UK_SEPARATOR + subBizType + Constants.UK_SEPARATOR + outBizId;
    }

    public static String cleanUkMark(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll(Constants.CLEAN_PARTEN, "");
    }

    //同 odps delete_html 除去html 标签 和其他特殊字符处理
    public static String deleteHtml(String content) {
        content = content.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "").replaceAll("[(/>)<]", "");
        content = content.replace("\n", " ").replace("\t", " ").replace("\r", " ").replace("。.", ".").replace("⚫", "").replace("nbsp;", " ");
        content = content.replaceAll(" +", " ");
        return content;
    }

    public static String objectToString(Object obj) {
        return obj == null ? null : obj.toString();
    }

    public static Long objectToLong(Object obj) {
        String a = (obj == null ? null : obj.toString());
        if (StringUtils.isNumeric(a)) {
            return Long.parseLong(a);
        }
        return null;
    }

    public static BigDecimal objectToBigDecimal(Object obj) {
        String a = (obj == null ? null : obj.toString());
        if (StringUtils.isNumeric(a.replace(".", ""))) {
            return new BigDecimal(a);
        }
        return null;
    }

    public static String cleanUrl(String url) {
        url = url.trim();
        if (url.contains(Constants.URL_SPM)) {
            url = url.replaceAll("&?spm=[^&]*", "");
        }
        if (url.contains(Constants.URL_SCM)) {
            url = url.replaceAll("&?scm=[^&]*", "");
        }
        if (url.startsWith("//")) {
            url = url.substring(2);
        }
        if (!url.contains("https://") && !url.contains("http://")) {
            url = "https://" + url;
        }
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        if (url.length() > 200) {
            url = url.substring(0, 200);
        }
        return url;
    }

    public static String cleanUrlParam(String url) {
        url = url.trim();
        if (url.contains(Constants.Q_MARK)) {
            url = url.substring(0, url.indexOf(Constants.Q_MARK));
        }
        if (url.contains(Constants.HASH_MARK)) {
            url = url.substring(0, url.indexOf(Constants.HASH_MARK));
        }
        if (url.startsWith("//")) {
            url = url.substring(2);
        }
        if (!url.contains("https://") && !url.contains("http://")) {
            url = "https://" + url;
        }
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        if (url.length() > 200) {
            url = url.substring(0, 200);
        }
        return url;
    }

    public static Boolean filterUrl(String url, List<String> blackUrlList) {
        if (StringUtils.isEmpty(url) || StringUtils.isBlank(url)) {
            return false;
        }
        for (String blackUrl : blackUrlList) {
            if (url.contains(blackUrl)) {
                return false;
            }
        }
        if (!url.contains("aliyun.com") && !url.contains("aliyun.cn")) {
            return false;
        }
        return true;
    }

    /**
     * @param content
     * @param step
     * @return 清理内容 内容处理
     */
    public static String cleanContent(String content, int step) {
        char[] contentChar = content.toCharArray();
        Set<String> replaceSet = new TreeSet<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.equals(o2)) {
                    return 0;
                }
                int n1 = o2.length() - o1.length();
                return n1 == 0 ? 1 : n1;
            }
        });
        int start = 0;
        int end = 0;
        char tempChar;
        int charLength = contentChar.length;
        for (int i = 0; i < charLength; i++) {
            tempChar = contentChar[i];
            if (end - start <= step) {
                if (!isChinese(tempChar)) {
                    end = i;
                } else {
                    start = i + 1;
                    end = i + 1;
                }
            }
            if (end - start > step) {
                if (i == charLength - 1) {
                    end = i + 1;
                    replaceSet.add(content.substring(start, end));
                    break;
                }
                if (!isChinese(tempChar)) {
                    end = i;
                } else {
                    end = i;
                    replaceSet.add(content.substring(start, end));
                    start = i;
                }
            }
        }

        for (String replaceStr : replaceSet) {
            content = content.replace(replaceStr, ",");
        }
        return content;
    }

    public static Boolean isChinese(char ch) {
        if (19968 <= ch && ch < 40869) {
            return true;
        }
        return false;
    }


    public static String getFirstSplit(String str) {
        str = str.trim();
        if (str.contains(Constants.SPACE_SEPARATOR)) {
            str = str.split(Constants.SPACE_SEPARATOR)[0];
        }
        if (str.contains(Constants.UL_SEPARATOR)) {
            str = str.split(Constants.UL_SEPARATOR)[0];
        }
        if (str.contains(Constants.COMMA_SEPARATOR)) {
            str = str.split(Constants.COMMA_SEPARATOR)[0];
        }
        if (str.contains(Constants.SEMI_SEPARATOR)) {
            str = str.split(Constants.SEMI_SEPARATOR)[0];
        }
        return str;
    }

    public static String getBizTypeFromUk(String uk){
        if(StringUtils.isEmpty(uk)){
            return null;
        }
        return uk.split(Constants.UK_SEPARATOR)[0];
    }

    public static void main(String[] args) {
//      System.out.println(MsgUtils.deleteHtml("k  3123<p>qweqw阿达<div style=\"kkk\">12 312       3123</div>qwdsdswqd</p>daf阿达<div/>"));  ;
//      System.out.println(objectToBigDecimal("2.3"));
//        System.out.println(MsgUtils.cleanUrlParam("https://www.aliyun.com/core/online-consult#?#spm=5176.224200.J_2145224690.1.7721586cSt6PP9&from=bg778QBnk9&scm=20140722.455.1.3254"));
//        System.out.println(cleanContent("我是中国人二恶烷若342343242fr而非343fewfwefsfewgfasfdwegfaeffewfafd反反复复veeeewewerqweqweccweqwe", 10));
//        System.out.println(MsgUtils.isChinese('"'));
//        System.out.println(MsgUtils.cleanContent("画板Created with Sketch. 全部 域名 商标 公司 搜索 发布定制需求 文档备案控制台 登录免费注册 企业服务网站建设多端小程序解决方案 产品分类                   {\"moduleinfo\":{\"searchLink\":\"//m.aliyun.com/product/search\",\"channels_count\":[{\"count_phone\":10,\"count\":10}],\"menuButtons_count\":[{\"count_phone\":2,\"count\":2}],\"noAppDownload\":true,\"logo\":\"https://img.alicdn.com/tfs/TB11B9iM7voK1RjSZPfXXXPKFXa-338-80.png\",\"searchText\":\"\"},\"menuButtons\":[{\"isLogin\":\"true\",\"isSearch\":\"false\",\"link\":\"https://free.aliyun.com/\",\"text\":\"免费注册，享免费套餐\"},{\"link\":\"https://m.aliyun.com/product/search\",\"isSearch\":true}],\"channels\":[{\"spmId\":\"hotproducts\",\"channelStr\":\"hotproducts\",\"maxShowNum\":\"4\",\"isOpenSubChannel\":\"true\",\"link\":\"javascript:void(0);\",\"title\":\"热门产品\",\"subChannelLayout\":\"grid\",\"subChannels\":[{\"spmId\":\"0\",\"tce_rule_count\":\"1\",\"link\":\"https://www.aliyun.com/product/ecs?utm_source=mmenu\",\"title\":\"云服务器ECS\",\"isOpenThreeChannel\":\"false\"},{\"spmId\":\"0\",\"tce_rule_count\":\"1\",\"link\":\"https://www.aliyun.com/activity/new\",\"title\":\"新人特惠\",\"isOpenThreeChannel\":\"false\"},{\"spmId\":\"0\",\"tce_rule_count\":\"1\",\"link\":\"https://free.aliyun.com/\",\"title\":\"免费试用\",\"isOpenThreeChannel\":\"false\"}",10));
//        System.out.println(MsgUtils.cleanUrl("https://www.aliyun.com/"));

//        System.out.println(MsgUtils.getFirstSplit("蜂蜜旅行 旅游 使用通证解决跨境支付和用户激励的跨境旅游公司"));
//        System.out.println(MsgUtils.cleanUkMark("htpps://www.aliyun.com?a=5"));
    }

}
