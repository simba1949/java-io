package top.simba1949.nio.reactor.multiThreadReactor;

import java.io.IOException;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 第四步，创建业务处理器，用于处理读写事件
 * handler 负责处理业务逻辑
 *
 * @author anthony
 * @date 2023/8/9
 */
public class MultiThreadReactorHandler implements Runnable {
    private final SocketChannel channel;
    private final SelectionKey selectionKey;

    private final ByteBuffer inputBuffer = ByteBuffer.allocate(8 * 1024);
    private final ByteBuffer outputBuffer = ByteBuffer.allocate(8 * 1024);

    // 处理器实例状态：发送和接收，一个连接对应一个处理器实例
    private static final int RECEIVING = 0;
    private static final int SENDING = 1;
    private int state = RECEIVING;

    // 引入线程池
    static ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    MultiThreadReactorHandler(Selector selector, SocketChannel c) throws IOException {
        System.out.println("MultiThreadReactorHandler MultiThreadReactorHandler 当前线程信息：id=" + Thread.currentThread().getId());
        channel = c;
        channel.configureBlocking(false);
        channel.setOption(StandardSocketOptions.TCP_NODELAY, true);

        // 与之前的注册方式不同，先仅仅取得选择键，之后再单独设置感兴趣的IO事件
        selectionKey = channel.register(selector, 0);
        // 将 Handler 自身作为选择键的附件，一个连接对应一个处理器示例
        selectionKey.attach(this);
        // 注册读就绪事件
        selectionKey.interestOps(SelectionKey.OP_READ);
        // 使尚未返回的第一个选择操作立即返回。
        selector.wakeup();
    }

    @Override
    public void run() {
        System.out.println("MultiThreadReactorHandler run 当前线程信息：id=" + Thread.currentThread().getId());
        // 提交数据传输任务到线程池
        // 使得IO处理不在IO事件轮询线程中执行，而是在独立的线程池中执行
        pool.submit(this::asyncRun);
    }

    /**
     * 数据传输与业务处理任务，不在IO事件轮询线程中执行，而是在独立的线程池中执行
     */
    private synchronized void asyncRun() {
        System.out.println("MultiThreadReactorHandler asyncRun 当前线程信息：id=" + Thread.currentThread().getId());
        try {
            if (state == SENDING) {
                // 发送状态，把数据写入连接通道
                channel.write(outputBuffer);
                // 清空数据，并切换成写模式
                outputBuffer.clear();
                // 注册 read 就绪事件，开始接收客户端数据
                selectionKey.interestOps(SelectionKey.OP_READ);
                // 修改状态
                state = RECEIVING;
            } else if (state == RECEIVING) {
                // 接收专题，从通道读取数据
                int len = 0;
                while ((len = channel.read(inputBuffer)) > 0) {
                    System.out.print(new String(inputBuffer.array(), 0, len, StandardCharsets.UTF_8));
                    inputBuffer.clear();
                }
                // 准备写数据到通道中，注册 write 就绪事件
                selectionKey.interestOps(SelectionKey.OP_WRITE);
                state = SENDING;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}