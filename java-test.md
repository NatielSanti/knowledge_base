[Заглавная](README.md)

# Java Test

+ [Виды тестирования](java-test.md#Виды-тестирования)
+ [Пирамида тестирования](java-test.md#Пирамида-тестирования)
+ [Зачем нужно юнит-тестирование?](java-test.md#Зачем-нужно-юнит-тестирование?)
+ [TDD](java-test.md#TDD)
+ [Mockito](java-test.md#Mockito)
+ [MockMvc](java-test.md#MockMvc)
+ [Признаки плохого юнит-теста](java-test.md#Признаки-плохого-юнит-теста)
+ [FIRST](java-test.md#FIRST)

[unit-test-1]:img/unit-test/unit-test-bad-1.jpeg
[unit-test-2]:img/unit-test/unit-test-bad-2.jpeg
[unit-test-3]:img/unit-test/unit-test-bad-3.jpeg
[unit-test-4]:img/unit-test/unit-test-bad-4.jpeg
[unit-test-5]:img/unit-test/unit-test-bad-5.jpeg
[unit-test-6]:img/unit-test/unit-test-bad-6.jpeg
[unit-test-7]:img/unit-test/unit-test-bad-7.jpeg
[test-pyramid]:img/unit-test/test-pyramid.PNG

[к оглавлению](#Java-Test)

## Виды тестирования

Для начала нужно понять ЧТО нужно тестировать
- *Функциональное тестирование* – как работают классы, как реализован функционал, 
как выполняются все функции приложения, правильно ли работает API
- *Нефункциональное тестирование* – нагрузочное тестирование, тестирование UX, тестирование установки

- *Black-box|white-box*
- *Регрессионное тестирование* – не упало ли то, что написали раньше
- *Системное тестирование* – тестирование системы целиком
- *Юнит-тетсирование* – тестирование отдельного модуля.

### О Unit-тестировании

- «Модуль» понятие растяжимое, но обычно это ровно один класс.
- Обычно юнит-тестирование осуществляется автоматически с помощью специальных авто-тестов.
- Обычно они запускаются во время сборки приложения. 
- И используются специальные фреймворки – `JUnit`,  кстати на этих фреймворках можно писать не только unit-тесты, 
но и интеграционные, и нагрузочные и т.д.

[к оглавлению](#Java-Test)

## Пирамида тестирования

![icon][test-pyramid]

[к оглавлению](#Java-Test)

## Зачем нужно юнит-тестирование?

- Ошибки выявляются раньше.
- Ошибки не повторяются (зафиксированы тестами)
- Ошибок выявляется намного больше
- Руками Вы протестируете один случай, а юнит тестами – сразу все
- В конце-концов, это требуется во всех вакансиях

Кто запускает тестовый фреймворк
- `surefire-maven-plugin` – специальный плагин для запуска тестов
- `Runner` тестов в `IDE` (`IntelliJ IDEA`)

Тестовый фреймворк – синтаксис тестов, базовые matcher-ы
- `Junit 4, 5`
- `TestNG`
- `AssertJ`
- `hamcrest`

Матчеры (assertEquals, assertThat)
- На самом деле из 2 – `JUnit4` и `JUnit5`
- `JUnit 4` – известный полноценный фреймворк
- `JUnit 5` – огромная платформа для тестирования (в терминах 5-ой называется `junit-platform`)
- `Junit 5` Содержит свои плюшки (в терминах 5-ой называется `junit-jupiter`)
- На `JUnit 5` можно запускать `Junit4` (в терминах 5-ой называется `junit-vintage`)

[к оглавлению](#Java-Test)

## TDD

- TDD – методология разработки
- Сначала пишется падающий тест
- Потому он исправляется (причём пишется только код, который исправляет тест)
- Потом рефакторинг
- TDD – не самоцель
- Написать огромный тест, а потом класс – это не TDD
- Это не просто техника test-first, это ещё и море техник.
- Код, написанный в бессознательном состоянии, но test-first, лучше кода написанного без тестов
- Крайне стабильное ПО.

[к оглавлению](#Java-Test)

## Mockito

- Часто бывает, что класс зависит от другого
- С IoC контейнером и классами написанным таким образом – всё сплошь и рядом
- Если создавать зависимый класс – то это интеграционный тест
- А если класс исользует БД – то прям совсем мрак.
- Для этого используют моки (стабы) – специальные классы реаизующие интерфейс
- Моки можно писать самостоятельно
- Но обычно поручают фреймворку
- Самый популярный фреймворк - Mockito

Данный пример создал некорректно, так как предполагается, что созданный mock будет передан в другой сервис и 
там использоваться.
```gradle
dependencies {
    implementation 'junit:junit:4.12'
    implementation 'org.springframework.boot:spring-boot-starter-test:2.2.6.RELEASE'
    testImplementation group: 'org.mockito', name: 'mockito-core', version: '2.24.0'
    compileOnly group: 'org.projectlombok', name: 'lombok', version: '1.18.20'
}
```
Test Service class
```java
public interface DataService {

    void saveData(List<String> dataToSave);

    String getDataById(String id);

    String getDataById(String id, Supplier<String> calculateIfAbsent);

    List<String> getData();

    List<String> getDataListByIds(List<String> idList);

    List<String> getDataByRequest(DataSearchRequest request);
}

@AllArgsConstructor
@Getter
class DataSearchRequest {

    String id;

    Date updatedBefore;

    int length;
}
```
В случае, когда необходимо создать экземпляр enum либо финализированного класса, то необходимо в 
```test/resources/mockito-extensions/org.mockito.plugins.MockMaker```добавить строку```mock-maker-inline```
Mock методы возвращают null для объектов и 0 для примитивов и пустой лист для коллекций.
Поведение spy-объектов по умолчанию идентично поведению обычного экземпляра класса, однако они дают мне те же 
возможности, что и mock-объекты: позволяют переопределять их поведение и наблюдать за их использованием 
(см. следующие разделы). Важный момент: spy — не обёртка вокруг того экземпляра, на основе которого он создан! 
Поэтому вызов метода spy на состояние изначального экземпляра не повлияет.
```java
// Создание mock.
DataService dataServiceMock = Mockito.mock(DataService.class);
// Создание spy.
DataService dataServiceSpy = Mockito.spy(DataService.class);
// or
DataService dataService = new DataService();
dataServiceSpy = Mockito.spy(dataService);
```
Далее мы задаём поведение мока, при помощи конструкции ```Mockito.when```, что возвращает объект типа 
```OngoingStubbing``` и ожидает вызова ```Mockito.then...```
```java
List<String> data = new ArrayList<>();
data.add("dataItem");
Mockito.when(dataService.getAllData()).thenReturn(data);
```
Альтернативный способ. В первой реализации при задании поведения метода (в данном случае ```getAllData()```) 
сначала выполняется вызов ещё не переопределённой его версии, и только потом, в недрах Mockito, происходит 
переопределение. Во второй же такого вызова не происходит — методу ```Stubber.when``` передаётся непосредственно 
mock, а уже у возвращённого этим методом объекта того же типа, но другой природы совершается вызов 
переопределяемого метода. Эта разница всё и определяет. Связывание через ```Mockito.do...``` никак не контролирует 
на стадии компиляции то, какой переопределяемый метод я вызову и совместим ли он по типу с заданным возвращаемым 
значением.
```java
List<String> data = new ArrayList<>();
data.add("dataItem");
Mockito.doReturn(data).when(dataService).getData();
```
При работе с ```void``` методами можно использовать ```Mockito.doThrow```, ```Mockito.doAnswer``` и достаточно 
редко пригождающийся ```Mockito.doNothing```.

Передача параметров в моки.
```java
Mockito.when(dataService.getDataItemById(any()))
       .thenReturn("dataItem");
// or
Mockito.when(dataService.getDataItemById("idValue"))
       .thenReturn("dataItem");
// or
Mockito.when(dataService.getDataItemById(Mockito.eq("idValue")))
       .thenReturn("dataItem");

Mockito.when(dataService.getDataById(
             Mockito.argThat(arg -> arg == null || arg.length() > 5)))
       .thenReturn("dataItem");

// Можно пробросить ошибку привызове метода.
Mockito.when(dataService.getDataById("invalidId"))
       .thenThrow(new IllegalArgumentException());
//or
Mockito.when(dataService.getDataById("invalidId"))
       .thenThrow(IllegalArgumentException.class);

// Первый вызов вернёт valueA1, второй valueA2, а далее будет пробрасываться исключение.
Mockito.when(dataService.getDataById("a"))
       .thenReturn("valueA1", "valueA2")
       .thenThrow(IllegalArgumentException.class);  
       
// реагируем по-разному на разные входные параметры.
Mockito.when(dataService.getDataByIds(Mockito.any()))
       .thenAnswer(invocation -> invocation
                .<List<String>>getArgument(0).stream()
                .map(id -> {
                    switch (id) {
                        case "a":
                            return "dataItemA";
                        case "b":
                            return "dataItemB";
                        default:
                            return null;
                    }
                })
                .collect(Collectors.toList())); 
```
Можем проверить количество вызовов метода, если метод не был вызван или вызван более нужногоколичества раз, 
то тест упадёт.
Вспомогательные методы: Mockito.never - не был вызван, Mockito.atLeast, Mockito.atLeastOnce, Mockito.atMost, 
Mockito.only - мок не был вызван вообще ни разу.
```java
Mockito.verify(dataService, Mockito.times(1))
       .getDataById(Mockito.any());
// Вставляет таймаут
Mockito.verify(dataService, Mockito.after(1000).times(1))
       .getDataById(Mockito.any());
```
Последовательность проверок.
```java
dataService.getDataById("a");
dataService.getDataById("b");
Mockito.verify(dataService, Mockito.times(2)).getDataById(Mockito.any());
Mockito.verify(dataService, Mockito.times(1)).getDataById("a");
Mockito.verify(dataService, Mockito.never()).getDataById("c");

dataService.getDataById("c");
Mockito.verify(dataService, Mockito.times(1)).getDataById("c");
Mockito.verifyNoMoreInteractions(dataService);
```
Проверка количества вызовов. Такой тест пройдёт только в том случае, если до приведённого фрагмента был дважды 
вызван метод saveData, а потом единожды — getData. Обратите внимание, что объект InOrder можно сгенерировать и до, 
и после подлежащих учёту вызовов — он в любом случае сработает.
```java
InOrder inOrder = Mockito.inOrder(dataService);
inOrder.verify(dataService, times(2)).saveData(any());
inOrder.verify(dataService).getData();
```
Сессии моков, необходимы, чтобы для каждого теста не создавать отдельный мок, а просто перед каждым тестовым методом 
сбрасывать его сессию при водить в "чистое" состояние.
```java
@Mock
DataService dataService;

MockitoSession session;

@Before
public void beforeMethod() {
    session = Mockito.mockitoSession()
            .initMocks(this)
            .startMocking();
}

@Test
public void testMethod() {
    // some code using the dataService field
}

@After
public void afterMethod() {
    session.finishMocking();
}
```
Живой пример.
```java
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class QuadraticEquationService {

    public double[] method(double a, double b, double c) throws IOException {
        double disc = b*b - 4*a*c;
        if (disc < 0)
            throw new IOException("Some error");

        double[] result = new double[2];
        result[0] = ((b * -1) + Math.sqrt(disc)) / (2 * a);
        result[1] = ((b * -1) - Math.sqrt(disc)) / (2 * a);

        return result;
    }
}
```
Тест
```java
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class QuadraticEquationServiceTest {

    @Mock
    QuadraticEquationService qe;

    @Before
    public void setUp() throws Exception {
        Mockito.when(qe.method(Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyDouble()))
                .thenReturn(new double[]{3,2});
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void method() throws IOException {
        double[] result = qe.method(1, -6, -7);
        Assert.assertEquals(result[0], 3, 0);
    }
}
```

[к оглавлению](#Java-Test)

## MockMvc
Необходимо подключить MVC Spring и сформировать WebConfig

```java
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
public class RoleControllerIntegrationTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    private static final String CONTENT_TYPE = "application/text;charset=ISO-8859-1";

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void givenEmployeeNameJohnWhenInvokeRoleThenReturnAdmin() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/role/John"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(CONTENT_TYPE))
                .andExpect(MockMvcResultMatchers.content().string("ADMIN"));
    }
}
///////////
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.baeldung.controller.parameterized"})
public class WebConfig {

    @Autowired
    private ServletContext ctx;
}
```

[к оглавлению](#Java-Test)

## Признаки плохого юнит-теста

### Условия в тестах
#### Неправильно
![icon][unit-test-1]
#### Правильно
![icon][unit-test-2]
### Циклы в тестах
#### Неправильно
![icon][unit-test-3]
#### Правильно
![icon][unit-test-4]
### Толстые и сложные тесты
#### Неправильно
![icon][unit-test-5]
#### Правильно
![icon][unit-test-6]
![icon][unit-test-7]

[к оглавлению](#Java-Test)

## FIRST

- *Быстрота (Fast).* Тесты должны выполняться быстро. Все мы знаем, что разработчики люди, 
а люди ленивы, поскольку эти выражения являются “транзитивными”, 
то можно сделать вывод, что люди тоже ленивы. 
А ленивый человек не захочет запускать тесты при каждом изменении кода, 
если они будут долго выполняться.

- *Независимость (Independent).* Результаты выполнения одного теста не должны быть входными 
данными для другого. Все тесты должны выполняться в произвольном порядке, 
поскольку в противном случае при сбое одного теста каскадно “накроется” 
выполнение целой группы тестов.

- *Повторяемость (Repeatable).* Тесты должны давать одинаковые результаты не зависимо от 
среды выполнения. Результаты не должны зависеть от того, в
ыполняются ли они на вашем локальном компьютере, на компьютере соседа или же на билд-сервере. 
В противном случае найти концы с концами будет весьма не просто.

- *Очевидность (Self-Validating).* Результатом выполнения теста должно быть булево значение. 
Тест либо прошел, либо не прошел и это должно быть легко понятно любому разработчику.  
Не нужно заставлять людей читать логи только для того, чтобы определить прошел тест успешно или нет.

- *Своевременность (Timely).* Тесты должны создаваться своевременно. 
Несвоевременность написания тестов является главной причиной того, 
что они откладываются на потом, а это “потом” так никогда и не наступает. 
Даже если вы и не будете писать тесты перед кодом (
хотя этот вариант уже доказал свою жизнеспособность) их нужно писать как минимум параллельно с кодом.

[Заглавная](README.md)