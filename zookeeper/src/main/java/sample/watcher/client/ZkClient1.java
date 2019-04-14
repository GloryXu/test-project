package sample.watcher.client;

/**
 * @author glory
 * @description client1客户端
 * @date Created at 下午11:39 18-12-17
 */
public class ZkClient1 {
    public static void main(String[]args){
        Client client = new Client();
        client.initZkClient("localhost:22801",3000,"/zka");
        client.dataMaker(0);
    }
}
