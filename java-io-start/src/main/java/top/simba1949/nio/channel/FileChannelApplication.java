package top.simba1949.nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author anthony
 * @version 2023/7/29 7:06
 */
public class FileChannelApplication {
	public static void main(String[] args) throws IOException {
		// 使用 FileChannelRead 读取数据
		// read();

		// 使用 FileChannelRead 写数据
		// write();

		// 使用 FileChannelRead 读写数据
		readAndWrite();
	}

	/**
	 * FileChannelRead 的基本使用
	 *
	 * @throws IOException
	 */
	public static void read() throws IOException {
		// 分配一个 Buffer
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);

		String filePath = "./java-io-start/src/main/resources/file/channel/FileChannelRead";
		// 需要通过使用一个InputStream、OutputStream 或 RandomAccessFile 来获取一个 FileChannelRead 实例。
		RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "rw");
		// 获取一个 FileChannelRead 实例
		FileChannel fileChannel = randomAccessFile.getChannel();
		fileChannel.read(byteBuffer);

		// 切换模式
		byteBuffer.flip();

		while (byteBuffer.hasRemaining()) {
			System.out.print("" + (char) byteBuffer.get());
		}
	}

	/**
	 * 1. 准备源数据，将源数据写入到 buffer 中
	 * 2. 将 buffer 切换模式
	 * 3. 创建一个输出流，并打开 channel
	 * 4. 将 buffer 中的数据写入到 channel 中
	 */
	public static void write() {
		// 1. 准备源数据，将源数据写入到 buffer 中
		// 源数据
		String writeStr = "君不见黄河之水天上来";
		byte[] writeStrByteArray = writeStr.getBytes(StandardCharsets.UTF_8);
		// 分配一个 Buffer
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
		// 将数据写入到 buffer 中
		byteBuffer.put(writeStrByteArray);

		// 2. 将 buffer 切换模式
		byteBuffer.flip();

		// 3. 创建一个输出流，并打开 channel
		// 创建一个输出流
		String filePath = "./java-io-start/src/main/resources/file/channel/FileChannelWrite";
		FileOutputStream fileOutputStream = null;
		FileChannel channel = null;
		try {
			fileOutputStream = new FileOutputStream(filePath);
			channel = fileOutputStream.getChannel();

			// 4. 将 buffer 中的数据写入到 channel 中
			channel.write(byteBuffer);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != channel) {
				try {
					channel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != fileOutputStream) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 使用 FileChannelRead 读写数据
	 */
	public static void readAndWrite() {
		// 分配一个 Buffer
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);

		String readFilePath = "./java-io-start/src/main/resources/file/channel/FileChannelRead";
		String writeFilePath = "./java-io-start/src/main/resources/file/channel/FileChannelWrite";

		// 需要通过使用一个InputStream、OutputStream 或 RandomAccessFile 来获取一个 FileChannelRead 实例。
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			fileInputStream = new FileInputStream(readFilePath);
			FileChannel inputStreamChannel = fileInputStream.getChannel();
			inputStreamChannel.read(byteBuffer);

			// 切换 Buffer 模式
			byteBuffer.flip();

			fileOutputStream = new FileOutputStream(writeFilePath);
			FileChannel outputStreamChannel = fileOutputStream.getChannel();
			outputStreamChannel.write(byteBuffer);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != fileOutputStream) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != fileInputStream) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
