/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.bomtransporte.dao;

import br.com.bomtransporte.modelo.PrecoDistancia;
import br.com.bomtransporte.modelo.PrecoPeso;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Gustavo Carvalho <gustavo.carvalho.costa@outlook.com>
 */
public class PrecoPesoDao extends Conexao implements Dao {

    /**
     *
     * @param obj
     * @throws Exception
     */
    @Override
    public void inserir(Object obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param obj
     * @throws Exception
     */
    @Override
    public void alterar(Object obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param obj
     * @throws Exception
     */
    @Override
    public void excluir(Object obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Object> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     *
     * @return
     * @throws Exception
     */
    public List<PrecoPeso> listarTodosAtivados() throws Exception{
        List<PrecoPeso> listaPeso = new ArrayList<>();
        PrecoPeso precoPeso;
        
        inicializarAtributos();
        
        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        
        stmt = con.prepareStatement("SELECT idPrecoPeso, peso, valor" +
                        " FROM PrecoPeso" +
                        " WHERE ativado = true;");
        rs = stmt.executeQuery();
        
        while(rs.next()){
            precoPeso = new PrecoPeso();
     
               precoPeso.setIdPrecoPeso(rs.getInt(("idPrecoPeso")));
               precoPeso.setPeso(rs.getString("peso"));
               precoPeso.setValor(rs.getDouble("valor"));
            listaPeso.add(precoPeso);
        }
        close();
        return listaPeso;
    }

}



