package top.simba1949.io.characterStream.piped;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @author anthony
 * @date 2023/8/7
 */
public class PipedReaderApplication {
    public static void main(String[] args) {
        writerAndReader();
    }

    /**
     * 写和读
     */
    public static void writerAndReader() {

        try {
            PipedReader pipedReader = new PipedReader();
            PipedWriter pipedWriter = new PipedWriter();

            String src = "君不见黄河之水天上来";
            int length = src.length();

            // 向 PipedWriter 写数据
            new Thread(() -> {
                try {
                    pipedWriter.write(src);
                    pipedWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        pipedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            // 读取 PipedWriter 数据
            pipedReader.connect(pipedWriter);
            new Thread(() -> {
                try {
                    int readChar;
                    while ((readChar = pipedReader.read()) != -1) {
                        System.out.print((char) readChar);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        pipedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
