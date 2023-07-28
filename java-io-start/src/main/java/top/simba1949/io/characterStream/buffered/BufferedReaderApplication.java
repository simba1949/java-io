package top.simba1949.io.characterStream.buffered;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author anthony
 * @date 2023/7/28
 */
public class BufferedReaderApplication {
    public static void main(String[] args) {
        // 单个字符读取
        // singleCharRead();

        // 自定义缓存读取
        // customFlushRead();
    }

    /**
     * 单个字符读取
     */
    public static void singleCharRead() {
        // 创建输入源
        String readFilePath = "./java-io-start/src/main/resources/file/BufferedReader";
        File readFile = new File(readFilePath);

        // 选择流
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(readFile));
            // 每次都去的字符信息
            int readChar = -1;
            while ((readChar = reader.read()) != -1) {
                System.out.print((char) readChar);
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

    /**
     * 自定义缓存读取
     */
    public static void customFlushRead() {
        // 创建输入源
        String readFilePath = "./java-io-start/src/main/resources/file/BufferedReader";
        File readFile = new File(readFilePath);

        // 选择流
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(readFile));

            // 定义缓冲区
            char[] flush = new char[10];
            // 每次读取的字符数
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
