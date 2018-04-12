package zookeeper;

import java.util.HashSet;
import java.util.Set;

/**
 * 节点数据结构
 */
public class RegisterState {
    private String port;//记录服务端口
    private String ip;//记录服务的IP
    private String serverName ; //记录服务端名
    private Set<String> clientNames = new HashSet<String>(); //记录哪些客户端订阅了服务
    private String stats ;// 记录当前链接状态
    private int num = 0; //记录多少个链接

    private String node ;//节点名称

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Set<String> getClientNames() {
        return clientNames;
    }

    public void setClientNames(Set<String> clientNames) {
        this.clientNames = clientNames;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }
}
