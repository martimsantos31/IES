package pt.ua.deti.ies.kafka_message_broker;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "lab05", groupId = "kafka-listener-group")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }
}
