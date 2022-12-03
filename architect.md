[Заглавная](README.md)

# Architect

+ [IaaS, SaaS, PaaS](architect.md#IaaS,-SaaS,-PaaS)
+ [Fault tolerant microservice](architect.md#Fault-tolerant-microservice)
+ [Event Sourcing](architect.md#Event-Sourcing)
+ [DDD](architect.md#DDD)
+ [CQRS](architect.md#CQRS)
+ [Enterprise integration patterns](architect.md#Enterprise-integration-patterns)
+ [Fault tolerant microservice](architect.md#Fault-tolerant-microservice)
+ [MS vs Monolith](architect.md#MS-vs-Monolith)
+ [MS vs SOA](architect.md#SOA-vs-MSA)
+ [Spring Cloud](architect.md#Spring-Cloud)
+ [Patterns for distributed transactions](architect.md#Patterns-for-distributed-transactions)
+ [Kafka](architect.md#Kafka)
+ [UML](architect.md#UML)
+ [Software Architectural Patterns](architect.md#Software-Architectural-Patterns)

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

[//]: # ([docker_1]:img/microservices/docker_1.JPG)
[//]: # (![icon][docker_1])
[Continuous-integration-vs-delivery-vs-deployment.png]:img/ops/Continuous-integration-vs-delivery-vs-deployment.png

[к оглавлению](#Architect)

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

[к оглавлению](#Architect)

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

[к оглавлению](#Architect)

## Fault tolerant microservice
- Timeouts (сделай за 3 секунды. Может отправить запрос дальше в цепочку сервисов и ошибка будет только на обратнмо пути)
- Retries (3 раза и всё)
- Circuit Breaker (много ошибок - подняли новый)
- Deadlines (сделай до 15-00, не успел - верни ошибку сразу)
- Rate limiters (1000 запросов в секунду)

[к оглавлению](#Architect)


## DDD

![icon][domain]
![icon][aggregate]

[к оглавлению](#Architect)

## CQRS

- лучше использовать когда много сложных запросов, большая нагрузка на чтение
  ![icon][cqrs]

[к оглавлению](#Architect)

## Event Sourcing

- События не изменяются, не удаляются
- Изредка делается snapshot (для увеличения производительности, например)
- в событии хранится только дельта изменений, а так же тип изменения (например, nameChanged, jobRemoved)
- лучше использовать когда много мелких событий и конечное состояние не так важно
- историчность, отчетность, быстрота чтения и записи
- лучше не использовать когда событий мало, важно финальное состояние, много сложных запросов
- С большим монолитом доменом лучше не использовать

![icon][eventsourcing_cqrs]

[к оглавлению](#Architect)

## Enterprise integration patterns

### Integration Styles

<ul>
<li>
File Transfer
</li>
<li>
Shared Database
</li>
<li>
Remote Procedure Invocation
</li>
<li>
Messaging
<ul>
    <li>Message Channel
    </li>
    <li>
    Message
    </li>
<li>
Pipes and Filters
</li>
<li>
Message Router
</li>
<li>
Message Translator
</li>
<li>
Message Endpoint
</li>
</ul>
</li>
</ul>

[ссылка](#https://www.enterpriseintegrationpatterns.com/patterns/messaging/toc.html)

[к оглавлению](#Architect)

## Fault tolerant microservice
- Timeouts (сделай за 3 секунды. Может отправить запрос дальше в цепочку сервисов и ошибка будет только на обратнмо пути)
- Retries (3 раза и всё)
- Circuit Breaker (много ошибок - подняли новый)
- Deadlines (сделай до 15-00, не успел - верни ошибку сразу)
- Rate limiters (1000 запросов в секунду)

[к оглавлению](#Architect)

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

[к оглавлению](#Architect)

##SOA vs MSA

![icon][soa-vs-msa]

[к оглавлению](#Architect)

## Spring Cloud

![icon][spring-cloud]

[к оглавлению](#Architect)

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

[к оглавлению](#Architect)

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

[к оглавлению](#Architect)

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

[к оглавлению](#Architect)

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

[к оглавлению](#Architect)

[Заглавная](README.md)
