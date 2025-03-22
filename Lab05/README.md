# Lab 5

## Ex1

Não esquecer de dar docker-compose up

Criar um consumer:
    
    ```bash
    docker exec lab05-kafka-1 bash  
    ```

    ```container
    kafka-console-consumer --topic lab05 --from-beginning --bootstrap-server kafka:9092
    ```
Criar um producer:

    ```bash
    docker exec -it lab05-kafka-1 bash 
    ```

    ```container
    kafka-console-producer --topic lab05 --broker-list kafka:9092
    ```
Quando criamos outro consumeras mensagens enviadas pelo producer são recebidas em ambos os consumers, mas as mensagens enviadas
antes de criarmos o novo consumer nao são recebidas por ele.

## Ex2

Comandos importantes:

Adicionar uma dependencia:
´´´bash
   poetry add <library>
´´´


Atualizar o ambiente:
´´´bash
poetry lock
´´´

´´´bash
poetry install
´´´

Se estiver a usar o python 3.12>= terei que colocar estas linhas de código no consumer:

´´´python

      m = types.ModuleType('kafka.vendor.six.moves', 'Mock module')
      setattr(m, 'range', range)
      sys.modules['kafka.vendor.six.moves'] = m

´´´


