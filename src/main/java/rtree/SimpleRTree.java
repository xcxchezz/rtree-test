package rtree;

import java.util.ArrayList;
import java.util.List;

/**
 * Упрощенная версия R-дерева для демонстрации основных принципов
 */
public class SimpleRTree<T> {
    private SimpleNode<T> root;
    private int maxEntries;

    public SimpleRTree(int maxEntries) {
        this.maxEntries = maxEntries;
        this.root = new SimpleNode<>(true); // Начинаем с листового узла
    }

    /**
     * Вставка элемента в R-дерево
     */
    public void insert(SimpleRectangle rect, T value) {
        SimpleEntry<T> entry = new SimpleEntry<>(rect, value);
        insertEntry(entry);
    }

    private void insertEntry(SimpleEntry<T> entry) {
        SimpleNode<T> node = root;

        // Проходим к листу (в упрощенной версии всегда один уровень)
        while (!node.isLeaf()) {
            // В упрощенной версии просто выбираем первый дочерний узел
            if (!node.children.isEmpty()) {
                node = node.children.get(0);
            } else {
                break;
            }
        }

        // Добавляем запись в лист
        node.entries.add(entry);
        node.updateMBR();

        // Если превысили максимум, создаем новый уровень (упрощенная версия)
        if (node.entries.size() > maxEntries) {
            splitNode(node);
        }
    }

    private void splitNode(SimpleNode<T> node) {
        // Упрощенное расщепление: создаем новый родительский узел
        SimpleNode<T> newParent = new SimpleNode<>(false);
        SimpleNode<T> newSibling = new SimpleNode<>(true);

        // Перемещаем половину записей в новый sibling
        int mid = node.entries.size() / 2;
        for (int i = mid; i < node.entries.size(); i++) {
            newSibling.entries.add(node.entries.get(i));
        }
        // Удаляем перемещенные записи
        for (int i = node.entries.size() - 1; i >= mid; i--) {
            node.entries.remove(i);
        }

        // Обновляем MBR
        node.updateMBR();
        newSibling.updateMBR();

        // Добавляем детей к новому родителю
        newParent.children.add(node);
        newParent.children.add(newSibling);
        newParent.updateMBR();

        // Новый корень
        root = newParent;
    }

    /**
     * Поиск элементов, пересекающихся с заданным прямоугольником
     */
    public List<T> search(SimpleRectangle searchRect) {
        List<T> results = new ArrayList<>();
        searchNode(root, searchRect, results);
        return results;
    }

    private void searchNode(SimpleNode<T> node, SimpleRectangle searchRect, List<T> results) {
        if (node.isLeaf()) {
            // Проверяем все записи в листовом узле
            for (SimpleEntry<T> entry : node.entries) {
                if (entry.mbr.intersects(searchRect)) {
                    results.add(entry.value);
                }
            }
        } else {
            // Рекурсивно проверяем дочерние узлы
            for (SimpleNode<T> child : node.children) {
                if (child.mbr != null && child.mbr.intersects(searchRect)) {
                    searchNode(child, searchRect, results);
                }
            }
        }
    }

    /**
     * Вывод структуры дерева (для отладки)
     */
    public void printTree() {
        printNode(root, 0);
    }

    private void printNode(SimpleNode<T> node, int depth) {
        String indent = "";
        for (int i = 0; i < depth; i++) indent += "  ";

        if (node.isLeaf()) {
            System.out.println(indent + "Leaf: " + node.entries.size() + " entries");
            for (SimpleEntry<T> entry : node.entries) {
                System.out.println(indent + "  " + entry);
            }
        } else {
            System.out.println(indent + "Node: " + node.children.size() + " children, MBR: " + node.mbr);
            for (SimpleNode<T> child : node.children) {
                printNode(child, depth + 1);
            }
        }
    }
}
