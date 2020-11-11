package com.springboot.jdk.v8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LambdaMain {

	public static void main(String[] args) {
		// 1、排序
		List<String> names = new ArrayList<String>();
		names.add("Google ");
		names.add("Runoob ");
		names.add("Taobao ");
		names.add("Baidu ");
		names.add("Sina ");
		Collections.sort(names, (s1, s2) -> s1.compareTo(s2));

		// 2、线程启动
		new Thread(new Runnable() {
		    @Override

		    public void run() {
		    System.out.println("Before Java8, too much code for too little to do");

		    }

		}).start();
		
		new Thread( () -> System.out.println("In Java8, Lambda expression rocks !!") ).start();
		
		
		
		
	}

}
