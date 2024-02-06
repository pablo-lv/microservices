#!/bin/bash

# Push the Docker image to docker.io
docker image push docker.io/pablolucv/configserver:s10
echo "Config Server pushed to docker.io"
docker image push docker.io/pablolucv/eurekaserver:s10
echo "Eureka Server pushed to docker.io"
docker image push docker.io/pablolucv/accounts:s10
echo "Accounts pushed to docker.io"
docker image push docker.io/pablolucv/cards:s10
echo "Cards pushed to docker.io"
docker image push docker.io/pablolucv/loans:s10
echo "Loans pushed to docker.io"
docker image push docker.io/pablolucv/gatewayserver:s10
echo "Gateway Server pushed to docker.io"