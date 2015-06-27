package br.com.bomtransporte.dao;

import br.com.bomtransporte.modelo.PrecoDistancia;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gustavo Carvalho <gustavo.carvalho.costa@outlook.com>
 */
public class PrecoDistanciaDao extends Conexao{

    /**
     *
     * @param obj
     * @throws Exception
     */
    public void inserir(Object obj) throws Exception {
        PrecoDistancia precoDistancia = (PrecoDistancia) obj;
        
        inicializarAtributos();
        
        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
   
        stmt = con.prepareStatement("INSERT INTO `dbtransportadora`.`precodistancia`(`valor`,`origemDestinoUF`,`ativado`,`dataDesativado`,`funcionarioQueDesativou`)VALUES(?,?,?,?,?);");
        stmt.setDouble(1,precoDistancia.getValor());
        stmt.setString(2,precoDistancia.getOrigemDestinoUf());
        stmt.setBoolean(3,precoDistancia.getAtivado());
        stmt.setString(4,precoDistancia.getDataDesativado());
        stmt.setString(5,precoDistancia.getFuncionarioQueDesativou());
        stmt.execute();
       
        close();
    }
    
    /**
     *
     * @param obj
     * @throws Exception
     */
    public void alterarExcluir(Object obj) throws Exception {
        PrecoDistancia precoDistancia = (PrecoDistancia) obj;

        inicializarAtributos();

        con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);


            stmt = con.prepareStatement("UPDATE PrecoDistancia set ativado = ?, dataDesativado = ?, funcionarioQueDesativou = ? where idPrecoDistancia = ?");
            stmt.setBoolean(1, precoDistancia.getAtivado());
            stmt.setString(2, precoDistancia.getDataDesativado());
            stmt.setString(3, precoDistancia.getFuncionarioQueDesativou());
            stmt.setInt(4, precoDistancia.getIdPrecoDistancia());
            stmt.execute();
            
            close();
    }

    /**
     *
     * @param origemDestino
     * @return
     * @throws Exception
     */
    public PrecoDistancia verificarSeExiste(String origemDestino) throws Exception {   
        PrecoDistancia pd = null;
        
        inicializarAtributos(); 
        
        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        
        stmt = con.prepareStatement("select ativado, origemDestinoUf from PrecoDistancia where origemDestinoUf = '" + origemDestino + "' and ativado = true;"); 
        rs = stmt.executeQuery();

        while (rs.next()) {
            pd = new PrecoDistancia();
            pd.setOrigemDestinoUf(rs.getString("origemDestinoUf"));
            pd.setAtivado(rs.getBoolean("ativado"));
        }

        close();
        return pd;
    }
    
    /**
     *
     * @return
     * @throws Exception
     */
    public List<PrecoDistancia> listarTodosAtivados() throws Exception{
        List<PrecoDistancia> listaPreco = new ArrayList<>();
        PrecoDistancia precoDistancia;
        
        inicializarAtributos();
        
        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        
        stmt = con.prepareStatement("Select idPrecoDistancia, origemDestinoUf, valor from PrecoDistancia where ativado = true;");
        rs = stmt.executeQuery();
        
        while(rs.next()){
            precoDistancia = new PrecoDistancia();
            precoDistancia.setIdPrecoDistancia(rs.getInt("idPrecoDistancia"));
            precoDistancia.setValor(rs.getDouble("valor"));
            precoDistancia.setOrigemDestinoUf(rs.getString("origemDestinoUf"));
            listaPreco.add(precoDistancia);
        }
        close();
        return listaPreco;
    }
    
    /**
     *
     * @return
     * @throws Exception
     */
    public PrecoDistancia retornarPrecoDistancia() throws Exception{
        PrecoDistancia precoDistancia = null;
        
        inicializarAtributos();
        
        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        
        stmt = con.prepareStatement("Select idPrecoDistancia, origemDestinoUf, valor from PrecoDistancia where ativado = true;");
        rs = stmt.executeQuery();
        
        while(rs.next()){
            precoDistancia = new PrecoDistancia();
            precoDistancia.setIdPrecoDistancia(rs.getInt("idPrecoDistancia"));
            precoDistancia.setValor(rs.getDouble("valor"));
            precoDistancia.setOrigemDestinoUf(rs.getString("origemDestinoUf"));
        }
        close();
        return precoDistancia;
    }
    
}
