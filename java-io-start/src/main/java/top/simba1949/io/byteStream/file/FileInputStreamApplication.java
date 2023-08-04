package top.simba1949.io.byteStream.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author anthony
 * @date 2023/7/28
 */
public class FileInputStreamApplication {
    public static void main(String[] args) {
        // FileInputStream 每次读取一个字节信息
        // tryCatchFinally();

        // FileInputStream 每次读取一个字节信息
        // tryWithResources();

        // FileInputStream 使用自定义缓冲区读取字节信息
        customBufferedRead();
    }

    /**
     * FileInputStream 每次读取一个字节信息
     * 使用传统的 try-catch-finally
     */
    public static void tryCatchFinally() {
        String filePath = "./java-io-start/src/main/resources/file/byte/fileInputStream";
        File file = new File(filePath);

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);

            // 对流进行操作，每次读取一个字节，将字节值赋值给 readByte，读到没有值则返回 -1
            int readByte = -1;
            while ((readByte = fis.read()) != -1) {
                System.out.print((char) readByte);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * FileInputStream 每次读取一个字节信息
     * 使用语法糖 try-with-resources
     */
    public static void tryWithResources() {
        String filePath = "./java-io-start/src/main/resources/file/byte/fileInputStream";
        File file = new File(filePath);

        try (
                FileInputStream fis = new FileInputStream(file);
        ) {
            // 对流进行操作，每次读取一个字节，将字节值赋值给 temp，读到没有值则返回 -1
            int readByte = -1;
            while ((readByte = fis.read()) != -1) {
                System.out.print((char) readByte);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用自定义缓存区读取数据
     */
    public static void customBufferedRead() {
        String filePath = "./java-io-start/src/main/resources/file/byte/fileInputStream";
        File file = new File(filePath);

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            // 自定义缓存区
            byte[] flush = new byte[8];
            // 每次读取多少字节数，len 表示每次读取字节数的实际大小，内容最后一次读取完成后，还有进行一次读取，但是读取内容为空，返回-1
            int len = -1;
            // 每次读取 flush.length 字节数，并存储在 flush 中
            while ((len = fis.read(flush)) != -1) {
                for (int i = 0; i < len; i++) {
                    System.out.print((char) flush[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
