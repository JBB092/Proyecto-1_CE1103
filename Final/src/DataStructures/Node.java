package DataStructures;

public class Node {
    /**
     * Represents a node in a doubly linked list.
     */
    int data;
    /**
     * Reference to the next node.
     */
    Node next;
    /**
     * Reference to the previous node.
     */
    Node prev;

    /**
     * Constructs a node with the given data and initializes next and prev nodes.
     * 
     * @param data The data to be stored in the node.
     */
    public Node(int data){
        this.data=data;
        this.next=null;
        this.prev=null;
    }

    /**
     * Gets the data stored in the node.
     * 
     * @return The data stored in the node.
     */
    public int getData(){
        return this.data;
    }

    /**
     * Gets the reference to the next node.
     * 
     * @return The reference to the next node.
     */
    public Node getNext() {
        return this.next;
    }

    /**
     * Gets the reference to the previous node.
     * 
     * @return The reference to the previous node.
     */
    public Node getPrev() {
        return this.prev;
    }

    /**
     * Sets the reference to the next node.
     * 
     * @param node The node to set as the next node.
     */
    public void setNext(Node node){
        this.next=node;
    }

    /**
     * Sets the reference to the previous node.
     * 
     * @param node The node to set as the previous node.
     */
    public void setPrev(Node node){
        this.prev=node;
    }
}