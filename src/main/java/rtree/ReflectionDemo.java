package rtree;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

/**
 * Демонстрационная программа, использующая рефлексию для работы с классами R-дерева из jar файла
 */
public class ReflectionDemo {

    public static void main(String[] args) {
        try {
            System.out.println("=== Демонстрация работы R-дерева (с использованием рефлексии) ===\n");

            // Загружаем классы из jar файла
            URL jarUrl = new URL("file:lib/rtree-lib-1.0-SNAPSHOT.jar");
            URLClassLoader classLoader = new URLClassLoader(new URL[]{jarUrl});

            // Получаем классы
            Class<?> rtreeClass = classLoader.loadClass("rtree.RTree");
            Class<?> rectangleClass = classLoader.loadClass("rtree.Rectangle");

            // Создаем R-дерево
            Constructor<?> rtreeConstructor = rtreeClass.getConstructor(int.class);
            Object rtree = rtreeConstructor.newInstance(4);

            System.out.println("1. Вставка данных в R-дерево:");

            // Вставляем данные
            insertDataWithReflection(rtree, rtreeClass, rectangleClass, 10, 10, 20, 20, "Квартира 1");
            insertDataWithReflection(rtree, rtreeClass, rectangleClass, 15, 15, 25, 25, "Квартира 2");
            insertDataWithReflection(rtree, rtreeClass, rectangleClass, 30, 30, 40, 40, "Офис 1");
            insertDataWithReflection(rtree, rtreeClass, rectangleClass, 35, 35, 45, 45, "Офис 2");

            System.out.println("\n2. Поиск данных в R-дереве:");

            // Выполняем поиск
            System.out.println("\nПоиск в области (12, 12, 22, 22):");
            searchAndPrintWithReflection(rtree, rtreeClass, rectangleClass, 12, 12, 22, 22);

            System.out.println("\nПоиск в области (32, 32, 42, 42):");
            searchAndPrintWithReflection(rtree, rtreeClass, rectangleClass, 32, 32, 42, 42);

            System.out.println("\n=== Демонстрация завершена ===");

        } catch (Exception e) {
            System.out.println("Ошибка при работе с рефлексией: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void insertDataWithReflection(Object rtree, Class<?> rtreeClass,
            Class<?> rectangleClass, double x1, double y1, double x2, double y2, String data)
            throws Exception {

        // Создаем прямоугольник
        Constructor<?> rectConstructor = rectangleClass.getConstructor(double.class, double.class, double.class, double.class);
        Object rect = rectConstructor.newInstance(x1, y1, x2, y2);

        // Вызываем insert
        Method insertMethod = rtreeClass.getMethod("insert", rectangleClass, Object.class);
        insertMethod.invoke(rtree, rect, data);

        System.out.printf("Вставлено: %s -> (%.1f, %.1f, %.1f, %.1f)%n", data, x1, y1, x2, y2);
    }

    private static void searchAndPrintWithReflection(Object rtree, Class<?> rtreeClass,
            Class<?> rectangleClass, double x1, double y1, double x2, double y2)
            throws Exception {

        // Создаем прямоугольник для поиска
        Constructor<?> rectConstructor = rectangleClass.getConstructor(double.class, double.class, double.class, double.class);
        Object searchRect = rectConstructor.newInstance(x1, y1, x2, y2);

        // Вызываем search
        Method searchMethod = rtreeClass.getMethod("search", rectangleClass);
        @SuppressWarnings("unchecked")
        List<String> results = (List<String>) searchMethod.invoke(rtree, searchRect);

        System.out.printf("Область поиска: (%.1f, %.1f, %.1f, %.1f)%n", x1, y1, x2, y2);
        System.out.println("Найдено объектов: " + results.size());

        if (!results.isEmpty()) {
            System.out.println("Результаты:");
            for (String result : results) {
                System.out.println("  - " + result);
            }
        } else {
            System.out.println("Ничего не найдено");
        }
        System.out.println();
    }
}
