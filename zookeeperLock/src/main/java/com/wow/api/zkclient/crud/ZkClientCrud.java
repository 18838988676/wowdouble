package com.wow.api.zkclient.crud;

import com.wow.ZookeeperUtil;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

public class ZkClientCrud {
    ZkClient zkClient;

    public static void main(String[] args) {
        ZkClient zkClient=new ZkClient(ZookeeperUtil.connectString,ZookeeperUtil.sessionTimeout);
        //可以递归创建节点 但是都是null空节点
        zkClient.createPersistent("/abc/ccc/ddd",true);
        zkClient.create("/1","shuju", CreateMode.EPHEMERAL_SEQUENTIAL);
        zkClient.deleteRecursive("/abc/ccc/ddd");
    }



}
