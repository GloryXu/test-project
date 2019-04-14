package sample.subscribe;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import util.DateUtils;
import util.PrintUtils;

import java.io.IOException;

/**
 * @author xuguangrong
 * @description 订阅zk路径
 * @date Created at 15:52 2019/4/14
 */
public class ZkSubscribe {

    private static ZkClient zkClient;

    // zk config
    protected static String connectServer = "127.0.0.1:2181";
    protected static int sessionTimeout = 10000;
    protected static int connectionTimeout = 4000;// connect to zk server.

    protected static String path = "/glory";

    static {
        zkClient = new ZkClient(connectServer, sessionTimeout, connectionTimeout, new SerializableSerializer());
    }

    public static void main(String[] args) throws IOException {
        zkClient.deleteRecursive(ZkSubscribe.path);

        zkClient.subscribeChildChanges(path, (parentPath, currentChilds) -> {
            /*for (String currentChild : currentChilds) {
                System.out.println(DateUtils.now() + " parentPath = " + parentPath + ", currentChild = " + currentChild);
            }*/
            if (currentChilds.size() == 1) {
                System.out.println("业务逻辑处理慢了！！");
                Thread.sleep(20000);
            }
            System.out.println(PrintUtils.printArrayList(currentChilds));
            System.out.println();
        });

        zkClient.subscribeDataChanges(path, new IZkDataListener(){

            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                if ("hello 0".equals(data)) {
                    Thread.sleep(10000);
                }
                System.out.println(DateUtils.now() + " " + Thread.currentThread().getName() + ", dataPath : " + dataPath + ", data : " + data);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println(DateUtils.now() + " " + Thread.currentThread().getName() + ", dataPath : " + dataPath);
            }
        });

        // for hold on.
        System.out.println("Server is up...");
        System.in.read();
    }

}
