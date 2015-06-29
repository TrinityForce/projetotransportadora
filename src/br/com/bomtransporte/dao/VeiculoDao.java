package br.com.bomtransporte.dao;

import br.com.bomtransporte.modelo.Veiculo;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JhonattanSouza_
 */
public class VeiculoDao extends Conexao implements Dao {

    @Override
    public void inserir(Object obj) throws Exception {
        Veiculo veiculo = (Veiculo) obj;

        inicializarAtributos();

        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("INSERT INTO VEICULO(tipoVeiculo,STATUS, destino)VALUES(?,?,?)");
        stmt.setString(1, veiculo.tipoVeiculo());
        stmt.setString(2, veiculo.getStatus());
        stmt.setString(3, veiculo.getDestino());

        stmt.execute();

        close();
    }

    @Override
    public void alterar(Object obj) throws Exception {
        Veiculo veiculo = (Veiculo) obj;
        inicializarAtributos();
        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement(" update veiculo set totalPreenchido = ?, pesoPreenchido = ? where idVeiculo = ?;");
        stmt.setDouble(1, veiculo.getTotalPreenchido());
        stmt.setDouble(2, veiculo.getPesoPreenchido());
        stmt.setInt(3, veiculo.getIdVeiculo());
        stmt.execute();
        
        close();
    }

    @Override
    public void excluir(Object obj) throws Exception {

    }

    @Override
    public List<Object> listar() throws Exception {
        return null;
    }

    public List<Object> listarVeiculosAtivos() throws Exception {
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
            v.setDestino(rs.getString("destino"));
            v.setPesoPreenchido(rs.getDouble("pesoPreenchido"));
            v.setTotalPreenchido(rs.getDouble("totalPreenchido"));
            listaVeiculos.add(v);
        }

        close();

        return listaVeiculos;
    }
}
