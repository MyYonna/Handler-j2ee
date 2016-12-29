package UDP_IP_NIO;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

public class SimpleDatagramChannelClient {

	SimpleDatagramChannelClient(){
		try {
			Thread t = new Thread(new SimpleDatagramChannelThread());
			t.start();
			Scanner s = new Scanner(System.in);
			while(s.hasNext()){
				String line = s.nextLine();
				DatagramChannel receiveChannel = DatagramChannel.open();
				ByteBuffer buf = ByteBuffer.allocate(48);
				buf.clear();
				buf.put(line.getBytes());
				buf.flip();
				receiveChannel.send(buf, new InetSocketAddress("127.0.0.1", 1111));
			}
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public class SimpleDatagramChannelThread implements Runnable{
		SimpleDatagramChannelThread(){
			
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try{
				DatagramChannel receiveChannel = DatagramChannel.open();
				//receiveChannel.configureBlocking(false);
				DatagramSocket socket = receiveChannel.socket();
				socket.bind(new InetSocketAddress(2222));
				ByteBuffer buffer = ByteBuffer.allocate(48);
				while(true){
					receiveChannel.receive(buffer);
					System.out.println(new String(buffer.array()));
					buffer.clear();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args){
		new SimpleDatagramChannelClient();
	}
}
