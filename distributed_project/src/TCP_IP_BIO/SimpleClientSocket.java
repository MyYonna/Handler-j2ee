package TCP_IP_BIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleClientSocket {
	private PrintWriter pw;
	private BufferedReader br;
	private String message ;
	private static ExecutorService exec = Executors.newCachedThreadPool();  
	SimpleClientSocket(){
		try {
			Socket socket = new Socket("127.0.0.1",8888);
			System.out.println("启动客户端。。。。。服务器连接成功");
			exec.execute(new ClientHandlerThread(socket)); 
			//初始化输入输出流
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			pw= new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
			//这个是一个死循环，所以开启线程必须在这个循环的前面
			while((message = br.readLine())!=null){
				System.out.println(message);
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main (String[] args){
		new SimpleClientSocket();
	}
	
	public class ClientHandlerThread implements  Runnable {
		 private Socket socket;  
		 
		ClientHandlerThread(Socket socket){
			this.socket = socket;
		}
		public void run() {
			try {
				int i = 0;
				while(true){
					Thread.sleep(1000);
					i++;
					if(i>10){
						pw.write("exit");
						br.close();
						pw.close();
						socket.close();
						break;
					}
					System.out.println("第"+i+"次。。。");
					pw.println("hello ,server");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
