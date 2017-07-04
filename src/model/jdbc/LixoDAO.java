package model.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Lixo;
import model.Usuario;

public class LixoDAO {
    
    public void addLixo(Lixo lixo){
        String sql = "insert into lixo (tipo, quantidade, id_usuario, horario) values (?, ?, ?, ?)";
        ConnectionFactory con = new ConnectionFactory();
        
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setString(1, lixo.getTipo());
            stmt.setInt(2, lixo.getQuantidade());
            stmt.setInt(3, lixo.getUsuario().getId_usuario());
            stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Errou");
        }
    }
    
    public void deletaUsuario(int id_usuario){
        String sql = "delete from usuario where id_usuario = ?";
        ConnectionFactory con = new ConnectionFactory();
        
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setInt(1, id_usuario);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Errou");
        }
        
    }
    
    public ObservableList<Lixo> selectLixo(){
        ObservableList<Lixo> lixo = FXCollections.observableArrayList();
        // Escrever o SQL
        String sql = "select * from lixo order by id_lixo";
        ConnectionFactory con = new ConnectionFactory();
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Lixo lixo1 = new Lixo();
                lixo1.setId_lixo(rs.getInt("id_lixo"));
                lixo1.setQuantidade(rs.getInt("quantidade"));
                lixo1.setTipo(rs.getString("tipo"));
                lixo1.setHorario(rs.getTimestamp("horario"));
                lixo1.setId_usuario(rs.getInt("id_usuario"));
                lixo.add(lixo1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lixo;
    }
    
    public void atualiza(Usuario usuario){
        String sql = "update usuario set login_usuario = ?, senha_usuario = ?, email = ?, chave = ?, url_imagem = ? where id_usuario = ?";
        ConnectionFactory con = new ConnectionFactory();
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getChave());
            stmt.setString(5, usuario.getImagem());
            stmt.setInt(6, usuario.getId_usuario());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
