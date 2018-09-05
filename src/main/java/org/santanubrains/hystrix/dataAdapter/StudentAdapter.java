package org.santanubrains.hystrix.dataAdapter;

import java.util.List;

import org.santanubrains.hystrix.domain.Student;

import rx.Observable;

public interface StudentAdapter {
	
	Observable<List<Student>> getStudentDetailsForSchool(String schoolName);
}
