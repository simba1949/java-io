package top.simba1949.nio.reactor.singleThreadReactor;

import java.io.IOException;

/**
 * @author anthony
 * @date 2023/8/9
 */
public class EchoServer {
    public static void main(String[] args) throws IOException {
        new Reactor("127.0.0.1", 7777).run();
    }
}