package top.simba1949.nio.reactor.multiThreadReactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author anthony
 * @date 2023/8/9
 */
public class MultiThreadReactor {
    // 选择器集合
    private final Selector[] selectors = new Selector[2];

    private final ServerSocketChannel serverSocketChannel;

    // 引入多个子反应器
    SubReactor[] subReactors = null;

    /**
     * 第一步，创建用于启动服务端的服务
     *
     * @param port
     * @throws IOException
     */
    public MultiThreadReactor(String hostname, int port) throws IOException {
        // 打开选择器
        selectors[0] = Selector.open(); // 用于监听新连接事件
        selectors[1] = Selector.open(); // 用于监听传输事件

        // 打开 ServerSocketChannel
        serverSocketChannel = ServerSocketChannel.open();
        // 设置成非阻塞
        serverSocketChannel.configureBlocking(false);
        // 绑定ip
        serverSocketChannel.socket().bind(new InetSocketAddress(hostname, port));

        // 将 ServerSocketChannel 注册到 Selector，并监听  SelectionKey.OP_ACCEPT
        // 第一个选择器，负责监控新连接
        SelectionKey selectionKey = serverSocketChannel.register(selectors[0], SelectionKey.OP_ACCEPT);
        // 将 SelectionKey.OP_ACCEPT 事件的选择键附加 Acceptor 负责处理
        selectionKey.attach(new Acceptor());

        // 第一个子反应器，负责第一个选择器的新连接事件分发（而不处理业务）
        SubReactor subReactor1 = new SubReactor(selectors[0]);
        // 第二个子反应器，负责第二个选择器的传输事件分发（而不处理业务）
        SubReactor subReactor2 = new SubReactor(selectors[1]);
        subReactors = new SubReactor[]{subReactor1, subReactor2};

        System.out.println("服务端启动成功");
        System.out.println("MultiThreadReactor MultiThreadReactor 当前线程信息：id=" + Thread.currentThread().getId());
    }

    public void startService() {
        System.out.println("MultiThreadReactor startService 当前线程信息：id=" + Thread.currentThread().getId());
        // 一个子反应器对应一个线程
        new Thread(subReactors[0]).start();
        new Thread(subReactors[1]).start();
    }

    /**
     * 子反应器，负责事件分发，但是不负责事件处理
     */
    class SubReactor implements Runnable {

        private Selector selector;

        public SubReactor(Selector selector) {
            this.selector = selector;
        }

        @Override
        public void run() {
            try {
                // 这里进行监听准备就绪的事件
                while (!Thread.interrupted()) {
                    System.out.println("MultiThreadReactor run 当前线程信息：id=" + Thread.currentThread().getId());

                    selector.select();

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
            System.out.println("MultiThreadReactor dispatch 当前线程信息：id=" + Thread.currentThread().getId());

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
                System.out.println("Acceptor run 当前线程信息：id=" + Thread.currentThread().getId());

                // 接收一个新的连接
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null) {
                    // 创建传输处理器，并且将传输通道注册到选择器2上
                    new MultiThreadReactorHandler(selectors[1], socketChannel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}