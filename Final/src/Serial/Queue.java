package Serial;

/**
 * Esta clase representa una cola implementada utilizando lista doblemente enlazada.
 * En una cola, los elementos se añaden al final y se eliminan del principio (FIFO - First-In-First-Out)
 * @author José Barquero
 */
public class Queue {
    private DoublyLinkedList doublyLinkedList; //La lista doblemente enlazada que representa la cola

    /**
     * Constructor para crear una nueva instancia de la cola.
     */
    public Queue() {
        doublyLinkedList = new DoublyLinkedList();
    }

    
    /** 
     * Añade un elemento al final de la cola.
     * 
     * @param data El elemento a añadir a la cola.
     */
    public void enqueue(int data) {
        doublyLinkedList.insertAtEnd(data);
    }

    /**
     * Elimina el primer elemento de la cola.
     * Si la cola está vacía, no se realiza ninguna acción.
     */
    public void dequeue() {
        if (!doublyLinkedList.isEmpty()) {
            doublyLinkedList.head = doublyLinkedList.head.next;
            if (doublyLinkedList.head != null) {
                doublyLinkedList.head.prev = null;
            } else {
                doublyLinkedList.last = null;
            }
            System.out.println("Primer elemento eliminado de la cola.");
        } else {
            System.out.println("La cola está vacía, no se puede eliminar un elemento.");
        }
    }

    
    /** 
     * Obtiene el tamaño actual de la cola.
     * 
     * @return int El número de elementos en la cola
     */
    public int size() {
        int count = 0;
        Node current = doublyLinkedList.head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }
    
    public static void main(String[] args) {
         Queue cola= new Queue();

         cola.enqueue(10);
         cola.enqueue(20);
         cola.enqueue(30);

         System.out.println("Tamaño de la cola: " + cola.size());

         cola.dequeue();

         System.out.println("Tamaño de la cola después de eliminar un elemento: "+ cola.size());
    }
}