package top.simba1949.nio.demo.discard;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author anthony
 * @date 2023/8/9
 */
public class DiscardClient {
    public static void main(String[] args) {
        SocketChannel socketChannel = null;

        try {
            // 打开选择器
            Selector selector = Selector.open();

            // 打开 SocketChannel
            socketChannel = SocketChannel.open();
            // 设置为非阻塞
            socketChannel.configureBlocking(false);
            // 注册到选择器中
            socketChannel.register(selector, SelectionKey.OP_READ);
            // 连接远程服务
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 7777));

            while (!socketChannel.finishConnect()) {
                // 自旋等待连接完成
            }

            System.out.println("客户端连接服务端成功");

            String msg = "01,01,001";
            byte[] content = msg.getBytes(StandardCharsets.UTF_8);
            ByteBuffer buffer = ByteBuffer.allocate(content.length);
            buffer.put(content);
            // 读写反转
            buffer.flip();
            // 将缓存去的数据写到通道中
            socketChannel.write(buffer);
            socketChannel.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != socketChannel) {
                try {
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
