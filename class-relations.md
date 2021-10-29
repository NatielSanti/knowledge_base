[Заглавная](README.md)

# Class relations
+ [Inheritance](class-relations.md#Inheritance)
+ [Association-Composition](class-relations.md#Association-Composition)
+ [Association-Aggregation](class-relations.md#Association-Aggregation)

## Inheritance

Прямое наследование. Автомобиль это вид транспорта.
```python
class Vehicle
{
    bool hasWheels;
}

class Car : Vehicle
{
    string model = "Porshe";
    int numberOfWheels = 4
}
```
[к оглавлению](#Class-relations)

## Association-Composition

Ассоциация. Композиция - вид ассоциации. Одна сущность является полем в другой. 
Двигатель создаётся в конструкторе Автомобиля.

```
class Engine
{
    int power;
    public Engine(int p)
    {
        power = p;
    }
}

class Car
{
    string model = "Porshe";
    Engine engine;
    public Car()
    {
        this.engine = new Engine(360);
    }
}
```

[к оглавлению](#Class-relations)

## Association-Aggregation

Ассоциация. Агрегация - вид ассоциации. Одна сущность является полем в другой.
Двигатель создаётся в другом месте, а потом передаётся в качестве параметра.

```
class Engine
{
    int power;
    public Engine(int p)
    {
       power = p;
    }
}        

class Car
{
    string model = "Porshe";
    Engine engine;
    public Car(Engine someEngine)
    {
         this.engine = someEngine;
    }
}

Engine goodEngine = new Engine(360);
Car porshe = new Car(goodEngine);

```
[к оглавлению](#Class-relations)

[Заглавная](README.md)

