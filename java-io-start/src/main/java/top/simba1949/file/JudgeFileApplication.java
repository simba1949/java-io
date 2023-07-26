package top.simba1949.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author anthony
 * @version 2023/7/26 23:05
 */
public class JudgeFileApplication {
	public static final Logger log = LoggerFactory.getLogger(JudgeFileApplication.class);

	public static void main(String[] args) {
		String filePath = "D:\\Program Files\\file.txt";
		File file = new File(filePath);


		boolean existsFlag = file.exists();
		log.info("判断文件对象是否存在：{}", existsFlag);

		boolean directoryFlag = file.isDirectory();
		log.info("判断文件对象是否是文件夹：{}", directoryFlag);

		boolean fileFlag = file.isFile();
		log.info("判断文件对象是否是文件：{}", fileFlag);

		boolean readFlag = file.canRead();
		log.info("判断文件是否可读：{}", readFlag);

		boolean writeFlag = file.canWrite();
		log.info("判断文件是否可写：{}", writeFlag);

		boolean canExecuteFlag = file.canExecute();
		log.info("判断文件是否可执行：{}", canExecuteFlag);

		boolean hiddenFlag = file.isHidden();
		log.info("判断文件是否隐藏：{}", hiddenFlag);
	}
}
