package com.fzy.jdk8.stream;


import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * Stream（流）是一个来自数据源的元素队列并支持聚合操作
 *
 *   1.元素是特定类型的对象，形成一个队列。 Java中的Stream并不会存储元素，而是按需计算
 *   2.数据源 流的来源。 可以是集合，数组，I/O channel， 产生器generator 等
 *   3.聚合操作 类似SQL语句一样的操作， 比如filter, map, reduce, find, match, sorted等
 *
 * @author YuShu
 * @create 2021-03-25 17:14
 */
public class StreamStudy {


    /**
     * ######################################################
     * #=========================jdk8====================#
     * ######################################################
     */



    /**
     * 生成流
     *
     * stream() − 为集合创建串行流
     * parallelStream() − 为集合创建并行流
     */
    public static void produceStream(){

        List<String> strings = Arrays.asList(new String[]{"a","b","","d","e",""});
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());//生成流并去除为空的数据
        System.out.println("filtered :" + filtered);

    }


    /**
     *
     * forEach() 来迭代流中的每个数据。以下代码片段使用 forEach 输出了10个随机数:
     */
    public static void forEach(){

        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);

    }

    /**
     *
     * filter() 方法用于通过设置的条件过滤出元素。以下代码片段使用 filter 方法过滤出空字符串：
     */
    public static void filter(){

        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        //获取非空字符串的数量
        long count = strings.stream().filter(s -> !s.isEmpty()).count();
        long count1 = strings.stream().filter(s -> StringUtils.isNotEmpty(s)).count();
        System.out.println("count:" + count1);

    }

    /**
     *
     * map() 方法用于映射每个元素到对应的结果，以下代码片段使用 map 输出了元素对应的平方数：
     */
    public static void map(){

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        //获取对应的平方数
        List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println("squaresList :" + squaresList);

    }


    /**
     *
     * sorted() 方法用于对流进行排序。以下代码片段使用 sorted 方法对输出的 10 个随机数进行排序：
     */
    public static void sorted(){

        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);

    }

    /**
     *
     * parallelStream 是流并行处理程序的代替方法。以下实例我们使用 parallelStream 来输出空字符串的数量：
     */
    public static void parallelStream(){
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        //获取非空字符串的数量
        long count = strings.parallelStream().filter(s -> !s.isEmpty()).count();
        System.out.println("count :" + count);

    }

    /**
     *
     * Collectors() 类实现了很多归约操作，例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串：
     */
    public static void collectors(){

        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选列表: " + filtered);
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);

    }


    /**
     * 统计
     *
     * 另外，一些产生统计结果的收集器也非常有用。它们主要用于int、double、long等基本类型上，它们可以用来产生类似如下的统计结果。
     */
    public static void statistics(){

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        IntSummaryStatistics stats  = numbers.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());

    }

    /**
     * 排序并取出前3条数据
     */
    public static void limitCount(){
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        strings = strings.stream().sorted().limit(3).collect(Collectors.toList());
        System.out.println(strings);
    }

    /**
     * 不同类型的 Stream 流
     */
    public static void distinctStream(){

        Arrays.asList("a1","a2","a3")
                .stream()// 创建流
                .findFirst()// 找到第一个元素
                .ifPresent(System.out::println);// 如果存在,即输出

        //原始类型流支持额外的终端聚合操作,sum()以及average()
        Arrays.stream(new int[]{1,2,3})
                .map(n -> 2 * n + 1)// 对数值中的每个对象执行 2*n + 1 操作
                .average()// 求平均值
                .ifPresent(System.out::println);// 如果值不为空，则输出

        //将常规对象流转换为原始类型流
        //这个时候,中间操作 mapToInt(),mapToLong() 以及mapToDouble就派上用场了:
        Stream.of("a1","a2","a3")
                .map(s -> s.substring(1))// 对每个字符串元素从下标1位置开始截取
                .mapToInt(Integer::parseInt)// 转成 int 基础类型类型流
                .max()// 取最大值
                .ifPresent(System.out::println);// 不为空则输出

        //将双精度流首先转换成 int 类型流,然后再将其装换成对象流
        Stream.of(1.0,2.0,3.0)
                .mapToInt(Double::intValue)// double 类型转 int
                .mapToObj(i -> "a" + i)// 对值拼接前缀 a
                .forEach(System.out::println);// 打印




    }





    public static void ifPresent(){

        List<String> strings = Arrays.asList("aaa", "abbb", "acc", "ddd");
        System.out.println("原始值" + strings);

        Optional<String> largest=strings.stream().max(String::compareToIgnoreCase);

        List<String> bb=new ArrayList();
        largest.ifPresent(bb::add);

        System.out.println("ifPresent 的用法："+bb);

        Optional<Boolean> added=largest.map(bb::add);
        System.out.println("会有返回值处理:"+added.get());


    }

    public static void test(){
        List<String> l1 = new ArrayList<>();
        List<String> strings = Arrays.asList("aaa", "abbb", "acc", "ddd");
        //lambda(增强for)
        strings.forEach(m ->{
            l1.add(m);
        });

        System.out.println(l1);
    }


    /**
     * ######################################################
     * #=========================jdk7====================#
     * ######################################################
     */


    /**
     * 获取空字符串
     *
     * @param strings 需要处理字符串
     * @return
     */
    private static int getCountEmptyStringUsingJava7(List<String> strings){

        int count = 0;
        for (String s : strings){
            if (s.isEmpty()){
                count++;
            }
        }
        return count;
    }

    /**
     * 统计字符串长度为3的数量
     *
     * @param strings 需要处理字符串
     * @return
     */
    private static int getCountLength3UsingJava7(List<String> strings){

        int count = 0;
        for (String s : strings){
            if (s.length() == 3){
                count++;
            }
        }
        return count;
    }

    /**
     * 去除空字符串
     *
     * @param strings 需要处理字符串
     * @return
     */
    private static List<String> deleteEmptyStringsUsingJava7(List<String> strings){

        List<String> filteredList = new ArrayList<>();
        for (String s : strings){
            if (s.isEmpty()){
                filteredList.add(s);
            }
        }
        return filteredList;
    }

    /**
     * 删除空字符串，并使用逗号把它们合并起来
     *
     * @param strings 需要处理字符串
     * @param separator 分割符
     * @return
     */
    private static String getMergedStringUsingJava7(List<String> strings , String separator){

        StringBuilder stringBuilder = new StringBuilder();
        for (String s : strings){
            if (!s.isEmpty()){
                stringBuilder.append(s);
                stringBuilder.append(separator);
            }
        }
        String mergedString = stringBuilder.toString();
        return mergedString.substring(0, mergedString.length()-2);

    }


    /**
     * 获取列表元素平方数
     *
     * @param numbers 列表元素
     * @return
     */
    private static List<Integer> getSquares(List<Integer> numbers){

        List<Integer> squaresList = new ArrayList<Integer>();

        for (Number n : numbers){
            Integer square = new Integer(n.intValue() * n.intValue());
            if (!squaresList.contains(square)){
                squaresList.add(square);
            }
        }
        return squaresList;

    }


    /**
     * 获取列表元素的最大值
     *
     * @param numbers 列表元素
     * @return
     */
    private static int getMax(List<Integer> numbers){

        int max = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++){
            Integer number = numbers.get(i);
                if (number > max){
                    max = number;
                }
        }
        return max;

    }

    public static void main(String[] args){


        /*List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");

        // 删除空字符串，并使用逗号把它们合并起来
        String mergedString = getMergedStringUsingJava7(strings,", ");
        System.out.println("合并字符串: " + mergedString);

        strings.stream().filter(s -> s.startsWith("a"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);*/

        /*StreamStudy.distinctStream();*/
/*
        StreamStudy.filter();
*/
        BigDecimal b1 = new BigDecimal("64.7024");//24.698,24.702
        BigDecimal b2 = new BigDecimal("4");

        System.out.println(b1.add(b2));
        System.out.println(b1.subtract(b2));
        System.out.println(b1.multiply(b2));
        //结果：6.1755。除法保留有效位，不然容易报错
        //ROUND_HALF_UP:四舍五入(一般采用这个)
        System.out.println(b1.divide(b2,2,BigDecimal.ROUND_HALF_UP));
        /*//ROUND_HALF_DOWN:四舍五入，如果结果小数位只比保留位多一位且最后一位为5则不进位
        System.out.println(b1.divide(b2,3,BigDecimal.ROUND_HALF_DOWN));
        //ROUND_UP:始终加一
        System.out.println(b1.divide(b2,3,BigDecimal.ROUND_UP));
        //ROUND_DOWN:直接截取
        System.out.println(b1.divide(b2,3,BigDecimal.ROUND_DOWN));*/




    }


    public static void test1(){

        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                });
            /*.forEach(s -> System.out.println("forEach: " + s));*/

    }

}
