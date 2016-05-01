/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pp.jasper.example.entidade.relatorio;

import br.edu.ifpb.pp.jasper.example.entidade.Pessoa;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Emanuel Batista da Silva Filho - emanuelbatista2011@gmail.com
 */
@Named
@RequestScoped
public class GerarRelatorioJEE {

    public void gerarRelatorio() {
        try {
            List<Pessoa> pessoas = new ArrayList<>();
            Pessoa pessoa1 = new Pessoa();
            pessoa1.setNome("Emanuel");
            pessoa1.setIdade(20);
            pessoa1.setSobrenome("Batista");
            pessoas.add(pessoa1);

            Pessoa pessoa2 = new Pessoa();
            pessoa2.setNome("João");
            pessoa2.setIdade(27);
            pessoa2.setSobrenome("Taba");
            pessoas.add(pessoa2);

            JasperReport jasperReport =(JasperReport)JRLoader.loadObject(getClass().getClassLoader().getResourceAsStream("relatorio.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new PessoaDataSource(pessoas));
            byte[] byteJasper = JasperExportManager.exportReportToPdf(jasperPrint);
            OutputStream out=getOutputStreamFaces();
            out.write(byteJasper);
            out.flush();
        } catch (JRException | IOException ex) {
            Logger.getLogger(GerarRelatorioJEE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private OutputStream getOutputStreamFaces() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
            return response.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(GerarRelatorioJEE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
