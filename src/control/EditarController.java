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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Criptografia;
import model.GerenciaImagem;
import model.Usuario;
import model.jdbc.UsuarioDAO;
import view.manage.Admin;
import view.manage.Editar;

public class EditarController implements Initializable {
    
    @FXML
    private PasswordField textSenha;
    
    @FXML
    private PasswordField textSenha1;

    @FXML
    private TextField textLogin;

    @FXML
    private ImageView imagem;

    @FXML
    private TextField textEmail;
    
    private static Usuario usuario;
    
    
    @FXML
    void trocarImagem(ActionEvent event) {
        GerenciaImagem gerenciaImagem = new GerenciaImagem();
        usuario.setImagem(gerenciaImagem.getNovaImagem());
        
        atualizaImagem();
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
    
    public boolean validaLogin(){
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario.setUsuarios(usuarioDAO.selectUsuario());
        for (int i = 0; i < Usuario.getUsuarios().size(); i++) {
            if (Usuario.getUsuarios().get(i).getLogin().equals(textLogin.getText())) {
                return true;   
            }
        }
        return false;
    }

    @FXML
    void alterar(ActionEvent event) {
        if (!textSenha1.getText().equals(textSenha.getText())) {
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setTitle("Erro");
            alerta.setHeaderText("Senha diferentes!");
            alerta.setContentText("Confirme a senha correta!");
            alerta.show();
            
        } else if (validaEmail()){
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setTitle("Erro");
            alerta.setHeaderText("Email Invalido!");
            alerta.setContentText("Digite um email valido!");
            alerta.show();
            
        } else if (validaLogin()){
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setTitle("Erro");
            alerta.setHeaderText("Login Invalido!");
            alerta.setContentText("Esse login já existe!");
            alerta.show();
            
        } else {
            Criptografia criptografia = new Criptografia();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Path deleta = Paths.get(System.getProperty("user.dir") + "\\src\\imagens\\" + usuario.getLogin() + ".png");
            Path user = Paths.get(System.getProperty("user.dir") + "\\src\\imagens\\user.png");
            if (deleta.equals(user)) {
                System.out.println("Não deletou");
            } else {
                try {
                    Files.delete(deleta);
                } catch (IOException ex) {
                    Logger.getLogger(EditarController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            usuario.setLogin(textLogin.getText());
            usuario.setEmail(textEmail.getText());
            usuario.setChave(criptografia.genKey(textSenha.getText().length()));
            usuario.setSenha(criptografia.criptografa(textSenha.getText(), usuario.getChave()));
            String urlImagem = usuario.getImagem();
            Path source = Paths.get(usuario.getImagem());
            urlImagem = System.getProperty("user.dir") + "\\src\\imagens\\" + textLogin.getText() + ".png";
            Path dest = Paths.get(urlImagem);
            try {
                Files.copy(source, dest);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            usuarioDAO.atualiza(usuario);
            
            
            Editar editar = new Editar();
            Admin admin = new Admin();
            editar.fechaEditar();
            admin.iniciaAdmin();
        }
    }
    
    public void atualizaEditar(){
        System.out.println(usuario.getId_usuario());
        textLogin.setText(usuario.getLogin());
        textEmail.setText(usuario.getEmail());
    }
    
    public void atualizaImagem(){
        imagem.setImage(new Image("file:///" + usuario.getImagem()));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        atualizaEditar();
        atualizaImagem();
    }    

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        EditarController.usuario = usuario;
    }
    
    @FXML
    void voltar(ActionEvent event) {
        Editar editar = new Editar();
        Admin admin = new Admin();
        editar.fechaEditar();
        admin.iniciaAdmin();
    }
    
}
