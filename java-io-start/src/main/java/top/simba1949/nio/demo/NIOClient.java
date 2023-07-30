package top.simba1949.nio.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author anthony
 * @version 2023/7/30 10:28
 */
public class NIOClient {
	public static void main(String[] args) throws IOException {
		// 创建客户端的 SocketChannel，并绑定远程服务器端口
		SocketChannel socketChannel = SocketChannel.open();
		// 设置非阻塞
		socketChannel.configureBlocking(false);

		InetSocketAddress inetSocketAddress = new InetSocketAddress(7777);
		// 连接远程服务器，返回 true 说明连接成功，返回 false 说明连接失败
		if (!socketChannel.connect(inetSocketAddress)) {
			// 连接远程服务器是否完成，返回 true 说明连接完成，返回 false 说明连接未完成
			while (!socketChannel.finishConnect()) {
				System.out.println("因为连接需要时间，客户端不会阻塞，可以做其他事情...");
			}
		}

		// 如果连接成功，就发送数据
		String msg = "Hello World!";
		// 将字节数组包装到缓冲区中
		ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8));
		// 将 buffer 中的数据写到 channel 中
		socketChannel.write(byteBuffer);

		// 用于阻塞
		System.in.read();
	}
}
