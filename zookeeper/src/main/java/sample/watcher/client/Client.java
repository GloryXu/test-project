package sample.watcher.client;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * @author glory
 * @description zk client端
 * @date Created at 下午11:36 18-12-17
 */
public class Client implements Watcher, AsyncCallback.StatCallback {

    ZooKeeper zk;
    boolean dead;
    String znode;
    Stat stat;
    int index;

    public void initZkClient(String ipport, int timetout, String znode) {
        try {
            this.znode = znode;
            zk = new ZooKeeper(ipport, timetout, this);
            stat = zk.exists(znode, false);
            if (null == stat) {
                zk.create(znode, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE,
                        CreateMode.PERSISTENT);
            }
            zk.getData(znode, this, stat);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void processResult(int rc, String s, Object o, Stat stat) {
        boolean exists;
        switch (rc) {
            case KeeperException.Code.Ok:
                exists = true;
                break;
            case KeeperException.Code.NoNode:
                exists = false;
                break;
            case KeeperException.Code.SessionExpired:
            case KeeperException.Code.NoAuth:
                dead = true;
                return;
            default:
                zk.exists(znode, true, this, null);
                return;
        }

        byte b[] = null;
        if (exists) {
            try {
                b = zk.getData(znode, false, null);
                System.out.println(new String(b));
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public void process(WatchedEvent event) {
        String path = event.getPath();
        if (event.getType() == Event.EventType.None) {
            switch (event.getState()) {
                case SyncConnected:
                    break;
                case Expired:
                    dead = true;
                    break;
            }
        } else {
            if (path != null && path.equals(znode)) {
                zk.exists(znode, true, this, null);
            }
        }
    }

    public void dataMaker(int i) {
        index = i;
        int max = index + 10;
        boolean run = true;
        while (run) {
            try {
                zk.setData(znode, ((index++) + "").getBytes(), -1);
                Thread.sleep(50);
                if (index > max) {
                    run = false;
                    zk.close();
                }
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

