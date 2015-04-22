package com.xuyuanchao.programstudy;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ZooKeeperTest {

	ZooKeeper zookeeper = null;
	final String host = "10.229.131.249:2181";

	Watcher watcher = new Watcher() {
		public void process(WatchedEvent event) {
			System.out.println("Start");
		}
	};

	public void connect() throws IOException {
		//Localhost:2181
		//int session time out
		//watcher
		this.zookeeper = new ZooKeeper(host,30000,watcher);
	}
	
	public void close() throws InterruptedException {
		this.zookeeper.close();
	}
	
	public void create() throws IOException, KeeperException, InterruptedException {
		this.connect();
		String result = this.zookeeper.create("/testjava", "testjava_data".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.out.println(result);
		this.close();
	}
	
	public void getData() throws IOException, KeeperException, InterruptedException {
		this.connect();
		byte[] result = this.zookeeper.getData("/testjava",null,null);
		System.out.println(new String(result));
		this.close();
	}
	
	public void getChild() throws IOException, KeeperException, InterruptedException{
		this.connect();
		this.zookeeper.getChildren("/testjava", null);
	}
	
	public void delete() throws IOException, KeeperException, InterruptedException {
		this.connect();
		this.zookeeper.delete("/testjava", -1);
		this.close();
	}
	
	
	
	public static void main(String[] args) {
		ZooKeeperTest zkTest =  new ZooKeeperTest();
		try {
			zkTest.delete();
		} catch (IOException | KeeperException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
