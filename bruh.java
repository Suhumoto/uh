//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
class Circle {
    private double radius;
    private static final double PI = 3.14159;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double calculateArea() {
        return PI * radius * radius;
    }

    public double calculateCircumference() {
        return 2 * PI * radius;
    }

    public void printInfo() {
        System.out.printf("Радіус: %.2f, Площа: %.2f, Довжина кола: %.2f%n",
                radius, calculateArea(), calculateCircumference());
    }
}

public class bruh {
    public static void main(String[] args) {
        Circle circle = new Circle(5);
        circle.printInfo();
    }
}


