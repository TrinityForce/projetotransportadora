package br.com.bomtransporte.dao;

import br.com.bomtransporte.modelo.Cidade;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JhonattanSouza_
 */
public class CidadeDao extends Conexao implements Dao{

    @Override
    public void inserir(Object obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar(Object obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(Object obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> listar() throws Exception {
        List<Object> listaCidade = new ArrayList();
        Cidade cidade;
        
        inicializarAtributos();
        
        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        
        stmt = con.prepareStatement("select id, nome, uf from cidades;");
        
        rs = stmt.executeQuery();
        
        while(rs.next()){
            cidade = new Cidade();
            cidade.setIdCidade(rs.getInt("id"));
            cidade.setNome(rs.getString("nome"));
            cidade.setUf(rs.getString("uf"));
            
            listaCidade.add(cidade);
        }
        return listaCidade;
    }  
    
    public Integer BuscarCidade(String nomeCidade, String uf) throws Exception{

        Integer idCidade = null;

        inicializarAtributos();
        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        
        stmt= con.prepareStatement("select * from CidadeCorreios where nome = ? and uf =?;");
        stmt.setString(1, nomeCidade);
        stmt.setString(2, uf);
        rs = stmt.executeQuery();
        
        if (rs.next()) {

            idCidade =(rs.getInt("id"));
        }
       
    return idCidade;
    }
}
