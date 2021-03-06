Wifi Networks excercise
=======================
The wifi-networks application exposes 3 REST APIs that enable clients to connect devices to networks, report a device throughput and get network details.

The data based on one to many relationship between 2 entities - network and device, so we could use a relational database, but It seems more suitable to use a documnet database like MongoDB, where the parent and non-lazy childs are in one document record, it's simple and readable (for example, no joins are necessary). I did not have enough time to add mongo to the party, so I used a Map where each value is a network "document". The Map data is stored as JSON to a file in /var/lib/wifiNetworks/repository (can be configured via a system variable in the Dockerfile). 

Installation
-------------
Download the repository as zip.
To run the wifi-networks app use the following commands (linux):

```
unzip wifi-networks-master.zip
cd wifi-networks-master/docker/
chmod +x run_wifi_networks_docker.sh
./run_wifi_networks_docker.sh
```
  
At this point, you should have a running container named 'wifi-networks-cont1'
run `docker ps` to verify.

*Note*: you can build the Spring boot application JAR yourself:

```
export JAVA_HOME=/opt/java/jdk1.8.0_171
chmod +x mvnw
./mvnw package
```
But for convinience I already placed the JAR file in the docker folder.

Sending requets
----------------

The client can use curl or Postman, an example curl is added at the botom of each API section.

API no.1 - Get network details
-------------------------------

Request: GET http://localhost:8080/api/network?id=123456

Response: 404 if network not found, or:
  {
    "id": 123456,
    "auth": "wpa",
    "avg_throughput": 450,
    "devices": [
        "a1b1",
        "a1b2",
        "a1b3",
        "a1b4"
    ]
  }

  Example curl:
  ```curl -X GET http://localhost:8080/api/network?id=123456```

API no.2 - connect device
--------------------------

Request: PUT http://localhost:8080/api/network/connect
  body: {
    "device_id": "a1b3",
    "network_id": "123456",
    "auth" : "wpa"
    }

Response: HTTP code 200/409/500/400 and the relevant message.
  
Example curl:   
```curl -X PUT -H "Content-Type: application/json" -d '{ "device_id": "a1b1", "network_id": "123456", "auth" : "wpa" }'    http://localhost:8080/api/network/connect```

API no.3 - report throughput
----------------------------

Request: POST http://localhost:8080/api/network/report
  body: {
    "device_id": "a1b3",
    "network_id": 123456,
    "throughput" : 1000
    }

Response: HTTP code 200/409/500/400 and the relevant message.
  
Example curl: 
```curl -X POST -H "Content-Type: application/json" -d '{ "device_id": "a1b1", "network_id": 123456, "throughput" : 400 }' http://localhost:8080/api/network/report```
  
  
