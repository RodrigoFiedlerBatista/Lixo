package control;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Lixo;
import model.Usuario;
import model.jdbc.LixoDAO;
import view.manage.CadastrarLixo;
import view.manage.Home;

public class CadastrarLixoController implements Initializable {
    
    @FXML
    private TextField textQuantidade;

    @FXML
    private ComboBox<String> comboTipo;
    
    private static Usuario usuario;
    
    public void atualizaComboTipo(){
        comboTipo.setItems(getLixoTipo());
    }
    
    public ObservableList<String> getLixoTipo(){
        ObservableList<String> tipos = FXCollections.observableArrayList();
        tipos.add("Metal");
        tipos.add("Papel");
        tipos.add("Vidro");
        tipos.add("Plastico");
        tipos.add("Outro");
        return tipos;
    }

    @FXML
    void addLixo(ActionEvent event) {
        Lixo lixo = new Lixo();
        LixoDAO lixoDAO = new LixoDAO();
        if (validaCampo()) {
            CadastrarLixo cadastrarLixo = new CadastrarLixo();
            Home home = new Home();
            lixo.setQuantidade(Integer.valueOf(textQuantidade.getText()));
            lixo.setTipo(comboTipo.getValue());
            lixo.setUsuario(usuario);
            lixoDAO.addLixo(lixo);
            cadastrarLixo.close();
            home.iniciaHome();
            Alert alerta = new Alert(AlertType.INFORMATION);
            alerta.setTitle("Cadastro");
            alerta.setHeaderText("Lixo Cadastrado");
            alerta.setContentText("Cadastro realizado com sucesso!");
            alerta.show();
            
        } else {
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setTitle("Erro");
            alerta.setHeaderText("Campo Invalido");
            alerta.setContentText("Digite valores válidos nos campos");
            alerta.show();
        }
        
        
    }
    
    public boolean validaCampo(){
        if ("".equals(textQuantidade.getText()) || "".equals(comboTipo.getValue())) {
            return false;
        } else if (Pattern.matches("[0-9]+", textQuantidade.getText()) == false) {
            return false;
        } else {
            return true;
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        atualizaComboTipo();
        System.out.println(String.valueOf(usuario.getId_usuario()));
    }    

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        CadastrarLixoController.usuario = usuario;
    }
    
    
    
}
