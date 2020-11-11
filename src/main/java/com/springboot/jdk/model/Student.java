package com.springboot.jdk.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Student {
	
	private int no;
	private String name;
	private String sex;
	private float height;

	public Student(int no, String name, String sex, float height) {
		this.no = no;
		this.name = name;
		this.sex = sex;
		this.height = height;
	}

	public Student() {
		super();
	}

	@Override
	public String toString() {
		return "Student [no=" + no + ", name=" + name + ", sex=" + sex + ", height=" + height + "]";
	}
	
	public static List<Student> getStudentList() {
		List<Student> students = new ArrayList<>();
		Student stuA = new Student(1, "A", "M", 184);
		Student stuB = new Student(2, "B", "G", 163);
		Student stuC = new Student(3, "C", "M", 175);
		Student stuD = new Student(4, "D", "G", 158);
		Student stuE = new Student(5, "E", "M", 170);
		students.add(stuA);
		students.add(stuB);
		students.add(stuC);
		students.add(stuD);
		students.add(stuE);
		return students;
	}
	
}
