package com.fzy.jdk8.atomic;


import com.fzy.thread.thread_pool.ExcutorProcessPool;

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
        //1、getAndAdd()方法:先获取当前值再加上特定的值
        AtomicLong atomicLong = new AtomicLong(10);
        atomicLong.getAndAdd(3);
        System.out.println("Value：" + atomicLong.get());

        //2、addAndGet()方法:以原子方式将给定值添加到当前值,先加上特定的值，再获取结果
        AtomicLong atomicLong2 = new AtomicLong(3);
        atomicLong2.addAndGet(5);
        System.out.println("Value：" + atomicLong2.get());

        //3、compareAndSet()方法:如果当前值 == 预期值，则以原子方式将该值设置为给定的更新值
        AtomicLong atomicLong3 = new AtomicLong(10);
        atomicLong3.compareAndSet(10,15);
        System.out.println("Value：" + atomicLong3.get());

        //4、getAndSet()方法:以原子方式设置为给定值，并返回旧值
        AtomicLong atomicLong4 = new AtomicLong(10);
        atomicLong4.getAndSet(6);
        System.out.println("Value：" + atomicLong4.get());






    }







}
