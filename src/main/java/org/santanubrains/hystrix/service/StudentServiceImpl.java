package org.santanubrains.hystrix.service;

import java.util.List;

import javax.inject.Inject;

import org.santanubrains.hystrix.dataAdapter.StudentAdapter;
import org.santanubrains.hystrix.domain.Student;

import rx.Observable;

public class StudentServiceImpl implements StudentService {

	private StudentAdapter studentAdapter;

	@Inject
	public StudentServiceImpl(StudentAdapter studentAdapter) {
		super();
		this.studentAdapter = studentAdapter;
	}

	@Override
	public Observable<List<Student>> getStudentDetailsForSchool(String schoolName) {
		return studentAdapter.getStudentDetailsForSchool(schoolName);
	}

}
