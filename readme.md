Kafka Producer and Consumer
=========================

----

This project demonstrate the very basics of creating a Kafka producer and consumer.
The producer can be found under com.abdo.kafka.demo.producer package
The consumer can be found under com.abdo.kafka.demo.consumer package

### Build and run ###

#### Prerequisites ####

- Java 8
- Scala 2.11
- Maven > 3.0
- Kafka 2.11

#### Configurations ####
There are no specific configuration other than downloading the Kafka broker. 
The following is the simple steps to run the producer and consumer.

1. run the zookeeper.
2. run the broker.
3. run the test class KafkaProducerTest.
4. run the test class KafkaConsumerTest.


Note: we are using the simple/default configuration comes with the Kafka

#### To run from terminal ####

**Run ZooKeeper**

From the root location where the kafka folder exists run the following
    
   `$> bin/zookeeper-server-start.sh config/zookeeper.properties` 

**Run Broker**

From the root location where the kafka folder exists run the following. 
You can run this line several times with differnet properties file to make a cluster.
In below example we ran three servers.
    
   `$> kafka-server-start.sh config/server.properties`
   `$> kafka-server-start.sh config/server-1.properties`
   `$> kafka-server-start.sh config/server-2.properties` 


**Create Topic**
From the root location where the kafka folder exists run the following. 
The below will create a topci named "User_Messages" with 3 partition and 3 replica

   `$> bin/kafka-topics.sh --create --topic "User_Messages"  --zookeeper localhost:2181  --partitions 3  --replication-factor 3`

You can check the status of the topic by running the following command

   `$> bin/kafka-topics.sh --describe --topic my_topic --zookeeper localhost:2181`


  - - - -

**Notes**

1. To create topic with one partition only and 3 replica, run the following 
 * `$> bin/kafka-topics.sh --create --topic "User_Messages"  --zookeeper localhost:2181  --partitions 3  --replication-factor 3`
2. To list all topics, run the following
 * `$> bin/kafka-topics.sh --list  --zookeeper localhost:2181`
3. To delete a topic, run the following  ( the delete.topic.enable has to be true on all brokers first )
 * `$> bin/kafka-topics.sh --delete --topic "test"  --zookeeper localhost:2181`
4- To run a the Kafka "console" producer that is shipped with the Kafka folder
 * `$> bin/kafka-console-producer.sh  --broker-list localhost:9092 --topic User_Messages`
   OR
 * `$> bin/kafka-console-producer.sh config/producer.properties`
5- To run a the Kafka "console" consumer that is shipped with the Kafka folder
 * `$> in/kafka-console-consumer.sh --zookeeper localhost:2181 --topic User_Messages --from-beginning`




''''