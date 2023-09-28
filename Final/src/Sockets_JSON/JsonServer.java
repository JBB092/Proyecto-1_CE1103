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

import DataStructures.Queue;

public class JsonServer {

    private static List<PrintWriter> clientWriters = new ArrayList<>();
    private static Queue clientQueue = new Queue();  // Cola para los clientes

    private static int clientIdCounter = 1;  // Contador para asignar IDs únicos


    public static void main(String[] args) {
        try {
            // Crear el servidor en el puerto 12345
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Servidor iniciado. Esperando conexiones...");

            while (true) {
                // Esperar a que un cliente se conecte
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress());

                // Configurar streams de entrada y salida
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Agregar el escritor del cliente a la lista
                synchronized (clientWriters) {
                    clientWriters.add(out);
                }

                // Crear un hilo para manejar la comunicación con este cliente
                Thread clientThread = new Thread(new ClientHandler(clientSocket, out));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private PrintWriter out;

        public ClientHandler(Socket clientSocket, PrintWriter out) {
            this.clientSocket = clientSocket;
            this.out = out;
        }

        @Override
        public void run() {
            try {
                // Recibir el mensaje en formato JSON desde el cliente
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String jsonMessage = in.readLine();

                // Convertir el JSON a un objeto
                ObjectMapper objectMapper = new ObjectMapper();
                Message message = objectMapper.readValue(jsonMessage, Message.class);

                System.out.println("Mensaje recibido del cliente: " + message);

                // Si es una nueva conexión, enviar mensaje a todos los clientes
                if (message.isNewConnection()) {
                    // Asignar un ID único al cliente
                    int clientId = assignClientId();

                    // Agregar el cliente a la cola
                    clientQueue.enqueue(clientId);

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

                // Código restante
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private int assignClientId(){
            return clientIdCounter++;
        }
    }
}