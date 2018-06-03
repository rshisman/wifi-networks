FROM openjdk:8-jdk-alpine

ENV REPOSITORY_FILE_LOCATION="/var/lib/wifiNetworks/repository"

RUN mkdir -p /var/lib/wifiNetworks/repository
COPY ./wifi-networks-0.0.1-SNAPSHOT.jar wifi-networks.jar

ENTRYPOINT ["java","-jar","/wifi-networks.jar"]
