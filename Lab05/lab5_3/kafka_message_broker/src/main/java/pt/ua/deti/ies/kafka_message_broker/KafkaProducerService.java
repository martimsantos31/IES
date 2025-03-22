package pt.ua.deti.ies.kafka_message_broker;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "lab05";

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(String message) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(new KafkaMessage(message));
            kafkaTemplate.send(TOPIC, jsonMessage);
            System.out.println("Sent message: " + jsonMessage);
        } catch (Exception e) {
            System.err.println("Failed to send message: " + e.getMessage());
        }
    }

    static class KafkaMessage {
        private String message;

        public KafkaMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}



