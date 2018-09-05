package org.santanubrains.hystrix.command;

import java.util.Date;

import javax.ws.rs.core.Response;

import org.santanubrains.hystrix.exception.ExceptionConfig;
import org.santanubrains.hystrix.response.ErrorResponse;
import org.santanubrains.hystrix.utility.JerseyAsyncClient;

import com.google.gson.Gson;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.functions.Action1;
import rx.Subscriber;

public class CommandSchoolObservable extends HystrixObservableCommand<Response> {

	private JerseyAsyncClient jerseyAsyncClient;
	private Gson gson;
	private final String schoolName;
	private final Long portNo;

	public CommandSchoolObservable(JerseyAsyncClient jerseyAsyncClient, Gson gson, String schoolName, Long portNo) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(CommandSchoolObservable.class.getName())));
		this.jerseyAsyncClient = jerseyAsyncClient;
		this.gson = gson;
		this.schoolName = schoolName;
		this.portNo = portNo;
	}

	@Override
	protected Observable<Response> construct() {

		return Observable.create(new OnSubscribe<Response>() {

			@Override
			public void call(Subscriber<? super Response> observer) {
				try {
					if (!observer.isUnsubscribed()) {
						jerseyAsyncClient.clientApi(schoolName, portNo).subscribe(new Action1<Response>() {

							@Override
							public void call(Response response) {
								observer.onNext(response);
								observer.onCompleted();
							}
						});
					}

				} catch (Exception e) {
					observer.onError(e);
				}

			}
		});
	}

	@Override
	protected Observable<Response> resumeWithFallback() {

		return Observable.create(new OnSubscribe<Response>() {

			@Override
			public void call(Subscriber<? super Response> observer) {
				try {
					if (!observer.isUnsubscribed()) {
						observer.onNext(Response.status(Response.Status.GATEWAY_TIMEOUT)
								.entity(gson.toJson(new ErrorResponse(ExceptionConfig.GATE_WAY_TIMEOUT,
										ExceptionConfig.ERROR_MESSAGE, new Date())))
								.build());
						observer.onCompleted();
					}
				} catch (Exception e) {
					observer.onError(e);
				}

			}
		});
	}

}
