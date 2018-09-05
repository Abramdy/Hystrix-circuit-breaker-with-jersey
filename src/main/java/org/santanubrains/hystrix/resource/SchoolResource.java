package org.santanubrains.hystrix.resource;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ManagedAsync;
import org.santanubrains.hystrix.exception.InternalErrorException;
import org.santanubrains.hystrix.log4j.Log4jUtil;
import org.santanubrains.hystrix.service.SchoolService;

import rx.Observer;

@Path("school/detail")
public class SchoolResource {

	private static final Logger logger = Log4jUtil.getLogger(SchoolResource.class);
	private SchoolService schoolService;

	@Inject
	public SchoolResource(SchoolService schoolService) {
		super();
		this.schoolService = schoolService;
	}

	@Path("{schoolname}/{portno}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ManagedAsync
	public void getSchoolDetailBySchoolName(@Suspended final AsyncResponse async,
			@PathParam("schoolname") final String schoolName, @PathParam("portno") final Long portNumber) {

		final CountDownLatch outerLatch = new CountDownLatch(1);

		schoolService.getSchoolDetailBySchoolName(schoolName.toLowerCase(), portNumber)
				.subscribe(new Observer<Response>() {

					@Override
					public void onCompleted() {
						logger.info("Completed");

					}

					@Override
					public void onError(Throwable error) {
						logger.error(error.getCause().getMessage());
						async.resume(error.getCause().getMessage());
						outerLatch.countDown();

					}

					@Override
					public void onNext(Response response) {
						logger.debug("onNext :" + response);
						async.resume(response);
						outerLatch.countDown();

					}
				});

		try {
			if (!outerLatch.await(10, TimeUnit.SECONDS)) {
				async.resume(new InternalErrorException());
			}
		} catch (Exception e) {
			async.resume(new InternalErrorException());
		}
	}

}
