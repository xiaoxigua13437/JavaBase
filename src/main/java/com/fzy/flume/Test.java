package com.fzy.flume;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.fzy.utils.ListUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {

    private Integer num;

    private Integer age;

    private Integer point;

    private String name;

    private String nameId;

    public static List<Test> tList = new ArrayList<>();

    static {

        Test t1 = new Test(1,11,2,"mack","1");
        Test t2 = new Test(1,11,1,"mack","2");
        Test t3 = new Test(2,9,4,"mack","3");
        Test t4 = new Test(2,9,3,"mack","4");
        Test t5 = new Test(3,10,5,"mack","5");
        /*tList.add(t1);
        tList.add(t2);
        tList.add(t3);
        tList.add(t4);
        tList.add(t5);*/
        tList = Arrays.asList(t1,t2,t3,t4,t5);

    }

    /*private Map<String,BigDecimal> mapToAccuracy(List<?> list,Collector<>){

        Map<String,BigDecimal> bigDecimalMap = new HashMap<>();



    }

*/


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
            Integer num = val.stream().mapToInt(Test::getNum).sum();
            Integer age = val.stream().mapToInt(Test::getAge).sum();
            map.put(key,NumberUtil.div(num,age,2));
        });
        return map;
    }


    /**
     * 获取重复元素
     * @param stream
     * @param <T>
     * @return
     */
    public static <T> List<T> getDuplicateElements(Stream<T> stream) {
        return stream
                .collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b)) // 获得元素出现频率的 Map,键为元素，值为元素出现的次数
                .entrySet().stream() // Set<Entry>转换为Stream<Entry>
                .filter(entry -> entry.getValue() > 1) // 过滤出元素出现次数大于 1 的 entry
                .map(entry -> entry.getKey()) // 获得 entry 的键（重复元素）对应的 Stream
                .collect(Collectors.toList()); // 转化为 List
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


        /*List<Students> list = tList.parallelStream().map(e ->{
            String name = e.getName();
            Integer num = 5;
            return new Students(name,"12",num > 6?num:2);
        }).collect(Collectors.toList());

        System.out.println(list);*/



        /*Map<String, List<Test>> listMap = tList.parallelStream().filter(r -> Objects.nonNull(r.getNum()))
                .filter(a-> Objects.nonNull(a.getAge()))
                .collect(Collectors.groupingBy(Test::getName));


        System.out.println(listMap);*/
        /*List<Test> list = new ArrayList<>();

        Map<Integer,List<Test>> listMap = tList.stream().collect(Collectors.groupingBy(Test::getNum));
        listMap.forEach((integer, testList) -> {
            Comparator<Test> comparator = Comparator.comparing(Test::getPoint);
            Test test = testList.parallelStream().max(comparator).get();
            list.add(test);
        });

        list.forEach(test1 -> {
            System.out.println(test1.toString() + "\r\n");
        });
    */
        //主要实现如果一个数字为超过3位,则会在其前面补零以到达规定的位数,其中o是被填充到缺省位的数字,3代表规定数字的总位数  d代表是整型
        //System.out.println(String.format("%03d",1));


        /*System.out.println(StringUtils.join(tList.toArray(),","));


        long current = System.currentTimeMillis();
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();

        System.out.println(zero);
        System.out.println(new Date().getTime());*/

        /*String str = "";
        List<String> l = Arrays.asList(str.split(",",1));
        for (String s : l){
            System.out.println(s);
        }*/
        List<Test> list = new ArrayList<>();

        Map<Integer,List<Test>> listMap = tList.stream().collect(Collectors.groupingBy(Test::getNum));
        listMap.forEach((integer, testList) -> {
            Comparator<Test> comparator = Comparator.comparing(Test::getPoint);
            Test test = testList.parallelStream().max(comparator).orElse(new Test());
            list.add(test);
        });
        System.out.println(JSON.toJSON(list));













    }
    /**
     * 根据指定字符分割字符串
     *
     * @param dataStr
     * @param s
     * @return
     */
    public static String[] getArrayByChar(String dataStr, String s) {
        return dataStr.trim().replaceAll("\\s*", "").split(s);
    }



}


