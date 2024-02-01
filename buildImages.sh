#!/bin/bash

# Change directory to /configserver and execute mvn compile jib:dockerBuild
cd configserver/ && mvn compile jib:dockerBuild
cd ..
# Change directory to /eurekaserver and execute mvn compile jib:dockerBuild
cd eurekaserver/ && mvn compile jib:dockerBuild
cd ..
# Change directory to /accounts and execute mvn compile jib:dockerBuild
cd accounts/ && mvn compile jib:dockerBuild
cd ..
# Change directory to /cards and execute mvn compile jib:dockerBuild
cd cards/ && mvn compile jib:dockerBuild
cd ..
# Change directory to /loans and execute mvn compile jib:dockerBuild
cd loans/ && mvn compile jib:dockerBuild
cd ..
# Change directory to /gatewayserver and execute mvn compile jib:dockerBuild
cd gatewayserver/ && mvn compile jib:dockerBuild



