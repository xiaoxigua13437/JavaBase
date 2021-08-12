package com.fzy.jdk8.atomic;


import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * AtomicLong是作用是对长整形进行原子操作。
 * 在32位操作系统中，64位的long 和 double 变量由于会被JVM当作两个分离的32位来进行操作，所以不具有原子性。而使用AtomicLong能让long的操作保持原子型
 *
 * @author yushu.zhao
 * @create 2021-08-11 09:43
 */
public class AtomicLongTest {


    public static void main(String[] args){
        //1、创建具有初始值 0 的新 AtomicLong,AtomicInteger。
        AtomicLong atomicLong = new AtomicLong(10);
        atomicLong.getAndAdd(3);
        System.out.println("Value：" + atomicLong.get());



    }







}
