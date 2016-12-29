package UDP_IP_NIO;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class SimpleDatagramChannelServer {

	SimpleDatagramChannelServer(){
		try {
			DatagramChannel receiveChannel = DatagramChannel.open();
			//receiveChannel.configureBlocking(false);
			DatagramSocket socket = receiveChannel.socket();
			socket.bind(new InetSocketAddress(1111));
			ByteBuffer buffer = ByteBuffer.allocate(48);
			buffer.clear();
			while(true){
				receiveChannel.receive(buffer);
				Thread t = new Thread(new SimpleDatagramChannelThread(buffer));
				t.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public class SimpleDatagramChannelThread implements Runnable{
		ByteBuffer buffer;
		
		SimpleDatagramChannelThread(ByteBuffer buffer){
			this.buffer = buffer;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("服务器收到的响应"+new String(buffer.array()));
			System.out.println("服务器发出的响应开始");
			String newData = "New String to write to file..." + System.currentTimeMillis();
			ByteBuffer buf = ByteBuffer.allocate(48);
			buf.clear();
			buf.put(newData.getBytes());
			buf.flip();
			try {
				DatagramChannel receiveChannel = DatagramChannel.open();
				receiveChannel.configureBlocking(false);
				receiveChannel.send(buf, new InetSocketAddress("127.0.0.1", 2222));
				System.out.println("服务器发出的响应结束");
				buffer.clear();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args){
		new SimpleDatagramChannelServer();
	}
}
