package top.simba1949.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * 创建 File 对象的几种方式
 * 1. public File(String pathname) ：pathname 为文件路径，可以是绝对路径，可以是相对路径，创建 File 对象；
 * 2. public File(File parent, String child) ：根据父 File 对象，和 child 路径，创建 File 对象；
 * 3. public File(String parent, String child) ：以 parent 为父路径，以 child 为子路径，创建 File 对象；
 * 4. public File(URI uri) ：基于网络资源，创建 File 对象；
 *
 * @author anthony
 * @version 2023/7/26 21:27
 */
public class CreateFileApplication {
	public static final Logger log = LoggerFactory.getLogger(CreateFileApplication.class);

	/**
	 * 基于文件路径，创建文件对象
	 * <p>
	 * Windows
	 * 绝对路径：
	 * - D:\\Program Files\\file.txt
	 * - D:/Program Files/file.txt
	 * 相对路径：（如果是在 main 包下相对路径是基于整个项目下的路径；如果是在 test 包下相对路径是基于该模块下的路径）
	 * - ./../file.txt
	 * - .\..\file.txt
	 * </p>
	 * <p>
	 * Linux
	 * 绝对路径：/data/file.txt
	 * 相对路径：./../file.text
	 * </p>
	 */
	public static void main(String[] args) throws IOException {
		// 绝对路径
		// absolutePath1();
		// absolutePath2();

		// 相对路径
		// relativePath1();
		// relativePath2();

		netPath();
	}

	/**
	 * 基于网络资源，创建 File 对象；
	 */
	public static void netPath() {
		// 网络资源
		String netPath = "https://repo1.maven.org/maven2/org/junit/jupiter/junit-jupiter/5.10.0/junit-jupiter-5.10.0.jar";
		File netPathFile = new File(netPath);

		// 输出网络文件名
		log.info("获取到的网络资源文件名：{}", netPathFile.getName());
	}

	public static void absolutePath1() throws IOException {
		// 绝对路径
		String absolutePath = "D:\\Program Files\\file.txt";
		File absolutePathFile = new File(absolutePath);

		// 输出绝对路径
		log.info("当前文件对象的绝对路径是：{}", absolutePathFile.toPath().toAbsolutePath());

		if (absolutePathFile.createNewFile()) {
			log.info("新文件创建完毕！");
		} else {
			log.info("文件已存在，无需创建！");
		}
	}

	/**
	 * 绝对路径2
	 *
	 * @throws IOException
	 */
	public static void absolutePath2() throws IOException {
		// 绝对路径
		String absolutePath = "D:/Program Files/file.txt";
		File absolutePathFile = new File(absolutePath);

		// 输出绝对路径
		log.info("当前文件对象的绝对路径是：{}", absolutePathFile.toPath().toAbsolutePath());

		if (absolutePathFile.createNewFile()) {
			log.info("新文件创建完毕！");
		} else {
			log.info("文件已存在，无需创建！");
		}
	}

	/**
	 * 相对路径1
	 *
	 * @throws IOException
	 */
	public static void relativePath1() throws IOException {
		// 相对路径
		String relativePath = "./../file.txt";
		File relativePathFile = new File(relativePath);

		// 输出绝对路径
		log.info("当前文件对象的绝对路径是：{}", relativePathFile.toPath().toAbsolutePath());

		if (relativePathFile.createNewFile()) {
			log.info("新文件创建完毕！");
		} else {
			log.info("文件已存在，无需创建！");
		}
	}

	/**
	 * 相对路径2
	 *
	 * @throws IOException
	 */
	public static void relativePath2() throws IOException {
		// 相对路径：
		String relativePath = ".\\..\\file.txt";
		File relativePathFile = new File(relativePath);

		// 输出绝对路径
		log.info("当前文件对象的绝对路径是：{}", relativePathFile.toPath().toAbsolutePath());

		if (relativePathFile.createNewFile()) {
			log.info("新文件创建完毕！");
		} else {
			log.info("文件已存在，无需创建！");
		}
	}
}