package top.simba1949.nio.reactor.multiThreadReactor;

import java.io.IOException;

/**
 * @author anthony
 * @date 2023/8/9
 */
public class MultiThreadReactorServer {
    public static void main(String[] args) throws IOException {
        new MultiThreadReactor("127.0.0.1", 7777).startService();
    }
}