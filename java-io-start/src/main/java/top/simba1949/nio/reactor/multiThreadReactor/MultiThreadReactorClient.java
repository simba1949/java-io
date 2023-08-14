package top.simba1949.nio.reactor.multiThreadReactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author anthony
 * @date 2023/8/9
 */
public class MultiThreadReactorClient {
    public static void main(String[] args) {
        SocketChannel socketChannel = null;
        try {
            // 打开 Selector
            Selector selector = Selector.open();

            // 打开 SocketChannel
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 7777));

            socketChannel.register(selector, SelectionKey.OP_READ);

            while (!socketChannel.finishConnect()) {
                // 自旋等待连接成功
            }

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String msg = scanner.next();
                byte[] content = msg.getBytes(StandardCharsets.UTF_8);
                ByteBuffer buffer = ByteBuffer.allocate(content.length);
                buffer.put(content);
                // 读写反转
                buffer.flip();
                // 将缓存去的数据写到通道中
                socketChannel.write(buffer);
            }
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