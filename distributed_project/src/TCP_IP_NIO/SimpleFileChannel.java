package TCP_IP_NIO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class SimpleFileChannel {

	public static void main(String[] args){
		RandomAccessFile file;
		RandomAccessFile file1;
		RandomAccessFile file2;
		RandomAccessFile file3;
		try {
			file = new RandomAccessFile("D:/read.txt","r");
			file1 = new RandomAccessFile("D:/read1.txt","rw");
			file2 = new RandomAccessFile("D:/read2.txt","rw");
			file3 = new RandomAccessFile("D:/read3.txt","rw");
			FileChannel fileChannel = file.getChannel();
			FileChannel file1Channel = file1.getChannel();
			FileChannel file2Channel = file2.getChannel();
			FileChannel file3Channel = file3.getChannel();
			//分配Buffer，设置capacity属性
			ByteBuffer byteBuffer = ByteBuffer.allocate(10);
			//写入数据经Buffer
			int byteRead = fileChannel.read(byteBuffer);
			while(byteRead != -1){
				System.out.println("read: "+byteRead);
				//从写模式切换到读模式
				byteBuffer.flip();
				//从Buffer中读取数据
				file1Channel.write(byteBuffer);
				//清空Buffer
				byteBuffer.clear();
				byteRead = fileChannel.read(byteBuffer);
			}
			//通道中有一个是FileChannel，那么久可以直接将数据从一个channel传输到另外呀一个channel
			file2Channel.transferFrom(fileChannel, 0, fileChannel.size());
			fileChannel.transferTo(0, fileChannel.size(),file3Channel);
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
