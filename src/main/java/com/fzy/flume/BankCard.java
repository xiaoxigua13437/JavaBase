package com.fzy.flume;

import lombok.Data;

@Data
public class BankCard {

    //账号
    private final String accountName;

    //钱
    private final int money;


    // 重写 toString() 方法, 方便打印 BankCard
    @Override
    public String toString() {
        return "BankCard{" +
                "accountName='" + accountName + '\'' +
                ", money='" + money + '\'' +
                '}';
    }
}
