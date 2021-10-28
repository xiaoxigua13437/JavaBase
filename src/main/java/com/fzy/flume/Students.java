package com.fzy.flume;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fzy.utils.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Students {

    private String name;

    private String age;

    private Integer num;

    /*private List<Test> list;*/

    /*public static void test(){
        Students s = new Students("无双","12",1);
        String place = StrUtil.format("{}{}", s.getName(),s.getAge());

        System.out.println(place);
    }


    public static void main(String[] args) throws IllegalAccessException {
        Students s = new Students("a","12",1);
        ObjectUtil.objectToMap(s);
    }*/


    public static void main(String[] args){

        /*Students s = new Students("无双","12",1,null);
        String str = StrUtil.format("{}{}", s.getName(),s.getAge());*/


        /*List<Test> tests = Test.tList;
        Students s = new Students("2","12",1,tests);

        String str = JSON.toJSONString(s);
        Students e = JSON.parseObject(str,Students.class);

        

        System.out.println(e);
*/

       /* List<String> s1 = new ArrayList<>();
        s1.add("1");
        List<String> s2 = new ArrayList<>();
        s2.add("2");

        s2.stream().sequential().collect(Collectors.toCollection(() -> s1));
        System.out.println(JSON.toJSON(s1));*/

        /*long l = Long.valueOf("1626852106000").longValue();
        System.out.println(new Date(l));*/

        /*Map<Integer, String> map = new TreeMap<>();

        map.put(2,"world");
        map.put(1,"hello");
        map.put(3,"hi");

        System.out.println(JSON.toJSON(map));*/

        List<Students> studentsList = new ArrayList<>();
        Students s1 = new Students("无双","12",1);
        studentsList.add(s1);
        Students s2 = new Students("无双","13",1);
        studentsList.add(s2);
        Students s3 = new Students("无双","14",1);
        studentsList.add(s3);

        List<String> strList = new ArrayList<>();
        strList.add("12");
        strList.add("14");

        List<Students> list = studentsList.stream().filter(s -> !strList.contains(s.getAge())).collect(Collectors.toList());

        System.out.println(JSONObject.toJSON(list));



    }




}
