package DataStructures;

public class Node {
    int data;
    Node next;
    Node prev;

    public Node(int data){
        this.data=data;
        this.next=null;
        this.prev=null;
    }

    public int getData(){
        return this.data;
    }

    public Node getNext() {
        return this.next;
    }

    public Node getPrev() {
        return this.prev;
    }

    public void setNext(Node node){
        this.next=node;
    }

    public void setPrev(Node node){
        this.prev=node;
    }
}
