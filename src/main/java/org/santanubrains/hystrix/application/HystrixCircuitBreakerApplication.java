package org.santanubrains.hystrix.application;

import java.util.Date;
import javax.ws.rs.ApplicationPath;

import org.apache.log4j.Logger;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.santanubrains.hystrix.dataAdapter.SchoolAdapter;
import org.santanubrains.hystrix.dataAdapter.SchoolAdapterImpl;
import org.santanubrains.hystrix.dataAdapter.StudentAdapter;
import org.santanubrains.hystrix.dataAdapter.StudentAdapterImpl;
import org.santanubrains.hystrix.exception.InternalErrorExceptionMapper;
import org.santanubrains.hystrix.filter.LoggingFilter;
import org.santanubrains.hystrix.filter.PoweredByResponseFilter;
import org.santanubrains.hystrix.log4j.Log4jUtil;
import org.santanubrains.hystrix.resource.SchoolResource;
import org.santanubrains.hystrix.resource.StudentResource;
import org.santanubrains.hystrix.service.SchoolService;
import org.santanubrains.hystrix.service.SchoolServiceImpl;
import org.santanubrains.hystrix.service.StudentService;
import org.santanubrains.hystrix.service.StudentServiceImpl;
import org.santanubrains.hystrix.utility.JerseyAsyncClient;

import com.google.gson.Gson;

@ApplicationPath("api")
public class HystrixCircuitBreakerApplication extends ResourceConfig {

	private static final Logger logger = Log4jUtil.getLogger(HystrixCircuitBreakerApplication.class);

	public HystrixCircuitBreakerApplication() {

		logger.info("Hystrix Circuit Breaker Jax-rs application initializing : " + new Date().toString());

		register(LoggingFilter.class);
		register(PoweredByResponseFilter.class);
		register(InternalErrorExceptionMapper.class);
		register(StudentResource.class);
		register(SchoolResource.class);

		register(new AbstractBinder() {

			@Override
			protected void configure() {

				bindAsContract(Gson.class);
				bindAsContract(JerseyAsyncClient.class);
				bind(StudentAdapterImpl.class).to(StudentAdapter.class);
				bind(StudentServiceImpl.class).to(StudentService.class);
				bind(SchoolAdapterImpl.class).to(SchoolAdapter.class);
				bind(SchoolServiceImpl.class).to(SchoolService.class);
			}

		});
	}
}
