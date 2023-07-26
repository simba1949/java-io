package top.simba1949.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author anthony
 * @version 2023/7/26 22:58
 */
public class FileOperateApplication {
	public static final Logger log = LoggerFactory.getLogger(FileOperateApplication.class);

	/**
	 * 文件操作
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		String filePath = "D:\\Program Files\\file.txt";
		File file = new File(filePath);

		// 文件重命名
		rename(file);
	}

	public static void rename(File file) {
		String destFilePath = "D:\\Program Files\\file-rename.txt";
		File destFile = new File(destFilePath);

		boolean flag = file.renameTo(destFile);
		log.info("文件重命名是否成功 = {}", flag);
	}
}
