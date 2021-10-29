[Заглавная](README.md)

# Design patterns
+ [Условные обозначения](design-patterns.md#Условные-обозначения)
+ [Хранитель (memento) и Цепочка обязанностей (chain of responsibility)](design-patterns.md#Хранитель-(memento)-и-Цепочка-обязанностей-(chain-of-responsibility))
+ [Наблюдатель (observer) и Команда (command)](design-patterns.md#Наблюдатель-(observer)-и-Команда-(command))
+ [Состояние (state) и Интерпретатор (interpreter)](design-patterns.md#Состояние-(state)-Интерпретатор-(interpreter))
+ [Стратегия (strategy) и Итератор (iterator)](design-patterns.md#Стратегия-(strategy)-и-Итератор-(iterator))
+ [Шаблонный метод (template method) и Посредник (mediator)](design-patterns.md#Шаблонный-метод-(template-method)-и-Посредник-(mediator))
+ [Посетитель (visitor) и Адаптер (adapter)](design-patterns.md#Посетитель-(visitor)-и-Адаптер-(adapter))
+ [Прокси (proxy) и Мост (bridge)](design-patterns.md#Прокси-(proxy)-и-Мост-(bridge))
+ [Абстрактная фабрика (abstract factory) и Компоновщик (composite)](design-patterns.md#Абстрактная-фабрика-(abstract-factory)-и-Компоновщик-(composite))
+ [Строитель (builder) и Декоратор (decorator)](design-patterns.md#Строитель-(builder)-и-Декоратор-(decorator))
+ [Фабричный метод (factory method) и Фасад (facade)](design-patterns.md#Фабричный-метод-(factory-method)-и-Фасад-(facade))
+ [Прототип (prototype) и Приспособленец (flyweight)](design-patterns.md#Прототип-(prototype)-и-Приспособленец-(flyweight))
+ [Одиночка (singleton)](design-patterns.md#Одиночка-(singleton))
+ [Антипаттерн](design-patterns.md#Антипаттерн)
+ [Dependency Injection](design-patterns.md#Dependency-Injection)

[к оглавлению](#Design-patterns)

## Условные обозначения
![icon][title]
[к оглавлению](#Design-patterns)
## Хранитель (memento) и Цепочка обязанностей (chain of responsibility)
![icon][memento_chainofresponsibility]
[к оглавлению](#Design-patterns)
## Наблюдатель (observer) и Команда (command)
![icon][observer_command]
[к оглавлению](#Design-patterns)
## Состояние (state) и Интерпретатор (interpreter)
![icon][state_interpreter]
[к оглавлению](#Design-patterns)
## Стратегия (strategy) и Итератор (iterator)
![icon][strategy_iterator]
[к оглавлению](#Design-patterns)
## Шаблонный метод (template method) и Посредник (mediator)
![icon][templatemethod_mediator]
[к оглавлению](#Design-patterns)
## Посетитель (visitor) и Адаптер (adapter)
![icon][visitor_adapter]
[к оглавлению](#Design-patterns)
## Прокси (proxy) и Мост (bridge)
![icon][proxy_bridge]
[к оглавлению](#Design-patterns)
## Абстрактная фабрика (abstract factory) и Компоновщик (composite)
![icon][abstractfactory_composite]
[к оглавлению](#Design-patterns)
## Строитель (builder) и Декоратор (decorator)
![icon][builder_decorator]
[к оглавлению](#Design-patterns)
## Фабричный метод (factory method) и Фасад (facade)
![icon][factorymethod_facade]
[к оглавлению](#Design-patterns)
## Прототип (prototype) и Приспособленец (flyweight)
![icon][prototype_flyweight]
[к оглавлению](#Design-patterns)
## Одиночка (singleton)
![icon][singleton]
[к оглавлению](#Design-patterns)

[title]:img/pattern/pattern_1.PNG
[memento_chainofresponsibility]:img/pattern/pattern_2.PNG
[observer_command]:img/pattern/pattern_3.PNG
[state_interpreter]:img/pattern/pattern_4.PNG
[strategy_iterator]:img/pattern/pattern_5.PNG
[templatemethod_mediator]:img/pattern/pattern_6.PNG
[visitor_adapter]:img/pattern/pattern_7.PNG
[proxy_bridge]:img/pattern/pattern_8.PNG
[abstractfactory_composite]:img/pattern/pattern_9.PNG
[builder_decorator]:img/pattern/pattern_10.PNG
[factorymethod_facade]:img/pattern/pattern_11.PNG
[prototype_flyweight]:img/pattern/pattern_12.PNG
[singleton]:img/pattern/pattern_13.PNG

## Антипаттерн

**Антипаттерн (anti-pattern)** — это распространённый подход к решению класса часто встречающихся проблем, являющийся неэффективным, рискованным или непродуктивным.

**Poltergeists (полтергейсты)** - это классы с ограниченной ответственностью и ролью в системе, чьё единственное предназначение — передавать информацию в другие классы. Их эффективный жизненный цикл непродолжителен. Полтергейсты нарушают стройность архитектуры программного обеспечения, создавая избыточные (лишние) абстракции, они чрезмерно запутанны, сложны для понимания и трудны в сопровождении. Обычно такие классы задумываются как классы-контроллеры, которые существуют только для вызова методов других классов, зачастую в предопределенной последовательности.

#### Признаки появления и последствия антипаттерна

-Избыточные межклассовые связи.
-Временные ассоциации.
-Классы без состояния (содержащие только методы и константы).
-Временные объекты и классы (с непродолжительным временем жизни).
-Классы с единственным методом, который предназначен только для создания или вызова других классов посредством временной ассоциации.
-Классы с именами методов в стиле «управления», такие как startProcess.

#### Типичные причины

-Отсутствие объектно-ориентированной архитектуры (архитектор не понимает объектно-ориентированной парадигмы).
-Неправильный выбор пути решения задачи.
-Предположения об архитектуре приложения на этапе анализа требований (до объектно-ориентированного анализа) могут также вести к проблемам на подобии этого антипаттерна.

1) **Внесенная сложность (Introduced complexity):** Необязательная сложность дизайна. Вместо одного простого класса выстраивается целая иерархия интерфейсов и классов. Типичный пример «Интерфейс - Абстрактный класс - Единственный класс реализующий интерфейс на основе абстрактного».

2) **Инверсия абстракции (Abstraction inversion):** Сокрытие части функциональности от внешнего использования, в надежде на то, что никто не будет его использовать.

3) **Неопределённая точка зрения (Ambiguous viewpoint):** Представление модели без спецификации её точки рассмотрения.

4) **Большой комок грязи (Big ball of mud):** Система с нераспознаваемой структурой.

5) **Божественный объект (God object):** Концентрация слишком большого количества функций в одной части системы (классе).

6) **Затычка на ввод данных (Input kludge):** Забывчивость в спецификации и выполнении поддержки возможного неверного ввода.

7) **Раздувание интерфейса (Interface bloat):** Разработка интерфейса очень мощным и очень сложным для реализации.

8) **Волшебная кнопка (Magic pushbutton):** Выполнение результатов действий пользователя в виде неподходящего (недостаточно абстрактного) интерфейса. Например, написание прикладной логики в обработчиках нажатий на кнопку.

9) **Перестыковка (Re-Coupling):** Процесс внедрения ненужной зависимости.

10) **Дымоход (Stovepipe System):** Редко поддерживаемая сборка плохо связанных компонентов.

11) **Состояние гонки (Race hazard):** непредвидение возможности наступления событий в порядке, отличном от ожидаемого.

12) **Членовредительство (Mutilation):** Излишнее «затачивание» объекта под определенную очень узкую задачу таким образом, что он не способен будет работать с никакими иными, пусть и очень схожими задачами.

13) **Сохранение или смерть (Save or die):** Сохранение изменений лишь при завершении приложения.
[к оглавлению](#Design-patterns)

## Что такое Dependency Injection?
**Dependency Injection (внедрение зависимости)** - это набор паттернов и принципов разработки програмного обеспечения, которые позволяют писать слабосвязный код. В полном соответствии с принципом единой обязанности объект отдаёт заботу о построении требуемых ему зависимостей внешнему, специально предназначенному для этого общему механизму.

[к оглавлению](#Design-patterns)

[Заглавная](README.md)