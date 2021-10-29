package com.fzy.jdk8.stream;


import java.util.*;
import java.util.stream.Collectors;

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
public class Stream {


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
        System.out.println("count:" + count);

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



    public static Map<String,Object> getParams(){

        Map<String,Object> strMap = new HashMap<String,Object>();
        strMap.put("a",1);
        strMap.put("b",2);
        strMap.put("c",3);

        strMap.forEach((key, value) -> {
            System.out.println(key+"::"+value);
        });

        return strMap;
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
     * @return
     */
    private static String getMergedStringUsingJava7(List<String> strings){

        StringBuilder stringBuilder = new StringBuilder();
        for (String s : strings){
            if (!s.isEmpty()){
                stringBuilder.append(s);
                stringBuilder.append(", ");
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


        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");

        // 删除空字符串，并使用逗号把它们合并起来
        String mergedString = getMergedStringUsingJava7(strings);
        System.out.println("合并字符串: " + mergedString);

        //Stream.statistics();
    }


}
