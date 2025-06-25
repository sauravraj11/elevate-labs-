import java.util.*;

class Student {
    String id;
    String name;
    int age;
    String department;

    Student(String id, String name, int age, String department) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
    }
}

public class StudentCRUD {
    static HashMap<String, Student> students = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        while (true) {
            System.out.println("\n--- Student Record System ---");
            System.out.println("1. Create Student Record");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student Record");
            System.out.println("4. Delete Student Record");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1": createStudent(); break;
                case "2": viewStudents(); break;
                case "3": updateStudent(); break;
                case "4": deleteStudent(); break;
                case "5": System.out.println("Exiting program."); return;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void createStudent() {
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();
        if (students.containsKey(id)) {
            System.out.println("Student ID already exists.");
            return;
        }
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = Integer.parseInt(sc.nextLine());
        System.out.print("Enter Department: ");
        String dept = sc.nextLine();
        students.put(id, new Student(id, name, age, dept));
        System.out.println("Student record created.");
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        System.out.println("\nStudent Records:");
        for (Student s : students.values()) {
            System.out.println("ID: " + s.id + ", Name: " + s.name + ", Age: " + s.age + ", Department: " + s.department);
        }
    }

    static void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        String id = sc.nextLine();
        if (!students.containsKey(id)) {
            System.out.println("Student not found.");
            return;
        }
        System.out.print("Enter New Name: ");
        String name = sc.nextLine();
        System.out.print("Enter New Age: ");
        int age = Integer.parseInt(sc.nextLine());
        System.out.print("Enter New Department: ");
        String dept = sc.nextLine();
        students.put(id, new Student(id, name, age, dept));
        System.out.println("Student record updated.");
    }

    static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        String id = sc.nextLine();
        if (students.containsKey(id)) {
            students.remove(id);
            System.out.println("Student record deleted.");
        } else {
            System.out.println("Student not found.");
        }
    }
}


public class CLI_CRUD {
    public static void main(String[] args) {
        
    }
    
}
