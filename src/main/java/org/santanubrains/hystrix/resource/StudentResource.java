package org.santanubrains.hystrix.resource;

import java.util.List;
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

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ManagedAsync;
import org.santanubrains.hystrix.domain.Student;
import org.santanubrains.hystrix.exception.InternalErrorException;
import org.santanubrains.hystrix.log4j.Log4jUtil;
import org.santanubrains.hystrix.service.StudentService;

import com.google.gson.Gson;

import rx.Observer;

@Path("student/detail")
public class StudentResource {

	private static final Logger logger = Log4jUtil.getLogger(StudentResource.class);
	private StudentService studentService;
	private Gson gson;

	@Inject
	public StudentResource(StudentService studentService, Gson gson) {
		super();
		this.studentService = studentService;
		this.gson = gson;
	}

	@Path("{schoolname}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ManagedAsync
	public void getStudentDetailBySchoolName(@Suspended final AsyncResponse async,
			@PathParam("schoolname") final String schoolName) {

		final CountDownLatch outerLatch = new CountDownLatch(1);

		studentService.getStudentDetailsForSchool(schoolName.toLowerCase()).subscribe(new Observer<List<Student>>() {

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
			public void onNext(List<Student> studentList) {
				logger.debug("onNext :" + gson.toJson(studentList));
				async.resume(gson.toJson(studentList));
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
