package com.fzy.mixed_block.plusClass;

import cn.hutool.core.util.StrUtil;

/**
 *
 * 字符串工具-StrUtil
 *
 * @author yushu.zhao
 * @create 2021-04-22 16:36
 */
public class StrUtilTest {


    /**
     *
     */
    public static void format(){
        String template = "{}爱{}，就像老鼠爱大米";
        String str = StrUtil.format(template,"我","你");
        System.out.println(str);
    }







}
