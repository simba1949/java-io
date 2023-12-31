package top.simba1949.io.byteStream.object;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @author anthony
 * @date 2023/8/4
 */
public class ObjectInputStreamApplication {
    public static void main(String[] args) {
        read();
    }

    /**
     * 使用 ObjectInputStream 读取 InputStream in 中的序列化数据
     */
    public static void read() {
        // 读取序列化数据文件
        String filePath = "./java-io-start/src/main/resources/file/byte/ObjectOutputStream";
        File file = new File(filePath);

        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            // ObjectInputStream 只有一个 public 构造方法：ObjectInputStream(InputStream in)
            // 使用 ObjectInputStream 读取 InputStream in 中序列化数据
            ois = new ObjectInputStream(fis);

            User user = (User) ois.readObject();
            System.out.println(user.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (null != ois) {
                try {
                    // 包装流底层会自动调用被包装流的 close 方法，这里只需要关闭包装流即可
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
