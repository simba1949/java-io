package top.simba1949.io.byteStream.filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;

/**
 * @author anthony
 * @date 2023/8/4
 */
public class PushbackInputStreamApplication {
    public static void main(String[] args) {
        input();
    }

    /**
     * 使用推回输入流
     */
    public static void input() {
        String filePath = "./java-io-start/src/main/resources/file/byte/filter/PushbackInputStream";
        File file = new File(filePath);

        PushbackInputStream pis = null;
        try {
            pis = new PushbackInputStream(new FileInputStream(file));

            for (int i = 0; i < 9; i++) {
                int readByte = -1;
                boolean firstPerFlag = true;
                while ((readByte = pis.read()) != -1) {
                    char readChar = (char) readByte;
                    if (!firstPerFlag) {
                        pis.skip(1);
                    }

                    if ('\n' == readChar && firstPerFlag) {
                        // 如果读取的字符是 I，回退该字符到缓冲流中
                        pis.unread(readByte);
                        firstPerFlag = false;
                        break;
                    } else {
                        System.out.print(readChar);
                    }
                }
                System.out.println("-------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != pis) {
                try {
                    pis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
