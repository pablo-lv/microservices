#!/bin/bash

# Push the Docker image to docker.io
docker image push docker.io/pablolucv/configserver:s9
echo "Config Server pushed to docker.io"
docker image push docker.io/pablolucv/eurekaserver:s9
echo "Eureka Server pushed to docker.io"
docker image push docker.io/pablolucv/accounts:s9
echo "Accounts pushed to docker.io"
docker image push docker.io/pablolucv/cards:s9
echo "Cards pushed to docker.io"
docker image push docker.io/pablolucv/loans:s9
echo "Loans pushed to docker.io"
docker image push docker.io/pablolucv/gatewayserver:s9
echo "Gateway Server pushed to docker.io"