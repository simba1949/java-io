package top.simba1949.io.characterStream.string;

import java.io.IOException;
import java.io.StringWriter;

/**
 * @author anthony
 * @date 2023/8/7
 */
public class StringWriteApplication {
    public static void main(String[] args) {
        write();
    }

    /**
     * 写
     */
    public static void write() {
        StringWriter stringWriter = null;
        try {
            stringWriter = new StringWriter();
            stringWriter.write("君不见黄河之水天上来");

            System.out.println(stringWriter.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != stringWriter) {
                try {
                    stringWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
