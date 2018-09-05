package org.santanubrains.hystrix.dataAdapter;

import java.util.List;

import org.santanubrains.hystrix.dao.StudentDao;
import org.santanubrains.hystrix.domain.Student;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

public class StudentAdapterImpl implements StudentAdapter {

	@Override
	public Observable<List<Student>> getStudentDetailsForSchool(String schoolName) {
		return Observable.create(new OnSubscribe<List<Student>>() {

			@Override
			public void call(Subscriber<? super List<Student>> observer) {
				try {
					if (!observer.isUnsubscribed()) {
						observer.onNext(StudentDao.getStudentDetailForSchool(schoolName));
						observer.onCompleted();
					}
				} catch (Exception e) {
					observer.onError(e);
				}

			}
		});
	}

}
