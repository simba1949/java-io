package top.simba1949.io.characterStream.charArray;

import java.io.CharArrayReader;
import java.io.IOException;

/**
 * @author anthony
 * @date 2023/8/7
 */
public class CharArrayReaderApplication {
    public static void main(String[] args) {
        read();
    }

    /**
     * 读
     */
    public static void read() {
        CharArrayReader car = null;
        try {
            char[] chars = "君不见黄河之水天上来".toCharArray();
            car = new CharArrayReader(chars);

            int readChar;
            while ((readChar = car.read()) != -1) {
                System.out.println((char) readChar);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != car) {
                car.close();
            }
        }
    }
}
