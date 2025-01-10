import java.util.*;

public class RedBlackTree {
    private Node root;
    private Node TNULL; // Sentinel node

    public RedBlackTree() {
        TNULL = new Node(0, "", ""); // Sentinel node
        TNULL.isRed = false; // TNULL is always black
        root = TNULL;
    }

    // Insert a new book
    public void insert(int isbn, String title, String author) {
        Node node = new Node(isbn, title, author);
        node.parent = null;
        node.left = TNULL;
        node.right = TNULL;

        Node y = null;
        Node x = root;

        while (x != TNULL) {
            y = x;
            if (node.isbn < x.isbn)
                x = x.left;
            else
                x = x.right;
        }

        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.isbn < y.isbn) {
            y.left = node;
        } else {
            y.right = node;
        }

        if (node.parent == null) {
            node.isRed = false;
            return;
        }

        if (node.parent.parent == null) {
            return;
        }

        fixInsert(node);
    }

    // Fix Red-Black Tree after insert
    private void fixInsert(Node k) {
        Node u;
        while (k.parent.isRed) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left;
                if (u.isRed) {
                    u.isRed = false;
                    k.parent.isRed = false;
                    k.parent.parent.isRed = true;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.isRed = false;
                    k.parent.parent.isRed = true;
                    leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right;
                if (u.isRed) {
                    u.isRed = false;
                    k.parent.isRed = false;
                    k.parent.parent.isRed = true;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.isRed = false;
                    k.parent.parent.isRed = true;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.isRed = false;
    }

    // Rotate the node to the left
    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != TNULL) {
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

    // Rotate the node to the right
    private void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != TNULL) {
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

    // Search for a book by ISBN
    public Node search(int isbn) {
        Node current = root;
        while (current != TNULL) {
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

    // In-order traversal
    public void inOrderTraversal(Node node) {
        if (node != TNULL) {
            inOrderTraversal(node.left);
            System.out.println("ISBN: " + node.isbn + ", Title: " + node.title + ", Author: " + node.author);
            inOrderTraversal(node.right);
        }
    }

    // Get the root node
    public Node getRoot() {
        return root;
    }
}
