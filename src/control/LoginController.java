package control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Criptografia;
import model.Lixo;
import model.Usuario;
import model.jdbc.LixoDAO;
import model.jdbc.UsuarioDAO;
import view.manage.Admin;
import view.manage.Cadastrar;
import view.manage.Home;
import view.manage.Login;

public class LoginController implements Initializable {

    @FXML
    private PasswordField textSenha;

    @FXML
    private TextField textLogin;
    
    @FXML
    private ImageView imagem1;

    private static int contador;
    
    @FXML
    void login(ActionEvent event) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario.setUsuarios(usuarioDAO.selectUsuario());
        if (textSenha.getText().equals("admin") && textLogin.getText().equals("admin")) {
            Admin admin = new Admin();
            Login login = new Login();
            admin.iniciaAdmin();
            login.fechaLogin();
        } else {
            contador = Usuario.getUsuarios().size();
            for (int i = 0; i < Usuario.getUsuarios().size(); i++) {
                Criptografia criptografia = new Criptografia();
                String senha = Usuario.getUsuarios().get(i).getSenha();
                String chave = Usuario.getUsuarios().get(i).getChave();
                String login = Usuario.getUsuarios().get(i).getLogin();
                senha = criptografia.decriptografa(senha, chave);
                if (textSenha.getText().equals(senha) && textLogin.getText().equals(login)) {
                    Home home = new Home();
                    Login login1 = new Login();
                    HomeController.setUsuario(Usuario.getUsuarios().get(i));
                    login1.fechaLogin();
                    home.iniciaHome();
                
                } else {
                    contador--;
                    if (contador == 0) {
                        Alert alerta = new Alert(AlertType.ERROR);
                        alerta.setTitle("Erro");
                        alerta.setHeaderText("Login ou senha inválidos");
                        alerta.setContentText("Erro de autenticação");
                        alerta.show();
                    }
                }
            }
        }
    }

    @FXML
    void cadastrar(ActionEvent event) {
        Cadastrar cadastrar = new Cadastrar();
        Login login = new Login();
        login.fechaLogin();
        cadastrar.iniciaCadastrar();
    }
    
    public void iniciaImagem(){
        imagem1.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\lixo_login.png"));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciaImagem();
        
        
        
        
        
        /*Set<PosixFilePermission> perms =
        PosixFilePermissions.fromString("rwxr-x---");
        FileAttribute<Set<PosixFilePermission>> attr =
        PosixFilePermissions.asFileAttribute(perms);*/
        
        /*Path diretorio = Paths.get("C:\\Users\\Rodrigo\\Desktop\\novo");
        try {
            Files.createDirectory(diretorio);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        // Copia Arquivo
        /*Path source = Paths.get("C:\\Users\\Rodrigo\\Desktop\\Novo Documento de Texto (2).txt");
        Path dest = Paths.get("C:\\Users\\Rodrigo\\Desktop\\Easy Steam Money\\Novo Documento de Texto (3).txt");
        try {
            Files.copy(source, dest);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        
        
        
    }    
    
}
