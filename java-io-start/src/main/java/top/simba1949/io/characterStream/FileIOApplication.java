package top.simba1949.io.characterStream;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author anthony
 * @date 2023/7/28
 */
public class FileIOApplication {
    public static void main(String[] args) {
        // 单字符读取
        // singleCharReader();

        // 自定义缓存读取
        // customFlushRead();

        // 写
        // write();

        // 读和写
        readerAndWriter();
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

    /**
     * 写
     */
    public static void write() {
        // 创建输出源
        String writeFilePath = "./java-io-start/src/main/resources/file/FileWriter";
        File writeFile = new File(writeFilePath);
        // 字符
        String readStr = "天道酬勤，the time of show me code";

        // 选择流
        FileWriter writer = null;
        try {
            writer = new FileWriter(writeFile);
            // 操作流
            writer.write(readStr, 0, readStr.length());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != writer) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 读和写
     */
    public static void readerAndWriter() {
        // 创建输入源
        String readFilePath = "./java-io-start/src/main/resources/file/FileReader";
        File readFile = new File(readFilePath);
        // 创建输出源
        String writeFilePath = "./java-io-start/src/main/resources/file/FileWriter";
        File writeFile = new File(writeFilePath);

        // 选择流
        FileReader reader = null;
        FileWriter writer = null;

        try {
            reader = new FileReader(readFile);
            // false 表示覆盖，true 表示追加在文件末尾
            writer = new FileWriter(writeFile, false);

            // 缓冲区
            char[] flush = new char[10];
            // 每次读取的长度
            int len = -1;
            while ((len = reader.read(flush)) != -1) {
                writer.write(flush, 0, len);
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (null != writer) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

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
