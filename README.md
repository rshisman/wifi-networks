Wifi Networks excercise
=======================
The wifi-networks application exposes 3 REST APIs that enable clients to connect devices to networks, report a device throughput and get network details.

The data based on one to many relationship between 2 entities - network and device, so we could use a relational database, but It seems more suitable to use a documnet database like MongoDB, where the parent and non-lazy childs are in one document record, it's simple and readable (for example, no joins are necessary). I did not have enough time to add mongo to the party, so I used a Map where each value is a network "document". The Map data is stored as JSON to a file in /var/lib/wifiNetworks/repository (can be configured via a system variable in the Dockerfile). 

Installation:
1. Download the docker folder to a computer with docker engine installed.
2. From this folder run the following commands:
  docker build --tag=wifi-networks-app:latest --rm=true .
  docker run -p 8080:8080 --name wifi-networks-cont1 wifi-networks-app
3. At this point, you should have a container named 'wifi-networks-cont1' (run 'docker ps' to validate).

Sending requets:
1. Get network details:
  Request: GET http://localhost:8080/api/network?id=123456
  Response: 
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
  
