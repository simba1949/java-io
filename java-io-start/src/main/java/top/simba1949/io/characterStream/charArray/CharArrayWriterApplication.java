package top.simba1949.io.characterStream.charArray;

import java.io.CharArrayWriter;
import java.io.IOException;

/**
 * @author anthony
 * @date 2023/8/7
 */
public class CharArrayWriterApplication {
    public static void main(String[] args) {
        write();
    }

    /**
     * 写到流中
     */
    public static void write() {
        CharArrayWriter charArrayWriter = null;
        try {
            charArrayWriter = new CharArrayWriter();
            charArrayWriter.write("君不见黄河之水天上来");

            System.out.println(charArrayWriter.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != charArrayWriter) {
                charArrayWriter.close();
            }
        }
    }
}
