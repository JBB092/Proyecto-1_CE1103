package Sockets_JSON;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class JsonServer {

    public static void main(String[] args) {
        try {
            // Crear el servidor en el puerto 12345
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Servidor iniciado. Esperando conexiones...");

            while (true) {
                // Esperar a que un cliente se conecte
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress());

                // Crear un hilo para manejar la comunicación con este cliente
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                // Configurar streams de entrada y salida
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Recibir el mensaje en formato JSON desde el cliente
                String jsonMessage = in.readLine();

                // Convertir el JSON a un objeto
                ObjectMapper objectMapper = new ObjectMapper();
                Message message = objectMapper.readValue(jsonMessage, Message.class);

                System.out.println("Mensaje recibido del cliente: " + message);

                // Enviar una respuesta al cliente
                Message responseMessage = new Message("Servidor", "Respuesta recibida");
                String jsonResponse = objectMapper.writeValueAsString(responseMessage);
                out.println(jsonResponse);

                // Cerrar conexión con este cliente
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}