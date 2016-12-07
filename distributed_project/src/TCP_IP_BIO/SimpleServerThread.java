package TCP_IP_BIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleServerThread implements Runnable {
	private BufferedReader br = null;
	private PrintWriter pw = null;
	private String msg = null;
	private Socket socket;
	public SimpleServerThread(Socket socket) {
		this.socket = socket;
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
			while( (msg = br.readLine()) != null){
				if(msg.equals("exit")){
					System.out.println("客户端退出");
					if(br != null){
						br.close();
					}
					if(pw != null){
						pw.close();
					}
					socket.close();
				}else{
					//当客户端读取流的方法使用的是readLine时，服务端的输出流使用的方法应该为println
					System.out.println("服务器收到了你的请求为："+msg);
					pw.println("服务器收到了你的请求为："+msg);
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
