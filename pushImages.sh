#!/bin/bash

# Push the Docker image to docker.io
docker image push docker.io/pablolucv/configserver:s11
echo "Config Server pushed to docker.io"
docker image push docker.io/pablolucv/eurekaserver:s11
echo "Eureka Server pushed to docker.io"
docker image push docker.io/pablolucv/accounts:s11
echo "Accounts pushed to docker.io"
docker image push docker.io/pablolucv/cards:s11
echo "Cards pushed to docker.io"
docker image push docker.io/pablolucv/loans:s11
echo "Loans pushed to docker.io"
docker image push docker.io/pablolucv/gatewayserver:s11
echo "Gateway Server pushed to docker.io"