#!/bin/bash

# Push the Docker image to docker.io
docker image push docker.io/pablolucv/configserver:s12
echo "Config Server pushed to docker.io"
docker image push docker.io/pablolucv/eurekaserver:s12
echo "Eureka Server pushed to docker.io"
docker image push docker.io/pablolucv/accounts:s12
echo "Accounts pushed to docker.io"
docker image push docker.io/pablolucv/cards:s12
echo "Cards pushed to docker.io"
docker image push docker.io/pablolucv/loans:s12
echo "Loans pushed to docker.io"
docker image push docker.io/pablolucv/gatewayserver:s12
echo "Gateway Server pushed to docker.io"