package com.fzy.mixed_block.plusClass;

import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 字符串工具-StrUtil
 *
 * @author yushu.zhao
 * @create 2021-04-22 16:36
 */
public class StrUtilTest {


    private static long totalNum = 0;


    /**
     *
     */
    public static void format(){
        String template = "第{}层合格率:{}";
        String str = StrUtil.format(template,"1","20%");
        System.out.println(str);
    }



    public static void main(String[] args){

        System.out.println("AB".indexOf("A"));

    }





}
