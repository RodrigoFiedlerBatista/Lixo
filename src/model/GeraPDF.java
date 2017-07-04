package model;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class GeraPDF {
    
    private static DirectoryChooser escolheDiretorio = new DirectoryChooser();
    
    public void gerarPdf() throws IOException{
        
        File arquivo = escolheDiretorio.showDialog(new Stage());
        String caminho = arquivo.getAbsolutePath();
        System.out.println(caminho);
        
        Document documento = new Document();
        
        try {
            PdfWriter.getInstance(documento, new FileOutputStream(caminho + "\\Usuarios.pdf"));
            documento.open();
            
            
            documento.add(geraTabela());
            documento.close();
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(GeraPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public PdfPTable geraTabela() throws IOException, BadElementException{
        
        // Tabela de Usuarios
        PdfPTable tabela = new PdfPTable(4);
        PdfPCell linha;
        linha = new PdfPCell(new Phrase("Usuários"));
        linha.setHorizontalAlignment(Element.ALIGN_CENTER);
        linha.setColspan(4);
        tabela.addCell(linha);
        tabela.addCell("ID");
        tabela.addCell("Login");
        tabela.addCell("Email");
        tabela.addCell("Imagem");
        for (int i = 0; i < Usuario.getUsuarios().size(); i++) {
            Image imagem = Image.getInstance(Usuario.getUsuarios().get(i).getImagem());
            tabela.addCell(String.valueOf(Usuario.getUsuarios().get(i).getId_usuario()));
            tabela.addCell(Usuario.getUsuarios().get(i).getLogin());
            tabela.addCell(Usuario.getUsuarios().get(i).getEmail());
            tabela.addCell(imagem);
        }
        return tabela;
    }
    
}
