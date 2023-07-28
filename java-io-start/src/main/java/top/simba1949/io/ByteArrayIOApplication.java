package top.simba1949.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author anthony
 * @date 2023/7/28
 */
public class ByteArrayIOApplication {

    /**
     * 字节数组源可以看做是本地电脑的内存、远程服务器的内存、网络上的内存，Java 可以直接访问，流可以不用关闭，由 GC 进行关闭，关闭方法是空方法。
     * 任何东西都可以转换成字节数组。字节数组存储在内存，尽可能占用内存小。
     * ByteArrayInputStream ：二进制——>字符
     * ByteArrayOutputStream ：字符——>二进制
     *
     * @param args
     */
    public static void main(String[] args) {
        // ByteArrayInputStream 单字节读取
        //singleByteRead();

        // ByteArrayInputStream 自定义缓存读取数据
        // flushRead();

        // ByteArrayOutputStream 写入
        // write();

        // ByteArrayInputStream 和 ByteArrayOutputStream 进行读写
        readAndWrite();
    }

    /**
     * ByteArrayInputStream 单字节读取
     */
    public static void singleByteRead() {
        // 读取的字符
        String readStr = "When the darkness falls will you please shine her the way (shine he the way).";
        // 创建源
        byte[] readStrBytes = readStr.getBytes();

        // 选择流
        ByteArrayInputStream is = new ByteArrayInputStream(readStrBytes);
        int temp = -1;
        while ((temp = is.read()) != -1) {
            System.out.print((char) temp);
        }
        try {
            // 关闭流
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
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
        ByteArrayInputStream is = new ByteArrayInputStream(readStrBytes);
        try {
            // 自定义缓冲区
            byte[] flush = new byte[2];
            // 每次读取多少字节数，len 表示每次读取字节数的实际大小，内容最后一次读取完成后，还有进行一次读取，但是读取内容为空，返回-1
            int len = -1;
            while ((len = is.read(flush)) != -1) {
                System.out.print(new String(flush, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
            // 操作流
            byte[] flush = new byte[10];
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
