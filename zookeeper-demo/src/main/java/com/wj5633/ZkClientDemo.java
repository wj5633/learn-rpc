package com.wj5633;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/3/6 0:32
 * @description
 */

public class ZkClientDemo {

    public static void main(String[] args) throws InterruptedException {
        String zkServers = "127.0.0.1:2181";
        int connectionTimeout = 3000;

        ZkClient zkClient = new ZkClient(zkServers, connectionTimeout);
        String path = "/zk-data";

        if (zkClient.exists(path)) {
            zkClient.delete(path);
        }

        zkClient.createPersistent(path);
        zkClient.writeData(path, "test_data-1");

        final String data = zkClient.readData(path);
        System.out.println(data);

        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("handleDataChange, dataPath: " + dataPath + " data: " + data);
            }

            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("handleDataDeleted, dataPath: " + dataPath);
            }
        });

        zkClient.writeData(path, "test_data_2");

        zkClient.delete(path);

        Thread.sleep(Integer.MAX_VALUE);
    }
}
