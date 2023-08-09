package top.simba1949.nio.channel.socketChannel.groupChat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author anthony
 * @version 2023/7/30 11:54
 */
public class GroupChatNIOClient {
	// 服务端相关信息
	private static final String SERVER_HOST = "127.0.0.1";
	private static final int SERVER_PORT = 7777;
	// 客户端相关信息
	private String clientName;
	private Selector selector;
	// 面向流的监听套接字的通道
	private SocketChannel socketChannel;

	/**
	 * 1.连接服务器
	 * 2.发送消息
	 * 3.接受服务器消息
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// 创建客户端
		GroupChatNIOClient client = new GroupChatNIOClient();

		// 读取数据
		// 启动一个线程
		new Thread(() -> {
			while (true) {
				client.readInfo();
				try {
					// 每隔3秒读取一次
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();


		// 发送数据
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String msg = scanner.nextLine();
			client.sendInfo(msg);
		}
	}

	/**
	 * 在构造器中完成初始化
	 */
	public GroupChatNIOClient() {
		try {
			// 打开选择器
			selector = Selector.open();
			// 连接远程服务器
			socketChannel = SocketChannel.open(new InetSocketAddress(SERVER_HOST, SERVER_PORT));
			// 设置为非阻塞
			socketChannel.configureBlocking(false);
			socketChannel.register(selector, SelectionKey.OP_READ);
			// 设置客户端名称
			clientName = socketChannel.getLocalAddress().toString().substring(1);
			System.out.println(clientName + " is ok ...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 向服务器发送消息
	 *
	 * @param info
	 */
	public void sendInfo(String info) {
		info = clientName + " 说：" + info;

		try {
			// 创建缓冲区
			ByteBuffer byteBuffer = ByteBuffer.wrap(info.getBytes(StandardCharsets.UTF_8));
			// 将 buffer 中的数据写入到 channel 中
			socketChannel.write(byteBuffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取消息
	 */
	public void readInfo() {
		try {
			// 获取已经准备的事件个数
			int readChannels = selector.select();
			if (readChannels > 0) {
				Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
				while (iterator.hasNext()) {

					SelectionKey selectionKey = iterator.next();
					if (selectionKey.isReadable()) {
						SelectableChannel selectableChannel = selectionKey.channel();
						if (selectableChannel instanceof SocketChannel) {
							SocketChannel socketChannel = (SocketChannel) selectableChannel;
							ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
							// 读取通道的数据到 buffer 中
							int write = socketChannel.read(byteBuffer);

							// 模式转换
							byteBuffer.flip();

							if (write > 0) {
								// 将缓冲区的数据转换成字符串
								byte[] bytes = new byte[byteBuffer.limit()];
								byteBuffer.get(bytes);
								String msg = new String(bytes, StandardCharsets.UTF_8);

								System.out.println("客户端读到了消息：" + msg);
							}
						}
					}

					iterator.remove();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
