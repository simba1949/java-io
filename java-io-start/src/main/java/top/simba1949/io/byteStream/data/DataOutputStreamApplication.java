package top.simba1949.io.byteStream.data;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author anthony
 * @date 2023/8/7
 */
public class DataOutputStreamApplication {
    public static void main(String[] args) {
        write();
    }

    /**
     * DataOutputStream 写
     */
    public static void write() {
        String filePath = "./java-io-start/src/main/resources/file/byte/filter/DataOutputStream";
        File file = new File(filePath);

        DataOutputStream dos = null;
        try {
            FileOutputStream fos = new FileOutputStream(file);
            dos = new DataOutputStream(fos);

            dos.writeUTF("君不见黄河之水天上来");
            dos.writeBoolean(true);
            dos.writeInt(9);

            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != dos) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
