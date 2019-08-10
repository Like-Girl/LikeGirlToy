package cn.likegirl.java.jvm.io.mina.example.simplest;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MinaTimeClient {

	private static final String HOSTNAME = "localhost";

	private static final int PORT = 8080;

	private static final long CONNECT_TIMEOUT = 30 * 1000L; // 30 seconds

	// Set this to false to use object serialization instead of custom codec.
	// 将其设置成为: false  以 object serialization 为解码器而不是自定义解码器
	private static final boolean USE_CUSTOM_CODEC = true;

	public static void main(String[] args) throws Throwable {
	    NioSocketConnector connector = new NioSocketConnector();
	    connector.setConnectTimeoutMillis(CONNECT_TIMEOUT);

	    if (USE_CUSTOM_CODEC) {
	    	connector.getFilterChain().addLast("codec",
					new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
	    } else {
	        connector.getFilterChain().addLast("codec",
	            new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
	    }

	    connector.getFilterChain().addLast("logger", new LoggingFilter());
	    connector.setHandler(new TimeClientHandler());
	    IoSession session;

	    for (;;) {
	        try {
	            ConnectFuture future = connector.connect(new InetSocketAddress(HOSTNAME, PORT));
	            future.awaitUninterruptibly();
	            session = future.getSession();
	            break;
	        } catch (RuntimeIoException e) {
	            System.err.println("Failed to connect.");
	            e.printStackTrace();
	            Thread.sleep(5000);
	        }
	    }

	    // wait until the summation is done
	    session.getCloseFuture().awaitUninterruptibly();
	    connector.dispose();
	}

}
