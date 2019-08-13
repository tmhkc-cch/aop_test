package com.test.tmhkc.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tmhkc
 * @Description:netty服务端
 * @date 2019-08-06 11:17
 **/
@Slf4j
public class HelloNettyServer {
    //端口
    private static int port = 8888;

    public static void main(String[] args) throws Exception {
        //创建线程组  用来接收连接请求
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //创建线程组 用来处理网络操作
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        //创建服务器端 配置参数
        ServerBootstrap serverBootstrap = new ServerBootstrap();


        serverBootstrap.group(bossGroup, workerGroup)//设置线程组
                .channel(NioServerSocketChannel.class)//5.使用NioServerSocketChannel作为服务器端通道的实现
                .option(ChannelOption.SO_BACKLOG, 128) //6.设置线程队列中等待连接的个数
                .childOption(ChannelOption.SO_KEEPALIVE, true) //7.保持活动连接状态
                .childHandler(new ChannelInitializer<SocketChannel>() {  //8. 创建一个通道初始化对象
                    public void initChannel(SocketChannel sc) {   //9. 往Pipeline链中添加自定义的handler类
                        sc.pipeline().addLast(new NettyServerHandler());
                    }
                });
        ChannelFuture cf = serverBootstrap.bind(port).sync();  //10. 绑定端口 bind方法是异步的  sync方法是同步阻塞的
        log.info("服务端开始监听端口：" + port + "...");

        //11. 关闭通道，关闭线程组
        cf.channel().closeFuture().sync(); //异步
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
        log.info("over");
    }


}

@Slf4j
class NettyServerHandler extends ChannelInboundHandlerAdapter {

    //读取数据事件
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        log.info("Server:" + ctx);
        ByteBuf buf = (ByteBuf) msg;
        log.info("客户端发来的消息：" + buf.toString(CharsetUtil.UTF_8));
    }

    //数据读取完毕事件
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello，jvxb， nice to meet you！Here is Server。", CharsetUtil.UTF_8));
    }

    //异常发生事件
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable t) {
        log.error("error",t);
        ctx.close();
    }

}

















