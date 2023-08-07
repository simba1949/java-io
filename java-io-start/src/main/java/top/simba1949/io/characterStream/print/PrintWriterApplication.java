package top.simba1949.io.characterStream.print;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * @author anthony
 * @date 2023/7/28
 */
public class PrintWriterApplication {
    public static void main(String[] args) throws FileNotFoundException {
        printToFile();
    }

    /**
     * 重定向到文件中
     *
     * @throws FileNotFoundException
     */
    public static void printToFile() throws FileNotFoundException {
        // 创建输出源
        String writeFilePath = "./java-io-start/src/main/resources/file/character/PrintWriter";

        PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(new FileOutputStream(writeFilePath)), true);
        printWriter.println("重定向到文件中：君不见黄河之水天上来");
        printWriter.flush();
        printWriter.close();
    }
}
