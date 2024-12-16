//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.*;

// Базовий клас "Книга"
class Book {
    protected String title;
    protected String author;
    protected int year;
    protected String isbn;
    protected boolean isAvailable;

    public Book(String title, String author, int year, String isbn) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
        this.isAvailable = true;
    }

    public void setAvailability(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getInfo() {
        return "Title: " + title + ", Author: " + author + ", Year: " + year + ", ISBN: " + isbn;
    }
}

// Похідний клас "Художня книга"
class FictionBook extends Book {
    private String genre;

    public FictionBook(String title, String author, int year, String isbn, String genre) {
        super(title, author, year, isbn);
        this.genre = genre;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + ", Genre: " + genre;
    }

    public String getGenre() {
        return genre;
    }
}

// Похідний клас "Навчальна книга"
class EducationalBook extends Book {
    private String subject;
    private String level;

    public EducationalBook(String title, String author, int year, String isbn, String subject, String level) {
        super(title, author, year, isbn);
        this.subject = subject;
        this.level = level;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + ", Subject: " + subject + ", Level: " + level;
    }

    public String getSubject() {
        return subject;
    }
}

// Клас "Читач"
class Reader {
    private String name;
    String id;
    private List<Book> borrowedBooks;

    public Reader(String name, String id) {
        this.name = name;
        this.id = id;
        this.borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            borrowedBooks.add(book);
            book.setAvailability(false);
            System.out.println(name + " borrowed " + book.title);
        } else {
            System.out.println("Book is not available: " + book.title);
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.setAvailability(true);
            System.out.println(name + " returned " + book.title);
        } else {
            System.out.println("Book not found in borrowed list: " + book.title);
        }
    }
}

// Клас "Бібліотека"
class Library {
    private List<Book> books;
    private List<Reader> readers;

    public Library() {
        this.books = new ArrayList<>();
        this.readers = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerReader(Reader reader) {
        readers.add(reader);
    }

    public void lendBook(String isbn, String readerId) {
        Book book = findBookByIsbn(isbn);
        Reader reader = findReaderById(readerId);

        if (book != null && reader != null) {
            reader.borrowBook(book);
        } else {
            System.out.println("Invalid book ISBN or reader ID.");
        }
    }

    public void returnBook(String isbn, String readerId) {
        Book book = findBookByIsbn(isbn);
        Reader reader = findReaderById(readerId);

        if (book != null && reader != null) {
            reader.returnBook(book);
        } else {
            System.out.println("Invalid book ISBN or reader ID.");
        }
    }

    public void listBooks() {
        for (Book book : books) {
            System.out.println(book.getInfo() + (book.isAvailable() ? " (Available)" : " (Not Available)"));
        }
    }

    public void searchByGenre(String genre) {
        for (Book book : books) {
            if (book instanceof FictionBook) {
                FictionBook fictionBook = (FictionBook) book;
                if (fictionBook.getGenre().equalsIgnoreCase(genre)) {
                    System.out.println(fictionBook.getInfo());
                }
            }
        }
    }

    public void searchBySubject(String subject) {
        for (Book book : books) {
            if (book instanceof EducationalBook) {
                EducationalBook educationalBook = (EducationalBook) book;
                if (educationalBook.getSubject().equalsIgnoreCase(subject)) {
                    System.out.println(educationalBook.getInfo());
                }
            }
        }
    }

    private Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.isbn.equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    private Reader findReaderById(String id) {
        for (Reader reader : readers) {
            if (reader.id.equals(id)) {
                return reader;
            }
        }
        return null;
    }
}

// Головний клас
public class LibrarySystem {
    public static void main(String[] args) {
        Library library = new Library();

        // Додавання книг
        library.addBook(new FictionBook("The Hobbit", "J.R.R. Tolkien", 1937, "1234", "Fantasy"));
        library.addBook(new EducationalBook("Physics Fundamentals", "Isaac Newton", 1687, "5678", "Physics", "University"));

        // Реєстрація читача
        Reader reader = new Reader("Alice", "reader1");
        library.registerReader(reader);

        // Видача книги
        library.lendBook("1234", "reader1");

        // Спроба повторного взяття тієї ж книги
        library.lendBook("1234", "reader1");

        // Повернення книги
        library.returnBook("1234", "reader1");

        // Перегляд усіх книг
        System.out.println("\nBooks in library:");
        library.listBooks();

        // Пошук книг за жанром
        System.out.println("\nSearch by genre (Fantasy):");
        library.searchByGenre("Fantasy");

        // Пошук книг за предметом
        System.out.println("\nSearch by subject (Physics):");
        library.searchBySubject("Physics");
    }
}

