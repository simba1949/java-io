package top.simba1949.io.byteStream.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author anthony
 * @date 2023/7/28
 */
public class FileOutputStreamApplication {
    public static void main(String[] args) {
        // 写操作
        // writer();
    }

    /**
     * 写
     */
    public static void writer() {
        // 创建写的源
        String filePath = "./java-io-start/src/main/resources/file/fileOutputStream";
        File file = new File(filePath);

        //
        String msg = "So long lives this and this gives life to thee.";
        byte[] src = msg.getBytes();

        try (
                // 选择流，写入是否追加，默认 false 表示重写， true 表示在文件后面追加
                FileOutputStream os = new FileOutputStream(file, true);
        ) {
            os.write(src, 0, src.length);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
