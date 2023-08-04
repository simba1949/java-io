package top.simba1949.io.byteStream.filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;

/**
 * @author anthony
 * @date 2023/8/4
 */
public class PushbackInputStreamApplication {
	public static void main(String[] args) {
		input();
	}

	/**
	 * 使用推回输入流
	 */
	public static void input() {
		String filePath = "./java-io-start/src/main/resources/file/byte/filter/PushbackInputStream";
		File file = new File(filePath);

		PushbackInputStream pis = null;
		try {
			pis = new PushbackInputStream(new FileInputStream(file));
			// 用于表示是否是上一次读取的 \n 数据，如果是打印出来；如果不是，说明是本次循环的数据，推回输入流中，进入下一循环打印
			boolean lastReadFlag = false;
			for (int i = 0; i < 6; i++) {

				int readByte = -1;
				while ((readByte = pis.read()) != -1) {
					char readChar = (char) readByte;
					if ('\n' == readChar && !lastReadFlag) {
						// 如果读取的字符是 \n，回退该字符到缓冲流中
						pis.unread(readByte);
						lastReadFlag = true;
						// 打破当前循环，进入下一循环
						break;
					} else {
						lastReadFlag = false;
						System.out.print(readChar);
					}
				}

				// 用来分割每一循环
				System.out.println("");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != pis) {
				try {
					pis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
