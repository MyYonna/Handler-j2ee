package UDP_IP_BIO;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleDatagramSocketServer {
	public static  ExecutorService e = Executors.newFixedThreadPool(10);
	SimpleDatagramSocketServer(){
			try {
				DatagramSocket serverSocket = new DatagramSocket(9999);
				while(true){
					byte[] buffer = new byte[65507];
					DatagramPacket receivePacket = new DatagramPacket(buffer,buffer.length);
					serverSocket.receive(receivePacket);
					Thread t = new Thread(new SimpleDatagramSocketThread(receivePacket));
					e.execute(t);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	public static void main(String[] args){
		new SimpleDatagramSocketServer();
	}
	
	public class SimpleDatagramSocketThread implements Runnable{
		public DatagramPacket packet;
		
		SimpleDatagramSocketThread(DatagramPacket packet){
			this.packet = packet;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				DatagramSocket socket = new DatagramSocket();
				String msg = new String(packet.getData());
				System.out.println("服务器获取的客户端数据。。。"+msg);
				String reback = "不错，小伙子";
				System.out.println("服务器回传开始。。。");
				int  port  = 8888;
				InetAddress host = packet.getAddress();
				DatagramPacket packet1 = new DatagramPacket(reback.getBytes(),0,reback.getBytes().length,host,port);
				socket.send(packet1);
				System.out.println("服务器回传结束。。。");
				socket.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}
		
		
	}
}
