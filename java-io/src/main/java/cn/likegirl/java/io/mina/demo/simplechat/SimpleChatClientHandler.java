package cn.likegirl.java.io.mina.demo.simplechat;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * 说明：简单聊天室 客户端 处理类
 *
 * @author <a href="http://www.waylau.com">waylau.com</a>  2015年4月10日
 */
public class SimpleChatClientHandler extends IoHandlerAdapter {
	@Override
	public void messageReceived(IoSession session, Object message) {
		String str = message.toString();
		System.out.println(str);
	}
}
