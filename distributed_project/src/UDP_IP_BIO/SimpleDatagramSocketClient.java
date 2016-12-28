package UDP_IP_BIO;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleDatagramSocketClient {
	public static  ExecutorService e = Executors.newFixedThreadPool(10);
	SimpleDatagramSocketClient(){
		try {
			Thread t = new Thread(new SimpleDatagramSocketThread());
			e.execute(t);
			
			DatagramSocket socket = new DatagramSocket();
			Scanner s = new Scanner(System.in);
			while(s.hasNextLine()){
				String msg = s.nextLine();
				if(msg.equals("exit")){
					break;
				}else{
					InetAddress server=Inet4Address.getLocalHost(); 
					DatagramPacket packet = new DatagramPacket(msg.getBytes(),0,msg.getBytes().length,server,9999);
					socket.send(packet);
				}
			}
			s.close();
			socket.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
	}
	public static void main(String[] args){
		new SimpleDatagramSocketClient();
	}
	
	public class SimpleDatagramSocketThread implements Runnable{
		
		SimpleDatagramSocketThread(){
			
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try{
				DatagramSocket serverSocket = new DatagramSocket(8888);
				while(true){
					byte[] buffer = new byte[65507];
					DatagramPacket receivePacket = new DatagramPacket(buffer,buffer.length);
					serverSocket.receive(receivePacket);
					System.out.println(new String(buffer));
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
