package top.simba1949.io.byteStream.byteArray;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author anthony
 * @date 2023/7/28
 */
public class ByteArrayInputStreamApplication {
    public static void main(String[] args) {
        // ByteArrayInputStream 单字节读取
        singleByteRead();

        // ByteArrayInputStream 自定义缓存读取数据
        // flushRead();
    }

    /**
     * ByteArrayInputStream 单字节读取
     */
    public static void singleByteRead() {
        // 读取的字符
        String readStr = "When the darkness falls will you please shine her the way (shine he the way).";
        // 创建源
        byte[] readStrBytes = readStr.getBytes();

        ByteArrayInputStream bais = null;
        try {
            // 选择流
            bais = new ByteArrayInputStream(readStrBytes);

            // 每次读取的字节信息
            int readByte = -1;
            while ((readByte = bais.read()) != -1) {
                System.out.print((char) readByte);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != bais) {
                try {
                    bais.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 自定义缓存读取数据
     */
    public static void flushRead() {
        // 读取的字符
        String readStr = "When the darkness falls will you please shine her the way (shine he the way).";
        // 创建源
        byte[] readStrBytes = readStr.getBytes();

        // 选择流
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(readStrBytes);
            // 自定义缓冲区
            byte[] flush = new byte[2];
            // 每次读取多少字节数，len 表示每次读取字节数的实际大小，内容最后一次读取完成后，还有进行一次读取，但是读取内容为空，返回-1
            int len = -1;
            while ((len = bais.read(flush)) != -1) {
                System.out.print(new String(flush, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bais) {
                try {
                    bais.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
