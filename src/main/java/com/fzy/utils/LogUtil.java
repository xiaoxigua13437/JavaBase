package com.fzy.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * 日志工具类
 *
 * @author yushu.zhao
 * @create 2021-05-31 13:39
 */
@Slf4j
public class LogUtil {

    /**
     * 日志醒目线,info以下级别使用
     */
    public static final String LOG_ALARM_LINE = "\n"
            + " - >>>>>>>>>>>>>>>>>>>>日志醒目线>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n"
            + " - >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";

    public static final String LOG_ERROR_LINE ="\n"
            + " - >>>>>>>>>>>>>>>>>>>>日志警告线>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n"
            + " - >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
    public static final String Log_FINISH_LINE = " - ____________________日志完结线________________________________________________________________________________";

    /**
     * 定义日志等级
     */
    public static  final String LOG_LEVEL_INFO="INFO";
    public static  final String LOG_LEVEL_DEBUG="DEBUG";
    public static  final String LOG_LEVEL_ERROR="ERROR";

    /**
     *
     *
     * @date 2018年12月21日
     * @className LogUtil
     * @methodNname appendStackTraceElements
     * @returnType String
     * @param stackTraceElements
     * @return
     * @description 打印栈内信息
     */
    public static String appendStackTraceElements(StackTraceElement[] stackTraceElements) {
        StringBuffer e=new StringBuffer();
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            e=e.append("at ").append(stackTraceElement.getClassName()).append(".").append(stackTraceElement.getMethodName()).append("(").append(stackTraceElement.getFileName()).append(":").append(stackTraceElement.getLineNumber()).append( ")").append("\n");
        }
        return e.toString();
    }

    /**
     *
     * @Descripton info级别日志打印
     */
    public static void info(String msg, StackTraceElement[] stackTraceElements) {
        String stackTraceValue=appendStackTraceElements(stackTraceElements);
        log.info(LOG_ERROR_LINE + "\n" + msg + stackTraceValue);
    }

    /**
     *
     * @Descripton debug级别日志打印
     */
    public static void debug(String msg, StackTraceElement[] stackTraceElements) {
        String stackTraceValue=appendStackTraceElements(stackTraceElements);
        log.debug(LOG_ERROR_LINE + "\n" + msg + stackTraceValue);
    }

    /**
     * 
     * @Descripton error级别日志打印
     */
    public static void error(String msg, StackTraceElement[] stackTraceElements) {
        String stackTraceValue=appendStackTraceElements(stackTraceElements);
        log.error(LOG_ERROR_LINE + "\n" + msg + stackTraceValue);
    }
}
