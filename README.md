# R-Tree Demonstration Project 🌳

[![Java Version](https://img.shields.io/badge/Java-8+-blue.svg)](https://openjdk.java.net/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)
[![GitHub Repo](https://img.shields.io/badge/GitHub-Repository-black.svg)](https://github.com/xcxchezz/rtree-test)

## 📋 О репозитории

Этот репозиторий содержит демонстрацию работы с R-Tree - структурой данных для эффективного пространственного индексирования в двумерном пространстве. Проект включает как оригинальную библиотеку R-Tree (скомпилированную для Java 17), так и упрощенную реализацию, совместимую с Java 8.

## 🚀 Быстрый старт

```bash
# Клонирование репозитория
git clone https://github.com/xcxchezz/rtree-test.git
cd rtree-test

# Компиляция и запуск демонстрации
javac -cp "." -source 1.8 -target 1.8 -encoding UTF-8 src/main/java/rtree/*.java -d target/classes
java -cp target/classes rtree.MainDemo
```

## 📋 Системные требования

- **Java**: 8 или выше
- **Операционная система**: Windows, Linux, macOS
- **Память**: Минимум 64 MB RAM

## Структура проекта

```
├── lib/rtree-lib-1.0-SNAPSHOT.jar     # Оригинальная библиотека R-Tree (Java 17)
├── src/main/java/rtree/
│   ├── MainDemo.java                 # Основная демонстрационная программа (упрощенная реализация)
│   ├── SimpleRTree.java              # Упрощенная реализация R-Tree
│   ├── SimpleRectangle.java          # Класс прямоугольника
│   ├── SimpleEntry.java              # Класс записи
│   ├── SimpleNode.java               # Класс узла дерева
│   └── ReflectionDemo.java           # Демонстрация с использованием рефлексии
├── pom.xml                           # Конфигурация Maven
├── README.md                         # Эта документация
└── README_RTree_Demo.md             # Детальная документация по демонстрации
```

## Архитектура R-Tree

### Классы оригинальной библиотеки (rtree-lib-1.0-SNAPSHOT.jar)

#### 1. `rtree.RTree<T>`
Основной класс дерева R-Tree для пространственного индексирования.

**Конструктор:**
- `RTree(int maxEntries)` - создает дерево с максимальной вместимостью узла

**Методы:**
- `void insert(Rectangle rect, T value)` - вставляет элемент
- `List<T> search(Rectangle query)` - ищет элементы в заданной области

### Классы упрощенной реализации (Java 8 совместимые)

#### 1. `rtree.SimpleRTree<T>`
Упрощенная версия R-Tree для демонстрации основных принципов.

**Конструктор:**
- `SimpleRTree(int maxEntries)` - создает дерево с максимальной вместимостью узла

**Методы:**
- `void insert(SimpleRectangle rect, T value)` - вставляет элемент
- `List<T> search(SimpleRectangle query)` - ищет элементы в заданной области
- `void printTree()` - выводит структуру дерева

#### 2. `rtree.Rectangle` (оригинальная) / `rtree.SimpleRectangle` (упрощенная)
Представляет прямоугольную область в 2D пространстве.

**Поля:**
- `double x1, y1` - координаты левого нижнего угла
- `double x2, y2` - координаты правого верхнего угла

**Методы:**
- `boolean intersects(Rectangle other)` - проверка пересечения
- `boolean contains(Rectangle other)` - проверка вложенности
- `double area()` - вычисление площади
- `static Rectangle combine(Rectangle a, Rectangle b)` - объединение прямоугольников

#### 3. `rtree.Entry<T>` (оригинальная) / `rtree.SimpleEntry<T>` (упрощенная)
Элемент дерева, содержащий ограничивающий прямоугольник и значение.

**Поля:**
- `Rectangle mbr` - ограничивающий прямоугольник
- `T value` - хранимое значение

#### 4. `rtree.Node<T>` (оригинальная) / `rtree.SimpleNode<T>` (упрощенная)
Внутренний узел дерева (используется реализацией).

**Поля:**
- `boolean leaf` - является ли листом
- `List<Entry<T>> entries` - элементы листового узла
- `List<Node<T>> children` - дочерние узлы
- `Rectangle mbr` - ограничивающий прямоугольник узла

**Методы:**
- `void updateMBR()` - пересчет ограничивающего прямоугольника

## Возможности демонстрации

### 1. Базовые операции
- Вставка элементов с прямоугольными областями
- Поиск элементов в заданной области
- Демонстрация пересечения прямоугольников

### 2. Пространственные запросы
- Поиск в различных областях города
- Демонстрация различных типов запросов
- Обработка пустых результатов

### 3. Реальный пример использования
- Система управления недвижимостью
- Географические координаты объектов
- Поиск по районам

### 4. Аспекты производительности
- Автоматическое разделение узлов
- Балансировка дерева
- Эффективность поиска

## Как запустить

### Системные требования
- Java 8+ (текущая демонстрация совместима с Java 8)
- Maven (опционально)

### Компиляция и запуск упрощенной реализации

```bash
# Переход в директорию проекта
cd C:\Users\opapc\IdeaProjects\test11

# Компиляция всех классов
javac -cp "." -source 1.8 -target 1.8 -encoding UTF-8 src/main/java/rtree/*.java -d target/classes

# Запуск основной демонстрации
java -cp target/classes rtree.MainDemo
```

### Альтернативные варианты запуска

```bash
# Запуск с использованием Maven (если установлен)
mvn compile
java -cp "target/classes" rtree.MainDemo

# Запуск демонстрации с рефлексией (для работы с оригинальной библиотекой)
# Примечание: требует Java 17+
java -cp target/classes rtree.ReflectionDemo
```

### Проверка работы

После запуска вы увидите:
1. Вставку 8 объектов недвижимости в R-дерево
2. Автоматическое построение структуры дерева
3. Результаты поиска в различных областях
4. Статистику найденных объектов

## Примеры использования

### Базовый пример с упрощенной реализацией

```java
// Создание R-Tree
SimpleRTree<String> tree = new SimpleRTree<>(4);

// Вставка элементов
SimpleRectangle rect = new SimpleRectangle(0, 0, 10, 10);
tree.insert(rect, "Мой объект");

// Поиск
List<String> results = tree.search(new SimpleRectangle(5, 5, 15, 15));

// Вывод структуры дерева
tree.printTree();
```

### Географическая система

```java
SimpleRTree<String> landmarks = new SimpleRTree<>(4);

// Добавление достопримечательностей
landmarks.insert(new SimpleRectangle(55.7558, 37.6176, 55.7559, 37.6177), "Красная площадь");
landmarks.insert(new SimpleRectangle(55.7520, 37.6175, 55.7521, 37.6176), "ГУМ");

// Поиск в районе
List<String> found = landmarks.search(new SimpleRectangle(55.75, 37.61, 55.76, 37.62));
```

### Работа с оригинальной библиотекой (Java 17+)

```java
// Требует Java 17+ и библиотеку rtree-lib-1.0-SNAPSHOT.jar
import rtree.RTree;
import rtree.Rectangle;

RTree<String> tree = new RTree<>(4);
Rectangle rect = new Rectangle(0, 0, 10, 10);
tree.insert(rect, "Мой объект");
List<String> results = tree.search(new Rectangle(5, 5, 15, 15));
```

## Алгоритм работы R-Tree

1. **Вставка:**
   - Находим подходящий листовой узел
   - Если узел полон, разделяем его на два
   - Вставляем элемент в подходящий узел

2. **Поиск:**
   - Начинаем с корневого узла
   - Рекурсивно спускаемся по дереву
   - Проверяем пересечение MBR узлов с запросом
   - Собираем все подходящие элементы

3. **Балансировка:**
   - Узлы автоматически разделяются при переполнении
   - MBR пересчитываются для оптимального покрытия

## Области применения

- **ГИС (геоинформационные системы)** - хранение и поиск географических объектов
- **Игровые движки** - оптимизация поиска объектов в пространстве
- **Базы данных** - пространственные индексы
- **Системы навигации** - поиск ближайших объектов
- **Медицинские системы** - анализ изображений
- **Физическое моделирование** - обнаружение коллизий

## Ограничения текущей реализации

- Не поддерживает удаление элементов (только вставка и поиск)
- Оптимизирована для 2D пространства
- Не поддерживает k-ближайших соседей
- Нет поддержки bulk-загрузки

## Технические характеристики

- **Временная сложность вставки:** O(log n)
- **Временная сложность поиска:** O(log n + k), где k - количество результатов
- **Пространственная сложность:** O(n)
- **Минимальная заполненность узла:** обычно 40% (зависит от реализации)

## Тестирование

Проект включает модульные тесты в `RTreeTest.java`, которые проверяют:
- Корректность вставки и поиска
- Обработку пересечений прямоугольников
- Поведение при пустом дереве
- Разделение узлов при переполнении

## Особенности текущей реализации

### Упрощенная реализация (рекомендуется для запуска)
- Полностью совместима с Java 8
- Демонстрирует основные принципы работы R-Tree
- Включает визуализацию структуры дерева
- Поддерживает вставку и поиск

### Оригинальная библиотека
- Более полная и оптимизированная реализация
- Требует Java 17+
- Скомпилирована в `rtree-lib-1.0-SNAPSHOT.jar`
- Может быть использована через рефлексию (ReflectionDemo.java)

## 🤝 Как внести вклад

Мы приветствуем вклад в развитие проекта! Вот как вы можете помочь:

### 📝 Сообщение об ошибках
Если вы нашли ошибку, пожалуйста, создайте [Issue](https://github.com/xcxchezz/rtree-test/issues) со следующими деталями:
- Описание проблемы
- Шаги для воспроизведения
- Ожидаемое поведение
- Фактическое поведение
- Информация о вашей среде (Java версия, ОС)

### 🚀 Предложения по улучшению
Идеи по улучшению приветствуются! Создайте Issue с меткой `enhancement` или отправьте Pull Request.

### 🔧 Разработка
1. Форкните репозиторий
2. Создайте feature ветку: `git checkout -b feature/amazing-feature`
3. Внесите изменения
4. Запустите тесты: `mvn test` (если применимо)
5. Сделайте commit: `git commit -m 'Add amazing feature'`
6. Push в ветку: `git push origin feature/amazing-feature`
7. Создайте Pull Request

### 📋 Стандарты кода
- Используйте Java naming conventions
- Добавляйте комментарии к сложным алгоритмам
- Пишите понятные commit сообщения
- Обновляйте документацию при внесении изменений

## 📄 Лицензия

Этот проект распространяется под лицензией MIT. Подробности смотрите в файле [LICENSE](LICENSE).

```
MIT License

Copyright (c) 2025 xcxchezz

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
```

## 📞 Контакты

- **Автор**: xcxchezz
- **GitHub**: [https://github.com/xcxchezz](https://github.com/xcxchezz)
- **Репозиторий**: [https://github.com/xcxchezz/rtree-test](https://github.com/xcxchezz/rtree-test)
- **Issues**: [https://github.com/xcxchezz/rtree-test/issues](https://github.com/xcxchezz/rtree-test/issues)

## 🙏 Благодарности

- Благодарность за изучение структур данных и алгоритмов пространственного индексирования
- Вдохновение от оригинальных работ по R-Tree (Guttman, 1984)
- Сообщество разработчиков за вклад в развитие алгоритмов

## 📚 Ссылки

- [Оригинальная статья о R-Tree](https://dl.acm.org/doi/10.1145/971697.602266) - Antonin Guttman, 1984
- [Wikipedia: R-tree](https://en.wikipedia.org/wiki/R-tree)
- [Java Documentation](https://docs.oracle.com/en/java/)

---

⭐ Если этот проект был полезен для вас, поставьте звезду на GitHub!
