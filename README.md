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

4)Open the postman and call the service
 

