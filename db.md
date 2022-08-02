[Заглавная](README.md)

# DB

+ [Языки структуры БД](db.md#Языки-структуры-БД)
+ [Типы БД](db.md#Типы-БД)
+ [CAP theory](db.md#CAP-theory)
+ [Пессимистическая и оптимистическая блокировка](db.md#Пессимистическая-и-оптимистическая-блокировка)
+ [Типы индексов](db.md#Indexes)
+ [Optimizing queries](db.md#Optimizing-queries)

[sql-type]:img/db/sql_type.PNG
[sql-nosql]:img/db/sql-nosql.PNG
[cap]:img/db/cap.PNG

## Типы БД

![icon][sql-nosql]

[к оглавлению](#DB)

## Языки структуры БД

![icon][sql-type]

[к оглавлению](#DB)

## CAP theory

- согласованность данных (англ. consistency) — во всех вычислительных узлах в один момент времени данные 
не противоречат друг другу;
- доступность (англ. availability) — любой запрос к распределённой системе завершается корректным откликом, 
однако без гарантии, что ответы всех узлов системы совпадают;
- устойчивость к разделению (англ. partition tolerance) — расщепление распределённой системы на 
несколько изолированных секций не приводит к некорректности отклика от каждой из секций.

Базы данных могут выполнять только 2 из трёх условий:
- *CA* (`Availability + Consistency – Parition tolerance`), 
когда данные во всех узлах кластера согласованы и доступны, но не устойчивы к разделению.
Это означает, что реплики одной и той же информации, распределенные по разным серверам друг другу, 
не противоречат друг другу и любой запрос к распределённой системе завершается корректным откликом. 
Такие системы возможны при поддержке ACID-требований к транзакциям 
(`Атомарность, Согласованность, Изоляция, Долговечность`) и абсолютной надежности сети. 
На практике таких решений на основе кластерных систем управления базами данных почти не существует. 
Классическим примером CA-системы называют распределённую службу каталогов LDAP, 
а также реляционные базы данных (`PostgreSQL, MySQL, MariaDB, MS SQL Server`).
- *CP-система* (`Consistency + Partition tolerance – Availability`) в каждый момент обеспечивает 
целостность данных и способна работать в условиях распада в ущерб доступности, не выдавая отклик на запрос. 
Устойчивость к разделению требует дублирования изменений во всех узлах системы, 
что реализуется с помощью распределённых пессимистических блокировок для сохранения целостности. 
По сути, CP – это система с несколькими синхронно обновляемыми мастер-базами. 
Она всегда корректна, отрабатывая транзакцию, только в том случае, 
если изменения удалось распространить по всем серверам. 
Она продолжает корректно читать данные даже при отказе одного из узлов кластера. 
Но в этом случае запись будет обрываться или сильно задерживаться, 
пока система не убедится в своей целостности и согласованности (консистентности). 
Из NoSQL-СУБД к CP-системам принято относить 
`Apache HBase, MongoDB, Redis, MemcasheDB, Berkley DB, HyperTable и Google Big Table`.
- *AP-система* (`Availability + Partition tolerance – Consistency`) не гарантирует целостность данных, 
обеспечивая их доступность и устойчивость к разделению, например, как в распределённых веб-кэшах и DNS. 
Считается, что большинство NoSQL-СУБД относятся к этому классу систем, 
обеспечивая лишь некоторой уровень согласованности данных в конечном счете (`eventually consistent`). 
Таким образом, AP-система может быть представлена кластером из нескольких узлов, 
каждый из которых может принимать данные, но не обязуется в тот же момент распространять их на другие сервера. 
Такая система отлично справляется с отказами нескольких узлов, но, когда они снова начинают работать, 
возможна выдача пользователям старых данных. К AP-системам относят `CoucheDB, Cassandra, Riak, Amazon DynamoDB`.

![icon][cap]

[к оглавлению](#DB)

## Пессимистическая и оптимистическая блокировка

### Пессимистическое управление параллелизмом

Система блокировок не допускает, чтобы изменение данных одними пользователями влияло на других пользователей.
После того как действие пользователя приводит к блокировке, до тех пор пока инициатор ее не снимет, 
другие пользователи не могут выполнять действия, которые могут вызвать конфликт с блокировкой. 
Это называется пессимистическим управлением, поскольку в основном применяется в средах с 
большим количеством состязаний данных, где затраты на защиту данных с помощью блокировок меньше затрат 
на откат транзакций в случае конфликтов параллелизма.

### Оптимистическое управление параллелизмом

При оптимистическом управлении параллелизмом пользователи не блокируют данные на период чтения. 
Когда пользователь обновляет данные, система проверяет, вносил ли другой пользователь в них изменение 
после считывания. Если другой пользователь изменял данные, возникает ошибка. Как правило, 
при получении сообщения об ошибке пользователь откатывает транзакцию и начинает ее заново. 
Это называется оптимистическим управлением, поскольку в основном применяется в средах с 
небольшим количеством состязаний данных, где затраты на периодический откат транзакции меньше 
затрат на блокировку данных при считывании.

[к оглавлению](#DB)

## Indexes

#### Хеш таблицы
Hash-индексы были предложены Артуром Фуллером, и предполагают хранение не самих значений, 
а их хэшей, благодаря чему уменьшается размер(а, соответственно, и увеличивается скорость их обработки) 
индексов из больших полей. Таким образом, при запросах с использованием HASH-индексов, 
сравниваться будут не искомое со значения поля, а хэш от искомого значения с хэшами полей.
Из-за нелинейнойсти хэш-функций данный индекс нельзя сортировать по значению, 
что приводит к невозможности использования в сравнениях больше/меньше и «is null». 
Кроме того, так как хэши не уникальны, то для совпадающих хэшей применяются методы разрешения коллизий.

#### B-Tree

Семейство B-Tree индексов — это наиболее часто используемый тип индексов, 
организованных как сбалансированное дерево, упорядоченных ключей. 
Они поддерживаются практически всеми СУБД как реляционными, так нереляционными, 
и практически для всех типов данных.

Так как большинство, наверное, их хорошо знает(или могут прочесть о них например, здесь), 
то единственное, что, пожалуй, следует здесь отметить, это то, 
что данный тип индекса оптимален для множества с хорошим распределением значений и 
высокой мощностью(cardinality-количество уникальных значений).

#### Bitmap

Bitmap index – метод битовых индексов заключается в создании отдельных битовых карт 
(последовательность 0 и 1) для каждого возможного значения столбца, 
где каждому биту соответствует строка с индексируемым значением, а его значение равное 1 означает, 
что запись, соответствующая позиции бита содержит индексируемое значение для данного столбца или свойства.

[к оглавлению](#DB)

## Масштабирование SQL и NoSQL
Описанные ниже схемы масштабирования применимы как для реляционных баз данных, 
тах и для NoSQL-хранилищ. Разумеется, что у всех баз данных и хранилищ есть своя специфика, 
поэтому мы рассмотрим только основные направления и в детали реализации вдаваться не будем.

#### Партиционирование (partitioning)
Партиционирование — это разбиение таблиц, содержащих большое количество записей, 
на логические части по неким выбранным администратором критериям. 
Партиционирование таблиц делит весь объем операций по обработке данных на несколько 
независимых и параллельно выполняющихся потоков, что существенно ускоряет работу СУБД. 
Для правильного конфигурирования параметров партиционирования необходимо, 
чтобы в каждом потоке было примерно одинаковое количество записей.

Например, на новостных сайтах имеет смысл партиционировать записи по дате публикации, 
так как свежие новости на несколько порядков более востребованы и чаще требуется работа именно с ними, 
а не со всех архивом за годы существования новостного ресурса.

#### Репликация (replication)
Репликация — это синхронное или асинхронное копирование данных между несколькими серверами. 
Ведущие сервера называют мастерами (master), а ведомые сервера — слэйвами (slave). 
Мастера используются для изменения данных, а слэйвы — для считывания. 
В классической схеме репликации обычно один мастер и несколько слэйвов, 
так как в большей части веб-проектов операций чтения на несколько порядков больше, 
чем операций записи. Однако в более сложной схеме репликации может быть и несколько мастеров.

Например, создание нескольких дополнительных slave-серверов позволяет снять с основного сервера 
нагрузку и повысить общую производительность системы, 
а также можно организовать слэйвы под конкретные ресурсоёмкие задачи и таким образом, 
например, упростить составление серьёзных аналитических отчётов — 
используемый для этих целей slave может быть нагружен на 100%, 
но на работу других пользователей приложения это не повлияет.

#### Шардинг (sharding)
Шардинг — это прием, который позволяет распределять данные между разными физическими серверами. 
Процесс шардинга предполагает разнесения данных между отдельными шардами на основе некого ключа шардинга. 
Связанные одинаковым значением ключа шардинга сущности группируются в набор данных по заданному ключу, 
а этот набор хранится в пределах одного физического шарда. Это существенно облегчает обработку данных.

Например, в системах типа социальных сетей ключом для шардинга может быть ID пользователя, 
таким образом все данные пользователя будут храниться и обрабатываться на одном сервере, 
а не собираться по частям с нескольких.

Партиционирование, репликация и шардинг — три основных подхода к масштабированию баз данных. 
Они позволяют обеспечить повышение быстродействия приложения и повысить устойчивость к высоким нагрузкам.

[к оглавлению](#DB)

## Optimizing queries

12 Query optimization tips for better performance
- Tip 1: Add missing indexes
- Tip 2: Check for unused indexes
```roomsql
SELECT
  *
FROM TestTable
WHERE IntColumn = '1';
```
When executing this query, SQL Server will perform implicit data type conversion, 
i.e. convert int data to varchar and run the comparison only after that. 
In this case, indexes won’t be used. 
How can you avoid this? We recommend using the CAST() 
function that converts a value of any type into a specified datatype. 
Look at the query below.
```roomsql
SELECT
  *
FROM TestTable
WHERE IntColumn = CAST(@char AS INT);
```
```roomsql
SELECT
  *
FROM TestTable
WHERE DATEPART(YEAR, SomeMyDate) = '2021';
```
In this case, implicit data type conversion will take place too, 
and the indexes won’t be used. 
To avoid this, we can optimize the query in the following way:
```roomsql
SELECT
  *
FROM TestTable
WHERE SomeDate >= '20210101'
AND SomeDate < '20220101'
```
- Tip 3: Avoid using multiple OR in the FILTER predicate
```roomsql
SELECT
  *
FROM USER
WHERE Name = @P
OR login = @P;
```
If we split this query into two SELECT 
queries and combine them by using the UNION operator, 
SQL Server will be able to make use of the indexes, and the query will be optimized.
```roomsql
SELECT * FROM USER
WHERE Name = @P
UNION
SELECT * FROM USER
WHERE login = @P;
```
- Tip 4: Use wildcards at the end of a phrase only
  Wildcards serve as a placeholder for words and phrases and can be 
added at the beginning/end of them. To make data retrieval faster and more efficient, 
you can use wildcards in the SELECT statement at the end of a phrase. For example:
```roomsql
SELECT
  p.BusinessEntityID
 ,p.FirstName
 ,p.LastName
 ,p.Title
FROM Person.Person p
WHERE p.FirstName LIKE 'And%';
```
However, you might encounter situations where you regularly need 
to search by the last symbols of a word, number, or phrase—for example, 
by the last digits of a telephone number. 
In this case, we recommend creating a persisted computed column and running the 
REVERSE() function on it for easier back-searching.
```roomsql
CREATE TABLE dbo.Customer (
  id INT IDENTITY PRIMARY KEY
 ,CardNo VARCHAR(128)
 ,ReversedCardNo AS REVERSE(CardNo) PERSISTED
)
GO

CREATE INDEX ByReversedCardNo ON dbo.Customer (ReversedCardNo)
GO
CREATE INDEX ByCardNo ON dbo.Customer (CardNo)
GO

INSERT INTO dbo.Customer (CardNo)
  SELECT
    NEWID()
  FROM master.dbo.spt_values sv

SELECT TOP 100
  *
FROM Customer c

--searching for CardNo that end in 510c
SELECT
  *
FROM dbo.Customer
WHERE CardNo LIKE '%510c'

SELECT
  *
FROM dbo.Customer
WHERE ReversedCardNo LIKE REVERSE('%510c')
```
- Tip 5: Avoid too many JOINs
  
When you add multiple tables to a query and join them, you may overload it.
In addition, a large number of tables to retrieve data from may result 
in an inefficient execution plan. When generating a plan, 
the SQL query optimizer needs to identify how the tables are joined, in which order, 
how and when to apply filters and aggregation.

JOIN elimination is one of the many techniques to achieve efficient query plans. 
You can split a single query into several separate queries which can later be joined, 
and thus remove unnecessary joins, subqueries, tables, etc.
- Tip 6: Avoid using SELECT DISTINCT
  However, this may require the tool to process large volumes of data and as a result, 
make the query run slowly. Generally, it is recommended to avoid using 
SELECT DISTINCT and simply execute the SELECT statement but specify columns.
- Tip 7: Use SELECT fields instead of SELECT *
- Tip 8: Use TOP to sample query results
```roomsql
SELECT TOP 5
  p.BusinessEntityID
 ,p.FirstName
 ,p.LastName
 ,p.Title
FROM Person.Person p
WHERE p.FirstName LIKE 'And%';
```
- Tip 9: Run the query during off-peak hours
- Tip 10: Minimize the usage of any query hint
- Tip 11: Minimize large write operations
- Tip 12: Create joins with INNER JOIN (not WHERE)
```roomsql
SELECT
  d.DepartmentID
 ,d.Name
 ,d.GroupName
FROM HumanResources.Department d
INNER JOIN HumanResources.EmployeeDepartmentHistory edh
  ON d.DepartmentID = edh.DepartmentID
```
The INNER JOIN statement returns all matching rows from joined tables, 
while the WHERE clause filters the resulting rows based on the specified condition. 
Retrieving data from multiple tables based on the WHERE keyword condition 
is called NON-ANSI JOINs while INNER JOIN belongs to ANSI JOINs.

There is no difference for SQL Server how you write the query – 
using ANSI or NON-ANSI joins – it’s just much easier to understand and analyze 
queries written using ANSI joins. You can clearly see where the JOIN conditions 
and the WHERE filters are, whether you missed any JOIN or filter predicates, 
whether you joined the required tables, etc.
```roomsql
SELECT
  d.Name
 ,d.GroupName
 ,d.DepartmentID
FROM HumanResources.Department d
    ,HumanResources.EmployeeDepartmentHistory edh
WHERE d.DepartmentID = edh.DepartmentID
```

[ссылка](https://blog.devart.com/how-to-optimize-sql-query.html)

[к оглавлению](#DB)

[Заглавная](README.md)
