# Execution Order

## 1. start the docker

Start the docker-compose.yml file with the command ```docker compose up``` to create the docker containers. 
It is containing the kafka and zookeeper containers.

## 2. Start the Application

The application sends the messages to the kafka topic defined in the Main class. In this case it is "recipe".

## 3. Start the consumer

To see the messages you can use whatever you like. I used the Big Data Tool from IntelliJ. 

You can send a ```POST``` Request in Postman to ```http://localhost:8080/``` to send a message to the topic. With the following example
body:

```json
{
  "id":1,
  "name":"Gulasch",
  "description":"Guades Gulasch",
  "ingredients":"Was i ned",
  "instructions":"Kochs",
  "evaluation":4,
  "preparationTime":12,
  "category":"Eintopf"
}
```

Of course it's not perfect since ingredients should be a list, but I wanted to keep it simple and make it work in the first place.