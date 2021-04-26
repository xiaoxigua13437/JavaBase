package com.fzy.example;

//import com.sun.tools.doclets.internal.toolkit.builders.AbstractBuilder;
import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author fuzhongyu
 * @date 2018/1/10
 */

public class Te1 {

    public static void main(String[] args) {

//        final int count=100000;
//        final String s="a";
//
//        long time1=System.currentTimeMillis();
//        String str1="";
//        for (int i=0;i<count;i++){
//            str1=str1+s;
//        }
//        System.out.println("----->time1"+(System.currentTimeMillis()-time1));
//
//        long time2=System.currentTimeMillis();
//        String str2="";
//        for (int i=0;i<count;i++){
//            str2=str2.concat(s);
//        }
//        System.out.println("----->time2"+(System.currentTimeMillis()-time2));
//
//        long time3=System.currentTimeMillis();
//        StringBuilder builder=new StringBuilder("");
//        for (int i=0;i<count;i++){
//            builder.append(s);
//        }
//        builder.toString();
//        System.out.println("----->time2"+(System.currentTimeMillis()-time3));

        Queue<String> queue=new ArrayDeque<>(2);
        queue.offer("a");
        queue.offer("b");
        System.out.println(queue.poll());
        System.out.println(Arrays.toString(queue.toArray()));

        Map map=new TreeMap();


    }
}
