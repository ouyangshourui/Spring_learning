package com.zwh.bigdata.webdemo.hello;

import com.zwh.bigdata.webdemo.util.ExampleSelectClient;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.DisposableBean;

import java.util.concurrent.TimeUnit;

public class zkleaderSelect implements DisposableBean, Runnable  {
    private Thread thread;
    private volatile boolean someCondition=true;
    private static final String PATH = "/ourui/leader";
    private Object object = new Object();
    @Override
    public void run() {
        while(true){
            if(someCondition) {
                synchronized (object) {
                    System.out.println("zkleaderSelect test*******************");
                    try {
                        CuratorFramework client = CuratorFrameworkFactory.
                                newClient("192.168.184.63:2181", new ExponentialBackoffRetry(1000, 3));
                        ExampleSelectClient example = new ExampleSelectClient(client);
                        client.start();
                        example.start();

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            Thread.sleep(TimeUnit.SECONDS.toMillis(2));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    someCondition = false;
                }
            }


        }

    }
    zkleaderSelect(){
        this.thread = new Thread(this);
        thread.start();
    }

    @Override
    public void destroy() throws Exception {


    }
}
