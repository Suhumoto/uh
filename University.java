import java.util.*;

public class University {
    private String name;
    private List<Department> departments;

    public University(String name) {
        this.name = name;
        this.departments = new ArrayList<>();
    }

    public void addDepartment(Department department) {
        departments.add(department);
    }

    public void displayInfo() {
        System.out.println("University: " + name);
        for (Department department : departments) {
            department.displayInfo();
        }
    }

    public class Department {
        private String name;
        private List<Student> students;

        public Department(String name) {
            this.name = name;
            this.students = new ArrayList<>();
        }

        public void addStudent(Student student) {
            students.add(student);
        }

        public void displayInfo() {
            System.out.println("  Department: " + name);
            for (Student student : students) {
                System.out.println("    Student ID: " + student.id + ", Name: " + student.name);
            }
        }

        public class Student {
            private String name;
            private int id;

            public Student(String name, int id) {
                this.name = name;
                this.id = id;
            }
        }
    }

    public static void main(String[] args) {
        University university = new University("Tech University");

        University.Department csDepartment = university.new Department("Computer Science");
        University.Department mathDepartment = university.new Department("Mathematics");

        csDepartment.addStudent(csDepartment.new Student("Alice", 101));
        csDepartment.addStudent(csDepartment.new Student("Bob", 102));

        mathDepartment.addStudent(mathDepartment.new Student("Charlie", 201));
        mathDepartment.addStudent(mathDepartment.new Student("David", 202));

        university.addDepartment(csDepartment);
        university.addDepartment(mathDepartment);

        university.displayInfo();
    }
}

