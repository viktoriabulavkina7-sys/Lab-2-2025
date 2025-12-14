# Лабораторная работа №2 по ООП

## Тема: Табулированные функции

### Выполнил
Булавкина Виктория Станиславовна
Группа: 6201-120303D  
Самарский университет им. академика С.П. Королева  
2025 год

## Описание работы
Разработка набора классов для работы с функциями одной переменной, заданными в табличной форме.

## Реализованные классы

### 1. FunctionPoint
Класс, описывающий точку табулированной функции.

Конструкторы:
- `FunctionPoint(double x, double y)` - точка с заданными координатами
- `FunctionPoint(FunctionPoint point)` - копия существующей точки
- `FunctionPoint()` - точка (0, 0)

Методы:
- `getX()`, `setX(double x)`
- `getY()`, `setY(double y)`

### 2. TabulatedFunction
Класс, описывающий табулированную функцию.

Конструкторы:
- `TabulatedFunction(double leftX, double rightX, int pointsCount)`
- `TabulatedFunction(double leftX, double rightX, double[] values)`

Основные методы:
- `double getLeftDomainBorder()` - левая граница области определения
- `double getRightDomainBorder()` - правая граница области определения
- `double getFunctionValue(double x)` - значение функции с линейной интерполяцией
- `int getPointsCount()` - количество точек
- `FunctionPoint getPoint(int index)` - получение копии точки
- `void setPoint(int index, FunctionPoint point)` - замена точки
- `void deletePoint(int index)` - удаление точки
- `void addPoint(FunctionPoint point)` - добавление новой точки

## Структура проекта