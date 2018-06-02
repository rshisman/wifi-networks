Wifi Networks excercise
=======================
The wifi-networks application exposes 3 REST APIs that enable clients to connect devices to networks, report a device throughput and get network details.
The data based on one to many relationship between 2 entities - network and device, so we could use a relational database, but It seems more suitable to use a documnet database like MongoDB, where the parent and non-lazy childs are in one document record, it's simple and readable (for example, no joins are necessary). I did not have enough time to add mongo to the party, so I used a Map where each value is a network "document". The Map data is stored as JSON to a file in /var/lib/wifiNetworks/repository (can be configured via a system variable). 
