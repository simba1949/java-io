package top.simba1949;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author anthony
 * @version 2023/7/26 22:09
 */
public class FileTestApplication {

	@Test
	public void test() throws IOException {
		// 相对路径：相对路径是基于整个项目下的路径
		String relativePath = "./../doc/testFileRelativePath.txt";
		File relativePathFile = new File(relativePath);

		if (relativePathFile.createNewFile()) {
			System.out.println("新文件创建完毕！");
		} else {
			System.out.println("文件已存在，无需创建！");
		}

		// 输出绝对路径
		System.out.println(relativePathFile.toPath().toRealPath());
	}
}
