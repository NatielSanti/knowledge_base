[Заглавная](README.md)

# Java
+ [Mockito](java-mock.md#Mockito)
+ [MockMvc](java-mock.md#MockMvc)

## Mockito

Данный пример создал некорректно, так как предполагается, что созданный mock будет передан в другой сервис и там использоваться.
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
Поведение spy-объектов по умолчанию идентично поведению обычного экземпляра класса, однако они дают мне те же возможности, что и mock-объекты: позволяют переопределять их поведение и наблюдать за их использованием (см. следующие разделы). Важный момент: spy — не обёртка вокруг того экземпляра, на основе которого он создан! Поэтому вызов метода spy на состояние изначального экземпляра не повлияет.
```java
// Создание mock.
DataService dataServiceMock = Mockito.mock(DataService.class);
// Создание spy.
DataService dataServiceSpy = Mockito.spy(DataService.class);
// or
DataService dataService = new DataService();
dataServiceSpy = Mockito.spy(dataService);
```
Далее мы задаём поведение мока, при помощи конструкции ```Mockito.when```, что возвращает объект типа ```OngoingStubbing``` и ожидает вызова ```Mockito.then...```
```java
List<String> data = new ArrayList<>();
data.add("dataItem");
Mockito.when(dataService.getAllData()).thenReturn(data);
```
Альтернативный способ. В первой реализации при задании поведения метода (в данном случае ```getAllData()```) сначала выполняется вызов ещё не переопределённой его версии, и только потом, в недрах Mockito, происходит переопределение. Во второй же такого вызова не происходит — методу ```Stubber.when``` передаётся непосредственно mock, а уже у возвращённого этим методом объекта того же типа, но другой природы совершается вызов переопределяемого метода. Эта разница всё и определяет. Связывание через ```Mockito.do...``` никак не контролирует на стадии компиляции то, какой переопределяемый метод я вызову и совместим ли он по типу с заданным возвращаемым значением.
```java
List<String> data = new ArrayList<>();
data.add("dataItem");
Mockito.doReturn(data).when(dataService).getData();
```
При работе с ```void``` методами можно использовать ```Mockito.doThrow```, ```Mockito.doAnswer``` и достаточно редко пригождающийся ```Mockito.doNothing```.

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
Можем проверить количество вызовов метода, если метод не был вызван или вызван более нужногоколичества раз, то тест упадёт.
Вспомогательные методы: Mockito.never - не был вызван, Mockito.atLeast, Mockito.atLeastOnce, Mockito.atMost, Mockito.only - мок не был вызван вообще ни разу.
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
Проверка количества вызовов. Такой тест пройдёт только в том случае, если до приведённого фрагмента был дважды вызван метод saveData, а потом единожды — getData. Обратите внимание, что объект InOrder можно сгенерировать и до, и после подлежащих учёту вызовов — он в любом случае сработает.
```java
InOrder inOrder = Mockito.inOrder(dataService);
inOrder.verify(dataService, times(2)).saveData(any());
inOrder.verify(dataService).getData();
```
Сессии моков, необходимы, чтобы для каждого теста не создавать отдельный мок, а просто перед каждым тестовым методом сбрасывать его сессию при водить в "чистое" состояние.
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

[к оглавлению](#Java)

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

[к оглавлению](#Java)

[Заглавная](README.md)