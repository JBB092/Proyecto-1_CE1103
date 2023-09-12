import com.fazecast.jSerialComm.*;
import java.util.Scanner;
import java.io.IOException;

public class Arduino {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        
        SerialPort sp = SerialPort.getCommPort("COM3");
        sp.setComPortParameters(9600,8,1,0);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING,0,0);
        //------------------------------------------------------------------------------------------
        if(!sp.openPort()){
            System.out.println("\n COM port NOT available \n");
            return;
        }
        //------------------------------------------------------------------------------------------
        Scanner input =new Scanner(System.in);
        while (true) {
            System.out.println("\n Enter a number of LED blinks (0 to exit): ");
            Integer blinks = input.nextInt();
            if(blinks==0){
                break;
            }
            
            Thread.sleep(1500);
            sp.getOutputStream().write(blinks.byteValue());
        }
        input.close();
    }
}
