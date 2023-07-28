package top.simba1949.io.characterStream;

import java.io.*;

/**
 * @author anthony
 * @date 2023/7/28
 */
public class BufferedIOApplication {
    public static void main(String[] args) {
        // 单个字符读取
        // singleCharRead();

        // 自定义缓存读取
        // customFlushRead();

        // 使用 BufferedWriter 进行写
        // writer();

        // 使用 BufferedReader 和 BufferedWriter 进行读写
        readAndWrite();
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

    /**
     * 使用 BufferedWriter 进行写
     */
    public static void writer() {
        // 创建输出源
        String writeFilePath = "./java-io-start/src/main/resources/file/FileWriter";
        File writeFile = new File(writeFilePath);

        // 2.选择流
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(writeFile));
            String src = "dear god, 哦，我亲爱的上帝";
            // 3.操作流
            writer.write(src);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4.关闭流
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
     * 使用 BufferedReader 和 BufferedWriter 进行读写
     */
    public static void readAndWrite() {
        // 创建输入源
        String readFilePath = "./java-io-start/src/main/resources/file/BufferedReader";
        File readFile = new File(readFilePath);
        // 创建输出源
        String writeFilePath = "./java-io-start/src/main/resources/file/BufferedWriter";
        File writeFile = new File(writeFilePath);

        // 2.选择流
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(readFile));
            writer = new BufferedWriter(new FileWriter(writeFile));
            // 缓冲区
            char[] flush = new char[10];
            // 每次读取的字符长度
            int len = -1;
            while ((len = reader.read(flush)) != -1) {
                writer.write(flush, 0, len);
            }
            // 对输出流进行 flush
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4.关闭流
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
