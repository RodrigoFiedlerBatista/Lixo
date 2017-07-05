package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import model.jdbc.LixoDAO;

public class GraficoBarra {
    
    public XYChart.Series montaGrafico(Usuario usuario) {
        
        XYChart.Series grafico = new XYChart.Series<>();
        grafico.setName("Lixo");
        
        ObservableList<XYChart.Data> dados = FXCollections.observableArrayList();
        LixoDAO lixoDAO = new LixoDAO();
        Lixo.setLixo(lixoDAO.selectLixo());
        
        int contadorMetal = 0;
        int contadorPapel = 0;
        int contadorPlastico = 0;
        int contadorVidro = 0;
        int contadorOutro = 0;
        
        for (int i = 0; i < Lixo.getLixo().size(); i++) {
            
            if (Lixo.getLixo().get(i).getId_usuario() == usuario.getId_usuario()) {
                
                if (Lixo.getLixo().get(i).getTipo().equals("Metal")) {
                    contadorMetal = contadorMetal + Lixo.getLixo().get(i).getQuantidade();
                } else if(Lixo.getLixo().get(i).getTipo().equals("Papel")) {
                    contadorPapel = contadorPapel + Lixo.getLixo().get(i).getQuantidade();
                } else if (Lixo.getLixo().get(i).getTipo().equals("Plastico")) {
                    contadorPlastico = contadorPlastico + Lixo.getLixo().get(i).getQuantidade();
                } else if (Lixo.getLixo().get(i).getTipo().equals("Vidro")) {
                    contadorVidro = contadorVidro + Lixo.getLixo().get(i).getQuantidade();
                } else if (Lixo.getLixo().get(i).getTipo().equals("Outro")) {
                    contadorOutro = contadorOutro + Lixo.getLixo().get(i).getQuantidade();
                }
                
            }
            
        }
        
        dados.add(new XYChart.Data("Metal", contadorMetal));
        dados.add(new XYChart.Data("Plastico", contadorPlastico));
        dados.add(new XYChart.Data("Papel", contadorPapel));
        dados.add(new XYChart.Data("Vidro", contadorVidro));
        dados.add(new XYChart.Data("Outro", contadorOutro));
        
        grafico.setData(dados);
        return grafico;
    }
    
}
