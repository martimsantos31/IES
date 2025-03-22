from confluent_kafka import Consumer, KafkaException
import json


consumer_config = {
    'bootstrap.servers': 'localhost:29092',
    'group.id': 'lab05-group',
    'auto.offset.reset': 'earliest',
}


consumer = Consumer(consumer_config)


consumer.subscribe(['lab05'])

try:
    print("A consumir mensagens do tópico 'lab05'...")
    while True:
        message = consumer.poll(timeout=1.0)

        if message is None:
            continue
        if message.error():
            if message.error().code() == KafkaException._PARTITION_EOF:
                print(f"Fim da partição {message.topic()} [{message.partition()}] com offset {message.offset()}")
            else:
                raise KafkaException(message.error())
        else:
            print(f"Received message: {json.loads(message.value().decode('utf-8'))}")

except KeyboardInterrupt:
    print("Encerrando consumidor...")

finally:
    consumer.close()
