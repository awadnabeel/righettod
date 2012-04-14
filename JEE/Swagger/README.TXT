**********
* README *
**********

This project explore somes features of SWAGGER framework : http://swagger.wordnik.com/ . 

This framework is used to describe REST services.

REST services are exposed here through JAX-RS using RI provided into JEE6 server GlassFish 3.1.

Project use Maven under Netbeans.

URL of REST services are (CURL samples commands to access them):
[ADD DATA]
ADD           : curl -X POST -d "id=123&name=A4&constructor=audi" http://localhost:8080/Swagger/car.xml
ADD           : curl -X POST -d "id=321&name=A6&constructor=audi" http://localhost:8080/Swagger/car.json

[GET DATA]
GET           : curl -X GET http://localhost:8080/Swagger/car.xml/123
GET           : curl -X GET http://localhost:8080/Swagger/car.json/321

[DELETE DATA]
DELETE        : curl -X DELETE http://localhost:8080/Swagger/car.xml/123
DELETE        : curl -X DELETE http://localhost:8080/Swagger/car.json/321

[SERVICES DOCUMENTATION]
DOCUMENTATION : curl -X GET http://localhost:8080/Swagger/resources.xml
DOCUMENTATION : curl -X GET http://localhost:8080/Swagger/resources.json
DOCUMENTATION : curl -X GET http://localhost:8080/Swagger/car.xml
DOCUMENTATION : curl -X GET http://localhost:8080/Swagger/car.json


----------------------
Dominique Righetto
April 2012