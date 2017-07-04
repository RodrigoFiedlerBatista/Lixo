package view.manage;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login extends Application {

    private static Stage login = new Stage();
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        login = stage;
    }
    
    public void iniciaLogin(){
        Stage palco = new Stage();
        try {
            start(palco);
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void fechaLogin(){
        login.close();
    }
    
    public static void main(String[] args) {
        System.out.println(LocalDateTime.now());
        launch(args);
    }
    
}
