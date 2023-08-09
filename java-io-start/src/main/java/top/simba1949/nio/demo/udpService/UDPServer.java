package top.simba1949.nio.demo.udpService;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * @author anthony
 * @date 2023/8/9
 */
public class UDPServer {
    public static void main(String[] args) throws IOException {
        DatagramChannel datagramChannel = null;
        try {
            // 打开 Selector
            Selector selector = Selector.open();

            // 打开 DatagramChannel
            datagramChannel = DatagramChannel.open();
            // 设置为非阻塞
            datagramChannel.configureBlocking(false);
            // 绑定监听地址
            datagramChannel.bind(new InetSocketAddress("127.0.0.1", 7777));

            // 将通道注册到选择器上，监听读操作
            datagramChannel.register(selector, SelectionKey.OP_READ);
            System.out.println("UDP服务启动成功");

            // 通过选择器查询IO事件
            while (selector.select() > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                // 为每一个通道分配缓存区
                ByteBuffer buffer = ByteBuffer.allocate(8 * 1024);
                // 循环遍历每一个连接的通道
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    // 是否有可读准备就绪
                    if (selectionKey.isReadable()) {
                        // 读取 DatagramChannel 数据到缓冲区 Buffer 中
                        // receive()方法会将接收到的数据包内容复制到指定的Buffer. 如果Buffer容不下收到的数据，多出的数据将被丢弃。
                        datagramChannel.receive(buffer);
                        // 反转读写
                        buffer.flip();
                        // 读取最大
                        System.out.println(new String(buffer.array(), 0, buffer.limit(), StandardCharsets.UTF_8));
                        buffer.clear();
                    }

                    // 手动从集合中移除当前的 SelectionKey，防止重复操作
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != datagramChannel) {
                datagramChannel.close();
            }
        }
    }
}
