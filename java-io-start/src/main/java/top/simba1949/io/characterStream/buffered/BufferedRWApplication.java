package top.simba1949.io.characterStream.buffered;

import java.io.*;

/**
 * @author anthony
 * @date 2023/7/28
 */
public class BufferedRWApplication {
    public static void main(String[] args) {


        // 使用 BufferedReader 和 BufferedWriter 进行读写
        readAndWrite();
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
