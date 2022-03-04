FROM openjdk:8u191-jre-alpine3.8

RUN apk add curl jq

# Workspace
WORKDIR /usr/share/udemy

# Add .jar under target from Host
# into this image
ADD target/selenium-docker.jar          selenium-docker.jar
ADD target/selenium-docker-tests.jar    selenium-docker-tests.jar
ADD target/libs                         libs

# In case you want to add any other dependencies like .csv/.json/.prop here

# Add suite files
ADD book-flight-module.xml              book-flight-module.xml
ADD search-module.xml                   search-module.xml

# ADD health check script
ADD healthcheck.sh                      healthcheck.sh
RUN dos2unix healthcheck.sh
#RUN wget https://s3.amazonaws.com/selenium-docker/healthcheck/healthcheck.sh

# BROWSER
# HUB_HOST
# MODULE

#ENTRYPOINT java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* -DBROWSER=$BROWSER -DHUB_HOST=$HUB_HOST org.testng.TestNG $MODULE

ENTRYPOINT sh healthcheck.sh
