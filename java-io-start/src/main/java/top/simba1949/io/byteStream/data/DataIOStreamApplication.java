package top.simba1949.io.byteStream.data;

import java.io.*;

/**
 * @author anthony
 * @date 2023/7/28
 */
public class DataIOStreamApplication {

    /**
     * 数据流保留了数据类型，处理八大基本数据类型和String字符串类型，读取顺序和写出顺序一致
     *
     * @param args
     */
    public static void main(String[] args) {
        readAndWrite();
    }

    public static void readAndWrite() {
        // 1.创建源
        // 2.选择流
        DataOutputStream dos = null;
        DataInputStream dis = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);
            // 3.操作流
            dos.writeUTF("长城在");
            dos.writeBoolean(false);
            dos.writeChar(97);
            dos.writeDouble(1.00);
            byte[] bytes = baos.toByteArray();

            // 读取的顺序和写入的顺序一致
            dis = new DataInputStream(new ByteArrayInputStream(bytes));

            String s = dis.readUTF();
            boolean b = dis.readBoolean();
            char c = dis.readChar();
            double v = dis.readDouble();

            System.out.println("读到的 s  数据是 " + s);
            System.out.println("读到的 b  数据是 " + b);
            System.out.println("读到的 c  数据是 " + c);
            System.out.println("读到的 v  数据是 " + v);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4.关闭流
            if (null != dos) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
