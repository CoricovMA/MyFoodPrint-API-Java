docker build . --no-cache -t test2

docker run --name test2 -d -p 80:8080 -p 443:8443 test2
