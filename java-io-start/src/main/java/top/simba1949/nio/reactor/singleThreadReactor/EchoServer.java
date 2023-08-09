package top.simba1949.nio.reactor.singleThreadReactor;

import java.io.IOException;

/**
 * @author anthony
 * @date 2023/8/9
 */
public class EchoServer {
    public static void main(String[] args) throws IOException {
        System.out.println("当前线程信息：id=" + Thread.currentThread().getId() + "，name=" + Thread.currentThread().getName());
        new Reactor("127.0.0.1", 7777).run();
    }
}