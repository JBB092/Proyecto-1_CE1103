package Sockets_JSON;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import DataStructures.Mesh;
import DataStructures.Queue;

/**
 * Represents a JSON server that handles client connections and message exchange in JSON format.
 * The JsonServer class represents a server that listens for client connections, assigns unique IDs to clients,
 * and exchanges messages with clients in JSON format.
 * 
 * @author José Barquero
 */
public class JsonServer {

    /**
     * List of PrintWriter objects for clients' output streams.
     */
    private static List<PrintWriter> clientWriters = new ArrayList<>();

    /**
     * The queue to store connected clients.
     */
    private static Queue clientQueue = new Queue();  // Queue for clients

    /**
     * Counter to assign unique client IDs.
     */
    private static int clientIdCounter = 1;  // Counter to assign unique IDs

    /**
     * The current state of the server.
     */
    private static ServerState serverState = ServerState.WAITING_FOR_CLIENTS;

    /**
     * The main method to start the JSON server.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started. Waiting for connections...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected from " + clientSocket.getInetAddress());

                // Assign a unique ID to the client
                int clientId = assignClientId();

                // Add the client to the queue
                clientQueue.enqueue(clientSocket, clientId);

                // Check if we can start sending messages
                if (serverState == ServerState.WAITING_FOR_CLIENTS && clientQueue.size() >= 2) {
                    serverState = ServerState.SENDING_MESSAGES;

                    // Send messages to all clients
                    synchronized (clientQueue) {
                        for (Socket client : clientQueue.getAllClients()) {
                            sendInitialMessage(client);
                        }
                    }
                }

                // Create a thread to handle communication with this client
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendInitialMessage(Socket clientSocket) throws IOException {
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        Message initialMessage = new Message("Server", "You can start sending messages.", true, -1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMessage = objectMapper.writeValueAsString(initialMessage);
        out.println(jsonMessage);
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private PrintWriter out;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            try {
                this.out = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String jsonMessage = in.readLine();

                ObjectMapper objectMapper = new ObjectMapper();
                Message message = objectMapper.readValue(jsonMessage, Message.class);

                System.out.println("Message received from the client: " + message);

                // If it's a new connection, send a message to all clients
                if (message.isNewConnection()) {
                    // Assign a unique ID to the client
                    int clientId = assignClientId();

                    // Add the client to the queue
                    clientQueue.enqueue(clientSocket, clientId);

                    // Send a message to all clients
                    synchronized (clientWriters) {
                        for (PrintWriter writer : clientWriters) {
                            if (writer != out) {
                                Message responseMessage = new Message("Server",
                                        "New client connected: " + message.getContent(), false, clientId);
                                responseMessage.setNewConnection(true);
                                String jsonResponse = objectMapper.writeValueAsString(responseMessage);
                                writer.println(jsonResponse);
                            }
                        }
                    }
                }

                if (message.getMessageType() == Message.MessageType.MESH_JSON) {
                    String meshJson = message.getContent();
                    ObjectMapper objectMapper2 = new ObjectMapper();
                    try {
                        Mesh mesh = objectMapper2.readValue(meshJson, Mesh.class);
                        System.out.println("Mesh received from the client: " + mesh);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static int assignClientId() {
        return clientIdCounter++;
    }

    private enum ServerState {
        WAITING_FOR_CLIENTS,
        SENDING_MESSAGES
    }
}
