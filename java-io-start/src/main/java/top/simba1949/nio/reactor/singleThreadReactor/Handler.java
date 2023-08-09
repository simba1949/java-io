package top.simba1949.nio.reactor.singleThreadReactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * 第四步，创建业务处理器，用于处理读写事件
 * handler 负责处理业务逻辑
 *
 * @author anthony
 * @date 2023/8/9
 */
public class Handler implements Runnable {
    final SocketChannel channel;
    final SelectionKey selectionKey;

    ByteBuffer inputBuffer = ByteBuffer.allocate(8 * 1024);
    ByteBuffer outputBuffer = ByteBuffer.allocate(8 * 1024);

    // 处理器实例状态：发送和接收，一个连接对应一个处理器实例
    static final int RECEIVING = 0;
    static final int SENDING = 1;
    int state = RECEIVING;

    Handler(Selector selector, SocketChannel socketChannel) throws IOException {
        System.out.println("当前线程信息：id=" + Thread.currentThread().getId() + "，name=" + Thread.currentThread().getName());
        this.channel = socketChannel;
        socketChannel.configureBlocking(false);

        // 与之前的注册方式不同，先仅仅取得选择键，之后再单独设置感兴趣的IO事件
        selectionKey = this.channel.register(selector, 0);
        // 将 Handler 自身作为选择键的附件，一个连接对应一个处理器示例
        selectionKey.attach(this);
        // 注册读就绪事件
        selectionKey.interestOps(SelectionKey.OP_READ);
        // 使尚未返回的第一个选择操作立即返回。
        selector.wakeup();
    }

    @Override
    public void run() {
        try {
            System.out.println("当前线程信息：id=" + Thread.currentThread().getId() + "，name=" + Thread.currentThread().getName());

            if (state == SENDING) {
                // 发送状态，把数据写入连接通道
                channel.write(outputBuffer);
                // 清空数据，并切换成写模式
                outputBuffer.clear();
                // 注册 read 就绪事件，开始接收客户端数据
                selectionKey.interestOps(SelectionKey.OP_READ);
                // 修改状态
                state = RECEIVING;
                //
                selectionKey.cancel();
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

                // 处理结束了，这里不能关闭 SelectionKey 需要重复使用
                // selectionKey.cancel();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}