package org.santanubrains.hystrix.service;

import java.util.List;

import org.santanubrains.hystrix.domain.Student;

import rx.Observable;

public interface StudentService {

	Observable<List<Student>> getStudentDetailsForSchool(String schoolName);
}
