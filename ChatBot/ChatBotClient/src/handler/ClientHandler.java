package handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
	
	
	
	/*@Override
	public void channelRead(ChannelHandlerContext arg0, Object in) throws Exception {
		System.out.println("찍자");
		ByteBuf buf = (ByteBuf) in;
		byte[] b = new byte[buf.readableBytes()];
		buf.getBytes(0, b, 0, buf.readableBytes());
        String s = new String(b);
        //System.out.println("Client received: " + ByteBufUtil.hexDump(in) + ":" + s);
        System.out.println("Client received: " + s);
	}*/


	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
        ctx.close();
	}


	@Override
	protected void channelRead0(ChannelHandlerContext arg0, ByteBuf in) throws Exception {
		byte[] b = new byte[in.readableBytes()];
		in.getBytes(0, b, 0, in.readableBytes());
        String s = new String(b);
        String[] strArr = null;
        int cnt = 0;
        
        System.out.println(s);
        
        if( s != null ) {
        	if( s.indexOf("%") > -1 ) {
        		strArr = s.split("%");
        		for(String str : strArr){
        			System.out.println( ++cnt + ". " + str);
        		} 
        	}
        }else {
        	System.out.println("존재하는 파일이 없습니다.");
        }
	}
	


}
