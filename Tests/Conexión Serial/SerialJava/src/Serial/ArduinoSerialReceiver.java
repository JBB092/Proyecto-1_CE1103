package Serial;
import com.fazecast.jSerialComm.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

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
            }
            
            try {
                Thread.sleep(100); // Espera un breve período para evitar un ciclo de lectura muy rápido
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}