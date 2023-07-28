package top.simba1949.io.byteStream.byteArray;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author anthony
 * @date 2023/7/28
 */
public class ByteArrayOutputStreamApplication {
    public static void main(String[] args) {
        // ByteArrayOutputStream 写入
        // write();
    }

    /**
     * ByteArrayOutputStream 写入
     */
    public static void write() {
        // 创建输入出源
        String msg = "When the darkness falls will you please shine her the way (shine he the way).";

        byte[] bytes = null;
        // 选择流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            // 写入到输出流流中
            baos.write(msg.getBytes());

            // 从输出流中获取字节数组
            byte[] destSrc = baos.toByteArray();
            String s = new String(destSrc, 0, destSrc.length);
            System.out.print(s);

            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != baos) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
