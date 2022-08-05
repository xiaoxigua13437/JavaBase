package com.fzy.flume;

import org.apache.flume.*;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.junit.Test;

import java.sql.Connection;
import java.util.Arrays;

/**
 * @author fuzhongyu
 * @date 2017/12/14
 */

public class FlumeSkinsJdbc extends AbstractSink implements Configurable {

    private Connection connection;

    @Override
    public Status process() throws EventDeliveryException {

        Channel channel=getChannel();
        Transaction transaction=channel.getTransaction();
        Event event=null;
        transaction.begin();
        while (true){
            event=channel.take();
            if (event !=null){
                break;
            }
        }
        System.out.println(Arrays.toString(event.getBody()));

        return null;
    }

    @Override
    public void configure(Context context) {

    }


    @Test
    public void test(){
        while (true){
            new FlumeSkinsJdbc();
        }
    }
}
