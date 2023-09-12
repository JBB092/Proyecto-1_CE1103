import com.fazecast.jSerialComm.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ArduinoSerialReceiver {
    public static void main(String[] args) {
        SerialPort sp = SerialPort.getCommPort("COM3");
    
        // Configura la velocidad de baudios y otros parámetros según los de Arduino.
        sp.setBaudRate(9600); // Asegúrate de que coincida con la velocidad configurada en Arduino.
        
        // Abre el puerto serial
        if (sp.openPort()) {
            System.out.println("Puerto serial abierto correctamente.");
        } else {
            System.err.println("Error al abrir el puerto serial.");
            return;
        }
        
        // Lee datos del puerto serial y muestra los resultados.
        while (true) {
            if (sp.bytesAvailable() > 0) {
                byte[] readBuffer = new byte[sp.bytesAvailable()];
                int bytesRead = sp.readBytes(readBuffer, readBuffer.length);
                String data = new String(readBuffer, StandardCharsets.UTF_8); // Usar UTF-8 u otra codificación adecuada
                System.out.println("Datos recibidos desde Arduino: " + data);
            }
            
            try {
                Thread.sleep(100); // Espera un breve período para evitar un ciclo de lectura muy rápido
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}