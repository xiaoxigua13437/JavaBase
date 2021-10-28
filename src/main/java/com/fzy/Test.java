package com.fzy;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Fucai
 * @date 2018/6/4
 */

public class Test {

  public static void main(String[] args) {
    /*int i=1;
    Map<String,Object> map=new HashMap<>();
    map.put("q",10);
    map.put("a",11);
    map.put("a",12);*/

    String uuid = UUID.randomUUID().toString();
    System.out.println(uuid.toUpperCase().replace("-", ""));



  }

}
