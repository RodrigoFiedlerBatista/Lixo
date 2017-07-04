package model;

import control.CadastrarLixoController;
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
        
        
        
        for (int i = 0; i < Lixo.getLixo().size(); i++) {
            
            if (Lixo.getLixo().get(i).getId_usuario() == usuario.getId_usuario()) {
                
                if (Lixo.getLixo().get(i).getTipo().equals("Metal")) {
                    dados.add(new XYChart.Data("Metal", Lixo.getLixo().get(i).getQuantidade()));
                }
                
                
            }
            
            
        }
        
        
        /*for (int i = 0; i < MesDAO.getMeses().size(); i++) {
            
            int totalPorMes = 0;
            
            for (int j = 0; j < VendaDAO.getVendas().size(); j++) {
                
                if (MesDAO.getMeses().get(i).equals(VendaDAO.getVendas().get(j).getMes())) {
                    totalPorMes++;
                }
                
            }
            if (totalPorMes > 0) {
                dados.add(new XYChart.Data(MesDAO.getMeses().get(i), totalPorMes));
            }
            
            
        }*/
        
        grafico.setData(dados);
        return grafico;
    }
    
}
