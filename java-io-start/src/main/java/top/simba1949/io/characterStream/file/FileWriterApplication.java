package top.simba1949.io.characterStream.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author anthony
 * @date 2023/7/28
 */
public class FileWriterApplication {
    public static void main(String[] args) {
        // 写
        // write();
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
}
