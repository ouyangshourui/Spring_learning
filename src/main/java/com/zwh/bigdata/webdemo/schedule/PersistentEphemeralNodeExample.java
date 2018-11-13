package com.zwh.bigdata.webdemo.schedule;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.nodes.PersistentEphemeralNode;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

//@Component
 public class PersistentEphemeralNodeExample {
    private static final String PATH = "/ourui/leader/ephemeralNode";
    private static final String PATH2 = "/example/node";
    //@Scheduled(fixedRate = 5000)
    @Autowired
    @Bean
    public String reportPersistentEphemeralNode(CuratorFramework client)   {
        String isLeader= System.getProperty("leader");
        if(isLeader==null){
            isLeader="notleader";
        }

        System.out.println("System.getProperty(\"leader\") is :"+isLeader);
        String value=null;
        if(isLeader.equals("leader")) {
            try {
                InetAddress addr = InetAddress.getLocalHost();
                String ip = addr.getHostAddress().toString();
                PersistentEphemeralNode node = new PersistentEphemeralNode(client, PersistentEphemeralNode.Mode.EPHEMERAL, PATH, ip.getBytes());
                node.start();
                node.waitForInitialCreate(3, TimeUnit.SECONDS);
                value = new String(client.getData().forPath(PATH));
                System.out.println("临时节点路径：" + PATH + " | 值: " + value);
                //Thread.sleep(TimeUnit.SECONDS.toMillis(30));
                //node.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

 return value;
    }

}
