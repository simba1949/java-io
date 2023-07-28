package top.simba1949.io;

import java.io.*;

/**
 * @author anthony
 * @version 2023/7/27 22:58
 */
public class BufferedIOApplication {

    /**
     * 字节缓冲流（文本字节流的装饰者）
     * BufferedInputStream 和 BufferedOutputStream
     *
     * @param args
     */
    public static void main(String[] args) {
        // 字节缓冲流读取
        // read();

        // 使用自定义缓冲区读取
        // useCustomBufferRead();

        // 使用 BufferedOutputStream 写
        // write();

        // BufferedInputStream 和 BufferedOutputStream 进行读写
        readAndWrite();
    }

    /**
     * 单个字节读取
     */
    public static void read() {
        String filePath = "./java-io-start/src/main/resources/file/BufferedInputStream";
        File file = new File(filePath);

        try (
                // 创建一个
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))
        ) {
            // 操作流
            int readByte = -1;
            while ((readByte = bis.read()) != -1) {
                System.out.print("读取的字节信息是：" + (char) readByte);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用自定义缓冲区读取
     */
    public static void useCustomBufferRead() {
        String filePath = "./java-io-start/src/main/resources/file/BufferedInputStream";
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
                //
                for (int i = 0; i < len; i++) {
                    System.out.print("读取的字节信息是：" + (char) flush[i]);
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

    /**
     * BufferedOutputStream 写
     */
    public static void write() {
        String filePath = "./java-io-start/src/main/resources/file/BufferedOutputStream";
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

    /**
     * BufferedInputStream 和 BufferedOutputStream 进行读写
     */
    public static void readAndWrite() {
        String readFilePath = "./java-io-start/src/main/resources/file/BufferedInputStream";
        File readFile = new File(readFilePath);

        String writeFilePath = "./java-io-start/src/main/resources/file/BufferedOutputStream";
        File writeFile = new File(writeFilePath);

        // 选择流
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(new FileInputStream(readFile));
            // false 表示每次写入，不追加直接覆盖，true表示追加
            bos = new BufferedOutputStream(new FileOutputStream(writeFile, false));
            // 操作流
            byte[] flush = new byte[1024 * 8];
            int len = -1;
            while ((len = bis.read(flush)) != -1) {
                bos.write(flush, 0, len);
            }
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流（先关闭输出流，再关闭输入流）
            if (null != bos) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

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
