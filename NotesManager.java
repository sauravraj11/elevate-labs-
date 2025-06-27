import java.io.*;
import java.util.*;

public class NotesManager {
    static final String FILE_NAME = "notes.txt";
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Add Note\n2. View Notes\n3. Delete All Notes\n4. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addNote();
                case 2 -> viewNotes();
                case 3 -> deleteNotes();
                case 4 -> System.exit(0);
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void addNote() {
        System.out.print("Enter your note: ");
        String note = scanner.nextLine();
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write(note + "\n");
            System.out.println("Note added.");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

    static void viewNotes() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No notes found.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("\nYour Notes:");
            while ((line = br.readLine()) != null) {
                System.out.println("- " + line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    static void deleteNotes() {
        try {
            new FileWriter(FILE_NAME, false).close();
            System.out.println("All notes deleted.");
        } catch (IOException e) {
            System.out.println("Error deleting notes.");
        }
    }
}
