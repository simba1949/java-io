package top.simba1949.nio.channel.datagramChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author anthony
 * @date 2023/8/9
 */
public class UDPClient {
    public static void main(String[] args) {
        DatagramChannel datagramChannel = null;
        try {
            // 打开 DatagramChannel
            datagramChannel = DatagramChannel.open();
            // 配置成非阻塞
            datagramChannel.configureBlocking(false);
            System.out.println("UDP客户端启动成功");

            // 分配一个缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(8 * 1024);
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                // 读取控制台数据
                String next = scanner.next();
                // 将时间和数据存放在缓冲区
                byte[] contentByte = (System.currentTimeMillis() + ">>" + next).getBytes(StandardCharsets.UTF_8);
                buffer.put(contentByte);
                // 反转读写
                buffer.flip();
                // 通过 DatagramChannel 将缓冲区数据发送出去
                datagramChannel.send(buffer, new InetSocketAddress("127.0.0.1", 7777));
                // 情况缓冲区
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != datagramChannel) {
                try {
                    datagramChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
