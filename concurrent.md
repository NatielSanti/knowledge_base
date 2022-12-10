[Заглавная](README.md)

# Java Concurrent

+ [Проблемы многопоточки: ABA, DCL](concurrent.md#Проблемы-многопоточки)
+ [Основные характеристики Thread](concurrent.md#Основные-характеристики-Thread)
+ [Потоки через Runnable лучше](concurrent.md#Потоки-через-Runnable-лучше)
+ [Методы потоков: yield(), sleep(), interrupt(), join(), Понятие Монитор, Wait, notify и notifyAll, Жизненный цикл потока, LockSupport и парковка потоков](concurrent.md#Методы-потоков)
+ [Взаимодействие потоков и проблемы: Deadlock, Livelock, Starvation, Race Condition, Volatile, Атомарность, Happens Before](concurrent.md#Взаимодействие-потоков-и-проблемы)
+ [Мьютекс, монитор и семафор](concurrent.md#Мьютекс-монитор-и-семафор)
+ [CompletableFuture](concurrent.md#CompletableFuture)
+ [Executor и ThreadPool](concurrent.md#Executor-и-ThreadPool)
+ [Locks](concurrent.md#Locks)

[markword]:img/concurrent/markword.PNG
[semaphore]:img/concurrent/semaphore.PNG
[atomic]:img/concurrent/atomic.PNG
[happens-before]:img/jmm/happens-before.png
[semaphore]:img/concurrent/semaphore.PNG
[livelock]:img/concurrent/livelock.PNG
[happens-before-2]:img/concurrent/happens-before-2.png
[happens-before-3]:img/concurrent/happens-before-3.png
[jmm]:img/concurrent/jmm.png
[jmm-2]:img/concurrent/jmm-2.png
[jmm-3]:img/concurrent/jmm-3.png

## Проблемы многопоточки

### ABA

Проблема с Атомиками. Текущий поток держит ссылку на `A` и хочет её перезаписать. 
В это время до `CAS` операции другой поток изменил значение `A` на `B`, а другой поток потом с `B` на `A`.
Когда наш поток придёт делать операцию CAS ссылка будет схожа с той прошлой, но не обязательно той же самой, 
возможно у неё будет изменено состояние. Для решения было создано `AtomicMarkableReference`.

### DCL Singleton

Double checked locking
 
```java
// Однопоточная версия
class Foo {
    private Helper helper = null;
    public Helper getHelper() {
        if (helper == null)
            helper = new Helper();
        return helper;
    }
    // и остальные члены класса…
}
```
Этот код не будет корректно работать в многопоточной программе. Метод `getHelper()` должен получать блокировку на случай,
 если его вызовут одновременно из двух потоков. Действительно, если поле `helper` ещё не инициализировано, 
 и одновременно два потока вызовут метод `getHelper()`, то оба потока попытаются создать объект, 
 что приведет к созданию лишнего объекта. Эта проблема решается использованием синхронизации, 
 как показано в следующем примере.
 
```java
// Правильная, но "дорогая" по времени выполнения многопоточная версия
class Foo { 
    private Helper helper = null;
    public synchronized Helper getHelper() {
        if (helper == null)
            helper = new Helper();
        return helper;
    }
    // и остальные члены класса…
}
```

Этот код работает, но он вносит дополнительные накладные расходы на синхронизацию. 
Первый вызов `getHelper()` создаст объект, и нужно синхронизировать только те несколько потоков, 
которые будут вызывать `getHelper()` во время инициализации объекта. 
После инициализации синхронизация при вызове `getHelper()` является излишней, 
так как будет производиться только чтение переменной. 
Так как синхронизация может уменьшить производительность в 100 раз и более, 
накладные расходы на блокировку при каждом вызове этого метода кажутся излишними: 
как только инициализация завершена, необходимость в блокировке отпадает. 
Многие программисты попытались оптимизировать этот код следующим образом:

1) Сначала проверяется, инициализирована ли переменная (без получения блокировки). Если она инициализирована, 
её значение возвращается немедленно.
2) Получение блокировки.
3) Повторно проверяется, инициализирована ли переменная, так как вполне возможно, 
что после первой проверки другой поток инициализировал переменную. Если она инициализирована, 
её значение возвращается.
4) В противном случае, переменная инициализируется и возвращается.

```java
// Некорректно работающая (в Symantec JIT и версиях Java 1.4 и ранее) многопоточная версия
// Шаблон "Double-Checked Locking"
class Foo {
    private Helper helper = null;
    public Helper getHelper() {
        if (helper == null) {
            synchronized(this) {
                if (helper == null) {
                    helper = new Helper();
                }
            }
        }
        return helper;
    }
    // и остальные члены класса…
}
```

На интуитивном уровне, этот код кажется корректным. Однако существуют некоторые проблемы 
(в версии `Java 1.4` и ранее и нестандартных реализациях `JRE`), которых, возможно, следует избегать. 
Представим себе, что события в многопоточной программе протекают так:

Поток А замечает, что переменная не инициализирована, затем получает блокировку и начинает инициализацию.
Семантика некоторых языков программирования такова, что потоку А разрешено присвоить разделяемой переменной 
ссылку на объект, который находится в процессе инициализации (что в общем-то вполне однозначно нарушает 
причинно-следственную связь, ведь программист вполне явно просил присваивать переменной ссылку на объект
(то есть — опубликовать ссылку в общий доступ) — в момент после инициализации, а не в момент до инициализации).
Поток Б замечает, что переменная инициализирована (по крайней мере, ему так кажется), 
и возвращает значение переменной без получения блокировки. 
Если поток Б теперь будет использовать переменную до того момента, когда поток А закончит инициализацию, 
поведение программы будет некорректным.
Одна из опасностей использования блокировки с двойной проверкой в `J2SE 1.4` (и более ранних версиях) состоит в том, 
что часто кажется, что программа работает корректно. Во-первых, рассмотренная ситуация будет возникать не очень часто;
 во-вторых, сложно отличить корректную реализацию данного шаблона от такой, которая имеет описанную проблему. 
 В зависимости от компилятора, распределения планировщиком процессорного времени для потоков, 
 а также природы других работающих конкурентных процессов, ошибки, 
 спровоцированные с некорректной реализацией блокировки с двойной проверкой, обычно происходят бессистемно. 
 Воспроизведение таких ошибок обычно затруднено.

Можно решить проблему при использовании `J2SE 5.0`. Новая семантика ключевого слова `volatile` даёт возможность 
корректно обработать запись в переменную в данном случае.

```java
// Работает с новой семантикой volatile
// Не работает в Java 1.4 и более ранних версиях из-за семантики volatile
class Foo {
    private volatile Helper helper = null;
    public Helper getHelper() {
        if (helper == null) {
            synchronized(this) {
                if (helper == null)
                    helper = new Helper();
            }
        }
        return helper;
    }
    // и остальные члены класса…
}
```

Возможно лучшая версия.
Потому что в момент создания инстанса, есть момент, когда инстанс не `null`, 
но поля еще не заполнены. Для решения этой проблемы - вводится новая временная переменная, 
которая копирует в ссылку `singleton` уже полностью сконфигурированный инстанс.
Представим ситуацию, когда первый поток еще не заполнил поля, но уже создал объект, 
в это время второй поток забирает ссылку на не до конца сконфигурированный объект.
Сокращает количество чтений из статических полей.

Еще вводится приватный лок, который нельзя утащить из этого объекта наружу.

```java
class Singleton {
    
    private static volatile Singleton INSTANCE = null;
    private static final Object lock = new Object();
    
    private String name;
    private int age;
    
    public static Singleton getInstance() {
        Singleton temp = INSTANCE;
        if (temp == null) {
            synchronized(lock) {
                temp = INSTANCE;
                if (temp == null){
                    INSTANCE = temp = new Singleton("name", age);
                }
            }
        }
        return temp;
    }
    
    private Singleton(String name, int age){
        this.name = name;
        this.age = age;
    }
}
```

Еще есть варианты через `ENUM`.
Инстанциируется во время обращения к классу из-за логики работы ENUM.

```java
public enum Singleton{
    INSTANCE("name", 12);
 
    private final String name;
    private final int age;
    
    Singleton(String name, int age){
        this.name = name;
        this.age = age;
    }
    
    public static void staticMethod(){
        System.out.println("do static method");
    }

    public void nonStaticMethod(){
         System.out.println("do nonstatic method");
    }
}

class Main{
    public static void main(String[] args) {
        Singleton.staticMethod();
        Singleton.INSTANCE.nonStaticMethod();
    }
}
```

Через вложенный статический класс.
Инстанс синглтона не будет создан при обращении к статическому методу, но будет 
создан при обращении к внутреннему статическому классу `SingletonCreator`.
Потокобезопасность гарантируется тем, что загрузка классов потокобезопасна.

```java
public class Singleton{
    
    private Singleton(){}
 
    public static void doSomething(){
        System.out.println("Something");
    }
    
    private static Singleton getInstance(){
        return SingletonCreator.INSTANCE; // при обращении к статик методу инстанс не будет создан
    }
    
    private static class SingletonCreator {
        private static Singleton INSTANCE = new Singleton();
    }
}
```


[к оглавлению](concurrent.md#Java-Concurrent)

## Основные характеристики Thread

Потоки могут быть объединены в группы, а те с свои группы. 
Это позволяет создавать иерархию и задавать верние и нижние границы приоритета потоков в группе.

```java
public static void main(String []args){
	Thread currentThread = Thread.currentThread();
	ThreadGroup threadGroup = currentThread.getThreadGroup();
	System.out.println("Thread: " + currentThread.getName());
	System.out.println("Thread Group: " + threadGroup.getName());
	System.out.println("Parent Group: " + threadGroup.getParent().getName());
}
```
Обработчик исключений.
```java
public static void main(String []args) {
	Thread th = Thread.currentThread();
	th.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
		@Override
		public void uncaughtException(Thread t, Throwable e) {
			System.out.println("Возникла ошибка: " + e.getMessage());
		}
	});
    System.out.println(2/0);
}
```

[к оглавлению](concurrent.md#Java-Concurrent)

## Потоки через Runnable лучше

- Вариант с потомком от Thread плох уже тем, что мы в иерархию классов включаем Thread. 
- Второй минус — мы начинаем нарушать принцип "Единственной ответственности" SOLID, т.к. 
наш класс становится одновременно ответственным и за управление потоком и за некоторую задачу, 
которая должна выполняться в этом потоке.
- А ещё Runnable является функциональным интерфейсом начиная с Java 1.8. 
Это позволяет писать код задач для потоков ещё красивее:

[к оглавлению](concurrent.md#Java-Concurrent)

## Методы потоков

### yield()
`yield` лишь передаёт некоторую рекомендацию планировщику потоков Java, 
что данному потоку можно дать меньше времени исполнения. Но что будет на самом деле, 
услышит ли планировщик рекомендацию и что вообще он будет делать — 
зависит от реализации JVM и операционной системы.

### sleep()

Программист не может взаимодействовать с этим планировщиком напрямую из Java кода, 
но он может через JVM попросить планировщик на какое-то время поставить поток на паузу, "усыпить" его. 
Поток перешёл в статус `Sleeping`. Необходимо оборачивать `InterruptedException`.
```java
try {
	TimeUnit.SECONDS.sleep(60);
	System.out.println("Waked up");
} catch (InterruptedException e) {
	e.printStackTrace();
}
```

### interrupt()

Всё дело в том, что пока поток ожидает во сне, кто-то может захотеть прервать это ожидание. 
На этот случай мы обрабатываем такое исключение. Сделано это было после того, 
как метод `Thread.stop` объявили `Deprecated`, т.е. устаревшим и нежелательным к использованию. 
Причиной тому было то, что при вызове метода stop поток просто "убивался", что было очень непредсказуемо. 
Мы не могли знать, когда поток будет остановлен, не могли гарантировать консистентность данных. 

Представте, что вы пишете данные в файл и тут поток уничтожают. 
Поэтому, решили, что логичнее будет поток не убивать, а информировать его о том, что ему следует прерваться. 
Как на это реагировать — дело самого потока.

```java
public static void main(String []args) {
	Runnable task = () -> {
		try {
			TimeUnit.SECONDS.sleep(60);
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
		}
	};
	Thread thread = new Thread(task);
	thread.start();
	thread.interrupt();
}
```

В этом примере мы не будем ждать 60 секунд, а сразу напечатаем 'Interrupted'. 
Всё потому, что мы вызвали у потока метод `interrupt`. 
Данный метод выставляет "internal flag called interrupt status". 
То есть у каждого потока есть внутренний флаг, недоступный напрямую. 
Но у нас есть native методы для взаимодействия с этим флагом.

Но это не единственный способ. Поток может быть в процессе выполнения, не ждать чего-то, 
а просто выполнять действия. Но может предусмотреть, что его захотят завершить в определённый момент его работы. 
Например:

```java
public static void main(String []args) {
	Runnable task = () -> {
		while(!Thread.currentThread().isInterrupted()) {
			//Do some work
		}
		System.out.println("Finished");
	};
	Thread thread = new Thread(task);
	thread.start();
	thread.interrupt();
}
```

В примере выше видно, что цикл `while` будет выполняться до тех пор, пока поток не прервут снаружи.

Про флаг isInterrupted важно знать то, что если мы поймали `InterruptedException`, 
флаг `isInterrupted` сбрасывается, и тогда `isInterrupted` будет возвращать `false`. 
Есть также статический метод у класса Thread, который относится только к текущему потоку — `Thread.interrupted()`, 
но данный метод сбрасывает значение флага на `false`!

### join()

Самым простым типом ожидания является ожидание завершения другого потока.
В данном примере новый поток будет спать 5 секунд. В то же время, главный поток `main` будет ждать, 
пока спящий поток не проснётся и не завершит свою работу.
Бросает `InterruptedException`

```java
public static void main(String []args) throws InterruptedException {
	Runnable task = () -> {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
		}
	};
	Thread thread = new Thread(task);
	thread.start();
	thread.join();
	System.out.println("Finished");
}
```

Благодаря средствам мониторинга можно увидеть, что просиходит с потоком. 
Метод `join` довольно прост, потому что является просто методом с `java` кодом, который выполняет `wait`, 
пока поток, на котором он вызван, живёт. Как только поток умирает (при завершении), ожидание прерывается. 
Вот и вся магия метода `join`. Поэтому, перейдём к самому интересному.

### Понятие Монитор

У каждого объекта в `Java` есть заголовок (`header`) — своего рода внутренние метаданные, 
которые недоступны программисту из кода, но которые нужны виртуальной машине, чтобы работать с объектами правильно.

В состав заголовка объекта входит `MarkWord`, которое выглядит следующим образом:

![icon][markword]

```java
public class HelloWorld{
    public static void main(String []args){
        Object object = new Object();
        synchronized(object) {
            System.out.println("Hello World");
        }
    }
}
```

Итак, при помощи ключевого слова `synchronized` текущий поток (в котором выполняются эти строки кода) 
пытается использовать монитор, ассоциированный с объектом `object` и "получить лок" или "захватить монитор" 
(второй вариант даже предпочтетельнее). Если за монитор нет соперничества (т.е. никто больше не хочет выполнить 
`synchronized` по такому же объекту), Java может попытаться выполнить оптимизацию, называемую "`biased locking`". 
В заголовке объекта в `Mark Word` выставится соответствующий тэг и запись о том, к какому потоку привязан монитор. 
Это позволяет сократить накладные расходы при захватывании монитора.

Если монитор уже ранее был привязан к другому потоку, тогда такой блокировки недостаточно. 
`JVM` переключается на следующий тип блокировки — `basic locking`. 
Она использует `compare-and-swap` (CAS) операции. При этом в заголовке в `Mark Word` уже хранится не сам `Mark Word`, 
а ссылка на его хранение + изменяется тэг, чтобы `JVM` поняла, что у нас используется базовая блокировка.

Если же возникает соперничество (`contention`) за монитор нескольких потоков (один захватил монитор, 
а второй ждёт освобождение монитора), тогда тэг в `Mark Word` меняется, 
и в `Mark Word` начинает храниться ссылка уже на монитор как объект — некоторую внутреннюю сущность `JVM`. 
Как сказано в `JEP`, в таком случае требуется место в Native Heap области памяти на хранение этой сущности. 
Ссылка на место хранения этой внутренней сущности и будет находиться в `Mark Word` объекта.

Таким образом, как мы видим, монитор — это действительно механизм обеспечения синхронизации 
доступа нескольких потоков к общим ресурсам. Существует несколько реализаций этого механизма, 
между которыми переключается `JVM`. Поэтому для простоты, говоря про монитор, мы говорим на самом деле про локи.

### Wait, notify и notifyAll

У `Thread` есть ещё один метод ожидания, который при этом связан с монитором. 
В отличие от `sleep` и `join`, его нельзя просто так вызвать. И зовут его `wait`.

Выполняется метод `wait` на объекте, на мониторе которого мы хотим выполнить ожидание.

```java
public static void main(String []args) throws InterruptedException {
	    Object lock = new Object();
	    // task будет ждать, пока его не оповестят через lock
	    Runnable task = () -> {
	        synchronized(lock) {
	            try {
	                lock.wait();
	            } catch(InterruptedException e) {
	                System.out.println("interrupted");
	            }
	        }
	        // После оповещения нас мы будем ждать, пока сможем взять лок
	        System.out.println("thread");
	    };
	    Thread taskThread = new Thread(task);
	    taskThread.start();
        // Ждём и после этого забираем себе лок, оповещаем и отдаём лок
	    Thread.currentThread().sleep(3000);
	    System.out.println("main");
	    synchronized(lock) {
	        lock.notify();
	    }
}
```

### Жизненный цикл потока
Как мы видели, поток в процессе жизни меняет свой статус. По сути эти изменения и являются жизненным циклом потока.

Когда поток только создан, то он имеет статус `NEW`. 
В таком положении он ещё не запущен и планировщик потоков Java (`Thread Scheduler`) ещё не знает ничего о новом потоке.

Для того, чтобы о потоке узнал планировщик потоков, необходимо вызвать метод `thread.start()`. 
Тогда поток перейдёт в состояние `RUNNABLE`. В интернете есть много неправильных схем, 
где разделяют состояния `Runnable` и `Running`. Но это ошибка, т.к. 
Java не отличает статус "готов к работе" и "работает (выполняется)".

Когда поток жив, но не активен (не `Runnable`), он находится в одном из двух состояний:
`BLOCKED` — ожидает захода в защищённую (`protected`) секцию, т.е. в `synchonized` блок.
`WAITING` — ожидает другой поток по условию. Если условие выполняется, планировщик потоков запускает поток.
Если поток ожидает по времени, он находится в статусе `TIMED_WAITING`. 

Если поток больше не выполняется (завершился успешно или с `exception`), он переходит в статус `TERMINATED`.

Чтобы узнать состояние потока (его `state`), используется метод `getState`.

У потоков также есть метод `isAlive`, который возвращает `true`, если поток не `Terminated`.

### LockSupport и парковка потоков

Данный класс ассоциирует с каждым потоком, который его использует, "`permit`" или разрешение. 

Вызов метода park возвращается немедленно, если `permit` доступен, занимая этот самый `permit` в процессе вызова. 
Иначе он блокируется.

Вызов метода `unpark` делает `permit` доступным, если он ещё недоступен.

`Permit` есть всего 1.

В `Java API` для `LockSupport` ссылаются на некий `Semaphore`. Давайте посмотрим на простой пример:

```java
import java.util.concurrent.Semaphore;
public class HelloWorldApp{

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(0);
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            // Просим разрешение и ждём, пока не получим его
            e.printStackTrace();
        }
        System.out.println("Hello, World!");
    }
}
```

![icon][semaphore]

Данный код будет вечно ждать, потому что в семафоре сейчас 0 `permit`. 
А когда в коде вызывается `acquire` (т.е. запросить разрешение), то поток ожидает, пока разрешение не получит.

Так как мы ждём, то обязаны обработать `InterruptedException`.

Интересно, что семафор реализует отдельное состояние потока. Если мы посмотрим в `JVisualVM`, то увидим, 
что у нас состояние не `Wait`, а `Park`.

```java
public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            //Запаркуем текущий поток
            System.err.println("Will be Parked");
            LockSupport.park();
            // Как только нас распаркуют - начнём действовать
            System.err.println("Unparked");
        };
        Thread th = new Thread(task);
        th.start();
        Thread.currentThread().sleep(2000);
        System.err.println("Thread state: " + th.getState());

        LockSupport.unpark(th);
        Thread.currentThread().sleep(2000);
}
```

Статус потока будет `WAITING`, но `JVisualVM` различает `wait` от `synchronized` и `park` от `LockSupport`. 

Почему так важен этот `LockSupport`? Обратимся снова к `Java API` и посмотрим про `Thread State` `WAITING`. 

Как видим, в него можно попасть только тремя способами. 2 способа — это `wait` и `join`. А третий — это `LockSupport`.

Локи в Java построены так же на `LockSupport` и представляют более высокоуровневые инструменты. 
Давайте попробуем воспользоваться таковым.

Посмотрим, например, на `ReentrantLock`:

```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class HelloWorld{

    public static void main(String []args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Runnable task = () -> {
            lock.lock();
            System.out.println("Thread");
            lock.unlock();
        };
        lock.lock();

        Thread th = new Thread(task);
        th.start();
        System.out.println("main");
        Thread.currentThread().sleep(2000);
        lock.unlock();
    }
}
```

Как и в прошлых примерах, тут всё просто. `lock` ожидает, пока кто-то освободит ресурс. 
Если посмотреть в `JVisualVM`, мы увидим, что новый поток будет запаркован, пока `main` поток не отдаст ему лок.

[к оглавлению](concurrent.md#Java-Concurrent)

## Взаимодействие потоков и проблемы

### Deadlock

Самой страшной проблемой является `Deadlock`. 
Когда два и более потоков вечно ожидают друг друга — это называется `Deadlock`.

```java
public class Deadlock {
    static class Friend {
        private final String name;
        public Friend(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public synchronized void bow(Friend bower) {
            System.out.format("%s: %s has bowed to me!%n",
                    this.name, bower.getName());
            bower.bowBack(this);
        }
        public synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s has bowed back to me!%n",
                    this.name, bower.getName());
        }
    }

    public static void main(String[] args) {
        final Friend alphonse = new Friend("Alphonse");
        final Friend gaston = new Friend("Gaston");
        new Thread(() -> alphonse.bow(gaston)).start();
        new Thread(() -> gaston.bow(alphonse)).start();
    }
}
```

Поток 1 ждёт лока от потока 0. Почему так выходит? `Thread-1` начинает выполнение и выполняет метод `Friend#bow`. 
Он помечен ключевым словом `synchronized`, то есть мы забираем монитор по `this`. 
Мы на вход в метод получили ссылку на другого `Friend`. 
Теперь, поток `Thread-1` хочет выполнить метод у другого `Friend`, 
тем самым получив лок и у него. Но если другой поток (в данном случае `Thread-0`) успел войти в метод `bow`, 
то лок уже занят и `Thread-1` ждёт `Thread-0`, и наоборот. Блокировка неразрешимая, поэтому она `Dead`, то есть мёртвая. 
Как мёртвая хватка (которую не разжать), так и мёртвая блокировка, из которой не выйти.

### Livelock

`Livelock` заключается в том, что потоки внешне как бы живут, 
но при этом не могут ничего сделать, т.к. условие, по которым они пытаются продолжить свою работу, 
не могут выполниться. По сути `Livelock` похож на `deadlock`, 
но только потоки не "зависают" на системном ожидании монитора, а что-то вечно делают. Например:

```java
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public static void log(String text) {
        String name = Thread.currentThread().getName(); //like Thread-1 or Thread-0
        String color = ANSI_BLUE;
        int val = Integer.valueOf(name.substring(name.lastIndexOf("-") + 1)) + 1;
        if (val != 0) {
            color = ANSI_PURPLE;
        }
        System.out.println(color + name + ": " + text + color);
        try {
            System.out.println(color + name + ": wait for " + val + " sec" + color);
            Thread.currentThread().sleep(val * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Lock first = new ReentrantLock();
        Lock second = new ReentrantLock();

        Runnable locker = () -> {
            boolean firstLocked = false;
            boolean secondLocked = false;
            try {
                while (!firstLocked || !secondLocked) {
                    firstLocked = first.tryLock(100, TimeUnit.MILLISECONDS);
                    log("First Locked: " + firstLocked);
                    secondLocked = second.tryLock(100, TimeUnit.MILLISECONDS);
                    log("Second Locked: " + secondLocked);
                }
                first.unlock();
                second.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        new Thread(locker).start();
        new Thread(locker).start();
    }
}
```
Как видно из примера, оба потока поочерёдно пытаются захватить оба лока, но им это не удаётся. 
При этом они не в `deadlock`, то есть визуально с ними всё хорошо и они выполняют свою работу.

![icon][livelock]

### Starvation

Помимо блокировок (`deadlock` и `livelock`) есть ещё одна проблема при работе с многопоточностью — `Starvation`, 
или "голодание". От блокировок это явление отличается тем, что потоки не заблокированы, 
а им просто не хватает ресурсов на всех. Поэтому пока одни потоки на себя берут всё время выполнения, 
другие не могут выполниться:

### Race Condition

При работе с многопоточностью есть такое понятие, как "состояние гонки". 
Это явление заключается в том, что потоки делят между собой некоторый ресурс и код написан таким образом, 
что не предусматривает корректную работу в таком случае. Взглянем на пример:

```java
public class App {
    public static int value = 0;

    public static void main(String[] args) {
        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                int oldValue = value;
                int newValue = ++value;
                if (oldValue + 1 != newValue) {
                    throw new IllegalStateException(oldValue + " + 1 = " + newValue);
                }
            }
        };
        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();
    }
}
```

Этот код может выдать ошибку не с первого раза. И выглядеть она может вот таким вот образом:

```java
Exception in thread "Thread-1" java.lang.IllegalStateException: 7899 + 1 = 7901
    at App.lambda$main$0(App.java:13)
    at java.lang.Thread.run(Thread.java:745)
```

Как видно, пока присваивалось `newValue` что-то пошло не так, и `newValue` стало больше. 
Какой-то из потоков в состоянии гонки успел изменить `value` между этими двумя командам. 
Как мы видим, проявилась гонка между потоками. А теперь представьте, 
как важно не совершать похожие ошибки с денежными операциями...

### Volatile

Говоря про взаимодействие потоков стоит особо отметить ключевое слово `volatile`.

```java
public class App {
    public static boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        Runnable whileFlagFalse = () -> {
            while(!flag) {
            }
            System.out.println("Flag is now TRUE");
        };

        new Thread(whileFlagFalse).start();
        Thread.sleep(1000);
        flag = true;
    }
}
```

Самое интересное, что он с высокой долей вероятности не отработает. 
Новый поток не увидит изменения `flag`. Чтобы это исправить для поля `flag` нужно указать ключевое слово `volatile`. 

Все действия выполняет процессор. Но результаты вычислений нужно где-то хранить. 
Для этого есть основная память и есть аппаратный кэш у процессора. 
Эти кэши процессора — своего рода маленький кусочек памяти для более быстрого обращения к данным, 
чем обращения к основной памяти. Но у всего есть и минус: 
данные в кэше могут быть не актуальны (как в примере выше, когда значение флага не обновилось). 
Так вот, ключевое слово `volatile` указывает `JVM`, что мы не хотим кэшировать нашу переменную. 
Это позволяет увидеть актуальный результат во всех потоках.

Кроме того, важно помнить, что `volatile` — это про видимость, а не про атомарность измений. 
Если взять код из `Race Condition`, то мы увидим в `IntelliJ Idea` подсказку:

### Атомарность

![icon][atomic]

Атомарные операции — это операции, которые нельзя разделить.

Например, операция присваивания значения переменной — атомарная.

К сожалению, инкремент не является атомарной операцией, 
т.к. для инкремента требуется аж три операции: получить старое значение, прибавить к нему единицу, сохранить значение.

Почему важна атомарность? В примере с инкрементом, если появится состояние гонки, 
в любой момент общий ресурс (т.е. общее значение) может внезапно измениться.

Кроме того, важно, что 64-битные структуры тоже не атомарны, например `long` и `double`.

```java
public class App {
    public static int value = 0;
    public static AtomicInteger atomic = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                value++;
                atomic.incrementAndGet();
            }
        };
        for (int i = 0; i < 3; i++) {
            new Thread(task).start();
        }
        Thread.sleep(300);
        System.out.println(value);
        System.out.println(atomic.get());
    }
}
```

Специальный класс для работы с атомарным `Integer` всегда будет выводить нам 30000, 
а вот `value` будет меняться от раза к разу.

### Happens Before

«Выполняется прежде» (англ. `happens before`) — отношение строгого частичного порядка 
(арефлексивное, антисимметричное, транзитивное), введённое между атомарными командами 
(++ и -- не атомарны!), придуманное Лесли Лэмпортом и не означающее «физически прежде». 
Оно значит, что вторая команда будет «в курсе» изменений, проведённых первой.

#### Модель памяти Java

В частности, одно выполняется прежде другого для таких операций (список не исчерпывающий):

Синхронизация и мониторы:
- Захват монитора (начало `synchronized`, метод `lock`) и всё, что после него в том же потоке.
- Возврат монитора (конец `synchronized`, метод `unlock`) и всё, что перед ним в том же потоке.
- Таким образом, оптимизатор может заносить строки в синхроблок, но не наружу.
- Возврат монитора и последующий захват другим потоком.
Запись и чтение:
- Любые зависимости по данным (то есть запись в любую переменную и последующее чтение её же) в одном потоке.
- Всё, что в том же потоке перед записью в volatile-переменную, и сама запись.
- volatile-чтение и всё, что после него в том же потоке.
- Запись в volatile-переменную и последующее считывание её же. Таким образом, 
`volatile`-запись делает с памятью то же, что возврат монитора, а чтение — то же, что захват. 
А значит: если один поток записал в `volatile`-переменную, а второй обнаружил это, всё, 
что предшествует записи, выполняется раньше всего, что идёт после чтения; см. иллюстрацию.

![icon][happens-before]
![icon][happens-before-2]
![icon][happens-before-3]
![icon][jmm]
![icon][jmm-2]
![icon][jmm-3]

- Для объектных переменных (например, `volatile List x;`) столь сильные гарантии выполняются для ссылки на объект, 
но не для его содержимого.
Обслуживание объекта:
- Статическая инициализация и любые действия с любыми экземплярами объектов.
- Запись в final-поля в конструкторе и всё, что после конструктора. 
Как исключение из всеобщей транзитивности, это соотношение `happens-before` не соединяется транзитивно 
с другими правилами и поэтому может вызвать межпоточную гонку.
- Любая работа с объектом и `finalize()`.
Обслуживание потока:
- Запуск потока и любой код в потоке.
- Зануление переменных, относящихся к потоку, и любой код в потоке.
- Код в потоке и `join();` код в потоке и `isAlive() == false`.
- `interrupt()` потока и обнаружение факта останова.

[к оглавлению](concurrent.md#Java-Concurrent)

## Мьютекс монитор и семафор

- *Мьютекс* — это специальный объект для синхронизации потоков. 
Задача мьютекса — обеспечить такой механизм, чтобы доступ к объекту в определенное время был только у одного потока
1) Возможны только два состояния — «свободен» и «занят». 
2) Состояниями нельзя управлять напрямую. 
В Java нет механизмов, которые позволили бы явно взять объект, получить его мьютекс и присвоить ему нужный статус.

- *Монитор* — это дополнительная «надстройка» над мьютексом, это «невидимый» для программиста кусок кода.

```java
public class Main {
   private Object obj = new Object();
   public void doSomething() {
       synchronized (obj) {
           obj.someImportantMethod();
       }
   }
}
```

Преобразуется в нечто подобное (!!!Псевдокод):
```java
public class Main {

   private Object obj = new Object();

   public void doSomething() throws InterruptedException {
       while (obj.getMutex().isBusy()) {
           Thread.sleep(1);
       }
       obj.getMutex().isBusy() = true;
       obj.someImportantMethod();
       obj.getMutex().isBusy() = false;
   }
}
```

- *Семафор* — это средство для синхронизации доступа к какому-то ресурсу. 
Его особенность заключается в том, что при создании механизма синхронизации он использует счетчик. 
Счетчик указывает нам, сколько потоков одновременно могут получать доступ к общему ресурсу.

![icon][semaphore]

```java
Semaphore(int permits);
Semaphore(int permits, boolean fair);
```

- ```int permits``` — начальное и максимальное значение счетчика. То есть то, 
сколько потоков одновременно могут иметь доступ к общему ресурсу;

- ```boolean fair``` — для установления порядка, в котором потоки будут получать доступ. 
Если fair = true, доступ предоставляется ожидающим потокам в том порядке, в котором они его запрашивали. 
Если же он равен false, порядок будет определять планировщик потоков.

```java
class Philosopher extends Thread {

   private Semaphore sem;
   // поел ли философ
   private boolean full = false;
   private String name;

   Philosopher(Semaphore sem, String name) {
       this.sem=sem;
       this.name=name;
   }

   public void run() {
       try {
           // если философ еще не ел
           if (!full) {
               //Запрашиваем у семафора разрешение на выполнение
               sem.acquire();
               System.out.println (name + " садится за стол");

               // философ ест
               sleep(300);
               full = true;

               System.out.println (name + " поел! Он выходит из-за стола");
               sem.release();

               // философ ушел, освободив место другим
               sleep(300);
           }
       }
       catch(InterruptedException e) {
           System.out.println ("Что-то пошло не так!");
       }
   }
}
```

Код запуска

```java
public class Main {

   public static void main(String[] args) {

       Semaphore sem = new Semaphore(2);
       new Philosopher(sem,"Сократ").start();
       new Philosopher(sem,"Платон").start();
       new Philosopher(sem,"Аристотель").start();
       new Philosopher(sem,"Фалес").start();
       new Philosopher(sem,"Пифагор").start();
   }
}
```

[к оглавлению](concurrent.md#Java-Concurrent)

## CompletableFuture

Важно, что в момент получения результата при помощи метода `get` 
выполнение становится синхронным. Как вы думаете, какой механизм тут будет использован? 
Правильно, нет блока синхронизации — поэтому `WAITING` в `JVisualVM` мы 
увидим не как `monitor` или `wait`, а как тот самый `park` 
(т.к. используется механизм `LockSupport`).

Шло время, и в `Java 1.8` появился новый класс, который зовётся `CompletableFuture`. 
Он реализует интерфейс `Future`, то есть наши `task` будут выполнены в будущем, 
и мы сможем выполнить get и получить результат. Но ещё он реализует некоторый `CompletionStage`. 
Из перевода уже понятно его назначение: это некий этап (`Stage`) каких-то вычислений.

```java
import java.util.concurrent.CompletableFuture;
public class App {
    public static void main(String []args) throws Exception {
        // CompletableFuture уже содержащий результат
        CompletableFuture<String> completed;
        completed = CompletableFuture.completedFuture("Просто значение");
        // CompletableFuture, запускающий (run) новый поток с Runnable, поэтому он Void
        CompletableFuture<Void> voidCompletableFuture;
        voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("run " + Thread.currentThread().getName());
        });
        // CompletableFuture, запускающий новый поток, результат которого возьмём у Supplier
        CompletableFuture<String> supplier;
        supplier = CompletableFuture.supplyAsync(() -> {
            System.out.println("supply " + Thread.currentThread().getName());
            return "Значение";
        });
    }
}
```

Если мы выполним этот код, то увидим, что создание `CompletableFuture` подразумевает запуск и всей цепочки. 
Поэтому при некоторой схожести со `SteamAPI` из `Java8` в этом отличие этих подходов. Например:

```java
List<String> array = Arrays.asList("one", "two");
Stream<String> stringStream = array.stream().map(value -> {
	System.out.println("Executed");
	return value.toUpperCase();
});
```

Это пример `Java 8 Stream Api` (подробнее можно с ним ознакомиться здесь "Руководство по Java 8 Stream API 
в картинках и примерах"). Если запустить этот код, то Executed не отобразится. 
То есть при создании стрима в Java стрим не запускается сразу, а ждёт, когда из него захотят значение. 
А вот `CompletableFuture` запускает цепочку на выполнение сразу, не дожидаясь того, 
что у него попросят посчитанное значение

Второе, что надо помнить, что `CompletalbeFuture` в своей работе использует `Runnable`, 
потребителей и функции. Учитывая это, вы всегда сможете вспомнить, что с `CompletableFuture` можно делать так:

```java
public static void main(String []args) throws Exception {
        AtomicLong longValue = new AtomicLong(0);
        Runnable task = () -> longValue.set(new Date().getTime());
        Function<Long, Date> dateConverter = (longvalue) -> new Date(longvalue);
        Consumer<Date> printer = date -> {
            System.out.println(date);
            System.out.flush();
        };
        // CompletableFuture computation
        CompletableFuture.runAsync(task)
                         .thenApply((v) -> longValue.get())
                         .thenApply(dateConverter)
                         .thenAccept(printer);
}
```

#### Мы можем объединять результат `CompletableFuture` с результатом другого `CompletableFuture`

```java
Supplier newsSupplier = () -> {
        try {
			Thread.currentThread().sleep(3000);
			return "Message";
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}};

CompletableFuture<String> reader = CompletableFuture.supplyAsync(newsSupplier);
CompletableFuture.completedFuture("!!")
				 .thenCombine(reader, (a, b) -> b + a)
				 .thenAccept(result -> System.out.println(result))
				 .get();
```

#### А ещё мы можем не только объединить (combine), но и возвращать `CompletableFuture`

Тут хочется отметить, что для краткости использован метод `CompletableFuture.completedFuture`. 
Данный метод не создаёт новый поток, поэтому остальная цепочка будет выполнена в том же потоке, 
в котором был вызван `completedFuture`.

Также есть метод `thenAcceptBoth`. Он очень похож на accept, но если `thenAccept` принимает `consumer`, 
то `thenAcceptBoth` принимает на вход ещё один `CompletableStage` + `BiConsumer`, то есть `consumer`, 
который на вход принимает 2 источника, а не один.

Есть ещё интересная возможность со словом `Either`.

```java
CompletableFuture.completedFuture(2L)
				.thenCompose((val) -> CompletableFuture.completedFuture(val + 2))
                               .thenAccept(result -> System.out.println(result));
```

#### `CompletableFuture` — обработкой ошибок

Данный код ничего не сделает, т.к. упадёт исключение и ничего не будет. 
Но если мы раскомментируем `exceptionally`, то мы определим поведение.

```java
CompletableFuture.completedFuture(2L)
				 .thenApply((a) -> {
					throw new IllegalStateException("error");
				 }).thenApply((a) -> 3L)
				 //.exceptionally(ex -> 0L)
				 .thenAccept(val -> System.out.println(val));
```
[к оглавлению](concurrent.md#Java-Concurrent)

## Executor и ThreadPool

```java
public static void main(String []args) throws Exception {
	Runnable task = () -> System.out.println("Task executed");
	Executor executor = (runnable) -> {
		new Thread(runnable).start();
	};
	executor.execute(task);
}
```

У нас есть `Executor` для `execute` (т.е. выполнения) некой задачи в потоке, 
когда реализация создания потока скрыта от нас. У нас есть `ExecutorService` — особый `Executor`, 
который имеет набор возможностей по управлению ходом выполнения. И у нас есть фабрика `Executors`, 
которая позволяет создавать `ExecutorService`. Давайте теперь это проделаем сами:

```java
public static void main(String[] args) throws ExecutionException, InterruptedException {
	Callable<String> task = () -> Thread.currentThread().getName();
	ExecutorService service = Executors.newFixedThreadPool(2);
	for (int i = 0; i < 5; i++) {
		Future result = service.submit(task);
		System.out.println(result.get());
	}
	service.shutdown();
}
```

ак мы видим, мы указали фиксированный пул потоков (`Fixed Thread Pool`) размером 2. 
После чего мы поочередно отправляем в пул задачи. Каждая задача возвращает строку (`String`), 
содержащую имя потока (`currentThread().getName()`). Важно в самом конце выполнить `shutdown` для `ExecutorService`, 
потому что в противном случае наша программа не завершится.

В фабрике Executors есть и другие фабричные методы. Например, мы можем создать пул всего из одного потока — 
`newSingleThreadExecutor` или пул с кэшированием `newCachedThreadPool`, когда потоки будут убираться из пула, 
если они простаивают 1 минуту. 

На самом деле, за этими `ExecutorService` прячется блокирующая очередь, 
в которую помещаются задачи и из которой эти задачи выполняются.

#### ThreadPoolExecutor

Как мы ранее увидели, внутри фабричных методов в основном создаётся `ThreadPoolExecutor`. 
На функциональность влияет то, какие значения переданы в качестве максимума и минимума потоков, 
а также какая очередь используется. А использоваться может любая реализация интерфейса 
`java.util.concurrent.BlockingQueue`.

Говоря о `ThreadPoolExecutor`'ах, стоит отметить интересные особенности при работе. Например, 
нельзя посылать задачи в `ThreadPoolExecutor`, если там нет места:

```java
public static void main(String[] args) throws ExecutionException, InterruptedException {
	int threadBound = 2;
	ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, threadBound,
            0L, TimeUnit.SECONDS, new SynchronousQueue<>());
	Callable<String> task = () -> {
		Thread.sleep(1000);
		return Thread.currentThread().getName();
	};
	for (int i = 0; i < threadBound + 1; i++) {
		threadPoolExecutor.submit(task);
	}
	threadPoolExecutor.shutdown();
}
```

Данный код упадёт с ошибкой вида:


```java
Task java.util.concurrent.FutureTask@7cca494b rejected from java.util.concurrent.ThreadPoolExecutor@7ba4f24f[
Running, pool size = 2, active threads = 2, queued tasks = 0, completed tasks = 0]
```
[к оглавлению](concurrent.md#Java-Concurrent)

## Locks

#### Семафоры
Самое простое средство контроля за тем, сколько потоков могут одновременно работать — семафор. 
Как на железной дороге. Горит зелёный — можно. Горит красный — ждём. 
Что мы ждём от семафора? Разрешения. Разрешение на английском — `permit`. 
Чтобы получить разрешение — его нужно получить, что на английском будет `acquire`. 
А когда разрешение больше не нужно мы его должны отдать, то есть освободить его или избавится от него, 
что на английском будет release. Посмотрим, как это работает.

Нам потребуется импорт класса `java.util.concurrent.Semaphore`.

```java
public static void main(String[] args) throws InterruptedException {
	Semaphore semaphore = new Semaphore(0);
	Runnable task = () -> {
		try {
			semaphore.acquire();
			System.out.println("Finished");
			semaphore.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	};
	new Thread(task).start();
	Thread.sleep(5000);
	semaphore.release(1);
}
```

Как видим, запомнив английские слова, мы понимаем, как работает семафор. 
Интересно, что главное условие — на "счету" семафора должен быть положительное количество `permit`'ов. 
Поэтому, инициировать его можно и с минусом. И запрашивать (`acquire`) можно больше, чем 1.

#### CountDownLatch
Следующий механизм — `CountDownLatch`. `CountDown` на английском — это отсчёт, а `Latch` — задвижка или защёлка. 
То есть если переводить, то это защёлка с отсчётом.

Тут нам понадобится соответствующий импорт класса `java.util.concurrent.CountDownLatch`.

Это похоже на бега или гонки, когда все собираются у стартовой линии и когда все готовы — дают разрешение, 
и все одновременно стартуют. 

```java
public static void main(String[] args) {
	CountDownLatch countDownLatch = new CountDownLatch(3);
	Runnable task = () -> {
		try {
			countDownLatch.countDown();
			System.out.println("Countdown: " + countDownLatch.getCount());
			countDownLatch.await();
			System.out.println("Finished");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	};
	for (int i = 0; i < 3; i++) {
		new Thread(task).start();
 	}
}
```

`await` на английском — ожидать. То есть мы сначала говорим `countDown`. Как говорит гугл переводчик, 
count down — "an act of counting numerals in reverse order to zero", 
то есть выполнить действие по обратному отсчёту, цель которого — досчитать до нуля. 
А дальше говорим await — то есть ожидать, пока значение счётчика не станет ноль.

Интересно, что такой счётчик — одноразовый. Как сказано в JavaDoc — 
"When threads must repeatedly count down in this way, instead use a CyclicBarrier", 
то есть если нужен многоразовый счёт — надо использовать другой вариант, который называется `CyclicBarrier`.

#### CyclicBarrier
Как и следует из названия, `CyclicBarrier` — это циклический барьер.
Нам понадобится импорт класса `java.util.concurrent.CyclicBarrier`.

```java
public static void main(String[] args) throws InterruptedException {
	Runnable action = () -> System.out.println("На старт!");
	CyclicBarrier berrier = new CyclicBarrier(3, action);
	Runnable task = () -> {
		try {
			berrier.await();
			System.out.println("Finished");
		} catch (BrokenBarrierException | InterruptedException e) {
			e.printStackTrace();
		}
	};
	System.out.println("Limit: " + berrier.getParties());
	for (int i = 0; i < 3; i++) {
		new Thread(task).start();
	}
}
```

Как видим, поток выполняет `await`, то есть ожидает. При этом уменьшается значение барьера. 
Барьер считается сломанным (`berrier.isBroken()`), когда отсчёт дошёл до нуля.

Чтобы сбросить барьер, нужно вызвать `berrier.reset()`, чего не хватало в `CountDownLatch`.

`Exchanger`
Следующее средство — `Exchanger`. Exchange с английского переводится как обмен или обмениваться. 
А `Exchanger` — обменник, то есть то, через что обмениваются.

```java
public static void main(String[] args) {
	Exchanger<String> exchanger = new Exchanger<>();
	Runnable task = () -> {
		try {
			Thread thread = Thread.currentThread();
			String withThreadName = exchanger.exchange(thread.getName());
			System.out.println(thread.getName() + " обменялся с " + withThreadName);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	};
	new Thread(task).start();
	new Thread(task).start();
}
```

Тут мы запускаем два потока. Каждый из них выполняет метод exchange и ожидает, 
когда другой поток так жевыполнит метод exchange. Таким образом, 
потоки обменяются между собой переданными аргументами.

Интересная штука. Ничего ли она вам не напоминает?

А напоминает он `SynchronousQueue`, которая лежит в основе `cachedThreadPool`'а.

```java
public static void main(String[] args) throws InterruptedException {
	SynchronousQueue<String> queue = new SynchronousQueue<>();
	Runnable task = () -> {
		try {
			System.out.println(queue.take());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	};
	new Thread(task).start();
	queue.put("Message");
}
```

В примере видно, что запустив новый поток, данный поток уйдёт в ожидание, т.к. в очереди будет пусто. 
А дальше `main` поток положит в очередь текст "Message". При этом он сам остановится на время, 
которой нужно, пока не получат из очереди этот текстовый элемент.

#### Phaser
И напоследок самое сладкое — `Phaser`.

Нам понадобится импорт класса `java.util.concurrent.Phaser`.

```java
public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new Phaser();
        // Вызывая метод register, мы регистрируем текущий поток (main) как участника
        phaser.register();
        System.out.println("Phasecount is " + phaser.getPhase());
        testPhaser(phaser);
        testPhaser(phaser);
        testPhaser(phaser);
        // Через 3 секунды прибываем к барьеру и снимаемся регистрацию. Кол-во прибывших = кол-во регистраций = пуск
        Thread.sleep(3000);
        phaser.arriveAndDeregister();
        System.out.println("Phasecount is " + phaser.getPhase());
    }

    private static void testPhaser(final Phaser phaser) {
        // Говорим, что будет +1 участник на Phaser
        phaser.register();
        // Запускаем новый поток
        new Thread(() -> {
            String name = Thread.currentThread().getName();
            System.out.println(name + " arrived");
            phaser.arriveAndAwaitAdvance(); //threads register arrival to the phaser.
            System.out.println(name + " after passing barrier");
        }).start();
    }
```
Из примера видно, что барьер при использовании `Phaser`'а прорывается, 
когда количество регистраций совпадает с количеством прибывших к барьеру.

[к оглавлению](concurrent.md#Java-Concurrent)

[Заглавная](README.md)