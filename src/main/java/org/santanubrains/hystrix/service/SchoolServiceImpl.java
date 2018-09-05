package org.santanubrains.hystrix.service;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.santanubrains.hystrix.dataAdapter.SchoolAdapter;

import rx.Observable;

public class SchoolServiceImpl implements SchoolService {

	private SchoolAdapter schoolAdapter;

	@Inject
	public SchoolServiceImpl(SchoolAdapter schoolAdapter) {
		super();
		this.schoolAdapter = schoolAdapter;
	}

	@Override
	public Observable<Response> getSchoolDetailBySchoolName(String schoolName, Long portNumber) {

		return schoolAdapter.getSchoolDetailBySchoolName(schoolName, portNumber);
	}

}
