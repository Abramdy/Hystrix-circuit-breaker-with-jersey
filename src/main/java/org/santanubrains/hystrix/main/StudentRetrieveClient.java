package org.santanubrains.hystrix.main;

import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.santanubrains.hystrix.domain.Student;
import org.santanubrains.hystrix.response.StudentResponse;

import com.google.gson.Gson;

public class StudentRetrieveClient {

	public static void main(String[] args) {
		Gson gson = new Gson(); 
		Client client = ClientBuilder.newBuilder().build();
		WebTarget target = client.target("http://localhost:9090/jax-rs-circuitbreaker/api/student/detail/cvraman");
		long start = System.currentTimeMillis();
		target.request().async().get(new InvocationCallback<Response>() {
			@Override
			public void completed(Response response) {
				final List<Student> studentList = response.readEntity(new GenericType<List<Student>>() {});
				System.out.println("response: " + gson.toJson(new StudentResponse(new Date(),studentList)));
				System.out.println("time taken: " + (System.currentTimeMillis() - start));
			}

			@Override
			public void failed(Throwable throwable) {
				throwable.printStackTrace();
			}
		});
		System.out.println("request returns");

	}

}
