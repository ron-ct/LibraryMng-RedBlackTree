class RedBlackTree {
    private Node root;
    private final Node NoChildren; //All leaves (NIL nodes) are black. NIL nodes are used as the sentinel nodes-> representing the absence of children.

    public RedBlackTree() {
        NoChildren = new Node(0, "", ""); // Initialize NoChildren
        NoChildren.isRed = false;
        root = NoChildren;
    }

    // Rotate left
    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != NoChildren) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    // Rotate right
    private void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != NoChildren) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    // Fix the Red-Black Tree after insertion
    private void fixInsert(Node k) {
        while (k.parent != null && k.parent.isRed) {
            if (k.parent == k.parent.parent.left) {
                Node uncle = k.parent.parent.right;
                if (uncle.isRed) { // Case 1
                    k.parent.isRed = false;
                    uncle.isRed = false;
                    k.parent.parent.isRed = true;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) { // Case 2
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.isRed = false; // Case 3
                    k.parent.parent.isRed = true;
                    rightRotate(k.parent.parent);
                }
            } else {
                Node uncle = k.parent.parent.left;
                if (uncle.isRed) { // Mirror case 1
                    k.parent.isRed = false;
                    uncle.isRed = false;
                    k.parent.parent.isRed = true;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) { // Mirror case 2
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.isRed = false; // Mirror case 3
                    k.parent.parent.isRed = true;
                    leftRotate(k.parent.parent);
                }
            }
        }
        root.isRed = false;
    }

    // Insert a new book
    public void insert(int isbn, String title, String author) {
        Node newNode = new Node(isbn, title, author);
        newNode.left = NoChildren;
        newNode.right = NoChildren;

        Node parent = null;
        Node current = root;

        while (current != NoChildren) {
            parent = current;
            if (newNode.isbn < current.isbn) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        newNode.parent = parent;
        if (parent == null) {
            root = newNode;
        } else if (newNode.isbn < parent.isbn) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        fixInsert(newNode);
    }

    // Search for a book by ISBN
    public Node search(int isbn) {
        Node current = root;
        while (current != NoChildren) {
            if (isbn == current.isbn) {
                return current;
            } else if (isbn < current.isbn) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null; // Not found
    }

    // In-order traversal to display books
    public void inOrderTraversal(Node node) {
        if (node != NoChildren) {
            inOrderTraversal(node.left);
            System.out.println("ISBN: " + node.isbn + ", Title: " + node.title + ", Author: " + node.author);
            inOrderTraversal(node.right);
        }
    }

    public Node getRoot() {
        return root;
    }
}