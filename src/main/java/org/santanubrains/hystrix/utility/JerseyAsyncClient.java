package org.santanubrains.hystrix.utility;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.santanubrains.hystrix.domain.Student;
import org.santanubrains.hystrix.log4j.Log4jUtil;
import org.santanubrains.hystrix.response.StudentResponse;

import com.google.gson.Gson;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

public class JerseyAsyncClient {

	private static Client client = null;
	private static WebTarget target = null;
	private static final Logger logger = Log4jUtil.getLogger(JerseyAsyncClient.class);

	private Gson gson;

	public JerseyAsyncClient() {
		super();
	}

	@Inject
	public JerseyAsyncClient(Gson gson) {
		super();
		this.gson = gson;
	}

	@PostConstruct
	public void init() {
		client = ClientBuilder.newBuilder().build();
	}

	public Observable<Response> clientApi(String schoolName, Long portNo) {

		return Observable.create(new OnSubscribe<Response>() {

			@Override
			public void call(Subscriber<? super Response> observer) {
				if (!observer.isUnsubscribed()) {
					target = client.target(
							"http://localhost:" + portNo + "/jax-rs-circuitbreaker/api/student/detail/" + schoolName);
					logger.info("Client & WebTarget ready");
					target.request().async().get(new InvocationCallback<Response>() {
						@Override
						public void completed(Response response) {
							final List<Student> studentList = response.readEntity(new GenericType<List<Student>>() {
							});
							observer.onNext(
									Response.status(response.getStatus()).entity(gson.toJson(new StudentResponse(new Date(),studentList))).build());
							observer.onCompleted();
						}

						@Override
						public void failed(Throwable throwable) {
							observer.onError(throwable);
						}
					});
				}

			}
		});

	}

	@PreDestroy
	public void destroy() {
		client.close();
		logger.info("Client closed...");
	}
}
