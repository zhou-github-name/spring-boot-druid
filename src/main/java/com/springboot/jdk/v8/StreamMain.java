package com.springboot.jdk.v8;

import java.util.Iterator;
import java.util.List;

import com.springboot.jdk.model.Student;

/**
 * Java 8系列之Stream的基本语法详解
 * 
 * @author E102535
 */
public class StreamMain {

	/**
	 * 1.创建Stream:通过stream()方法，取得集合对象的数据集。
	 * 2.Intermediate:通过一系列中间（Intermediate）方法，对数据集进行过滤、检索等数据集的再次处理。如上例中，使用filter
	 * ()方法来对数据集进行过滤。
	 * 3.Terminal通过最终（terminal）方法完成对数据集中元素的处理。如上例中，使用forEach()完成对过滤后元素的打印。
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		List<Student> studentList = Student.getStudentList();
		// 1、foreach
		for (Student student : studentList) {
			if (student.getSex().equals("G")) {
				System.out.println(student.toString());
			}
		}
		// 2、迭代器
		Iterator<Student> iterator = studentList.iterator();
		while (iterator.hasNext()) {
			Student stu = iterator.next();
			if (stu.getSex().equals("G")) {
				System.out.println(stu.toString());
			}
		}
		// 3、stream
		studentList.stream()
			.filter(student -> student.getSex().equals("G"))
			.forEach(student -> System.out.println(student.toString()));

		// 刚才提到的Stream的操作有Intermediate、Terminal和Short-circuiting：
		// Intermediate：map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、peek、 skip、 parallel、 sequential、 unordered

		// Terminal：forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、count、iterator

		// Short-circuiting：anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、limit

	}

}
