[Заглавная](README.md)

# Architect

+ [IaaS, SaaS, PaaS](architect.md#IaaS,-SaaS,-PaaS)
+ [Fault tolerant microservice](architect.md#Fault-tolerant-microservice)
+ [Event Sourcing](architect.md#Event-Sourcing)
+ [DDD](architect.md#DDD)
+ [Fault tolerant microservice](architect.md#Fault-tolerant-microservice)
+ [MS vs Monolith](architect.md#MS-vs-Monolith)
+ [MS vs SOA](architect.md#SOA-vs-MSA)
+ [Spring Cloud](architect.md#Spring-Cloud)
+ [Patterns for distributed transactions](architect.md#Patterns-for-distributed-transactions)
+ [Kafka](architect.md#Kafka)
+ [RabbitMQ vs Kafka vs ActiveMQ](architect.md#RabbitMQ-vs-Kafka-vs-ActiveMQ)
+ [UML](architect.md#UML)
+ [Software Architectural Patterns](architect.md#Software-Architectural-Patterns)
+ [Microservice Patterns](architect.md#Microservice-Patterns)
+ [Задержки](architect.md#Задержки)
+ [System Design Template](architect.md#System-Design-Template)
+ [Horizontal vs Vertical Scaling](architect.md#Horizontal-vs-Vertical-Scaling)
+ [Consistent hashing](architect.md#Consistent-hashing)
+ [Distributed cache](architect.md#Distributed-cache)
+ [Design API](architect.md#Design-API)
+ [Оценка ёмкости хранилищ](architect.md#Оценка-ёмкости-хранилищ)
+ [Publisher Subscriber Model](architect.md#Publisher-Subscriber-Model)

[example-1]:img/dist_systems/example-1.png
[2pc-ok]:img/dist_systems/2pc-ok.png
[2pc-fail]:img/dist_systems/2pc-fail.png
[saga-ok]:img/dist_systems/saga-ok.png
[saga-fail]:img/dist_systems/saga-fail.png
[soa-vs-msa]:img/dist_systems/soa-vs-msa.png
[spring-cloud]:img/spring/spring-cloud.png

[kube1]:img/dist_systems/kubernates/node-pod.png
[kube2]:img/dist_systems/kubernates/pod-types.png
[kube3]:img/dist_systems/kubernates/selector.png
[kube4]:img/dist_systems/kubernates/selector2.png
[kube5]:img/dist_systems/kubernates/service.png
[kube6]:img/dist_systems/kubernates/K8scheatsheet.jpg

[saaspaas]:img/dist_systems/saaspaas.png
[iaassaaspaas]:img/ops/iaassaaspaas.png
[aggregate]:img/ops/aggregate.png
[cqrs]:img/ops/cqrs.png
[eventsourcing_cqrs]:img/ops/eventsourcing_cqrs.png
[domain]:img/ops/domain.png

[kafka-storage-1]:img/architect/kafka-storage-1.png
[kafka-storage-2]:img/architect/kafka-storage-2.png

[uml]:img/architect/uml/uml.png
[uml-relations]:img/architect/uml/uml-relations.png
[uml-str-class]:img/architect/uml/uml-str-class.png
[uml-str-component]:img/architect/uml/uml-str-component.png
[uml-str-composite]:img/architect/uml/uml-str-composite.png
[uml-str-deployment]:img/architect/uml/uml-str-deployment.png
[uml-str-object]:img/architect/uml/uml-str-object.png
[uml-str-package]:img/architect/uml/uml-str-package.png
[uml-str-profile]:img/architect/uml/uml-str-profile.png
[uml-beh-activity]:img/architect/uml/uml-beh-activity.png
[uml-beh-communication]:img/architect/uml/uml-beh-communication.png
[uml-beh-interaction]:img/architect/uml/uml-beh-interaction.png
[uml-beh-sequence]:img/architect/uml/uml-beh-sequence.png
[uml-beh-state-machine]:img/architect/uml/uml-beh-state-machine.png
[uml-beh-timing]:img/architect/uml/uml-beh-timing.png
[uml-beh-use-case]:img/architect/uml/uml-beh-use-case.png

[10-arch-patterns]:img/architect/10-arch-patterns.png

[decomposite-by-business]:img/architect/microservice-pattern/decomposite-by-business.png
[decomposite-by-subdomain]:img/architect/microservice-pattern/decomposite-by-subdomain.png
[refactoring-strangler]:img/architect/microservice-pattern/refactoring-strangler.png
[refactoring-anti-corruption]:img/architect/microservice-pattern/refactoring-anti-corruption.png
[db-per-service]:img/architect/microservice-pattern/db-per-service.png
[db-api-composition]:img/architect/microservice-pattern/db-api-composition.png
[db-cqrs-1]:img/architect/microservice-pattern/db-cqrs-1.png
[db-cqrs-2]:img/architect/microservice-pattern/db-cqrs-2.png
[db-event-sourcing]:img/architect/microservice-pattern/db-event-sourcing.png
[db-saga]:img/architect/microservice-pattern/db-saga.png
[comm-api-gateway]:img/architect/microservice-pattern/comm-api-gateway.png
[comm-back-for-front]:img/architect/microservice-pattern/comm-back-for-front.png
[ui-client-build]:img/architect/microservice-pattern/ui-client-build.png
[ui-server-build]:img/architect/microservice-pattern/ui-server-build.png
[discovery-client]:img/architect/microservice-pattern/discovery-client.png
[discovery-server]:img/architect/microservice-pattern/discovery-server.png
[deploy-blue-green]:img/architect/microservice-pattern/deploy-blue-green.png
[deploy-host]:img/architect/microservice-pattern/deploy-host.png
[deploy-canary-release]:img/architect/microservice-pattern/deploy-canary-release.png
[fault-tolerance-circuit]:img/architect/microservice-pattern/fault-tolerance-circuit.png
[fault-tolerance-bulkhead-1]:img/architect/microservice-pattern/fault-tolerance-bulkhead-1.png
[fault-tolerance-bulkhead-2]:img/architect/microservice-pattern/fault-tolerance-bulkhead-2.png
[fault-service-mesh]:img/architect/microservice-pattern/fault-service-mesh.png
[monitoring-log]:img/architect/microservice-pattern/monitoring-log.png
[monitoring-tracing]:img/architect/microservice-pattern/monitoring-tracing.png
[monitoring-health-check]:img/architect/microservice-pattern/monitoring-health-check.png
[other-ambassador]:img/architect/microservice-pattern/other-ambassador.png
[other-sidecar]:img/architect/microservice-pattern/other-sidecar.png
[other-customer-driven-contract]:img/architect/microservice-pattern/other-customer-driven-contract.png
[other-external-conf]:img/architect/microservice-pattern/other-external-conf.png

[Continuous-integration-vs-delivery-vs-deployment.png]:img/ops/Continuous-integration-vs-delivery-vs-deployment.png
[arch-patterns]:img/architect/arch_patterns.jpg
[micro-patterns]:img/architect/10-arch-patterns.png

[youtube-capacity]:img/architect/youtube-capacity.png
[processor-time]:img/architect/processor-time.png

[к оглавлению](architect.md#Architect)

## IaaS, SaaS, PaaS

IaaS, PaaS или SaaS — это модели предоставления облачных сервисов.
То, как они соотносятся друг с другом,
часто изображают в виде пирамиды с разным уровнем контроля информации.
Вершина — это конечный пользователь, который работает с личными данными,
«завернутыми» в виде программы или сервиса с удобным интерфейсом.
Программа или сервис разворачиваются на некой технологической платформе, это второй уровень пирамиды.
Наконец, ее основа — это инфраструктура: виртуальные серверы, вычислительные мощности,
накопители и каналы связи.

![icon][iaassaaspaas]

![icon][saaspaas]

#### SaaS (Software-as-a-Service).
Эта облачная модель — самая распространенная. Программы и сервисы разрабатывает и обслуживает провайдер,
размещает их в облаке и предлагает конечному пользователю через браузер или приложение на его ПК.
Клиент лишь вносит абонплату (или пользуется сервисом бесплатно),
обновлением и технической поддержкой программ занимается провайдер.
SaaS-сервисы могут предоставлять место для хранения файлов (Dropbox),
офисный пакет документов для работы (Google Doc, Microsoft Office 365),
помогать организовывать фотографии (Flickr) или общаться с другими людьми (Facebook).
Основной клиент SaaS-сервисов — обычный пользователь.

#### PaaS (Platform-as-a-Service).
В этом случае облачный провайдер предоставляет доступ к операционным системам,
средствам разработки и тестирования, системам управления базами данных.
Провайдер контролирует не только серверы, системы хранения данных и вычислительные мощности,
но также предлагает пользователю на выбор определенные платформы и средства управления ими.
Примеры PaaS: Google App Engine, IBM Bluemix, Microsoft Azure, VMWare Cloud Foundry.
Пользователи PaaS-сервисов — это разработчики ПО.

#### IaaS (Infrastructure-as-a-Service).
При этой модели потребитель получает информационно-технологические ресурсы —
виртуальные серверы с определенной вычислительной мощностью и объемами памяти.
Всем «железом» занимается провайдер. Он устанавливает на него ПО для создания виртуальных машин,
но не занимается установкой и поддержкой ПО пользователя.
Провайдер контролирует только физическую и виртуальную инфраструктуру.
Примеры IaaS: IBM Softlayer, Hetzner Cloud, Microsoft Azure, Amazon EC2, GigaCloud.
Клиенты IaaS — это системные администраторы компаний.

С точки зрения конечного пользователя SaaS — самая понятная и удобная облачная модель.
Часто проще и эффективнее использовать готовый SaaS-сервис,
который уже соответствует определенным требованиям. Но готовые решения не всегда существуют,
и в таком случае модели PaaS и IaaS — незаменимы.

[ссылка](https://gigacloud.ua/ru/blog/navchannja/hmarna-piramida-iaas-paas-i-saas#:~:text=IaaS%2C%20PaaS%20или%20SaaS%20—%20это,или%20сервиса%20с%20удобным%20интерфейсом.)

[к оглавлению](architect.md#Architect)

## MS vs Monolith

#### MS Advantages:
1) The microservice architecture is easier to reason about/design for a complicated system.
2) They allow new members to train for shorter periods and have less context before touching a system.
3) Deployments are fluid and continuous for each service.
4) They allow decoupling service logic on the basis of business responsibility
5) They are more available as a single service having a bug does not bring down the entire system.
   This is called a single point of failure.
6) Individual services can be written in different languages.
7) The developer teams can talk to each other through API sheets instead of working on the same repository,
   which requires conflict resolution.
8) New services can be tested easily and individually.
   The testing structure is close to unit testing compared to a monolith.

#### Microservices are at a disadvantage to Monoliths in some cases. Monoliths are favorable when:
1) The technical/developer team is very small
2) The service is simple to think of as a whole.
3) The service requires very high efficiency, where network calls are avoided as much as possible.
4) All developers must have context of all services.

[к оглавлению](architect.md#Architect)

## Fault tolerant microservice
- Timeouts (сделай за 3 секунды. Может отправить запрос дальше в цепочку сервисов и ошибка будет только на обратнмо пути)
- Retries (3 раза и всё)
- Circuit Breaker (много ошибок - подняли новый)
- Deadlines (сделай до 15-00, не успел - верни ошибку сразу)
- Rate limiters (1000 запросов в секунду)

[к оглавлению](architect.md#Architect)


## DDD

Предметно-ориентированное проектирование (DDD), описанное в книге 
Эрика Эванса `Domain-Driven Design`, — это более узкая разновидность ООП, 
предназначенная для разработки сложной бизнес-логики.

В DDD есть также тактические шаблоны, которые служат строительными блоками для доменных моделей. 
Каждый шаблон представляет собой роль, которую класс играет в доменной модели, 
и описывает характеристики этого класса. Разработчики широко применяют следующие строительные блоки.

- `Сущность` — объект, обладающий устойчивой идентичностью. 
Две сущности, чьи атрибуты имеют одинаковые значения, — это все равно разные объекты. 
В приложении Java ЕЕ классы, которые сохраняются с помощью аннотации `@Entity` из JPA, 
обычно представляют собой сущности DDD.
- `Объект значений` — объект, представляющий собой набор значений. 
Два объекта значений с одинаковыми атрибутами взаимозаменяемы. Примером таких объектов может 
служить класс DTO, который состоит из валюты и суммы.
- `Фабрика` — объект или метод, реализующий логику создания объектов, 
которую ввиду ее сложности не следует размещать прямо в конструкторе. 
Фабрика также может скрывать конкретные классы, экземпляры которых создает. 
Она реализуется в виде статического метода или класса.
- `Репозиторий` — объект, предоставляющий доступ к постоянным сущностям и инкапсулирующий 
механизм доступа к базе данных.
- `Сервис` — объект, реализующий бизнес-логику, которой не место внутри сущности или объекта значений.

![icon][domain]
![icon][aggregate]

[к оглавлению](architect.md#Architect)

## Enterprise integration patterns

### Integration Styles

- File Transfer
- Shared Database
- Remote Procedure Invocation
- Messaging
  - Message Channel
  - Message
  - Pipes and Filters
  - Message Router
  - Message Translator
  - Message Endpoint

[ссылка](#https://www.enterpriseintegrationpatterns.com/patterns/messaging/toc.html)

[к оглавлению](architect.md#Architect)

## MS vs Monolith

#### MS Advantages:
1) The microservice architecture is easier to reason about/design for a complicated system.
2) They allow new members to train for shorter periods and have less context before touching a system.
3) Deployments are fluid and continuous for each service.
4) They allow decoupling service logic on the basis of business responsibility
5) They are more available as a single service having a bug does not bring down the entire system.
   This is called a single point of failure.
6) Individual services can be written in different languages.
7) The developer teams can talk to each other through API sheets instead of working on the same repository,
   which requires conflict resolution.
8) New services can be tested easily and individually.
   The testing structure is close to unit testing compared to a monolith.

#### Microservices are at a disadvantage to Monoliths in some cases. Monoliths are favorable when:
1) The technical/developer team is very small
2) The service is simple to think of as a whole.
3) The service requires very high efficiency, where network calls are avoided as much as possible.
4) All developers must have context of all services.

[к оглавлению](architect.md#Architect)

##SOA vs MSA

![icon][soa-vs-msa]

[к оглавлению](architect.md#Architect)

## Spring Cloud

![icon][spring-cloud]

[к оглавлению](architect.md#Architect)

## Patterns for distributed transactions

In a monolithic system, we have a database system to ensure ACIDity.
We now need to clarify the following key problems.

![icon][example-1]

#### Two-phase commit (2pc) pattern

2pc is widely used in database systems. For some situations, you can use 2pc for microservices. Just be careful;
not all situations suit 2pc and, in fact, 2pc is considered impractical within a microservice architecture
(explained below).

**So what is a two-phase commit?**

As its name hints, 2pc has two phases: A prepare phase and a commit phase. In the prepare phase,
all microservices will be asked to prepare for some data change that could be done atomically.
Once all microservices are prepared, the commit phase will ask all the microservices to make the actual changes.

Normally, there needs to be a global coordinator to maintain the lifecycle of the transaction,
and the coordinator will need to call the microservices in the prepare and commit phases.

Here is a 2pc implementation for the customer order example:

![icon][2pc-ok]

In the example above, when a user sends a put order request,
the `Coordinator` will first create a global transaction with all the context information.
It will then tell CustomerMicroservice to prepare for updating a customer fund with the created transaction.
The `CustomerMicroservice` will then check, for example,
if the customer has enough funds to proceed with the transaction.
Once `CustomerMicroservice` is OK to perform the change,
it will lock down the object from further changes and tell the `Coordinator` that it is prepared.
The same thing happens while creating the order in the `OrderMicroservice`.
Once the `Coordinator` has confirmed all microservices are ready to apply their changes,
it will then ask them to apply their changes by requesting a commit with the transaction.
At this point, all objects will be unlocked.

If at any point a single microservice fails to prepare,
the `Coordinator` will abort the transaction and begin the rollback process.
Here is a diagram of a 2pc rollback for the customer order example:

![icon][2pc-fail]

In the above example, the `CustomerMicroservice` failed to prepare for some reason,
but the `OrderMicroservice` has replied that it is prepared to create the order.
The `Coordinator` will request an abort on the `OrderMicroservice` with the transaction and the
`OrderMicroservice` will then roll back any changes made and unlock the database objects.

**Benefits of using 2pc**

2pc is a very strong consistency protocol. First,
the prepare and commit phases guarantee that the transaction is atomic.
The transaction will end with either all microservices returning successfully or all microservices have nothing changed.  
Secondly, 2pc allows read-write isolation.
This means the changes on a field are not visible until the coordinator commits the changes.

**Disadvantages of using 2pc**

While 2pc has solved the problem,
it is not really recommended for many microservice-based systems because 2pc is synchronous (blocking).
The protocol will need to lock the object that will be changed before the transaction completes.
In the example above, if a customer places an order, the "fund" field will be locked for the customer.
This prevents the customer from applying new orders.
This makes sense because if a "prepared" object changed after it claims it is "prepared,"
then the commit phase could possibly not work.

This is not good. In a database system, transactions tend to be fast—normally within 50 ms.
However, microservices have long delays with RPC calls,
especially when integrating with external services such as a payment service.
The lock could become a system performance bottleneck. Also,
it is possible to have two transactions mutually lock each other (deadlock)
when each transaction requests a lock on a resource the other requires.

#### Saga pattern

The Saga pattern is another widely used pattern for distributed transactions.
It is different from 2pc, which is synchronous. The Saga pattern is asynchronous and reactive.
In a Saga pattern, the distributed transaction is fulfilled by asynchronous local
transactions on all related microservices. The microservices communicate with each other through an event bus.

Here is a diagram of the Saga pattern for the customer order example:

![icon][saga-ok]

In the example above, the `OrderMicroservice` receives a request to place an order.
It first starts a local transaction to create an order and then emits an `OrderCreated` event.
The `CustomerMicroservice` listens for this event and updates a customer fund once the event is received.
If a deduction is successfully made from a fund, a `CustomerFundUpdated` event will then be emitted,
which in this example means the end of the transaction.
If any microservice fails to complete its local transaction,
the other microservices will run compensation transactions to rollback the changes.
Here is a diagram of the Saga pattern for a compensation transaction:

![icon][saga-fail]

In the above example, the UpdateCustomerFund failed for some reason and it then emitted a
`CustomerFundUpdateFailed` event.
The `OrderMicroservice` listens for the event and start its compensation
transaction to revert the order that was created.

**Advantages of the Saga pattern**

One big advantage of the Saga pattern is its support for long-lived transactions.
Because each microservice focuses only on its own local atomic transaction,
other microservices are not blocked if a microservice is running for a long time.
This also allows transactions to continue waiting for user input.
Also, because all local transactions are happening in parallel, there is no lock on any object.

**Disadvantages of the Saga pattern**

The Saga pattern is difficult to debug, especially when many microservices are involved.
Also, the event messages could become difficult to maintain if the system gets complex.
Another disadvantage of the Saga pattern is it does not have read isolation.
For example, the customer could see the order being created, but in the next second,
the order is removed due to a compensation transaction.

**Adding a process manager**

To address the complexity issue of the Saga pattern, it is quite normal to add a process manager as an orchestrator.
The process manager is responsible for listening to events and triggering endpoints.

[к оглавлению](architect.md#Architect)

## Kafka

- Распределённая
- Отказоустойчивая
- Высокая доступность (партиция упала, но данные всё равно доступны в других партициях)
- Согласованность и надёжность данных (CA из CAP теоремы)
- Высокая производительность (1000000 сообщенйи в секунду)
- Горизонтальная масштабируемость
- Интегрируемость (много систем имеют интеграцию с кафкой)

### Решаемая задача

1) Обмен сообщений, особенно когда нужно чтобы одинаковые сообщения читали разные приложения
2) Потоковая передача данных
3) Ведение журналов

Решает задачу передачи данных от продюсеров к потребителям и 
распределению их между потребителями по какому-либо признаку.
Но тут возникают сложности:

- Надёжность и гарантия доставки данных
- Подключение новых потребителей
- Отправители знают потребителей, это не всегда нужно
- Интеграция разных технических стеков (потребитель на джава, продюсер на питоне)

### Сущности Кафки

- **Broker** (Кафка сервис, Кафка нод)
    - Приём, хранение и выдача сообщений
    - Брокеры объединяются в Кафка Кластер
- **Zookeeper**
    - `Конфигурация`, `состояние кластера Кафка` и `адресная книга`. Координатор
    - Быстрое чтение и медленная запись
    - Один из брокеров становится `Контроллером` в мастер-слейв системе. 
  Обеспечивает консистентность данных
- **Message** (Record)
    - Key/Value pair + Заголовок + Временной штапм
- **Topic/Partition**
    - Data Stream
    - `FIFO` на уровне партиций. Считывание происходит в том же порядке, 
    в котором происходила запись 
    (Например, RabbitMQ очередность не гарантирует из-за наличия приоритетности)
    - Топик делится на Партиции
    - Топик распределяется по брокерам в клстере
    - Кафка не гарантирует, что партиции будут равномерно распределены по брокерам
    - Иногда балансировку приходится производить вручную, 
  так как топики распределяются только по объёму
- **Producer**
    - Гарантия доставки `Acks` 
      - 0 (подтверждения не нужно)
      - 1 (нужно только от лидера)
      - -1(all) подтверждение требуется от всех insync реплик и от лидера
    - Семантика доставки
      - Сообщение отправилось НЕ БОЛЕЕ 1 РАЗА
      - Сообщение отправилось НЕ МЕНЕЕ 1 РАЗА
      - Сообщение отправилось 1 РАЗ (идемпотентность)
- **Consumer**
    - команда `poll` позволяет получить пачку сообщений из партиции. не по одному
    - Consumer group либо один потребитель подключаются к брокерам.
      Потребителей в группе столько же сколько партиций (лидеров) для чтения, либо меньше
      (тогда один потребитель будет считывать данные из двух партиций, например)
    - `Kafka Consumer Offset` - последнее считаннео группой сообщение. `__consumer_offset` - 
  отдельный топик для хранения оффсетов групп на стороне брокера.
  
      ![icon][kafka-storage-2]

    - Типы коммитов 
        - **auto commit** (at most once) может терять коммиты
        - **manual commit** (at least once) может дублировать коммиты
        - **custom offset management** (ровно 1 раз)
    - `offset.retention.minutes` (7 дней по умолчанию), если группа не считывала сообщения с топика 7 дней 
  оффсет сбрасывается
    - `auto.offset.reset` либо earliest/latest - при сброке консьюмер группа начинает либо с 
  самого раннего сообщения в топике, либо с самого последнего

      
### Хранение данных

- Хранятся в папке .logs/A-0 (где А - название топика, а 0 - партиция)
- В папке A-0 лежит три файла:
  - 00.log - сами данные хранятся как записи из 4 полей - 
  `offset`, `position`, `timestamp`, `message`
  
  ![icon][kafka-storage-1]

  - 00.index - маппинг offset на position
  - 00.timeindex - маппинг timestamp на offset
- По-умолчанию лимит на объём одного файла - 1 Гб. Сегментация. 
Timestamp сегмента это максимальный timestamp сообщений в этом файле
- Операция удаления данных из топика не поддерживается! Но можно использовать TTL 
(Time to Live) и старые сегменты партиций могут удаляться. Если мы замечаем, 
что сегменты не удаляются, значит там есть события с высоким timestamp/

### Репликация данных

- Параметр `replication-factor` указывает сколько минимум копий 
партиций топика должно быть в разных брокерах.
- Главная реплика может быть впереди по данным от копий-реплик. Если брокер с партицией где 
находится главная реплика упадёт - есть риск потерять данные
- Назначением реплики-лидера занимается Контроллер
- Операции чтения-записи производятся только с Главной репликой. Может произойти ситуация, 
когда все репликилидеры находятся в одном брокере, 
тогда происходит неравномерное распределение нагрузки
- В случае падения лидера, новым лидером становится самая полная реплика - ISR (insync replica). 
В ISR реплики данные записываются сразу, а не периодически.
- `min.insync.replica` обычно ставят на 1 меньше чем `replication-factor`

### Алгоритм отправки от продюсера

1) Получение метаданных от Zookeeper (состояние кластера, где брокеры, 
где топики, какие реплики являются лидерами)
2) Сериализация сообщения
3) Определение партиции
    - Точная партиция (explict partition)
    - По-очереди (round-robin)
    - По ключу (key_hash % n)
4) Компрессация сообщения. Сжатие при помощи кодеков
5) Сообщения объединяются в батчи (`batch.size` - мин размер батча до отправки, 
`linger.mc` - сколько максимум мы ждём до формирования батча)

### Kafka performance

- Масштабируемость
- Последовательное чтение и запись (на диск запись происходит последовательно, а знаичт быстро)
- **Zero-copy** - данные копируются из памяти сразу в сокет клиента
- Множество настроек

[к оглавлению](architect.md#Architect)

## RabbitMQ vs Kafka vs ActiveMQ

🔹𝗣𝗲𝗿𝗳𝗼𝗿𝗺𝗮𝗻𝗰𝗲 𝗮𝗻𝗱 𝗦𝗰𝗮𝗹𝗮𝗯𝗶𝗹𝗶𝘁𝘆: Kafka is designed for high throughput and horizontal scalability, 
making it well-suited for handling large volumes of data. RabbitMQ and ActiveMQ both offer high performance, 
but Kafka generally outperforms them in terms of throughput, particularly in scenarios with high data volume.

🔹𝗠𝗲𝘀𝘀𝗮𝗴𝗲 𝗣𝗿𝗶𝗼𝗿𝗶𝘁𝘆: RabbitMQ and ActiveMQ support message prioritization, allowing messages with higher priority to be 
processed before those with lower priority. Kafka does not have built-in message priority support.

🔹𝗠𝗲𝘀𝘀𝗮𝗴𝗲 𝗢𝗿𝗱𝗲𝗿𝗶𝗻𝗴: RabbitMQ and ActiveMQ guarantee message ordering within a single queue or topic, respectively. 
Kafka ensures message ordering within a partition but not across partitions within a topic.

🔹𝗠𝗲𝘀𝘀𝗮𝗴𝗲 𝗠𝗼𝗱𝗲𝗹: RabbitMQ uses a queue-based message model following the Advanced Message Queuing Protocol (AMQP), 
while Kafka utilizes a distributed log-based model. ActiveMQ is built on the Java Message Service (JMS) standard 
and also uses a queue-based message model.

🔹𝗗𝘂𝗿𝗮𝗯𝗶𝗹𝗶𝘁𝘆: All three message brokers support durable messaging, ensuring that messages are not lost in case 
of failures. However, the mechanisms for achieving durability differ among the three, with RabbitMQ and 
ActiveMQ offering configurable durability options and Kafka providing built-in durability through log replication.

🔹𝗥𝗲𝗽𝗹𝗶𝗰𝗮𝘁𝗶𝗼𝗻: RabbitMQ supports replication through Mirrored Queues, while Kafka features built-in partition 
replication. ActiveMQ uses a Primary-Replica replication mechanism.

🔹𝗦𝘁𝗿𝗲𝗮𝗺 𝗣𝗿𝗼𝗰𝗲𝘀𝘀𝗶𝗻𝗴: Kafka provides native stream processing capabilities through Kafka Streams, similarly 
RabbitMQ offers stream processing too, while ActiveMQ relies on third-party libraries for stream processing.

### Альтернатива очередям

Notifier - опрашивает сервисы каждые N-секунд на то работают ли они. Heart beat
Load Balancer - распределяет нагрузку и следит за тем, чтобы не было дубликатов
База данных для хранения информация о том какой сервер обрабатывает какой заказ.
---------------------
Всё это есть альтернатива обычной очереди. Инкапсулирует все эти системы в одну систему очередей

[к оглавлению](architect.md#Architect)

## UML

Unified modeling language

![icon][uml]

### UML relations

![icon][uml-relations]

### Ассоциация

![icon][uml-relations-association]

### Class UML diagram

Software engineers use class diagrams to show the classes, attributes, 
and methods involved in a system and to describe their relationships to each other. 
Class diagrams let developers sketch a static view of a system before they go on to create it.

![icon][uml-str-class]

### Object UML diagram

Object diagrams focus on representing a system at a particular point in time. 
An object diagram shows the real-world instances of classes and the 
relationships between these instances.

![icon][uml-str-object]

### Component UML diagram

In any software system, the various components can be composed of other, 
smaller components. A component diagram shows how these individual software 
components interact and the dependencies between them.

![icon][uml-str-component]

### Deployment UML diagram

Deployment diagrams don’t deal with abstract elements in a system. 
Instead, a deployment diagram models the physical deployment of the components, 
or nodes, in that system. It is concerned with real-world entities such as servers 
and computing resources, and with the
interaction between them as described in terms of connectivity and APIs.

![icon][uml-str-deployment]

### Packages UML diagram

Packages in UML are hierarchical groupings of elements that allow for the 
manageable organization of the various components in a system. For instance, 
packages can enable a developer to represent the different layers of code used 
in the system and show how these different layers interact.

![icon][uml-str-package]

### Composite UML diagram

Composite structure diagrams are concerned with the internal structure of a class, 
or classifier, and how its internal parts collaborate via particular ports 
at runtime to achieve their desired purpose.

![icon][uml-str-composite]

### Profile UML diagram

Profile diagrams enable the extension of a UML model with stereotypes. 
These stereotypes can be assigned to individual UML elements or connectors 
and used when modeling particular domains.

![icon][uml-str-profile]

### Sequence UML diagram

Sequence diagrams are based on the modeling of interactions between objects in a
particular time sequence. They provide an overview of how the different 
parts of a system
interact over time and how processes are carried out.

![icon][uml-beh-sequence]

### Use case UML diagram

Use case diagrams just show how a user can interact with a system. 
They depict actors and
the actions they can take to have an impact on the system.

![icon][uml-beh-use-case]

### State machine UML diagram

State machine diagrams are designed to capture the dynamic nature 
of a system and how it
can change from one state to another. These diagrams show possible states 
and transitions,
along with the actions or events that cause the system to change states.

![icon][uml-beh-state-machine]

### Communication UML diagram

A communication diagram is similar to a sequence diagram, as it shows the dynamic
interactions between objects over time. But a communication diagram gives more of an
overview of the entire system, with less focus on timing.

![icon][uml-beh-communication]


### Interaction UML diagram 
Interaction overview diagrams are concerned with the holistic view of a system. These
diagrams are similar to the activity diagram, because interaction overview diagrams visualize
the sequence of activities and flow of control. But they also allow for frames around activities
that enable complex inline interactions to be described.

![icon][uml-beh-interaction]

### Timing UML diagram

The timing diagram brings us to the end of our journey through UML diagram types.
Timing diagrams are a bit like sequence diagrams, because they show the behavior of
objects in the system over time. But the timing diagram uses a linear time axis that moves
from left to right and shows how conditions change in terms of levels of lifelines.

![icon][uml-beh-timing]

[к оглавлению](architect.md#Architect)

## Software Architectural Patterns

- Layered pattern
- Client-server pattern
- Master-slave pattern
- Pipe-filter pattern
- Broker pattern
- Peer-to-peer pattern
- Event-bus pattern
- Model-view-controller pattern
- Blackboard pattern
- Interpreter pattern

![icon][10-arch-patterns]

### 1) **Layered pattern**

This pattern can be used to structure programs that can be decomposed into groups of subtasks, 
each of which is at a particular level of abstraction. 
Each layer provides services to the next higher layer.

The most commonly found 4 layers of a general information system are as follows.

- **Presentation layer** (also known as UI layer)
- **Application layer** (also known as service layer)
- **Business logic layer** (also known as domain layer)
- **Data access layer** (also known as persistence layer)

**!!!** Иногда уровни другие - `Presentation`, `Business`, `Persistence`, `DB`

***Usage***

- General desktop applications.
- E-commerce web applications.

### 2) **Client-server pattern**

This pattern consists of two parties; a **server** and multiple **clients**. 
The server component will provide services to multiple client components. 
Clients request services from the server and the server provides relevant services 
to those clients. Furthermore, the server continues to listen to client requests.

***Usage***

Online applications such as email, document sharing and banking.

### 3) **Master-slave pattern**

This pattern consists of two parties; **master** and **slaves**. 
The master component distributes the work among identical slave components, 
and computes a final result from the results which the slaves return.

***Usage***

In database replication, the master database is regarded as the authoritative source, 
and the slave databases are synchronized to it.
Peripherals connected to a bus in a computer system (master and slave drives).

### 4) **Pipe-filter pattern**

This pattern can be used to structure systems which produce and process a stream of data. 
Each processing step is enclosed within a **filter** component. 
Data to be processed is passed through **pipes**. 
These pipes can be used for buffering or for synchronization purposes.

***Usage***

- Compilers. The consecutive filters perform lexical analysis, parsing, 
semantic analysis, and code generation.
- Workflows in bioinformatics.

### 5) **Broker pattern**

This pattern is used to structure distributed systems with decoupled components. 
These components can interact with each other by remote service invocations. 
A **broker** component is responsible for the coordination of communication among **components**.

Servers publish their capabilities (services and characteristics) to a broker. 
Clients request a service from the broker, and the broker then redirects the client
to a suitable service from its registry.

***Usage***

- Message broker software such as **Apache ActiveMQ**, **Apache Kafka**, 
**RabbitMQ** and **JBoss Messaging**.

### 6) **Peer-to-peer pattern**

In this pattern, individual components are known as **peers**. 
Peers may function both as a **client**, requesting services from other peers, 
and as a server, providing services to other peers. 
A peer may act as a client or as a server or as both, 
and it can change its role dynamically with time.

***Usage***

- File-sharing networks such as `Gnutella` and `G2`
- Multimedia protocols such as `P2PTV` and `PDTP`.
- Cryptocurrency-based products such as `Bitcoin` and `Blockchain`

### 7) **Event-bus pattern**

This pattern primarily deals with events and has 4 major components: **event source**, 
**event listener**, **channel** and **event bus**. Sources publish messages to particular 
channels on an event bus. Listeners subscribe to particular channels. 
Listeners are notified of messages that are published to a channel to which they 
have subscribed before.

***Usage***

- Android development
- Notification services

### 8) **Model-view-controller pattern**

This pattern, also known as MVC pattern, divides an interactive application in to 3 parts as,

    1) **model** — contains the core functionality and data
    2) **view** — displays the information to the user (more than one view may be defined)
    3) **controller** — handles the input from the user

This is done to separate internal representations of information from the ways 
information is presented to, and accepted from, the user. 
It decouples components and allows efficient code reuse.

***Usage***

- Architecture for World Wide Web applications in major programming languages.
- Web frameworks such as Django and Rails.

### 9) **Blackboard pattern**

This pattern is useful for problems for which no deterministic solution strategies are known. 
The blackboard pattern consists of 3 main components.

- **blackboard** — a structured global memory containing objects from the solution space
- **knowledge source** — specialized modules with their own representation
- **control component** — selects, configures and executes modules.

All the components have access to the blackboard. 
Components may produce new data objects that are added to the blackboard. 
Components look for particular kinds of data on the blackboard, 
and may find these by pattern matching with the existing knowledge source.

***Usage***

- Speech recognition
- Vehicle identification and tracking
- Protein structure identification
- Sonar signals interpretation.

### 10) **Interpreter pattern**

This pattern is used for designing a component that interprets programs written in a 
dedicated language. It mainly specifies how to evaluate lines of programs, 
known as sentences or expressions written in a particular language. 
The basic idea is to have a class for each symbol of the language.

***Usage***

- Database query languages such as SQL.
- Languages used to describe communication protocols.

[ссылка](https://towardsdatascience.com/10-common-software-architectural-patterns-in-a-nutshell-a0b47a1e9013)

[к оглавлению](architect.md#Architect)

## Microservice Patterns

![icon][arch-patterns]
![icon][micro-patterns]

26 паттернов по группам:

1) Паттерны декомпозиции на микросервисы 
   1) «Разбиение по бизнес-возможностям» (Decompose By Business Capability)
   2) «Разбиение по поддоменам» (Decompose By Subdomain)

2) Паттерны рефакторинга для перехода на микросервисы
   1) «Душитель» (Strangler)
   2) «Уровень защиты от повреждений» (Anti-Corruption Layer)

3) Паттерны управления данными в микросервисной архитектуре
   1) «База данных на сервис» (Database Per Service)
   2) «API-композиция» (API Composition)
   3) «Разделение команд и запросов» (Command Query Responsibility Segregation, CQRS)
   4) «Поиск событий» (Event Sourcing)
   5) «Сага» (Saga)

4) Паттерны коммуникации микросервисов
   1) «API-шлюз» (API Gateway)
   2) «Бэкенды для фронтендов» (Backends for Frontends, BFF)

5) Паттерны построения пользовательского интерфейса
   1) «Сборка пользовательского интерфейса на стороне клиента» (Client-Side UI Composition)
   2) «Сборка фрагментов страниц на стороне сервера» (Server-Side Page Fragment Composition)

6) Паттерны обнаружения сервисов в микросервисной архитектуре
   1) «Обнаружение сервисов на стороне клиента» (Client-Side Service Discovery)
   2) «Обнаружение сервисов на стороне сервера» (Server-Side Service Discovery)

7) Паттерны развертывания микросервисов
   1) «Экземпляр сервиса на хост» (Service Instance Per Host)
   2) «Сине-зеленое развертывание» (Blue-Green Deployment)

8) Паттерны повышения отказоустойчивости
   1) «Автоматический выключатель» (Circuit Breaker)
   2) «Переборка» (Bulkhead)
   3) «Сервисная сетка» (Service Mesh)

9) Паттерны мониторинга микросервисов
   1) «Агрегация логов» (Log Aggregation)
   2) «Распределенная трассировка» (Distributed Tracing)
   3) «Проверки здоровья» (Health Check)

10) Прочие паттерны проектирования микросервисов
    1) «Посредник» («Посол», Ambassador)
    2) «Коляска» («Прицеп», Sidecar)
    3) «Тестирование контрактов, ориентированных на потребителя» 
    (Consumer-Driven Contract Testing)
    4) «Внешняя конфигурация» (External Configuration)

### 1) Паттерны декомпозиции на микросервисы

Этот блок шаблонов предлагает решения для декомпозиции, 
то есть разделения приложений на микросервисы.

#### 1.1) Шаблон «Разбиение по бизнес-возможностям» (Decompose By Business Capability)

Один из наиболее известных способов разбиения на микросервисы — 
это определение бизнес-возможностей приложения и создание 
по одному микросервису на каждую из них. Бизнес-возможности представляют собой функции, 
которые будут доступны пользователям при работе с приложением.

![icon][decomposite-by-business]

#### 1.2) Шаблон «Разбиение по поддоменам» (Decompose By Subdomain)

`При разбиении по бизнес-возможностям могут появиться так называемые «божественные классы» 
(God Classes)` — сущности, которые будут общими для нескольких микросервисов. 
Как правило, их очень сложно разделить.

Например, в приложении для интернет-магазина такой сущностью может стать заказ. 
В приведенном выше примере он используется сразу в нескольких сервисах: создание заказов 
(Orders Creation), доставка заказов (Orders Delivery), оповещения о заказах (Orders Alerts), 
предзаказы (Preorders).

Чтобы избежать появления God Classes, можно использовать альтернативный шаблон 
разложения на микросервисы — разбиение по поддоменам. Он основан на концепциях 
предметно-ориентированного проектирования (`Domain-Driven Design, DDD`).

DDD разбивает всю модель предметной области (домен) на поддомены. 
`У каждого поддомена своя модель данных, область действия которой принято называть 
ограниченным контекстом (Bounded Context).` Каждый микросервис будет разрабатываться 
внутри этого ограниченного контекста. Основная задача при использовании DDD-подхода 
— подобрать поддомены и границы между ними так, чтобы они были максимально независимы 
друг от друга.

Если вернуться к примеру с интернет-магазином, то все, что связано с заказами, 
можно рассматривать в рамках поддомена «Заказы» (Orders Subdomain) и именно 
внутри этого поддомена создавать микросервис по управлению заказами (Orders Service). 
Таким образом, можно сократить число микросервисов по сравнению с декомпозицией 
на основе бизнес-возможностей. В нашем сильно упрощенном примере четыре микросервиса 
были преобразованы в один.

![icon][decomposite-by-subdomain]

Паттерн Decompose By Subdomain. Пример создания микросервисов на основе поддоменов 
для интернет-магазина: по сравнению с декомпозицией на основе бизнес-возможностей 
число микросервисов, так или иначе связанных с заказами, сокращено с 4 до 1

### 2) Паттерны рефакторинга для перехода на микросервисы

Эта группа шаблонов предназначена для организации взаимодействия с Legacy-приложениями 
и/или их постепенного перевода на микросервисную архитектуру.

#### 2.1) Шаблон «Душитель» (Strangler)

Рассмотренные выше способы разбиения на микросервисы хорошо подходят для новых приложений, 
создаваемых с нуля.` Однако на практике часто возникает необходимость перевести 
на микросервисную архитектуру уже существующие монолитные приложения.`
Разложение монолита на микросервисы требует времени и не может быть выполнено за одну итерацию. 
Поэтому и был разработан паттерн Strangler, названный по аналогии с лианой, 
которая постепенно душит обвиваемое ею дерево.

Этот шаблон означает миграцию монолитного приложения на микросервисную архитектуру 
путем `постепенного переноса существующих функций в микросервисы.` 
Настраивается маршрутизация запросов между устаревшим монолитом и микросервисами. 
Когда очередная функциональность переносится из монолита в микросервисы, 
`фасад перехватывает клиентский запрос и направляет его к микросервисам`. 
Новые функции при этом реализуются исключительно в микросервисах, минуя монолит. 
После переноса всех функций монолитное приложение полностью выводится из эксплуатации.

`Паттерн не рекомендуется использовать при небольших размерах монолита.` 
В таком случае лучшим решением будет его единовременный перевод на микросервисную архитектуру, 
так как добавление фасада увеличивает задержки и затрудняет тестирование.

![icon][refactoring-strangler]

#### 2.2) Шаблон «Уровень защиты от повреждений» (Anti-Corruption Layer)

При переводе Legacy-приложений на микросервисы `рефакторинг некоторых подсистем может 
оказаться очень долгим либо вовсе невозможным`. `Но взаимодействовать с устаревшими 
подсистемами все равно нужно`, несмотря на то, что в них, возможно, 
используются не самые современные технологии в части построения API, схем данных и так далее.

Для таких случаев отлично подходит паттерн Anti-Corruption Layer. 
`Он предназначен для изолирования различных подсистем путем размещения между ними 
дополнительного уровня`, который может быть реализован как компонент приложения или 
независимая служба. Этот уровень связывает две подсистемы, `позволяя им оставаться 
максимально независимыми друг от друга`. Он содержит всю логику, необходимую для 
передачи данных в обе стороны: при взаимодействии с каждой из подсистем используется 
именно ее модель данных.

![icon][refactoring-anti-corruption]


### 3) Паттерны управления данными в микросервисной архитектуре

Этот блок шаблонов описывает возможные варианты взаимодействия микросервисов с базами данных.

#### 3.1) Шаблон «База данных на сервис» (Database Per Service)

Основная рекомендация при переходе на микросервисы — `предоставить каждому сервису 
собственное хранилище данных`, чтобы не было сильных зависимостей на уровне данных. 
При этом имеется в виду именно логическое разделение данных, то есть `микросервисы 
могут совместно использовать одну и ту же физическую базу данных, 
но в ней они должны взаимодействовать с отдельной схемой, коллекцией или таблицей`.

Основанный на этих принципах паттерн Database Per Service повышает автономность 
микросервисов и уменьшает связь между командами, разрабатывающими отдельные сервисы.

У паттерна есть и недостатки: `усложняется обмен данными между сервисами и 
предоставление транзакционных гарантий ACID`. Паттерн не стоит применять в 
небольших приложениях — он предназначен для крупномасштабных проектов с большим 
числом микросервисов, где каждой команде требуется полное владение ресурсами для 
повышения скорости разработки и лучшего масштабирования.

![icon][db-per-service]

Паттерну `Database Per Service` часто противопоставляют другой шаблон — 
`Shared Database («Разделяемая база данных»)`. По сути, он представляет собой `антипаттерн` и 
подразумевает использование одного хранилища данных несколькими микросервисами. 
Его допускается использовать на начальных стадиях миграции на микросервисную 
архитектуру или в очень небольших приложениях, разрабатываемых одной командой (2–3 микросервиса).

#### 3.2) Шаблон «API-композиция» (API Composition)

Этот шаблон является одним из возможных вариантов получения данных из нескольких сервисов 
после применения к ним паттерна Database Per Service. `Он предлагает создать отдельное API, 
которое будет вызывать необходимые сервисы`, владеющие данными, и выполнять соединение 
полученных от них результатов в памяти. Паттерн можно рассматривать как вариант 
использования другого шаблона — API Gateway, о котором мы поговорим ниже.

API Composition — это самый простой способ получения данных из нескольких источников, 
но он `может привести к неэффективному объединению больших наборов данных в памяти`. 
Альтернативным решением является следующий шаблон CQRS.

![icon][db-api-composition]

#### 3.3) Шаблон «Разделение команд и запросов» (Command Query Responsibility Segregation, CQRS)

Этот паттерн предлагает `отделить изменение данных (Command) от чтения данных (Query)`. 
Шаблон CQRS имеет две формы: простую и расширенную.

В простой форме для чтения и записи используются отдельные модели ORM 
(Object-Relational Mapping), но `общее хранилище данных`.

![icon][db-cqrs-1]

В расширенной форме используются `разные хранилища данных`, оптимизированные для 
записи и чтения данных. `Данные копируются из хранилища для записи в хранилище для 
чтения асинхронно.` В результате хранилище для чтения отстает от хранилища для записи, 
но в конечном итоге является согласованным. 
Расширенный CQRS `часто используется совместно с паттерном Event Sourcing`, 
речь о котором идет далее.

![icon][db-cqrs-2]

Паттерн CQRS обеспечивает высокую доступность данных, 
независимое масштабирование систем чтения/записи и более быстрое чтение данных 
в микросервисах, управляемых событиями. Однако его использование `увеличивает 
сложность системы и приводит к слабой согласованности данных`. 
Паттерн подходит для сложных систем, где для чтения данных требуется запрос 
в несколько хранилищ или операции чтения и записи имеют разную нагрузку.


#### 3.4) Шаблон «Поиск событий» (Event Sourcing)

Приложения с микросервисной архитектурой часто используют асинхронные методы связи: 
сообщения или события. `Для обеспечения атомарности операций` в таких системах 
рекомендуется применять шаблон Event Sourcing.

В традиционных базах данных объект с текущим состоянием сохраняется напрямую. 
При использовании шаблона Event Sourcing `вместо объектов сохраняются события`, 
изменяющие их состояния. Итоговое состояние объекта можно получить путем повторной 
обработки серии событий, пришедших за определенное время. Различные службы могут 
воспроизводить события из хранилища событий, чтобы вычислить соответствующее состояние 
своих хранилищ данных. `Для реализации хранилища событий обычно применяется шаблон CQRS`.

`Паттерн рекомендуется использовать в высокомасштабируемых транзакционных системах,
управляемых событиями.` Он не подходит для простых приложений, где микросервисы могут 
синхронно обмениваться данными (например, через API).

![icon][db-event-sourcing]

- События не изменяются, не удаляются
- Изредка делается snapshot (для увеличения производительности, например)
- в событии хранится только дельта изменений, а так же тип изменения (например, nameChanged, jobRemoved)
- лучше использовать когда много мелких событий и конечное состояние не так важно
- историчность, отчетность, быстрота чтения и записи
- лучше не использовать когда событий мало, важно финальное состояние, много сложных запросов
- С большим монолитом доменом лучше не использовать

![icon][eventsourcing_cqrs]

#### 3.5) Шаблон «Сага» (Saga)

Этот паттерн предназначен для `управления распределенными транзакциями в 
микросервисной архитектуре`, где применение традиционного протокола двухфазной 
фиксации транзакций (Two-phase commit protocol, 2PC) становится трудноосуществимым.

При использовании паттерна `каждая локальная транзакция обновляет данные в 
хранилище в рамках одного микросервиса` и публикует событие или сообщение, которые, 
в свою очередь, запускают следующую локальную транзакцию и так далее. 
`Если локальная транзакция завершается с ошибкой, выполняется серия компенсирующих транзакций`, 
которые отменяют изменения предыдущих транзакций.

Для координации транзакций существует два основных способа:

- **Хореография**. Децентрализованная координация, при которой каждый микросервис прослушивает 
события/сообщения другого микросервиса и решает, следует предпринять действие или нет.
- **Оркестровка**. Централизованная координация, при которой отдельный компонент (оркестратор) 
сообщает микросервисам, какое действие необходимо выполнить далее.

Использование шаблона обеспечивает согласованность транзакций в слабосвязанных 
распределенных системах, однако `увеличивает сложность отладки`. 
Saga отлично подходит для систем, управляемых событиями и/или использующих базы 
данных NoSQL без поддержки 2PC, но не рекомендуется при использовании 
баз данных SQL и в системах с циклическими зависимостями между сервисами.

![icon][db-saga]

### 4) Паттерны коммуникации микросервисов

Этот блок шаблонов охватывает способы внешних взаимодействий микросервисов: 
с клиентскими приложениями, удаленными сервисами и так далее.

#### 4.1) «API-шлюз» (API Gateway)

Наиболее очевидный способ обращения к микросервисам — прямое обращение от клиента к сервису. 
И его вполне можно применять в небольших проектах. Однако в приложениях 
корпоративного масштаба с большим числом микросервисов рекомендуется 
использовать шаблон API Gateway.

Этот паттерн основан на `применении шлюза, который находится между клиентским 
приложением и микросервисами, обеспечивая единую точку входа для клиента`.

В зависимости от конкретной цели использования паттерна иногда выделяют следующие 
его разновидности:

- **Gateway Routing.** Шлюз используется как обратный Proxy, 
перенаправляющий запросы клиента на соответствующий сервис.
- **Gateway Aggregation.** Шлюз используется для разветвления клиентского 
запроса на несколько микросервисов и возвращения агрегированных ответов клиенту.
- **Gateway Offloading.** Шлюз решает сквозные задачи, которые являются общими 
для сервисов: аутентификация, авторизация, SSL, ведение журналов и так далее.

Применение паттерна `сокращает число вызовов`, `обеспечивает независимость клиента 
от протоколов`, используемых в сервисах: REST, AMQP, gRPC и так далее, обеспечивает 
централизованное управление сквозной функциональностью. `Однако шлюз может стать 
единой точкой отказа`, требует тщательного мониторинга и при отсутствии масштабирования 
бывает узким местом системы.

![icon][comm-api-gateway]

#### 4.2) Шаблон «Бэкенды для фронтендов» (Backends for Frontends, BFF)

Этот паттерн является `вариантом реализации шаблона API Gateway`. 
Он также обеспечивает дополнительный уровень между микросервисами и клиентами, 
но вместо одной точки входа вводит `несколько шлюзов для каждого типа клиента: 
Web, Mobile, Desktop` и так далее.

С помощью паттерна можно добавить API, `адаптированные к потребностям каждого клиента`, 
избавившись от хранения большого количества ненужных настроек в одном месте. 
Но шаблон не стоит применять в тех случаях, когда разница в требованиях к API 
у разных типов клиентов незначительна либо приложение само по себе небольшое: 
это приведет лишь к дублированию кода и увеличению числа компонентов.

![icon][comm-back-for-front]

### 5) Паттерны построения пользовательского интерфейса

Эта группа шаблонов предлагает решения для отображения на одной странице 
или экране пользовательского интерфейса данных из нескольких микросервисов.

#### 5.1) Шаблон «Сборка пользовательского интерфейса на стороне клиента» (Client-Side UI Composition)

При использовании этого шаблона разметка HTML создается и обновляется непосредственно 
в браузере. `Каждый экран/страница пользовательского интерфейса разбивается на фрагменты, 
данные для которых получают различные микросервисы.` Каждый такой фрагмент, по сути, 
представляет собой мини-приложение, которое может отображать и обновлять свою 
разметку независимо от остальной части страницы.

Многие современные фреймворки, например AngularJS и ReactJS, помогают в реализации 
этого шаблона. Они используют `принцип одностраничных приложений` 
(Single-Page Application, SPA), позволяя обновлять отдельную область экрана, 
а не всю страницу целиком.

![icon][ui-client-build]

#### 5.2) Шаблон «Сборка фрагментов страниц на стороне сервера» (Server-Side Page Fragment Composition)

При использовании этого шаблона сборка фрагментов пользовательского интерфейса 
происходит на сервере, а клиентская часть получает уже полностью собранную страницу, 
благодаря чему достигается `более высокая скорость загрузки`. 
`Сборка обычно выполняется отдельной службой`, которая находится между браузером и 
серверами приложений: Nginx, Varnish, CDN.

![icon][ui-server-build]

### 6) Паттерны обнаружения сервисов в микросервисной архитектуре

Эта группа шаблонов описывает методы, которые могут использовать клиентские 
приложения для определения местонахождения нужных им сервисов. 
Это особенно важно в микросервисных приложениях, так как они работают в 
виртуализированных и контейнерных средах, где количество экземпляров сервисов 
и их расположение изменяются динамически.

Ключевым компонентом обнаружения микросервисов выступает реестр сервисов 
(`Service Registry`) — база данных с информацией о расположении сервисных экземпляров. 
Когда экземпляры запускаются и останавливаются, информация в реестре обновляется. 
Но взаимодействовать с реестром сервисов можно двумя путями, которые и легли 
в основу описанных ниже шаблонов.

#### 6.1) Шаблон «Обнаружение сервисов на стороне клиента» (Client-Side Service Discovery)

Первый способ обнаружения сервисов — на стороне клиента. В этом случае сервисы и их 
клиенты напрямую взаимодействуют с реестром. Последовательность шагов следующая:

1) Экземпляр сервиса обращается к API реестра, чтобы `зарегистрировать свое сетевое 
местоположение`. Он также может предоставить URL-адрес для проверки своей работоспособности 
(Health Check), который будет использоваться для продления срока его регистрации в реестре.
2) Клиент самостоятельно обращается к реестру сервисов, чтобы `получить список 
экземпляров сервисов`. Для улучшения производительности клиент может кэшировать 
экземпляры сервиса.
3) Клиент использует `алгоритм балансировки нагрузки`, циклический или случайный, 
чтобы выбрать конкретный экземпляр сервиса и отправить ему запрос.

Ключевое преимущество обнаружения сервисов на стороне клиента — `его независимость от 
используемой платформы развертывания`. Например, если часть ваших сервисов развернута 
на K8s, а остальные работают в устаревшей среде, то обнаружение на уровне приложения 
будет лучшим вариантом, так как серверное решение на базе Kubernetes не будет 
совместимо со всеми сервисами.

К недостаткам подхода можно отнести `необходимость использования различных клиентских 
библиотек для каждого языка программирования`, а иногда и фреймворка. Кроме этого, 
на вашу команду ложится дополнительная нагрузка по настройке и обслуживанию реестра сервисов.

![icon][discovery-client]

#### 6.2) Шаблон «Обнаружение сервисов на стороне сервера» (Server-Side Service Discovery)

Второй способ обнаружения сервисов — на стороне сервера. В этом случае за регистрацию, 
обнаружение сервисов и маршрутизацию запросов отвечает инфраструктура развертывания. 
Последовательность шагов следующая:

1) Регистратор, который обычно является частью платформы развертывания, `прописывает все 
экземпляры сервисов в реестре сервисов`. По каждому экземпляру сохраняется DNS-имя и 
виртуальный IP-адрес (VIP).
2) Вместо того чтобы обращаться к реестру напрямую, `клиент делает запрос по 
DNS-имени сервиса`. Запрос поступает в маршрутизатор, являющийся частью платформы 
развертывания.
3) Маршрутизатор обращается к реестру сервисов для получения сетевого расположения 
экземпляров нужного сервиса.
4) `Маршрутизатор применяет балансировку нагрузки`, чтобы выбрать конкретный экземпляр 
сервиса и отправить ему запрос.

Все современные платформы развертывания, включая Docker, Kubernetes и другие, как правило, 
имеют встроенный реестр и механизмы обнаружения сервисов.

Основное преимущество паттерна состоит в том, что `всеми аспектами обнаружения 
сервисов занимается сама платформа`. Дополнительный код на стороне клиента или 
сервисов не требуется. Благодаря этому достигается независимость от используемых 
в приложении языков программирования и фреймворков.

`Недостатком паттерна является невозможность его применения к сервисам, которые 
развернуты вне основной платформы`, реализующей механизмы обнаружения. 
Несмотря на это ограничение, рекомендуется использовать обнаружение сервисов 
на стороне сервера всюду, где это осуществимо.

![icon][discovery-server]

### 7) Паттерны развертывания микросервисов

Этот блок шаблонов описывает возможные варианты развертывания разработанных микросервисов.

#### 7.1) Шаблон «Экземпляр сервиса на хост» (Service Instance Per Host)

При переходе на микросервисную архитектуру рекомендуется проводить развертывание 
каждого экземпляра сервиса на собственном хосте (виртуальном или физическом). 
Паттерн Service Instance Per Host `позволяет изолировать экземпляры сервисов 
друга от друга`, избежать конфликтов версий и требований к ресурсам, 
максимально использовать ресурсы хоста, а также легче и быстрее проводить 
повторные развертывания. К недостаткам паттерна можно отнести `потенциально 
менее эффективное использование ресурсов` по сравнению с развертыванием 
нескольких экземпляров на хост.

Иногда выделяют разновидности описанного шаблона, наиболее часто используемые 
на практике: `«Экземпляр сервиса на виртуальную машину»` (Service Instance Per VM) 
и `«Экземпляр сервиса на контейнер»` (Service Instance Per Container). 
При их использовании каждый экземпляр сервиса упаковывается и разворачивается 
в виде отдельной виртуальной машины либо контейнера соответственно.

![icon][deploy-host]

#### 7.2) Шаблон «Сине-зеленое развертывание» (Blue-Green Deployment)

Паттерн позволяет выполнить развертывание новых версий сервисов максимально 
незаметно для пользователей, сократив время простоя до минимума. 
Это достигается за счет `запуска двух идентичных производственных сред — 
условно синего и зеленого цвета`. Предположим, что синий — это существующий 
активный экземпляр, а зеленый — это новая версия приложения, развернутая параллельно с ним.

`В любой момент времени только одна из сред является активной`, и именно 
она обслуживает весь производственный трафик. После успешного развертывания 
новой версии — с прохождением всех тестов и так далее — трафик переключается 
на нее. В случае ошибок всегда можно вернуться к предыдущей версии.

![icon][deploy-blue-green]

#### 7.3) Canary release

Подвид Blue-Green Deployment, где на прод выкатывается новая версия и
направляется небольшой трафик для проверки.

- балансировка
  - одна нода всегда canary
  - Canary-нода задается в процессе деплоя
- мониторинг, так как важно знать, что ожидает каждый пользователь, 
и как детально работают наши сервисы
  - Количество ошибок, Время выполнения запросов, Размер очереди, 
  Количество успешных ответов в секунду, Время выполнения 95% всех запросов, Бизнес-метрики
    (Counter, Gauge, Summary). Инструменты - ELK Stack, Prometheus
- анализ версий, чтобы понимать, насколько хорошо новая версия будет работать в продакшн
  - Смотреть метрики только canary-ноды
  - Canary-нода сравнивается с любой другой нодой
  - Canary-нода сравнивается с собой в прошлом
- автоматизация — пишем последовательность развертывания (deployment pipeline).

![icon][deploy-canary-release]

[Ссылка на статью](https://habr.com/ru/company/oleg-bunin/blog/493026/)

### 8) Паттерны повышения отказоустойчивости

Эта группа шаблонов предназначена для повышения надежности приложений с 
микросервисной архитектурой.

- Timeouts (сделай за 3 секунды. Может отправить запрос дальше
  в цепочку сервисов и ошибка будет только на обратнмо пути)
- Retries (3 раза и всё)
- Circuit Breaker (много ошибок - подняли новый)
- Deadlines (сделай до 15-00, не успел - верни ошибку сразу)
- Rate limiters (1000 запросов в секунду)

#### 8.1) Шаблон «Автоматический выключатель» (Circuit Breaker)

При взаимодействии микросервисов не исключены ситуации, когда по какой-то причине 
один из сервисов перестает отвечать. Справиться с временными сбоями 
(медленное сетевое соединение, временная недоступность и так далее) 
`помогают повторные вызовы`. Однако при более серьезных сбоях, вызванных полным 
отказом сервиса, повторные вызовы будут лишь расходовать ресурсы.

В таких случаях рекомендуется использовать шаблон Circuit Breaker. 
Микросервис будет запрашивать другой микросервис через Proxy-сервер. 
Он `подсчитывает количество недавних сбоев` и на основе него определяет, 
разрешать ли выполнение последующих вызовов или немедленно возвращать исключение.

Proxy-сервер может находиться в трех состояниях:

- **Closed**. Идет передача запросов между сервисами и подсчет количества сбоев. 
Если число сбоев за заданный интервал времени `превышает пороговое значение`, 
выключатель Proxy-сервера переводится в состояние Open.
- **Open**. Запросы от исходного сервиса немедленно возвращаются с ошибкой. 
`По истечении заданного тайм-аута` выключатель переводится в состояние Half-Open.
- **Half-Open**. `Выключатель пропускает ограниченное количество запросов` от 
исходного сервиса и подсчитывает число успешных запросов. Если необходимое 
количество достигнуто, выключатель переходит в состояние Closed, если 
нет — возвращается в статус Open.

Использование шаблона повышает отказоустойчивость и предотвращает каскадные сбои, 
но требует тщательной настройки и мониторинга.

![icon][fault-tolerance-circuit]

#### 8.2) Шаблон «Переборка» (Bulkhead)

Свое название паттерн получил благодаря переборкам, используемым в судостроении: 
они защищают корабль от полного затопления в случае повреждения отдельных его частей. 
Так же и в архитектуре приложения: `переборки изолируют элементы приложения в пулы`, 
чтобы в случае сбоя одного из них остальные продолжали функционировать.

Шаблон позволяет `разделить ресурсы`, чтобы гарантировать, что ресурсы, 
используемые для вызова одного сервиса, не влияют на ресурсы, используемые для 
вызова другого сервиса. Пример — использование отдельного пула соединений для 
каждого из нижестоящих сервисов.

![icon][fault-tolerance-bulkhead-1]

Еще один вариант использования шаблона — `назначение каждому клиенту 
сервиса отдельного экземпляра сервиса`. В таком случае, если один из 
клиентов сделает слишком много запросов, перегрузив свой экземпляр, 
другие клиенты смогут продолжить работу.

![icon][fault-tolerance-bulkhead-2]

Использование этого паттерна `предотвращает каскадные сбои` и `изолирует критически 
важные ресурсы`, но приводит к дополнительной сложности и менее эффективному 
использованию ресурсов.

#### 8.3) «Сервисная сетка» (Service Mesh)

`Service Mesh` – это конфигурируемый инфраструктурный уровень с низкой задержкой, 
который `нужен для обработки большого объема сетевых межпроцессных коммуникаций между 
программными интерфейсами приложения (API)`. Service Mesh обеспечивает быструю, 
надёжную и безопасную коммуникацию между `контейнеризированными` и часто эфемерными 
сервисами инфраструктуры приложений. Service Mesh предоставляет такие возможности, 
как `обнаружение сервисов, балансировку нагрузки, шифрование, прозрачность, трассируемость, 
аутентификацию и авторизацию, а также поддержку шаблона автоматического выключения 
(circuit breaker)`.

Service Mesh обычно реализуется путем предоставления каждому экземпляру сервиса 
экземпляра прокси, который называется `Sidecar`. Sidecar обрабатывают коммуникации 
между сервисами, производят мониторинг и устраняют проблемы безопасности, то есть все, 
что может быть абстрагировано от отдельных сервисов. Таким образом, разработчики могут 
писать, поддерживать и обслуживать код приложения в сервисах, а системные администраторы 
могут работать с Service Mesh и запускать приложение.

Примеры: Istio

![icon][fault-service-mesh]

### 9) Паттерны мониторинга микросервисов

Этот блок шаблонов охватывает возможные варианты построения мониторинга работы 
микросервисов.

#### 9.1) Шаблон «Агрегация логов» (Log Aggregation)

Хорошей практикой при разработке микросервисов считается ведение логов 
каждым экземпляром сервиса. Логи могут содержать ошибки, предупреждения, 
информационные и отладочные сообщения. Но с увеличением числа сервисов 
анализ логов, разнесенных по различным хостам, становится затруднительным.

Паттерн Log Aggregation предлагает `использовать централизованную службу 
ведения логов`, которая будет собирать логи от каждого экземпляра сервиса. 
Это предоставит пользователям единую точку для поиска, анализа логов и 
настройки предупреждений, которые будут запускаться при появлении в них 
определенных сообщений.

![icon][monitoring-log]

#### 9.2) Шаблон «Распределенная трассировка» (Distributed Tracing)

В микросервисной архитектуре для выполнения клиентских запросов может 
потребоваться работа нескольких взаимосвязанных микросервисов. 
Каждый сервис обрабатывает запрос путем выполнения одной или нескольких операций, 
включая обращение к базе данных, публикацию сообщений и так далее. 
С увеличением числа сервисов становится все сложнее отследить место возникновения ошибок.

Паттерн Distributed Tracing разработан для решения этой проблемы. 
Он предлагает `назначать каждому внешнему запросу уникальный идентификатор` (TraceId), 
который будет передаваться всем сервисам, участвующим в обработке запроса, 
и фиксироваться в журналах. Это позволит разработчикам видеть, как обрабатывается 
отдельный запрос, путем поиска в агрегированных журналах его внешнего идентификатора.

![icon][monitoring-tracing]

#### 9.3) Шаблон «Проверки здоровья» (Health Check)

Иногда экземпляр сервиса, более не способный обрабатывать внешние запросы, 
остается доступен для других подсистем. Например, сервис может исчерпать 
пул соединений к базе данных — фактически он становится неработоспособным, 
но принимать внешние запросы по-прежнему в состоянии, хоть и без последующей 
корректной обработки. В таких случаях система мониторинга должна выдавать 
своевременное предупреждение, а балансировщик нагрузки, реестр служб и другие 
подсистемы не должны направлять запросы на отказавший экземпляр.

Для решения этой задачи предназначен паттерн Health Check. Он предлагает 
определить для каждого сервиса `конечную точку, которую можно использовать 
для проверки работоспособности`, например /health. Этот API должен проверять 
статус хоста, подключение к другим сервисам, инфраструктуре и любую иную 
бизнес-логику. Клиент — служба мониторинга, реестр служб или балансировщик 
нагрузки — будет периодически обращаться к конечной точке для проверки 
работоспособности экземпляра сервиса.

![icon][monitoring-health-check]

### 10) Прочие паттерны проектирования микросервисов

Здесь рассмотрим наиболее важные шаблоны из иных групп.

#### 10.1) Шаблон «Посредник» («Посол», Ambassador)

Приложениям и сервисам часто требуются общие функции, относящиеся к мониторингу, 
ведению журналов, настройкам безопасности, сетевым службам и так далее. 
Однако в микросервисной архитектуре отдельные сервисы могут быть построены 
с помощью различных языков и технологий — следовательно, они могут иметь 
свои зависимости и требовать определенных языковых библиотек. 
Паттерн Ambassador предлагает `помещать клиентские фреймворки и библиотеки 
для решения периферийных задач внутрь вспомогательного сервиса`,
выступающего в роли Proxy между клиентским приложением или основным сервисом 
и прочими частями системы.

Применение паттерна Ambassador позволяет:

- `Унифицировать обращение клиентских приложений` к общим задачам независимо 
от используемого языка и фреймворка.
- Решать периферийные задачи, не затрагивая основную функциональность, 
в том числе за счет передачи разработки отдельным специализированным командам. 
Это полезно, например, при необходимости централизованного управления сетевыми 
вызовами и `функциями безопасности` — во избежание дублирования сложного кода на 
каждом компоненте отдельно.
- Добавлять новую функциональность в Legacy-приложения, которые тяжело поддаются 
рефакторингу.

Так как добавление Proxy пусть и незначительно, но `увеличивает сетевые задержки`, 
шаблон Ambassador не рекомендуется использовать, когда время задержки критично. 
`Также паттерн лучше не применять в случаях, когда можно обойтись стандартной 
клиентской библиотекой` — например, если используется всего один язык или нет 
возможности выделить общие периферийные задачи.

Развернуть Proxy можно как демон или службу. Если основной сервис является 
контейнерным, Proxy также разворачивается как отдельный контейнер на том же хосте, 
для этой цели используется другой паттерн — Sidecar.

![icon][other-ambassador]

#### 10.2) Шаблон «Коляска» («Прицеп», Sidecar)

Паттерн Sidecar предлагает помещать периферийные задачи, связанные с мониторингом, 
безопасностью, отказоустойчивостью и так далее, в отдельный компонент и 
развертывать его внутри собственного процесса или контейнера. 
Так обеспечивается однородный интерфейс для сервисов основного приложения, 
которые могут быть написаны на разных языках.

Sidecar не  обязательно является частью приложения, но связан с ним: для каждого 
экземпляра приложения рядом развертывается экземпляр Sidecar. Sidecar имеет 
тот же жизненный цикл, что и основное приложение.

Преимуществами паттерна являются `независимость вспомогательного компонента от 
платформы основного приложения`, возможность их доступа к одним и тем же ресурсам, 
минимизация задержек из-за их близкого расположения и возможность независимого 
обновления. К недостаткам можно отнести `накладные расходы на создание 
дополнительного компонента`. Шаблон не рекомендуется использовать для небольших 
приложений, а также в тех случаях, когда можно обойтись библиотеками и 
стандартными механизмами расширений.

![icon][other-sidecar]

#### 10.3) Шаблон «Тестирование контрактов, ориентированных на потребителя» (Consumer-Driven Contract Testing)

Это один из стилей тестирования, который рекомендуют использовать в 
крупномасштабных проектах, где несколько команд работают над различными сервисами. 
Суть паттерна в том, что `набор автоматизированных тестов для каждого сервиса 
(Provider Microservice) пишется разработчиками других сервисов (Consumer Microservice), 
вызывающих проверяемый сервис`. Каждый такой набор тестов является контрактом, 
проверяющим, соответствует ли сервис провайдера ожиданиям потребителя. 
Сами тесты включают в себя запрос и ожидаемый ответ.

Паттерн Consumer-Driven Contract Testing увеличивает автономность команд 
и позволяет своевременно обнаруживать изменения в сервисах, написанных 
другими командами. Но его применение может `потребовать дополнительной 
работы по интеграции тестов`, так как команды могут пользоваться различными 
инструментами тестирования.

![icon][other-customer-driven-contract]

#### 10.4) Шаблон «Внешняя конфигурация» (External Configuration)

Практически все приложения во время работы используют разнообразные 
конфигурационные параметры: адреса служб, строки подключения к базам данных, 
учетные данные, пути к сертификатам и так далее. При этом параметры будут 
отличаться в зависимости от среды выполнения: Dev, Stage, Prod и так далее. 
Хранить конфигурации локально — в файлах, развертываемых вместе с приложением, 
— считается очень плохой практикой, особенно при переходе на микросервисы. 
Это приводит к серьезным рискам безопасности и требует повторного развертывания 
при каждом изменении конфигурационных параметров.

Поэтому в приложениях корпоративного уровня рекомендуется использовать шаблон 
External Configuration, предлагающий `хранить все конфигурации во внешнем хранилище`. 
В качестве такого хранилища может выступать облачная служба хранения, 
база данных или другая система.

В результате применения шаблона процесс сборки будет отделен от среды выполнения, 
а риски безопасности будут сведены к минимуму, так как конфигурации для 
производственной среды перестанут являться частью кодовой базы.

![icon][other-external-conf]

### Почему важно применять шаблоны проектирования в микросервисной архитектуре

При переходе на микросервисы предстоит принимать множество архитектурных решений,
от которых будет зависеть эффективность итогового продукта. Знание и правильный
выбор подходящих паттернов во многом упрощают и ускоряют этот процесс.
Ведь всегда лучше полагаться на многолетний опыт других разработчиков,
чем пытаться изобрести собственное решение с нуля.

Вот лишь некоторые преимущества, которых можно достичь, используя паттерны
проектирования микросервисов:

- Уменьшение ошибок при проектировании микросервисов — без необходимости их
  рефакторинга в дальнейшем.
- Более быстрая и качественная миграция монолитов на микросервисную архитектуру.
- Предотвращение ненужных вызовов и неэффективного использования ресурсов.
- Отсутствие проблем с подключением новых сервисов, их интеграцией друг с другом и
  базами данных.
- Лучшая масштабируемость: добавление дополнительных сервисов не вызывает
  затруднений в обслуживании зависимостей.
- Повышение отказоустойчивости.
- Минимизация угроз безопасности, в том числе сокрытие конечных точек микросервисов.
- Сокращение работ по обслуживанию и отладке.

[ссылка](https://mcs.mail.ru/blog/26-osnovnyh-patternov-mikroservisnoj-razrabotki)

[к оглавлению](architect.md#Architect)

## Задержки

```roomsql
Latency Comparison Numbers
--------------------------
L1 cache reference                           0.5 ns
Branch mispredict                            5   ns
L2 cache reference                           7   ns                      14x L1 cache
Mutex lock/unlock                           25   ns
Main memory reference                      100   ns                      20x L2 cache, 200x L1 cache
Compress 1K bytes with Zippy            10,000   ns       10 us
Send 1 KB bytes over 1 Gbps network     10,000   ns       10 us
Read 4 KB randomly from SSD*           150,000   ns      150 us          ~1GB/sec SSD
Read 1 MB sequentially from memory     250,000   ns      250 us
Round trip within same datacenter      500,000   ns      500 us
Read 1 MB sequentially from SSD*     1,000,000   ns    1,000 us    1 ms  ~1GB/sec SSD, 4X memory
HDD seek                            10,000,000   ns   10,000 us   10 ms  20x datacenter roundtrip
Read 1 MB sequentially from 1 Gbps  10,000,000   ns   10,000 us   10 ms  40x memory, 10X SSD
Read 1 MB sequentially from HDD     30,000,000   ns   30,000 us   30 ms 120x memory, 30X SSD
Send packet CA->Netherlands->CA    150,000,000   ns  150,000 us  150 ms

Notes
-----
1 ns = 10^-9 seconds
1 us = 10^-6 seconds = 1,000 ns
1 ms = 10^-3 seconds = 1,000 us = 1,000,000 ns
```

[Ссылка на статью](
http://highscalability.com/blog/2011/1/26/google-pro-tip-use-back-of-the-envelope-calculations-to-choo.html)

[к оглавлению](architect.md#Architect)

## System Design Template

#### (1) FEATURE EXPECTATIONS [5 min]

```
(1) Use cases
(2) Scenarios that will not be covered
(3) Who will use
(4) How many will use
(5) Usage patterns
```

#### (2) ESTIMATIONS [5 min]

```
(1) Throughput (QPS for read and write queries)
(2) Latency expected from the system (for read and write queries)
(3) Read/Write ratio
(4) Traffic estimates
- Write (QPS, Volume of data)
- Read  (QPS, Volume of data)
(5) Storage estimates
(6) Memory estimates
- If we are using a cache, what is the kind of data we want to store in cache
- How much RAM and how many machines do we need for us to achieve this ?
- Amount of data you want to store in disk/ssd
```

#### (3) DESIGN GOALS [5 min]

```
(1) Latency and Throughput requirements
(2) Consistency vs Availability  [Weak/strong/eventual => consistency | Failover/replication => availability]
```

#### (4) HIGH LEVEL DESIGN [5-10 min]

```
(1) APIs for Read/Write scenarios for crucial components
(2) Database schema
(3) Basic algorithm
(4) High level design for Read/Write scenario
```

#### (5) DEEP DIVE [15-20 min]

```
(1) Scaling the algorithm
        (2) Scaling individual components: 
                -> Availability, Consistency and Scale story for each component
                -> Consistency and availability patterns
        (3) Think about the following components, how they would fit in and how it would help
                a) DNS
                b) CDN [Push vs Pull]
                c) Load Balancers [Active-Passive, Active-Active, Layer 4, Layer 7]
                d) Reverse Proxy
                e) Application layer scaling [Microservices, Service Discovery]
                f) DB [RDBMS, NoSQL]
                        > RDBMS 
                            >> Master-slave, Master-master, Federation, Sharding, Denormalization, SQL Tuning
                        > NoSQL
                            >> Key-Value, Wide-Column, Graph, Document
                                Fast-lookups:
                                -------------
                                    >>> RAM  [Bounded size] => Redis, Memcached
                                    >>> AP [Unbounded size] => Cassandra, RIAK, Voldemort
                                    >>> CP [Unbounded size] => HBase, MongoDB, Couchbase, DynamoDB
                g) Caches
                        > Client caching, CDN caching, Webserver caching, Database caching, Application caching, Cache @Query level, Cache @Object level
                        > Eviction policies:
                                >> Cache aside
                                >> Write through
                                >> Write behind
                                >> Refresh ahead
                h) Asynchronism
                        > Message queues
                        > Task queues
                        > Back pressure
                i) Communication
                        > TCP
                        > UDP
                        > REST
                        > RPC
```

#### (6) JUSTIFY [5 min]

```
(1) Throughput of each layer
(2) Latency caused between each layer
(3) Overall latency justification
```

[Примеры](https://github.com/donnemartin/system-design-primer#system-design-interview-questions-with-solutions)

[к оглавлению](architect.md#Architect)

## Horizontal vs Vertical Scaling

| Horizontal Scaling      | Vertical Scaling               |
|-------------------------|--------------------------------|
| Requires Load Balancing | n/a                            |
| ++ Resilient            | Single point of failure        |
| Network call            | ++ Inter process communication |
| Data inconsistency      | ++ Consistent                  |
| ++ Scales well          | Hardware limit                 |

[к оглавлению](architect.md#Architect)

## Consistent hashing

Механизм помогающий реализовать load balancing. RequestId - случайно генерированное число от клиента от 1 до М-1.

1) Деление на сектора. Через хеш функцию RequestId -> m1, например **RequestId % n** - где n количество серверов
- если мы добавим новый сервер - может начаться проблема из-за привязки к n. 
- Старые операции, обрабатываемые на старом сервисе выполняются на новом, а база может быть неконсистентной. 
- Бьёт старые кеши. Например, хеши с 0 по 25 на 1 сервер, хеши с 26 по 50 на 2 сервер, с 51-75 на 3, с 76 по 100 на 4. 
Добавляем пятый сервер и тогда хеши сдвинутся - 0-20 на 1, 21-40 на 2, 41-60 на 3, 61-80 на 4, 81-100 на 5. 
И того хеши с 20-25, 41-50, 60-100. То есть мы теряем в итоге 55 процентов кешей.

2) Деление по кольцу. Запросы сыпятся на следующий по хешу сервер. 
- При добавлении нового сервера или убавлении только 1 сервер будет затронут
- Множество хеш-функций позволяют разделить это кольцо на много участков, 
так что при отключении одного сервера его нагрузка не ляжет на другой один, а разпределится.

[к оглавлению](architect.md#Architect)

## Distributed cache

### Зачем нужен кеш?
- **Чтобы уменьшить количество вызовов.**
Например, вы храните в памяти данные профилей пользователей, потому что это часто используемая информация. 
Ну или это статичные справочники. 
- **Чтобы не делать одни и те же вычисления много раз.**
Например, мы вычисляем средний возраст пользаков и не хотим каждый раз вычислять это число.
- **Уменьшить нагрузку на БД**

### Почему не хранить ВСЁ в кеше?
- Память (оперативная) кеша дороже чем память на жестком диске (SSD Или HDD)
- Большй объём информации в кеше увеличит скорость поиска в нём, мы лишимся скорости, 
а ведь это важное преимущество кеша

### Политика кеша
Когда загружать новую информацию в кеш, когда стоит запрашивать данные из кеша?
- **LRU** least recently used - последний используемый
- **LFU** least frequently used - чаще всего используемый (Может хранить старый популярный запрос, который уже не актуален)

### Проблемы
- Extra calls. Если кеш не заполнен, то это дополнительный запрос каждый раз
- Trashing. Маленький кеш и большое количество разных запросов постоянно обновляют кеш
- Nonconsistency - данные в бд обновились другим сервисом и кеш памяти текущего сервиса хранит неактуальные данные

### Куда поместить кеш?
1) **Память сервиса**
- Быстрее, потмоу что находится в той же памяти что и приложение
- В случае падения сервиса его кеш тоже обнулится
- неконсистентность кешей разных реплик
- не рекомендуется для чувствительной информации
2) **Глобальный кеш**
- может быть распределенным и масштабировать независимо
- медленнее, но лишен проблемы неконсистентности
- падение сервера приложение не влияет на глобальный кеш

### Как заполнять кеш?

1) **write-through** 
- новая информация сначала записывается в кеш и потом в бд
- проблема, если кеш был обновлен одним сервисом, потом обновил бд, но кеш второго сервиса неактуален
2) **write-back** 
- сначала записываем в бд, потом бд обновляет инфу в кеше
- дороже, так как при большом кеше приходится много проверять актуально кеша
3) **гибрид**
- запись новых данных не производится сразу, а только через некоторое время большой пачкой
- может использоваться только с недорогими данными ( не с финансовыми данными и паролями )

[к оглавлению](architect.md#Architect)

## Design API

Контракт между приложением и сторонним пользователем/другим приложением о том 
как вызывать его функции через сетевое взаимодейцствие.

1) АПИ должно делать то, о чем говорится в названии
2) Не вводите дополнительные параметры, если это не необходимо
3) Не стоит использовать слишком сложные сущности в ответе
4) Ошибки АПИ должны соответствовать ожиданиям и его отвестсвенности (быть может он не должен валидировать запрос)
5) Использовать разные типы HTTP запросов GET,POST,...
6) Избегать сайд эффектов (Например запрашивая несуществующий ID, ты ожидаешь, что он добавится). 
- Запрос делает больше чем от него можно ожидать. 
- Запросы должны быть атомарными
7) Для большого запроса рекомендуется использовать 
- пагинацию или фрагментацию
- Уменьшение размера ответов
- Использовать кеш

[к оглавлению](architect.md#Architect)

## Оценка ёмкости хранилищ

### Само видео
- Час видео в среднем на ютубе весит 200 мегабайт. 200/60 = 3.3 мегабайт/ минута
- (**1 миллиард пользователей** * **1/1000** (те кто загружает видео)) * 10 (средняя длина видео).
(1000000000*1/1000)*10 = 10 000 000 минут в день

- 10 000 000 * 3,3 = 33 000 000 мегабайт = 33 терабайта в день.
- Желательно хранить копии видео. Умножаем на 3 и того 100 терабайт в день.
- Видео должны быть в разных разрешениях. 720 + 480+320+240+144 в среднем займёт как 2 видео 720. 
И того 200 терабайт в день.

![icon][youtube-capacity]

Тут много допущений. Например, размер видео и количество пользователей каждый день, прцоент загрузок.
На самом деле ютуб сообщал о 600 терабайт в день.


### Кеш
Это картинка превью видео, плюс текстовые данные описания, информация об авторе и прочее.
10кб(превью) * 90 (просматриваемых превью в день) * 1000000 видео = 1Тб памяти для кеша.

Выгоднее брать компьютеры по 16гигов оперативки. И того 64 компьютеров для кеша. 
Умножаем на 3 для реплик и еще на 2 про запас. И того оно 500 машин.

### Процессоры
1 000 000 видео в день. Каждое по 10 минут и того 1 * 10^7минут видео в день надо обработать.
Это 10^4/3 часов видео в день. Это 40 мегабайт в секунду. Умножаем на 10, 
чтобы обработать видео в разные форматы и разрешения. И того 400мб/сек
Процессор должен - считать видео с диска + обработать видео + записать назад.
10 мс(считать данные) + 20мс(обработать видео) + 20 мс(записать данные). И того 50мс на обработка 1мб данных.
Это 1с на 20мб данных. Это значит нужно 20 процессоров. Умножаем на 2 про запас. И того 40.

![icon][processor-time]

[к оглавлению](architect.md#Architect)

## Publisher Subscriber Model

Использования очередей

1) Decouples a system's services.
2) Easily add subscribers and publishers without informing the other.
3) Converts multiple points of failure to single point of failure.
4) Interaction logic can be moved to services/ message broker.

Disadvantages:
1) An extra layer of interaction slows services
2) Cannot be used in systems requiring strong consistency of data
3) Additional cost to team for redesigning, learning and maintaining the message queues.

[к оглавлению](architect.md#Architect)

[Заглавная](README.md)
