package sample.watcher.client;

/**
 * @author glory
 * @description zk client 2
 * @date Created at 下午11:40 18-12-17
 */
public class ZkClient2 {

    public static void main(String[]args){
        Client client = new Client();
        client.initZkClient("localhost:22801",3000,"/zka");
        client.dataMaker(20);
    }

}
