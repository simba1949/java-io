package top.simba1949.io.byteStream.buffered;

import java.io.*;

/**
 * @author anthony
 * @version 2023/7/27 22:58
 */
public class BufferedIOStreamApplication {

    /**
     * 字节缓冲流（文本字节流的装饰者）
     * BufferedInputStream 和 BufferedOutputStream
     *
     * @param args
     */
    public static void main(String[] args) {
        // BufferedInputStream 和 BufferedOutputStream 进行读写
        readAndWrite();
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
            // 缓冲区
            byte[] flush = new byte[1024 * 8];
            // 每次读取的字节长度
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
