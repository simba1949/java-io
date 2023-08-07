package top.simba1949.io.byteStream.piped;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @author anthony
 * @date 2023/8/7
 */
public class PipedIOStreamApplication {
    public static void main(String[] args) {
        writeAndRead();
    }

    /**
     * PipedInputStream 类使得可以作为字节流读取管道的内容。
     * 管道是同一 JVM 内的线程之间的通信通道。
     * 使用两个已连接的管道流时，要为每个流操作创建一个线程，
     * read() 和 write() 都是阻塞方法，如果一个线程同时读写就会造成死锁
     */
    public static void writeAndRead() {
        try {
            PipedInputStream pis = new PipedInputStream();
            PipedOutputStream pos = new PipedOutputStream();
            // 输入管道连接输出管道
            pis.connect(pos);

            // 向 PipedOutputStream 进行写数据
            new Thread(() -> {
                try {
                    for (int i = 0; i < 100; i++) {
                        pos.write((UUID.randomUUID().toString() + "\n").getBytes(StandardCharsets.UTF_8));
                    }
                    pos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        pos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            // 通过 PipedInputStream 读取 PipedOutputStream 数据
            new Thread(() -> {
                try {
                    int readByte;
                    while ((readByte = pis.read()) != -1) {
                        System.out.print((char) readByte);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        pis.close();
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
