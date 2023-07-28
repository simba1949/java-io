package top.simba1949.io.byteStream.sequence;

import java.io.*;
import java.util.Vector;

/**
 * 序列化流，或者合并流
 * 从输入流的有序集合开始，并从第一个输入流开始读取，直到到达文件末尾，
 * 接着从第二个输入流读取，依次类推，直到到达包含的最后一个输入流的文件末尾为止。
 *
 * @author anthony
 * @date 2023/7/28
 */
public class SequenceInputStreamApplication {
    public static void main(String[] args) throws IOException {
        merge();
    }

    public static void merge() throws IOException {
        // 将该文件夹下所有的文件合并输出到一个文件中
        String dirFilePath = "./java-io-start/src/main/resources/file/SequenceInputStream/dir";
        File dirFile = new File(dirFilePath);

        // 创建输出源
        String writeFilePath = "./java-io-start/src/main/resources/file/SequenceInputStream/merge";
        // 这里需要追加内容才能合并
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(writeFilePath, true));

        Vector<InputStream> vector = new Vector<>();
        SequenceInputStream sis = null;
        for (int i = 0; i < dirFile.listFiles().length; i++) {
            vector.add(new BufferedInputStream(new FileInputStream(dirFilePath + "/" + i + ".tmp")));
        }
        sis = new SequenceInputStream(vector.elements());

        // 创建缓冲区
        byte[] flush = new byte[1024];
        int len = -1;
        while ((len = sis.read(flush)) != -1) {
            bos.write(flush, 0, len);
        }

        bos.flush();
        bos.close();
        sis.close();
    }
}
