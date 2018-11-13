package com.zwh.bigdata.webdemo.util;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ExampleSelectClient extends LeaderSelectorListenerAdapter implements Closeable {
    private final String name="spring_ExampleSelectClient";
    private final LeaderSelector leaderSelector;
    private final AtomicInteger leaderCount = new AtomicInteger();

    @Autowired
    public ExampleSelectClient(CuratorFramework client) {
        leaderSelector = new LeaderSelector(client, "/ourui/leader", this);
        leaderSelector.autoRequeue();
        leaderSelector.start();
    }

    public void start() throws IOException {
        leaderSelector.start();
    }


    @Override
    public void close() throws IOException {
        leaderSelector.close();
    }

    @Override
    public void takeLeadership(CuratorFramework client) throws Exception {
        final int waitSeconds = 60;
        System.out.println(name + " is now the leader. Waiting " + waitSeconds + " seconds...");
        System.out.println(name + " has been leader " + leaderCount.getAndIncrement() + " time(s) before.");
        try {
            System.out.println("**********************:"+"doing something");
            Thread.sleep(TimeUnit.SECONDS.toMillis(60));

        } catch (InterruptedException e) {
            System.err.println(name + " was interrupted.");
            Thread.currentThread().interrupt();
        } finally {
            close();
            System.out.println(name + " relinquishing leadership.\n");
        }
    }
}