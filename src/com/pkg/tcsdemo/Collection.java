package com.pkg.tcsdemo;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;



public class Collection {

	// Collection
	/*
	 * JVM (internal working.)Collection, threads, java.util.concurrent, Design
	 * Patterns (Singleton, Factory, Dependency Injection) , AOP, transaction
	 * Web Frameworks, Servlets, JSPs, JavaScript, jQuery, Angular, Node,
	 * Reactive, Ember REST Apis, JSON Databases: MySQL, NoSql (MongoDB,
	 * Cassandra)
	 */
	// Now we will learn about ConcurrentModificationException in Collections
	public static void main1(String[] args) {
		List<Integer> intList = new ArrayList<Integer>();
		Thread writer = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Writer thread "+ Thread.currentThread().getName()+" is running");
				for (int i = 0; i < 10; i++) {
					System.out.println("Written "+ i);
					intList.add(i);
				}
			}
		});

		Thread reader = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Reader thread "+ Thread.currentThread().getName()+" is running");
				for (Integer i:intList){
					System.out.println(i);
				}
			}
		});
		
		try{
			reader.start();
			writer.start();
			/*try {
				writer.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
		}catch(ConcurrentModificationException cem){
			System.out.println("Exceptiopn occured"+cem.getMessage());
		}
		

	}
	
	public static void main(String[] args) {
		DemoObject demo=new DemoObject();
		Runnable runnable=new Runnable() {
			
			@Override
			public synchronized void run() {
				demo.runInside();
			}
		};
		Thread t1=new Thread(runnable,"First Thread");
		Thread t2=new Thread(runnable,"Second Thread");
		Thread t3=new Thread(runnable,"Third Thread");
		t1.start();
		t2.start();
		t3.start();
	}
}

class DemoObject{
	
	public void runInside(){
		System.out.println(Thread.currentThread().getName() +" has started running");
		printNumbers(10);
	}
	
	public void printNumbers(int n){
		for(int i=0;i<n;i++){
			System.out.println(i + " was printed by "+Thread.currentThread().getName());
		}
	}
}