package top.simba1949.io.characterStream.filter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;

/**
 * @author anthony
 * @date 2023/8/7
 */
public class PushbackReaderApplication {
    public static void main(String[] args) {
        read();
    }

    /**
     * 读取
     */
    public static void read() {
        String filePath = "./java-io-start/src/main/resources/file/character/PushbackReader";
        File file = new File(filePath);

        PushbackReader pushbackReader = null;
        try {
            pushbackReader = new PushbackReader(new FileReader(file));

            int readChar;
            boolean lastReadFlag = false;
            while ((readChar = pushbackReader.read()) != -1) {
                char charData = (char) readChar;
                if ('\n' == charData) {
                    if (lastReadFlag) {
                        // 如果是上次的标识，直接跳过，然后置为false
                        lastReadFlag = false;
                        // 用于切分
                        System.out.println("\n");
                    } else {
                        // 如果不是上次一次的标识，推回到流中，然后置为true
                        pushbackReader.unread(readChar);
                        lastReadFlag = true;
                    }
                } else {
                    System.out.print(charData);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != pushbackReader) {
                try {
                    pushbackReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
