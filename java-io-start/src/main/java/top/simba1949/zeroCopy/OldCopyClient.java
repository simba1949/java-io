package top.simba1949.zeroCopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author anthony
 * @version 2023/7/30 20:35
 */
public class OldCopyClient {

	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("localhost", 7777);

		String filePath = "./java-io-start/src/main/resources/file/zeroCopy/jdk-8u202-windows-i586.zip";
		FileInputStream fileInputStream = new FileInputStream(filePath);

		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

		byte[] bufferArray = new byte[4096];
		long readCount;
		long total = 0;

		long startTime = System.currentTimeMillis();
		while ((readCount = fileInputStream.read(bufferArray)) > 0) {
			total += readCount;
			dataOutputStream.write(bufferArray);
		}

		System.out.println("发送总字节数：" + total + "，耗时：" + (System.currentTimeMillis() - startTime));

		dataOutputStream.close();
		socket.close();
		fileInputStream.close();
	}
}
