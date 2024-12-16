interface Shape {
    double calculateArea();
    String getInfo();
}

// Клас "Коло"
class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String getInfo() {
        return "Circle with radius: " + radius + ", Area: " + calculateArea();
    }
}

// Клас "Прямокутник"
class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public String getInfo() {
        return "Rectangle with width: " + width + ", height: " + height + ", Area: " + calculateArea();
    }
}

// Клас "Трикутник"
class Triangle implements Shape {
    private double base;
    private double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }

    @Override
    public String getInfo() {
        return "Triangle with base: " + base + ", height: " + height + ", Area: " + calculateArea();
    }
}

// Задача 2: Тварини

// Інтерфейс для тварин
interface Animal {
    String makeSound();
    String getFood();
    String getInfo();
}

// Клас "Собака"
class Dog implements Animal {
    @Override
    public String makeSound() {
        return "Woof";
    }

    @Override
    public String getFood() {
        return "Meat";
    }

    @Override
    public String getInfo() {
        return "Dog: Sound - " + makeSound() + ", Food - " + getFood();
    }
}

// Клас "Кішка"
class Cat implements Animal {
    @Override
    public String makeSound() {
        return "Meow";
    }

    @Override
    public String getFood() {
        return "Fish";
    }

    @Override
    public String getInfo() {
        return "Cat: Sound - " + makeSound() + ", Food - " + getFood();
    }
}

// Клас "Птах"
class Bird implements Animal {
    @Override
    public String makeSound() {
        return "Tweet";
    }

    @Override
    public String getFood() {
        return "Seeds";
    }

    @Override
    public String getInfo() {
        return "Bird: Sound - " + makeSound() + ", Food - " + getFood();
    }
}

// Основний клас для демонстрації
public class Main {
    public static void main(String[] args) {
        // Масив фігур
        Shape[] shapes = {
                new Circle(5),
                new Rectangle(4, 6),
                new Triangle(3, 7)
        };

        System.out.println("Geometric Shapes:");
        for (Shape shape : shapes) {
            System.out.println(shape.getInfo());
        }

        // Масив тварин
        Animal[] animals = {
                new Dog(),
                new Cat(),
                new Bird()
        };

        System.out.println("\nAnimals:");
        for (Animal animal : animals) {
            System.out.println(animal.getInfo());
        }
    }
}

