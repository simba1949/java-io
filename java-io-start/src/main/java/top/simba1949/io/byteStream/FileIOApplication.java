package top.simba1949.io.byteStream;

import java.io.*;

/**
 * @author anthony
 * @date 2023/7/27
 */
public class FileIOApplication {

    /**
     * FileInputStream 和 FileOutputStream 的运用
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        // FileInputStream 每次读取一个字节信息
        // tryCatchFinally();

        // FileInputStream 每次读取一个字节信息
        // tryWithResources();

        // FileInputStream 使用自定义缓冲区读取字节信息
        // customBufferedRead();

        // 写操作
        // writer();

        // 读和写
        readAndWrite();
    }

    // =============================================
    // ================ InputStream ================
    // =============================================

    /**
     * FileInputStream 每次读取一个字节信息
     */
    public static void tryCatchFinally() {
        String filePath = "./java-io-start/src/main/resources/file/fileInputStream";
        File file = new File(filePath);

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);

            // 对流进行操作，每次读取一个字节，将字节值赋值给 readByte，读到没有值则返回 -1
            int readByte = -1;
            while ((readByte = fis.read()) != -1) {
                System.out.print("读取到的字节信息时{}" + (char) readByte);
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
     */
    public static void tryWithResources() {
        String filePath = "./java-io-start/src/main/resources/file/fileInputStream";
        File file = new File(filePath);

        try (
                FileInputStream fis = new FileInputStream(file);
        ) {
            // 对流进行操作，每次读取一个字节，将字节值赋值给 temp，读到没有值则返回 -1
            int readByte = -1;
            while ((readByte = fis.read()) != -1) {
                System.out.print("读取到的字节信息时{}" + (char) readByte);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用自定义缓存区读取数据
     */
    public static void customBufferedRead() {
        String filePath = "./java-io-start/src/main/resources/file/fileInputStream";
        File file = new File(filePath);

        // try-with-resource 方式
        try (
                FileInputStream fis = new FileInputStream(file);
        ) {
            // 自定义缓存区
            byte[] flush = new byte[8];
            // 每次读取多少字节数，len 表示每次读取字节数的实际大小，内容最后一次读取完成后，还有进行一次读取，但是读取内容为空，返回-1
            int len = -1;
            // 每次读取 flush.length 字节数，并存储在 flush 中
            while ((len = fis.read(flush)) != -1) {
                for (int i = 0; i < len; i++) {
                    System.out.print("读取到的字节信息时{}" + (char) flush[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // =============================================
    // ================ OutputStream ===============
    // =============================================

    /**
     * 写
     */
    public static void writer() {
        // 创建写的源
        String filePath = "./java-io-start/src/main/resources/file/fileOutputStream";
        File file = new File(filePath);

        //
        String msg = "So long lives this and this gives life to thee.";
        byte[] src = msg.getBytes();

        try (
                // 选择流，写入是否追加，默认 false 表示重写， true 表示在文件后面追加
                FileOutputStream os = new FileOutputStream(file, true);
        ) {
            os.write(src, 0, src.length);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // =============================================
    // ======= InputStream & OutputStream ==========
    // =============================================

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
