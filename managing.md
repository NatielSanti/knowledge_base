[Заглавная](README.md)

# Managering

+ [Choosing methodology](#Choosing-methodology)
+ [Agile](#Agile)
+ [Scrum](#Scrum)
+ [Extreme Programming](managing.md#Extreme-programming)
+ [Техники оценки задач](managing.md#Техники-оценки-задач)
+ [SDLC](managing.md#SDLC)
+ [NFR and FR](managing.md#NRF-and-FR)
+ [Доверие клиента](#Доверие-клиента)
+ [New technology implementation](#New-technology-implementation)
+ [Падение качества продукта](#Падение-качества-продукта)

[к оглавлению](#Managering)

[sdlc]:img/manage/sdlc.png
[xp]:img/manage/xp.png
[choose_methodology]:img/manage/methodologies-flowchart.png
[agile]:img/manage/agile.png

## Choosing methodology

![icon][choose_methodology]

### When does **waterfall** ok?

- The project/requirements/customer demand is not prone to change
- There is no need customer feedback
- The project is small in scope
- The speed of delivery is not a priority

### Agile Frameworks

- Scrum
- Kanban
  - Devops
  - Support
  - Avoid bottlenecks
- Lean
- XP (extreme programming)
  - user stories, refactoring, pair programming
  - frequent releases, small teams
  - continuous value delivery is a high priority.
- FDD (feature driven dev)
  - Focus on stakeholders, large projects
- Crystal
  - Small teams, less documentation, reporting, micromanagement
- DSDM (dyn systems dev method)
  - feasibility + business studies
  - Reduce risk for budget

### Scrum vs Kanban

1) The time-boxed sprints are optional in Kanban, unlike scrum where 
iterations are time-bound to 2-4 weeks.
2) Work is pulled from the backlog, as a single unit, as and when needed, 
unlike Scrum where we pick up batches of work from the product.
3) In addition to the above, the client can add new features during an ongoing 
iteration, unlike Scrum, where no addition of new features can happen 
once an iteration starts.
4) Multiple teams share the Kanban board.
5) Moreover, there are no defined roles in Kanban like Product Owner (PO), 
Scrum Master (SM) or Scrum team, etc.

[к оглавлению](#Managering)

## Agile

- Agile is a value-driven approach to developing solutions. 
- It is focused on people, adaptability, and providing maximum value to customers.

![icon][agile]

### Mindset
1) Put Customer First
2) Trust In the Team - working in a self-managed team.
3) Emphasize Experimentation - creativity and look for opportunities 
to experiment and innovate

### Values & Principles – Agile Manifesto
1) Individuals and interactions over processes and tools.
2) Working software over comprehensive documentation.
3) Customer collaboration over contract negotiation - 
building a customer feedback loop into the development life cycle.
4) Responding to change over following a plan - 
dynamic strategy allowing the team to adjust its priorities and plans - 
sprints, change priorities, incremental delivery

- priority is to satisfy the customer through early and continuous 
delivery of valuable software.
- Welcome changing requirements, even late in development
- Deliver working software frequently
- Businesspeople and developers must work together daily throughout the project.
- Build projects around motivated individuals
- information to and within a development team is face-to-face conversation.

Three key areas: **adaptability**, **user experience**, and **testing**

[к оглавлению](#Managering)

## Scrum

### Principles
- **Transparency** – all team members involved, daily scrum
- **Inspection** – team prioritize task to achieve high quality scrum, 
sprint review
- **Adaptation** – flex accommodate all customer needs

### Values
- **Commitment** (achieve the goal)
- **Focus** (on the work in sprint)
- **Openness** (to changes)
- **Respect** (each other)
- **Courage** (to do right thing)

### Scrum Team (Roles)

1) **Development Team**
  - Creating a plan for the **Sprint**, the **Sprint Backlog**;
  - the team sets a **Definition of Done**;
  - Adapting their plan each day toward the Sprint Goal; and,
  - Holding each other accountable as professionals.
  
2) **Product Owner** - in a product development team is responsible 
for managing the product backlog to achieve the desired result that 
a team seeks to accomplish. Key activities to accomplish this include:
  - Clearly expressing Product Backlog items.
  - Ordering the items in the Product Backlog to best achieve goals and missions.
  - Optimizing the value of the work the Development Team performs.
  - Ensuring that the Product Backlog is visible, transparent, and clear to all, 
and shows what the Scrum Team will work on next.
  - Ensuring the Development Team understands items in the Product Backlog to 
the level needed.

3) **Scrum Master** - The Scrum Master is accountable for establishing 
Scrum as defined in the Scrum Guide. They do this by helping everyone understand 
Scrum theory and practice, both within the Scrum Team and the organization. 
The responsibilities of a Scrum Master include:
   - Clearing obstacles
   - Establishing an environment where the team can be effective
   - Addressing team dynamics
   - Ensuring a good relationship between the team and the Product Owner 
   as well as others outside the team
   - Protecting the team from outside interruptions and distractions.

### Artifacts
1) **Product Backlog** – Here, the Product owner meets the client and takes 
down all the requirements. 
A document referred to as Product Backlog captures these requirements 
Product Goal, Product backlog refinements
   - Product Goal - describes a future state of the product which can serve 
   as a target for the Scrum Team to plan against
2) **Sprint Backlog**- the set of Product Backlog items selected for the Sprint. 
Is is a plan by and for the Developers
   - Sprint Goal - is the single objective for the Sprint. 
   Although the Sprint Goal is a commitment by the Developers, 
   it provides flexibility in terms of the exact work needed to achieve it.
3) **Increment** – An Increment is a concrete stepping stone toward the 
Product Goal. Multiple Increments may be created within a Sprint. 
The sum of the Increments is presented at the Sprint Review thus supporting 
empiricism.
   - Definition Of Done - The Definition of Done is a formal description of 
   the state of the Increment when it meets the quality measures required for 
   the product.

### Events – need to adapt scrum artifacts
- The Sprint – one iteration which consist of set of other events.
- Sprint Planning
- Daily Scrum
- Sprint Review
- Sprint Retrospective
- Grooming
- Demonstration

[к оглавлению](#Managering)

## Extreme programming

![icon][xp]

[к оглавлению](#Managering)

## Техники оценки задач

- ####*1. Трехбалльная оценка*
   Проблема с выполнением оценки до начала любой работы заключается в том, 
что она может быть крайне неточной. Даже если это та же команда и тот же тип работы, 
оценки могут быть нереальными. Особенно, если вы попадаете в ловушку мысли: 
«Мы уже делали это раньше, поэтому во второй раз будем намного быстрее».

Вот что делает трехточечную оценку такой полезной. 
У вас есть три значения: наиболее вероятная оценка, плюс оптимистичная и пессимистичная.

Оттуда вы получите наиболее точное число, сложив значения и разделив их на три.

- ####*2. Планирование покера*
   Покер планирования лучше всего подходит для небольшого количества предметов. Э
то полезный метод, который быстро получает единодушное одобрение. 
Каждый член команды получает набор специально пронумерованных карточек, обсуждает требования к элементу, 
а затем анонимно представляет свою лучшую оценку. Обсуждение происходит до тех пор, 
пока не будет достигнут консенсус.

Этот подход идеально подходит для 10 или менее задач и групп до восьми человек.

- ####*3. Группировка по интересам*
   Этот подход к оценке работает, когда члены команды группируют похожие элементы. 
Если задачи кажутся связанными по объему и трудозатратам, вы объединяете их, 
пока не получите четкий набор групп.

Вы можете использовать тот же набор значений, что и в других методах (последовательность Фибоначчи), 
или сделать группы более широкими, чтобы они были ближе к методу большого, малого и неопределенного.

- ####*4. Случайное распределение*
   Этот метод, также известный как протокол заказа, размещает элементы в порядке от меньшего к большему. 
Каждый человек по очереди выбирает, переместить предмет вверх или вниз на одно место, 
обсудить предмет или передать.

Ваш процесс завершается, когда все пасуют в свой ход.

- ####*5. Размеры футболок (единицы оценки)*
   XS, S, M, L, XL — это единицы, которые вы будете использовать для оценки Agile-проектов для этой техники.
Одна из причин успеха этого подхода заключается в том, что он отходит от стандартных единиц времени и, 
таким образом, может помочь командам мыслить более критически.

Здесь вашей единицей оценки может быть что угодно, 
и нестандартное мышление может помочь вашей команде объективно сравнивать элементы для получения 
более точных оценок.

- ####*6. Ведра*
   Подобно покеру планирования, метод ведра направлен на достижение консенсуса 
посредством обсуждения и присвоения ценности каждой задаче. 
Фасилитатор начинает с одной задачи, ставит ее посередине, а затем продолжает читать и 
раскладывать задачи по корзинам относительно первой. 
Команда может разделить задачи и разделить их на группы, прежде чем снова собраться и просмотреть.

Обсуждение является ключом к тому, чтобы убедиться, что все согласны, 
прежде чем будут установлены окончательные оценки. Вы также позаботитесь о том, 
чтобы задачи были распределены равномерно, а не, скажем, меньше 200.

- ####*7. Большой, маленький, неуверенный*
   Быстрый, простой и продуктивный, этот метод похож на группирование, 
но с назначением только трех возможных значений. 
Вы начнете с обсуждения, а затем разделите и властвуйте, чтобы добавить все задачи в большие, 
малые или неопределенные группы.

- ####*8. Точечное голосование*
   Это довольно просто: каждый человек получает определенное количество точек и использует их, 
чтобы проголосовать за большие и малые проекты. Больше точек означает, 
что требуется больше времени и усилий. 
Меньшее количество точек указывает на довольно простой и быстрый элемент.

Какой из них вы попробуете первым? При желании вы можете использовать другой метод в 
начале любой дорожной карты или квартала.

[к оглавлению](#Managering)

## SDLC

![icon][sdlc]

[к оглавлению](#Managering)

## NFR and FR

описывает, что должна делать программная система, 
в то время как нефункциональные требования накладывают ограничения на то, 
как система будет это делать.

Примером *функционального требования* будет:

- Система должна отправлять электронное письмо всякий раз, 
когда выполняется определенное условие (например, заказ сделан, клиент подписан и т.д.).

Связанное *нефункциональное требование* к системе может быть:

- Письма должны быть отправлены с задержкой не более 12 часов 
после такой активности.

Функциональное требование описывает поведение системы, поскольку 
оно относится к функциональности системы. Бизнес требования.

Нефункциональное требование разрабатывает характеристику производительности 
системы. Оптимизация, стабильность, чистый код

Обычно *нефункциональные требования8 относятся к таким областям, как:

- доступность
- Емкость, ток и прогноз
- податливость
- Документация
- Аварийное восстановление
- КПД
- эффективность
- растяжимость
- Отказоустойчивость
- Interoperability
- Ремонтопригодность
- Конфиденциальность
- портативность
- Качественный
- надежность
- упругость
- Время отклика
- прочность
- Масштабируемость
- Безопасность
- стабильность
- Supportability
- способность быть свидетелем в суде

Нефункциональные требования иногда определяются в терминах метрик 
(то есть того, что можно измерить в системе), чтобы сделать их более осязаемыми. 
Нефункциональные требования могут также описывать аспекты системы, 
которые не связаны с ее выполнением, а скорее с ее развитием во времени 
(например, ремонтопригодность, расширяемость, документация и т.д.).

FR  достигается путём юнит тестирования и функциональног тестирования.
NFR достигается путём код-ревью, статических анализаторов.

[к оглавлению](#Managering)

## Доверие клиента

1) ***Быстрее и лучше***

Лучше озвучивать точные сроки и суммы, потому что и вариант, 
когда вы сделали всё быстрее и вариант когда вы сделали всё медленнее плох, 
так как подрывает ваш профессионализм в глазах заказчика

2) ***Подготовка к переговорам***

Здесь речь идёт о тактике переговоров. Соблюдать баланс уверенности, тактичности, 
скорости речи

3) ***Признавайте свои старые ошибки***
4) ***Предложите новую стратегию взаимодействия***
5) ***Не зацикливайтесь на старых проблемах***
6) ***Не пытаться приуменьшить прошлые проблемы***

Да разве это были проблемы? Ну ерунда же.

[ссылка](https://habr.com/ru/post/90600/)

[к оглавлению](#Managering)

## Падение качества продукта

8 основных причин, почему в растущем проекте падает качество

1) ***Команда перестала понимать, какую ценность приносит продукту***.

Возникает когда команда не получает фидбэка от заказчика, а лишь выполняет задачи ПМа.
Необходимо объяснить для чего нужна та или иная задача.

2) ***Стратегия нацелена только на ближайшую перспективу***

Уделяйте время для проговаривания с отделами разработки, дизайна, QA, 
куда сейчас движется компания, 
например: «Мы хотим освоить новую целевую аудиторию».

3) ***Бизнес и разработка не синхронизированы***

Проблемы появляются, когда технические требования описаны исключительно в 
категориях бизнеса, например, в виде user story: «Я как пользователь должен 
иметь возможность оплатить заказ с помощью Google Рay». 
Тогда у команды слишком много простора для творчества, потому что описана не задача,
а очень высокоуровневый фреймворк. И не всегда решение, которое придумает разработчик,
совпадет с потребностью заказчика.

В команде нужен человек, который будет переводить бизнес-запросы в цели, 
а затем «мапить» в функциональные требования для разработчиков.

4) ***Дисбаланс в руководстве***

Например, в руководстве много технарей и они оптимизируют всё, 
чтобы сделать эффективный 
и качественный продукт и забывают про дизайн, удобство, маркетинг.

Хорошо, когда в руководстве есть люди, что могут правильно оценить дизайн, 
разработку и бизнес-часть.

5) ***Инструменты не годятся для масштабирования***

Если начинаете с маленького стартапа, то определитесь с моментом, 
когда нужно перейти на другие инструменты, и спланируйте время для переноса данных.

6) ***Процессы не подготовлены для роста***

Если заранее не продумать, как масштабировать процессы, 
появляется риск уйти в одну из сторон:

- В полный хаос
- В полную бюрократию

7) ***Расплывчатые культурные ценности в компании***

Основные группы отличий, из-за которых возникают конфликты: язык, религия, 
политические воззрения, сексуальная ориентация и частная жизнь. 
Обсуждение любой из этих тем почти всегда ведет к проблемам в коммуникации, 
так что старайтесь их избегать.

8) ***Неправильный рекрутинг***

[к оглавлению](#Managering)

[Заглавная](README.md)
