//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    class Book {
        private String title;
        private String author;
        private double price;

        public Book(String title, String author, double price) {
            this.title = title;
            this.author = author;
            this.price = price;
        }

        public void applyDiscount(double percent) {
            this.price -= this.price * (percent / 100);
        }

        public void printInfo() {
            System.out.printf("Назва: %s, Автор: %s, Ціна: %.2f грн%n", title, author, price);
        }
    }

    public class bruh {
        public void main(String[] args) {
            Book book1 = new Book("Війна і мир", "Лев Толстой", 500);
            Book book2 = new Book("Майстер і Маргарита", "Михайло Булгаков", 300);
            Book book3 = new Book("Тарас Бульба", "Микола Гоголь", 200);

            book3.applyDiscount(10);

            book1.printInfo();
            book2.printInfo();
            book3.printInfo();
        }
    }
}

