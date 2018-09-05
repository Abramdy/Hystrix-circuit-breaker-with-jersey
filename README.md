# Hystrix-circuit-breaker-with-jersey
A microservice implementation in jersey framework with asynchronous JerseyClient and Jersey endpoint.Technology stack include Rxjava, Hystrix, Gson and so on. Hystrix is a latency and fault tolerance library designed to isolate points of access to remote systems, services and 3rd party libraries, stop cascading failure and enable resilience in complex distributed systems where failure is inevitable.

-----------------------------------------------------------------------------------------------------------------------------------------

There are two endpoints in the microservice. First endpoint requests the Second endpoint with jerseyAsyncClient api to get the response. The Second endpoint sometime becomes unreachable due to lot of requests or it is down.So here comes the Netflix hystrix library to solve the problem of fault tolerance of second endpoint with fallback implementation.

-----------------------------------------------------------------------------------------------------------------------------------------

In reallife scenerio ,the two endpoints will be running on different server with different configuration.
For particle purpose, in that code example the same above behaviour can be obtain by changing the port no of the second service. Basically both the service works in the same port number. But for demo purpose we will be changing the port number of the second service dynamically from the first service when the request goes out.

------------------------------------------------------------------------------------------------------------------------------------------

First EndPoint:-
[x]
