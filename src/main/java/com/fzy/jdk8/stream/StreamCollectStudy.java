package com.fzy.jdk8.stream;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * collect 是一个非常有用的终端操作，它可以将流中的元素转变成另外一个不同的对象，例如一个List,Set或Map
 *
 * @author yushu.zhao
 * @create 2021-04-26 10:33
 */
public class StreamCollectStudy {


    public static void test1(){
        List<Person> filtered = Person.init()
                .stream()
                .filter(p -> p.name.startsWith("P"))
                .collect(Collectors.toList());

        System.out.println(filtered);
    }



    public static void test2(){
        Map<Integer,List<Person>> personsByAge = Person.init()
                .stream()
                .collect(Collectors.groupingBy(p -> p.age));// 以年龄为 key,进行分组

        personsByAge
                .forEach((age, p) -> System.out.format("age %s: %s\n", age, p));

    }












     static class Person {

        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return name;
        }

        public static List<Person> init(){

            // 构建一个 Person 集合
            List<Person> persons =
                    Arrays.asList(
                            new Person("Max", 18),
                            new Person("Peter", 23),
                            new Person("Pamela", 23),
                            new Person("David", 12));

            return persons;
        }

    }




}
