package zookeeper;

import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 1.订阅服务
 * 2.缓存服务
 * 3.服务宕机重新订阅
 */
public class Client implements Watcher{
    ZookeeperRegister register = new ZookeeperRegister();
    private ZooKeeper zooKeeper ;
    /**
     * 存储本地的服务
     */
    private Map<String,RegisterState> stateList = new ConcurrentHashMap<String, RegisterState>();

    /**
     * 订阅服务,客户端订阅注册中心的所有服务,把服务存储到本地
     * @param clientName
     */
    void subscriber(String clientName){
        try {
            zooKeeper = register.getZookeeper(this);
            //true 代表 使用zookeeper默认的监听,也就是构造的时候传入的监听,在这里是client对象.
            // getChildren 向ZK 发了请求,带有监听事件,
            List<String> list = zooKeeper.getChildren(ZookeeperRegister.ROOT,true);
            if (list.isEmpty()) throw  new RuntimeException("未发现服务");
            for (String node: list) {
                System.out.println(node);
                byte[]  data=zooKeeper.getData(ZookeeperRegister.ROOT+"/"+node,true,null);
                RegisterState state= JSON.parseObject(new String(data),RegisterState.class);
                //  可以增加负载均衡策略 TODO
                if(null!=state&&state.getStats().equals("init")){
                    //就是建立关系 服务端和客户端的一个关系
                    state.getClientNames().add(clientName);
                    state.setStats("run");
                    //建立服务于path关系
                    state.setNode(node);
                    zooKeeper.setData(ZookeeperRegister.ROOT+"/"+state.getNode(),JSON.toJSONString(state).getBytes(),-1);
                }else{
                    System.out.println("没有找到可用run服务");
                }

                //服务存储到本地 其实这里如果存储到 本地的话 最好是有个初始化方法, 获取注册中心所有的服务, 然后订阅,
                //之后 每次取服务的时候 ,通过负载均衡策略 决定使用哪个服务. 写在这里没啥用.
                stateList.put(state.getServerName(),state);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //发生断开或者宕机 客户端需要重新订阅
    public void process(WatchedEvent event) {
        Event.EventType eventType = event.getType();
        if(eventType== Event.EventType.NodeChildrenChanged){
            stateList.clear();
            subscriber(clientName);
        }
    }

    public static void main(String[] args) {
        Client orderClient=new Client();
        orderClient.subscriber("交易系统01");
        orderClient.setClientName("交易系统01");
        System.out.println(orderClient.stateList);
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String clientName ;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }


    public Map<String, RegisterState> getStateList() {
        return stateList;
    }

    /**
     * 对外获取服务地址
     * @param serverName
     * @return
     */
    public  String getStateByServerName(String serverName) {
        return stateList.get(serverName).getIp()+":"+stateList.get(serverName).getPort();
    }
}
