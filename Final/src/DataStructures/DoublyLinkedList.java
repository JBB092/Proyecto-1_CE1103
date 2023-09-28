package DataStructures;

public class DoublyLinkedList {
    public Node head;
    public Node last;
    public Node current;

    public DoublyLinkedList(){
        this.head=null;
        this.last=null;
        this.current=null;
    }

    public boolean isEmpty(){
        if(this.head==null && this.last==null){
            return true;
        }
        else{
            return false;
        }
    }

    public void insertAtEnd(int data){
        Node newNode= new Node(data);
        
        if(isEmpty()){
            this.head=newNode;
            this.last=newNode;
            this.current=this.head;
        }
        else{
            Node lastNode=this.last;
            lastNode.setNext(newNode);
            newNode.setPrev(lastNode);
            this.last=newNode;
        }
    }

    public void displayCurrent(){
        if(current!=null){
            System.out.println("Valor del nodo actual (current): "+current.getData());
        }
        else{
            System.out.println("No hay nodo actual (current es null).");
        }
    }

    public void moveCurrentForward() {
        if (current != null && current.getNext() != null) {
            current = current.getNext();
            System.out.println("Current se ha movido hacia adelante.");
        } else {
            System.out.println("Current ya está en el último nodo.");
        }
    }

    public void moveCurrentBackward() {
        if (current != null && current.getPrev() != null) {
            current = current.getPrev();
            System.out.println("Current se ha movido hacia atrás.");
        } else {
            System.out.println("Current ya está en el primer nodo.");
        }
    }
}
