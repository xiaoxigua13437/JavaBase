package com.fzy.mixed_block.collect;

import java.util.*;

/**
 * list和map常用遍历方式
 *
 * @author yushu.zhao
 * @create 2019-04-20 14:35
 */
public class ErgodicDataByMuster {



    /**
     * 总结
     * 注:循环遍历list有三种方式
     * 1.循环删除list中特定一个元素的,可以使用三种方式中的任意一种,但在使用中要注意上面分析的各个问题。
     * 2.循环删除list中多个元素的，应该使用迭代器iterator方式。
     * 3.当使用ArrayList时，我们可以使用iterator实现遍历删除；而当我们使用CopyOnWriteArrayList时，我们直接使用增强型for循环遍历删除即可，此时使用iterator遍历删除反而会出现问题。
     */
    public static void removeParams(){

        List<String> l = Arrays.asList("aaa", "abbb", "acc", "ddd");
        //增强for
        for(String x:l){
            if(x.equals("del"))
                l.remove(x);
        }
        //for循环遍历list
        for(int i=0;i<l.size();i++){
            if(l.get(i).equals("del"))
                l.remove(i);
        }
        //iterator遍历
        Iterator<String> it = l.iterator();
        while(it.hasNext()){
            String x = it.next();
            if(x.equals("del")){
                it.remove();
            }
        }

    }


    /**
     * map的3种遍历方式
     *
     * @return strMap
     */
    public static Map<String,Object> getParams(){

        Map<String,Object> strMap = new HashMap<String,Object>();
        strMap.put("a",1);
        strMap.put("b",2);
        strMap.put("c",3);
        //普通循环
        for (String key : strMap.keySet()){
            String value = strMap.get(key).toString();//
            System.out.println("key:"+key+" value:"+value);
        }
        //lambda表达式
        strMap.forEach((key, value) -> {
            System.out.println(key+"::"+value);
        });
        //Map集合循环遍历方式三 推荐,尤其是容量大时
        for (Map.Entry<String,Object> m : strMap.entrySet()){
            System.out.println("key:" + m.getKey() + " value:" + m.getValue());
        }
        return strMap;

    }





    public static void main(String[] args){

        //ErgodicList.removeParams();

    }



}
