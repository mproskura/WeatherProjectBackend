# Weather project

## Project objective
The objective of the project is presenting weather reports from multiple sources in one place and in unified format. Additional goal was to create service that is open to easy implementation of additional weather services with publicly available APIs. 

## Functionality
Following functionalities are available:
* text-based location search
* location presentation on a map
* weather reports from 3 external services
* data filtering 
* caching of responses from external services to speed up application and limit number of queries

## Technology
Weather project consists of separately developed frontend and backend applications. Backend application exposes API that frontend uses. 

### Backend

* Java 11
* Spring Web
* Spring Boot
* Spring Data
* SQL Database (MySQL was used for development and PostgreSQL is used in deployment environment)
* JUnit 5
* Lombok

### Frontend

* React JS
* Leaflet
* Axios
* Material UI

## Deployment
Deploy version is available here:

However, if you wanted to deploy your own, following steps are necessary:
 1. Install SQL database compatible with Spring Data.
 2. Set up database in application.properties file of backend application.
 3. Deploy complied backend application to web server of your choice. Make sure it can access database and have ports necessary for API open on firewall.
 4. Set up backend host URL in axios.js file.
 5. Deploy complied frontend application to web server of your choice.

## Future development
The project is finished and fully functional. Future development opportunities include:
 * Weather queries optimization on backend side
 * Sources filtering on frontend
 * Historical data browsing
 * Integration with additional weather data sources
 * Native mobile apps using existing API

## Licence
[MIT licence](https://choosealicense.com/licenses/mit/)

## Author
Marek Proskura [mproskura@gmail.com](mailto:mproskura@gmail.com)