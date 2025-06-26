import java.util.*;

public class LibrarySystem {
    static class Book {
        private String id;
        private String title;
        private boolean isIssued;

        public Book(String id, String title) {
            this.id = id;
            this.title = title;
            this.isIssued = false;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public boolean isIssued() {
            return isIssued;
        }

        public void setIssued(boolean issued) {
            this.isIssued = issued;
        }
    }

    static class User {
        private String userId;
        private String name;

        public User(String userId, String name) {
            this.userId = userId;
            this.name = name;
        }

        public String getUserId() {
            return userId;
        }

        public String getName() {
            return name;
        }
    }

    static class Library {
        private List<Book> books = new ArrayList<>();

        public void addBook(Book book) {
            books.add(book);
        }

        public void issueBook(String bookId, User user) {
            for (Book book : books) {
                if (book.getId().equals(bookId)) {
                    if (!book.isIssued()) {
                        book.setIssued(true);
                        System.out.println(user.getName() + " issued \"" + book.getTitle() + "\"");
                    } else {
                        System.out.println("Book is already issued.");
                    }
                    return;
                }
            }
            System.out.println("Book not found.");
        }

        public void returnBook(String bookId, User user) {
            for (Book book : books) {
                if (book.getId().equals(bookId)) {
                    if (book.isIssued()) {
                        book.setIssued(false);
                        System.out.println(user.getName() + " returned \"" + book.getTitle() + "\"");
                    } else {
                        System.out.println("Book was not issued.");
                    }
                    return;
                }
            }
            System.out.println("Book not found.");
        }

        public void listBooks() {
            for (Book book : books) {
                System.out.println(book.getTitle() + " | " + (book.isIssued() ? "Issued" : "Available"));
            }
        }
    }

    public static void main(String[] args) {
        Library library = new Library();

        Book b1 = new Book("B001", "Java Programming");
        Book b2 = new Book("B002", "Data Structures");
        Book b3 = new Book("B003", "Operating Systems");

        User u1 = new User("U001", "Alice");
        User u2 = new User("U002", "Bob");

        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);

        library.listBooks();

        library.issueBook("B001", u1);
        library.issueBook("B001", u2);
        library.returnBook("B001", u1);
        library.issueBook("B001", u2);

        library.listBooks();
    }
}
