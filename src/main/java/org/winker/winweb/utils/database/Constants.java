package org.winker.winweb.utils.database;

/**
 * 系统中的常量
 *
 * @ClassName: Constants
 * @Author: lingjin.flj
 * @Descrption:
 * @Date: 10:24 AM
 */
public class Constants {

    public final static String xxx = "xxx";

    public final static String UK_SEPARATOR = "@@";

    public final static String UL_SEPARATOR = "_";

    public final static String TAB_SEPARATOR = "\t";

    public final static String COMMA_SEPARATOR = ",";

    public final static String SPACE_SEPARATOR = " ";

    public final static String SEMI_SEPARATOR = ";";

    public final static String SLASH_SEPARATOR = "/";

    public final static String SPOT_SEPARATOR = ".";

    public final static String CLEAN_PARTEN = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

    public final static String ERROR_PREFIX_RECALL_NOT_FIT = "error_prefix_recall_not_fit";

    public final static String OP_STATUS_OK = "OK";
    public final static String OP_STATUS_FAIL = "FAIL";

    //No index in query 参考 https://help.aliyun.com/document_detail/193826.html
    public final static String OP_ERROR_CODE_NO_INDEX = "2112";
    public final static String OP_ERROR_CODE_TIME_OUT = "1000";
    public final static String OP_KEY_ERRORS = "errors";
    public final static String OP_KEY_CODE = "code";



    public final static int MAX_QUERY_LEN = 64;

    public final static int MAX_QUERY_TAG_RECALL_NUM = 1024;

    public final static int COMPLEX_QUERY_LEN = 24;

    public final static String DIAMOND_GROUP = "search-service-aliyun";

    public final static String DIAMOND_ID_D_C_V = "disableCacheVersion";

    public final static String DIAMOND_ID_B_W_L = "blackWordList";

    public final static String DIAMOND_ID_W_W_L = "whiteWordList";

    public final static String UNIT_YUAN = "元";

    public final static String REQ_ID_PREFIX = "reqId:";

    public final static String URL_SPM = "spm=";

    public final static String URL_SCM = "scm=";

    public final static String Q_MARK = "?";

    public final static String HASH_MARK = "#";

    public final static double EPS = 1e-5;

    public final static double OPENSEARCH_BIAS = 10000;

    public final static int TAIR_NAMESPASE = 1545;

}
