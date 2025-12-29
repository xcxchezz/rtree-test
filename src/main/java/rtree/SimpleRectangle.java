package rtree;

/**
 * Упрощенная версия прямоугольника для демонстрации
 */
public class SimpleRectangle {
    public double x1, y1, x2, y2;

    public SimpleRectangle(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public boolean intersects(SimpleRectangle other) {
        return !(x2 < other.x1 || other.x2 < x1 || y2 < other.y1 || other.y2 < y1);
    }

    public boolean contains(SimpleRectangle other) {
        return x1 <= other.x1 && y1 <= other.y1 && x2 >= other.x2 && y2 >= other.y2;
    }

    public double area() {
        return (x2 - x1) * (y2 - y1);
    }

    public static SimpleRectangle combine(SimpleRectangle r1, SimpleRectangle r2) {
        return new SimpleRectangle(
            Math.min(r1.x1, r2.x1),
            Math.min(r1.y1, r2.y1),
            Math.max(r1.x2, r2.x2),
            Math.max(r1.y2, r2.y2)
        );
    }

    @Override
    public String toString() {
        return String.format("(%.1f,%.1f,%.1f,%.1f)", x1, y1, x2, y2);
    }
}
