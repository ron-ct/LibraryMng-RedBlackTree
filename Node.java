class Node {
    int isbn; // ISBN number (used for sorting)
    String title; 
    String author;
    boolean isRed;
    Node left, right, parent;

    public Node(int isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.isRed = true; // New nodes are red initially
    }
}