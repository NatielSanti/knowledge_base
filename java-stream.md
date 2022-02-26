[Заглавная](README.md)

# Java Stream API
+ [Способы создания стримов](java-stream.md#Способы-создания-стримов)
+ [Краткое описание конвейерных методов работы со стримами](java-stream.md#Краткое-описание-конвейерных-методов-работы-со-стримами)
+ [Описание терминальных методов работы со стримами](java-stream.md#Описание-терминальных-методов-работы-со-стримами)
+ [Краткое описание дополнительных методов у числовых стримов](java-stream.md#Краткое-описание-дополнительных-методов-у-числовых-стримов)

[к оглавлению](#Java-Stream-API)

##Способы создания стримов
```java
// Создание стрима из значений
Stream<String> streamFromValues = Stream.of("a1", "a2", "a3");

// Создание стрима из массива
String[] array = {"a1","a2","a3"};
Stream<String> streamFromArrays = Arrays.stream(array);
Stream<String> streamFromArrays1 = Stream.of(array);

// Создание стрима из файла (каждая запись в файле будет отдельной строкой в стриме)
File file = new File("1.tmp");
file.deleteOnExit();
PrintWriter out = new PrintWriter(file);
out.println("a1");
out.println("a2");
out.println("a3");
out.close();

Stream<String> streamFromFiles = Files.lines(Paths.get(file.getAbsolutePath()));

// Создание стрима из коллекции
Collection<String> collection = Arrays.asList("a1", "a2", "a3");
Stream<String> streamFromCollection = collection.stream();

// Создание числового стрима из строки
IntStream streamFromString = "123".chars();
streamFromString.forEach((e)->System.out.print(e + " , ")); // напечатает streamFromString = 49 , 50 , 51 ,

// С помощью Stream.builder
Stream.Builder<String> builder = Stream.builder();
Stream<String> streamFromBuilder = builder.add("a1").add("a2").add("a3").build();

// Создание бесконечных стримов
// С помощью Stream.iterate
Stream<Integer> streamFromIterate = Stream.iterate(1, n -> n + 2);

// С помощью Stream.generate
Stream<String> streamFromGenerate = Stream.generate(() -> "a1");

// Создать пустой стрим
Stream<String> streamEmpty = Stream.empty();

// Создать параллельный стрим из коллекции
Stream<String> parallelStream = collection.parallelStream();
```
[к оглавлению](#Java-Stream-API)

##Краткое описание конвейерных методов работы со стримами
```java
// filter	
// Отфильтровывает записи, возвращает только записи, соответствующие условию	
collection.stream().filter(«a1»::equals).count()

// skip	
// Позволяет пропустить N первых элементов	
collection.stream().skip(collection.size() — 1).findFirst().orElse(«1»)

// distinct	
// Возвращает стрим без дубликатов (для метода equals)	
collection.stream().distinct().collect(Collectors.toList())

// map	
// Преобразует каждый элемент стрима	
collection.stream().map((s) -> s + "_1").collect(Collectors.toList())

// peek	
// Возвращает тот же стрим, но применяет функцию к каждому элементу стрима	
collection.stream().map(String::toUpperCase).peek((e) -> System.out.print("," + e)).collect(Collectors.toList())

// limit	
// Позволяет ограничить выборку определенным количеством первых элементов	
collection.stream().limit(2).collect(Collectors.toList())

// sorted	
// Позволяет сортировать значения либо в натуральном порядке, либо задавая Comparator	
collection.stream().sorted().collect(Collectors.toList())

// mapToInt, mapToDouble, mapToLong	
// Аналог map, но возвращает числовой стрим (то есть стрим из числовых примитивов)	
collection.stream().mapToInt((s) -> Integer.parseInt(s)).toArray()

// flatMap, flatMapToInt, flatMapToDouble, flatMapToLong	
// Похоже на map, но может создавать из одного элемента несколько	
collection.stream().flatMap((p) -> Arrays.asList(p.split(",")).stream()).toArray(String[]::new)
```
[к оглавлению](#Java-Stream-API)

##Описание терминальных методов работы со стримами
```java
// findFirst	
// Возвращает первый элемент из стрима (возвращает Optional)	
collection.stream().findFirst().orElse(«1»)

// findAny	
// Возвращает любой подходящий элемент из стрима (возвращает Optional)	
collection.stream().findAny().orElse(«1»)

// collect	
// Представление результатов в виде коллекций и других структур данных	
collection.stream().filter((s) -> s.contains(«1»)).collect(Collectors.toList())

// count	
// Возвращает количество элементов в стриме	
collection.stream().filter(«a1»::equals).count()

// anyMatch	
// Возвращает true, если условие выполняется хотя бы для одного элемента	
collection.stream().anyMatch(«a1»::equals)

// noneMatch	
// Возвращает true, если условие не выполняется ни для одного элемента	
collection.stream().noneMatch(«a8»::equals)

// allMatch	
// Возвращает true, если условие выполняется для всех элементов	
collection.stream().allMatch((s) -> s.contains(«1»))

// min	
// Возвращает минимальный элемент, в качестве условия использует компаратор	
collection.stream().min(String::compareTo).get()

// max	
// Возвращает максимальный элемент, в качестве условия использует компаратор	
collection.stream().max(String::compareTo).get()

// forEach	
// Применяет функцию к каждому объекту стрима, порядок при параллельном выполнении не гарантируется	
set.stream().forEach((p) -> p.append("_1"));

// forEachOrdered	
// Применяет функцию к каждому объекту стрима, сохранение порядка элементов гарантирует	
list.stream().forEachOrdered((p) -> p.append("_new"));

// toArray	
// Возвращает массив значений стрима	
collection.stream().map(String::toUpperCase).toArray(String[]::new);

// reduce	
// Позволяет выполнять агрегатные функции на всей коллекцией и возвращать один результат	
collection.stream().reduce((s1, s2) -> s1 + s2).orElse(0)
```
[к оглавлению](#Java-Stream-API)

##Краткое описание дополнительных методов у числовых стримов
```java
// sum	
// Возвращает сумму всех чисел	
collection.stream().mapToInt((s) -> Integer.parseInt(s)).sum()

// average	
// Возвращает среднее арифметическое всех чисел	
collection.stream().mapToInt((s) -> Integer.parseInt(s)).average()

// mapToObj	
// Преобразует числовой стрим обратно в объектный	
intStream.mapToObj((id) -> new Key(id)).toArray()
```

##Несколько других полезных методов стримов
```java
// isParallel	Узнать является ли стрим параллельным
// parallel	Вернуть параллельный стрим, если стрим уже параллельный, то может вернуть самого себя
// sequential	Вернуть последовательный стрим, если стрим уже последовательный, то может вернуть самого себя
```

[к оглавлению](#Java-Stream-API)

[Заглавная](README.md)