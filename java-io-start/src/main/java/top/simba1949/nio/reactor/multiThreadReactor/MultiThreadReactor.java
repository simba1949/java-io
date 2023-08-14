package top.simba1949.nio.reactor.multiThreadReactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author anthony
 * @date 2023/8/9
 */
public class MultiThreadReactor {
	ServerSocketChannel serverSocketChannel;
	AtomicInteger next = new AtomicInteger(0);
	Selector bossSelector = null;
	Reactor bossReactor = null;

	// selectors集合,引入多个selector选择器
	Selector[] workSelectors = new Selector[2];
	//引入多个子反应器
	Reactor[] workReactors = null;

	/**
	 * 第一步，创建用于启动服务端的服务
	 *
	 * @param port
	 * @throws IOException
	 */
	public MultiThreadReactor(String hostname, int port) throws IOException {
		// 打开选择器
		bossSelector = Selector.open(); // 用于监听新连接事件
		workSelectors[0] = Selector.open(); // 用于监听 read/write 事件
		workSelectors[1] = Selector.open(); // 用于监听 read/write 事件

		// 打开 ServerSocketChannel
		serverSocketChannel = ServerSocketChannel.open();
		// 设置成非阻塞
		serverSocketChannel.configureBlocking(false);
		// 绑定ip
		serverSocketChannel.socket().bind(new InetSocketAddress(hostname, port));

		// 将 ServerSocketChannel 注册到 Selector，并监听  SelectionKey.OP_ACCEPT
		// 第一个选择器，负责监控新连接
		SelectionKey selectionKey = serverSocketChannel.register(bossSelector, SelectionKey.OP_ACCEPT);
		// 将 SelectionKey.OP_ACCEPT 事件的选择键附加 Acceptor 负责处理
		selectionKey.attach(new Acceptor());

		// bossReactor反应器，处理新连接的bossSelector
		bossReactor = new Reactor(bossSelector);

		// 第一个子反应器，子反应器负责一个worker选择器
		Reactor workReactor1 = new Reactor(workSelectors[0]);
		// 第二个子反应器，子反应器负责一个worker选择器
		Reactor workReactor2 = new Reactor(workSelectors[1]);
		workReactors = new Reactor[]{workReactor1, workReactor2};
	}

	public void startService() {
		// 一子反应器对应一条线程
		new Thread(bossReactor).start();
		new Thread(workReactors[0]).start();
		new Thread(workReactors[1]).start();
	}

	/**
	 * 子反应器，负责事件分发，但是不负责事件处理
	 */
	class Reactor implements Runnable {

		private final Selector selector;

		public Reactor(Selector selector) {
			this.selector = selector;
		}

		@Override
		public void run() {
			try {
				// 这里进行监听准备就绪的事件
				while (!Thread.interrupted()) {
					selector.select(1000);

					Set<SelectionKey> selectionKeys = selector.selectedKeys();
					Iterator<SelectionKey> iterator = selectionKeys.iterator();
					while (iterator.hasNext()) {
						dispatch(iterator.next());
					}

					selectionKeys.clear();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * 负责分发
		 *
		 * @param selectionKey
		 */
		void dispatch(SelectionKey selectionKey) {
			Runnable handler = (Runnable) (selectionKey.attachment());
			// 调用之前绑定到选择键的 handler 对象
			if (handler != null) {
				handler.run();
			}
		}
	}

	/**
	 * 创建连接处理器，用来处理连接事件
	 * Acceptor 负责连接处理
	 */
	class Acceptor implements Runnable {
		@Override
		public void run() {
			try {
				// 接收一个新的连接
				SocketChannel socketChannel = serverSocketChannel.accept();
				if (socketChannel != null) {
					int index = next.get();
					Selector selector = workSelectors[index];
					// 创建传输处理器，并且将传输通道注册到选择器2上
					new MultiThreadReactorHandler(selector, socketChannel);
				}

				if (next.incrementAndGet() == workSelectors.length) {
					next.set(0);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}