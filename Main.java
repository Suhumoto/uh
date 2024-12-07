//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
class Book {
    private String title;
    private String author;
    private int publicationYear;

    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public String toString() {
        return "Book: " + title + " by " + author + ", " + publicationYear;
    }
}

class Library {
    private Book[] books = new Book[100];
    private int count = 0;

    public void addBook(Book book) {
        if (count < books.length) {
            books[count++] = book;
        }
    }

    public void removeBook(String title) {
        for (int i = 0; i < count; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {
                books[i] = books[--count];
                books[count] = null;
                break;
            }
        }
    }

    public Book[] searchByAuthor(String author) {
        Book[] results = new Book[count];
        int resultCount = 0;
        for (int i = 0; i < count; i++) {
            if (books[i].getAuthor().equalsIgnoreCase(author)) {
                results[resultCount++] = books[i];
            }
        }
        return trimResults(results, resultCount);
    }

    public Book[] searchByTitle(String title) {
        Book[] results = new Book[count];
        int resultCount = 0;
        for (int i = 0; i < count; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {
                results[resultCount++] = books[i];
            }
        }
        return trimResults(results, resultCount);
    }

    public int getBookCount() {
        return count;
    }

    private Book[] trimResults(Book[] results, int size) {
        Book[] trimmed = new Book[size];
        for (int i = 0; i < size; i++) {
            trimmed[i] = results[i];
        }
        return trimmed;
    }
}
class BankAccount {
    private String accountHolderName;
    private double balance;
    private boolean isBlocked;

    public BankAccount(String accountHolderName, double initialBalance) {
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        this.isBlocked = false;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void deposit(double amount) {
        if (!isBlocked && amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (!isBlocked && amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }

    public void blockAccount() {
        isBlocked = true;
    }

    public void unblockAccount() {
        isBlocked = false;
    }
}

class Bank {
    private BankAccount[] accounts = new BankAccount[100];
    private int count = 0;

    public void createAccount(String accountHolderName, double initialBalance) {
        if (count < accounts.length) {
            accounts[count++] = new BankAccount(accountHolderName, initialBalance);
        }
    }

    public void blockAccount(String accountHolderName) {
        BankAccount account = findAccountByName(accountHolderName);
        if (account != null) {
            account.blockAccount();
        }
    }

    public void unblockAccount(String accountHolderName) {
        BankAccount account = findAccountByName(accountHolderName);
        if (account != null) {
            account.unblockAccount();
        }
    }

    public void transferMoney(String fromAccountHolder, String toAccountHolder, double amount) {
        BankAccount fromAccount = findAccountByName(fromAccountHolder);
        BankAccount toAccount = findAccountByName(toAccountHolder);

        if (fromAccount != null && toAccount != null && !fromAccount.isBlocked() && !toAccount.isBlocked()) {
            if (fromAccount.getBalance() >= amount) {
                fromAccount.withdraw(amount);
                toAccount.deposit(amount);
            }
        }
    }

    public BankAccount findAccountByName(String accountHolderName) {
        for (int i = 0; i < count; i++) {
            if (accounts[i].getAccountHolderName().equalsIgnoreCase(accountHolderName)) {
                return accounts[i];
            }
        }
        return null;
    }

    public int getTotalAccounts() {
        return count;
    }
}
class Employee {
    private String name;
    private double salary;
    private String department;

    public Employee(String name, double salary, String department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee: " + name + ", " + department + ", Salary: " + salary;
    }
}

class Company {
    private Employee[] employees = new Employee[100];
    private int count = 0;

    public void addEmployee(Employee employee) {
        if (count < employees.length) {
            employees[count++] = employee;
        }
    }

    public void removeEmployee(String name) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getName().equalsIgnoreCase(name)) {
                employees[i] = employees[--count];
                employees[count] = null;
                break;
            }
        }
    }

    public double calculateTotalSalary() {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total += employees[i].getSalary();
        }
        return total;
    }

    public double calculateAverageSalary() {
        if (count == 0) return 0;
        return calculateTotalSalary() / count;
    }

    public int getEmployeeCount() {
        return count;
    }
}



