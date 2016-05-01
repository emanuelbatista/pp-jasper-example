/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pp.jasper.example.entidade.relatorio;

import br.edu.ifpb.pp.jasper.example.entidade.Pessoa;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Emanuel Batista da Silva Filho - emanuelbatista2011@gmail.com
 */
public class GerarRelatorioJSE {

    public static void main(String[] args) throws JRException, ClassNotFoundException {
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

        JasperReport jasperReport = (JasperReport)JRLoader.loadObject(ClassLoader.getSystemResourceAsStream("relatorio.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new PessoaDataSource(pessoas));

        JasperViewer.viewReport(jasperPrint);

    }
}
