package com.fzy.socket.udp;

import com.fzy.serializable.SerializableUtils;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 *  udp数据传输,服务接收端
 *
 * @author fuzhongyu
 * @date 2017/11/9
 */

public class SocketUdpServer {

    public static void start(int serverPort,SocketUdpServerMethod method){
        new SocketUdpServer().startUp(serverPort,method);
    }

    /**
     * 启动udp服务端socket,接收数据
     * @param serverPort  端口
     * @throws IOException
     */
    public void startUp(int serverPort,SocketUdpServerMethod method){
        try {
            InetSocketAddress inetSocketAddress=new InetSocketAddress(serverPort);
            DatagramSocket datagramSocket=new DatagramSocket(inetSocketAddress);
            datagramSocket.setReceiveBufferSize(100);
            while (true){
                byte[] buf=new byte[1024*16];
                DatagramPacket datagramPacket=new DatagramPacket(buf,buf.length);
                datagramSocket.receive(datagramPacket);
                new Thread(new ServerExecuteThread(datagramSocket,datagramPacket,method)).start();
            }
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("---- 启动服务端 socket 失败 ");
        }

    }

    /**
     * 执行业务
     */
    private class ServerExecuteThread implements Runnable{

        private DatagramSocket socket;

        private DatagramPacket packet;

        private SocketUdpServerMethod method;

        public ServerExecuteThread(DatagramSocket socket,DatagramPacket packet,SocketUdpServerMethod method){
            this.socket=socket;
            this.packet=packet;
            this.method=method;
        }

        @Override
        public void run() {
            System.out.println("===有新的连接请求：ip:"+packet.getAddress()+"  port:"+packet.getPort()+"=====");
            Object resultData=null;
            if(packet.getData()!=null){
//                resultData=method.service(SerializableUtils.unserialize(packet.getData()));
                resultData=method.service(new String(packet.getData()));
            }
            if(resultData!=null){
//                byte[] bytes=SerializableUtils.serialize(resultData);
                byte[] bytes=String.valueOf(resultData).getBytes();
                DatagramPacket packetReturn=new DatagramPacket(bytes,bytes.length);
                packetReturn.setSocketAddress(packet.getSocketAddress());
                try {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    socket.send(packetReturn);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("==== server socket 返回数据失败 =====");
                }
            }


        }
    }

}
