package top.simba1949.io.byteStream.print;

import java.io.*;

/**
 * @author anthony
 * @date 2023/7/28
 */
public class PrintStreamApplication {
    public static void main(String[] args) throws FileNotFoundException {
        printStream();
    }

    public static void printStream() throws FileNotFoundException {
        String readFilePath = "./java-io-start/src/main/resources/file/PrintStream";
        File readFile = new File(readFilePath);
        PrintStream printStream = new PrintStream(new BufferedOutputStream(new FileOutputStream(readFile)), true);
        // 打印流重定向到文件中
        System.setOut(printStream);
        printStream.println("重定向到文件中");
        printStream.close();

        // 在重定向到控制台
        System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(FileDescriptor.out)), true));
        System.out.println("重定向到控制台");
    }
}
