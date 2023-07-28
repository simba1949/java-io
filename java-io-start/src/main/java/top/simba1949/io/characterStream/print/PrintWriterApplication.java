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
        printWriter();
    }

    public static void printWriter() throws FileNotFoundException {
        // 创建输出源
        String writeFilePath = "./java-io-start/src/main/resources/file/PrintWriter";

        PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(new FileOutputStream(writeFilePath)));
        printWriter.println("printWriter 流笔记");
        printWriter.flush();
        printWriter.close();
    }
}
