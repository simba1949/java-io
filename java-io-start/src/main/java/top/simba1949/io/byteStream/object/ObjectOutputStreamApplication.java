package top.simba1949.io.byteStream.object;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author anthony
 * @date 2023/8/4
 */
public class ObjectOutputStreamApplication {
    public static void main(String[] args) {
        write();
    }

    /**
     * 使用 ObjectOutputStream 序列化对象到 OutputStream 中
     */
    public static void write() {
        User user = new User();
        user.setId(18L);
        user.setUsername("李白");
        user.setFlag(true);

        String filePath = "./java-io-start/src/main/resources/file/byte/ObjectOutputStream";
        File file = new File(filePath);

        ObjectOutputStream oos = null;
        try {
            FileOutputStream fos = new FileOutputStream(file);
            // ObjectOutputStream 只有一个 public ObjectOutputStream(OutputStream out) 构造方法
            // 需要传一个输出 OutputStream out 流
            oos = new ObjectOutputStream(fos);

            // 将对象写入到输出流中
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != oos) {
                try {
                    // 包装流底层会自动调用被包装流的 close 方法，这里只需要关闭包装流即可
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
