package com.fzy.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义工具类
 *
 * @author yushu.zhao
 * @create 2021-05-31 13:36
 */
public class ExceptionUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionUtil.class);


    /**
     * @author yin
     * @param paramName
     * @param clazz
     * @param thread
     * @Decrease 参数为空时的异常日志打印
     */
    public static void  infoParamNullLogPrint(String paramName,Class clazz,Thread thread){
        String errMsg = "参数：" + paramName + "为空，请检查传输数据是否为Null!";
        LogUtil.info(errMsg,thread.getStackTrace());
    }

    /**
     * @author yin
     * @param paramName
     * @param clazz
     * @param thread
     * @Decrease 参数为空时的异常日志打印
     */
    public  static void errorParamNullLogPrint(String paramName,Class clazz,Thread thread){
        String errMsg="空指针异常警告！参数：" + paramName + "为空，请检查传参过程是否出现错误!";
        StackTraceElement[] a=thread.getStackTrace();
        LogUtil.error(errMsg,a);
    }

    /**
     * @author yin
     * @param paramName
     * @param clazz
     * @param thread
     * @Decrease 参数为空时的异常日志打印
     */
    public  static void debugParamNullLogPrint(String paramName,Class clazz,Thread thread){
        String errMsg = "参数：" + paramName + "为空，请检查传输数据是否为Null!";
        LogUtil.debug(errMsg,thread.getStackTrace());
    }




}
