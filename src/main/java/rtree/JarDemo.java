package rtree;

import rtree.RTree;
import rtree.Rectangle;
import java.util.List;

/**
 * Демонстрационная программа, использующая оригинальные классы R-Tree из jar файла
 * Требует Java 17+
 */
public class JarDemo {

    public static void main(String[] args) {
        System.out.println("=== Демонстрация работы R-дерева (используя оригинальные классы из jar) ===\n");

        // Создаем R-дерево с максимум 4 элементов в узле
        RTree<String> rtree = new RTree<>(4);

        System.out.println("1. Вставка данных в R-дерево:");

        // Вставляем несколько прямоугольников с данными
        insertData(rtree, 10, 10, 20, 20, "Квартира 1");
        insertData(rtree, 15, 15, 25, 25, "Квартира 2");
        insertData(rtree, 30, 30, 40, 40, "Офис 1");
        insertData(rtree, 35, 35, 45, 45, "Офис 2");
        insertData(rtree, 50, 50, 60, 60, "Магазин 1");
        insertData(rtree, 55, 55, 65, 65, "Магазин 2");
        insertData(rtree, 70, 70, 80, 80, "Ресторан 1");
        insertData(rtree, 75, 75, 85, 85, "Ресторан 2");

        System.out.println("\n2. Поиск данных в R-дереве:");

        // Примеры поиска
        System.out.println("\nПоиск в области (12, 12, 22, 22):");
        searchAndPrint(rtree, 12, 12, 22, 22);

        System.out.println("\nПоиск в области (32, 32, 42, 42):");
        searchAndPrint(rtree, 32, 32, 42, 42);

        System.out.println("\nПоиск в области (52, 52, 62, 62):");
        searchAndPrint(rtree, 52, 52, 62, 62);

        System.out.println("\nПоиск в области (72, 72, 82, 82):");
        searchAndPrint(rtree, 72, 72, 82, 82);

        // Поиск в области, которая пересекает несколько объектов
        System.out.println("\nПоиск в большой области (0, 0, 100, 100):");
        searchAndPrint(rtree, 0, 0, 100, 100);

        // Поиск в пустой области
        System.out.println("\nПоиск в пустой области (200, 200, 210, 210):");
        searchAndPrint(rtree, 200, 200, 210, 210);

        System.out.println("\n=== Демонстрация завершена ===");
    }

    /**
     * Вставляет данные в R-дерево
     */
    private static void insertData(RTree<String> rtree, double x1, double y1, double x2, double y2, String data) {
        Rectangle rect = new Rectangle(x1, y1, x2, y2);
        rtree.insert(rect, data);
        System.out.printf("Вставлено: %s -> (%.1f, %.1f, %.1f, %.1f)%n", data, x1, y1, x2, y2);
    }

    /**
     * Выполняет поиск и выводит результаты
     */
    private static void searchAndPrint(RTree<String> rtree, double x1, double y1, double x2, double y2) {
        Rectangle searchRect = new Rectangle(x1, y1, x2, y2);
        List<String> results = rtree.search(searchRect);

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
