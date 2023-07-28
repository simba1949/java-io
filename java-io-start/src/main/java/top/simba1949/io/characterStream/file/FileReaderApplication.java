package top.simba1949.io.characterStream.file;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author anthony
 * @date 2023/7/28
 */
public class FileReaderApplication {
    public static void main(String[] args) {
        // 单字符读取
        // singleCharReader();

        // 自定义缓存读取
        // customFlushRead();
    }

    /**
     * 单字符读取
     */
    public static void singleCharReader() {
        // 创建源
        String readFilePath = "./java-io-start/src/main/resources/file/FileReader";
        File readFile = new File(readFilePath);

        // 选择流
        FileReader reader = null;
        try {
            reader = new FileReader(readFile);

            // 每次读取的字符信息
            int readChar;
            while ((readChar = reader.read()) != -1) {
                System.out.print("" + (char) readChar);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 自定义缓存读取
     */
    public static void customFlushRead() {
        // 创建源
        String readFilePath = "./java-io-start/src/main/resources/file/FileReader";
        File readFile = new File(readFilePath);

        // 选择流
        FileReader reader = null;
        try {
            reader = new FileReader(readFile);

            // 缓冲区
            char[] flush = new char[10];
            // 每次读取的长度
            int len = -1;
            while ((len = reader.read(flush)) != -1) {
                System.out.print(new String(flush, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
