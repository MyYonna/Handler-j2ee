package TCP_IP_NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleChannelServer {

	public static  ExecutorService e = Executors.newFixedThreadPool(10);
	SimpleChannelServer(){
		try {
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.socket().bind(new InetSocketAddress(10086));
			serverSocketChannel.configureBlocking(false);
			//打开一个selector
			Selector selector = Selector.open();
			//将channel注册到selector上，并提供感兴趣事件
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT );
			while(true){
				int i = selector.select();
				if(i>0){
					Set<SelectionKey> selectionKeys = selector.selectedKeys();
					Iterator<SelectionKey> iterator = selectionKeys.iterator();
					while(iterator.hasNext()){
						SelectionKey selectionKey = iterator.next();
						if(selectionKey.isAcceptable()){
							System.out.println("接收到客户那的连接请求。。。");
							SocketChannel sc = (SocketChannel)serverSocketChannel.accept();
							if(sc == null){
								continue;
							}
							sc.configureBlocking(false);
							sc.register(selector, SelectionKey.OP_READ);
						}else if(selectionKey.isReadable()){
							System.out.println("检测到客户端写入完成，服务器读就绪。。。");
							SocketChannel sc = (SocketChannel)selectionKey.channel();
							ByteBuffer  byteBuffer = ByteBuffer.allocate(10);
							int byteL = sc.read(byteBuffer);
							while(byteL != 0){
								System.out.println(new String(byteBuffer.array()));
								byteBuffer.clear();
								byteL = sc.read(byteBuffer);
							}
							
							sc.configureBlocking(false);
							sc.register(selector, SelectionKey.OP_WRITE);
						}else if(selectionKey.isWritable()){
							System.out.println("服务器写就绪。。。");
							SocketChannel sc = (SocketChannel)selectionKey.channel();
							ByteBuffer byteBuffer1 = ByteBuffer.allocate(1024);
							byteBuffer1.put("这是什么东西，让我如此麻烦".getBytes());
							byteBuffer1.flip();
							while(byteBuffer1.hasRemaining()){
								sc.write(byteBuffer1);
							}
							byteBuffer1.clear();
							sc.close();
							serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT );
						}
					}
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args){

		new SimpleChannelServer();
		
	}
}
			
