package top.simba1949.zeroCopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author anthony
 * @version 2023/7/30 20:39
 */
public class OldCopyServer {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(7777);

		while (true) {
			Socket socket = serverSocket.accept();
			System.out.println("一个客户端建立连接");
			DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

			long totalCount = 0;
			byte[] bufferByteArray = new byte[4096];
			while (true) {
				int readCount = dataInputStream.read(bufferByteArray, 0, bufferByteArray.length);
				if (-1 == readCount) {
					break;
				}
				totalCount += readCount;
			}

			// 客户端发送的数据总字节数和服务器端接收的数据总字节数，可能不一致
			System.out.println("总共读取字节数：" + totalCount);
		}
	}
}
