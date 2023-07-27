package top.simba1949.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author anthony
 * @version 2023/7/27 22:58
 */
public class BufferedIOApplication {
	public static final Logger log = LoggerFactory.getLogger(BufferedIOApplication.class);


	/**
	 * 字节缓冲流（文本字节流的装饰者）
	 * BufferedInputStream 和 BufferedOutputStream
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// 字节缓冲流读取
		read();
	}

	/**
	 *
	 */
	public static void read() {
		String filePath = "./java-io-start/src/main/resources/file/fileInputStream";
		File file = new File(filePath);

		try (
				// 创建一个
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))
		) {
			// 操作流
			int temp = -1;
			while ((temp = bis.read()) != -1) {
				log.info("读取的字节信息是：{}", (char) temp);
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
