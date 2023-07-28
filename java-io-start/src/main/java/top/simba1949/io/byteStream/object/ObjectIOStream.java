package top.simba1949.io.byteStream.object;

import java.io.*;

/**
 * @author anthony
 * @date 2023/7/28
 */
public class ObjectIOStream {
    public static void main(String[] args) {
        encodeAndDecode();
    }

    public static void encodeAndDecode() {
        // 1.创建源
        User user = new User();
        user.setId(18L);
        user.setUsername("李白");
        user.setFlag(true);

        // 2.选择流
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);

            // 3.操作流
            oos.writeObject(user);
            byte[] bytes = baos.toByteArray();

            ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
            User decodeUser = (User) ois.readObject();
            System.out.println(decodeUser);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (null != oos) {
                // 4.关闭流
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != ois) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
