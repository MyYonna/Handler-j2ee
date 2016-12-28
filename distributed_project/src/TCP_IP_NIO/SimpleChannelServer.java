package TCP_IP_NIO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleChannelServer {

	public static  ExecutorService e = Executors.newFixedThreadPool(1);
	public Selector selector;
	SimpleChannelServer(){
		try {
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.socket().bind(new InetSocketAddress(10086));
			serverSocketChannel.configureBlocking(false);
			//打开一个selector
			selector = Selector.open();
			while(true){
				//无限接收客户端的连接
				SocketChannel sc = (SocketChannel)serverSocketChannel.accept();
				if(sc != null){
					sc.configureBlocking(false);
					sc.register(selector, SelectionKey.OP_READ);
					int i = selector.select();
					if(i>0){
						Set<SelectionKey> selectionKeys = selector.selectedKeys();
						Iterator<SelectionKey> iterator = selectionKeys.iterator();
						while(iterator.hasNext()){
							SelectionKey selectionKey = iterator.next();
							Thread  t = new Thread(new SimpleNIOThread(selectionKey));
							e.execute(t);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args){

		new SimpleChannelServer();
		
	}
	
	public class SimpleNIOThread implements Runnable {
		private SelectionKey selectionKey;
		
		SimpleNIOThread(SelectionKey selectionKey){
			this.selectionKey = selectionKey;
		}
		
		@Override
		public void run() {
			try{
				if(selectionKey.isReadable()){
					System.out.println("检测到客户端写入完成，服务器读就绪。。。");
					SocketChannel sc = (SocketChannel)selectionKey.channel();
					ByteBuffer  byteBuffer = ByteBuffer.allocate(10);
					int byteL = sc.read(byteBuffer);
					RandomAccessFile file = new RandomAccessFile("D:/write.txt", "rw");
					file.seek(file.length());
					FileChannel fileChannel = file.getChannel();
					while(byteL != 0){
						byteBuffer.flip();
						fileChannel.write(byteBuffer);
						byteBuffer.clear();
						byteL = sc.read(byteBuffer);
					}
					file.close();
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
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
			
