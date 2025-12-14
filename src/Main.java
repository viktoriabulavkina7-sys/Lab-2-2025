// Файл: Main.java
import functions.*;

public class Main {
    public static void main(String[] args) {
        // 1. Создаем табулированную функцию для y = x^2
        System.out.println("=== Создание функции y = x^2 ===");
        double[] values = {0, 1, 4, 9, 16, 25};
        TabulatedFunction func = new TabulatedFunction(0, 5, values);
        func.printFunction();

        // 2. Вычисляем значения в разных точках
        System.out.println("\n=== Вычисление значений ===");
        double[] testPoints = {-1, 0, 0.5, 2, 2.5, 5, 6};
        for (double x : testPoints) {
            double y = func.getFunctionValue(x);
            if (Double.isNaN(y)) {
                System.out.printf("f(%.1f) = не определено (вне области)\n", x);
            } else {
                System.out.printf("f(%.1f) = %.2f\n", x, y);
            }
        }

        // 3. Добавляем новую точку
        System.out.println("\n=== Добавление точки (2.5, 6.25) ===");
        func.addPoint(new FunctionPoint(2.5, 6.25));
        func.printFunction();

        // 4. Изменяем существующую точку
        System.out.println("\n=== Изменение точки с индексом 2 ===");
        func.setPointY(2, 3.9);
        System.out.printf("Новое значение точки 2: (%.1f; %.2f)\n",
                func.getPointX(2), func.getPointY(2));

        // 5. Удаляем точку
        System.out.println("\n=== Удаление точки с индексом 3 ===");
        func.deletePoint(3);
        func.printFunction();

        // 6. Тестируем границы области определения
        System.out.println("\n=== Границы области определения ===");
        System.out.printf("Левая граница: %.1f\n", func.getLeftDomainBorder());
        System.out.printf("Правая граница: %.1f\n", func.getRightDomainBorder());
        System.out.printf("Количество точек: %d\n", func.getPointsCount());

        // 7. Получаем копию точки
        System.out.println("\n=== Копия точки ===");
        FunctionPoint copy = func.getPoint(1);
        System.out.printf("Копия точки 1: (%.1f; %.2f)\n", copy.getX(), copy.getY());

        // 8. Пробуем добавить некорректную точку
        System.out.println("\n=== Попытка добавить некорректную точку ===");
        try {
            func.addPoint(new FunctionPoint(10, 100)); // Корректно
            System.out.println("Точка (10, 100) добавлена успешно");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}