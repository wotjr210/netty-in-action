package handler;

import biz.ChatKeyword;
import biz.ChatMethods;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class ServerHandler extends ChannelInboundHandlerAdapter {



	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("client channel active");
		Channel channel = ctx.channel();
		channel.writeAndFlush("Sever Connected");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("client channel inactive");
		ctx.flush();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		
		 String message = null;
	     message = (String)msg;


		
		/*ByteBuf in = (ByteBuf) msg;
		byte[] b = new byte[in.readableBytes()];
		in.getBytes(0, b, 0, in.readableBytes());
		String s = new String(b);*/
		
		System.out.println("channelRead of [SERVER]" + message);
		
		if("list".equals(message)) {
			ChatMethods c = ChatMethods.getInstance();
			String rtn = c.showList();
			
			final ByteBuf buf =
		            Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(rtn, CharsetUtil.UTF_8));
			
			byte[] b = new byte[buf.readableBytes()];
			buf.getBytes(0, b, 0, buf.readableBytes());
			String s = new String(b);
			
			System.out.println("s ::::::: " + s);
			
			ctx.channel().writeAndFlush(buf);
		}else {
			ctx.channel().writeAndFlush("hahahahahah");
		}
		
		/*Channel incoming = ctx.channel();
		if (channel != incoming) {
			// 메시지 전달.
			channel.writeAndFlush("[" + incoming.remoteAddress() + "]" + message + "\n");
		}
		if ("bye".equals(message.toLowerCase())) {
			ctx.close();
		}*/

	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelReadComplete(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
