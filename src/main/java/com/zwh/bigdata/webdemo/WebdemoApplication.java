package com.zwh.bigdata.webdemo;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//@ComponentScan(basePackages = {"com.haiziwang.bigdata.webdemo.util"})
public class WebdemoApplication {

	@Bean
	public  CuratorFramework createCuratorFramework() {
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
		return client;
	}
	public static void main(String[] args) {
		System.setProperty("leader",args[0]);
		SpringApplication springApplication = new SpringApplication(WebdemoApplication.class);
		springApplication.addListeners(new ApplicationStartup());
		springApplication.run(args);
	}
}
