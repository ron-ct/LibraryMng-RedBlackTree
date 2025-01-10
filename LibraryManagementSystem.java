public class LibraryManagementSystem {
    public static void main(String[] args) {
        RedBlackTree library = new RedBlackTree();

        // Insert books
        library.insert(12345, "Java Programming", "James Gosling");
        library.insert(23456, "Introduction to Algorithms", "Cormen");
        library.insert(34567, "Clean Code", "Robert Martin");
        library.insert(45678, "Effective Java", "Joshua Bloch");

        // Create LibraryManager and pass the RedBlackTree instance
        LibraryManager manager = new LibraryManager(library);
        
        // Display all books
        manager.displayAllBooks();

        // Now you can perform operations
        manager.addUser(1, "Alice");
        manager.borrowBook(1, 12345);
        manager.displayUserBooks(1);

        // Returning a book
        manager.returnBook(1, 12345);
        manager.displayUserBooks(1);
    }
}
