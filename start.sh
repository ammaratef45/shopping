#!/bin/sh

# start the discovery app
cd service-discovery
nohup ./mvnw spring-boot:run  >> nohup.out 2>>nohup.err &
cd ../

# start the security app
cd security-service/security-service
nohup ./mvnw spring-boot:run  >> nohup.out 2>>nohup.err &
cd ../../

# start the orders app
cd order-service/order-service
nohup ./mvnw spring-boot:run  >> nohup.out 2>>nohup.err &
cd ../../
