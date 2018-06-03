docker build -t wifi-networks-app:latest --rm=true .
docker run -p 8080:8080 --name wifi-networks-cont1 wifi-networks-app
