import java.io.IOException;
import com.fazecast.jSerialComm.SerialPort;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class JavaFX_Arduino extends Application {

    public static SerialPort sp= SerialPort.getCommPort("COM3");

    @FXML Button btnEnter, btnQuit;
    @FXML TextField txtBlinks;

    public void handleEnter(ActionEvent e) throws IOException{
        String blinksStr =txtBlinks.getText();
        Integer blinks = Integer.parseInt(blinksStr);
        sp.getOutputStream().write(blinks.byteValue());
    }

    public void handleQuit(ActionEvent e){
        Platform.exit();
    }
    
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ArduinoFX.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Arduino LED Control");
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        sp.setComPortParameters(9600,8,1,0);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING,0,0);
        if(!sp.openPort()){
            System.out.println("\nCOM por NOT available\n");
            return;
        }
        launch(args);
        
    }
}
