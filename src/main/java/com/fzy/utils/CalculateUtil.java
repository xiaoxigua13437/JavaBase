package com.fzy.utils;

import java.math.BigDecimal;

/**
 * 计算工具类
 *
 * @author yushu.zhao
 * @create 2021-05-20 13:42
 */
public class CalculateUtil {

    /**
     * 将double转化为int
     */
    public static int doubleToInt(double value) {
        String temp = String.valueOf(value);
        System.out.println(temp);
        String str = temp.substring(0, temp.indexOf(".")) + temp.substring(temp.indexOf(".") + 1);
        int intgeo = Integer.parseInt(str);
        return intgeo;
    }

    /**
     * double元转化为int的分
     */
    public static int doubleToInt2(Double value) {
        value = value * 100;
        int fee = Integer.valueOf(value.intValue());
        return fee;
    }

    /**
     * int分转化为double元
     */
    public static double intToDouble(int value) {
        double tempValue = value;
        Double temp = null;
        try {
            temp = div(tempValue, 100, 2);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * 提供精确加法计算的add方法
     *
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和
     */
    public static double add(double value1, double value2) {
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.add(b2).doubleValue();
    }


    /**
     * 提供精确减法运算的sub方法
     *
     * @param value1 被减数
     * @param value2 减数
     * @return 两个参数的差
     */
    public static double sub(double value1, double value2) {
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确乘法运算的mul方法
     *
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static double mul(double value1, double value2) {
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供精确的除法运算方法div
     *
     * @param value1 被除数
     * @param value2 除数
     * @param scale 精确范围
     * @return 两个参数的商
     */
    public static double div(double value1, double value2, int scale) throws IllegalAccessException {
        // 如果精确范围小于0，抛出异常信息
        if (scale < 0) {
            throw new IllegalAccessException("精确度不能小于0");
        }
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.divide(b2, scale).doubleValue();
    }


    /**
     * 提供精确比较计算的compareTo方法
     *
     * @param value1 被比较数
     * @param value2 比较数
     * @return 两个参数的比较结果 在数字上小于、等于或大于 value2 时，返回 -1、0 或 1
     */
    public static int compareTo(double value1, double value2) {
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.compareTo(b2);
    }

    public static void main(String[] args) {
        int a = doubleToInt2(0.01);
        System.out.println(a);
        double temp = intToDouble(1);
        System.out.println(temp);
    }










}
