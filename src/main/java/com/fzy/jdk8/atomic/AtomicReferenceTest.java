package com.fzy.jdk8.atomic;

import com.fzy.flume.BankCard;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * AtomicReference保证
 *
 * @author yushu.zhao
 * @create 2021-08-23 16:29
 * @since 1.01
 */
public class AtomicReferenceTest {

    private static final AtomicReference<BankCard> atomicReference = new AtomicReference<>(new BankCard("yushu",100));


    public static void main(String[] args){


        for (int i = 0; i < 10; i++){
            new Thread(() ->{
                while (true){
                    // 使用 AtomicReference.get 获取
                    final BankCard card = atomicReference.get();
                    BankCard newCard = new BankCard(card.getAccountName(),card.getMoney() + 100);
                    // 使用 CAS 乐观锁进行非阻塞更新
                    if (atomicReference.compareAndSet(card,newCard)){
                        System.out.println(newCard);
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }







    }



}
