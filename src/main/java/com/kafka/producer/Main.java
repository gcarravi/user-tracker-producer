package com.kafka.producer;

import com.kafka.producer.model.Event;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

import static java.lang.Thread.sleep;

@Slf4j
public class Main {

    public static void main(String[] args) throws InterruptedException{
        EventGenerator eventGenerator = new EventGenerator();

        Properties properties = new Properties();
        properties.put("bootstrap.servers","localhost:9093, localhost:9094");
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        //kafka producer is instantiated
        Producer<String, String> producer = new KafkaProducer<String, String>(properties);

        //Generate random events. We will generate 10 events at an interval of 1 sec
        for(int i = 0; i<=10; i++){
            Event event = eventGenerator.generateEvent();

            String key = extractKey(event);
            String value = extractValue(event);

            ProducerRecord<String, String> producerRecord = new ProducerRecord<>("user-tracking", key, value);

            log.info("Sending event: " + key + " , " + value);
            producer.send(producerRecord);

            sleep(1000);
        }

        producer.close();
    }

    private static String extractKey(Event event){
        return event.getUser().getUserId().toString();
    }

    private static String extractValue(Event event){
        return String.format("%s,%s,%s", event.getProduct().getDesignType(), event.getProduct().getColor(), event.getProduct().getProductType());
    }
}
