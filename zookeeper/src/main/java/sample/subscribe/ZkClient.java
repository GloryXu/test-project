package sample.subscribe;

import org.I0Itec.zkclient.serialize.SerializableSerializer;
import util.DateUtils;
import util.PrintUtils;

import java.io.IOException;
import java.util.Random;

/**
 * @author xuguangrong
 * @description zk client
 * @date Created at 16:12 2019/4/14
 */
public class ZkClient {

    private static org.I0Itec.zkclient.ZkClient zkClient;

    static {
        zkClient = new org.I0Itec.zkclient.ZkClient(ZkSubscribe.connectServer,
                ZkSubscribe.sessionTimeout, ZkSubscribe.connectionTimeout, new SerializableSerializer());
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        zkClient.deleteRecursive(ZkSubscribe.path);

        for (int i = 0;i < 5;i++) {
            // write data at the same path.
            /*String data;
            if (!zkClient.exists(ZkSubscribe.path)) {
                data = "hello 0";
                zkClient.create(ZkSubscribe.path, data, CreateMode.EPHEMERAL);
            } else {
                data = "hello " + new Random().nextInt(100);
                zkClient.writeData(ZkSubscribe.path, data);
            }
            System.out.println(DateUtils.now() + " Client writeData = " + data);*/

            // create child
            String childPath;
            if (!zkClient.exists(ZkSubscribe.path)) {
                childPath = "/child0";
                zkClient.createPersistent(ZkSubscribe.path + childPath, true);
            } else {
                childPath = "/child" + new Random().nextInt(100);
                zkClient.createPersistent(ZkSubscribe.path + childPath, true);
            }
            System.out.println(DateUtils.now() + " Child path = " + childPath);
            Thread.sleep(1000);
            System.out.println(PrintUtils.printArrayList(zkClient.getChildren(ZkSubscribe.path)));
        }

        // for hold on.
        System.in.read();
    }
}
