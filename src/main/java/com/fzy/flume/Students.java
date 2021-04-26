package com.fzy.flume;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Students {

    private String name;

    private String age;

    public static void test(){
        Students s = new Students("无双","12");
        String place = StrUtil.format("{}{}", s.getName(),s.getAge());

        System.out.println(place);
    }


    public static void main(String[] args){
        Students.test();

    }

}
