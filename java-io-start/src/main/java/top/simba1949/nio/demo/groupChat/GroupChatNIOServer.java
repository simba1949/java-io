package top.simba1949.nio.demo.groupChat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author anthony
 * @version 2023/7/30 11:54
 */
public class GroupChatNIOServer {
	// 定义属性
	private Selector selector;
	// 面向流的监听套接字的通道
	private ServerSocketChannel serverSocketChannel;
	// 定义端口
	private static final int PORT = 7777;

	/**
	 * 1.启动服务器，监听对应的端口
	 * 2.服务器接受客户端消息，并实现转发（包括客户端上线的处理，客户端离线的处理）
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// 启动代码
		GroupChatNIOServer server = new GroupChatNIOServer();
		// 监听
		server.listen();
	}

	/**
	 * 在构造方法中初始化
	 */
	public GroupChatNIOServer() {
		try {
			// 打开选择器
			selector = Selector.open();
			// 打开 serverSocketChannel
			serverSocketChannel = ServerSocketChannel.open();
			// 绑定端口
			serverSocketChannel.bind(new InetSocketAddress(PORT));
			// 设置为非阻塞
			serverSocketChannel.configureBlocking(false);
			// 将 ServerSocketChannel 注册到选择器（Selector）中，并监听 accept 事件
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 监听
	 */
	public void listen() {
		try {
			while (true) {
				int count = selector.selectNow();
				// 大于0，则说明有事件需要处理
				if (count > 0) {
					Set<SelectionKey> selectionKeys = selector.selectedKeys();
					Iterator<SelectionKey> iterator = selectionKeys.iterator();
					while (iterator.hasNext()) {
						// 获取对应的 selectionKey
						SelectionKey selectionKey = iterator.next();

						// 监听 accept
						if (selectionKey.isAcceptable()) {
							// serverSocketChannel 监听到事件，接收连接事件，并获取创建对应的通道 channel
							SocketChannel socketChannel = serverSocketChannel.accept();
							socketChannel.configureBlocking(false);
							// 将接收连接创建的通道注册到选择器中
							socketChannel.register(selector, SelectionKey.OP_READ);
							// 接收连接时，可以处理一些业务
							System.out.println(socketChannel.getRemoteAddress() + "上线");
						}

						// 监听
						if (selectionKey.isReadable()) {
							read(selectionKey);
						}

						// 手动从集合中移除当前的 SelectionKey，防止重复操作
						iterator.remove();
					}
				}
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 读取客户端消息
	 */
	private void read(SelectionKey selectionKey) {
		SocketChannel socketChannel = null;
		try {
			if (selectionKey.isReadable()) {
				// 通过 SelectionKey 反向获取对应的 channel
				socketChannel = (SocketChannel) selectionKey.channel();
				// 创建一个缓冲区
				ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
				// 将 channel 数据读取到缓冲区中，并返回读取到的字节数
				int readByteNum = socketChannel.read(byteBuffer);
				if (readByteNum > 0) {
					// 模式反转
					byteBuffer.flip();

					// 将缓冲区的数据转换成字符串
					byte[] bytes = new byte[byteBuffer.limit()];
					byteBuffer.get(bytes);
					String msg = new String(bytes, StandardCharsets.UTF_8);
					// 输出消息
					System.out.println("从客户端读取到的消息是：" + msg);
					// 向其他客户端转发此消息
					sendInfoToOtherClients(msg, socketChannel);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();

			try {
				System.out.println(socketChannel.getRemoteAddress() + "离线了");
				// 如果某个客户端离线了
				// 取消 SelectionKey 在选择器上的注册
				selectionKey.cancel();
				// 关闭通道
				socketChannel.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * 转发消息
	 */
	private void sendInfoToOtherClients(String msg, SocketChannel self) throws IOException {
		System.out.println("服务器转发消息中。。。");

		// 遍历所有注册到 selector 上的 socketChannel，并排除自己
		for (SelectionKey selectionKey : selector.keys()) {
			Channel targetChannel = selectionKey.channel();

			if (targetChannel instanceof SocketChannel && targetChannel != self) {
				// 转型
				SocketChannel socketChannel = (SocketChannel) targetChannel;
				// 创建缓冲区
				ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8));
				// 写入到对应的通道中
				socketChannel.write(byteBuffer);
			}
		}
	}
}
