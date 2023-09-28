package DataStructures;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a queue using a doubly linked list to manage client sockets and IDs.
 * 
 * The Queue class represents a queue using a doubly linked list to manage client sockets and IDs.
 * It allows adding (enqueue) and removing (dequeue) elements, as well as checking the queue's status.
 * 
 * @author José Barquero
 */
public class Queue {
    /**
     * The doubly linked list that represents the queue.
     */
    private DoublyLinkedList doublyLinkedList;

    /**
     * List of client sockets.
     */
    private List<Socket> clientSockets;

    /**
     * Constructs an empty queue.
     */
    public Queue() {
        doublyLinkedList = new DoublyLinkedList();
        clientSockets = new ArrayList<>();
    }

    /**
     * Adds a socket and its associated client ID to the end of the queue.
     *
     * @param socket The socket to be added.
     * @param clientId The ID of the client associated with the socket.
     */
    public void enqueue(Socket socket, int clientId) {
        clientSockets.add(socket);
        doublyLinkedList.insertAtEnd(clientId);
    }

    /**
     * Removes and returns the client ID from the front of the queue.
     * Also removes the associated socket (FIFO - First In, First Out).
     *
     * @return The client ID removed from the queue.
     */
    public int dequeue() {
        if (!doublyLinkedList.isEmpty()) {
            int removedClientId = doublyLinkedList.removeFromFront();
            clientSockets.remove(0); // Remove the socket from the front (FIFO)
            System.out.println("First element removed from the queue. Client disconnected: " + removedClientId);
            return removedClientId;
        } else {
            System.out.println("The queue is empty, cannot remove an element.");
            return -1; // Return a value indicating removal failure
        }
    }

    /**
     * Checks if the queue is empty.
     *
     * @return True if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return doublyLinkedList.isEmpty();
    }

    /**
     * Gets the size of the queue.
     *
     * @return The number of elements in the queue.
     */
    public int size() {
        return doublyLinkedList.size();
    }

    /**
     * Gets a list of all client sockets in the queue.
     *
     * @return A list of client sockets.
     */
    public List<Socket> getAllClients() {
        return clientSockets;
    }
}
