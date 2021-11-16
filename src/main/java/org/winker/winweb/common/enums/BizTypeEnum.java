package org.winker.winweb.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：tom
 * @date ：Created in 2020/12/25 11:40 上午
 * @description：搜索一级类型
 * @modified By：
 * @version: $
 */
public enum BizTypeEnum {
    OVERFLOW("overflow", "stackOverFlow", null,1);

    BizTypeEnum(String value, String desc,Class infoClass,Integer number) {
        this.value = value;
        this.desc = desc;
        this.infoClass = infoClass;
        this.number = number;
    }

    private static Map<String, BizTypeEnum> valueMap = null;
    private String value;
    private String desc;
    private Class infoClass;
    private Integer number;

    public static BizTypeEnum getEnumByValue(String value) {
        initEnumMap();
        return valueMap.get(value);
    }

    static {
        initEnumMap();
    }

    private synchronized static void initEnumMap() {
        if (valueMap == null) {
            Map<String, BizTypeEnum> vm = new HashMap();
            for (BizTypeEnum bizType : BizTypeEnum.values()) {
                vm.put(bizType.getValue(), bizType);
            }
            valueMap = vm;
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Class getInfoClass() {
        return infoClass;
    }

    public void setInfoClass(Class infoClass) {
        this.infoClass = infoClass;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
