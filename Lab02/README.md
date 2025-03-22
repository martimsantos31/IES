# Lab2
## Ex1a
Para colocarmos um server a executar temos que primeiro instanciar um objeto do tipo Server e nos argumentos escolhemos
qual a porta que queremos colocar o server a rodar, 

De seguida, é aconselhável colocarmos a inicialização dentro de um try catch, para que se aconteça algum erro pelo menos
temos o log do erro e conseguimos perceber o que se passa, os comandos importantes são:

- start() - Inicia o sevidor e a partir deste momento o server começa a 'observar' os requests HTTP que chegam á porta.
- join() - Faz com que a thread principal do programa espere até que o server seja interrompido manualmente.
- setHandler() - Este é dos mais importantes e necessários para que consigamos que o sistema funcione bem, serve para 
configurar o objeto responsável por lidar com as requests HTTP que o servidor recebe.

O objeto Handler pode ser uma classe que nós modificamos ou podemos usar sem modificações as classes do jetty.

## Ex1b
Para fazer este exercicio depois de pesquisar encontrei um método muito útil, o:

- getParameter() - O argumento é o parametro que nós queremos ir buscar ao url e basicamente vamos buscar informações que
podem ser importantes para resolver o problema com que estamos a lidar. 

## Ex2

IntelliJ wizzard é as ajudas que o intelliJ nos dá, como por exemplo a criar projetos etc...
Notas sobre o tomcat, usar a versão 10 quando estamos a usar o jakarta 11, pelo menos usei esse e não estava a funcionar
com tomcat 9.
Reparar se a versão do java que o container do docker está a usar é a mesma do projeto maven.
Erro 505 quando acedemos ao localhost deu-me por causa destes dois pontos acima.

## Ex3

Criar sempre através do IntelliJ o projeto SpringBoot
Comando para rodar o projeto:
- ./mvnw spring-boot:run

* Se a porta tiver em uso, verificamos qual o processo está a utilizá-la com:
- sudo lsof -i :8080
* E de seguida encerramos o mesmo com:
- sudo kill -9 PID
* Sendo que em PID substituimos pelo PID do processo.

* Usamos a tag @Controller para identificar que o nosso handler de HTTP requests é o nosso controller

* A tag @GetMapping assegura que os HTTP GET requests são mapeados para o "url" que passamos como argumento

* Aplicações a usar springboot, o html fica dentro da pasta src/main/resources/templates

* Se algo estiver a dar errado e aparentemente estiver tudo correto tentar instalar o thymeleaf
* A página index é um recurso e apenas pelo nome é detetada como a página home do nosso projeto
* Usamos um record quando queremos evitar repetições de código e quando precisamos de uma classe imutavel
* Neste caso do exercício b usamos um rest controller porque o nosso objetivo é que ele responda aos request com JSON ao invés
de html

## Ex4

* Não há muito a adicionar no exercicio 4 sendo que utilizei basicamente os mesmos conhecimentos que no exercicio 3



