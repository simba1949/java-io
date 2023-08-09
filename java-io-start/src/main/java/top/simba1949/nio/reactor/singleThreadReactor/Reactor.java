package top.simba1949.nio.reactor.singleThreadReactor;

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
public class Reactor implements Runnable {

    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;

    /**
     * 第一步，创建用于启动服务端的服务
     *
     * @param port
     * @throws IOException
     */
    public Reactor(String hostname, int port) throws IOException {
        // 打开选择器
        selector = Selector.open();
        // 打开 ServerSocketChannel
        serverSocketChannel = ServerSocketChannel.open();
        // 设置成非阻塞
        serverSocketChannel.configureBlocking(false);
        // 绑定ip
        serverSocketChannel.socket().bind(new InetSocketAddress(hostname, port));
        // 将 ServerSocketChannel 注册到 Selector，并监听  SelectionKey.OP_ACCEPT
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 将 SelectionKey.OP_ACCEPT 事件的选择键附加 Acceptor 负责处理
        selectionKey.attach(new Acceptor());

        System.out.println("服务端启动成功");
        System.out.println("当前线程信息：id=" + Thread.currentThread().getId() + "，name=" + Thread.currentThread().getName());
    }

    /**
     * 第二步，实现Runnable接口，在 run 方法中，进行分发
     */
    @Override
    public void run() {
        try {
            // 这里进行监听准备就绪的事件
            while (!Thread.interrupted()) {
                System.out.println("当前线程信息：id=" + Thread.currentThread().getId() + "，name=" + Thread.currentThread().getName());

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
        System.out.println("当前线程信息：id=" + Thread.currentThread().getId() + "，name=" + Thread.currentThread().getName());

        Runnable handler = (Runnable) (selectionKey.attachment());
        // 调用之前绑定到选择键的 handler 对象
        if (handler != null) {
            handler.run();
        }
    }

    /**
     * 第三步，创建连接处理器，用来处理连接事件
     * Acceptor 负责连接处理
     */
    class Acceptor implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("当前线程信息：id=" + Thread.currentThread().getId() + "，name=" + Thread.currentThread().getName());

                // 接收一个新的连接
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null) {
                    // 新的连接进来之后，创建一个 handler 进行处理读就绪事件
                    new Handler(selector, socketChannel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}