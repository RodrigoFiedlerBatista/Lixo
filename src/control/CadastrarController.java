package control;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Criptografia;
import model.GerenciaImagem;
import model.Usuario;
import model.jdbc.UsuarioDAO;
import view.manage.Cadastrar;
import view.manage.Login;

public class CadastrarController implements Initializable {

    @FXML
    private PasswordField textSenha;

    @FXML
    private TextField textLogin;

    @FXML
    private TextField textEmail;
    
    @FXML
    private Label labelErro;

    @FXML
    private Label labelLogin;

    @FXML
    private Label labelEmail;
    
    @FXML
    private Label labelSenha;
    
    @FXML
    private ImageView imagem;

    @FXML
    void cadastrar(ActionEvent event) {
        
        
        
        // Checa se há informações nas TextFields
        if ("".equals(textLogin.getText()) || "".equals(textSenha.getText()) || "".equals(textEmail.getText())) {
            if ("".equals(textLogin.getText())) {
                labelLogin.setVisible(true);
                labelErro.setVisible(true);
            } else {
                labelLogin.setVisible(false);
            }
            if ("".equals(textSenha.getText())) { 
                labelSenha.setVisible(true);
                labelErro.setVisible(true);
            } else {
                labelSenha.setVisible(false);
            }
            if ("".equals(textEmail.getText())) {
                labelEmail.setVisible(true);
                labelErro.setVisible(true);
            } else {
                labelEmail.setVisible(false);
            }
            
        } else if (validaEmail()) {
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setTitle("Erro");
            alerta.setHeaderText("Email Invalido");
            alerta.setContentText("Cadastre um email valido");
            alerta.show();
        } else if (validaLogin()) {
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setTitle("Erro");
            alerta.setHeaderText("Login Invalido");
            alerta.setContentText("Login já Cadastrado");
            alerta.show();
            
        } else {    
        
            
            labelLogin.setVisible(false);
            labelSenha.setVisible(false);
            labelEmail.setVisible(false);
            labelErro.setVisible(false);
            
            // Cria um objeto da classe usuario
            Usuario usuario = new Usuario();
            // Cria um objeto da classe UsuarioDAO
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            // Cria um objeto da classe criptografia
            Criptografia criptografia = new Criptografia();
            // Passa o valor da TextField senha para a String senha
            String senha = textSenha.getText();
            // Cria uma chave criptografada de acordo com o tamanho da senha
            String chave = criptografia.genKey(senha.length());
            // Usuario recebe o valor da TextField login
            usuario.setLogin(textLogin.getText());
            // Usuario recebe o valor da chave criptografada
            usuario.setChave(chave);
            // Usuario recebe a senha criptografada
            usuario.setSenha(criptografia.criptografa(senha, chave));
            // Usuario recebe o valor da TextField email
            usuario.setEmail(textEmail.getText());
            
            // Copia Imagem para o diretório do netbeans
            Path source = Paths.get(urlImagem);
            urlImagem = System.getProperty("user.dir") + "\\src\\imagens\\" + textLogin.getText() + ".png";
            Path dest = Paths.get(urlImagem);
            try {
                Files.copy(source, dest);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Usuario recebe a url da imagem
            usuario.setImagem(urlImagem);
            // Método para adicionar o usuario ao banco de dados
            usuarioDAO.addUsuario(usuario);
            
            
            
            // Cria um diretório do usuário cadastrado
            /*Path diretorio = Paths.get(System.getProperty("user.dir") + "\\src\\imagens\\" + textLogin.getText());
            try {
                Files.createDirectory(diretorio);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            
            
            
            
            // Fecha a tela de cadastro e abre a de login
            Cadastrar cadastrar = new Cadastrar();
            Login login = new Login();
            cadastrar.fechaCadastrar();
            login.iniciaLogin();
            
            // Alerta indicando sucesso no cadastro
            Alert alerta = new Alert(AlertType.INFORMATION);
            alerta.setTitle("Cadastro");
            alerta.setHeaderText("Usuario Cadastrado!");
            alerta.setContentText("Sucesso!");
            alerta.show();
            
            
        }
    }
    
    public boolean validaEmail(){
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(textEmail.getText());
        if (m.find() && m.group().equals(textEmail.getText())) {
            return false;
        } else {
            return true;
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        Cadastrar cadastrar = new Cadastrar();
        Login login = new Login();
        login.iniciaLogin();
        cadastrar.fechaCadastrar();
    }
    
    public boolean validaLogin(){
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario.setUsuarios(usuarioDAO.selectUsuario());
        for (int i = 0; i < Usuario.getUsuarios().size(); i++) {
            if (Usuario.getUsuarios().get(i).getLogin().equalsIgnoreCase(textLogin.getText())) {
                return true;   
            }
        }
        return false;
    }
    
    private String urlImagem = System.getProperty("user.dir") + "\\src\\imagens\\user.png";
    
    public void iniciaImagem(){
        imagem.setImage(new Image("file:///" + urlImagem));
    }
    
    @FXML
    void trocarImagem(ActionEvent event) {
        GerenciaImagem gerenciaImagem = new GerenciaImagem();
        urlImagem = gerenciaImagem.getNovaImagem();
        imagem.setImage(new Image("file:///" + urlImagem));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciaImagem();
        
        
        
    }    
    
}
