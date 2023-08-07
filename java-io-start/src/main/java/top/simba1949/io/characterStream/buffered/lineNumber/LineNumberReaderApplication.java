package top.simba1949.io.characterStream.buffered.lineNumber;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * @author anthony
 * @date 2023/8/7
 */
public class LineNumberReaderApplication {
    public static void main(String[] args) {
        read();
    }

    /**
     * 按行读取
     */
    public static void read() {
        // 创建输入源
        String readFilePath = "./java-io-start/src/main/resources/file/character/BufferedReader";
        File readFile = new File(readFilePath);

        LineNumberReader lineNumberReader = null;
        try {
            lineNumberReader = new LineNumberReader(new FileReader(readFile));
            String readStr;
            while ((readStr = lineNumberReader.readLine()) != null) {
                System.out.println(readStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != lineNumberReader) {
                try {
                    lineNumberReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
