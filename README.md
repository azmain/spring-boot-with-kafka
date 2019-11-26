# Apache Kafka With Spring Boot

[apache-kafka]: https://kafka.apache.org/quickstart
Steps I Have Followed(windows):
1. First of all download apache kafka.
from [here](https://www.apache.org/dyn/closer.cgi?path=/kafka/2.3.0/kafka_2.12-2.3.0.tgz).
2. After downloading the kafka_2.12-2.3.0.tgz file extracted it with winzip.

## Running the zookeeper
1. Go to the kafka_2.12-2.3.0 directory.
2. Run the command from the cmd 
``bin/windows/zookeeper-server-start.bat C:/Apache/kafka_2.12-2.3.0/config/zookeeper.properties``.
3. I have followed their official site at [Apache Kafka][apache-kafka] site.
4. I had use the full path for configuration file, otherwise it was giving error(not find).
5. After lots of messages I got zookeeper running on port 2181 which is default in the configuration file.
``INFO binding to port 0.0.0.0/0.0.0.0:2181``.
6. I have updated my configuration for `dataDir` as `../logs`.

## Running the kafka server
1. Go to the kafka_2.12-2.3.0 directory.
2. Run the command from the cmd 
``bin/windows/kafka-server-start.bat C:/Apache/kafka_2.12-2.3.0/config/server.properties``.
3. I couldn't run it at the first attempt! Got an error as 
`'wmic' is not recognized as an internal or external command, operable program or batch file`.
4. So I had to add `C:\Windows\System32\wbem` as path variable in the Environment Variables.
5. Again run the command.
6. This time after lots of messages I got kafka server running on port 9092 which is default in the configuration file.
``INFO Awaiting socket connections on 0.0.0.0:9092``.
6. I have updated my configuration for `dataDir` as `../logs` here as well.

## Creating a topic
1. Got to the kafka_2.12-2.3.0 directory.
2. Run the command from the cmd 
`bin/windows/kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic users`.
3. Here my replica is 1, partition is 1 and topic name is users(in my code I named the topic name as users).
4. I have checked the topic list by running the command `bin/windows/kafka-topics.bat --list --bootstrap-server localhost:9092
`.
5. That's all command line part.

So, my zookeeper and kafka server are up & running.

## Spring Boot project to produce & consume messages
1. Go to [Spring Initializer](https://start.spring.io/).
2. Add Web & Kafka dependency.
3. Create the project.
4. Configure the kafka configuration in `application.properties`.
5. Created a producer, a consumer & one controller with an endpoint.
6. Run the project.
7. When the project is running go to the endpoint in the browser `http://localhost:9000/kafka/publish/myMessage`.
8. The producer will publish the message & the consumer will consume it.
9. Both will log the message in the console.