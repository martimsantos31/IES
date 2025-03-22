from confluent_kafka import Producer
import json

def fibonacci_until(n):
    a, b = 0, 1
    while a < n:
        yield a
        a, b = b, a + b

producer = Producer({'bootstrap.servers': 'localhost:29092'})

def delivery_report(err, msg):
    if err is not None:
        print(f"Message delivery failed: {err}")
    else:
        print(f"Message delivered to {msg.topic()} [{msg.partition()}] @ offset {msg.offset()}")

def send_fibonacci_message(n):
    for i in fibonacci_until(n):
        message = {'nMec': n, 'generatedNumber': i, 'type': 'fibonacci'}
        producer.produce(
            'lab05',
            key=str(n),
            value=json.dumps(message),
            callback=delivery_report
        )
        producer.flush()

send_fibonacci_message(113786)
