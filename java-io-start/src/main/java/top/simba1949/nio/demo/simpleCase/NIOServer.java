package top.simba1949.nio.demo.simpleCase;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author anthony
 * @version 2023/7/30 9:59
 */
public class NIOServer {
	public static void main(String[] args) throws IOException {
		// 创建 Selector 对象，实际上是 WindowsSelectorImpl
		Selector selector = Selector.open();

		// 创建 ServerSocketChannel 对象
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		// 绑定端口
		serverSocketChannel.socket().bind(new InetSocketAddress(7777));
		// 设置为非阻塞
		serverSocketChannel.configureBlocking(false);
		// 将 ServerSocketChannel 注册到 Selector 中
		// 第二个入参表示通过Selector监听Channel时对什么事件感兴趣。可以监听四种不同类型的事件：Connect/Accept/Read/Write
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

		// 循环等待客户端连接
		while (true) {
			// Selector 进行监听 select()/select(long timeout)/selectNow() 方法，返回有事件发生的通道的个数；
			if (selector.selectNow() == 0) {
				// System.out.println("服务器无连接");
				continue;
			}

			// 如果返回大于0，就获取相关的 SelectionKey 集合
			// 1.如果返回大于0，表示已经获取到关注的事件
			// 2.通过 SelectionKey 可以反向获取通道
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = selectionKeys.iterator();
			while (iterator.hasNext()) {
				// 获取 SelectionKey
				SelectionKey selectionKey = iterator.next();

				// 根据 key 对应的通道发生的事件做处理
				// 如果是 OP_ACCEPT，有新的客户端连接
				if (selectionKey.isAcceptable()) {
					// 该客户端生成一个 SocketChannel
					SocketChannel socketChannel = serverSocketChannel.accept();
					socketChannel.configureBlocking(false);
					// 第一个参数：将当前的 SocketChannel 注册到 Selector 中，
					// 第二个参数：关注事件 SelectionKey.OP_READ，
					// 第三个参数：关联一个 buffer
					socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
				}

				// 如果是 OP_READ，事件可读
				if (selectionKey.isReadable()) {
					SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
					socketChannel.configureBlocking(false);
					// 获取到该 SocketChannel 的 buffer
					ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
					// 将 channel 数据读取到 buffer 中
					socketChannel.read(byteBuffer);
					// 转换模式
					byteBuffer.flip();
					// 从 buffer 中获取数据
					while (byteBuffer.hasRemaining()) {
						System.out.print("" + (char) byteBuffer.get());
					}
				}

				// 手动从集合中移除当前的 SelectionKey，防止重复操作
				iterator.remove();
			}
		}
	}
}
