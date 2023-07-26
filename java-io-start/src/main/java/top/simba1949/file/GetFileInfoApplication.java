package top.simba1949.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author anthony
 * @version 2023/7/26 22:25
 */
public class GetFileInfoApplication {
	public static final Logger log = LoggerFactory.getLogger(GetFileInfoApplication.class);

	/**
	 * 获取文件和目录信息
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		String filePath = "D:/Program Files/file.txt";
		File file = new File(filePath);
		// 获取文件名称
		String name = file.getName();
		log.info("获取文件名：{}", name);

		// 获取文件路径
		String path = file.getPath();
		log.info("获取文件路径：{}", path);

		// 获取文件绝对路径
		String absolutePath = file.getAbsolutePath();
		log.info("获取文件绝对路径：{}", absolutePath);

		// 获取父级文件对象
		String parent = file.getParent();
		log.info("获取父级文件路径：{}", parent);

		// 获取文件字节数
		long length = file.length();
		log.info("获取到文件字节数：{}", length);

		// 获取最后一次修改文件的时间
		long lastedModified = file.lastModified();
		log.info("获取到文件最后一次修改时间：{}", lastedModified);
	}
}
