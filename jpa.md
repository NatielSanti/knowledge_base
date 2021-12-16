[Заглавная](README.md)

# ORM and JPA

+ [EntityManager и основные его функции](jpa.md#EntityManager-и-основные-его-функции)
+ [Spring JdbcTemplate](jpa.md#Spring-JdbcTemplate)
+ [Hibernate and JPA difference](jpa.md#Hibernate-and-JPA-difference)
+ [Требования JPA к Entity классам](jpa.md#Требования-JPA-к-Entity-классам)

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

[Заглавная](README.md)