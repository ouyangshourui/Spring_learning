package com.zwh.bigdata.webdemo.schedule;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Curator framework watch test.
 */
//@Component
public class CuratorWatcherTest {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);


    /** Zookeeper info */
    private static final String ZK_ADDRESS = "192.168.184.63:2181";
    private static final String ZK_PATH = "/ourui/leader/ephemeralNode";

    @Scheduled(fixedRate = 10000)
    public void CuratorWatcher() throws Exception {
        log.isWarnEnabled();
        CuratorFramework client = CuratorFrameworkFactory.
                newClient("192.168.184.63:2181", new ExponentialBackoffRetry(1000, 3));
        client.getConnectionStateListenable().addListener(new ConnectionStateListener()
        {
            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState)
            {
                System.out.println("连接状态:" + newState.name());
            }
        });
        client.start();
       // client.close();
    }

}

