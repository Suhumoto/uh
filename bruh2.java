//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
class Book {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public void printInfo() {
        System.out.printf("Назва: %s, Автор: %s%n", title, author);
    }
}

class Student {
    private String name;
    private String groupNumber;
    private double averageGrade;
    private Book[] books; // Масив книг
    private int bookCount; // Лічильник для доданих книг

    public Student(String name, String groupNumber, double averageGrade) {
        this.name = name;
        this.groupNumber = groupNumber;
        this.averageGrade = averageGrade;
        this.books = new Book[10]; // Максимальна кількість книг
        this.bookCount = 0;
    }

    public void addBook(Book book) {
        if (bookCount < books.length) {
            books[bookCount++] = book;
        } else {
            System.out.println("Неможливо додати більше книг!");
        }
    }

    public void updateGrade(double newGrade) {
        this.averageGrade = newGrade;
    }

    public void printInfo() {
        System.out.printf("Ім'я: %s, Група: %s, Середній бал: %.2f%n", name, groupNumber, averageGrade);
        System.out.println("Книги:");
        for (int i = 0; i < bookCount; i++) {
            books[i].printInfo();
        }
    }
}

public class bruh2 {
    public static void main(String[] args) {
        Book book1 = new Book("Війна і мир", "Лев Толстой");
        Book book2 = new Book("Майстер і Маргарита", "Михайло Булгаков");
        Book book3 = new Book("Тарас Бульба", "Микола Гоголь");

        Student student1 = new Student("Андрій", "КН-202", 4.5);
        Student student2 = new Student("Марія", "ІТ-101", 4.8);
        Student student3 = new Student("Олена", "ФІ-303", 4.2);

        student1.addBook(book1);
        student2.addBook(book2);
        student3.addBook(book3);

        student1.updateGrade(4.7);

        student1.printInfo();
        student2.printInfo();
        student3.printInfo();
    }
}



