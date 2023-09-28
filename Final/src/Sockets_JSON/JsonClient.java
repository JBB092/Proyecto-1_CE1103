package Sockets_JSON;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class JsonClient {

    public static void main(String[] args) {
        try {
            // Conectar al servidor en el puerto 12345
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Conectado al servidor.");

            // Configurar streams de entrada y salida
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Crear un mensaje en formato JSON para indicar una nueva conexión
            Message message = new Message("Cliente", "Hola, servidor!", true, -1);  // El ID será asignado por el servidor
            message.setMessageType(Message.MessageType.NEW_CLIENT);  // Establece el tipo de mensaje
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonMessage = objectMapper.writeValueAsString(message);

            // Enviar el mensaje al servidor
            out.println(jsonMessage);

            // Leer la respuesta del servidor en formato JSON
            String jsonResponse = in.readLine();

            // Convertir el JSON a un objeto
            Message responseMessage = objectMapper.readValue(jsonResponse, Message.class);
            
            // Si es una nueva conexión, actualizar el ID asignado por el servidor
            if (responseMessage.isNewConnection() && responseMessage.getClientId() != -1) {
                System.out.println("Conectado con ID de cliente: " + responseMessage.getClientId());
            }

            // Cerrar conexión
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}