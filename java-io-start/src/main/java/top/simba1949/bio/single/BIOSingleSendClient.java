package top.simba1949.bio.single;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @author anthony
 * @version 2023/7/28 22:25
 */
public class BIOSingleSendClient {
	public static void main(String[] args) {
		try {
			// 1.创建 socket 对象请求服务端的连接
			Socket socket = new Socket("127.0.0.1", 9999);
			// 2.从 socket 对象中获取一个字节输出流
			OutputStream outputStream = socket.getOutputStream();
			// 3.把字节输出流包装成一个打印流
			PrintStream printStream = new PrintStream(outputStream);
			printStream.println("Hello World!");
			printStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
