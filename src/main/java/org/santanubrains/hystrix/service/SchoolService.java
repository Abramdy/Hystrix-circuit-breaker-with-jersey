package org.santanubrains.hystrix.service;

import javax.ws.rs.core.Response;

import rx.Observable;

public interface SchoolService {

	Observable<Response> getSchoolDetailBySchoolName(String schoolName, Long portNumber);
}
