package top.simba1949.io.characterStream.string;

import java.io.IOException;
import java.io.StringReader;

/**
 * @author anthony
 * @date 2023/8/7
 */
public class StringReaderApplication {
    public static void main(String[] args) {
        read();
    }

    /**
     * 读
     */
    public static void read() {
        StringReader stringReader = null;
        try {
            String src = "君不见黄河之水天上来";
            stringReader = new StringReader(src);

            int readChar;
            while ((readChar = stringReader.read()) != -1) {
                System.out.println((char) readChar);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != stringReader) {
                stringReader.close();
            }
        }
    }
}
