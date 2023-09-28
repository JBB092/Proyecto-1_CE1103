package Serial;

import com.fazecast.jSerialComm.SerialPort;

import java.nio.charset.StandardCharsets;

import DataStructures.*;
import Game.*;

/**
 * Clase que representa un receptor para recibir datos desde un Arduino a través de un puerto serie.
 * @author José Barquero
 */
public class ArduinoSerialReceiver {
    private SerialPort serialPort;
    private DoublyLinkedList list;
    private GameBoard miGameBoard;
    private int currentPosition;

    /**
     * Constructor de la clase ArduinoSerialReceiver.
     * Inicializa la lista enlazada, el tablero de juego y la posición actual.
     * Configura el puerto serial y agrega un oyente para los datos seriales.
     */
    public ArduinoSerialReceiver() {
        list = new DoublyLinkedList();
        miGameBoard = new GameBoard();
        currentPosition = 0;

        // Insertar algunos elementos en la lista para demostrar su funcionalidad
        list.insertAtEnd(1);
        list.insertAtEnd(2);
        list.insertAtEnd(3);

        // Obtener el puerto serial con el que se va a comunicar
        serialPort = SerialPort.getCommPort("COM3");
        serialPort.setBaudRate(9600);

        // Agregar el oyente de datos seriales
        serialPort.addDataListener(new SerialDataListener(this));

        // Abre el puerto serial
        if (serialPort.openPort()) {
            System.out.println("Puerto serial abierto correctamente.");
        } else {
            System.err.println("Error al abrir el puerto serial.");
            return;
        }
    }

    /**
     * Obtiene el puerto serial utilizado para la comunicación.
     *
     * @return El puerto serial utilizado para la comunicación.
     */
    public SerialPort getSerialPort() {
        return serialPort;
    }

    /**
     * Procesa los datos seriales recibidos desde Arduino.
     *
     * @param data Los datos recibidos desde Arduino.
     */
    public void processSerialData(String data) {
        System.out.println("Datos recibidos desde Arduino: " + data);

        // Realizar acciones basadas en los datos recibidos
        if (data.equalsIgnoreCase("r")) {
            list.displayCurrent();
            currentPosition++;
            System.out.println("Movido hacia la derecha a la posición: " + currentPosition);
        } else if (data.equalsIgnoreCase("+x")) {
            list.moveCurrentForward();
            GameBoard.movePositionRight(miGameBoard);
        } else if (data.equalsIgnoreCase("-x")) {
            list.moveCurrentBackward();
        }
    }

    /**
     * Método principal para iniciar la recepción de datos seriales desde Arduino.
     *
     * @param args Los argumentos de la línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args) {
        new ArduinoSerialReceiver();

        // Mantener el programa en ejecución
        while (true) {
            // Puedes realizar otras tareas aquí si es necesario
        }
    }
}