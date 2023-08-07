package top.simba1949.io.byteStream.buffered;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author anthony
 * @date 2023/7/28
 */
public class BufferedOutputStreamApplication {
    public static void main(String[] args) {
        // 使用 BufferedOutputStream 写
        write();
    }

    /**
     * BufferedOutputStream 写
     */
    public static void write() {
        String filePath = "./java-io-start/src/main/resources/file/byte/BufferedOutputStream";
        File file = new File(filePath);

        // 创建输入源
        String msg = "When the darkness falls will you please shine her the way (shine he the way)";

        BufferedOutputStream bos = null;
        try {
            // 选择 BufferedOutputStream
            bos = new BufferedOutputStream(new FileOutputStream(file));
            // 将数据写入
            bos.write(msg.getBytes());
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (null != bos) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
