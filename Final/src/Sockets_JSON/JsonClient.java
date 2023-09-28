package Sockets_JSON;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Game.*;
import DataStructures.*;

/**
 * Represents a JSON client that connects to a server and exchanges messages in JSON format.
 * The JsonClient class represents a client that connects to a server using a socket and exchanges
 * messages with the server in JSON format.
 * 
 * @author José Barquero
 */
public class JsonClient {

    /**
     * The main method to start the JSON client.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        try {
            // Connect to the server at port 12345
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Connected to the server.");

            // Set up input and output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Create a message in JSON format to indicate a new connection
            Message message = new Message("Client", "Hello, server!", true, -1);  // The ID will be assigned by the server
            message.setMessageType(Message.MessageType.NEW_CLIENT);  // Set the message type
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonMessage = objectMapper.writeValueAsString(message);

            // Send the message to the server
            out.println(jsonMessage);

            // Read the server's response in JSON format
            String jsonResponse = in.readLine();

            // Convert the JSON to an object
            Message responseMessage = objectMapper.readValue(jsonResponse, Message.class);

            // If it's a new connection, update the ID assigned by the server
            if (responseMessage.isNewConnection() && responseMessage.getClientId() != -1) {
                System.out.println("Connected with client ID: " + responseMessage.getClientId());
            }

            // Create a message in JSON format with the mesh
            Mesh mesh = createMesh();  // Define your logic to create the Mesh object
            String meshJson = objectMapper.writeValueAsString(mesh);
            Message meshMessage = new Message("Client", meshJson, false, -1);
            meshMessage.setMessageType(Message.MessageType.MESH_JSON);
            String jsonMeshMessage = objectMapper.writeValueAsString(meshMessage);

            // Send the mesh message to the server
            out.println(jsonMeshMessage);

            // Close the connection
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a mesh object.
     * Here you should implement the logic to create a valid Mesh object.
     * Return a valid Mesh object.
     *
     * @return A valid Mesh object.
     */
    private static Mesh createMesh() {
        // Implement your logic to create a Mesh object here
        // Return a valid Mesh object
        Mesh mesh = new Mesh();
        // Add points, lines, boxes, etc. to the mesh as needed
        return mesh;
    }
}
