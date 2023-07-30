package top.simba1949.zeroCopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author anthony
 * @version 2023/7/30 20:34
 */
public class NIOZeroCopyServer {
	public static void main(String[] args) throws IOException {
		InetSocketAddress inetSocketAddress = new InetSocketAddress(7001);

		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		ServerSocket serverSocket = serverSocketChannel.socket();
		serverSocket.bind(inetSocketAddress);

		ByteBuffer byteBuffer = ByteBuffer.allocate(8 * 1024 * 1024);

		while (true) {
			SocketChannel socketChannel = serverSocketChannel.accept();
			int readCount = 0;
			try {
				readCount = socketChannel.read(byteBuffer);
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}

			// 倒带
			byteBuffer.rewind();
		}
	}
}
