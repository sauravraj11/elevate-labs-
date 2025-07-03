import java.sql.*;
import java.util.Scanner;

public class EmployeeApp {
    static final String DB_URL = "jdbc:mysql://localhost:3306/your_database"; // or "jdbc:postgresql://localhost:5432/your_database"
    static final String USER = "your_username";
    static final String PASS = "your_password";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            while (true) {
                System.out.println("\nEMPLOYEE DATABASE SYSTEM");
                System.out.println("1. Add Employee");
                System.out.println("2. View All Employees");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Choose option: ");
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Position: ");
                    String position = sc.nextLine();
                    System.out.print("Salary: ");
                    double salary = sc.nextDouble();
                    addEmployee(conn, name, position, salary);
                } else if (choice == 2) {
                    viewEmployees(conn);
                } else if (choice == 3) {
                    System.out.print("Enter ID to update: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("New Name: ");
                    String name = sc.nextLine();
                    System.out.print("New Position: ");
                    String position = sc.nextLine();
                    System.out.print("New Salary: ");
                    double salary = sc.nextDouble();
                    updateEmployee(conn, id, name, position, salary);
                } else if (choice == 4) {
                    System.out.print("Enter ID to delete: ");
                    int id = sc.nextInt();
                    deleteEmployee(conn, id);
                } else if (choice == 5) {
                    break;
                } else {
                    System.out.println("Invalid choice.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sc.close();
    }

    static void addEmployee(Connection conn, String name, String position, double salary) throws SQLException {
        String sql = "INSERT INTO employees (name, position, salary) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, position);
            stmt.setDouble(3, salary);
            stmt.executeUpdate();
            System.out.println("Employee added.");
        }
    }

    static void viewEmployees(Connection conn) throws SQLException {
        String sql = "SELECT * FROM employees";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\nEmployee List:");
            while (rs.next()) {
                System.out.printf("ID: %d | Name: %s | Position: %s | Salary: %.2f%n",
                        rs.getInt("id"), rs.getString("name"), rs.getString("position"), rs.getDouble("salary"));
            }
        }
    }

    static void updateEmployee(Connection conn, int id, String name, String position, double salary) throws SQLException {
        String sql = "UPDATE employees SET name=?, position=?, salary=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, position);
            stmt.setDouble(3, salary);
            stmt.setInt(4, id);
            int updated = stmt.executeUpdate();
            System.out.println(updated > 0 ? "Employee updated." : "Employee not found.");
        }
    }

    static void deleteEmployee(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM employees WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int deleted = stmt.executeUpdate();
            System.out.println(deleted > 0 ? "Employee deleted." : "Employee not found.");
        }
    }
}
