public class LibraryManagementSystem {
    public static void main(String[] args) {
        RedBlackTree library = new RedBlackTree();

        // Insert books
        library.insert(12345, "Java Programming", "James Gosling");
        library.insert(23456, "Introduction to Algorithms", "Cormen");
        library.insert(34567, "Clean Code", "Robert Martin");
        library.insert(45678, "Effective Java", "Joshua Bloch");

        // Display all books
        System.out.println("Books in the library:");
        library.inOrderTraversal(library.getRoot());


        // Search for a book
        int searchISBN = 23456;
        Node book = library.search(searchISBN);
        if (book != null) {
            System.out.println("\nBook found: ISBN: " + book.isbn + ", Title: " + book.title + ", Author: " + book.author);
        } else {
            System.out.println("\nBook with ISBN " + searchISBN + " not found.");
        }

        //delete a book
        library.deleteNode(12345);
        library.inOrderTraversal(library.getRoot());
    }
}