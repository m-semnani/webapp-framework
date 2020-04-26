#!/bin/bash

echo 'builing spring boot docker image ...'
cd back-springboot
mvn clean install
mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
#docker image rm productmngt/back-springboot:latest
docker build -t productmngt/back-springboot .
cd ..
echo 'Image (productmngt/back-springboot:latest) has been created successfully.'

echo 'builing angular docker image ...'
cd front-angular
docker build -t productmngt/front-angular:latest .
cd ..
echo 'Image (productmngt/front-angular:latest) has been created successfully.'

docker-compose up

