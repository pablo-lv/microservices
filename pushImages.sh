#!/bin/bash

# Push the Docker image to docker.io
docker image push docker.io/pablolucv/configserver:s8
docker image push docker.io/pablolucv/eurekaserver:s8
docker image push docker.io/pablolucv/accounts:s8
docker image push docker.io/pablolucv/cards:s8
docker image push docker.io/pablolucv/loans:s8