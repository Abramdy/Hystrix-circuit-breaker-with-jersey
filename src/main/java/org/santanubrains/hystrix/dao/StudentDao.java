package org.santanubrains.hystrix.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.santanubrains.hystrix.domain.Student;

public class StudentDao {

	private static Map<String, List<Student>> schoolDB = null;
	private static List<Student> student = null;
	static {
		schoolDB = new HashMap<String, List<Student>>();

		student = new ArrayList<Student>();
		Student std = new Student("Santanu Saha", "MCA");
		student.add(std);
		std = new Student("Shikha Bhatia", "MCA");
		student.add(std);

		schoolDB.put("kiit", student);

		student = new ArrayList<Student>();
		std = new Student("Raja Vicharapu", "Btech CSE");
		student.add(std);
		std = new Student("Ahkashjit Nayak", "Btech EEE");
		student.add(std);

		schoolDB.put("cvraman", student);

	}

	public static List<Student> getStudentDetailForSchool(String schoolName) {
		List<Student> studentList = schoolDB.get(schoolName);
		if (studentList == null) {
			studentList = new ArrayList<Student>();
			Student std = new Student("Not Found", "N/A");
			studentList.add(std);
		}
		return studentList;

	}
}
