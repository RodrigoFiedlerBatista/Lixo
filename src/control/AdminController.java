 package control;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.GeraPDF;
import model.Usuario;
import model.jdbc.UsuarioDAO;
import view.manage.Admin;
import view.manage.Editar;
import view.manage.Login;

public class AdminController implements Initializable {
    
    @FXML
    private TableView<Usuario> tabelaUsuario;

    @FXML
    private TableColumn<Usuario, ImageView> colImagem;

    @FXML
    private TableColumn<Usuario, Integer> colId;

    @FXML
    private TableColumn<Usuario, String> colEmail;

    @FXML
    private TableColumn<Usuario, String> colLogin;
    
    @FXML
    private PieChart pieChart;
    
    private static Usuario usuario;
    
    public void atualizaTabela(){
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario.setUsuarios(usuarioDAO.selectUsuario());
        for (int i = 0; i < Usuario.getUsuarios().size(); i++) {
            ImageView imagem = new ImageView(new Image("file:///" + Usuario.getUsuarios().get(i).getImagem()));
            imagem.setFitHeight(100);
            imagem.setFitWidth(100);
            Usuario.getUsuarios().get(i).setImagem2(imagem);
        }
        tabelaUsuario.setItems(Usuario.getUsuarios());
        colId.setCellValueFactory(new PropertyValueFactory("id_usuario"));
        colLogin.setCellValueFactory(new PropertyValueFactory("login"));
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        colImagem.setCellValueFactory(new PropertyValueFactory("imagem2"));
        
    }
    
    public void selecionaUsuario(){
        tabelaUsuario.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Usuario>() {
            @Override
            public void changed(ObservableValue<? extends Usuario> observable, Usuario oldValue, Usuario newValue) {
                usuario = newValue;
            }
        });
        
    }
    
    @FXML
    void deletaUsuario(ActionEvent event) {
        
        Alert alerta = new Alert(AlertType.CONFIRMATION);
        alerta.setTitle("Confirmação");
        alerta.setHeaderText("Tem certeza que deseja excluir esse usuário?");
        
        alerta.showAndWait().ifPresent(b ->{
            if (b == alerta.getButtonTypes().get(0)) {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                usuarioDAO.deletaUsuario(usuario.getId_usuario());
                System.out.println(usuario.getImagem());
                Path p = Paths.get(usuario.getImagem());
                try {
                    Files.delete(p);
                } catch (IOException ex) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
                atualizaTabela();
            }
        });
    }
    
    public TableView getTabela(){
        return this.tabelaUsuario;
    }
    
    @FXML
    void pdfUsuario(ActionEvent event) throws IOException {
        GeraPDF geraPDF = new GeraPDF();
        geraPDF.gerarPdf();
    }

    @FXML
    void editarUsuario(ActionEvent event) {
        EditarController.setUsuario(usuario);
        EditarController.setAdminL(true);
        Editar editar = new Editar();
        Admin admin = new Admin();
        editar.iniciaEditar();
        admin.fechaAdmin();
    }
    
    @FXML
    void voltar(ActionEvent event) {
        Login login = new Login();
        Admin admin = new Admin();
        login.iniciaLogin();
        admin.fechaAdmin();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        atualizaTabela();
        selecionaUsuario();
    }    
    
}
