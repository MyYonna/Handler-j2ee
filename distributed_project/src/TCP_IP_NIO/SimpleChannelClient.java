package TCP_IP_NIO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Set;

public class SimpleChannelClient {
	public static void main(String[] args){
		try {
			SocketChannel channel = SocketChannel.open();
			//设置通道为非阻塞才能使用selector
			channel.configureBlocking(false);
			channel.connect(new InetSocketAddress("127.0.0.1",10086));
			//打开一个selector
			Selector selector = Selector.open();
			//将channel注册到selector上，并提供感兴趣事件
			channel.register(selector, SelectionKey.OP_CONNECT);
			//等待感兴趣事件在注册的通道啊上就绪。这个方法会一直阻塞到某个注册的通道的事件就绪
			int nKeys = 0;
			while(true){
				//nKeys大于0，说明有通道准备就绪了
				nKeys = selector.select();
				if(nKeys > 0){
					//获得已经就绪的SelectionKey对象，包含了一些属性
					Set<SelectionKey> keys = selector.selectedKeys();
					for(SelectionKey key:keys){
						//判断是哪个事件就绪了
						if(key.isConnectable()){
							//从selectionKey中拿到对应的通道
							SocketChannel sc = (SocketChannel) key.channel();
						
							if(sc.finishConnect()){
								sc.configureBlocking(false);
								sc.register(selector, SelectionKey.OP_WRITE);
							}
							continue;
						}else if(key.isReadable()){
							System.out.println("获取服务器的数据");
							ByteBuffer buffer = ByteBuffer.allocate(10);
							SocketChannel sc = (SocketChannel) key.channel();
							RandomAccessFile file = new RandomAccessFile("D:\\readme11.txt", "rw");
							long length = file.length();
							file.seek(length);
							FileChannel fileChannel = file.getChannel();
							int ret = 0;
							byte[] inputByte = {};
							while((ret = sc.read(buffer))!= -1){
								buffer.flip();
								int firstLength = inputByte.length;
								inputByte = Arrays.copyOf(inputByte,firstLength + ret);
								//fileChannel是阻塞的
								 System.arraycopy(buffer.array(),0,inputByte,firstLength,ret);
								fileChannel.write(buffer);
								buffer.clear();
							}
							System.out.println("从服务器回传"+new String(inputByte));
							sc.close();
							fileChannel.close();
							file.close();
							selector.selectedKeys().clear();
						}else if(key.isWritable()){
							System.out.println("写入服务器的数据...");
							SocketChannel sc = (SocketChannel)key.channel();
							ByteBuffer buffer = ByteBuffer.allocate(1024);
								buffer.put("这还能不能抢到火车票了".getBytes());
								buffer.flip();
								//SocketChannel是非阻塞的，需要循环判断是否写完
								while( buffer.hasRemaining()){
									sc.write(buffer);
								}
								buffer.clear();
							
								sc.configureBlocking(false);
								sc.register(selector, SelectionKey.OP_READ);
						}
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}