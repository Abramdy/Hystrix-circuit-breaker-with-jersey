package org.santanubrains.hystrix.dataAdapter;

import javax.ws.rs.core.Response;

import rx.Observable;

public interface SchoolAdapter {

	Observable<Response> getSchoolDetailBySchoolName(String schoolName, Long portNo);
}
