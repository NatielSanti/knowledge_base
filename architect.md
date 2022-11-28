[Заглавная](README.md)

# Architect
+ [IaaS, SaaS, PaaS](#IaaS,-SaaS,-PaaS)
+ [Fault tolerant microservice](#Fault-tolerant-microservice)
+ [Event Sourcing](#Event-Sourcing)
+ [DDD](#DDD)
+ [CQRS](#CQRS)
+ [Enterprise integration patterns](#Enterprise-integration-patterns)

[example-1]:img/dist_systems/example-1.png
[2pc-ok]:img/dist_systems/2pc-ok.png
[2pc-fail]:img/dist_systems/2pc-fail.png
[saga-ok]:img/dist_systems/saga-ok.png
[saga-fail]:img/dist_systems/saga-fail.png
[soa-vs-msa]:img/dist_systems/soa-vs-msa.png

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

[Заглавная](README.md)
