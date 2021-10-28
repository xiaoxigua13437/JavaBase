package com.fzy.jdk8.stream;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * JDK8对List进行分组操作（stream的groupBy）,group by生成一个拥有分组功能的Collector，有三个重载方法
 * 注:
 * 1.需要一个参数：按照该参数进行分组。结果返回一个Map集合，每个Map的key默认是分组参数的类型，value是一个List集合
 * 2.需要两个参数：第二参数是Collector类型，可以对value进行处理
 * 3.需要三个参数，第三个参数添加了对结果Map的生成方式，默认是HashMap
 *
 * @author yushu.zhao
 * @create 2021-04-25 13:16
 */
public class TestGroupBy {


    @Data
    public static class User {
        private Integer id;
        private Integer schoolId;
        private String userName;
        private String edu;
        private double price;
    }
    public static List<User> users = new ArrayList<>();

    static {
        TestGroupBy.User u1 = new TestGroupBy.User();
        u1.setId(1001);
        u1.setSchoolId(100);
        u1.setUserName("小1");
        u1.setEdu("001");
        u1.setPrice(0.01);

        TestGroupBy.User u2 = new TestGroupBy.User();
        u2.setId(1002);
        u2.setSchoolId(100);
        u2.setUserName("小2");
        u2.setEdu("002");
        u2.setPrice(0.20);

        TestGroupBy.User u3 = new TestGroupBy.User();
        u3.setId(2010);
        u3.setSchoolId(200);
        u3.setUserName("小3");
        u3.setEdu("001");
        u3.setPrice(3.00);

        TestGroupBy.User u4 = new TestGroupBy.User();
        u4.setId(3001);
        u4.setSchoolId(300);
        u4.setEdu("001");
        u4.setPrice(40.0);
        u3.setUserName("小4");

        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
    }

    /**
     * 需要一个参数:按照该参数进行分组。结果返回一个Map集合，每个Map的key默认是分组参数的类型，value是一个List集合
     */
    public static void test1(){
        Map<String,List<User>> collect = users.stream().collect(Collectors.groupingBy(User::getEdu));
    }


    public static void test2(){

        Map<String,List<Integer>> collect = users.stream().collect(Collectors.groupingBy(User::getEdu,
                //第二个参数对Map的value进行处理（映射）
                Collectors.mapping(User::getId, Collectors.toList())));

    }


    public static void test3(){

        Map <String,Double> collect = users.stream().collect(Collectors.groupingBy(User::getEdu,
                //对参数进行累计求和
                Collectors.summingDouble(User::getPrice)));
        System.out.println(collect);

    }


    public static void test4(){
        //对结果的统计
        Map <String,Long> collect = users.stream().collect(Collectors.groupingBy(User::getEdu,
                //获取count数量
                Collectors.counting()));

        collect.forEach((s, a) -> {
            System.out.println(s+"\n");
            System.out.println(a+"\n");

        });
        System.out.println(collect);

    }


    public static void test5(){

        //取出对象中的属性值
        List<String> s = users.stream().map(e -> e.getUserName()).collect(Collectors.toList());
        System.out.println(s);
    }

    /**
     * MapValue相除
     */
    public static Map<String, Integer> mapValueDivision(Map<String, Integer> m1, Map<String, Integer> m2) {
        Map<String,BigDecimal> map = new HashMap<>();
        for (String sourceKey : m1.keySet()) {
            if (m2.containsKey(sourceKey)) {
                m2.put(sourceKey, m1.get(sourceKey) / m2.get(sourceKey));
            }
        }
        System.out.println(m2);
        return m2;
    }




    /**
     *
     * @param dou
     */
    public static int DoubleFormatInt(Double dou){
        DecimalFormat df = new DecimalFormat("##0.00");
        return Integer.parseInt(df.format(dou));
    }


    public static void main(String[] args) {


        BigDecimal a =null;
        Integer faultRate = 6;
        a = BigDecimal.valueOf(faultRate.doubleValue()/3);
        BigDecimal b =a.setScale(2, RoundingMode.HALF_UP);//保留两位小数
        System.out.println("结果是"+b);
        //结果转化成百分比
        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(2);
        System.out.println(percent.format(b.doubleValue()));




        /*Map<String,Integer> a = new HashMap<>();
        a.put("1",1);
        Map<String,Integer> b = new HashMap<>();
        b.put("1",2);
        TestGroupBy.mapValueDivision(a,b);*/



        /*List<String> lists=new ArrayList<>();
        lists.add("a");
        lists.add("b");
        lists.add("a");
        lists.add("a");
        //将最终结果映射为LinkedHashSet结构。
        LinkedHashSet<String> collect = lists.stream().
                collect(Collectors.toCollection(LinkedHashSet::new));
        System.out.println(collect);*/
    }

}
