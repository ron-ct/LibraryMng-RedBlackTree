import java.util.*;

public class LibraryManager {
    private RedBlackTree library;
    private Map<Integer, User> users;

    public LibraryManager(RedBlackTree library) {
        this.library = library;
        this.users = new HashMap<>();
    }

    // Add a user
    public void addUser(int id, String name) {
        if (users.containsKey(id)) {
            System.out.println("Error: User with ID " + id + " already exists.");
            return;
        }
        users.put(id, new User(name, id));
        System.out.println("User added: " + name);
    }

    // Borrow a book
    public void borrowBook(int userId, int isbn) {
        User user = users.get(userId);
        if (user == null) {
            System.out.println("Error: User with ID " + userId + " not found.");
            return;
        }

        Node book = library.search(isbn);
        if (book == null) {
            System.out.println("Error: Book with ISBN " + isbn + " not found.");
            return;
        }

        if (book.isRed) {  // Book already borrowed
            System.out.println("Error: Book with ISBN " + isbn + " is already borrowed.");
            return;
        }

        user.borrowBook(book);
        book.isRed = true; // Mark the book as borrowed
        System.out.println(user.name + " borrowed " + book.title);
    }

    // Return a book
    public void returnBook(int userId, int isbn) {
        User user = users.get(userId);
        if (user == null) {
            System.out.println("Error: User with ID " + userId + " not found.");
            return;
        }

        Node book = library.search(isbn);
        if (book == null) {
            System.out.println("Error: Book with ISBN " + isbn + " not found.");
            return;
        }

        if (!user.hasBorrowed(book)) {
            System.out.println(user.name + " has not borrowed this book.");
            return;
        }

        user.returnBook(book);
        book.isRed = false; // Mark the book as available
        System.out.println(user.name + " returned " + book.title);
    }

    // Display borrowed books for a user
    public void displayUserBooks(int userId) {
        User user = users.get(userId);
        if (user == null) {
            System.out.println("Error: User with ID " + userId + " not found.");
            return;
        }

        System.out.println(user.name + " has borrowed the following books:");
        for (Node book : user.borrowedBooks) {
            System.out.println("ISBN: " + book.isbn + ", Title: " + book.title);
        }
    }

    // Display all books in the library
    public void displayAllBooks() {
        System.out.println("All books in the library:");
        library.inOrderTraversal(library.getRoot());
    }
}
