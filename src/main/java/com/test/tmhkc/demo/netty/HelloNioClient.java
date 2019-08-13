package com.test.tmhkc.demo.netty;

import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

@Slf4j
public class HelloNioClient {

    // 需连接的服务端IP或域名，此例中服务器即为本机。
    private static String serverName = "127.0.0.1";
    // 需连接的服务端端口
    private static int port = 8888;


    public static void main(String[] args) throws Exception {

        //1.打开SocketChannel，用于与服务器建立连接通道
        SocketChannel schannel = SocketChannel.open();
        //2.连接通道设置为非阻塞模式
        schannel.configureBlocking(false);
        //3.根据目标服务器地址和端口与目标服务器建立连接。
        schannel.connect(new InetSocketAddress(serverName, port));
        //4.获取选择器，即创建客户端的多路复用器
        Selector selector = Selector.open();
        //5.将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_CONNECT事件。
        schannel.register(selector, SelectionKey.OP_CONNECT);
        //6.轮询获取选择器上已经准备就绪的事件，
        while (true) {
            //这是一个阻塞方法，基于内核实现，会一直等待直到有数据可读，返回值是key的数量（可以有多个）
            log.info("客户端Selector等待注册事件发生...");
            selector.select();

            //获取当前选择器中所有注册的选择键
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                //获取准备就绪的事件
                SelectionKey key = iterator.next();
                // 删除已选的key,以防重复处理
                iterator.remove();

                //判断具体是什么事件，来进行相应处理
                if (key.isConnectable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    // 如果正在连接，则完成连接
                    if (channel.isConnectionPending()) {
                        channel.finishConnect();
                    }
                    log.info("【客户端Connectable事件发生：与服务端完成连接。】");
                    // 设置成非阻塞
                    channel.configureBlocking(false);

                    //连接上服务端时给服务端发送信息
                    String sendMsg = "Hello, I am jvxb. Here is Nio Client.";
                    channel.write(ByteBuffer.wrap(sendMsg.getBytes()));
                    //在和服务端连接成功之后，为了可以接收到服务端的信息，需要给通道设置读的权限。
                    channel.register(selector, SelectionKey.OP_READ);

                    // 获得了可读的事件
                } else if (key.isReadable()) {
                    log.info("【客户端Readable事件发生：接收到服务端数据。】");
                    //获取当前选择器上读就绪状态的通道
                    SocketChannel channel = (SocketChannel) key.channel();
                    //通过通道和缓冲区，获取服务端返回过来的数据
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    channel.read(buffer);
                    String msg = new String(buffer.array()).trim();
                    log.info("客户端收到服务器消息：" + msg);
                }

            }

        }
    }

}
