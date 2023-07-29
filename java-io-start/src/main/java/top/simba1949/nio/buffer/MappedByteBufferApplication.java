package top.simba1949.nio.buffer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author anthony
 * @version 2023/7/29 8:34
 */
public class MappedByteBufferApplication {
	public static void main(String[] args) throws IOException {
		mappedByteBuffer();
	}

	public static void mappedByteBuffer() throws IOException {
		String filePath = "./java-io-start/src/main/resources/file/channel/MappedByteBuffer";
		RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "rw");

		FileChannel channel = randomAccessFile.getChannel();
		/**
		 * 参数1：FileChannel.MapMode.READ_WRITE:，使用的读写的模式
		 * 参数2：0，表示可以直接修改的起始位置
		 * 参数3：5，是映射到内存的大小，即文件有多少字节可以映射到内存中；
		 * 可以直接修改的范围就是 0-5
		 */
		MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
		mappedByteBuffer.put(0, (byte) 'H');
		mappedByteBuffer.put(1, (byte) 'E');
		mappedByteBuffer.put(2, (byte) 'L');
		mappedByteBuffer.put(3, (byte) 'L');
		mappedByteBuffer.put(4, (byte) 'O');
		// mappedByteBuffer.put(5, (byte) ' ');

		// 注意：不要在开发工具中打开文件，去操作系统文件夹中打开，或者重新读取文件
		channel.close();
		randomAccessFile.close();
	}
}
