# Airbank demo Java + Spring orchestration project

## Technology
* Java 11
* Spring boot 2.1.6
* Springfox 2.9.2
* Hibernate validator 6.0.17
* Mapstruct 1.3.0

## Functionality
Orchestrates https://jsonplaceholder.typicode.com/ `user` and `todo` info. Using Spring MVC & Webflux.

## Running
```
$ mvn spring-boot:run
```

Usage:  
```
curl -v http://localhost:8080/user/1
```
for information about user id 1.

Or visit http://localhost:8080/swagger-ui.html where REST API documentation is located with Try-it-out feature.

Supports `GET`, `HEAD`, `OPTIONS` operations, `ETag` and `If-None-Match` header.

##Configuration
* Backend url: `jsonplaceholder.baseurl`
* Backend connection properties: `timeout.connection`, `timeout.read`, `timeout.write`