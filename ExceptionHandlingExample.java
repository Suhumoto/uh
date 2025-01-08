public class ExceptionHandlingExample {
    public static void main(String[] args) {
        try {
            // Виклик методів, які генерують різні помилки
            int result = divide(10, 0); // Поділ на нуль
            System.out.println("Result: " + result);

            String text = null;
            printLength(text); // Спроба роботи з null-значенням

            int[] numbers = {1, 2, 3};
            System.out.println(numbers[5]); // Вихід за межі масиву

        } catch (ArithmeticException e) {
            System.out.println("Помилка: Поділ на нуль!");
        } catch (NullPointerException e) {
            System.out.println("Помилка: Null-значення!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Помилка: Вихід за межі масиву!");
        } finally {
            System.out.println("Цей блок виконується завжди.");
        }

        System.out.println("Програма продовжує працювати.");
    }

    // Метод для демонстрації ArithmeticException
    public static int divide(int a, int b) {
        return a / b;
    }

    // Метод для демонстрації NullPointerException
    public static void printLength(String text) {
        System.out.println("Довжина тексту: " + text.length());
    }
}
