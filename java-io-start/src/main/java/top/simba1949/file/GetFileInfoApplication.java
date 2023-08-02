package top.simba1949.file;

import java.io.File;
import java.io.IOException;

/**
 * @author anthony
 * @version 2023/7/26 22:25
 */
public class GetFileInfoApplication {

	/**
	 * 获取文件和目录信息
	 *
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		String filePath = "D:/Program Files/file.txt";
		File file = new File(filePath);
		// 获取文件名称
		String name = file.getName();
		System.out.println("获取文件名：" + name);

		// 获取文件绝对路径（如果文件对象路径是绝对路径，返回绝对路径，如果不是绝对路径，拼接相对路径返回）
		String absolutePath = file.getAbsolutePath();
		System.out.println("获取文件绝对路径-getAbsolutePath：" + absolutePath);
		// getCanonicalPath 规范路径名字符串。规范路径名既是绝对的，也是唯一的【类似于 java.io.Path.toRealPath】
		String canonicalPath = file.getCanonicalPath();
		System.out.println("获取文件绝对路径-getCanonicalPath：" + canonicalPath);

		// 获取父级文件路径
		String parent = file.getParent();
		System.out.println("获取父级文件路径：" + parent);

		// 获取文件字节数
		long length = file.length();
		System.out.println("获取到文件字节数：" + length);

		// 获取文件所在磁盘的总容量字节数
		long totalSpace = file.getTotalSpace();
		System.out.println("获取文件所在磁盘的总容量字节数： " + totalSpace);
		// 获取文件所在磁盘的已使用容量字节数
		long usableSpace = file.getUsableSpace();
		System.out.println("获取文件所在磁盘的已使用容量字节数： " + usableSpace);
		// 获取文件所在磁盘的未使用容量字节数
		long freeSpace = file.getFreeSpace();
		System.out.println("获取文件所在磁盘的未使用容量字节数： " + freeSpace);

		// 获取最后一次修改文件的时间
		long lastedModified = file.lastModified();
		System.out.println("获取到文件最后一次修改时间：" + lastedModified);

		// 获取文件路径（可能是绝对路径，也可能是相对路径）
		String path = file.getPath();
		System.out.println("获取文件路径：" + path);
	}
}
