# Database Configuration

I chose to use a PostgresSQL database for this project.
Therefore, I included the config in the application.properties file as follows:

``` properties
spring.datasource.url=jdbc:postgresql://localhost:5432/sfr
spring.datasource.username=postgres
spring.datasource.password=root

# Automate creating database
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.defer-datasource-initialization=true
spring.jpa.properties.hibernate.ddl-auto=update
```

With this config Spring will automatically create the tables for me. 

For this to work, I needed the following dependencies in my build.gradle file:

``` groovy
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
testImplementation 'org.springframework.boot:spring-boot-starter-test'

implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
runtimeOnly 'org.postgresql:postgresql'
testImplementation 'org.springframework.boot:spring-boot-starter-test'
```

One of the reasons I chose PostgreSQL is because I would say, in my case it is better to have a relational database, 
because recipes always look the same. I don't expect a lot of different data structures, so I don't see the need of a 
NoSQL database, but even when I would need it, PostgreSQL support a bit of NoSQL features, which doesn't mean, that it is
a NoSQL database or can replace it.
Furthermore, PostgreSQL works smoothly with Spring Boot, since I just need to add the configuration. Also it can hanlde 
JSON and array values. 