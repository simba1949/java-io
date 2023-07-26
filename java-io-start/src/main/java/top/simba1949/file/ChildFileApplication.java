package top.simba1949.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author anthony
 * @version 2023/7/26 22:49
 */
public class ChildFileApplication {
	public static final Logger log = LoggerFactory.getLogger(CreateFileApplication.class);

	/**
	 * 获取文件夹下所有子文件信息
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		String filePath = "D:\\Program Files";
		File file = new File(filePath);

		// 获取文件夹下所有子文件名称（包括文件夹和文件）
		String[] childFileNameList = file.list();
		if (null != childFileNameList) {
			for (String childFileName : childFileNameList) {
				log.info("获取到的子文件名称：{}", childFileName);
			}
		}

		// 获取文件夹下所有子文件对象（包括文件夹和文件）
		File[] childFileList = file.listFiles();
		if (null != childFileList) {
			for (File childFile : childFileList) {
				log.info("获取到的子文件对象：{}", childFile);
			}
		}
	}
}
