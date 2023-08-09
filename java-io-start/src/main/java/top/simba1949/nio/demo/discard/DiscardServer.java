package top.simba1949.nio.demo.discard;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * @author anthony
 * @date 2023/8/9
 */
public class DiscardServer {
    public static void main(String[] args) {
        ServerSocketChannel serverSocketChannel = null;
        try {
            // 打开选择器
            Selector selector = Selector.open();

            // 打开 ServerSocketChannel
            serverSocketChannel = ServerSocketChannel.open();
            // 配置成非阻塞
            serverSocketChannel.configureBlocking(false);
            // 绑定 IP 和 PORT
            serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 7777));
            // 注册到 Selector
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("DiscardServer 启动成功");

            // 轮询准备就绪的IO事件
            while (selector.select() > 0) {
                // 获取已经准备就绪事件对应的选择键
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    // 获取选择件
                    SelectionKey selectionKey = iterator.next();

                    if (selectionKey.isAcceptable()) {
                        // 接收已经连接准备就绪的IO事件的通道
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        // 将连接的通道切换成非阻塞模式
                        socketChannel.configureBlocking(false);
                        // 将连接的通道注册到选择器上，监听可读事件
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (selectionKey.isReadable()) {
                        // 接收已经可读的IO事件的通道
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        // 分配缓冲区
                        ByteBuffer buffer = ByteBuffer.allocate(64);

                        while (socketChannel.read(buffer) > 0) {
                            // 反转读写
                            buffer.flip();
                            System.out.println(new String(buffer.array(), 0, buffer.limit(), StandardCharsets.UTF_8));
                            buffer.compact();
                        }
                    }

                    // 移除已经处理过的选择键，防止重复
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != serverSocketChannel) {
                try {
                    serverSocketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
