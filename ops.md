[Заглавная](README.md)

# DEVOPS
+ [Continuous integration vs delivery vs deployment](#Continuous-integration-vs-delivery-vs-deployment)
+ [Patterns for distributed transactions](#Patterns-for-distributed-transactions)
+ [SOA vs MSA](#SOA-vs-MSA)
+ [Kubernetes](#Kubernetes)
+ [IaaS, SaaS, PaaS](#IaaS,-SaaS,-PaaS)

[example-1]:img/microservices/example-1.png
[2pc-ok]:img/microservices/2pc-ok.png
[2pc-fail]:img/microservices/2pc-fail.png
[saga-ok]:img/microservices/saga-ok.png
[saga-fail]:img/microservices/saga-fail.png
[soa-vs-msa]:img/microservices/soa-vs-msa.png

[kube1]:img/microservices/kubernates/node-pod.png
[kube2]:img/microservices/kubernates/pod-types.png
[kube3]:img/microservices/kubernates/selector.png
[kube4]:img/microservices/kubernates/selector2.png
[kube5]:img/microservices/kubernates/service.png
[kube6]:img/microservices/kubernates/K8scheatsheet.jpg

[iaassaaspaas]:img/ops/iaassaaspaas.png
[//]: # ([docker_1]:img/microservices/docker_1.JPG)
[//]: # (![icon][docker_1])
[Continuous-integration-vs-delivery-vs-deployment.png]:img/ops/Continuous-integration-vs-delivery-vs-deployment.png

[к оглавлению](#DEVOPS)

## Continuous integration vs delivery vs deployment

![icon][Continuous-integration-vs-delivery-vs-deployment.png]

### Непрерывная интеграция
#### Что от вас потребуется (расходы)
Вашей команде придется писать автоматические тесты для каждой новой функции, 
улучшения или баг-фикса.
Необходим сервер непрерывной интеграции, 
который может отслеживать основной репозиторий и автоматически запускать 
тесты для каждого нового отправленного коммита.
Разработчикам необходимо выполнять слияние своих изменений как можно чаще, 
как минимум один раз в день.
#### Что вы получите
В рабочую среду попадает меньше багов, поскольку за счет автоматических 
тестов ухудшения обнаруживаются на ранних этапах.
Когда все проблемы интеграции решаются на ранних этапах, сборка релиза проходит легко.
Переключаться на другой контекст приходится реже, 
так как разработчики получают предупреждение сразу же, как только нарушат сборку, 
и могут поработать над исправлением, прежде чем перейти к другой задаче.
Радикально снижаются затраты на тестирование: 
ваш CI‑сервер может выполнять сотни тестов за несколько секунд.
Команда контроля качества тратит меньше времени на тестирование и может сосредоточиться 
на повышении культуры качества.
###Непрерывная поставка
####Что от вас потребуется (расходы)
Для непрерывной интеграции нужна прочная основа. 
Ваш комплект тестов должен покрывать достаточную часть базы кода.
Развертывания необходимо автоматизировать. Хотя запуск все еще осуществляется вручную, 
после начала развертывания вмешательство человека требоваться не должно.
Скорее всего, вашей команде понадобится освоить флаги возможностей, чтобы возможности, 
работа над которыми не завершена, не влияли на работу клиентов.
####Что вы получите
Отныне развертывание ПО перестало быть сложным. 
Вашей команде больше не нужно тратить несколько дней на подготовку к релизу.
Можно чаще выпускать релизы, ускоряя цикл обратной связи со своими клиентами.
Решения по поводу небольших изменений принимаются без лишнего напряжения, 
что способствует ускорению итераций.
###Непрерывное развертывание
####Что от вас потребуется (расходы)
Ваша культура тестирования должна быть на самом высоком уровне. 
Качество вашего комплекта тестов будет определять качество ваших релизов.
Процесс документирования должен идти в ногу с темпами развертываний.
Флаги возможностей становятся неотъемлемой частью процесса выпуска серьезных изменений. 
Они обеспечивают возможность координировать работу с другими отделами 
(такими как поддержка, маркетинг, PR и т. д).
####Что вы получите
Вы сможете ускорить процесс разработки, так как вам не нужно будет прерывать его на время релизов. 
Конвейеры развертывания срабатывают автоматически при каждом внесении изменений.
Сокращается количество рисков, связанных с релизами, 
и облегчается выпуск фиксов в случае появления проблем, 
поскольку каждое развертывание осуществляется после внесения сравнительно небольшого 
количества изменений.
Клиенты видят непрерывный поток улучшений, при этом качество повышается каждый день, 
а не раз в месяц, квартал или год.
[к оглавлению](#DEVOPS)

## Patterns for distributed transactions

In a monolithic system, we have a database system to ensure ACIDity. We now need to clarify the following key problems.

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

[к оглавлению](#DEVOPS)

##SOA vs MSA

![icon][soa-vs-msa]

[к оглавлению](#DEVOPS)

##Kubernetes

![icon][kube6]
![icon][kube1]
![icon][kube2]
![icon][kube3]
![icon][kube4]
![icon][kube5]

[к оглавлению](#DEVOPS)

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

[к оглавлению](#DEVOPS)

[Заглавная](README.md)