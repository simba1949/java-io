package top.simba1949.io.byteStream.buffered;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author anthony
 * @date 2023/7/28
 */
public class BufferedInputStreamApplication {
    public static void main(String[] args) {
        // 单个字节读取
        // read();

        // 使用自定义缓冲区读取
        useCustomBufferRead();
    }

    /**
     * 单个字节读取
     */
    public static void read() {
        String filePath = "./java-io-start/src/main/resources/file/byte/filter/BufferedInputStream";
        File file = new File(filePath);

        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(file));

            // 每次读取的字节信息
            int readByte = -1;
            while ((readByte = bis.read()) != -1) {
                System.out.print((char) readByte);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bis) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 使用自定义缓冲区读取
     */
    public static void useCustomBufferRead() {
        String filePath = "./java-io-start/src/main/resources/file/byte/filter/BufferedInputStream";
        File file = new File(filePath);

        BufferedInputStream bis = null;
        try {
            // 选择流
            bis = new BufferedInputStream(new FileInputStream(file));
            // 缓冲区
            byte[] flush = new byte[1024];
            // 每次读取的长度
            int len = -1;
            while ((len = bis.read(flush)) != -1) {
                // 每次打印缓冲中的数据
                for (int i = 0; i < len; i++) {
                    System.out.print((char) flush[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (null != bis) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
