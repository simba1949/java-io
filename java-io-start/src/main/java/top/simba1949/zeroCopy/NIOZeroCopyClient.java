package top.simba1949.zeroCopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * TODO ANTHONY 传输大文件有问题
 *
 * @author anthony
 * @version 2023/7/30 21:01
 */
public class NIOZeroCopyClient {
	public static void main(String[] args) throws IOException {
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.connect(new InetSocketAddress("127.0.0.1", 7001));

		String filePath = "./java-io-start/src/main/resources/file/zeroCopy/jdk-8u202-windows-i586.zip";
		File file = new File(filePath);
		FileChannel fileChannel = new FileInputStream(file).getChannel();

		// 准备发送
		long startTime = System.currentTimeMillis();

		// 在 linux 下，一个 transferTo 方法就可以完成传输
		// 在 windows 下，一次调用 transferTo 只能发送 8m数据，就需要分段传输文件，而且要注意传输时的位置
		// transferTo 底层使用到零拷贝
		long position = 0;
		long batchSize = 1024 * 1024;
		long total = 0;

		while (position < file.length()) {
			long count = batchSize;
			if (position + batchSize > file.length()) {
				count = file.length();
			}

			long realSize = fileChannel.transferTo(position, count, socketChannel);
			position += realSize;
			total += realSize;
		}

		System.out.println("发送总字节数：" + total + "，耗时：" + (System.currentTimeMillis() - startTime));

		fileChannel.close();
		socketChannel.close();
	}
}
