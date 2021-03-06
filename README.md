# Hystrix-circuit-breaker-with-jersey
A microservice implementation in jersey framework with asynchronous JerseyClient and Jersey endpoint.Technology stack include Rxjava, Hystrix, Gson and so on. Hystrix is a latency and fault tolerance library designed to isolate points of access to remote systems, services and 3rd party libraries, stop cascading failure and enable resilience in complex distributed systems where failure is inevitable.

-----------------------------------------------------------------------------------------------------------------------------------------

There are two endpoints in the microservice. First endpoint requests the Second endpoint with jerseyAsyncClient api to get the response. The Second endpoint sometime becomes unreachable due to lot of requests or it is down.So here comes the Netflix hystrix library to solve the problem of fault tolerance of second endpoint with fallback implementation.

-----------------------------------------------------------------------------------------------------------------------------------------

In reallife scenerio ,the two endpoints will be running on different server with different configuration.
For particle purpose, in that code example the same above behaviour can be obtain by changing the port no of the second service. Basically both the service works in the same port number. But for demo purpose we will be changing the port number of the second service dynamically from the first service when the request goes out.

------------------------------------------------------------------------------------------------------------------------------------------

First EndPoint (School Service):-
- [x] http://ipaddress:portno/jax-rs-circuitbreaker/api/school/detail/{schoolname}/{portno}

Here schoolname and portno both are pathparam. 

(http://ipaddress:portno/jax-rs-circuitbreaker/api/school/detail/kiit/9090)

Response:-
{
    "time": "Sep 5, 2018 1:33:15 PM",
    "student": [
        {
            "name": "Bob",
            "className": "MCA"
        },
        {
            "name": "John",
            "className": "MCA"
        }
    ]
}

Note:- If the student service is down or not available.

(Here the same will be obtained by changing the port of the second service from the first service request)

Response:-
{
    "errorCode": 504,
    "errorMessage": "CIRCUIT BREAKER ENABLED!!!No Response From Student Service at this moment. Service will be back shortly",
    "date": "Sep 5, 2018 1:36:42 PM"
}

Second EndPoint (Student Service):-
- [x] http://ipaddress:portno/jax-rs-circuitbreaker/api/student/detail/{schoolname}

Here schoolname is the pathparam which will be set from the first end point request using JerseyAsyncClient api for the second end point request. Similary the port number will be set in the same way. 

("http://ipaddress:"+portno+"/jax-rs-circuitbreaker/api/student/detail/"+schoolname)

Response:-
[
    {
        "name": "Bob",
        "className": "Btech CSE"
    },
    {
        "name": "John",
        "className": "Btech EEE"
    }
]



