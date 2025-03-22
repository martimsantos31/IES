# Lab 3
## Ex1

* Quando criamos uma tabela user e estamos a usar H2 temos que renomear a entidade pois já existe uma tabela predefinida
com esse nome. Para isso basta adicionar a anotação @Table(name="newname") à entidade User.

* Quando numa entidade queremos que alguns dos campos sejam de preenchimento obrigatório para criar essa mesma entidade colocamos a taga @NotBlank.

* Os projetos spring seguem o padrão MVC, neste caso temos User o model, o UserController a representar o controller e também as views quando está a retornar os templates criados por nós.

* Para nos facilitar a vida na interface UserRepositório implementámos um class do spring chamada CrudRepository para nos facilitar a criação das operações CRUD para a entidade User.

## Ex2

* Para tornar uma coluna unique e nullable=false basta adicionar a tag @Column(unique=true, nullable=false) à coluna que queremos que tenha essas propriedades.

* É sempre bom termos o cuidado de separar tudo por pastas, por exemplo, as entidades numa pasta, os controllers noutra, etc.

* Ter sempre atenção á tag @RequestBody quando estamos a fazer um POST request, pois é com ela que conseguimos obter os dados que queremos inserir na base de dados.

## Ex3

* Pensar sempre em fazer a separação de responsabilidades entre layers, por exemplo, a camada de serviço é responsável por toda a lógica de negócio.

* Quando estamos a fazer o dockerfile temos que ter atenção ao nosso pom principalmente quando estamos a identificar qual é o nosso main file para depois 
enquanto estivermos a dockerizar a aplicação não termos problemas.

* Quando estamos a fazer um docker-compose temos que ter atenção ao nome do serviço que estamos a criar, pois é com esse nome que vamos referenciar o serviço no docker-compose.

* Comando importantes:
    * docker-compose up -d
    * docker-compose down
    * docker-compose logs
    * docker-compose ps
    * docker-compose exec <servicename> bash



