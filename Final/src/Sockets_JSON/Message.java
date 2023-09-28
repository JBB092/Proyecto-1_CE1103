package Sockets_JSON;

public class Message {
    private String sender;
    private String content;
    private boolean isNewConnection;  // Nuevo campo para indicar si es una nueva conexión
    private int clientId;
    private MessageType messageType;

    public Message() {
    }

    public Message(String sender, String content, boolean isNewConnection, int clientId) {
        this.sender = sender;
        this.content = content;
        this.isNewConnection = isNewConnection;
        this.clientId = clientId;
        this.messageType = MessageType.REGULAR_MESSAGE;
    }
    

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isNewConnection() {
        return isNewConnection;
    }

    public void setNewConnection(boolean newConnection) {
        isNewConnection = newConnection;
    }

    public MessageType getMessageType() {
        return messageType;
    }
    
    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
    

    @Override
    public String toString() {
        return "Message{" +
                "sender='" + sender + '\'' +
                ", content='" + content + '\'' +
                ", isNewConnection=" + isNewConnection +
                ", clientId=" + clientId +
                '}';
    }

    public int getClientId(){
        return clientId;
    }

    public enum MessageType{
        NEW_CLIENT,
        REGULAR_MESSAGE
    }
}