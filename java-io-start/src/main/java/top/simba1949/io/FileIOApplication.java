package top.simba1949.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author anthony
 * @date 2023/7/27
 */
public class FileIOApplication {
	public static final Logger log = LoggerFactory.getLogger(FileIOApplication.class);

	/**
	 * FileInputStream 和 FileOutputStream 的运用
	 *
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// FileInputStream 每次读取一个字节信息
		// tryCatchFinally();

		// FileInputStream 每次读取一个字节信息
		// tryWithResources();

		// FileInputStream 使用自定义缓冲区读取字节信息
		// customBufferedRead();

		// 写操作
		// writer();

		// 读和写
		readAndWrite();
	}

	// =============================================
	// ================ InputStream ================
	// =============================================

	/**
	 * FileInputStream 每次读取一个字节信息
	 */
	public static void tryCatchFinally() {
		String filePath = "./java-io-start/src/main/resources/file/fileInputStream";
		File file = new File(filePath);

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);

			// 对流进行操作，每次读取一个字节，将字节值赋值给 temp，读到没有值则返回 -1
			int temp = -1;
			while ((temp = fis.read()) != -1) {
				log.info("读取到的字节信息时{}", (char) temp);
			}
		} catch (IOException e) {
			log.info("异常信息{}", e.getMessage(), e);
		} finally {
			if (null != fis) {
				try {
					fis.close();
				} catch (IOException e) {
					log.info("流关闭失败，异常信息：{}", e.getMessage(), e);
				}
			}
		}
	}

	/**
	 * FileInputStream 每次读取一个字节信息
	 */
	public static void tryWithResources() {
		String filePath = "./java-io-start/src/main/resources/file/fileInputStream";
		File file = new File(filePath);

		try (
				FileInputStream fis = new FileInputStream(file);
		) {
			// 对流进行操作，每次读取一个字节，将字节值赋值给 temp，读到没有值则返回 -1
			int temp = -1;
			while ((temp = fis.read()) != -1) {
				log.info("读取到的字节信息时{}", (char) temp);
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 使用自定义缓存区读取数据
	 */
	public static void customBufferedRead() {
		String filePath = "./java-io-start/src/main/resources/file/fileInputStream";
		File file = new File(filePath);

		// try-with-resource 方式
		try (
				FileInputStream fis = new FileInputStream(file);
		) {
			// 自定义缓存区
			byte[] flush = new byte[8];
			// 对流进行操作，每次读取一个字节，将字节值赋值给 temp，读到没有值则返回 -1
			int temp = -1;
			// 每次读取 flush.length 字节数，并存储在 flush 中
			while ((temp = fis.read(flush)) != -1) {
				for (byte b : flush) {
					log.info("读取到的字节信息时{}", (char) b);
				}
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// =============================================
	// ================ OutputStream ===============
	// =============================================

	/**
	 * 写
	 */
	public static void writer() {
		// 创建写的源
		String filePath = "./java-io-start/src/main/resources/file/fileOutputStream";
		File file = new File(filePath);

		//
		String msg = "So long lives this and this gives life to thee.\n";
		byte[] src = msg.getBytes();

		try (
				// 选择流，写入是否追加，默认 false 表示重写， true 表示在文件后面追加
				FileOutputStream os = new FileOutputStream(file, true);
		) {
			os.write(src, 0, src.length);
			os.flush();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// =============================================
	// ======= InputStream & OutputStream ==========
	// =============================================

	/**
	 * 读和写
	 */
	public static void readAndWrite() {
		// 创建读的源
		String readFilePath = "./java-io-start/src/main/resources/file/fileInputStream";
		File readFile = new File(readFilePath);
		// 创建写的源
		String writeFilePath = "./java-io-start/src/main/resources/file/fileOutputStream";
		File writeFile = new File(writeFilePath);
		// 设置文件写，默认是只读
		writeFile.setWritable(Boolean.TRUE);

		try (
				// 创建输入流
				FileInputStream fis = new FileInputStream(readFile);
				// 创建输出流，写入是否追加，默认 false 表示重写， true 表示在文件后面追加
				FileOutputStream fos = new FileOutputStream(writeFile, false);
		) {
			// 创建缓存区
			byte[] flush = new byte[1024 * 8];
			int len = -1;
			while ((len = fis.read(flush)) != -1) {
				fos.write(flush, 0, len);
			}
			// 全部读取完毕后，flush 输出流
			fos.flush();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
