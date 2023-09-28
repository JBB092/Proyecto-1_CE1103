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

public class JsonServer {

    private static List<PrintWriter> clientWriters = new ArrayList<>();
    private static Queue clientQueue = new Queue();  // Cola para los clientes

    private static int clientIdCounter = 1;  // Contador para asignar IDs únicos

    private static ServerState serverState = ServerState.WAITING_FOR_CLIENTS;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Servidor iniciado. Esperando conexiones...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress());

                // Asignar un ID único al cliente
                int clientId = assignClientId();

                // Añadir el cliente a la cola
                clientQueue.enqueue(clientSocket, clientId);

                // Verificar si podemos comenzar a enviar mensajes
                if (serverState == ServerState.WAITING_FOR_CLIENTS && clientQueue.size() >= 2) {
                    serverState = ServerState.SENDING_MESSAGES;

                    // Enviar mensajes a todos los clientes
                    synchronized (clientQueue) {
                        for (Socket client : clientQueue.getAllClients()) {
                            sendInitialMessage(client);
                        }
                    }
                }

                // Crear un hilo para manejar la comunicación con este cliente
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendInitialMessage(Socket clientSocket) throws IOException {
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        Message initialMessage = new Message("Servidor", "Puedes comenzar a enviar mensajes.", true, -1);
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

                System.out.println("Mensaje recibido del cliente: " + message);

                // Si es una nueva conexión, enviar mensaje a todos los clientes
                if (message.isNewConnection()) {
                    // Asignar un ID único al cliente
                    int clientId = assignClientId();

                    // Agregar el cliente a la cola
                    clientQueue.enqueue(clientSocket, clientId);

                    // Enviar mensaje a todos los clientes
                    synchronized (clientWriters) {
                        for (PrintWriter writer : clientWriters) {
                            if (writer != out) {
                                Message responseMessage = new Message("Servidor", "Nuevo cliente conectado: " + message.getContent(), false, clientId);
                                responseMessage.setNewConnection(true);
                                String jsonResponse = objectMapper.writeValueAsString(responseMessage);
                                writer.println(jsonResponse);
                            }
                        }
                    }
                }

               if (message.getMessageType() == Message.MessageType.MESH_JSON) {
                    String meshJson=message.getContent();
                    ObjectMapper objectMapper2= new ObjectMapper();
                    try {
                        Mesh mesh=objectMapper2.readValue(meshJson, Mesh.class);
                        System.out.println("Mesh recibido del cliente: "+mesh);
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