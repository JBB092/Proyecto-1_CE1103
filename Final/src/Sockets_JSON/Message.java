package Sockets_JSON;

/**
 * Represents a message exchanged between client and server in JSON format.
 * The Message class represents a message, including the sender, content, whether it's a new connection,
 * the client ID, and the message type.
 * 
 * @author José Barquero 
 */
public class Message {
    /**
     * The sender of the message.
     */
    private String sender;

    /**
     * The content of the message.
     */
    private String content;

    /**
     * Indicates whether it's a new connection.
     */
    private boolean isNewConnection;

    /**
     * The client ID associated with the message.
     */
    private int clientId;

    /**
     * The type of the message.
     */
    private MessageType messageType;

    /**
     * Constructs an empty Message object.
     */
    public Message() {
    }

    /**
     * Constructs a Message object with specified sender, content, new connection status, and client ID.
     *
     * @param sender         The sender of the message.
     * @param content        The content of the message.
     * @param isNewConnection Indicates whether it's a new connection.
     * @param clientId       The client ID associated with the message.
     */
    public Message(String sender, String content, boolean isNewConnection, int clientId) {
        this.sender = sender;
        this.content = content;
        this.isNewConnection = isNewConnection;
        this.clientId = clientId;
        this.messageType = MessageType.REGULAR_MESSAGE;
    }

    /**
     * Gets the sender of the message.
     *
     * @return The sender of the message.
     */
    public String getSender() {
        return sender;
    }

    /**
     * Sets the sender of the message.
     *
     * @param sender The sender of the message.
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * Gets the content of the message.
     *
     * @return The content of the message.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the message.
     *
     * @param content The content of the message.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Checks if the message represents a new connection.
     *
     * @return True if the message represents a new connection, false otherwise.
     */
    public boolean isNewConnection() {
        return isNewConnection;
    }

    /**
     * Sets whether the message represents a new connection.
     *
     * @param newConnection True if the message represents a new connection, false otherwise.
     */
    public void setNewConnection(boolean newConnection) {
        isNewConnection = newConnection;
    }

    /**
     * Gets the type of the message.
     *
     * @return The type of the message.
     */
    public MessageType getMessageType() {
        return messageType;
    }

    /**
     * Sets the type of the message.
     *
     * @param messageType The type of the message.
     */
    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    /**
     * Returns a string representation of the message.
     *
     * @return A string representation of the message.
     */
    @Override
    public String toString() {
        return "Message{" +
                "sender='" + sender + '\'' +
                ", content='" + content + '\'' +
                ", isNewConnection=" + isNewConnection +
                ", clientId=" + clientId +
                '}';
    }

    /**
     * Gets the client ID associated with the message.
     *
     * @return The client ID associated with the message.
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * Represents the types of messages.
     */
    public enum MessageType {
        NEW_CLIENT,
        REGULAR_MESSAGE,
        MESH_JSON
    }
}
