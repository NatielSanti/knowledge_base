[Заглавная](README.md)

# Spring Questions
+ [What Is Spring Framework](spring.md#What-Is-Spring-Framework)
+ [Spring Benefits](spring.md#Spring-Benefits)
+ [Spring Sub-Projects](spring.md#Spring-Sub-Projects)
+ [Best Way of Injecting Beans](spring.md#Best-Way-of-Injecting-Beans)
+ [Difference Between BeanFactory and ApplicationContext](spring.md#Difference-Between-BeanFactory-and-ApplicationContext)
+ [Bean Life Cycle](spring.md#Bean-Life-Cycle)
+ [Design Patterns Used in the Spring Framework](spring.md#Design-Patterns-Used-in-the-Spring-Framework)
+ [Controller in Spring MVC](spring.md#Controller-in-Spring-MVC)
+ [@RequestMapping Annotation](spring.md#@RequestMapping-Annotation)
+ [Spring JdbcTemplate](spring.md#Spring-JdbcTemplate)
+ [Hibernate and JPA difference](spring.md#Hibernate-and-JPA-difference)

[bean-life-cycle]:img/Spring-Bean-Life-Cycle.jpg

[к оглавлению](#Spring-Questions)

## What Is Spring Framework
Spring is a powerful open-source, loosely coupled, lightweight, java framework meant for reducing the complexity of developing enterprise-level applications. This framework is also called the “framework of frameworks” as spring provides support to various other important frameworks like JSF, Hibernate, Structs, EJB, etc.

[к оглавлению](#Spring-Questions)

## Spring Benefits
Spring targets to make Jakarta EE development easier, so let's look at the advantages:

- **Lightweight** – There is a slight overhead of using the framework in development.
- **Inversion of Control (IoC)** – Spring container takes care of wiring dependencies of various objects instead of creating or looking for dependent objects.
- **Aspect-Oriented Programming (AOP)** – Spring supports AOP to separate business logic from system services.
- **IoC container** – manages Spring Bean life cycle and project-specific configurations
- **MVC framework** – used to create web applications or RESTful web services, capable of returning XML/JSON responses
- **Transaction management** – reduces the amount of boilerplate code in JDBC operations, file uploading, etc., either by using Java annotations or by Spring Bean XML configuration file
- **Exception Handling** – Spring provides a convenient API for translating technology-specific exceptions into unchecked exceptions.

[к оглавлению](#Spring-Questions)

## Spring Sub-Projects
- **Core** – a key module that provides fundamental parts of the framework, such as IoC or DI
- **JDBC** – enables a JDBC-abstraction layer that removes the need to do JDBC coding for specific vendor databases
- **ORM integration** – provides integration layers for popular object-relational mapping APIs, such as JPA, JDO and Hibernate
- **Web** – a web-oriented integration module that provides multipart file upload, Servlet listeners and web-oriented application context functionalities
- **MVC framework** – a web module implementing the Model View Controller design pattern
- **AOP module** – aspect-oriented programming implementation allowing the definition of clean method-interceptors and pointcuts

[к оглавлению](#Spring-Questions)

## Best Way of Injecting Beans
The recommended approach is to use constructor arguments for mandatory dependencies and setters for optional ones. This is because constructor injection allows injecting values to immutable fields and makes testing easier.

[к оглавлению](#Spring-Questions)

## Difference Between BeanFactory and ApplicationContext
#### Bean Factory
- Bean instantiation/wiring
#### Application Context
- Bean instantiation/wiring
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

## Controller in Spring MVC?
Simply put, all the requests processed by the DispatcherServlet are directed to classes annotated with @Controller. Each controller class maps one or more requests to methods that process and execute the requests with provided inputs.

[к оглавлению](#Spring-Questions)

## @RequestMapping Annotation
The ```@RequestMapping``` annotation is used to map web requests to Spring Controller methods. 
In addition to simple use cases, we can use it for mapping of HTTP headers, binding parts of the URI with ```@PathVariable```, 
and working with URI parameters and the ```@RequestParam``` annotation.

[к оглавлению](#Spring-Questions)

## Spring JdbcTemplate
The Spring JDBC template is the primary API through which we can access database operations logic that we’re interested in:

- Creation and closing of connections
- Executing statements and stored procedure calls
- Iterating over the ResultSet and returning results

[к оглавлению](#Spring-Questions)

## Hibernate and JPA difference
- **JPA** - Java Persistence API (JPA) defines the management of relational data in the Java applications.
- **Hibernate** - Hibernate is an Object-Relational Mapping (ORM) tool which is used to save the state of Java object into the database.
It is one of the most frequently used JPA implementation.

[к оглавлению](#Spring-Questions)

[Заглавная](README.md)