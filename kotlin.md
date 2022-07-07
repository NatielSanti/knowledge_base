[Заглавная](README.md)

# Kotlin

+ [Основы](#Core)
+ [Функции](#Функции)
+ [Классы и объекты](#Классы-и-объекты)
+ [ООП](#ООП)
+ [Обобщения-Generics](#Обобщения-Generics)
+ [Дополнительные возможности ООП](#Дополнительные-возможности-ООП)
+ [Коллекции](#Коллекции)
+ [Последовательности](#Последовательности)
+ [Корутины](#Корутины)

[Источник](https://metanit.com/kotlin/)

[к оглавлению](#Kotlin)

[//]: # ([docker_1]:img/microservices/docker_1.JPG)
[//]: # (![icon][docker_1])
[collections]:img/kotlin/collections.png

## Core

#### Переменные
`val age: Int = 23` - иммутабельная переменная
`var age: Int = 23` - мутабельная переменная
#### Примитивы
`Byte, Short, Int, Long` - как в java
`UByte, UShort, UInt, ULong` - только положительные числа
`val address: Int = 0x0A1` - шестнадцатиричные
`val a: Int = 0b0101` - двоичные
`val height: Double = 1.78` - с плавающей точкой как в java
`val pi: Float = 3.14F`
Тип - Любой
```kotlin
var name: Any = "Tom"
    println(name)   // Tom
    name = 6758
    println(name)   // 6758
```

#### Операции
`shl`: сдвиг битов числа со знаком влево
`shr`: сдвиг битов числа со знаком вправо
`ushr`: сдвиг битов беззнакового числа вправо
`and`: побитовая операция AND (логическое умножение или конъюнкция). 
Эта операция сравнивает соответствующие разряды двух чисел и возвращает единицу, 
если эти разряды обоих чисел равны 1. Иначе возвращает 0.
`or`: побитовая операция OR (логическое сложение или дизъюнкция). 
Эта операция сравнивают два соответствуюших разряда обоих чисел и возвращает 1, 
если хотя бы один разряд равен 1. Если оба разряда равны 0, то возвращается 0.
`xor`: побитовая операция XOR. Сравнивает два разряда и возвращает 1, если один из разрядов равен 1, а другой равен 0. 
Если оба разряда равны, то возвращается 0.
`inv`: логическое отрицание или инверсия - инвертирует биты числа
`in`: возвращает true, если операнд имеется в некоторой последовательности.
```kotlin
val a = 5
val b = a in 1..6       // true - число 5 входит в последовательность от 1 до 6
```
`when` - альтернатива if..else
```kotlin
val a = 10
when(a){
    in 10..19 -> println("a в диапазоне от 10 до 19")
    in 20..29 -> println("a в диапазоне от 20 до 29")
    !in 10..20 -> println("a вне диапазона от 10 до 20")
    else -> println("неопределенное значение")
}
```
Циклы
```kotlin
for(n in 1..9){
    print("${n * n} \t")
}
```

#### Диапозоны

```kotlin
val range = 1..5    // диапазон [1, 2, 3, 4, 5]
val range =  "a".."d"
val range2 =  5 downTo 1    // 5 4 3 2 1
val range1 = 1..10 step 2           // 1 3 5 7 9
val range2 = 10 downTo 1 step 3     // 10 7 4 1
val range1 = 1 until 9          // 1 2 3 4 5 6 7 8
val range2 = 1 until 9 step 2   // 1 3 5 7

val range = 1..5
var isInRange = 5 in range
println(isInRange)      // true
```

#### Массивы

```kotlin
val numbers: Array<Int> = arrayOf(1, 2, 3, 4, 5)
val numbers = Array(3, {5}) // [5, 5, 5]

var i = 1
val numbers = Array(3, { i++ * 2}) // [2, 4, 6]

val people = arrayOf("Tom", "Sam", "Bob")
var i = 0
while( i in people.indices){
    println(i) // 0, 1, 2
    i++
}

val numbers: IntArray = intArrayOf(1, 2, 3, 4, 5)
val doubles: DoubleArray = doubleArrayOf(2.4, 4.5, 1.2)
```
[к оглавлению](#Kotlin)

## Функции
Параметры функции неизменяемые `val`. А внутренние элементы параметров изменяемые. 
```kotlin
fun main() {
    displayUser("Tom", 23)
    displayUser("Alice", 19)
    displayUser("Kate", 25)
}
fun displayUser(name: String, age: Int){
    println("Name: $name   Age: $age")
}
```
Значения по умолчанию
```kotlin
fun displayUser(name: String, age: Int = 18, position: String="unemployed"){
    println("Name: $name   Age: $age  Position: $position")
}
```
Измененный порядок параметров вызова
```kotlin
fun main() {
    displayUser("Tom", position="Manager", age=28)
    displayUser(age=21, name="Alice")
    displayUser("Kate", position="Middle Developer")
}
```

#### Параметры переменной длины
```kotlin
fun printStrings(vararg strings: String){
    for(str in strings)
        println(str)
}
fun main() {
 
    printStrings("Tom", "Bob", "Sam")
    printStrings("Kotlin", "JavaScript", "Java", "C#", "C++")
}
```
Параметры переменной длины могут быть в начале вызова функции, если остальные параметры именованы
```kotlin
fun printUserGroup(group: String, vararg users: String, count:Int){
    println("Group: $group")
    println("Count: $count")
    for(user in users)
        println(user)
}
fun main() {
 
    printUserGroup("KT-091", "Tom", "Bob", "Alice", count=3)
}
```

#### Оператор * (spread operator) 
(не стоит путать со знаком умножения) позволяет передать параметру в качестве значения 
элементы из массива:

```kotlin
fun changeNumbers(vararg numbers: Int, koef: Int){
    for(number in numbers)
        println(number * koef)
}
fun main() {
 
    val nums = intArrayOf(1, 2, 3, 4)
    changeNumbers(*nums, koef=2)
}
```

#### Однострочные функции
```kotlin
fun square(x: Int) : Int = x * x
```
Можно делать локальные функции!!
```kotlin
fun compareAge(age1: Int, age2: Int){
 
    fun ageIsValid(age: Int)= age > 0 && age < 111
 
    if( !ageIsValid(age1) || !ageIsValid(age2)) {
        println("Invalid age")
        return
    }
 
    when {
        age1 == age2 -> println("age1 == age2")
        age1 > age2 -> println("age1 > age2")
        age1 < age2 -> println("age1 < age2")
    }
}
```

#### Переменные-функции
```kotlin
fun main() {
 
    val message: () -> Unit 
    message = ::hello
    message()
}
 
fun hello(){
    println("Hello Kotlin")
}
```
```kotlin
fun main() {

    // operation указывает на функцию sum
    var operation: (Int, Int) -> Int = ::sum
    val result1 = operation(14, 5)
    println(result1) // 19

    // operation указывает на функцию subtract
    operation = ::subtract
    val result2 = operation(14, 5)
    println(result2) // 9

}
fun sum(a: Int, b: Int): Int{
    return a + b
}
fun subtract(a: Int, b: Int): Int{
    return a - b
}
```
#### Функции высокого порядка
```kotlin
fun main() {

    action(5, 3, ::sum)         // 8
    action(5, 3, ::multiply)    // 15
    action(5, 3, ::subtract)    // 2
}

fun action (n1: Int, n2: Int, op: (Int, Int)-> Int){
    val result = op(n1, n2)
    println(result)
}
fun sum(a: Int, b: Int): Int{
    return a + b
}
fun subtract(a: Int, b: Int): Int{
    return a - b
}
fun multiply(a: Int, b: Int): Int {
    return a * b
}
```
#### Возвращение функции из функции
```kotlin
fun main() {
    val action1 = selectAction(1)
    println(action1(8,5))    // 13

    val action2 = selectAction(2)
    println(action2(8,5))    // 3
}
fun selectAction(key: Int): (Int, Int) -> Int{
    // определение возвращаемого результата
    when(key){
        1 -> return ::sum
        2 -> return ::subtract
        3 -> return ::multiply
        else -> return ::empty
    }
}
fun empty (a: Int, b: Int): Int{
    return 0
}
fun sum(a: Int, b: Int): Int {
    return a + b
}
fun subtract(a: Int, b: Int): Int{
    return a - b
}
fun multiply(a: Int, b: Int): Int{
    return a * b
}
```
#### Анонимные функции
```kotlin
fun main() {

    val sum = fun(x: Int, y: Int): Int = x + y
    val result = sum(5, 4)
    println(result)     // 9
}
```
#### Лямбда-выражения
```kotlin
fun main() {
    val printer = {message: String -> println(message)}
    printer("Hello")
    printer("Good Bye")

    {message: String -> println(message)}("Welcome to Kotlin")

    val sum = {x:Int, y:Int -> println(x + y)}
    sum(2, 3)   // 5
    sum(4, 5)   // 9

    val sum = {x:Int, y:Int -> x + y}
    val a = sum(2, 3)   // 5
    val b = sum(4, 5)   // 9
    println("a=$a  b=$b")
}
```
#### trailing lambda
```kotlin
fun doOperation(x: Int, y: Int, op: (Int, Int) ->Int){
    val result = op(x, y)
    println(result)
}

doOperation(3, 4, {a, b -> a * b}) // 12
doOperation(3, 4) {a, b -> a * b} // 12
```
[к оглавлению](#Kotlin)

## Классы и объекты

#### Классы
```kotlin
class Person{
    var name: String = "Undefined"
    var age: Int = 18
}
```
#### Конструкторы
```kotlin
fun main() {
    val tom = Person("Tom")
    val bob = Person("Bob")
    val alice = Person("Alice")
     
    println(tom.name)   // Tom
    println(bob.name)   // Bob
    println(alice.name) // Alice
}
 
class Person(_name: String){
    val name: String
    init{
        name = _name
    }
}
```
```kotlin
fun main() {

    val tom: Person = Person("Tom")
    val bob: Person = Person("Bob", 45)

    println("Name: ${tom.name}  Age: ${tom.age}")
    println("Name: ${bob.name}  Age: ${bob.age}")
}

class Person(_name: String){
    val name: String = _name
    var age: Int = 0

    constructor(_name: String, _age: Int) : this(_name){
        age = _age
    }
}
```
#### Пакеты и импорт
```kotlin
import email.send as sendEmail
import email.Message as EmailMessage
 
fun main() {
 
    val myMessage = EmailMessage("Hello Kotlin")
    sendEmail(myMessage, "tom@gmail.com")
}
```

#### Наследование
Чтобы функциональность класса можно было унаследовать, 
необходимо определить для этого класса аннотацию `open`. 
По умолчанию без этой аннотации класс не может быть унаследован.

```kotlin
fun main() {
 
    val bob = Employee("Bob", "JetBrains")
    bob.printName()
    bob.printCompany()
}
 
open class Person(val name: String){
    fun printName(){
        println(name)
    }
}
class Employee(empName: String, val company: String): Person(empName){
 
    fun printCompany(){
        println(company)
    }
}
```
[к оглавлению](#Kotlin)

## ООП
#### Сеттер и Геттер
```kotlin
var age: Int = 18
    set(value){
        if((value>0) and (value <110))
            field = value
    }
    get(){
        println("Call getter")
        return field
    }
 
fun main() {
    println(age)    // 18
    age = 45
    println(age)    // 45
    age = -345
    println(age)    // 45
}
```
#### Переопределение методов
```kotlin
open class Person(val name: String){
    open fun display(){
        println("Name: $name")
    }
}
open class Employee(name: String, val company: String): Person(name){
 
    override fun display() {
        println("Name: $name    Company: $company")
    }
}
class Manager(name: String, company: String):Employee(name, company){
    override fun display() {
        println("Name: $name Company: $company  Position: Manager")
    }
}
```
#### Interface
```kotlin
interface Movable{
    var speed: Int  // объявление свойства
    fun move()      // определение функции без реализации
    fun stop(){     // определение функции с реализацией по умолчанию
        println("Остановка")
    }
}

class Car : Movable{

    override var speed = 60
    override fun move(){
        println("Машина едет со скоростью $speed км/ч")
    }
}
class Aircraft : Movable{

    override var speed = 600
    override fun move(){
        println("Самолет летит со скоростью $speed км/ч")
    }
    override fun stop(){
        println("Приземление")
    }
}

fun main() {

    val m1: Movable = Car()
    val m2: Movable = Aircraft()
    // val m3: Movable = Movable() напрямую объект интерфейса создать нельзя

    m1.move()
    m1.stop()
    m2.move()
    m2.stop()
}
```
Переопределение
```kotlin
open class Video {
    open fun play() { println("Play video") }
}
 
interface AudioPlayable {
    fun play() { println("Play audio") }
}
 
class MediaPlayer() : Video(), AudioPlayable {
    // Функцию play обязательно надо переопределить
    override fun play() {
        super<Video>.play()         // вызываем Video.play()
        super<AudioPlayable>.play() // вызываем AudioPlayable.play()
    }
}
```

Внутренние и вложенные классы.
Вложенные классы не имею доступа к членам внешнего класса, а внутренние имеют. 
Различие в ключевом слове `inner`.
```kotlin
class A{
    private val n: Int = 1
    inner class B{ // внутренний класс, имеет доступ к членам внешнего класса
        private val n: Int = 1
        fun action(){
            println(n)          // n из класса B
            println(this.n)     // n из класса B
            println(this@B.n)   // n из класса B
            println(this@A.n)   // n из класса A
        }
    }
}
```
#### Data class
```kotlin
data class Person(val name: String, val age: Int){
    override fun toString(): String {
        return "Name: $name  Age: $age"
    }
}
```
- `equals()`: сравнивает два объекта на равенство
- `hashCode()`: возвращает хеш-код объекта
- `toString()`: возвращает строковое представление объекта
- `copy()`: копирует данные объекта в другой объект

При этом чтобы класс определить как data-класс, он должен соответствовать ряду условий:
- Первичный конструктор должен иметь как минимум один параметр
- Все параметры первичного конструктора должны предваряться ключевыми словами val или var, 
то есть определять свойства
- Свойства, которые определяются вне первичного конструктора, не используются в функциях toString, 
equals и hashCode
- Класс не должен определяться с модификаторами open, abstract, sealed или inner.

#### Декомпозиция data-классов
```kotlin
fun main() {
 
    val alice: Person = Person("Alice", 24)
 
    val (username, userage) = alice
    println("Name: $username  Age: $userage") // Name: Alice  Age: 24
}
 
data class Person(var name: String, var age: Int)
```

#### Делегирование
Передача вызовов в другой класс
```kotlin
interface Base {
    fun someFun()
}
 
class BaseImpl() : Base {
    override fun someFun() { }
}
 
class Derived(someBase: Base) : Base by someBase
```

#### Анонимные объекты

```kotlin
fun main() {
    val tom = object {
        val name = "Tom"
        var age = 37
        fun sayHello(){
            println("Hi, my name is $name")
        }
    }
    println("Name: ${tom.name}  Age: ${tom.age}")
    tom.sayHello()
}
// ------------------
fun main() {

    val tom = object : Person("Tom"){

        val company = "JetBrains"
        override fun sayHello(){
            println("Hi, my name is $name. I work in $company")
        }
    }

    tom.sayHello()  // Hi, my name is Tom. I work in JetBrains
}
open class Person(val name: String){
    open fun sayHello(){
        println("Hi, my name is $name")
    }
}
```

#### Анонимный объект как результат функции

```kotlin
fun main() {
    val tom = createPerson("Tom", "JetBrains")
    tom.sayHello()
}
private fun createPerson(_name: String, _company: String) = object{
    val name = _name
    val company = _company
    fun sayHello() = println("Hi, my name is $name. I work in $company")
}
```
Однако тут есть нюансы. Чтобы мы могли обращаться к свойствам и функциям анонимного объекта,
функция, которая возвращает этот объект, должна быть приватной.
Если же функция будет `private inline` или `public`. (за исключением унаследованных свойств)
```kotlin
fun main() {
    val tom = createPerson("Tom", "JetBrains")
    println(tom.name)   // норм - свойство name унаследовано от Person
    println(tom.company)    // ! Ошибка - свойство недоступно
}
private inline fun createPerson(_name: String, _comp: String) = object: Person(_name){
    val company = _comp
}
 
open class Person(val name: String)
```
[к оглавлению](#Kotlin)

## Обобщения-Generics
```kotlin
fun main() {

    val tom: Person<Int> = Person(367, "Tom")
    val bob: Person<String> = Person("A65", "Bob")

    println("${tom.id} - ${tom.name}")
    println("${bob.id} - ${bob.name}")
}

class Person<T>(val id: T, val name: String)
```
#### Ограничения обобщений
Если не ограничить тип <T> - 
то так как <T> может быть любого типа в некоторых случаях код может вызвать ошибку.
```kotlin
fun main() {
 
    val result1 = getBiggest(1, 2)
    println(result1)
    val result2 = getBiggest("Tom", "Sam")
    println(result2)
 
}
fun <T: Comparable<T>> getBiggest(a: T, b: T): T{
    return if(a > b) a
    else b
}
```
```kotlin
fun <T> getBiggest(a: T, b: T): T where T: Comparable<T>, 
                                        T: Number {
    return if(a > b) a
    else b
}

fun main() {

    val result1 = getBiggest(1, 2)
    println(result1)    // 2

    val result2 = getBiggest(1.6, -2.8)
    println(result2)    // 1.6

    // val result3 = getBiggest("Tom", "Sam")  // ! Ошибка - String не является производным от класса Number
    // println(result3)
}
```
#### Вариантность, ковариантность и контравариантность

Так же можно посмотреть в [PECS](java-core.md#PECS)
`инвариантность`: `Type<Base> != Type<Derived>`, 
вместо базового нельзя использовать производный и наоборот
`ковариантность`: вместо базового типа можно использовать производный, 
относится к возвращению данных из метода
`контрвариантность`: вместо производного типа можно использовать базовый, 
относится к передаче параметров в методов

- `Инвариантность` предполагает, что, если у нас есть классы `Base` и `Derived`, 
где `Derived` - производный класс от Base, 
то класс `C<Base>` не является ни базовым классом для `С<Derived>`, ни производным.
```kotlin
interface Messenger<T: Message>()

open class Message(val text: String)
class EmailMessage(text: String): Message(text)

fun changeMessengerToEmail(obj: Messenger<EmailMessage>){
    val messenger: Messenger<Message> = obj   // ! Ошибка
}
fun changeMessengerToDefault(obj: Messenger<Message>){
    val messenger: Messenger<EmailMessage> = obj      // ! Ошибка
}

fun changeMessengerToDefault(obj: Messenger<Message>){
    val messenger: Messenger<Message> = obj
}
fun changeMessengerToEmail(obj: Messenger<EmailMessage>){
    val messenger: Messenger<EmailMessage> = obj
}
```
- `Ковариантость` предполагает, что, если у нас есть классы `Base` и `Derived`, 
где `Base` - базовый класс для `Derived`, 
то класс `SomeClass<Base>` является базовым классом для `SomeClass<Derived>`
Для определения обобщенного типа как ковариантного параметр обощения определяется 
с ключевым словом `out`
```kotlin
interface Messenger<out T: Message>
open class Message(val text: String)
class EmailMessage(text: String): Message(text)

fun changeMessengerToEmail(obj: Messenger<EmailMessage>){
    val messenger: Messenger<Message> = obj
}
```
Вообще не случайно используется именно слово `out`. Оно указывает, 
что обобщенный тип может возвращать из функции значение типа `T`:

```kotlin
fun main() {

    val messenger: Messenger<Message> = EmailMessenger()
    val message = messenger.writeMessage("Hello Kotlin")
    println(message.text)    // Email: Hello Kotlin
}
open class Message(val text: String)
class EmailMessage(text: String): Message(text)

interface Messenger<out T: Message>{
fun writeMessage(text: String): T
}
class EmailMessenger(): Messenger<EmailMessage>{
override fun writeMessage(text: String): EmailMessage {
return EmailMessage("Email: $text")
}
}
```

- `Контравариантность` предполагает в какой-то степени обратную ситуацию. 
Контравариантность предполагает, что, если у нас есть классы `Base` и `Derived`, 
где `Base` - базовый класс для `Derived`, 
то объекту `SomeClass<Derived>` мы можем присвоить значение `SomeClass<Base>` 
(при ковариантности, наоборот, 
объекту `SomeClass<Base>` можно присвоить значение `SomeClass<Derived>`)

Для определения обобщенного типа как контравариантного параметр обобщения 
определяется с ключевым словом `in`

```kotlin
interface Messenger<in T: Message>
open class Message(val text: String)
class EmailMessage(text: String): Message(text)

fun changeMessengerToDefault(obj: Messenger<Message>){
    val messenger: Messenger<EmailMessage> = obj
}
```
```kotlin
fun main() {
 
    val messenger: Messenger<EmailMessage> = InstantMessenger() // InstantMessenger - это Messenger<Message>
 
    val message = EmailMessage("Hi Kotlin")
    messenger.sendMessage(message)
}
open class Message(val text: String)
class EmailMessage(text: String): Message(text)
 
interface Messenger<in T: Message>{
    //fun writeMessage(text: String): T
    fun sendMessage(message: T)
}
 
class InstantMessenger(): Messenger<Message>{
    override fun sendMessage(message: Message){
        println("Send message: ${message.text}")
    }
}
```

[к оглавлению](#Kotlin)

## Дополнительные возможности ООП

#### Null и nullable-типы
```kotlin
// val n : Int = null  //! ошибка, Int не допускает значение null
val d : Int? = null // норм, Int? допускает значение null
```
```kotlin
var age : Int? = null
age = 34              // Int? допускает null и числа
var name : String? = null
name = "Tom"        // String? допускает null и строки
```
```kotlin
var message : String? = "Hello"
val hello: String = message     // ! Ошибка - hello не допускает значение null

// у типа String свойство length возвращает длину строки
println("Message length: ${message.length}")    // ! Ошибка
```
Оператор `?:`
```kotlin
var name : String?  = "Tom"
val userName: String = name ?: "Undefined"  // если name = null, то присваивается "Undefined"
 
var age: Int? = 23
val userAge: Int = age ?:0  // если age равно null, то присваивается число 0
```
Оператор `?.`
```kotlin
var message : String? = "Hello"
val length: Int? = message?.length

val length: Int = message?.length ?:0
```
Оператор `!!` (not-null assertion operator) принимает один операнд. Если операнд равен `null`, 
то генерируется исключение. Если операнд не равен `null`, то возвращается его значение.
```kotlin
fun main() {
    try {
        val name : String?  = "Tom"
        val id: String = name!!
        println(id)
    } catch (e: Exception) { println(e.message)}
}
```
```kotlin
val name : String?  = null
val length :Int = name!!.length
```

#### Преобразование типов
- toByte
- toShort
- toInt
- toLong
- toFloat
- toDouble
- toChar

```kotlin
val s: String = "tom"
try {
    val d: Int = s.toInt()
    println(d)
}
catch(e: Exception){
    println(e.message)
}
```

#### Smart cast и оператор is
```kotlin
fun main() {
 
    val tom = Person("Tom")
    val bob = Employee("Bob", "JetBrains")
 
    checkEmployment(tom)    // Tom does not have a job
    checkEmployment(bob)    // Bob works in JetBrains
}
 
fun checkEmployment(person: Person){
    // println("${person.name} works in ${person.company}")    // Ошибка - у Person нет свойства company
    if(person is Employee){
        println("${person.name} works in ${person.company}")
    }
    else{
        println("${person.name} does not have a job")
    }
}
open class Person(val name: String)
class Employee(name: String, val company: String): Person(name)
```

- smart-преобразования применяются к локальным val-переменным 
(за исключением делегированных свойств)
- smart-преобразования применяются к val-свойствам, 
за исключением свойств с модификатором open 
(то есть открытых к переопределению в производных классах) или свойств, 
для которых явным образом определен геттер
- smart-преобразования применяются к локальным var-переменным 
(то есть к переменным, определенным в функциях), 
если переменная не изменяет своего значения в промежутке между проверкой и 
использованием и не используется в лямбда-выражении, которое изменяет ее, 
а также не является локальным делегированным свойством
- к var-свойствам smart-преобразования не применяются

#### Явные преобразования и оператор `as`
```kotlin
fun main() {
    val hello: String? = "Hello Kotlin"
    val message: String = hello as String
    println(message)
}
```
```kotlin
fun main() {
 
    val tom = Person("Tom")
    val bob = Employee("Bob", "JetBrains")
    checkCompany(tom)
    checkCompany(bob)
}
fun checkCompany(person: Person){
    val employee = person as? Employee
    if (employee!=null){
        println("${employee.name} works in ${employee.company}")
    }
    else{
        println("${person.name} is not an employee")
    }
}
open class Person(val name: String)
open class Employee(name: String, var company: String): Person(name)
```

#### Функции расширения

Функции расширения (extension function) позволяют добавить функционал к уже определенным типам. 
При этом типы могут быть определены где-то в другом месте, например, в стандартной библиотеке.
```kotlin
fun main() {

    val hello: String = "hello world"
    println(hello.wordCount('l'))   // 3
    println(hello.wordCount('o'))   // 2
    println(4.square())                 // 16
    println(6.square())                 // 36
}

fun String.wordCount(c: Char) : Int{
    var count = 0
    for(n in this){
        if(n == c) count++
    }
    return count
}
fun Int.square(): Int{
    return this * this
}
```

#### Перегрузка операторов

Простейшие операции преобразуются в функции:
- +a -> a.unaryPlus()
- -a -> a.unaryMinus()
- !a -> a.not()
- ++a / a++ -> a.inc()
- --a / a-- -> a.dec()
- a + b -> a.plus(b)
- a - b -> a.minus(b)
- a * b -> a.times(b)
- a / b -> a.div(b)
- a % b -> a.rem(b)
- a..b -> a.rangeTo(b)
- a == b -> a?.equals(b) ?: (b === null)
- a != b -> !(a?.equals(b) ?: (b === null))
- a > b -> a.compareTo(b) > 0
- a < b -> a.compareTo(b) < 0
- a >= b -> a.compareTo(b) >= 0
- a <= b -> a.compareTo(b) <= 0
- a += b -> a.plusAssign(b)
- a -= b -> a.minusAssign(b)
- a *= b -> a.timesAssign(b)
- a /= b -> a.divAssign(b)
- a %= b -> a.remAssign(b)
- a in b -> b.contains(a)
- a !in b -> !b.contains(a)
- a[i] -> a.get(i)
- a[i, j] -> a.get(i, j)
- a[i_1, ..., i_n] -> a.get(i_1, ..., i_n)
- a[i] = b -> a.set(i, b)
- a[i, j] = b -> a.set(i, j, b)
- a[i_1, ..., i_n] = b -> a.set(i_1, ..., i_n, b)
- a() -> a.invoke()
- a(i) -> a.invoke(i)
- a(i, j) -> a.invoke(i, j)
- a(i_1, ..., i_n) -> a.invoke(i_1, ..., i_n)
- 
```kotlin
fun main(){
    val counter1 = Counter(5)
    val counter2 = Counter(7)
 
    val counter1IsGreater = counter1 > counter2
    val counter3: Counter = counter1 + counter2
     
    println(counter1IsGreater)  // false
    println(counter3.value)     // 12
}
 
class Counter(var value: Int){
 
    operator fun compareTo(counter: Counter) : Int{
        return this.value - counter.value
    }
    operator fun plus(counter: Counter): Counter {
        return Counter(this.value + counter.value)
    }
}
```

#### Делегированные свойства

```kotlin
import kotlin.reflect.KProperty
fun main() {
    val tom = Person("Tom")
    println(tom.name)

    val bob = Person("Bob")
    println(bob.name)
}
class Person(_name: String){
    val name: String by LoggerDelegate(_name)
}
class LoggerDelegate(val personName: String) {
    operator fun getValue(thisRef: Person, property: KProperty<*>): String {
        println("Запрошено свойство ${property.name}")
        println("Устанавливаемое значение: $personName")
        return personName
    }
}
```
```kotlin
import kotlin.reflect.KProperty
 
fun main() {
 
    val tom = Person("Tom", 37)
    println(tom.age)    //37
    tom.age = 38
    println(tom.age)    //38
    tom.age = -139
    println(tom.age)    //38
 
}
class Person(val name: String, _age: Int){
    var age: Int by LoggerDelegate(_age)
}
class LoggerDelegate(private var personAge: Int) {
    operator fun getValue(thisRef: Person, property: KProperty<*>): Int{
        return personAge
    }
    operator fun setValue(thisRef: Person, property: KProperty<*>, value: Int){
        println("Устанавливаемое значение: $value")
        if(value > 0 && value < 110) personAge = value
    }
}
```

#### Scope функции

- `let`
Лямбда-выражение в функции `let` в качестве параметра it получает объект, 
для которого вызывается функция. 
Возвращаемый результат функции `let` представляет результат данного лямбда-выражения.
```kotlin
inline fun <T, R> T.let(block: (T) -> R): R
```
Распространенным сценарием, где применяется данная функция, представляет проверка на null:
```kotlin
fun main() {

    val sam = Person("Sam", "sam@gmail.com")
    sam.email?.let{ println("Email: $it") }     // Email: sam@gmail.com
    // аналог без функции let
    //if(sam.email!=null) println("Email:${sam.email}")
 
    val tom = Person("Tom", null)
    tom.email?.let{ println("Email: $it") } // функция let не будет выполняться

}
data class Person(val name: String, val email: String?)
```
- `with`
Лямбда-выражение в функции with в качестве параметра this получает объект, 
для которого вызывается функция. 
Возвращаемый результат функции `with` представляет результат данного лямбда-выражения.

```kotlin
inline fun <T, R> with(receiver: T, block: T.() -> R): R
```
Функция `with` принимает объект, для которого надо выполнить блок кода, в качестве параметра. 
Далее в самом блоке кода мы можем обращаться к общедоступным свойствам и методам объекта 
без его имени.
Обычно функция `with()` применяется, 
когда надо выполнить над объектом набор операций как единое целое:
```kotlin
fun main() {

    val tom = Person("Tom", null)
    val emailOfTom = with(tom) {
        if(email==null)
            email = "${name.lowercase()}@gmail.com"
        email
    }
    println(emailOfTom) // tom@gmail.com
}
data class Person(val name: String, var email: String?)
```
В данном случае функция `with` получает объект tom, 
поверяет его свойство email - если оно равно null, то устанавливает его на основе его имени. 
В качестве результата функции возвращается значение свойства email.

- `run`
Лямбда-выражение в функции run в качестве параметра this получает объект, 
для которого вызывается функция. 
Возвращаемый результат функции run представляет результат данного лямбда-выражения.

```kotlin
inline fun <T, R> T.run(block: T.() -> R): R
```
Функция run похожа на функцию `with` за тем исключением, что run реализована как функция расширения. 
Функция run также принимает объект, для которого надо выполнить блок кода, 
в качестве параметра. 
Далее в самом блоке кода мы можем обращаться к общедоступным свойствам и методам объекта 
без его имени.
```kotlin
fun main() {

    val tom = Person("Tom", null)
    val emailOfTom = tom.run {
        if(email==null)
            email = "${name.lowercase()}@gmail.com"
        email
    }
    println(emailOfTom) // tom@gmail.com
}
data class Person(val name: String, var email: String?)
```

- `apply`
Лямбда-выражение в функции `apply` в качестве параметра this получает объект, 
для которого вызывается функция. 
Возвращаемым результатом функции фактически является передаваемый в функцию объект 
для которого выполняется функция.

```kotlin
inline fun T.apply(block: T.() -> Unit): T
```
Например:
```kotlin
fun main() {

    val tom = Person("Tom", null)
    tom.apply {
        if(email==null)
            email = "${name.lowercase()}@gmail.com"
    }
    println(tom.email) // tom@gmail.com
}
data class Person(val name: String, var email: String?)
```
В данном случае, как и в примерах с функциями `with` и `run`, 
проверяем значение свойства email, и если оно равно null, устанавливаем его, 
используя свойство name.

Распространенным сценарием применения функции `apply()` 
является построение объекта в виде реализации вариации паттерна "Строитель":

```kotlin
fun main() {

    val bob = Employee()
    bob.name("Bob")
    bob.age(26)
    bob.company("JetBrains")
    println("${bob.name} (${bob.age}) - ${bob.company}") // Bob (26) - JetBrains
}

data class Employee(var name: String = "", var age: Int = 0, var company: String = "") {
fun age(_age: Int): Employee = apply { age = _age }
fun name(_name: String): Employee = apply { name = _name }
fun company(_company: String): Employee = apply { company = _company }
}
```
В данном случае класс Employee содержит три метода, 
которые устанавливают каждое из свойств класса. 
Причем каждый подобный метод вызывает функцию `apply()`, 
которое передает значение соответствующему свойству и возвращает текущий объект Employee.

- `also`
Лямбда-выражение в функции also в качестве параметра it получает объект, 
для которого вызывается функция. 
Возвращаемым результатом функции фактически является передаваемый в функцию 
объект для которого выполняется функция.

```kotlin
inline fun T.also(block: (T) -> Unit): T
```
Эта функция аналогична функции `apply` за тем исключением, что внутри `also` объект, 
над которым выполняется блок кода, доступен через параметр it:
```kotlin
fun main() {

    val tom = Person("Tom", null)
    tom.also {
        if(it.email==null)
            it.email = "${it.name.lowercase()}@gmail.com"
    }
    println(tom.email) // tom@gmail.com
}
data class Person(val name: String, var email: String?)
```
[к оглавлению](#Kotlin)

## Коллекции

![icon][collections]
## Последовательности
```kotlin
val people = sequenceOf("Tom", "Sam", "Bob")    //тип Sequence<String>
println(people.joinToString())  // Tom, Sam, Bob
```
```kotlin
val employees = listOf("Tom", "Sam", "Bob") // объект List<String>
val people = employees.asSequence()         //тип Sequence<String>
println(people.joinToString())    // Tom, Sam, Bob
```
```kotlin
var number = 0
val numbers = generateSequence{ number += 2; number}
println(numbers.take(5).joinToString())    // получаем первые 5 элементов последовательности - 2, 4, 6, 8, 10

var number = 0
val numbers = generateSequence{ number += 2; if(number > 8) null else number}
println(numbers.joinToString())    // 2, 4, 6, 8
```
```kotlin
val numbers = sequence {
    yield(1)
    yield(4)
    yield(7)
}
println(numbers.joinToString())    // 1, 4, 7

val personal = sequence {
    val data = listOf("Alice", "Kate", "Ann")
    yieldAll(data)
}
println(personal.joinToString())    // Alice, Kate, Ann
```
Функция `yield()` фактически возвращает во вне некоторое значение, 
которое ей передается через параметр. 
То есть при первом обращении к функции sequence сработает вызов yield(1), 
который возвратит значение 1. При втором обращении сработает вызов yield(4), 
который возвратит 4. И при третьем обращении сработает вызов yield(7). 
Таким образом, последовательность будет содержать 3 элемента: 1, 4, 7.

#### Операции с коллекциями

- `all(predicate: (T) -> Boolean): Boolean`
возвращает true, если все элементы соответствуют предикату, 
который передается в функцию в качестве параметра
- `any(): Boolean`
возвращает true, если коллекция содержит хотя бы один элемент
Дополнительная версия возвращает true, если хотя бы один элемент соответствуют предикату, 
который передается в функцию в качестве параметра
`any(predicate: (T) -> Boolean): Boolean`
- `asSequence(): Sequence<T>`
создает из коллекции последовательность
- `average(): Double`
возвращает среднее значение для числовой коллекции типов Byte, Int, Short, Long, Float, Double
- `chunked(size: Int): List<List<T>>`
расщепляет коллекцию на список, который состоит из объектов List, 
параметр size устанавливает максимальное количество элементов в каждом из списков
Дополнительная версия в качестве второго параметра получает функцию преобразования, 
которая преобразует каждый список в элемент новой коллекции
`chunked(size: Int,transform: (List<T>) -> R): List<R>`
- `contains(element: T): Boolean`
возвращает true, если коллекция содержит элемент element
- `count(): Int`
возвращает количество элементов в коллекции
Дополнительная версия возвращает количество элементов, которые соответствуют предикату
`count(predicate: (T) -> Boolean): Int`
- `distinct(): List<T>`
возвращает новую коллекцию, которая содержит только уникальные элементы
- `distinctBy(selector: (T) -> K): List<T>`
возвращает новую коллекцию, которая содержит только 
уникальные элементы с учетом функции селектора, 
которая передается в качестве параметра
- `drop(n: Int): List<T>`
возвращает новую коллекцию, которая содержит все элементы за исключением первых n элементов
- `dropWhile(predicate: (T) -> Boolean): List<T>`
возвращает новую коллекцию, которая содержит все элементы за исключением первых элементов, 
которые соответствуют предикату
- `elementAt(index: Int): T`
возвращает элемент по индексу index. Если индекс выходит за пределы коллекции, 
то генерируется исключение типа IndexOutOfBoundsException
- `elementAtOrElse(index: Int, defaultValue: (Int) -> T): T`
возвращает элемент по индексу index. Если индекс выходит за пределы коллекции, 
то возвращается значение, устанавливаемое функцией из параметра defaultValue
- `elementAtOrNull(index: Int): T?`
возвращает элемент по индексу index. Если индекс выходит за пределы коллекции, 
то возвращается null
- `filter(predicate: (T) -> Boolean): List<T>`
возвращает новую коллекцию из элементов, которые соответствуют предикату
- `filterNot(predicate: (T) -> Boolean): List<T>`
возвращает новую коллекцию из элементов, которые НЕ соответствуют предикату
- `filterNotNull(): List<T>`
возвращает новую коллекцию из элементов, которые не равны null
- `find(predicate: (T) -> Boolean): T?`
возвращает первый элемент, который соответствует предикату. 
Если элемент не найден, то возвращается null
- `findLast(predicate: (T) -> Boolean): T?`
возвращает последний элемент, который соответствует предикату. 
Если элемент не найден, то возвращается null
- `first(): T`
возвращает первый элемент коллекции
Дополнительная версия возвращает первый элемент, которые соответствует предикату
- `first(predicate: (T) -> Boolean): T`
Если элемент не найден, то генерируется исключение типа NoSuchElementException
- `firstOrNull(): T?`
возвращает первый элемент коллекции
Дополнительная версия возвращает первый элемент, которые соответствует предикату
- `firstOrNull(predicate: (T) -> Boolean): T?`
Если элемент не найден, то возвращается null
- `flatMap(transform: (T) -> List<R>): List<R>`
преобразует коллекцию элементов типа T в коллекцию элементов типа R,
используя функцию преобразования, которая передается в качестве параметра
- `fold(initial: R, operation: (acc: R, T) -> R): R`
Возвращает значение, которое является результатом действия функции 
operation над каждым элементом коллекции. Первый параметр функции operation - 
результат работы функции над предыдущим элементом коллекции (при первом вызове - 
значение из параметра initial), в второй параметр - текущий элемент коллекции.
- `forEach(action: (T) -> Unit)`
Выполняет для каждого элемента коллекции действие action.
- `groupBy(keySelector: (T) -> K): Map<K, List<T>>`
Группирует элементы по ключу, который возвращается функцией keySelector. 
Результат функции карта Map, где ключ - собственно ключ элементов, 
а значение - список List из элементов, которые соответствуют этому ключу
Дополнительная версия принимает функцию преобразования элементов:
`groupBy(keySelector: (T) -> K, valueTransform: (T) -> V): Map<K, List<V>>`
- `indexOf(element: T): Int`
Возвращает индекс первого вхождения элемента element. Если элемент не найден, возвращается -1
- `indexOfFirst(predicate: (T) -> Boolean): Int`
Возвращает индекс первого элемента, который соответствует предикату. 
Если элемент не найден, возвращается -1
- `indexOfLast(predicate: (T) -> Boolean): Int`
Возвращает индекс последнего элемента, который соответствует предикату. 
Если элемент не найден, возвращается -1
- `intersect(other: Iterable): Set`
Возвращает все элементы текущей коллекции, которые есть в коллекции other
- `joinToString(): String`
Генерирует из коллекции строку
- `last(): T`
возвращает последний элемент коллекции
Дополнительная версия возвращает последний элемент, которые соответствует предикату
- `last(predicate: (T) -> Boolean): T`
Если элемент не найден, то генерируется исключение типа NoSuchElementException
- `lastOrNull(): T?`
возвращает последний элемент коллекции
Дополнительная версия возвращает последний элемент, которые соответствует предикату
- `lastOrNull(predicate: (T) -> Boolean): T?`
Если элемент не найден, то возвращается null
- `lastIndexOf(element: T): Int`
Возвращает последний индекс элемента element. Если элемент не найден, возвращается -1
- `map(transform: (T) -> R): List<R>`
Применяет к элементам коллекции функцию трансформации и 
возвращает новую коллекцию из новых элементов
- `mapIndexed(transform: (index: Int, T) -> R): List<R>`
Применяет к элементам коллекции и их индексам функцию трансформации и 
возвращает новую коллекцию из новых элементов
- `mapNotNull(transform: (T) -> R?): List<R>`
Применяет к элементам коллекции функцию трансформации и возвращает 
новую коллекцию из новых элементов, которые не равны null
- `maxOf(selector: (T) -> Double): Double`
Возвращает максимальное значение на основе селектора
- `maxOfOrNull(selector: (T) -> Double): Double?`
Возвращает максимальное значение на основе селектора. Если коллекцию пуста, возвращается null
- `maxOrNull(): Double?`
Возвращает максимальное значение. Если коллекцию пуста, возвращается null
- `minOf(selector: (T) -> Double): Double`
Возвращает минимальное значение на основе селектора
- `minOfOrNull(selector: (T) -> Double): Double?`
Возвращает минимальное значение на основе селектора. Если коллекцию пуста, возвращается null
- `minOrNull(): Double?`
Возвращает минимальное значение. Если коллекцию пуста, возвращается null
- `minus(element: T): List<T>`
Возвращает новую коллекцию, которая содержит все элементы текущей за исключением элемента element.
Имеет разновидности, которую позволяют исключить из коллекции наборы элементов:
```kotlin
minus(elements: Array<T>): List<T>
minus(elements: Iterable<T>): List<T>
minus(elements: Sequence<T>): List<T>
plus(element: T): List<T>
```
Возвращает новую коллекцию, которая содержит все элементы текущей за 
исключением плюс элемент element.
Имеет разновидности, которую позволяют включить в коллекцию наборы элементов:
```kotlin
plus(elements: Array<T>): List<T>
plus(elements: Iterable<T>): List<T>
plus(elements: Sequence<T>): List<T>
reduce(operation: (acc: S, T) -> S): S
```
Возвращает значение, которое является результатом действия функции operation 
над каждым элементом коллекции. Первый параметр функции operation - 
результат работы функции над предыдущим элементом коллекции, 
в второй параметр - текущий элемент коллекции.
- `shuffled(): List<T>`
Условно перемешивает коллекцию
- `sorted(): List<T>`
Сортирует коллекцию по возрастанию
- `sortedBy(selector: (T) -> R?): List<T>`
Сортирует коллекцию по возрастанию на основе селектора
- `sortedByDescending(selector: (T) -> R?): List<T>`
Сортирует коллекцию по убыванию на основе функции-селектора
- `sortedDescending(): List<T>`
Сортирует коллекцию по убыванию
- `sum(): Int`
Возвращает сумму элементов коллекции.
- `subtract(other: Iterable): Set`
Возвращает набор элементов, которые есть в текущей коллекции и отсутствуют в коллекции other.
- `sum(): Int`
Возвращает сумму элементов коллекции
- `sumOf(selector: (T) -> Int): Int`
Возвращает сумму элементов коллекции на основе функции-селектора
- `take(n: Int): List<T>`
Возвращает новую коллекцию, которая содержит n первых элементов текущей коллекции
- `takeWhile(predicate: (T) -> Boolean): List<T>`
Возвращает новую коллекцию, которая содержит n первых элементов текущей коллекции, 
соответствующих функции-предикату
- `toHashSet(): HashSet<T>`
Создает из коллекции объект HashSet
- `toList(): List<T>`
Создает из коллекции объект List
- `toMap(): Map<K, V>`
Создает из коллекции объект Map
- `toSet(): Set<T>`
Создает из коллекции объект Set
- `union(other: Iterable): Set`
Возвращает набор уникальных элементов, которые есть в текущей коллекции и коллекции other

[к оглавлению](#Kotlin)

## Последовательности

[к оглавлению](#Kotlin)

## Корутины

Неблокируемый скоуп
```kotlin
import kotlinx.coroutines.*

suspend fun main() = coroutineScope{

    launch{
        for(i in 1..5){
            println(i)
            delay(400L)
        }
    }

    println("Start")
    println("End")
}
//Start
//End
//1
//2
//3
//4
//5
```
Блокируемый скоуп
```kotlin
import kotlinx.coroutines.*
 
fun main() = runBlocking{
    launch{
        for(i in 0..5){
            delay(400L)
            println(i)
        }
    }
 
    println("Hello Coroutines")
}
```
#### Ожидание завершения корутины
```kotlin
import kotlinx.coroutines.*
 
suspend fun main() = coroutineScope{
 
    val job = launch{
        for(i in 1..5){
            println(i)
            delay(400L)
        }
    }
 
    println("Start")
    job.join() // ожидаем завершения корутины
    println("End")
}
//Start
//1
//2
//3
//4
//5
//End
```

#### Отложенное выполнение launch
```kotlin
import kotlinx.coroutines.*
 
suspend fun main() = coroutineScope{
 
    // корутина создана, но не запущена
    val job = launch(start = CoroutineStart.LAZY) {
        delay(200L)
        println("Coroutine has started")
    }
 
    delay(1000L)
    job.start() // запускаем корутину
    println("Other actions in main method")
}
//Program has finished
//Hello work!
```

#### Async
Наряду с `launch` в пакете `kotlinx.coroutines` есть еще один построитель корутин - 
функция `async`. Эта функция применяется, когда надо получить из корутины некоторый результат.
`async` запускает отдельную корутину, которая выполняется параллельно с остальными корутинами.

```kotlin
import kotlinx.coroutines.*
 
suspend fun main() = coroutineScope{
 
    async{ printHello()}
    println("Program has finished")
}
suspend fun printHello(){
    delay(500L)  // имитация продолжительной работы
    println("Hello work!")
}

//Program has finished
//Hello work!
```
```kotlin
import kotlinx.coroutines.*
 
suspend fun main() = coroutineScope{
 
    val message: Deferred<String> = async{ getMessage()}
    println("message: ${message.await()}")
    println("Program has finished")
}
suspend fun getMessage() : String{
    delay(500L)  // имитация продолжительной работы
    return "Hello"
}

//message: Hello
//Program has finished
```
#### Отложенный запуск async
```kotlin
import kotlinx.coroutines.*
 
suspend fun main() = coroutineScope{
 
    // корутина создана, но не запущена
    val sum = async(start = CoroutineStart.LAZY){ sum(1, 2)}
 
    delay(1000L)
    println("Actions after the coroutine creation")
    println("sum: ${sum.await()}")   // запуск и выполнение корутины
}
fun sum(a: Int, b: Int) : Int{
    println("Coroutine has started")
    return a + b
}
//Actions after the coroutine creation
//Coroutine has started
//sum: 3
```
Если необходимо, чтобы корутина еще до метода `await()` начала выполняться, 
то можно вызвать метод `start()`:
```kotlin
import kotlinx.coroutines.*
 
suspend fun main() = coroutineScope{
 
    // корутина создана, но не запущена
    val sum = async(start = CoroutineStart.LAZY){ sum(1, 2)}
 
    delay(1000L)
    println("Actions after the coroutine creation")
    sum.start()                      // запуск корутины
    println("sum: ${sum.await()}")   // получаем результат
}
fun sum(a: Int, b: Int) : Int{
    println("Coroutine has started")
    return a + b
}
```

#### Диспетчеры корутин

- `Dispatchers.Default`: применяется по умолчанию, если тип диспетчера не указан явным образом. 
Этот тип использует общий пул разделяемых фоновых потоков и подходит для вычислений, 
которые не работают с операциями ввода-вывода (операциями с файлами, базами данных, сетью) 
и которые требуют интенсивного потребления ресурсов центрального процессора.

- `Dispatchers.IO`: использует общий пул потоков, создаваемых по мере необходимости, 
и предназначен для выполнения операций ввода-вывода (например, 
операции с файлами или сетевыми запросами).

- `Dispatchers.Main`: применяется в графических приложениях, например, 
в приложениях Android или JavaFX.

- `Dispatchers.Unconfined`: корутина не закреплена четко за определенным потоком или пулом потоков. 
Она запускается в текущем потоке до первой приостановки. 
После возобновления работы корутина продолжает работу в одном из потоков, 
который сторого не фиксирован. 
Разработчики языка Kotlin в обычной ситуации не рекомендуют использовать данный тип.

- `newSingleThreadContext` и `newFixedThreadPoolContext`: 
позволяют вручную задать поток/пул для выполнения корутины
```kotlin
import kotlinx.coroutines.*

suspend fun main() = coroutineScope{

    launch(Dispatchers.Default) {   // явным образом определяем диспетчер Dispatcher.Default
        println("Корутина выполняется на потоке: ${Thread.currentThread().name}")
    }
    println("Функция main выполняется на потоке: ${Thread.currentThread().name}")
} 
```

#### Отмена выполнения корутин

Для отмены выполнения корутины у объекта Job может применяться метод `cancel()`:
```kotlin
import kotlinx.coroutines.*
 
suspend fun main() = coroutineScope{
 
    val downloader: Job = launch{
        println("Начинаем загрузку файлов")
        for(i in 1..5){
            println("Загружен файл $i")
            delay(500L)
        }
    }
    delay(800L)     // установим задержку, чтобы несколько файлов загрузились
    println("Надоело ждать, пока все файлы загрузятся. Прерву-ка я загрузку...")
    downloader.cancel()    // отменяем корутину
    downloader.join()      // ожидаем завершения корутины
    println("Работа программы завершена")
}
//Начинаем загрузку файлов
//Загружен файл 1
//Загружен файл 2
//Надоело ждать, пока все файлы загрузятся. Прерву-ка я загрузку...
//Работа программы завершена
```
```kotlin
import kotlinx.coroutines.*
 
suspend fun main() = coroutineScope{
 
    val downloader: Job = launch{
        println("Начинаем загрузку файлов")
        for(i in 1..5){
            println("Загружен файл $i")
            delay(500L)
        }
    }
    delay(800L)
    println("Надоело ждать, пока все файлы загрузятся. Прерву-ка я загрузку...")
    downloader.cancelAndJoin()    // отменяем корутину и ожидаем ее завершения
    println("Работа программы завершена")
}
```
#### Обработка исключения CancellationException
```kotlin
import kotlinx.coroutines.*
 
suspend fun main() = coroutineScope{
 
    val downloader: Job = launch{
        try {
            println("Начинаем загрузку файлов")
            for(i in 1..5){
                println("Загружен файл $i")
                delay(500L)
            }
        }
        catch (e: CancellationException ){
            println("Загрузка файлов прервана")
        }
        finally{
            println("Загрузка завершена")
        }
    }
    delay(800L)
    println("Надоело ждать. Прерву-ка я загрузку...")
    downloader.cancelAndJoin()    // отменяем корутину и ожидаем ее завершения
    println("Работа программы завершена")
}
//Начинаем загрузку файлов
//Загружен файл 1
//Загружен файл 2
//Надоело ждать. Прерву-ка я загрузку...
//Загрузка файлов прервана
//Загрузка завершена
//Работа программы завершена
```
#### Каналы

Каналы позволяют передавать потоки данных. В Kotlin каналы представлены интерфейсом `Channel`, 
у которого следует выделить два основных метода:

`abstract suspend fun send(element: E): Unit`

Отправляет объект element в канал

`abstract suspend fun receive(): E`

Получает данные из канала

Определим простейший канал, через который будем передавать числа типа Int:
```kotlin
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
 
suspend fun main() = coroutineScope{
 
    val channel = Channel<Int>()
    launch {
        for (n in 1..5) {
            // отправляем данные через канал
            channel.send(n)
        }
        channel.close()  // Закрытие канала. Необязательно, 
                        // тогда канал будет открыт для дальнейшего использования
    }
     
    // получаем данные из канала
    repeat(5) {
        val number = channel.receive()
        println(number)
    }
    println("End")
}
//1
//2
//3
//4
//5
//End
```
#### Паттерн producer-consumer
Рассмотренный выше пример по сути является распростаненным способом передачи 
данных от одной корутины к другой. И чтобы упростить написание подобного кода, 
Kotlin предоставляет ряд дополнительных функций. 
Так, функция `produce()` представляет построитель корутины, который создает корутину, 
в которой передаются данные в канал. Например, с помощью функции `produce()` 
мы можем определить новую функцию-корутину, 
которая будет отправлять определенные данные:
```kotlin
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
 
suspend fun main() = coroutineScope{
 
    val users = getUsers()
    users.consumeEach { user -> println(user) }
    println("End")
}
 
fun CoroutineScope.getUsers(): ReceiveChannel<String> = produce{
    val users = listOf("Tom", "Bob", "Sam")
    for (user in users) {
        send(user)
    }
}
//Tom
//Bob
//Sam
//End
```
Здесь определяется функция `getUsers()`. 
Причем она определяется как функция интерфейса `CoroutineScope`. 
Функция должна возвращать объект `ReceiveChannel`, 
типизированный типом передаваемых данных (в данном случае передаем значения типа String).
Функция `getUsers()` представляет корутину, создаваемую построителем корутин produce. 
В корутине опять же проходим по списку строк и с помощью функции send передаем в канал данные.
Для потребления данных из канала может применяться метод `consumeEach()` объекта ReceiveChannel,
который по сути заменяет цикл for.
[к оглавлению](#Kotlin)

[Заглавная](README.md)