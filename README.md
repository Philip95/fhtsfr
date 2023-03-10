# Execution Order

## 1. start the docker

Start the docker-compose.yml file with the command ```docker compose up``` to create the docker containers. 
It is containing the kafka and zookeeper containers.

## 2. Start the Application

The application sends the messages to the kafka topic defined in the Main class. In this case it is "recipe".

## 3. Start the consumer

To see the messages you can use whatever you like. I used the Big Data Tool from IntelliJ. 

With the exercise ongoing, there probably will be the ability to see the messages at the frontend.