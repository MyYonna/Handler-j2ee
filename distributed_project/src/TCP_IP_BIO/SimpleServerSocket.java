package TCP_IP_BIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 最重要是是理解IO流，以及在关闭IO流的情况下socket也随之关闭，所以想保持socket连接只能flush,不能close
 * @author zhangpeng
 *
 */
public class SimpleServerSocket {

	public static void createServer(){
		try {
			//创建socket服务器
			ServerSocket ss = new ServerSocket(8888);
			System.out.println("服务启动。。。");
			//初始化一个线程池
			ExecutorService pool = Executors.newFixedThreadPool(10);
			//循环监听端口
			while(true){
				//因为socket为同步阻塞io模式，所以只能当有socket连接建立之后，之后的代码才能往下运行
				Socket socket = ss.accept();
				//设置响应时间
				socket.setSoTimeout(10000);
				SimpleServerThread sht = new SimpleServerThread(socket);
				//通过线程池启动线程
				//启动单独的线程来处理客户端的请求和进行响应
				pool.execute(sht);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		 createServer();
	}
}
