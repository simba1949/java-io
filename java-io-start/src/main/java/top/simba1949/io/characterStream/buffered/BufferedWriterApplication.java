package top.simba1949.io.characterStream.buffered;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author anthony
 * @date 2023/7/28
 */
public class BufferedWriterApplication {
    public static void main(String[] args) {
        // 使用 BufferedWriter 进行写
        writer();
    }

    /**
     * 使用 BufferedWriter 进行写
     */
    public static void writer() {
        // 创建输出源
        String writeFilePath = "./java-io-start/src/main/resources/file/character/FileWriter";
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
}
