## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [TODO] (#TODO)

## General info
This project is used for creating customers from different sources. 
Source being denoted by http header 'x-Source' in every create customer request.
It's also possible to to find customer by it's DB id and by following fields: firstName, lastname, patronymic, phoneNumber, email.

## Technologies
Project is created with:
* Java 17
* Spring Boot 3.0.1
* Postgres
* Docker

## Setup
* Make sure java 17 is installed
* Install docker
* Build project using `./gradlew build`
* Run docker-compose.yaml (application, postgres and pgadmin will spin up)

## TODO
Add more test cases for different datasources. Just 1 datasource was tested so far.