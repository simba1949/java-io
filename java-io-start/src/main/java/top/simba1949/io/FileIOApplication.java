package top.simba1949.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author anthony
 * @date 2023/7/27
 */
public class FileIOApplication {
    public static final Logger log = LoggerFactory.getLogger(FileIOApplication.class);

    public static void main(String[] args) throws FileNotFoundException {
        // FileInputStream 每次读取一个字节信息
        fileInputStreamAndTryCatchFinally();
    }

    /**
     * 使用 try-with-resources 风格
     */
    public static void tryWithResources() {

    }

    /**
     * 使用 try-catch-finally 风格
     */
    public static void tryCatchFinally() throws FileNotFoundException {
        String inFile = "";
        FileInputStream fis = new FileInputStream(inFile);

        String outFile = "";
        FileOutputStream fos = new FileOutputStream(outFile);
    }

    // =============================================
    // ================ InputStream ================
    // =============================================

    /**
     * FileInputStream 每次读取一个字节信息
     */
    public static void fileInputStreamAndTryCatchFinally() {
        String filePath = "./java-io-start/src/main/resources/file/fileInputStream";
        File file = new File(filePath);

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);

            // 对流进行操作，每次读取一个字节，将字节值赋值给 temp，读到没有值则返回 -1
            int temp = -1;
            while ((temp = fis.read()) != -1) {
                log.info("读取到的字节信息时{}", (char) temp);
            }
        } catch (IOException e) {
            log.info("异常信息{}", e.getMessage(), e);
        } finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    log.info("流关闭失败，异常信息：{}", e.getMessage(), e);
                }
            }
        }
    }

    public static void inputStreamAndTryWithResources() {
        String filePath = "";
        File file = new File(filePath);


    }

    // =============================================
    // ================ OutputStream ===============
    // =============================================

}
