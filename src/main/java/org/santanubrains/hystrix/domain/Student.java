package org.santanubrains.hystrix.domain;

import java.io.Serializable;

public class Student implements Serializable {

	private static final long serialVersionUID = 5414381410633255880L;
	private String name;
	private String className;

	public Student() {
		super();
	}

	public Student(String name, String className) {
		super();
		this.name = name;
		this.className = className;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
