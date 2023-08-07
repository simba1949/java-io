package top.simba1949.io.characterStream.byteToCharacter.file;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author anthony
 * @date 2023/7/28
 */
public class FileRWApplication {
    public static void main(String[] args) {
        // 读和写
        readerAndWriter();
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
