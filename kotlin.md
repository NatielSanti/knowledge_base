[Заглавная](README.md)

# Kotlin

+ [Основы](#Core)
+ [Функции](#Функции)
+ [Классы и объекты](#Классы-и-объекты)
+ [ООП](#ООП)
+ [Обобщения-Generics](#Обобщения-Generics)
+ [Дополнительные возможности ООП](#Дополнительные-возможности-ООП)

[к оглавлению](#Kotlin)

[//]: # ([docker_1]:img/microservices/docker_1.JPG)
[//]: # (![icon][docker_1])

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
[к оглавлению](#Kotlin)

[Заглавная](README.md)