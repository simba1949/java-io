package top.simba1949.io.byteToCharacter;

import java.io.*;

/**
 * @author anthony
 * @date 2023/7/28
 */
public class IOToRWApplication {
    public static void main(String[] args) {
        readAndWrite();
    }

    public static void readAndWrite() {
        // 1.创建源
        // 创建输入源
        String readFilePath = "./java-io-start/src/main/resources/file/InputStreamReader";
        File readFile = new File(readFilePath);
        // 创建输出源
        String writeFilePath = "./java-io-start/src/main/resources/file/OutputStreamWriter";
        File writeFile = new File(writeFilePath);

        // 2.选择流
        InputStreamReader isr = null;
        OutputStreamWriter osr = null;

        try {
            isr = new InputStreamReader(new FileInputStream(readFile));
            osr = new OutputStreamWriter(new FileOutputStream(writeFile));
            // 3.操作流
            // 自定义换乘功能区
            char[] flush = new char[10];
            // 每次读取的长度
            int len = -1;
            while ((len = isr.read(flush)) != -1) {
                osr.write(flush, 0, len);
            }
            osr.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4.关闭流
            if (null != osr) {
                try {
                    osr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != isr) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
