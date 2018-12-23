# Todo app

## How to build it ?

`mvn clean package` is all you need

## How to run it locally ?

A docker-compose version is coming... Waiting for it ou'll have to start a postgresql container on the same network.

### Create a docker network

`docker network create jpn-network`

## Start a local PostgresQL

You can do that using docker : 

`docker run --network=jpn-network -p 5432:5432 --name some-postgres -e POSTGRES_PASSWORD=postgres -d postgres`

## Build the Dockerfile

`docker build -t todoapp .`

## Run the container

`docker run --network=jpn-network -p 8080:8080 --name todoapp todoapp`

You'll be able to **create** or **list** todo entries using the example below : 

```bash
curl -X POST \
  http://localhost:8080/todo \
  -H 'content-type: application/json' \
  -d '{
	"title":"test"
}'
```

```bash
curl -X GET \
  http://localhost:8080/todo
```