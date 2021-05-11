package com.fzy.flume;

import cn.hutool.core.util.NumberUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Test {

    private Integer num;

    private Integer age;

    private String name;

    private String nameId;

    public static List<Test> tList = new ArrayList<>();

    static {

        Test t1 = new Test(1,12,"mack","1");
        Test t2 = new Test(1,12,"mack","2");
        Test t3 = new Test(1,12,"mack","3");
        Test t4 = new Test(1,12,"mack","4");
        Test t5 = new Test(1,12,"mack","5");
        /*tList.add(t1);
        tList.add(t2);
        tList.add(t3);
        tList.add(t4);
        tList.add(t5);*/
        tList = Arrays.asList(t1,t2,t3,t4,t5);

    }


    /**
     * MapValue(相同的key取精度并保留两位小数)
     *
     */
    private static Map<String, BigDecimal> mapValueDivision(Map<String, Integer> m1, Map<String, Integer> m2) {
        Map<String, BigDecimal> result = new HashMap<>();
        for (String sourceKey : m1.keySet()) {
            if (m2.containsKey(sourceKey)) {
                BigDecimal floorRate = NumberUtil.div(m1.get(sourceKey),m2.get(sourceKey),2);
                result.put(sourceKey, floorRate);
            }
        }
        return result;
    }


    /**
     * 计算概率
     *
     * @param l1 参数
     * @param l2 参数
     * @return rate
     */
    private static Map<String,BigDecimal> getRate(List<Test> l1, Collector<Test,?,Map<String,List<Test>>> l2){
        Map<String, BigDecimal> map = new HashMap<>();
        Map<String,List<Test>> tMap = l1.parallelStream().collect(l2);
        tMap.forEach((key, val) ->{
            Integer num = val.stream().collect(Collectors.summingInt(Test::getNum));
            Integer age = val.stream().collect(Collectors.summingInt(Test::getAge));
            map.put(key,NumberUtil.div(num,age,2));
        });
        return map;
    }



    public static void main(String[] args){

        /*Map<String,Integer> m1 = tList.stream().collect(Collectors.groupingBy(Test::getName, Collectors.summingInt(Test::getNum)));
        Map<String,Integer> m2 = tList.stream().collect(Collectors.groupingBy(Test::getName, Collectors.summingInt(Test::getAge)));

        Map<String,Integer> m3 = tList.stream().collect(Collectors.groupingBy(Test::getNameId, Collectors.summingInt(Test::getNum)));
        Map<String,Integer> m4 = tList.stream().collect(Collectors.groupingBy(Test::getNameId, Collectors.summingInt(Test::getAge)));*/


        /*Map<String,BigDecimal> b1 = Test.getRate(tList,Collectors.groupingBy(Test::getName));
        System.out.println(b1+"\n");
        Map<String,BigDecimal> b2 = Test.getRate(tList,Collectors.groupingBy(Test::getNameId));
        System.out.println(b2);*/


        List<Students> list = tList.parallelStream().map(e ->{
            String name = e.getName();
            Integer num = 5;
            return new Students(name,"12",num > 6?num:2);
        }).collect(Collectors.toList());

        System.out.println(list);


    }






}


