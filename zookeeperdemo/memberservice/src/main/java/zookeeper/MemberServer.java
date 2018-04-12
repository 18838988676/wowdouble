package zookeeper;

import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 会员服务注册,就是创建临时节点,服务宕机则节点消失.
 * 订阅就是 查找节点,然后订阅.
 */
public class MemberServer implements Watcher {
    ZookeeperRegister register = new ZookeeperRegister();
    private ZooKeeper zooKeeper ;

    void register(RegisterState state)  {
       zooKeeper = register.getZookeeper(this);
        try {
            //注册节点
            byte[] data = JSON.toJSONString(state).getBytes("UTF-8");
            zooKeeper.create(ZookeeperRegister.ROOT+"/"+state.getServerName(),data, ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    //这里在 服务端 与ZK断开时触发
    public void process(WatchedEvent event) {

    }

    public static void main(String[] args) throws IOException {
        RegisterState state = new RegisterState();
        state.setIp("192.168.1.1");
        state.setPort("80");
        state.setStats("init");
        state.setServerName("MemberServer");
        MemberServer memberServer = new MemberServer();
        memberServer.register(state);
        //
        System.in.read();
    }
}
