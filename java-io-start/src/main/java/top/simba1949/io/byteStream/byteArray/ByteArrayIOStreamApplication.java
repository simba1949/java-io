package top.simba1949.io.byteStream.byteArray;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author anthony
 * @date 2023/7/28
 */
public class ByteArrayIOStreamApplication {

    /**
     * 字节数组源可以看做是本地电脑的内存、远程服务器的内存、网络上的内存，Java 可以直接访问，流可以不用关闭，由 GC 进行关闭，关闭方法是空方法。
     * 任何东西都可以转换成字节数组。字节数组存储在内存，尽可能占用内存小。
     * ByteArrayInputStream ：二进制——>字符
     * ByteArrayOutputStream ：字符——>二进制
     *
     * @param args
     */
    public static void main(String[] args) {
        // ByteArrayInputStream 和 ByteArrayOutputStream 进行读写
        readAndWrite();
    }

    /**
     * ByteArrayInputStream 和 ByteArrayOutputStream 进行读写
     */
    public static void readAndWrite() {
        // 创建源
        String readStr = "God will you let her know that I love her so.";
        byte[] readStrBytes = readStr.getBytes();

        // 选择流
        ByteArrayInputStream bais = new ByteArrayInputStream(readStrBytes);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            // 缓冲区
            byte[] flush = new byte[10];
            // 每次读取的字节长度
            int len = -1;
            while ((len = bais.read(flush)) != -1) {
                // 每次读取 flush.length 字节数，但最终 os 拥有全部数据， os.toByteArray() 指的是 n 次读取数据的累加
                baos.write(flush, 0, len);
                baos.flush();
            }

            byte[] bytes = baos.toByteArray();
            System.out.print(new String(bytes, 0, bytes.length));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (null != baos) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

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
