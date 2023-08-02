package top.simba1949.file;

import java.io.File;

/**
 * @author anthony
 * @version 2023/7/26 23:05
 */
public class JudgeFileApplication {

	public static void main(String[] args) {
		String filePath = "D:\\Program Files\\file.txt";
		File file = new File(filePath);

		// 判断路径是否是绝对路径，Unix 下以 / 开头，windows 下以盘符\\或者\\\\开头
		boolean absoluteFlag = file.isAbsolute();
		System.out.println("判断文件路径是否是绝对路径：" + absoluteFlag);
		File file2 = new File("./../file.txt");
		boolean absoluteFlag2 = file2.isAbsolute();
		System.out.println("判断文件路径是否是绝对路径-2：" + absoluteFlag2);

		boolean existsFlag = file.exists();
		System.out.println("判断文件对象是否存在：" + existsFlag);

		boolean hiddenFlag = file.isHidden();
		System.out.println("判断文件是否隐藏：" + hiddenFlag);

		boolean directoryFlag = file.isDirectory();
		System.out.println("判断文件对象是否是文件夹：" + directoryFlag);

		boolean fileFlag = file.isFile();
		System.out.println("判断文件对象是否是文件：" + fileFlag);

		boolean readFlag = file.canRead();
		System.out.println("判断文件是否可读：" + readFlag);

		boolean writeFlag = file.canWrite();
		System.out.println("判断文件是否可写：" + writeFlag);

		boolean canExecuteFlag = file.canExecute();
		System.out.println("判断文件是否可执行：" + canExecuteFlag);
	}
}
