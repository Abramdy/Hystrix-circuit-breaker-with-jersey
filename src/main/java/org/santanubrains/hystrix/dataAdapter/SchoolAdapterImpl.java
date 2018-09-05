package org.santanubrains.hystrix.dataAdapter;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.santanubrains.hystrix.command.CommandSchoolObservable;
import org.santanubrains.hystrix.utility.JerseyAsyncClient;

import com.google.gson.Gson;

import rx.Observable;

public class SchoolAdapterImpl implements SchoolAdapter {

	private final JerseyAsyncClient jerseyAsyncClient;
	private final Gson gson;

	@Inject
	public SchoolAdapterImpl(JerseyAsyncClient jerseyAsyncClient, Gson gson) {
		super();
		this.jerseyAsyncClient = jerseyAsyncClient;
		this.gson = gson;
	}

	@Override
	public Observable<Response> getSchoolDetailBySchoolName(String schoolName, Long portNo) {

		return new CommandSchoolObservable(jerseyAsyncClient, gson, schoolName, portNo).observe();
	}

}
