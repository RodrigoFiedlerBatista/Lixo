package view.manage;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Cadastrar extends Application {

    private static Stage cadastrar = new Stage();
    
    public void iniciaCadastrar(){
        Stage palco = new Stage();
        try {
            start(palco);
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void fechaCadastrar(){
        cadastrar.close();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Cadastrar.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        cadastrar = stage;
    }
    
}
