package top.simba1949.nio;

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

		RandomAccessFile aFile = new RandomAccessFile(path, "rw");
		FileChannel inChannel = aFile.getChannel();

		// create buffer with capacity of 48 bytes
		ByteBuffer buf = ByteBuffer.allocate(48);

		int bytesRead = inChannel.read(buf); //read into buffer.
		while (bytesRead != -1) {
			buf.flip();  // make buffer ready for read
			while (buf.hasRemaining()) {
				System.out.print((char) buf.get()); // read 1 byte at a time
			}

			buf.clear(); // make buffer ready for writing
			bytesRead = inChannel.read(buf); // read into buffer.
		}
		aFile.close();

		System.out.println();
		System.out.println(bytesRead);
	}
}
