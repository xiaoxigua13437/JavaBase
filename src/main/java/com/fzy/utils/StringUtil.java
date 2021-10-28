package com.fzy.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author yushu.zhao
 * @create 2021-05-20
 */
public class StringUtil {


    public static boolean isEmpty(Object value){
        return (value == null || "".equals(value));
    }

    public static boolean isNotEmpty(Object value) {
        return (!isEmpty(value));
    }


    /**
     * 比较两个非空字符串是否相同
     */
    public static boolean compare(String str1,String str2){
        if (isEmpty(str1) || isEmpty(str2) ){
            return false;
        }
        return str1.equals(str2);
    }


    /**
     *
     * 检验两个非空字符串是否equal,有一个为null时返回false
     */
    public static boolean isEquals(String str1,String str2){
        if (str1 == null || str2 == null){
            return  false;
        }
        return str1.equals(str2);
    }




    /**
     * 将map转换成url参数格式 ,如 name=aaa&age=12
     *
     */
    public static String getUrlParamsByMap(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value=entry.getValue();
            if (value instanceof String){
                value=((String) value).replace(" ","");
            }
            buffer.append(entry.getKey()).append("=").append(value).append("&");
        }
        String returnStr = buffer.toString();
        if (returnStr.endsWith("&")) {
            returnStr = StringUtils.substringBeforeLast(returnStr, "&");
        }
        return returnStr;
    }


    /**
     * 替换URL参数中的值
     */
    public static String replaceToken(String url, String name, String value) {
        if (isNotEmpty(url) && isNotEmpty(value)) {
            int index = url.indexOf(name + "=");
            if (index != -1) {
                StringBuilder sb = new StringBuilder();
                sb.append(url.substring(0, index)).append(name + "=").append(value);
                int idx = url.indexOf("&", index);
                if (idx != -1) {
                    sb.append(url.substring(idx));
                }
                url = sb.toString();
            }

        }
        return url;
    }



    /**
     *
     * @author yin
     * @date 2018年12月11日
     * @className StringUtil
     * @methodNname appearNumber
     * @returnType int
     * @param content 字符串
     * @param regex 子串
     * @description 匹配字符串中子串出现的次数
     */
    public static int appearNumber(String content,String regex) {
        int count = 0;
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(content);
        while (m.find()) {
            count++;
        }
        return count;
    }

    /**
     *
     * @param ori
     * @param cut
     *
     */
    public static String subString(String ori,String cut){
        if (isEmpty(ori) || isEmpty(cut)){
            return ori;
        }
        Integer length = cut.length();
        Integer index = ori.indexOf(cut);
        Integer subIndex = index+length;
        if (index<0) {
            subIndex = 0;
        }
        String after = ori.substring(subIndex);
        System.out.println("index:"+index+"; after:"+after);
        return after;
    }

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.toUpperCase().replace("-", "");
    }

    public static void main(String[] args){

        int a = StringUtil.appearNumber("h/e/l/lo","/");
        System.out.println(a);

    }


}
