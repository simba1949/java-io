package top.simba1949.bio.multiClient;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author anthony
 * @version 2023/7/28 22:35
 */
public class BIOMultiClientClient {
	public static void main(String[] args) {
		try {
			// 1.创建 socket 对象请求服务端的连接
			Socket socket = new Socket("127.0.0.1", 9999);
			// 2.从 socket 对象中获取一个字节输出流
			OutputStream outputStream = socket.getOutputStream();
			// 3.把字节输出流包装成一个打印流
			PrintStream printStream = new PrintStream(outputStream);
			// 通过监听控制台的输入
			Scanner scanner = new Scanner(System.in);
			while (true) {
				System.out.println("请输入");
				String msg = scanner.nextLine();
				printStream.println(msg);
				printStream.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
