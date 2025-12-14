// Файл: functions/TabulatedFunction.java
package functions;

public class TabulatedFunction {
    private FunctionPoint[] points;
    private int size; // текущее количество точек
    private int capacity; // вместимость массива

    // Конструкторы
    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        if (pointsCount < 2 || leftX >= rightX) {
            throw new IllegalArgumentException("Некорректные параметры");
        }

        capacity = Math.max(pointsCount * 2, 10);
        points = new FunctionPoint[capacity];
        size = pointsCount;

        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            points[i] = new FunctionPoint(x, 0);
        }
    }

    public TabulatedFunction(double leftX, double rightX, double[] values) {
        this(leftX, rightX, values.length);

        for (int i = 0; i < values.length; i++) {
            points[i].setY(values[i]);
        }
    }

    // Методы для работы с функцией
    public double getLeftDomainBorder() {
        return points[0].getX();
    }

    public double getRightDomainBorder() {
        return points[size - 1].getX();
    }

    public double getFunctionValue(double x) {
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
            return Double.NaN;
        }

        // Поиск интервала
        int i = 0;
        while (i < size - 1 && x > points[i + 1].getX()) {
            i++;
        }

        // Если x совпадает с точкой
        if (Math.abs(x - points[i].getX()) < 1e-10) {
            return points[i].getY();
        }
        if (i < size - 1 && Math.abs(x - points[i + 1].getX()) < 1e-10) {
            return points[i + 1].getY();
        }

        // Линейная интерполяция
        double x1 = points[i].getX();
        double y1 = points[i].getY();
        double x2 = points[i + 1].getX();
        double y2 = points[i + 1].getY();

        return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
    }

    // Методы для работы с точками
    public int getPointsCount() {
        return size;
    }

    public FunctionPoint getPoint(int index) {
        checkIndex(index);
        return new FunctionPoint(points[index]);
    }

    public void setPoint(int index, FunctionPoint point) {
        checkIndex(index);

        // Проверка корректности новой точки
        if (index > 0 && point.getX() <= points[index - 1].getX()) {
            throw new IllegalArgumentException("Точка нарушает порядок по X");
        }
        if (index < size - 1 && point.getX() >= points[index + 1].getX()) {
            throw new IllegalArgumentException("Точка нарушает порядок по X");
        }

        points[index] = new FunctionPoint(point);
    }

    public double getPointX(int index) {
        checkIndex(index);
        return points[index].getX();
    }

    public void setPointX(int index, double x) {
        checkIndex(index);
        // Создаем временную точку для проверки
        FunctionPoint temp = new FunctionPoint(points[index]);
        temp.setX(x);
        setPoint(index, temp);
    }

    public double getPointY(int index) {
        checkIndex(index);
        return points[index].getY();
    }

    public void setPointY(int index, double y) {
        checkIndex(index);
        points[index].setY(y);
    }

    // Методы изменения количества точек
    public void deletePoint(int index) {
        checkIndex(index);

        if (size <= 2) {
            throw new IllegalStateException("Нельзя удалить точку: минимум 2 точки");
        }

        System.arraycopy(points, index + 1, points, index, size - index - 1);
        size--;

        // Уменьшаем массив если он слишком пустой
        if (size < capacity / 4 && capacity > 10) {
            resize(capacity / 2);
        }
    }

    public void addPoint(FunctionPoint point) {
        // Находим позицию для вставки
        int insertIndex = 0;
        while (insertIndex < size && points[insertIndex].getX() < point.getX()) {
            insertIndex++;
        }

        // Проверяем дубликат
        if (insertIndex < size && Math.abs(points[insertIndex].getX() - point.getX()) < 1e-10) {
            throw new IllegalArgumentException("Точка с таким X уже существует");
        }

        // Увеличиваем массив если нужно
        if (size == capacity) {
            resize(capacity * 2);
        }

        // Сдвигаем элементы
        System.arraycopy(points, insertIndex, points, insertIndex + 1, size - insertIndex);
        points[insertIndex] = new FunctionPoint(point);
        size++;
    }

    // Вспомогательные методы
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона: " + index);
        }
    }

    private void resize(int newCapacity) {
        FunctionPoint[] newPoints = new FunctionPoint[newCapacity];
        System.arraycopy(points, 0, newPoints, 0, size);
        points = newPoints;
        capacity = newCapacity;
    }

    // Дополнительный метод для печати
    public void printFunction() {
        System.out.println("Табулированная функция:");
        for (int i = 0; i < size; i++) {
            System.out.printf("(%6.2f; %6.2f)\n", points[i].getX(), points[i].getY());
        }
    }
}