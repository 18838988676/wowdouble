package com.wow;

import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zookeeper.RegisterState;
import zookeeper.ZookeeperRegister;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    ZookeeperRegister zookeeperRegister=new ZookeeperRegister();

    @RequestMapping("/list")
    public String list(HttpServletRequest request){
        ZooKeeper zooKeeper = zookeeperRegister.getZookeeper(new Watcher() {
            public void process(WatchedEvent event) {

            }
        });

        try {
            List<String>  data = zooKeeper.getChildren(ZookeeperRegister.ROOT,true);
            List<RegisterState> list = new ArrayList<RegisterState>();
            for(String server:data){
               byte[] bytes= zooKeeper.getData(zookeeperRegister.ROOT+"/"+server,false,null);
                RegisterState tlState=JSON.parseObject(new String(bytes),RegisterState.class);
                list.add(tlState);
            }
            request.setAttribute("servetList",list);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         return "admin";


    }

}
