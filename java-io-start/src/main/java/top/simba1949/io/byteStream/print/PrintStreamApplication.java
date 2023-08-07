package top.simba1949.io.byteStream.print;

import java.io.*;

/**
 * @author anthony
 * @date 2023/7/28
 */
public class PrintStreamApplication {
    public static void main(String[] args) throws FileNotFoundException {
        // 重定向到文件
        // printToFile();
        // 重定向到控制台
        printToConsole();
    }

    /**
     * 重定向到文件
     *
     * @throws FileNotFoundException
     */
    public static void printToFile() throws FileNotFoundException {
        String readFilePath = "./java-io-start/src/main/resources/file/byte/PrintStream";
        File readFile = new File(readFilePath);

        PrintStream printStream = new PrintStream(new BufferedOutputStream(new FileOutputStream(readFile)), true);

        // 打印流重定向到文件中
        System.setOut(printStream);
        printStream.println("重定向到文件中：君不见黄河之水天上来");
        printStream.close();
    }

    /**
     * 重定向到控制台
     */
    public static void printToConsole() {
        PrintStream printStream = new PrintStream(new BufferedOutputStream(new FileOutputStream(FileDescriptor.out)), true);
        // 在重定向到控制台
        System.setOut(printStream);
        printStream.println("重定向到控制台");
    }
}
