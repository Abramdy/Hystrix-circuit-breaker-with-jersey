package org.santanubrains.hystrix.exception;

public class ExceptionConfig {

	public static final String  ERROR_URL = "URI";
	public static final Integer GENERIC_INTERNAL_SERVER_ERROR = 500;
	public static final Integer NOT_FOUND = 404;
	public static final Integer GATE_WAY_TIMEOUT = 504;
	public static final String ERROR_MESSAGE = "CIRCUIT BREAKER ENABLED!!!No Response From Student Service at this moment. Service will be back shortly";
}
