package com.ugtworld.dto;

public class Student {
	
	private int rollNumber;
	private String name;
	private int inClass;
	private String gender;
	private int marks;
	
	public Student(int rollNumber, String name, int inClass, String gender, int marks) {
		super();
		this.rollNumber = rollNumber;
		this.name = name;
		this.inClass = inClass;
		this.gender = gender;
		this.marks = marks;
	}
	
	public int getRollNumber() {
		return rollNumber;
	}
	public String getName() {
		return name;
	}
	public int getInClass() {
		return inClass;
	}
	public String getGender() {
		return gender;
	}

	public int getMarks() {
		return marks;
	}
}
