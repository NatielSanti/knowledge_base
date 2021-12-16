[Заглавная](README.md)

# Spring Questions
+ [What Is Spring Framework](spring.md#What-Is-Spring-Framework)
+ [Spring Benefits](spring.md#Spring-Benefits)
+ [Spring advantages and disadvantages](spring.md#Spring-advantages-and-disadvantages)
+ [Difference between JavaEE and Spring](spring.md#Difference-between-JavaEE-and-Spring)
+ [Spring Sub-Projects](spring.md#Spring-Sub-Projects)
+ [Best Way of Injecting Beans](spring.md#Best-Way-of-Injecting-Beans)
+ [Difference Between BeanFactory and ApplicationContext](spring.md#Difference-Between-BeanFactory-and-ApplicationContext)
+ [Bean Life Cycle](spring.md#Bean-Life-Cycle)
+ [Differance between @Component, @Service and @Repository](spring.md#Differance-between-@Component,-@Service-and-@Repository)
+ [Design Patterns Used in the Spring Framework](spring.md#Design-Patterns-Used-in-the-Spring-Framework)
+ [Controller in Spring MVC](spring.md#Controller-in-Spring-MVC)
+ [@RequestMapping Annotation](spring.md#@RequestMapping-Annotation)
+ [Spring Boot Main Features](spring.md#Spring-Boot-Main-Features)
+ [Spring Boot Basic Annotations](spring.md#Spring-Boot-Basic-Annotations)

[bean-life-cycle]:img/Spring-Bean-Life-Cycle.jpg

[к оглавлению](#Spring-Questions)

## What Is Spring Framework
Spring is a powerful open-source, loosely coupled, lightweight, java framework meant for reducing the 
complexity of developing enterprise-level applications. This framework is also called the “framework of frameworks” 
as spring provides support to various other important frameworks like JSF, Hibernate, Structs, EJB, etc.

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

[Заглавная](README.md)