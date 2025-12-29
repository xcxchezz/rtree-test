package rtree;

import java.util.ArrayList;
import java.util.List;

/**
 * Упрощенный узел R-дерева для демонстрации
 */
public class SimpleNode<T> {
    public boolean leaf;
    public List<SimpleEntry<T>> entries;
    public List<SimpleNode<T>> children;
    public SimpleRectangle mbr;

    public SimpleNode(boolean leaf) {
        this.leaf = leaf;
        this.entries = new ArrayList<>();
        this.children = new ArrayList<>();
        this.mbr = null;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void updateMBR() {
        if (leaf) {
            // Для листового узла MBR охватывает все записи
            if (entries.isEmpty()) {
                mbr = null;
                return;
            }
            mbr = entries.get(0).mbr;
            for (int i = 1; i < entries.size(); i++) {
                mbr = SimpleRectangle.combine(mbr, entries.get(i).mbr);
            }
        } else {
            // Для внутреннего узла MBR охватывает MBR всех детей
            if (children.isEmpty()) {
                mbr = null;
                return;
            }
            mbr = children.get(0).mbr;
            for (int i = 1; i < children.size(); i++) {
                if (children.get(i).mbr != null) {
                    mbr = SimpleRectangle.combine(mbr, children.get(i).mbr);
                }
            }
        }
    }
}
