package model.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    
    private String bancoDeDados = "Lixo";
    private String usuario = "postgres";
    private String senha = "rodrigo1";
    
    public Connection getConnection(){
        Connection connect = null;
        try {
            connect = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/" + bancoDeDados, usuario, senha);
            System.out.println("Deu Certo!");
            
        } catch (Exception e) {
            System.out.println("Não deu");
        } 
        return connect;
        
    }
    
}
