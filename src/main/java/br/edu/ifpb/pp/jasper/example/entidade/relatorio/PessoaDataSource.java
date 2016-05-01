/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pp.jasper.example.entidade.relatorio;

import br.edu.ifpb.pp.jasper.example.entidade.Pessoa;
import java.util.Iterator;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Emanuel Batista da Silva Filho - emanuelbatista2011@gmail.com
 */
public class PessoaDataSource implements JRDataSource {

    private Iterator<Pessoa> pessoas;
    private Pessoa pessoa;

    public PessoaDataSource(List<Pessoa> pessoas) {
        this.pessoas = pessoas.iterator();
    }

    @Override
    public boolean next() throws JRException {
        boolean temProx = pessoas.hasNext();
        if (temProx) {
            pessoa = pessoas.next();
        }
        return temProx;
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        if ("nome".equals(jrf.getName())) {
            return pessoa.getNome();
        }else if("idade".equals(jrf.getName())){
            return pessoa.getIdade();
        }else if("sobrenome".equals(jrf.getName())){
            return pessoa.getSobrenome();
        }else{
            return "";
        }
        

    }

}
