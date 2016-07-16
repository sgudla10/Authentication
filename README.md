# Authentication
Set up the project 
1)Open the file in the cassandra-config.properties and add the cassandra host name in the propety name of "clusterIpAddressList" and update the port property toin the property name of "port"

2)open the database.cql file which is located in the
https://github.com/sgudla10/Authentication/blob/master/netgear-auth-domain/src/main/resources/database.cql

and execute the commands to create the keyspace and also the table and insert one record for testing

3)Please check out the code
run the following maven commands

mvn clean install

once it is build properly run the jetty continer

mvn jetty:run


4)Open the postman and call the service.As we inserted one record in the table .The different scenations with requests

Bad request scenario
***********************

1)http://hostname:8080/netgear-auth-service/user/login
Request 
{
    
    
}

Response
{
  "email": null,
  "lastName": null,
  "firstName": null,
  "name": null,
  "error": [
    {
      "code": "400.AuthService.BAD_REQUEST",
      "desc": ", password : may not be null, email : may not be null"
    }
  ]
}

HTTP response :400

2)http://hostname:8080/netgear-auth-service/user/login
Request 
{
    "email":"swati.gudla@gmail.com"

    
}
Response
{
  "email": null,
  "lastName": null,
  "firstName": null,
  "name": null,
  "error": [
    {
      "code": "400.AuthService.BAD_REQUEST",
      "desc": ", password : may not be null"
    }
  ]
}

Not Found Scenario
*********************
1)http://hostname:8080/netgear-auth-service/user/login
Request 

{
    "email":"swati.malla@gmail.com",
    "password":"22"

    
}
Response
{
  "email": null,
  "lastName": null,
  "firstName": null,
  "name": null,
  "error": [
    {
      "code": "400.AuthService.NOT_FOUND",
      "desc": "user not found"
    }
  ]
}
HTTP STATUS:404

Password incorrect scenario
******************************
http://hostname:8080/netgear-auth-service/user/login
1)Request
{
    "email":"swati.gudla@gmail.com",
    "password":"22"

    
}
Response
{
  "email": null,
  "lastName": null,
  "firstName": null,
  "name": null,
  "error": [
    {
      "code": "400.AuthService.BAD_REQUEST",
      "desc": "Password is incorrect"
    }
  ]
}
HTTP STATUS: 400 

Valid Request and Resposne
***************************
http://hostname:8080/netgear-auth-service/user/login
Request 

{
    "email":"swati.gudla@gmail.com",
    "password":"11"

    
}

Response
{
  "email": "swati.gudla@gmail.com",
  "lastName": "gudla",
  "firstName": "swati",
  "name": "swati malla",
  "error": null
}

5)Please check out the LICCap file for more instructions



 

