//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
class Dish {
    private String name;
    private double price;
    private String category;

    public Dish(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public void displayInfo() {
        System.out.println("Назва: " + name + ", Ціна: " + price + ", Категорія: " + category);
    }
}

class Restaurant {
    private static Dish[] menu = new Dish[100];
    private static int count = 0;

    public static void addDish(Dish dish) {
        if (count < menu.length) {
            menu[count++] = dish;
        } else {
            System.out.println("Меню переповнене!");
        }
    }

    public static int getTotalDishes() {
        return count;
    }

    public static void displayMenu() {
        for (int i = 0; i < count; i++) {
            menu[i].displayInfo();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Dish soup = new Dish("Суп", 80.0, "Закуска");
        Dish pizza = new Dish("Піца", 150.0, "Основна страва");
        Dish iceCream = new Dish("Морозиво", 50.0, "Десерт");

        Restaurant.addDish(soup);
        Restaurant.addDish(pizza);
        Restaurant.addDish(iceCream);

        System.out.println("Меню ресторану:");
        Restaurant.displayMenu();
        System.out.println("Загальна кількість страв у меню: " + Restaurant.getTotalDishes());
    }
}