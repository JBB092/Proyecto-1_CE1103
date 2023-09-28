package Serial;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import java.nio.charset.StandardCharsets;

/**
 * Clase que implementa la interfaz SerialPortDataListener para escuchar
 * eventos de datos disponibles en el puerto serie y procesarlos.
 * @author José Barquero
 */
public class SerialDataListener implements SerialPortDataListener {

    private ArduinoSerialReceiver parent;  // Referencia a la clase principal

    /**
     * Constructor de la clase SerialDataListener.
     *
     * @param parent La instancia de ArduinoSerialReceiver que actúa como clase principal.
     */
    public SerialDataListener(ArduinoSerialReceiver parent) {
        this.parent = parent;
    }

    /**
     * Obtiene los tipos de eventos que esta clase escucha, en este caso, solo
     * escucha eventos de datos disponibles en el puerto serie.
     *
     * @return El tipo de evento que se está escuchando.
     */
    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
    }

    /**
     * Método que se llama cuando se recibe un evento en el puerto serie.
     * Lee los datos disponibles, los convierte a una cadena y los procesa
     * en la clase principal.
     *
     * @param event El evento del puerto serie.
     */
    @Override
    public void serialEvent(SerialPortEvent event) {
        if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
            return;

        byte[] readBuffer = new byte[parent.getSerialPort().bytesAvailable()];
        int numBytes = parent.getSerialPort().readBytes(readBuffer, readBuffer.length);
        String data = new String(readBuffer, 0, numBytes, StandardCharsets.UTF_8);
        parent.processSerialData(data.trim());
    }
}