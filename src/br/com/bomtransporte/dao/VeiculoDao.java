package br.com.bomtransporte.dao;

import br.com.bomtransporte.modelo.Veiculo;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JhonattanSouza_
 */
public class VeiculoDao extends Conexao implements Dao{

    @Override
    public void inserir(Object obj) throws Exception {
        Veiculo veiculo = (Veiculo) obj;
        
        inicializarAtributos();
        
        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        
        stmt = con.prepareStatement("INSERT INTO VEICULO(tipoVeiculo,STATUS)VALUES(?,?)");
        stmt.setString(1, veiculo.tipoVeiculo());
        stmt.setString(2, veiculo.getStatus());
        
        stmt.execute();
        
        close();
    }

    @Override
    public void alterar(Object obj) throws Exception {
        
    }

    @Override
    public void excluir(Object obj) throws Exception {
        
    }

    @Override
    public List<Object> listar() throws Exception {
        return null;
    }
    
    public List<Object> listarVeiculosAtivos() throws Exception{
        List<Object> listaVeiculos = new ArrayList<>();
        
        inicializarAtributos();

        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("SELECT * FROM VEICULO WHERE STATUS = 'Aguardando';");
        rs = stmt.executeQuery();

        while (rs.next()) {
            Veiculo v = new Veiculo();
            v.setIdVeiculo(rs.getInt("idVeiculo"));
            v.tipoVeiculo(rs.getString("tipoVeiculo"));
            v.setStatus(rs.getString("status"));
            listaVeiculos.add(v);
        }

        close();
        
        return listaVeiculos;
    }
}
