package com.springboot.test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {
	
	public static void main(String[] args) {
		// 无序不重复的
		Set<String> set1 = new HashSet<>();
		set1.add("S1");
		set1.add("S5");
		set1.add("S3");
		set1.add("S4");
		set1.add("S2");
		System.out.println("HashSet：");
		set1.forEach(e -> System.out.print(e + ", "));
		
		// 保证元素添加的顺序：
		Set<String> set2 = new LinkedHashSet<>();
		set2.add("S1");
		set2.add("S5");
		set2.add("S3");
		set2.add("S4");
		set2.add("S2");
		System.out.println("");
		System.out.println("LinkedHashSet：");
		set2.forEach(e -> System.out.print(e + ", "));
		
		// 保证元素自然的顺序：
		Set<String> set3 = new TreeSet<>();
		set3.add("S1");
		set3.add("S5");
		set3.add("S3");
		set3.add("S4");
		set3.add("S2");
		System.out.println("");
		System.out.println("TreeSet：");
		set3.forEach(e -> System.out.print(e + ", "));
	}

}
