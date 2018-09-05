package org.santanubrains.hystrix.response;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.santanubrains.hystrix.domain.Student;

public class StudentResponse implements Serializable {

	private static final long serialVersionUID = 2223956907865134748L;
	private Date time;
	private List<Student> student;

	public StudentResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentResponse(Date time, List<Student> student) {
		super();
		this.time = time;
		this.student = student;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}

}
