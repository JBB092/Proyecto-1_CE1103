package DataStructures;

/**
 * Represents a doubly linked list.
 *
 * This class provides functionality to work with a doubly linked list, including insertion, traversal, and removal operations.
 *
 * @author José Barquero
 */
public class DoublyLinkedList {
    /**
     * Head of the doubly linked list.
     */
    public Node head;

    /**
     * Last node of the doubly linked list.
     */
    public Node last;

    /**
     * Current node in the doubly linked list.
     */
    public Node current;

    /**
     * Constructs an empty doubly linked list.
     */
    public DoublyLinkedList() {
        this.head = null;
        this.last = null;
        this.current = null;
    }

    /**
     * Checks if the doubly linked list is empty.
     *
     * @return True if the doubly linked list is empty, false otherwise.
     */
    public boolean isEmpty() {
        if (this.head == null && this.last == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Inserts a new node with the given data at the end of the doubly linked list.
     *
     * @param data The data to be inserted.
     */
    public void insertAtEnd(int data) {
        Node newNode = new Node(data);

        if (isEmpty()) {
            this.head = newNode;
            this.last = newNode;
            this.current = this.head;
        } else {
            Node lastNode = this.last;
            lastNode.setNext(newNode);
            newNode.setPrev(lastNode);
            this.last = newNode;
        }
    }

    /**
     * Displays the data of the current node.
     */
    public void displayCurrent() {
        if (current != null) {
            System.out.println("Valor del nodo actual (current): " + current.getData());
        } else {
            System.out.println("No hay nodo actual (current es null).");
        }
    }

    /**
     * Moves the current node forward.
     * If the current node is the last node, it remains unchanged.
     */
    public void moveCurrentForward() {
        if (current != null && current.getNext() != null) {
            current = current.getNext();
            System.out.println("Current se ha movido hacia adelante.");
        } else {
            System.out.println("Current ya está en el último nodo.");
        }
    }

    /**
     * Moves the current node backward.
     * If the current node is the first node, it remains unchanged.
     */
    public void moveCurrentBackward() {
        if (current != null && current.getPrev() != null) {
            current = current.getPrev();
            System.out.println("Current se ha movido hacia atrás.");
        } else {
            System.out.println("Current ya está en el primer nodo.");
        }
    }

    /**
     * Removes and returns the data from the front of the doubly linked list.
     *
     * @return The data from the front of the list.
     * @throws RuntimeException if the list is empty.
     */
    public int removeFromFront() {
        if (isEmpty()) {
            throw new RuntimeException("La lista está vacía.");
        }

        int data = head.getData();
        head = head.getNext();

        if (head != null) {
            head.setPrev(null);
        } else {
            last = null;
        }

        return data;
    }

    /**
     * Returns the size of the doubly linked list.
     *
     * @return The number of nodes in the list.
     */
    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }
}
