package rtree;

/**
 * Упрощенная версия записи для демонстрации
 */
public class SimpleEntry<T> {
    public SimpleRectangle mbr;
    public T value;

    public SimpleEntry(SimpleRectangle mbr, T value) {
        this.mbr = mbr;
        this.value = value;
    }

    @Override
    public String toString() {
        return value + " " + mbr;
    }
}
