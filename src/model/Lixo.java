package model;

import java.sql.Timestamp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Lixo {
    private int id_lixo;
    private String tipo;
    private int quantidade;
    private Usuario usuario;
    private Timestamp horario;
    private int id_usuario;
    private static ObservableList<Lixo> lixo = FXCollections.observableArrayList();

    public static ObservableList<Lixo> getLixo() {
        return lixo;
    }

    public static void setLixo(ObservableList<Lixo> lixo) {
        Lixo.lixo = lixo;
    }
    
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    public Timestamp getHorario() {
        return horario;
    }

    public void setHorario(Timestamp horario) {
        this.horario = horario;
    }

    public int getId_lixo() {
        return id_lixo;
    }

    public void setId_lixo(int id_lixo) {
        this.id_lixo = id_lixo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    
    
    
    
}
