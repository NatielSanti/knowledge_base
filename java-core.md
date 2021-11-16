[Заглавная](README.md)

# Java Core
+ [Отличие лямбды от анонимного класса](java-stream.md#Отличие-лямбды-от-анонимного-класса)

[к оглавлению](#Java-Core)

## Отличие лямбды от анонимного класса
```java
// Анонимный класс. Каждый раз при создании анонимного класса будет создан новый объект.
Comparator<Integer> comparable = new Comparator<Integer>() {
    @Override
    public int compare(Integer o1, Integer o2) {
        return Integer.compare(o1, o2);
    }
};

// Лямбда. Один раз будет скомпилирована лямбда и будет возвращаться.
Comparator<Integer> lambdaClass = (Integer o1, Integer o2) -> {
    return Integer.compare(o1, o2);
};
```

[к оглавлению](#Java-Core)

[Заглавная](README.md)