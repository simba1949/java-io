package top.simba1949.nio.buffer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.util.Objects;

/**
 * @author anthony
 * @version 2023/7/28 22:57
 */
public class BufferApplication {
	public static void main(String[] args) throws IOException {
		// intBuffer();

		byteBuffer();
	}

	public static void intBuffer() {
		// 创建一个 buffer （IntBuffer），容量是5
		IntBuffer intBuffer = IntBuffer.allocate(5);
		// 向 buffer 中存放数据
		for (int i = 0; i < intBuffer.capacity(); i++) {
			intBuffer.put(i);
		}
		// 如何从 buffer 读取数据
		// 将 buffer 进行读写切换
		intBuffer.flip();
		// 当前位置和限制是否有元素
		while (intBuffer.hasRemaining()) {
			// 获取元素，读取缓冲区的位置递增
			System.out.println(intBuffer.get());
		}
	}


	public static void byteBuffer() throws IOException {
		String fileName = "file/nio-data.txt";
		URL resource = BufferApplication.class.getClassLoader().getResource(fileName);
		String path = Objects.requireNonNull(resource).getPath();
		System.out.println("The path is " + path);

		// 将文件数据读取到文件对象中
		RandomAccessFile aFile = new RandomAccessFile(path, "rw");
		// 获取文件通道
		FileChannel inChannel = aFile.getChannel();

		// 分配 48 个 byte 的缓存
		ByteBuffer buf = ByteBuffer.allocate(48);

		// channel 读取自身数据到缓存中
		int bytesRead = inChannel.read(buf);
		while (bytesRead != -1) {
			// 反转缓存的读写
			buf.flip();
			// 判断当前位置和限制之间是否存在元素
			while (buf.hasRemaining()) {
				// get 每次读取一个字节
				System.out.print((char) buf.get());
			}

			// 情况缓存，为下步写 buffer 做准备
			buf.clear();
			// channel 再次读取自身数据到 buffer 中
			bytesRead = inChannel.read(buf);
		}
		// 关闭文件对象会自动关闭对应的 channel
		aFile.close();

		System.out.println();
		System.out.println(bytesRead);
	}
}