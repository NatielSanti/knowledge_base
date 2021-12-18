[Заглавная](README.md)

# ORM and JPA

+ [EntityManager и основные его функции](jpa.md#EntityManager-и-основные-его-функции)
+ [Spring JdbcTemplate](jpa.md#Spring-JdbcTemplate)
+ [Hibernate and JPA difference](jpa.md#Hibernate-and-JPA-difference)
+ [Требования JPA к Entity классам](jpa.md#Требования-JPA-к-Entity-классам)
+ [Difference between save() and persist() in Hibernate](jpa.md#Difference-between-save()-and-persist()-in-Hibernate)
+ [Advantages and disadvantages of hibernate compared to jdbc](jpa.md#Advantages-and-disadvantages-of-hibernate-compared-to-jdbc)

[entity-state]:img/entity-state.png

## EntityManager и основные его функции

EntityManager это интерфейс, который описывает API для всех основных операций над Enitity, получение данных и
других сущностей JPA. По сути главный API для работы с JPA. Основные операции:
1) Для операций над Entity: persist (добавление Entity под управление JPA), merge (обновление), remove (удаления),
   refresh (обновление данных), detach (удаление из управление JPA), lock (блокирование Enity от изменений в
   других thread),
2) Получение данных: find (поиск и получение Entity), createQuery, createNamedQuery, createNativeQuery, contains,
   createNamedStoredProcedureQuery, createStoredProcedureQuery
3) Получение других сущностей JPA: getTransaction, getEntityManagerFactory, getCriteriaBuilder, getMetamodel,
   getDelegate
4) Работа с EntityGraph: createEntityGraph, getEntityGraph
4) Общие операции над EntityManager или всеми Entities: close, isOpen, getProperties, setProperty, clear

[к оглавлению](#ORM-and-JPA)

## Spring JdbcTemplate
The Spring JDBC template is the primary API through which we can access database operations logic that we’re
interested in:

- Creation and closing of connections
- Executing statements and stored procedure calls
- Iterating over the ResultSet and returning results

[к оглавлению](#ORM-and-JPA)

## Hibernate and JPA difference
- **JPA** - Java Persistence API (JPA) defines the management of relational data in the Java applications.
- **Hibernate** - Hibernate is an Object-Relational Mapping (ORM) tool which is used to save the state of Java
  object into the database.
  It is one of the most frequently used JPA implementation.

[к оглавлению](#ORM-and-JPA)

## Требования JPA к Entity классам

1) Entity класс должен быть отмечен аннотацией Entity или описан в XML файле конфигурации JPA,
2) Entity класс должен содержать public или protected конструктор без аргументов (он также может иметь
   конструкторы с аргументами),
3) Entity класс должен быть классом верхнего уровня (top-level class),
4) Entity класс не может быть enum или интерфейсом,
5) Entity класс не может быть финальным классом (final class),
6) Entity класс не может содержать финальные поля или методы, если они участвуют в маппинге
   (persistent final methods or persistent final instance variables),
7) Если объект Entity класса будет передаваться по значению как отдельный объект (detached object),
   например через удаленный интерфейс (through a remote interface), он так же должен реализовывать Serializable
   интерфейс,
8) Поля Entity класс должны быть напрямую доступны только методам самого Entity класса и не должны быть
   напрямую доступны другим классам, использующим этот Entity. Такие классы должны обращаться только к методам
   (getter/setter методам или другим методам бизнес-логики в Entity классе),
9) Enity класс должен содержать первичный ключ, то есть атрибут или группу атрибутов которые уникально
   определяют запись этого Enity класса в базе данных,

![icon][entity-state]

[к оглавлению](#ORM-and-JPA)

##Difference between save() and persist() in Hibernate
#### `save()`	
- The return type of `save()` method is `Serializable`, returns generated id.	
- The `save()` method is only supported by Hibernate i.e hibernate specific.
- If the id generation type is AUTO, using the `save()` method we can pass identifiers in the entity.

#### `persist()`
- The return type of `persit()` method is void.
- The `persist()` method is supported by Hibernate as well as JPA `EntityManager` 
(In EntityManager `persist()` method has been defined).
- If the id generation type is AUTO and we pass identifier in `persist()` method, 
it will throw detached entity passed to persist exception.

[к оглавлению](#ORM-and-JPA)

##Advantages and disadvantages of hibernate compared to jdbc
#### Advantages of Hibernate over JDBC:

1. Hibernate is an ORM tool
2. Hibernate is an open source framework.
3. Better than JBDC.
4. Hibernate has an exception translator , 
which converts checked exceptions of JDBC in to unchecked exceptions of hibernate. 
So all exceptions in hibernate are unchecked exceptions and Because of this no need to handle exceptions explicitly.
5. Hibernate supports inheritance and polymorphism.
6. With hibernate we can manage the data stored across multiple tables, by applying relations(association)
7. Hibernate has its own query language called Hibernate Query Language. 
With this HQL hibernate became database independent.
8. Hibernate supports relationships like One-To-One, One-To-Many, Many-To-One ,Many-To-Many.
9. Hibernate has Caching mechanism. using this number of database hits will be reduced. 
so performance of an application will be increases.
10. Hibernate supports lot of databases.
11. Hibernate supported databases List.
12. Hibernate is a light weight framework because hibernate uses 
POJO classes for data transfer between application and database.
13. Hibernate has versioning and time stamp feature with this we can know how many number of times data is modified.
14. Hibernate also supports annotations along with XML.
15. Hibernate supports Lazy loading.
16. Hibernate is easy to learn it is developers friendly.
17. The architecture is layered to keep you isolated from having to know the underlying APIs.
18. Hibernate maintains database connection pool.
19. Hibernate  has Concurrency support.
20. Using Hibernate its Easy to maintain and it will increases productivity
    
####Disadvantages of Hibernate Compared to JDBC:
1. Hibernate is slow compared to JDBC because of generating many sql queries at 
run time but this is not considered as dis advantage in my view.
2. Below are some of the dis advantages but these are not applicable to small applications. 
But we have given some possible scenarios.

[к оглавлению](#ORM-and-JPA)

[Заглавная](README.md)