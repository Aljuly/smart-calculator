# Smart Calculator

> Online calculation service providing basic long arithmetic operations

## Table of contents

* [General info](#general-info)
* [Features](#features)
* [Technologies](#technologies)
* [Running the application](#running-the-application)
* [Documentation](#documentation)
* [Status](#status)


## General info

This service allows User proceed basic long arithmetic operations: addition, subtraction, multiplication, integer division, division with period finding. When User selects any particular operation, short operation description is appeared.     

For the non-authenticated users only addition operation is allowed. For the other operations one should register first.
  
## Features

To make a calculation one need to pass operation Id, first operand, second operand. The result will be JSON structure representing calculation result and calculation chain. Also request should contain security token.
There is such Operation Ids stored in database:
* 10000 - addition
* 10001 - subtraction
* 10002 - multiplication
* 10003 - integer division
* 10004 - division with period finding)

To get security token User have to authenticate or register. There is JWT authentication is used.
For request caching Redis is used. When User makes calculation request, its saves in Redis store along with response. Search in Redis store is providing when making every new request. If such a request found, response to User is taking from Redis.

## Technologies

* [Maven](https://maven.apache.org/) - Dependency Management
* [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit 
* [Apache Tomcat](http://tomcat.apache.org) - implementation of the Java Servlet, JavaServer Pages, Java Expression Language and Java WebSocket technologies
* [PostgreSQL](https://www.postgresql.org) - a powerful, open source object-relational database system
* [Redis](https://redis.io) - Redis is an open source (BSD licensed), in-memory data structure store
* [Log4j 2](https://logging.apache.org/log4j/2.x/) - a popular logging package for Java
* [git](https://git-scm.com/) - Free and Open-Source distributed version control system 
* [Postman](https://www.getpostman.com/) - API Development Environment (Testing Docmentation)

## Running the application

- Clone the Git repository.
- Open Command Prompt and Change directory (cd) to folder containing pom.xml
- Open Eclipse 
   - File -> Import -> Existing Maven Project -> Navigate to the Project folder, select the project
- Start PostgreSQL database. Sequentially execute schema.sql and operations.sql scripts from src->main->resources.
- In Eclipse right-click on Tomcat in Servers tab, choose Add and Remove dialog, move resource to server. Start the server.

## Documentation

* [Postman Collection](https://documenter.getpostman.com/view/5427906/S1TPZfMy) - online, with code auto-generated snippets in cURL


## Status

Project is: _in progress_