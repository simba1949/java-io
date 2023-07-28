package top.simba1949.bio.multiClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author anthony
 * @version 2023/7/28 22:34
 */
public class BIOMultiClientServer {
	public static void main(String[] args) throws Exception {
		// 1.定义一个 ServerSocket 对象进行服务端的端口注册
		ServerSocket serverSocket = new ServerSocket(9999);
		while (true) {
			// 2.监听客户端的 Socket 连接请求
			Socket socket = serverSocket.accept();
			new Thread(() -> {
				String name = Thread.currentThread().getName();
				System.out.println("当前线程的名称是" + name);
				try {
					// 3.从 socket 管道中得到一个字节输入流
					InputStream inputStream = socket.getInputStream();
					// 4.把字节输入流包装成一个缓冲字符输入流
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
					String msg;
					while ((msg = bufferedReader.readLine()) != null) {
						System.out.println("服务端接收到的数据是{}" + msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
		}
	}
}
