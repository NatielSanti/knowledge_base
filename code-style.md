[Заглавная](README.md)

# Code Style

+ [Creating a project from scratch](code-style.md#Creating-a-project-from-scratch)
+ [OWASP top 10](code-style.md#OWASP-top-10)
+ [Работа с Legacy](code-style.md#Работа-с-Legacy)
+ [Чистый код](code-style.md#Чистый-код)
+ [New technology implementation](code-style.md#New-technology-implementation)
+ [Code Coverage](code-style.md#Code-Coverage)
+ [Code Review](code-style.md#Code-Review)
+ [Из MLM alfa](code-style.md#Из-MLM-alfa)

[к оглавлению](#Code-Style)

## Creating a project from scratch

First questions
- A project is unique
- A project is dynamic
- A project is based on a management method which is defined in
  the initial planning stages
- A project is always subject to a run time and resources available
- A project seeks a specific objective from the start
- A project is not a routine operation like a process

[ссылка](https://www.sinnaps.com/en/project-management-academy/creating-project-from-scratch)

[к оглавлению](#Code-Style)

## OWASP top 10

В последнем отчете OWASP перечислены 10 основных уязвимостей:

- Инъекции (Injections).
- Нарушенная аутентификация (Broken Authentication).
- Раскрытие критически важных данных (Sensitive Data Exposure).
- Внешние объекты XML (XXE) (XML External Entities (XXE)).
- Нарушенный контроль доступа (Broken Access control).
- Неправильная конфигурация безопасности (Security misconfigurations).
- Межсайтовый скриптинг (XSS) (Cross Site Scripting (XSS)).
- Небезопасная десериализация (Insecure Deserialization).
- Использование компонентов с известными уязвимостями (Using Components with known vulnerabilities).
- Недостаточно подробные журналы и слабый мониторинг (Insufficient logging and monitoring).

[ссылка](https://proglib.io/p/chto-takoe-top-10-owasp-i-kakie-uyazvimosti-veb-prilozheniy-naibolee-opasny-2021-09-09)

[к оглавлению](#Code-Style)

## Работа с Legacy

### Отмазки
- Мы делали “просто” сайт, а теперь вы хотите получить новую “плюшку”, 
и нам нужно все это переписать, так как у нас легаси...
- Никто не знает, как это работает..
- Чтобы добавить модуль, необходимо весь сайт проверить — только так мы поймем, 
что и где может вылезти...
- Я туда не полезу ни в коем случае, там уже все плохо…

### Проблемы по сложности начиная с самой простой

1) Нет техдокументации.
2) Нет бизнес-документации.
3) Нет никого, кто это разрабатывал.
4) Мы не знаем, что должен получить пользователь в конечном итоге.
5) 200+ usage каждой функции и они называются getA.
6) Отсутствуют программисты, которые хотели бы/могли бы разрабатывать.

### Плохие доводы для бизнеса 
- Нанимаем новую команду для качественной “распилки” кода.
- Останавливаем добавление фич, теперь только рефакторинг
- У меня на WP уже все необходимое есть, нужно только базу мигрировать

### Аргументы для переговоров с бизнесом

1) Готов ли бизнес развиваться?
2) Стадия прототипа?
3) Разработка завершена и теперь только поддержка?
4) Хватает ли огня в наших глазах и не потухнет ли он?
5) Есть ли архитектор?

### П — Планирование

Для быстрой и эффективной работы с техническим долгом нужен план.

1. *Определение задач, на которых будем паразитировать.* 
Почему паразитировать? Часто бывает: мы продали фичу, 
а под ней частично подразумеваем рефакторинг.

2. *Определение требований высокого уровня.* 
Необходимо прописать четкий запрос бизнеса по “высокой планке”, 
дабы составить правильную документацию.

3. *Определение основных модулей системы, наложение модулей.* 
Перед стартом рефакторинга, нужно понять основные модули системы: где, как, 
что и с чем может взаимодействовать и разграничивать код на секции.

4. *Определение интеграции.* 
При создании конкретного модуля мы должны заранее продумать возможность 
вмонтировать его в соседний легаси.

5. *Определение команды.* 
Один в поле рефакторинга не воин. 
Команда — очень важный элемент успешного результата. Задор, 
командный драйв и отличное взаимодействие между участниками процесса must have.

6. *Как будем тестить?* 
Если вы собираетесь сдавать качественный проект, 
то нужно заранее продумать и варианты тестирования продукта.
   
### Советы по коду
1. *Не изобретать велосипед.* Для меня это главный лайфхак по написанию кода. 
Уже все придумано до вас, не стоит прибегать к танцам с бубнами и прочим 
экспериментам для решения вопросов с legacy code.

2. *Код стандарт.* Без единого стандарта каждый разработчик будет писать по-своему. 
“Авторский стиль” поспособствует хаосу и нарастит еще больше код-хлама.

3. *Код ревью.* Мало только одного наличия код-стандарта. 
Нужно еще, чтобы в команде был ответственный за его проверку. 
Иначе все вернется на круги своя, то есть к уровню старого кода.

4. *Static Code analyzers, PHP mess detector etc (вместо тысячи книг).* 
Эти и другие автоматические виды техники понадобятся для ускорения процесса, 
в частности с тем же код ревью.

5. *Пробуем микросервисы.* 
Отдельно также могут быть модули или библиотеки. 
Почему именно микросервисы? Их преимущество в максимальной изоляции логики и 
ограничении ее определенным API. Плюсом последней является то, 
что API представляет собой более монолитную сущность по сравнению с 
“адаптером в коде, которым можно поправить”. 
Однако у API есть один недочет в виде дополнительных расходов на сеть.

6. *Архитектура БД, источники данных.* 
Именно базу данных считаю первым “узким” 
местом любого легаси. Но каждый проектирует, как хочет, 
и даже в SQL можно найти неафишируемые недостатки.

7. *Nо SQL?* Если с архитектурной точки зрения вы можете оперировать сущностями 
— оперируйте. Понятие чего-то определённого и конечного поможет вам не создавать 
ненужные, дублирующие релляции.

8. *Декораторы, адаптеры, медиаторы …* Эти паттерны одни из главных для интеграции 
нового кода в старое легаси.

9. *План Б, или план отката при интеграции.* Многие делают ошибку, 
что забывают о нем. Он жизненно необходим в ситуации “когда что-то пойдет не так” 
при заливке нового материала. То есть как только мы начинаем строить архитектуру, 
уже на этом этапе мы должны понимать, как будем откатывать ее назад в случае бага.

10. *Новый код без (доки) тестов через неделю становится легаси.* 
Насколько красивым ни был бы ваш код, без документации через неделю он будет 
в статусе “legacy” — по причине своей непонятности.

11. *Тестирование.* Если юнит-тесты не по карману, то используем смоук, 
функциональные и интеграционные тесты. Насколько реально продать юнит-тесты 
бизнесу под соусом “чтобы сделать работу красиво?”. 
В наших реалиях это скорее редкость, нежели закономерность. 
Если же с “юнитами” по какой-то причине не складывается, то обращаемся к смоук, 
функциональным или интеграционным тестам, а также не забываем, 
что можем делегировать задачу, например, мануальному тестировщику.
   
[ссылка](https://habr.com/ru/post/431562/)

[к оглавлению](#Code-Style)

## New technology implementation

Steps for Implementing Technology in the Workplace
1) Research and Assess the Tech You Need. 
It's always important to research before you buy.
2) Develop an Implementation Blueprint.
3) Consider Assembling an Implementation Team.
4) Map Out the System.
5) Train All Employees.
6) Launch and Monitor the New Technology.

[к оглавлению](#Code-Style)

## Чистый код

1. ***Используйте линтер совместно с IDE*** н-р SonarLint
2. ***Правильный баланс комментариев***
   Объясняйте то, что код делает, а не определяет.
3. ***Автоматизация тестирования***  Unit, integration, e2e
4. ***Обзор кода вручную*** Код ревью
5. ***Quality Gates*** 

### Structural Code Quality characteristics

- **clear** – simple and clean, easy to understand by other
- **easy to maintain** - simple change don’t requires a lot of time 
or code rework
- **follow project guidelines** – allows to ensure consistency between 
developers, updated deliver fast and easier

### Pros of CC

- Easy to edit and understand, save time of devs
- Reduce number of defects
- Project functions properly
- Easier for new developers, reduce onboarding time
- Reduce software development risks and code complexity

### Risks of poor CC

- More time to understand, increase risk of incorrect implementations for devs
- More defects
- More time to implement new features
- Smts need to be rewritten

[к оглавлению](#Code-Style)

## Code Coverage

CC = lines of code executed with unit test / total number of code lines
- (+) Code coverage metrics may be useful to track the high-level project trend of 
unit testing activities
- (+) Identify areas of project with low coverage
- (-) Code coverage does not give any insight into what code is tested or the 
quality of the executed tests.

[к оглавлению](#Code-Style)

## Code Review

### Best practices
- The key goals of code review are to identify initial development bugs and 
facilitate a maintainable codebase

### Benefits
- Fewer defects – to identify structural errors (e.g. dead code, logic or algorithm bugs,
performance or architecture concerns, etc.) and functional errors 
(when code does not work as expected
- Knowledge sharing
- Consistent standards
- Compliance
- Feedback and grow for developers

### Risk of neglecting
- Lower structural code quality
- Lower functional code quality
- Lack of knowledge sharing
- Possible rework
- Possible tech issues

### Code review types
- Peer review
- Specialist review
- Instant code review (pair programming)

### Key areas of Code review
- **Functional correctness / Business logic**
  - Check if code author implemented all requested behaviors.
- **Structural correctness / Design**
  - Handle enough edge(corner) cases
  - Shorten/Faster/Safer
  - Replaced with more effective functional equivalent
  - Appropriate usage of patterns
  - Redundancies are eliminated
  - Dependencies are needed or worthwhile
  - Follow other clean code principles
- **Readability / Complexity**
  - Am I able to grasp the concepts in a reasonable amount of time?
  - Is the flow logical, and are the variables and the method names easy to follow?
  - Am I able to keep track through multiple files or functions?
  - Is there any inconsistent naming that is confusing?
  - Is the code consistent with the project in terms of style, API conventions, etc.?
  - Does this code have TODO comments?
- **Test correctness**
  - Read the tests. If there are no tests, but there should be, 
  ask the author to write them. While truly untestable features are rare, 
  untested implementations of features are unfortunately common.
  - Check the tests themselves. Are they covering edge cases? Are they readable? 
  Does the code change lower overall test coverage?
  - Think of ways the code could break and check if they are handled. 
  Does the change request introduce the risk of breaking test code, staging stacks, 
  or integration tests? Specific things to look for are changes in artifact 
  layout/structure and removal of test utilities or path changes in configuration.
  - Verify consistency. Style standards for tests are often different from the core code, 
  but they are still important.
  - Check if there is enough documentation or if the test covers all use 
  cases of business logic.
- **Non-functional requirements**
  - Security vulnerabilities
  - Common weakness – weak configuration, malicious input
  - Library usage, etc

### Code Review Checklist doc

- Useful for reviewers
- Quality metrics
- For newcomers

### Checklist best practices

- Require a different number of reviewers with a range of expertise. 
For KT it is better to engage more reviewers, including new or juniors. 
focusing on quality or security standards requires experienced devs.
- Peer review should be mandatory for each piece of code your team produces, 
while a specialist’s review is mandatory for critical parts only.
- Pay special attention to high-risk code that implements critical business logic, 
is performance-critical, or handles sensitive data.
- Checklists are living documents that evolve, and every team member 
must be responsible for updating them.
- Checklists must be accessible to everyone on the team through a shared space

### Tips for MR

- Limit changes per merge request
- Set time limit
- Person responsible for initial code review
- SonarQube and other code analysis tools
- Tests are OK

### Ethical aspects

- Be polite, positive and friendly
- Based on well-defined code standards
- Use checklist
- Constructive communication

### Best practice

- Practice mandatory peer code review on a pull/merge request basis.
- Identify how to choose and assign reviewers in the code review strategy 
for your project
- Work with your team to develop a code review checklist: a guide that every team 
member reviews regularly.
- Ensure that during the code review process, all team members:
  - Follow the coding standards.
  - Validate business logic.
  - Validate unit tests for changed logic as a part of the code review process.
  

[к оглавлению](#Code-Style)

## Knowledge Sharing

### Impacts of Unhealthy Knowledge Sharing

- Unclear Project Priorities - double work because team members might complete 
unnecessary tasks or complete tasks in the wrong way
- Unclear Project Standards - miscommunication and misunderstandings can arise, 
quality of work diminishing
- Waste of Project Resources (human, time, and money) – too much time for investigations, 
searching for task implementation, other people involved into discussions
- Bus Factor
- Missing Information – it can be easily lost or misunderstood during communication
- Ineffective Onboarding – lengthy and challenging.

### Benefits

- A Stable Team, Alignment Through Documented Standards, 
Available Comprehensive Information, Smooth Onboarding


### Effective Team’s Knowledge Sharing

- **Development process**
  - Review artifacts with rotating peers
  - Diversify task assignments
  - Keep breadcrumbs of your work – useful info
  - Plan time for documentation
- **Knowledge base**
  - The project goal and description
  - The project team structure and roles
  - Links to project resources
  - Links to environments
  - Project standards, rules, and conventions (for all competencies: 
  Managers, Developers, Quality Assurance Engineers (QA), Business Analysts (BA), 
  DevOps Engineers, etc.)
  - The development process description (branching, release process, 
  task life cycle, etc.)
- **Onboarding procedures**
  - A newcomer’s guidebook 
  - Assigning a mentor
- **Team communication**
  - Conduct knowledge sharing sessions/sync-ups
  - Keep team chats focused and diverse
  - Post important updates in news channels
  - Participate in project townhalls
  - Send meeting follow-ups

### Improvements when?
You have new ideas, find less sharable info, knowledge existed only in verbal form, 
discover missing info in documentation, find outdated pages

### Best Practices

- Establish a knowledge sharing process among development team members.
- Use a technical knowledge base stored in a collaboration system 
(Confluence, Wiki, etc.).
- Keep the technical knowledge base up to date, so it properly reflects the 
current project details.
- Ensure team members pick development tasks for all parts of the system evenly 
to prevent situations where some parts of the system are known by only one person 
(Bus Factor > 1).
- Support your project onboarding with a newcomer’s handbook or guide that 
covers the project system, teams, and development setup.
  - Update the handbook so that it properly reflects the current project details.
  - Coach or mentor newcomers throughout the onboarding period.

### Из MLM alfa

#### DB

При создание сервиса с db необходимо создать scheme db и пользователей для подключений сервиса, заводится заявкой при поставке
Требования к сервису со взаимодействию с DB

    модуль db в проекте для хранения скриптов миграции db
    скрипты миграции liquibase в формате xml
    sql скрипт для создания схемы и пользователь сервиса и выдача нужных прав, в корне папка sql
    Использовать Hibernate SEQUENCE-генератор id, использование иного не поддерживает многие полезные функции(пример, hibernate batch)

Требования в скриптам миграции liquibase

    Структура пакетов
    Нумерация версий в зависимости от релизной политике сервиса
    пакеты - v<major>.<minor>
    changelog - v<major>.<minor>_<порядковый номер>_<наименование>, <наименование> - логическая группировка по сущности
    Наименование changeSet в формате id=v<major>.<minor>_<порядковый номер changelog>_<наименование changeSet>
    Обязательно указывать changeSet.author в формате Ivan_Ivanov
    Обязательно начинать changeSet с проверок preCondition
    Параметры выносить в корневой changelog
    <property name="schema" value="cms"/>
    <property name="prefix" value="cms_"/>

Правила preCondition onFail

https://docs.liquibase.com/concepts/changelogs/preconditions.html#onFail/onError

Основные варианты, которые используются в большинстве проверок
MARK_RAN	Skips over the changeset but mark it as executed. Continues with the changelog.
WARN	Sends a warning and continues executing the changeset / changelog as normal

MARK_RAN - использовать в случае когда проверка требует единого выполнения а не скрипт для миграции/исполнения функции с множеством запусков,
к примеру создание таблиц, обновление таблицы/столбцов требуется выполнить один раз и при дальнейшем запуске миграции не стоит еще раз делать проверку, так как мы подразумеваем что если скрипт уже запускался и выполнился или нет не влияет на текущую миграцию

WARN - использовать в случае когда скрипт миграции может не исполниться в текущий момент, но требует выполнения в следующий раз пока проверка не будет успешна и скрипт не завершится с успехом(пометит в логе как пройденный)

Остальные варианты рассматривать как уникальные кейс и проводить технический анализ
Pool connections

hikari:
connection-timeout: 20000 #maximum number of milliseconds that a client will wait for a connection
minimum-idle: 10 #minimum number of idle connections maintained by HikariCP in a connection pool
maximum-pool-size: 10 #maximum pool size
idle-timeout: 10000 #maximum idle time for connection
max-lifetime: 30000 # maximum lifetime in milliseconds of a connection in the pool after it is closed.
auto-commit: false #default auto-commit behavior.

#### GIT

На свой компьютер требуется выполнить минимальный набор команд для настройки(с указанием своих данных на латинице):

git config --global user.name <Ivan Ivanov>
git config --global user.email test@alfabank.ru
Ветки

master - отражение прода, содержит теги с названиями(номерами) релизов и хотфиксов.

НЕ ИСПОЛЬЗУЕТСЯ РАЗРАБОТКОЙ, ТОЛЬКО CI


develop - отражения разработки, содержит актуальный master и feature не вошедшие в текущий release, но пойдут в будущие релизы.

НЕ ИСПОЛЬЗУЕТСЯ РАЗРАБОТКОЙ, ТОЛЬКО ЧЕРЕЗ PULL REQUEST и MERGE CI


feature/<номер задачи>_<наименование>, например, feature/MLM-2000_new_api - создается от ветки develop и release, в ней выполняется основная разработка по новому функционалу, техдолгу, рефакторингу кода.

Вливается в develop или release ТОЛЬКО ЧЕРЕЗ PULL REQUEST


release/<номер релиза>, например, release/1.0- релизная ветка, которая создается от develop и в нее сливаются все feature, которые должны быть в этом релизе.

При определение нового release при открытом текущем, отводится новый release от текущего, например release/3.1 от release/3.0, не имеется права выходить в прод раньше родительского, если родительская актуальна на этот момент(не удалена/закрыта)

После поставки и закрытие вливается в develop и master и все актуальные(параллельные) release

НЕ ИСПОЛЬЗУЕТСЯ РАЗРАБОТКОЙ, ТОЛЬКО ЧЕРЕЗ PULL REQUEST и MERGE CI


hotflix/<номер хотфикса> - создается от master для выполнения хотфиксов прода, в нее сливаются все bugfix которые необходимо поставить на прод в рамках этого hotflix

После поставки и закрытие вливается в develop и master и все актуальные release

НЕ ИСПОЛЬЗУЕТСЯ РАЗРАБОТКОЙ, ТОЛЬКО ЧЕРЕЗ PULL REQUEST и MERGE CI


bugfix/<номер задачи>_<наименование>, например, bugfix/MLM-2001_fix_npe - создается от ветки develop, release, hotflix, в ней выполняется работа по исправлению ошибок.

Вливается в develop, release, hotflix ТОЛЬКО ЧЕРЕЗ PULL REQUEST
Нумерация и релизная политика

Формат [major].[minor].[patch].[build]-[rc/beta/SNAPSHOT] пример версия 1.0.0.1-rc релиз кандидат сборка 1 от ветки release/1.0

major и minor отличается важностью и этапом проекта, согласуется руководством.

patch не используется в release, фиксирует версии в рамках hotflix, hotflix с правками ошибок release/1.0 исправляются в рамках hotflix/1.0.1

build номер сборки в рамках ветки

rc/beta/SNAPSHOT метки помечающие тип сборки
Требования

В наименование PULL REQUEST указывать номер и наименование задачи, свое или jira, пример MLM-2000: new post api

В commit message указывать номер задачи и описание, свое или jira, пример MLM-2000: new post api

В описание ПР добавлять минимальное описание выполненных работ, проверять наличие связи с Jira задачей, пример new rest api for post and refactoring code

Предпочтительный язык английский, в крайних случаях, сложное описание или сложности перевода, на русском

Максимальное количество коммитов = 3

Обязательный SQUASHED COMMITS перед оформлением ПР в случае если они выполняют содержат общие изменения.

[Заглавная](README.md)
