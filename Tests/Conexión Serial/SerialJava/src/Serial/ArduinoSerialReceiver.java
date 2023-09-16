package Serial;
import com.fazecast.jSerialComm.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

class Node{
    int data;
    Node next;
    Node prev;

    public Node(int data){
        this.data=data;
        this.next=null;
        this.prev=null;
    }

    public int getData(){
        return this.data;
    }

    public Node getNext() {
        return this.next;
    }

    public Node getPrev() {
        return this.prev;
    }

    public void setNext(Node node){
        this.next=node;
    }

    public void setPrev(Node node){
        this.prev=node;
    }
    
}

class DoublyLinkedList{
    public Node head;
    public Node last;
    public Node current;

    public DoublyLinkedList(){
        this.head=null;
        this.last=null;
        this.current=null;
    }

    public boolean isEmpty(){
        if(this.head==null && this.last==null){
            return true;
        }
        else{
            return false;
        }
    }

    public void insertAtEnd(int data){
        Node newNode= new Node(data);
        
        if(isEmpty()){
            this.head=newNode;
            this.last=newNode;
            this.current=this.head;
        }
        else{
            Node lastNode=this.last;
            lastNode.setNext(newNode);
            newNode.setPrev(lastNode);
            this.last=newNode;
        }
    }

    public void displayCurrent(){
        if(current!=null){
            System.out.println("Valor del nodo actual (current): "+current.getData());
        }
        else{
            System.out.println("No hay nodo actual (current es null).");
        }
    }

    public void moveCurrentForward() {
        if (current != null && current.getNext() != null) {
            current = current.getNext();
            System.out.println("Current se ha movido hacia adelante.");
        } else {
            System.out.println("Current ya está en el último nodo.");
        }
    }

    public void moveCurrentBackward() {
        if (current != null && current.getPrev() != null) {
            current = current.getPrev();
            System.out.println("Current se ha movido hacia atrás.");
        } else {
            System.out.println("Current ya está en el primer nodo.");
        }
    }
}

/* ------------------------------------------------------------------------------------------------------------------- */
/**
 Clase principal para la obtención de los datos enviados al puerto serial
 @author José Barquero
 */
public class ArduinoSerialReceiver {
    /**
     Función principal encargada de la lógica sobre los puertos seriales
     @param args
     */
    public static void main(String[] args) {

        DoublyLinkedList list = new DoublyLinkedList();

        list.insertAtEnd(1);
        list.insertAtEnd(2);
        list.insertAtEnd(3);

        SerialPort sp = SerialPort.getCommPort("COM3"); //Se obtiene el puerto a escoger
    
        // Configura la velocidad de baudios y otros parámetros según los de Arduino.
        sp.setBaudRate(9600); //Se colocan los baudios a utilizar según los de Arduino
        
        // Abre el puerto serial
        if (sp.openPort()) {
            System.out.println("Puerto serial abierto correctamente."); //Verificación del puerto serial abierto
        } else {
            System.err.println("Error al abrir el puerto serial."); //Mensaje de error por si el puerto serial no logró abrirse adecuadamente
            return;
        }
        
        // Lee datos del puerto serial y muestra los resultados.
        while (true) {
            if (sp.bytesAvailable() > 0) { //Se verifica que hayan bytes disponibles para leer en el puerto
                byte[] readBuffer = new byte[sp.bytesAvailable()]; //Se crea un buffer el cual permite tener un control de los bytes que son recibidos del arduino
                int bytesRead = sp.readBytes(readBuffer, readBuffer.length); //Se leen los datos recibidos
                String data = new String(readBuffer, StandardCharsets.UTF_8); //Se "traducen" los bytes recibidos para poder ser analizados
                System.out.println("Datos recibidos desde Arduino: " + data); //Impresión de los datos obtenidos para verificar su correcto funcionamiento
                
                if(data.trim().equalsIgnoreCase("r")){
                    list.displayCurrent();
                }
                if(data.trim().equalsIgnoreCase("+x")){
                    list.moveCurrentForward();
                }
                if(data.trim().equalsIgnoreCase("-x")){
                    list.moveCurrentBackward();
                }
            }
            
            try {
                Thread.sleep(100); // Espera un breve período para evitar un ciclo de lectura muy rápido
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}