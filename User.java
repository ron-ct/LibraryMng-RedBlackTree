import java.util.*;

public class User {
    String name;
    int id;
    List<Node> borrowedBooks;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
        this.borrowedBooks = new ArrayList<>();
    }

    // Borrow a book
    public void borrowBook(Node book) {
        borrowedBooks.add(book);
    }

    // Return a book
    public void returnBook(Node book) {
        borrowedBooks.remove(book);
    }

    // Check if the user has borrowed a book
    public boolean hasBorrowed(Node book) {
        return borrowedBooks.contains(book);
    }
}
