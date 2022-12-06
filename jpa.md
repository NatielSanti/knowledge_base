[Заглавная](README.md)

# ORM and JPA

+ [EntityManager и основные его функции](jpa.md#EntityManager-и-основные-его-функции)
+ [Spring JdbcTemplate](jpa.md#Spring-JdbcTemplate)
+ [Hibernate and JPA difference](jpa.md#Hibernate-and-JPA-difference)
+ [Hibernate cache](jpa.md#Hibernate-cache)
+ [JDBC, JPA, Hibernate, Spring Data JPA](#JDBC,-JPA,-Hibernate,-Spring-Data-JPA)
+ [Требования JPA к Entity классам](jpa.md#Требования-JPA-к-Entity-классам)
+ [Difference between save() and persist() in Hibernate](
jpa.md##Difference-between-save()-and-persist()-in-Hibernate)
+ [Advantages and disadvantages of hibernate compared to jdbc](
jpa.md#Advantages-and-disadvantages-of-hibernate-compared-to-jdbc)
+ [Database Transactions](jpa.md#Database-Transactions)
+ [Spring Transactions](jpa.md#Spring-Transactions)

[entity-state]:img/db/entity-state.png
[state-entity]:img/db/state_entity.PNG
[transaction-isolation-level]:img/db/transaction_isolation_level.PNG
[transaction-propagation]:img/db/transaction_propagation.PNG
[hybernate_cache]:img/db/hybernate_cache.png
[cglib-proxy-1]:img/db/cglib-proxy-1.png
[cglib-proxy-2]:img/db/cglib-proxy-2.png

[к оглавлению](#ORM-and-JPA)

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

## Hibernate cache
 - Методы `persist`, `merge` и `remove` `JPA EntityManager`
изменяют состояние данной сущности. Однако состояние сущности не
синхронизируется каждый раз, когда вызывается метод `EntityManager`.
На самом деле, изменения состояния синхронизируются только при выполнении метода
`flush` `EntityManager`.
Эта стратегия синхронизации кэша называется `write-behind` (отложенная запись).
Это нужно чтобы пакетно обрабатывать запросы в БД

### Кеш первого уровня

Кеш первого уровня всегда привязан к объекту сессии. 
Hibernate всегда по умолчанию использует этот кеш и его нельзя отключить.
```java
SharedDoc persistedDoc = (SharedDoc) session.load(SharedDoc.class, docId);
System.out.println(persistedDoc.getName());
user1.setDoc(persistedDoc);

persistedDoc = (SharedDoc) session.load(SharedDoc.class, docId);
System.out.println(persistedDoc.getName());
user2.setDoc(persistedDoc);
```
Один важный момент — при использовании метода `load()` Hibernate не выгружает из 
БД данные до тех пор пока они не потребуются. Иными словами — в момент, 
когда осуществляется первый вызов load, мы получаем прокси объект или сами данные 
в случае, если данные уже были в кеше сессии. 
Поэтому в коде присутствует `getName()` чтобы 100% вытянуть данные из БД.
   Тут также открывается прекрасная возможность для потенциальной оптимизации. 
В случае прокси объекта мы можем связать два объекта не делая запрос в базу, 
в отличии от метода `get()`. При использовании методов `save()`, `update()`, 
`saveOrUpdate()`, `load()`, `get()`, `list()`, `iterate()`, `scroll()` всегда 
будет задействован кеш первого уровня.

### Кеш второго уровня

Если кеш первого уровня привязан к объекту сессии, то кеш второго уровня привязан 
к объекту-фабрике сессий (Session Factory object). 
Что как бы подразумевает, что видимость этого кеша гораздо шире кеша первого уровня.
По-умолчанию отключен.

```xml
<property name="hibernate.cache.provider_class" value="net.sf.ehcache.hibernate.SingletonEhCacheProvider"/>
//или  в более старых версиях
//<property name="hibernate.cache.provider_class" value="org.hibernate.cache.EhCacheProvider"/>
<property name="hibernate.cache.use_second_level_cache" value="true"/>
```

На самом деле, хибернейт сам не реализует кеширование как таковое. 
А лишь предоставляет структуру для его реализации, поэтому подключить можно 
любую реализацию, которая соответствует спецификации нашего ORM фреймворка. 
Из популярных реализаций можна выделить следующие:
- EHCache
- OSCache
- SwarmCache
- JBoss TreeCache

чтение из кеша второго уровня происходит только в том случае, 
если нужный объект не был найден в кеше первого уровня.


### Кеш запросов

```java
Query query = session.createQuery("from SharedDoc doc where doc.name = :name");

SharedDoc persistedDoc = (SharedDoc) query.setParameter("name", "first").uniqueResult();
System.out.println(persistedDoc.getName());
user1.setDoc(persistedDoc);

persistedDoc = (SharedDoc) query.setParameter("name", "first").uniqueResult();
System.out.println(persistedDoc.getName());
user2.setDoc(persistedDoc);
```

Результаты такого рода запросов не сохраняются ни кешом первого, ни второго уровня. 
Это как раз то место, где можно использовать кеш запросов. 
Он тоже по умолчанию отключен. Для включения нужно добавить следующую строку в 
конфигурационный файл:
```xml
<property name="hibernate.cache.use_query_cache" value="true"/>
```
```java
Query query = session.createQuery("from SharedDoc doc where doc.name = :name");
query.setCacheable(true);
```
Кеш запросов похож на кеш второго уровня. Но в отличии от него — 
ключом к данным кеша выступает не идентификатор объекта, 
а совокупность параметров запроса. А сами данные — 
это идентификаторы объектов соответствующих критериям запроса. 
Таким образом, этот кеш рационально использовать с кешем второго уровня.

![icon][hibernate-cache]

### Cтратегии кеширования

Стратегии кеширования определяют поведения кеша в определенных ситуациях. 
Выделяют четыре группы:
- `Read-only`
- `Read-write`
- `Nonstrict-read-write`
Нужно обновлять, но редко и у нас не ожидается проблем с конкурентностью. Потоконебезопасный
- `Transactional` Стратегия транзакционного кэша обеспечивает поддержку поставщиков полностью 
транзакционного кэша, таких как `JBoss TreeCache`. Такой кеш можно использовать только в 
среде `JTA`, и вы должны указать `hibernate.transaction.manager_lookup_class`.

### Cache region

Регион или область — это логический разделитель памяти вашего кеша. 
Для каждого региона можна настроить свою политику кеширования 
(для EhCache в том же ehcache.xml). Если регион не указан, 
то используется регион по умолчанию, который имеет полное имя вашего класса для 
которого применяется кеширование. В коде выглядит так:

```java
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "STATIC_DATA")
```

А для кеша запросов так:

```java
query.setCacheRegion("STATIC_DATA");
//или в случае критерии
criteria.setCacheRegion("STATIC_DATA");
```

[ссылка](https://habr.com/ru/post/135176/)

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
   определяют запись этого Enity класса в базе данных

![icon][entity-state]
![icon][state-entity]

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

## Database Transactions

### ACID

**Атомарность (atomicity)** гарантирует, что никакая транзакция не будет зафиксирована в системе частично. 
Будут либо выполнены все её подоперации, либо не выполнено ни одной.

**Согласованность (consistency)**. Транзакция, достигающая своего нормального завершения и, тем самым, 
фиксирующая свои результаты, сохраняет согласованность базы данных.

**Изолированность (isolation)**. Во время выполнения транзакции параллельные транзакции не должны оказывать 
влияние на её результат.

**Долговечность (durability)**. Независимо от проблем на нижних уровнях (к примеру, обесточивание системы или 
сбои в оборудовании) изменения, сделанные успешно завершённой транзакцией, должны остаться сохранёнными после 
возвращения системы в работу.

### Transactions isolation levels

В порядке увеличения изолированности транзакций и, соответственно, надёжности работы с данными:

- Чтение неподтверждённых данных (грязное чтение) **(read uncommitted, dirty read)** — 
чтение незафиксированных изменений как своей транзакции, так и параллельных транзакций. Нет гарантии, что данные, 
изменённые другими транзакциями, не будут в любой момент изменены в результате их отката, 
поэтому такое чтение является потенциальным источником ошибок. Невозможны потерянные изменения, 
возможны неповторяемое чтение и фантомы.
- Чтение подтверждённых данных **(read committed)** — чтение всех изменений своей транзакции и 
зафиксированных изменений параллельных транзакций. Потерянные изменения и грязное чтение не допускается, 
возможны неповторяемое чтение и фантомы.
- Повторяемость чтения **(repeatable read, snapshot)** — чтение всех изменений своей транзакции, 
любые изменения, внесённые параллельными транзакциями после начала своей, недоступны. Потерянные изменения, 
грязное и неповторяемое чтение невозможны, возможны фантомы.
- Упорядочиваемость **(serializable)** — результат параллельного выполнения сериализуемой транзакции с другими 
транзакциями должен быть логически эквивалентен результату их какого-либо последовательного выполнения. 
Проблемы синхронизации не возникают.

![icon][transaction-isolation-level]

### Problems

При параллельном выполнении транзакций возможны следующие проблемы:

-Потерянное обновление **(lost update)** — при одновременном изменении одного блока данных разными транзакциями 
одно из изменений теряется;
-«Грязное» чтение **(dirty read)** — чтение данных, добавленных или изменённых транзакцией, 
которая впоследствии не подтвердится (откатится);
-Неповторяющееся чтение **(non-repeatable read)** — при повторном чтении в рамках одной транзакции ранее 
прочитанные данные оказываются изменёнными;
-Фантомное чтение **(phantom reads)** — одна транзакция в ходе своего выполнения несколько раз выбирает 
множество записей по одним и тем же критериям. Другая транзакция в интервалах между этими выборками добавляет 
или удаляет записи или изменяет столбцы некоторых записей, используемых в критериях выборки первой транзакции, 
и успешно заканчивается. В результате получится, что одни и те же выборки в первой транзакции дают разные множества 
записей. Предположим, имеется две транзакции, открытые различными приложениями, в которых выполнены следующие 
SQL-операторы:

### Transaction Propagation

![icon][transaction-propagation]

[к оглавлению](#ORM-and-JPA)

## JDBC, JPA, Hibernate, Spring Data JPA

#### JDBC 
– это мост между миром Java и миром баз данных.

#### JPA (Java Persistence API)
– это технология, которая позволяет удобно мапить объект Java и таблицу базы данных
- аннотации энтити класса( `@Column`, `@Id`, `@GeneratedValue`, `@ManyToMany`, `@Entity`, `@Table`)
- JPA – это просто спецификация, Вам нужен инструмент для ее реализации. 
Этим инструментом может быть Hibernate, TopLink, iBatis

#### Hibernate
- Одна из реализаций JPA
- EntityManager

#### Spring Data JPA
- По умолчанию Spring Data JPA использует Hibernate, в качестве ORM провайдера (чтобы выполнять запросы)
- Единственное, что нужно указать – это хост для Вашей базы данных, имя пользователя и пароль для доступа к ней. 
Spring Boot обеспечивает автоматическую настройку для всего подключения к базе. В том числе и пул соединений.

[к оглавлению](#ORM-and-JPA)

## Spring Transactions

### JDBC

Не имеет значения, используете ли вы аннотацию `@Transactional` от Spring, обычный 
`Hibernate`, `jOOQ` или любую другую библиотеку баз данных.

В конечном счёте, все они делают одно и то же - открывают и закрывают 
(назовём это "управлением") транзакции базы данных. 
Обычный код управления транзакциями `JDBC` выглядит следующим образом:

```java
import java.sql.Connection;

Connection connection = dataSource.getConnection(); // (1)

try (connection) {
    connection.setAutoCommit(false); // (2)
    // выполнить несколько SQL-запросов...
    connection.commit(); // (3)

} catch (SQLException e) {
    connection.rollback(); // (4)
}
```

1) Для запуска транзакций необходимо подключение к базе данных. 
`DriverManager.getConnection(url, user, password)` тоже подойдёт, 
хотя в большинстве корпоративных приложений вы будете иметь настроенный источник 
данных и получать соединения из него.
2) Это единственный способ "начать" транзакцию базы данных в Java, 
даже несмотря на то, что название может звучать немного странно. `setAutoCommit(true)` 
гарантирует, что каждый SQL-оператор будет автоматически завёрнут в собственную 
транзакцию, а `setAutoCommit(false)` - наоборот: Вы являетесь хозяином 
транзакции (транзакций), и Вам придётся начать вызывать `commit` и друзей. 
Обратите внимание, что флаг `autoCommit` действует в течение всего времени, 
пока ваше соединение открыто, что означает, что вам нужно вызвать метод только один раз, 
а не несколько.
3) Давайте зафиксируем нашу транзакцию...
4) Или откатим наши изменения, если произошло исключение.

Это происходит на базовом уровне вне зависимости от уровня абстракции, котоырй мы используем.

```java
@Transactional(
  propagation=TransactionDefinition.NESTED,
  isolation=TransactionDefinition.ISOLATION_READ_UNCOMMITTED
)
```
Преобразуется в нечто подобное 
```java
import java.sql.Connection;

// isolation=TransactionDefinition.ISOLATION_READ_UNCOMMITTED

connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED); // (1)

// propagation=TransactionDefinition.NESTED

Savepoint savePoint = connection.setSavepoint(); // (2)
...
connection.rollback(savePoint);
```

Вложенные транзакции в Spring - это просто точки сохранения JDBC / базы данных.

### Как работает управление транзакциями в Spring или Spring boot

#### 1) `TransactionTemplate`, либо непосредственно через `PlatformTransactionManager`

```java
@Service
public class UserService {

    @Autowired
    private TransactionTemplate template;

    public Long registerUser(User user) {
        Long id = template.execute(status ->  {
            // выполнить некоторый SQL, который, например,
            // вставляет пользователя в базу данных 
            // и возвращает автогенерированный идентификатор
          return id;
        });
    }
}
```

- Вам не нужно возиться с открытием и закрытием соединений с базой данных 
самостоятельно (`try-finally`). 
Вместо этого вы используете обратные вызовы транзакций.
- Вам также не нужно ловить `SQLExceptions`, поскольку Spring преобразует эти 
исключения в исключения времени выполнения (`runtime exceptions`)
- TransactionTemplate будет использовать TransactionManager внутри, 
который будет использовать источник данных.

#### 2) Декларативное управление транзакциями XML от Spring

```xml
<!-- транзакционный совет (advice) (что "происходит"; см. <aop:advisor/> бин ниже) -->
    <tx:advice id="txAdvice" transaction-manager="txManager">
        <!-- семантика транзакций... -->
        <tx:attributes>
            <!--все методы, начинающиеся с 'get', доступны только для чтения -->
            <tx:method name="get*" read-only="true"/>
            <!-- другие методы используют настройки транзакции по умолчанию (см. ниже) -->
            <tx:method name="*"/>
        </tx:attributes>
    </tx:advice>
```

Вы определяете advice `AOP` (Aspect Oriented Programming, 
аспектно-ориентированное программировие) с помощью приведенного выше блока XML, 
который затем можно применить к бин `UserService` следующим образом:

```xml
<aop:config>
    <aop:pointcut id="userServiceOperation" expression="execution(* x.y.service.UserService.*(..))"/>
    <aop:advisor advice-ref="txAdvice" pointcut-ref="userServiceOperation"/>
</aop:config>

<bean id="userService" class="x.y.service.UserService"/>
```
```java
public class UserService {

    public Long registerUser(User user) { 
        // выполняем SQL, который, например.
        // вставляет пользователя в базу данных 
        // и извлекает автогенерированный id
      return id;
    }
}
```

С точки зрения кода Java, этот декларативный подход к транзакциям выглядит намного проще, 
чем программный подход. Но он приводит к большому количеству сложного, 
многословного XML с конфигурациями указателей и советников (advisor).

#### 3) аннотацию `@Transactional` Spring (декларативное управление транзакциями, 
`Declarative Transaction Management`) 

- Убедитесь, что ваша Configuration Spring сопровождена аннотацией 
`@EnableTransactionManagement` Spring boot это будет сделано автоматически).

- Убедитесь, что вы указали менеджер транзакций в вашей Configuration Spring 
(это

И тогда Spring покажет себя достаточно умным, чтобы явно обрабатывать 
транзакции для вас: Любой публичный public метод бин, который вы 
сопровождаете аннотацией `@Transactional` , будет выполняться внутри 
транзакции базы данных (обратите внимание: есть некоторые подводные камни).

```java
@Configuration
@EnableTransactionManagement
public class MySpringConfig {

    @Bean
    public PlatformTransactionManager txManager() {
        return yourTxManager; // подробнее об этом позже
    }

}
```

### CGlib и JDK прокси - @Transactional под прикрытием

Теперь, когда вы используете `@Transactional` над бинами, 
Spring использует маленькую хитрость. Он не просто инстанцирует `UserService`, 
но и транзакционный прокси этого UserService.

Он делает это с помощью метода под названием `proxy-through-subclassing` с 
помощью библиотеки `Cglib`. Существуют и другие способы построения прокси 
(например, `Dynamic JDK proxies`), но пока оставим это на потом.

![icon][cglib-proxy-1]

- Открытие и закрытие соединений/транзакций с базой данных.
- А затем делегирование настоящему `UserService`, тому, который вы написали.
- А другие бины, такие как ваш `UserRestController`, 
никогда не узнают, что они разговаривают с прокси, а не с настоящим.

### Менеджер транзакций (например, PlatformTransactionManager)

```java
@Bean
public DataSource dataSource() {
    return new MysqlDataSource(); // (1)
} 
// Здесь вы создаёте источник данных, специфичный для базы данных или для пула соединений.
// В данном примере используется MySQL.

@Bean
public PlatformTransactionManager txManager() {
    return new DataSourceTransactionManager(dataSource()); // (2)
}
// Здесь вы создаёте свой менеджер транзакций, которому нужен источник данных,
// чтобы иметь возможность управлять транзакциями.
```

Всё просто. Все менеджеры транзакций имеют методы типа `doBegin` 
(для запуска транзакции) или `doCommit`, которые выглядят следующим образом - 
взяты прямо из исходного кода Spring с некоторым упрощением:

```java
public class DataSourceTransactionManager implements PlatformTransactionManager {

    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {
        Connection newCon = obtainDataSource().getConnection();
        // ...
        con.setAutoCommit(false);
        // Да, вот так.!
    }

    @Override
    protected void doCommit(DefaultTransactionStatus status) {
        // ...
        Connection connection = status.getTransaction().getConnectionHolder().getConnection();
        try {
            con.commit();
        } catch (SQLException ex) {
            throw new TransactionSystemException("Could not commit JDBC transaction", ex);
        }
    }
}
```

Таким образом, менеджер транзакций источника данных использует при управлении 
транзакциями точно такой же код, который вы видели в разделе JDBC.

![icon][cglib-proxy-2]


**Физические транзакции**: это ваши фактические транзакции `JDBC`.

**Логические транзакции**: это (потенциально вложенные) аннотированные 
`@Transactional` методы Spring.


### Самый распространенный "подводный камень" @Transactional

Кейс с вызовом одного транзакционного метода из транзакционного метода того класса.
Давайте вернемся к разделу "Прокси" этого руководства. 
Spring создает для вас транзакционный прокси UserService, 
но как только вы оказываетесь внутри класса UserService и вызываете другие 
внутренние методы, прокси больше не задействован. Это означает, 
что новой транзакции не будет

### Spring Boot

Однако единственное отличие Spring заключается в том, что он автоматически 
устанавливает аннотацию `@EnableTransactionManager` и создает `PlatformTransactionManager` 
для вас - с помощью автоконфигураций `JDBC`.

[ссылка](https://habr.com/ru/post/682362/)

[к оглавлению](#ORM-and-JPA)

[Заглавная](README.md)