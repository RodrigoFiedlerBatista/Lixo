package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;

public class Usuario {
    
    private String senha;
    private String chave;
    private String email;
    private String login;
    private String imagem;
    private int id_usuario;
    private ImageView imagem2;
    private static ObservableList<Usuario> usuarios = FXCollections.observableArrayList();

    public ImageView getImagem2() {
        return imagem2;
    }

    public void setImagem2(ImageView imagem2) {
        this.imagem2 = imagem2;
    }
    
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    public static ObservableList<Usuario> getUsuarios() {
        return usuarios;
    }

    public static void setUsuarios(ObservableList<Usuario> usuarios) {
        Usuario.usuarios = usuarios;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    
    
}
