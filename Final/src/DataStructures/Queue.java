package DataStructures;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Queue {
    private DoublyLinkedList doublyLinkedList; // La lista doblemente enlazada que representa la cola
    private List<Socket> clientSockets; // Lista de sockets de clientes

    public Queue() {
        doublyLinkedList = new DoublyLinkedList();
        clientSockets = new ArrayList<>();
    }

    public void enqueue(Socket socket, int clientId) {
        clientSockets.add(socket);
        doublyLinkedList.insertAtEnd(clientId);
    }

    public int dequeue() {
        if (!doublyLinkedList.isEmpty()) {
            int removedClientId = doublyLinkedList.removeFromFront();
            clientSockets.remove(0); // Remove the socket from the front (FIFO)
            System.out.println("Primer elemento eliminado de la cola. Cliente desconectado: " + removedClientId);
            return removedClientId;
        } else {
            System.out.println("La cola está vacía, no se puede eliminar un elemento.");
            return -1; // Devolvemos un valor que indique que no se pudo eliminar
        }
    }

    public boolean isEmpty() {
        return doublyLinkedList.isEmpty();
    }

    public int size() {
        return doublyLinkedList.size();
    }

    public List<Socket> getAllClients() {
        return clientSockets;
    }
}
