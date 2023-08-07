package top.simba1949.io.characterStream.byteToCharacter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @author anthony
 * @date 2023/7/28
 */
public class OutputStreamWriterApplication {
    public static void main(String[] args) {
        // OutputStreamWriter 写
        write();
    }

    /**
     * OutputStreamWriter 写
     */
    public static void write() {
        // 1.创建输出源
        String writeFilePath = "./java-io-start/src/main/resources/file/character/OutputStreamWriter";
        File writeFile = new File(writeFilePath);

        // 2.选择流
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(new FileOutputStream(writeFile, false));
            String msg = "把乾坤留在我心中的那一刻，就注定我不会寂寞。";
            // 3.操作流
            osw.write(msg, 0, msg.length());
            osw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4.关闭流
            if (null != osw) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
