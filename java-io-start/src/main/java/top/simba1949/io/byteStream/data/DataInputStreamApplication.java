package top.simba1949.io.byteStream.data;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author anthony
 * @date 2023/8/7
 */
public class DataInputStreamApplication {
    public static void main(String[] args) {
        read();
    }

    /**
     * DataOutputStream 读
     */
    public static void read() {
        String filePath = "./java-io-start/src/main/resources/file/byte/filter/DataOutputStream";
        File file = new File(filePath);

        DataInputStream dis = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            dis = new DataInputStream(fis);

            // 读的顺序要和写的顺序一致
            String readStr = dis.readUTF();
            boolean readBl = dis.readBoolean();
            int readInt = dis.readInt();

            System.out.println("读取的数据如下：");
            System.out.println("readStr=" + readStr);
            System.out.println("readBl=" + readBl);
            System.out.println("readInt=" + readInt);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != dis) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
