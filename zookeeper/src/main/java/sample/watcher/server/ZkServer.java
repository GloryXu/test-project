package sample.watcher.server;

import sample.watcher.Conf;

import java.io.IOException;

/**
 * @author glory
 * @description zkServer启动类
 * @date Created at 下午11:38 18-12-17
 */
public class ZkServer {
    public static void main(String[] args) {
        Conf conf = new Conf();
        conf.DIR = "/zookeeper";
        conf.PORT = 22801;
        conf.TICK_TIME = 2000;
        conf.MAX_CLIENT_CONNECTIONS = 60;
        Server srv = new Server();
        try {
            srv.zkStart(conf);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
