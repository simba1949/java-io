package top.simba1949.io.byteToCharacter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author anthony
 * @date 2023/7/28
 */
public class InputStreamReaderApplication {
    public static void main(String[] args) {
        // 单字符读取
        // singleCharRead();

        // 自定义缓存读取
        customFlushRead();
    }

    /**
     * 单字符读取
     */
    public static void singleCharRead() {
        // 1.创建输入源
        String readFilePath = "./java-io-start/src/main/resources/file/InputStreamReader";
        File readFile = new File(readFilePath);
        // 2.选择流
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(new FileInputStream(readFile));
            // 3.操作流
            // readByte 每次读取的字节信息
            int readByte = -1;
            while ((readByte = isr.read()) != -1) {
                System.out.print((char) readByte);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != isr) {
                try {
                    isr.close();
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
        // 1.创建输入源
        String readFilePath = "./java-io-start/src/main/resources/file/InputStreamReader";
        File readFile = new File(readFilePath);

        // 2.选择流
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(new FileInputStream(readFile));
            // 3.操作流
            // 自定义缓冲区
            char[] flush = new char[10];
            // 每次读取的字符长度
            int len = -1;
            while ((len = isr.read(flush)) != -1) {
                System.out.print(new String(flush, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4.关闭流
            if (null != isr) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
