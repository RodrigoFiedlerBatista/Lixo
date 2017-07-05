package control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.GraficoBarra;
import model.Usuario;
import view.manage.CadastrarLixo;
import view.manage.Editar;
import view.manage.Home;
import view.manage.Login;

public class HomeController implements Initializable {
    
    @FXML
    private BarChart barra;

    @FXML
    private ImageView imagem;
    
    private static Usuario usuario;

    @FXML
    void editar(ActionEvent event) {
        EditarController.setUsuario(usuario);
        EditarController.setAdminL(false);
        Editar editar = new Editar();
        Home home = new Home();
        editar.iniciaEditar();
        home.fechaHome();
    }

    @FXML
    void cadastrar(ActionEvent event) {
        Home home = new Home();
        CadastrarLixo cadastrarLixo = new CadastrarLixo();
        CadastrarLixoController.setUsuario(usuario);
        home.fechaHome();
        cadastrarLixo.show();
        
    }
    
    public void carregaBarChart(){
        GraficoBarra graficoBarra = new GraficoBarra();
        barra.getData().clear();
        barra.getData().addAll(graficoBarra.montaGrafico(usuario));
    }
    
    public void atualizaImagem(){
        imagem.setImage(new Image("file:///" + usuario.getImagem()));
    }
    
    public void atualizaHome(){
        
    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        HomeController.usuario = usuario;
    }
    
    @FXML
    void voltar(ActionEvent event) {
        Login login = new Login();
        Home home = new Home();
        home.fechaHome();
        login.iniciaLogin();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        atualizaImagem();
        carregaBarChart();
    }    
    
}
