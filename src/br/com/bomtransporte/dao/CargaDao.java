package br.com.bomtransporte.dao;

import br.com.bomtransporte.modelo.Carga;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CargaDao extends Conexao implements Dao {

    public Integer insertGetKey(Object obj) throws Exception {
        Carga carga = (Carga) obj;

        try {
            inicializarAtributos();
            con.setAutoCommit(false);

            con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            stmt = con.prepareStatement("INSERT INTO CARGA(descricao,peso,quantidade, idPedido_Cli) VALUES(?,?,?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, carga.getDescricao());
            stmt.setDouble(2, carga.getPeso());
            stmt.setInt(3, carga.getQuantidade());
            stmt.setInt(4, carga.getIdPedido());

            stmt.execute();

            rs = stmt.getGeneratedKeys();
            rs.next();
            carga.setIdCarga(rs.getInt(1));

            con.commit();
            System.err.println("cargadao foi");
            return carga.getIdCarga();

        } catch (SQLException e) {
            if (con != null) {
                con.rollback();
                System.out.println("Connection rollback...");
            }
            e.printStackTrace();

        } finally {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        }
        return null;
    }

    @Override
    public void alterar(Object obj) throws Exception {
        Carga carga = (Carga) obj;

        inicializarAtributos();

        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("UPDATE CARGA SET descricao = ? , peso = ?, quantidade = ? where idCarga = ?");
        stmt.setString(1, carga.getDescricao());
        stmt.setDouble(2, carga.getPeso());
        stmt.setInt(3, carga.getQuantidade());
        stmt.setInt(4, carga.getIdCarga());

        stmt.execute();

        con.close();
    }

    @Override
    public void excluir(Object obj) throws Exception {
        Carga carga = (Carga) obj;

        inicializarAtributos();

        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("Delete from carga where idCarga = ?");
        stmt.setInt(1, carga.getIdCarga());

        stmt.execute();

        con.close();
    }

    @Override
    public List<Object> listar() throws Exception {
        List<Object> listaCargas = new ArrayList<>();

        inicializarAtributos();

        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("SELECT * FROM CARGA");

        rs = stmt.executeQuery();

        while (rs.next()) {
            Carga carga = new Carga();
            carga.setIdCarga(rs.getInt("idCarga"));
            carga.setDescricao(rs.getString("descricao"));
            carga.setPeso(rs.getDouble("peso"));
            carga.setQuantidade(rs.getInt("quantidade"));

            listaCargas.add(carga);
        }
        con.close();
        return listaCargas;
    }

    public List<Object> listarCargas(Integer idPedido_cli) throws Exception {
        List<Object> listaCargas = new ArrayList<>();

        inicializarAtributos();

        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("SELECT * FROM CARGA WHERE idPedido_cli = ?");
        stmt.setInt(1, idPedido_cli);
        rs = stmt.executeQuery();

        while (rs.next()) {
            Carga carga = new Carga();
            carga.setIdCarga(rs.getInt("idCarga"));
            carga.setDescricao(rs.getString("descricao"));
            carga.setPeso(rs.getDouble("peso"));
            carga.setQuantidade(rs.getInt("quantidade"));

            listaCargas.add(carga);
        }
        con.close();
        return listaCargas;
    }

    @Override
    public void inserir(Object obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
