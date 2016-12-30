package Mina;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MinaTimeServer {

	
	private static final int port = 6480;
	
	public static void main(String[] args){
		//创建服务端监控线程
		IoAcceptor acceptor = new NioSocketAcceptor();
		//设置读取的数组大小
		acceptor.getSessionConfig().setReadBufferSize(2048);
		//设置闲置时间
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		
		//加日志记录器
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		//加编码过滤器
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
		//设置业务逻辑处理器
		acceptor.setHandler(new TimeServerHandler());
		
		try {
			//设置端口号
			acceptor.bind(new InetSocketAddress(port));
			//启动监听线程
			acceptor.bind();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
