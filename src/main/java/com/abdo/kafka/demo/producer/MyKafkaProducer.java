/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abdo.kafka.demo.producer;

import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 *
 * @author atawakol
 */
public class MyKafkaProducer {
    
    private static final String TOPIC = "User_Messages";
    private static final DateFormat formatter = DateFormat.getDateTimeInstance();
    
    public static void sendMessage(String key, String value) {
        String message = String.format("Message  %s with key %s has been sent at %s", value, String.valueOf(key), formatter.format(new Date()));
        KafkaProducer<String, String> producer = new KafkaProducer<>(getMandtoryConfigurations());
        ProducerRecord<String, String> recorder = new ProducerRecord<>(TOPIC, key, message);
        
        try {
            System.out.println("Trying to send");
            producer.send(recorder);
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        finally {
            producer.close();
        }
    }
    
    public static void sendMessage(String value) {
        
        String message = String.format("Message %s has been sent at %s", value, formatter.format(new Date()));
        KafkaProducer<String, String> producer = new KafkaProducer<>(getMandtoryConfigurations());
        ProducerRecord<String, String> recorder = new ProducerRecord<>(TOPIC, message);
        
        try {
            System.out.println("Trying to send");
            producer.send(recorder);
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
   
    private static Properties getMandtoryConfigurations(){
        Properties props = new Properties();
        // broker locations
        props.put("bootstrap.servers", "localhost:9092, localhost:9093, localhost:9094");
        // the serializer for the key of the messages
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // the serializer for the value of the messages
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        
        props.put("retries", 1);
        
        return props;
    }      
        
    private static Properties getDetailedConfigurations(){
        Properties props = new Properties();
        // broker locations
        props.put("bootstrap.servers", "localhost:9092, localhost:9093, localhost:9094");
        // the serializer for the key of the messages
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // the serializer for the value of the messages
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // if we need server acks on recieving the message (three possible value 0, 1, 2)
        props.put("acks", "");
        // the ceil memory limit in bytes to be used by producer for buffering the message
        props.put("buffer.memory", 33554432);
        // of we need any compression for the message
        props.put("compression.type", "none");
        // if message fails, how many times should we retry
        props.put("retries", 0);
        // topic_parition batch size in memory in bytes. When reached the message is sent to server
        props.put("batch.size", 16384);
        props.put("client.id", "");
        // how long the message should stay in the buffer before trying to send it
        props.put("linger.ms", 0);
        props.put("max.block.ms", 60000);
        props.put("max.request.size", 1048576);
        props.put("partitioner.class", "org.apache.kafka.clients.producer.internals.DefaultPartitioner");
        props.put("request.timeout.ms", 30000);
        props.put("timeout.ms", 30000);
        props.put("max.in.flight.requests.per.connection", 5);
        // how long to wait between retries
        props.put("retry.backoff.ms", 5);
        
        return props;
    }    
}
