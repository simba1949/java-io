package top.simba1949.io.byteStream.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author anthony
 * @date 2023/7/27
 */
public class FileIOStreamApplication {

    /**
     * FileInputStream 和 FileOutputStream 的运用
     *
     * @param args
     */
    public static void main(String[] args) {
        // 读和写
        readAndWrite();
    }

    /**
     * 读和写
     */
    public static void readAndWrite() {
        // 创建读的源
        String readFilePath = "./java-io-start/src/main/resources/file/fileInputStream";
        File readFile = new File(readFilePath);
        // 创建写的源
        String writeFilePath = "./java-io-start/src/main/resources/file/fileOutputStream";
        File writeFile = new File(writeFilePath);
        // 设置文件写，默认是只读
        writeFile.setWritable(Boolean.TRUE);

        try (
                // 创建输入流
                FileInputStream fis = new FileInputStream(readFile);
                // 创建输出流，写入是否追加，默认 false 表示重写， true 表示在文件后面追加
                FileOutputStream fos = new FileOutputStream(writeFile, false);
        ) {
            // 创建缓存区
            byte[] flush = new byte[1024 * 8];
            // 每次读取多少字节数，len 表示每次读取字节数的实际大小，内容最后一次读取完成后，还有进行一次读取，但是读取内容为空，返回-1
            int len = -1;
            while ((len = fis.read(flush)) != -1) {
                fos.write(flush, 0, len);
            }
            // 全部读取完毕后，flush 输出流
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
