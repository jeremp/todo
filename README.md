# Todo app

A Spring boot REST API to read/create todo entries.
Data are stored in a PostgresQL Database

## How to build it ?


`mvn clean package` is all you need


## How to run it locally ?

### Using Docker (easiest)

Build the containers : 

`docker-compose build`

Start the containers : 

`docker-compose up`

Stop the containers : 

`docker-compose stop`

### Without Docker

`java -jar target/todo-0.0.1-SNAPSHOT.jar` will start the app.
By default it will try to connect to a local PostgresQL instance using postgres/postgres as credentials.

If your Postgresql is running somewhere else or using other credentials you can set Spring properties in the command line :
 
`java -jar target/todo-0.0.1-SNAPSHOT.jar --spring.datasource.url=jdbc:postgresql://mypostgresql:5432/postgres --spring.datasource.username=myusername --spring.datasource.password=mypassword`

## Using the API

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