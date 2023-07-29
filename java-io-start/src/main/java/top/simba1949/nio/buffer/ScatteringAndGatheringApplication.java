package top.simba1949.nio.buffer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * scattering : 将数据写入到 buffer 时，可以采用 buffer 数组，依次写入【分散】
 * gathering ： 从 buffer 读取数据时，可以采用 buffer 数组，依次读；
 *
 * @author anthony
 * @version 2023/7/29 10:33
 */
public class ScatteringAndGatheringApplication {
	public static void main(String[] args) throws IOException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		InetSocketAddress inetSocketAddress = new InetSocketAddress(7777);
		// 绑定端口到 socket，并启动
		serverSocketChannel.socket().bind(inetSocketAddress);
		// 创建 buffer 数组
		ByteBuffer[] byteBufferArray = new ByteBuffer[2];
		byteBufferArray[0] = ByteBuffer.allocate(5);
		byteBufferArray[1] = ByteBuffer.allocate(3);

		// 等待客户端连接
		SocketChannel socketChannel = serverSocketChannel.accept();

		while (true) {
			// 清空
			Arrays.asList(byteBufferArray).forEach(ByteBuffer::clear);

			int byteRead = 0;
			while (byteRead < 8) {
				// 每次读取的字节数
				long read = socketChannel.read(byteBufferArray);
				byteRead += read;
				System.out.println("byteRead=" + byteRead);

				Stream.of(byteBufferArray)
						.map(buffer -> "position=" + buffer.position() + ", limit=" + buffer.limit())
						.forEach(System.out::println);
			}

			// 转换模式
			Arrays.asList(byteBufferArray).forEach(ByteBuffer::flip);

			// 将数据读出显示到客户端
			long byteWrite = 0;
			while (byteWrite < 8) {
				long write = socketChannel.write(byteBufferArray);
				byteWrite += write;
			}

			System.out.println("byteRead=" + byteRead + "，byteWrite=" + byteWrite);
		}
	}

}
