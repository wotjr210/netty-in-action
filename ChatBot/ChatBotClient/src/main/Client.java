package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import init.ClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
	
	 static final String HOST 	= "127.0.0.1";
	 static final int PORT 		= 54321;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		new Client().start();
	}
	
	public void start() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
        try {
            
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
            .channel(NioSocketChannel.class)
            .handler(new ClientInitializer());
            
            Channel channel = bootstrap.connect(HOST, PORT).sync().channel();
            ChannelFuture lastWriteFuture = null;
            
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            for (;;) {
                String line = in.readLine();
                if (line == null) {
                    break;
                }
 
                // Sends the received line to the server.
                lastWriteFuture = channel.writeAndFlush(line + "\r\n");
 
                // If user typed the 'bye' command, wait until the server closes
                // the connection.
                if ("bye".equals(line.toLowerCase())) {
                    channel.closeFuture().sync();
                    break;
                }
            }
 
            // Wait until all messages are flushed before closing the channel.
            if (lastWriteFuture != null) {
                lastWriteFuture.sync();
            }
        } finally {
            group.shutdownGracefully();
        }
	}
}
