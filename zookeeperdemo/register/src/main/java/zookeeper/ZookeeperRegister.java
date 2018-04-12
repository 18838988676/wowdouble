package zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * zookeeper 注册中心
 * 本质是zookeeper提供服务,需要链接zookeeper
 * 不能写成单例 或者静态方法
 */
public class ZookeeperRegister {
    ZooKeeper zooKeeper ;
   //目前写死 实际中可以通过配置 //TODO
    String connectString = "192.168.16.129:2181,192.168.16.129:2182,192.168.16.129:2183";
    int sessionTimeout = 6000 ;

    public static final String ROOT = "/regist";


    /**
     * 与zookeeper 建立链接
     * 其中watcher 是指的 可以通知状态更改的监视对象，可以通知节点事件
     * @param watcher
     * @return
     */
    public  ZooKeeper getZookeeper(Watcher watcher){
        try {
            zooKeeper = new ZooKeeper(connectString,sessionTimeout,watcher);
            if(zooKeeper.exists(ZookeeperRegister.ROOT,true)==null){
                zooKeeper.create(ZookeeperRegister.ROOT,new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("ZK 链接失败");
        }
        return zooKeeper;
    }

    public static void main(String[] args) {
        ZookeeperRegister register = new ZookeeperRegister();
        ZooKeeper zooKeeper = register.getZookeeper(new Watcher() {
            public void process(WatchedEvent event) {

            }
        });
        System.out.println(zooKeeper);
    }
}
