package org.winker.utils.database;

public class StringUtil {


    /**
     * 首字母大写
     */
    public static String convertToFU(String param){
        return param.replaceFirst(param.substring(0, 1), param.substring(0, 1).toUpperCase());
    }

    /**
     * 首字母小写
     */
    public static String convertToFL(String param){
        return param.replaceFirst(param.substring(0, 1), param.substring(0, 1).toLowerCase());
    }

    /**
     * 驼峰规则转化
     * @return
     */
    public static String convertToHump(String name){
        StringBuilder sb = new StringBuilder();
        String[] temp = name.split("_");
        for(int i=0;i<temp.length;i++){
            if(i==0){
                sb.append(convertToFL(temp[i].toLowerCase()));
            }else{
                sb.append(convertToFU(temp[i].toLowerCase()));
            }
        }
        return sb.toString();
    }

}
