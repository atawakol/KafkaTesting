/**
 * 
 *
 */
package com.abdo.kafka.demo.consumer;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;


/**
 *
 * The consumer assumes the following.
 * 1- Single Broker
 * 2- Two topics
 * 3- Three partitions per topic
 * 
 * @author atawakol
 */
public class MyKafkaConsumer {

    private static final String MY_FIRST_TOPIC = "my_first_topic";
    private static final String MY_SECOND_TOPIC = "my_second_topic";
    private static final String TOPIC = "User_Messages";
    private static final DateFormat formatter = DateFormat.getDateTimeInstance();

    public static void recieveMesssageBySubscribe() {

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(getMandtoryConfigurations(true));
        subscribeToTopics(consumer);

        try {
            while (true) {
                ConsumerRecords records = consumer.poll(100);
                printMessages(records);
            }

        } finally {
            consumer.close();
        }
    }

    private static void subscribeToTopics(KafkaConsumer<String, String> consumer) {
        List<String> topics = new ArrayList<>();
        
        //topics.add(TOPIC);
        topics.add(MY_FIRST_TOPIC);
        topics.add(MY_SECOND_TOPIC);

        consumer.subscribe(topics);
    }

    public static void recieveMesssageByAssign() {

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(getMandtoryConfigurations(false));
        assignToParitions(consumer);

        try {
            while (true) {
                ConsumerRecords records = consumer.poll(1000);
                printMessages(records);
            }

        } finally {
            consumer.close();
        }
    }

    private static void assignToParitions(KafkaConsumer<String, String> consumer) {
        List<TopicPartition> paritions = new ArrayList<>();
        paritions.add(new TopicPartition(MY_FIRST_TOPIC, 1));
        paritions.add(new TopicPartition(MY_SECOND_TOPIC, 0));

        consumer.assign(paritions);
    }

    private static Properties getMandtoryConfigurations(boolean subscribe) {
        Properties props = new Properties();
        // broker locations
        props.put("bootstrap.servers", "localhost:9092, localhost:9093, localhost:9094");
        // the serializer for the key of the messages
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // the serializer for the value of the messages
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        if (subscribe)
            props.put("group.id", "my-group");

        return props;
    }

    
    private static void printMessages(ConsumerRecords<String, String> records) {
        Iterator<ConsumerRecord<String, String>> iterator = records.iterator();

        while (iterator.hasNext()) {

            ConsumerRecord<String, String> record = iterator.next();
            System.out.println(String.format("Message recieved from Topic %s, partition %d and offset %d: The message details are key: %s and value %s",
                    record.topic(), record.partition(), record.offset(), record.key(), record.value()));
        }
    }

    private static Properties getDetailedConfigurations() {
        Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9092, localhost:9093");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        props.put("fetch.min.bytes", 1);
        props.put("group.id", "");
        props.put("heartbeat.interval.ms", 3000);
        props.put("max.partition.fetch.bytes", 1048576);
        props.put("session.timeout.ms", 30000);
        props.put("auto.offset.reset", "latest");
        props.put("connections.max.idle.ms", 540000);
        props.put("enable.auto.commit", true);
        props.put("exclude.internal.topics", true);
        props.put("max.poll.records", 2147483647);
        props.put("partition.assignment.strategy", "org.apache.kafka.clients.consumer.RangeAssignor");
        props.put("request.timeout.ms", 40000);
        props.put("auto.commit.interval.ms", 5000);
        props.put("fetch.max.wait.ms", 500);
        props.put("metadata.max.age.ms", 300000);
        props.put("reconnect.backoff.ms", 50);
        props.put("retry.backoff.ms", 100);
        props.put("client.id", "");

        return props;
    }

}
