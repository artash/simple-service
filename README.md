# ss

How to start the ss application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/service-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`


To execute commands against the locally running server:
```
curl -X POST http://localhost:8080/messages -H "Content-Type: application/json" -d '{"message": "foo"}'

curl -i http://localhost:8080/messages/2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7ae

```
