package br.com.bomtransporte.dao;

import br.com.bomtransporte.modelo.Cidade;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
//testanto commit

/**
 *
 * @author JhonattanSouza_
 */
public class CidadeDao extends Conexao implements Dao {
    
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
        Cidade cidade = null;
        
        inicializarAtributos();
        
        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        
        stmt = con.prepareStatement("select id, nome, uf from CidadeCorreios;");
        
        rs = stmt.executeQuery();
        
        while (rs.next()) {
            cidade = new Cidade();
            cidade.setIdCidade(rs.getInt("id"));
            cidade.setNome(rs.getString("nome"));
            cidade.setUf(rs.getString("uf"));
            
            listaCidade.add(cidade);
        }
        return listaCidade;
    }
    
    public List<Object> listarUf() throws Exception {
        List<Object> listaCidade = new ArrayList();
        Cidade cidade = null;
        
        inicializarAtributos();
        
        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        
        stmt = con.prepareStatement("select distinct uf from cidadecorreios;");
        
        rs = stmt.executeQuery();
        
        while (rs.next()) {
            cidade = new Cidade();
            cidade.setUf(rs.getString("uf"));
            listaCidade.add(cidade);
        }
        return listaCidade;
    }
    
    public List<String> listarCidadePorUF(String uf) throws Exception {
        List<String> list = new ArrayList();
        inicializarAtributos();
        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        stmt = con.prepareStatement("select nome from cidadeCorreios where uf = ?;");
        stmt.setString(1, uf);
        rs = stmt.executeQuery();
        
        while (rs.next()) {
            
            list.add(rs.getString("nome"));
        }
        return list;
    }
}
