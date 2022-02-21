[Заглавная](README.md)

# Spring Questions
+ [What Is Spring Framework](spring.md#What-Is-Spring-Framework)
+ [Виды конфигураций Spring приложений](spring.md#Виды-конфигураций-Spring-приложений)
+ [Spring Benefits](spring.md#Spring-Benefits)
+ [Spring advantages and disadvantages](spring.md#Spring-advantages-and-disadvantages)
+ [Difference between JavaEE and Spring](spring.md#Difference-between-JavaEE-and-Spring)
+ [Spring Sub-Projects](spring.md#Spring-Sub-Projects)
+ [Best Way of Injecting Beans](spring.md#Best-Way-of-Injecting-Beans)
+ [Difference Between BeanFactory and ApplicationContext](spring.md#Difference-Between-BeanFactory-and-ApplicationContext)
+ [Bean Life Cycle](spring.md#Bean-Life-Cycle)
+ [Bean Creation Process](spring.md#Bean-Creation-Process)
+ [Differance between @Component, @Service and @Repository](spring.md#Differance-between-@Component,-@Service-and-@Repository)
+ [Design Patterns Used in the Spring Framework](spring.md#Design-Patterns-Used-in-the-Spring-Framework)
+ [Controller in Spring MVC](spring.md#Controller-in-Spring-MVC)
+ [@RequestMapping Annotation](spring.md#@RequestMapping-Annotation)
+ [Spring Boot Main Features](spring.md#Spring-Boot-Main-Features)
+ [Spring Boot Basic Annotations](spring.md#Spring-Boot-Basic-Annotations)
+ [Injection of Prototype into Singleton](spring.md#Injection-of-Prototype-into-Singleton)
+ [Что класть и не класть в контекст](spring.md#Что-класть-и-не-класть-в-контекст)
+ [Применение AOP](spring.md#Применение-AOP)

[bean-life-cycle]:img/spring/Spring-Bean-Life-Cycle.jpg
[spring-bean-creation]:img/spring/spring-bean-creation.jpg
[spel]:img/spring/spel.PNG
[localization]:img/spring/localization.PNG
[cross-cutting]:img/spring/cross-cutting.PNG

[к оглавлению](#Spring-Questions)

## What Is Spring Framework
Spring is a powerful open-source, loosely coupled, lightweight, java framework meant for reducing the 
complexity of developing enterprise-level applications. This framework is also called the “framework of frameworks” 
as spring provides support to various other important frameworks like JSF, Hibernate, Structs, EJB, etc.

[к оглавлению](#Spring-Questions)

## Виды конфигураций Spring приложений

- Groovy-based (для фанатов).
- XML-based (классика, но
устарела).
- Annotation-based
    - Java-based (`@Bean`)
    - Annotation-based (`@Autowired`, `@Service`, `@Controller`)
    
- @Configuration
- @ComponentScan
- Annotation-based
- @Autowired
- Property-files
- SpEL - Spring Expression Language
- Локализация

### XML-based (классика, но устарела).

```xml
<bean id="personDAO" class="edu.spring.PersonDAO">
    <constructor-arg name="dbUrl" value="${db.url}" />
</bean>
<bean id="personService" class="edu.spring.PersonService">
    <constructor-arg name="dao" ref="personDAO"/>
</bean>
```
```java
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/context.xml");
        PersonService s = context.getBean(PersonService.class);
        s.getPerson();
    }
}
```

### Java-based
```java
@Configuration
class AppConfig {
    @Bean
    IPersonDAO personDAO(@Value("${db.url}") String dbUrl) {
        return new PersonDAO(dbUrl);
    }
    @Bean
    PersonService personService(IPersonDAO dao) {
        return new PersonService(dao);
    }
}
```

- Все бины располагаются в классах помеченных аннотацией `@Configuration`
- Таких классов может быть много – обычно на каждый слой/технологию
- Бины создаются в нестатических методах, помеченных аннотацией `@Bean`
- Зависимости бинов – параметры методов

### @Configuration

- Класс конфигурации должен иметь конструктор без параметров (обычно его просто не пишут – он уже есть)
- Могут содержать `@Autowired` поля, которые потом можно использовать в методах
- Методы – нестатические, генерируют бины, помечены аннотацией `@Bean`

Этой аннотацией помечены другие аннотации:
- `@SpringBootApplication`
- `@TestConfiguration`
- `@EnableWebSecurity`

Конфигурационный класс можетнаследоваться от специальных `Spring` конфигурационных файлов.

```java
@Configuration
class AppConfig {
    @Bean
    IPersonDAO personDAO(@Value("${db.url}") String dbUrl) {
        return new PersonDAO(dbUrl);
    }
    @Bean
    PersonService personService(IPersonDAO dao) {
        return new PersonService(dao);
    }
}
```

### @ComponentScan

- Ищет классы конфигураций
- Ищет классы, помеченные стереотипами `@Service`, `@Controller` и т.д.
- Если не задан package – ищет по пакетам «вглубь», начиная с текущего.
- Не ищет интерфейсы !!! (`Spring Data`, `Spring Integrations` имеют свои аннотации, 
которые надо добавлять дополнительно)
- Некоторые аннотации помечены `@ComponentScan` без параметров – например `@SpringBootApplication`
- Обычно в корне пакетов (`ru.otus`) располагается класс помеченный аннотацией `@ComponentScan` безпараметров, 
а все остальные конфигурации и сервисы находятся автоматически
- Если не задан package – ищет по пакетам «вглубь», начиная с текущего.

### Annotation-based

- Собственно это и есть Annotation-based контекст
- Предполагает использование аннотаций стереотипов `@Service`, `@Controller`, `@Repository`
- Предполагает использование аннотаций `@Required`, `@Autowired`, или `JSR-250`
- Обычно и комбинируется с `Java-based` аннотацией

### @Autowired

- Ставится на конструкторах, полях, сеттерах, методах
- Рекомендуется ставить на конструкторы (так можно класс протестировать без поднятия контекста, да и просто удобнее)
- Начиная со Spring 4.2.*, если конструктор в классе один, то подразумевается, что `@Autowired` на нём стоит
- Связывание происходит по типу
- Если такого бина нет, то будет `Exception`
- Если таких бинов несколько, то тоже `Exception`
• Чтобы выбрать нужный по ID, можно писать `Qualifier`

### Property-files

- Нужны для конфигурации приложения
- Хранятся в ресурсах (как `src/main/resources/`, так и `src/test/resources/`)
- Иногда фильтруются мейвеном
- Пишутся ли в `UTF-8` ? (нет)
- Но всё равно все пишут их в `UTF-8`
- Чтобы их использовать можно воспользоваться `SpEL` – `Spring Expression Language`
- С помощью плейсходера `${db.url}` можно получать свойство из загруженного файла.
- Но для этого придётся настроить `PropertyPlaceholderConfigurer` (в `Spring Boot` он уже есть).
- Одного такого бина достаточно на приложение + не смотрите примеры, где в нём прописываются файлы.

### SpEL - Spring Expression Language

- Крайне мощный инструмент
- Может спасти Вам жизнь в огромном проекте со `Spring Security` + `Spring Data`
- А может убить (`OWASP 2017:A1` – `Injections` – это не только про `SQL-инъекции`, а и про `SpEL` тоже)
- Высший пилотаж – расширять своими выражениями

![icon][spel]

### Локализация

- `Internationalization (i18n)` и `Localization (l10n)`
- Имеется встроенная поддержка в `Java`
- Поддерживается прекрасно в `Spring` на уровне контекста
```
// bundle.properties:
hello.world=Hello World!
hello.user=Hello, {0}!
// bundle_ru_RU.properties:
hello.world=Привет, Мир!
hello.user=Привет, {0}!
```

```java
@Bean
public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
    ms.setBasename("/i18n/bundle");
    ms.setDefaultEncoding("UTF-8");
    return ms;
}
```

![icon][localization]

[к оглавлению](#Spring-Questions)

## Spring Benefits
Spring targets to make Jakarta EE development easier, so let's look at the advantages:

- **Lightweight** – There is a slight overhead of using the framework in development.
- **Inversion of Control (IoC)** – Spring container takes care of wiring dependencies of various objects instead 
of creating or looking for dependent objects.
- **Aspect-Oriented Programming (AOP)** – Spring supports AOP to separate business logic from system services.
- **IoC container** – manages Spring Bean life cycle and project-specific configurations
- **MVC framework** – used to create web applications or RESTful web services, capable of returning XML/JSON responses
- **Transaction management** – reduces the amount of boilerplate code in JDBC operations, file uploading, etc., 
either by using Java annotations or by Spring Bean XML configuration file
- **Exception Handling** – Spring provides a convenient API for translating technology-specific exceptions into 
unchecked exceptions.

[к оглавлению](#Spring-Questions)

## Spring advantages and disadvantages

### Advantages of Spring

- Uses POJO, don’t need an enterprise container like an application server.
- Provides Modularity to developers.
- Consistency of Transaction Management.
- Well- Designed Web Framework.
- It can effectively organize middle-tier objects
- Spring application code is much easier to unit test.

### Disadvantages of Spring

- Complex and it lacks a clear focus.
- Quite difficult to learn Spring Framework for a new developer.
- Lots of XML in Spring.
- No clear guidelines on several topics on spring documentation.
- Longer Configuration

## Difference between JavaEE and Spring

01.	JavaEE is a Sun/Oracle standard/specification.	Spring is not a standard, strictly speaking, it is a framework.
02.	JavaEE is used for web development.	Spring is used for a template design for an application.
04.	JavaEE has oracle based license.	Spring has an open-source license.
05.	It is based on three-dimensional architectural frameworks. 	It is based on layered architecture containing many modules.
06.	It has an object-oriented language that contains a certain style and syntax.	It does not has a programming language.
07.	JavaEE has got good speed.	Spring is slower than JavaEE.
08.	JavaEE can be web-based or non-web-based.	Spring is based on almost 20 modules.
09.	It is typically got a graphical user interface created from the abstract window toolkit.	This makes the same syntax independent of an IDE.
10.	JavaEE uses JTA API with the execution.	Spring gives a certain layer to help different JTA execution merchants.

##Spring Sub-Projects
- **Core** – a key module that provides fundamental parts of the framework, such as IoC or DI
- **JDBC** – enables a JDBC-abstraction layer that removes the need to do JDBC coding for specific vendor databases
- **ORM integration** – provides integration layers for popular object-relational mapping APIs, such as JPA, 
JDO and Hibernate
- **Web** – a web-oriented integration module that provides multipart file upload, Servlet listeners and 
web-oriented application context functionalities
- **MVC framework** – a web module implementing the Model View Controller design pattern
- **AOP module** – aspect-oriented programming implementation allowing the definition of clean method-interceptors 
and pointcuts

[к оглавлению](#Spring-Questions)

## Best Way of Injecting Beans
The recommended approach is to use constructor arguments for mandatory dependencies and setters for optional ones. 
This is because constructor injection allows injecting values to immutable fields and makes testing easier.

```Constructor | Property```
- DI через конструктор – рекомендован.
- Но могут быть циклические зависимости (виноваты в этом –
Вы).
- Вы всегда получаете готовый к работе класс.
- C property может быть NPE.

[к оглавлению](#Spring-Questions)

## Difference Between BeanFactory and ApplicationContext

#### Bean Factory
- Bean instantiation/wiring
- Create beans when they called
#### Application Context
- Bean instantiation/wiring
- Create beans even if they don't called
- Implements Bean Factory
- Automatic BeanPostProcessor registration
- Automatic BeanFactoryPostProcessor registration
- Convenient MessageSource access (for i18n)
- ApplicationEvent publication

[к оглавлению](#Spring-Questions)

## Bean Life Cycle

![icon][bean-life-cycle]

[к оглавлению](#Spring-Questions)

## Bean Creation Process

![icon][spring-bean-creation]

[к оглавлению](#Spring-Questions)

## Design Patterns Used in the Spring Framework
- **Singleton Pattern** – singleton-scoped beans
- **Factory Pattern** – Bean Factory classes
- **Prototype Pattern** – prototype-scoped beans
- **Adapter Pattern** – Spring Web and Spring MVC
- **Proxy Pattern** – Spring Aspect-Oriented Programming support
- **Template Method Pattern** – JdbcTemplate, HibernateTemplate, etc.
- **Front Controller** – Spring MVC DispatcherServlet
- **Data Access Object** – Spring DAO support
- **Model View Controller** – Spring MVC

## Controller in Spring MVC
Simply put, all the requests processed by the DispatcherServlet are directed to classes annotated with @Controller. 
Each controller class maps one or more requests to methods that process and execute the requests with provided inputs.

[к оглавлению](#Spring-Questions)

## Differance between @Component, @Service and @Repository

In fact they are aliases to @Component.
1) Mark different layers of application - controller, service and data layers
2) Exception handling inside of spring.
3) Maybe in future Spring will add some logic to those annotations

[к оглавлению](#Spring-Questions)

## @RequestMapping Annotation
The ```@RequestMapping``` annotation is used to map web requests to Spring Controller methods. 
In addition to simple use cases, we can use it for mapping of HTTP headers, binding parts of the URI with 
```@PathVariable```, 
and working with URI parameters and the ```@RequestParam``` annotation.

[к оглавлению](#Spring-Questions)

## Spring Boot Main Features

Spring Boot is essentially a framework for rapid application development built on top of the Spring Framework. 
With its auto-configuration and embedded application server support, combined with the extensive documentation 
and community support it enjoys, Spring Boot is one of the most popular technologies in the Java ecosystem as of date.

Here are a few salient features:

- **Starters** – a set of dependency descriptors to include relevant dependencies at a go
- **Auto-configuration** – a way to automatically configure an application based on the dependencies present 
on the classpath
- **Actuator** – to get production-ready features such as monitoring
- **Security**
- **Logging**

[к оглавлению](#Spring-Questions)

## Spring Boot Basic Annotations
The primary annotations that Spring Boot offers reside in its ```org.springframework.boot.autoconfigure``` 
and its sub-packages.

Here are a couple of basic ones:

- ```@EnableAutoConfiguration``` – to make Spring Boot look for auto-configuration beans on its classpath and 
automatically apply them
- ```@SpringBootApplication``` – to denote the main class of a Boot Application. This annotation combines 
```@Configuration```, ```@EnableAutoConfiguration``` and ```@ComponentScan``` annotations with their default 
attributes.

[к оглавлению](#Spring-Questions)

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

[к оглавлению](#Spring-Questions)

## Injection of Prototype into Singleton

Контекст конфиг
```java
@Configuration
public class AppConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public PrototypeBean prototypeBean() {
        return new PrototypeBean();
    }

    @Bean
    public SingletonBean singletonBean() {
        return new SingletonBean();
    }
}
```
SingletonBean
```java
public class SingletonBean {

    @Autowired
    private PrototypeBean prototypeBean;

    public SingletonBean() {
        logger.info("Singleton instance created");
    }

    public PrototypeBean getPrototypeBean() {
        logger.info(String.valueOf(LocalTime.now()));
        return prototypeBean;
    }
}
```
Test
```java
public static void main(String[] args) throws InterruptedException {
    AnnotationConfigApplicationContext context = 
        new AnnotationConfigApplicationContext(AppConfig.class);
    
    SingletonBean firstSingleton = context.getBean(SingletonBean.class);
    PrototypeBean firstPrototype = firstSingleton.getPrototypeBean();
    
    // get singleton bean instance one more time
    SingletonBean secondSingleton = context.getBean(SingletonBean.class);
    PrototypeBean secondPrototype = secondSingleton.getPrototypeBean();

    isTrue(firstPrototype.equals(secondPrototype), "The same instance should be returned");
}
```

```log
Singleton Bean created
Prototype Bean created
11:06:57.894
// should create another prototype bean instance here
11:06:58.895
```
 
### 1. Injecting ApplicationContext BAD WAY
```java
public class SingletonAppContextBean implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public PrototypeBean getPrototypeBean() {
        return applicationContext.getBean(PrototypeBean.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
```

### 2. Method Injection
Another way to solve the problem is method injection with the `@Lookup` annotation.
Spring will override the `getPrototypeBean()` method annotated with` @Lookup`. 
It then registers the bean into the application context. 
Whenever we request the `getPrototypeBean()` method, it returns a new `PrototypeBean` instance.
It will use CGLIB to generate the bytecode responsible for fetching the `PrototypeBean` from the application context.

```java
@Component
public class SingletonLookupBean {

    @Lookup
    public PrototypeBean getPrototypeBean() {
        return null;
    }
}
```

### 3. Scoped Proxy

By default, Spring holds a reference to the real object to perform the injection. 
**Here, we create a proxy object to wire the real object with the dependent one.**
Each time the method on the proxy object is called, the proxy decides itself whether to create a new instance 
of the real object or reuse the existing one.
To set up this, we modify the `Appconfig` class to add a new `@Scope` annotation:
```java
@Scope(
  value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, 
  proxyMode = ScopedProxyMode.TARGET_CLASS)
```
By default, Spring uses CGLIB library to directly subclass the objects. 
To avoid CGLIB usage, we can configure the proxy mode with `ScopedProxyMode.INTERFACES`, 
to use the JDK dynamic proxy instead.

### 4. ObjectFactory Interface **Favorite**
Spring provides the `ObjectFactory<T>` interface to produce on demand objects of the given type:
```java
public class SingletonObjectFactoryBean {

    @Autowired
    private ObjectFactory<PrototypeBean> prototypeBeanObjectFactory;

    public PrototypeBean getPrototypeInstance() {
        return prototypeBeanObjectFactory.getObject();
    }
}
```
Let's have a look at` getPrototypeInstance()` method; `getObject()` returns a brand new instance of `PrototypeBean` 
for each request. Here, we have more control over initialization of the prototype.

Also, the `ObjectFactory` is a part of the framework; this means avoiding additional setup in order to use this option.

### 5. Create a Bean at Runtime Using `java.util.Function`

Another option is to create the prototype bean instances at runtime, 
which also allows us to add parameters to the instances.

To see an example of this, let's add a name field to our `PrototypeBean` class:
```java
public class PrototypeBean {
    private String name;
    
    public PrototypeBean(String name) {
        this.name = name;
        logger.info("Prototype instance " + name + " created");
    }

    //...   
}
```

Next, we'll inject a bean factory into our singleton bean by making use of the `java.util.Function` interface:
```java
public class SingletonFunctionBean {
    
    @Autowired
    private Function<String, PrototypeBean> beanFactory;
    
    public PrototypeBean getPrototypeInstance(String name) {
        PrototypeBean bean = beanFactory.apply(name);
        return bean;
    }

}
```
Finally, we have to define the factory bean, prototype and singleton beans in our configuration:
```java
@Configuration
public class AppConfig {
    @Bean
    public Function<String, PrototypeBean> beanFactory() {
        return name -> prototypeBeanWithParam(name);
    } 

    @Bean
    @Scope(value = "prototype")
    public PrototypeBean prototypeBeanWithParam(String name) {
       return new PrototypeBean(name);
    }
    
    @Bean
    public SingletonFunctionBean singletonFunctionBean() {
        return new SingletonFunctionBean();
    }
    //...
}
```

[к оглавлению](#Spring-Questions)

## Что класть и не класть в контекст

*Что класть в контекст*

- Бизнес-сервисы (DAO, Services);
- Подключения к внешним системам;
- Мапперы/Маршаллеры/конвертеры;
- Служебные бины (PersistenceContextManager…);
- Бизнес-бины стратегий (Паттерн стратегия).

*Что не нужно класть*

- Бизнес-объекты (бины, пользователи*);
- Настройки, кроме пачек/файлов настроек*;
- Объекты, которые понадобятся только один раз в один момент
(временные).
- Стандартные классы (String, InputStream, Locale*)

[к оглавлению](#Spring-Questions)

## Применение AOP

![icon][cross-cutting]

[к оглавлению](#Spring-Questions)

[Заглавная](README.md)