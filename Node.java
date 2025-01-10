class Node {
    int isbn;
    String title;
    String author;
    boolean isRed; // True if borrowed (Red), False if available (Black)
    Node left, right, parent;

    public Node(int isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.isRed = false; // Initially the book is available
        this.left = this.right = this.parent = null;
    }
}
