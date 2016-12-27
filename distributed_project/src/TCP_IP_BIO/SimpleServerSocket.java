package TCP_IP_BIO;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
/**
 * 最重要是是理解IO流，以及在关闭IO流的情况下socket也随之关闭，所以想保持socket连接只能flush,不能close
 * @author zhangpeng
 *
 */
public class SimpleServerSocket {

	    private static String kpath = "d:\\keytool\\sslsocket.keystore";  
	   private static String tpath = "d:\\keytool\\sslsocketserver.keystore";  
	   private static char[] password = "123456".toCharArray(); 
	public static ExecutorService pool = Executors.newFixedThreadPool(10);
	SimpleServerSocket(){
		createServer();
	}
	
	public  void createServer(){
			//创建socket服务器
			boolean flag = true;  
	        SSLContext context = null;  
	        try {  
	            KeyStore ks = KeyStore.getInstance("JKS");  
	            ks.load(new FileInputStream(kpath), password);  
	            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");  
	            kmf.init(ks, password);  
	            KeyManager[] km = kmf.getKeyManagers();  
	            KeyStore ts = KeyStore.getInstance("JKS");  
	            ts.load(new FileInputStream(tpath), password);  
	            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");  
	            tmf.init(ts);  
	            TrustManager[] tm = tmf.getTrustManagers();  
	            context = SSLContext.getInstance("SSL");  
	            context.init(km, tm, null);  
	        } catch (Exception e) {  
	            e.printStackTrace();   //捕获异常  
	        }  
	        SSLServerSocketFactory ssf = (SSLServerSocketFactory) context.getServerSocketFactory();  
	        try {  
	            SSLServerSocket ss = (SSLServerSocket) ssf.createServerSocket(8000);  
			System.out.println("服务启动。。。");
			//初始化一个线程池
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
			System.out.println("响应超时。。。");
		}
	}
	
	public class SimpleServerThread implements Runnable {
		private BufferedReader br = null;
		private PrintWriter pw = null;
		private String msg = null;
		
		public SimpleServerThread(Socket socket) {
			try {
				//对PrintWriter设置成自动提交
				pw = new PrintWriter(socket.getOutputStream(),true);
				//初始化输入输出流
				br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
				pw.println("服务器收到了你的请求为："+"开始连接");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try{
//				while( (msg = br.readLine()) != null){
				msg = br.readLine();
					if(msg.equals("exit")){
						System.out.println("客户端退出");
						if(br != null){
							br.close();
						}
						if(pw != null){
							pw.close();
						}
						pool.shutdownNow();
					}else{
						//当客户端读取流的方法使用的是readLine时，服务端的输出流使用的方法应该为println
						System.out.println("服务器收到了你的请求为："+msg);
						pw.println("服务器收到了你的请求为："+msg);
						
					}
//				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args){
		new SimpleServerSocket();
	}
}
