package br.com.bomtransporte.dao;

import br.com.bomtransporte.modelo.Carga;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JhonattanSouza_
 */
public class CargaDao extends Conexao implements Dao {

    /**
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public Integer insertGetKey(Object obj) throws Exception {
        Carga carga = (Carga) obj;

        try {
            inicializarAtributos();
            con.setAutoCommit(false);

            con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            stmt = con.prepareStatement("INSERT INTO CARGA(descricao,idprecoPeso,quantidade, idPedido_Cli,dimensaoCubica,status) VALUES(?,?,?, ?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, carga.getDescricao());
            stmt.setInt(2, carga.getIdPrecoPeso());
            stmt.setInt(3, carga.getQuantidade());
            stmt.setInt(4, carga.getIdPedido_Cli());
            stmt.setDouble(5, carga.getDimensaoCubica());
            stmt.setString(6, carga.getStatus());
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

        } finally {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        }
        return null;
    }

    /**
     *
     * @param obj
     * @throws Exception
     */
    @Override
    public void alterar(Object obj) throws Exception {
        Carga carga = (Carga) obj;

        inicializarAtributos();

        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("UPDATE CARGA SET descricao = ? , idPrecoPeso = ?, quantidade = ?, status = ? where idCarga = ?");
        stmt.setString(1, carga.getDescricao());
        stmt.setInt(2, carga.getIdPrecoPeso());
        stmt.setInt(3, carga.getQuantidade());
        stmt.setString(4, carga.getStatus());
        stmt.setInt(5, carga.getIdCarga());

        stmt.execute();

        con.close();
    }

    /**
     *
     * @param obj
     * @throws Exception
     */
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

    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Object> listar() throws Exception {
        List<Object> listaCargas = new ArrayList<>();

        inicializarAtributos();

        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("SELECT * FROM CARGA c join PrecoPeso pp On pp.idPrecoPeso = c.idPrecoPeso");

        rs = stmt.executeQuery();

        while (rs.next()) {
            Carga carga = new Carga();
            carga.setIdCarga(rs.getInt("idCarga"));
            carga.setDescricao(rs.getString("descricao"));
            carga.setPeso(rs.getString("pp.peso"));
            carga.setIdPrecoPeso(rs.getInt("c.idPrecoPeso"));
            carga.setQuantidade(rs.getInt("quantidade"));

            listaCargas.add(carga);
        }
        con.close();
        return listaCargas;
    }

    /**
     *
     * @param idPedido_cli
     * @return
     * @throws Exception
     */
    public List<Object> listarCargas(Integer idPedido_cli) throws Exception {
        List<Object> listaCargas = new ArrayList<>();
        inicializarAtributos();

        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("SELECT * FROM CARGA c"
                + " join PrecoPeso pp On pp.idPrecoPeso = c.idPrecoPeso"
                + " WHERE c.idPedido_Cli = ?;");
        stmt.setInt(1, idPedido_cli);
        rs = stmt.executeQuery();

        while (rs.next()) {
            Carga carga = new Carga();
            carga.setIdCarga(rs.getInt("c.idCarga"));
            carga.setDescricao(rs.getString("c.descricao"));
            carga.setQuantidade(rs.getInt("c.quantidade"));
            carga.setDimensaoCubica(rs.getDouble("c.dimensaoCubica"));
            carga.setIdPrecoPeso(rs.getInt("c.idPrecoPeso"));
            carga.setPeso(rs.getString("pp.peso"));
            carga.setValor(rs.getDouble("pp.valor"));
            carga.setStatus(rs.getString("c.status"));
            listaCargas.add(carga);
        }

        con.close();
        return listaCargas;
    }

    /**
     *
     * @param idPedido_cli
     * @param status
     * @return
     * @throws Exception
     */
    public List<Object> listarCargasPorStatus(Integer idPedido_cli, String status) throws Exception {
        List<Object> listaCargas = new ArrayList<>();
        inicializarAtributos();

        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("select * from Carga c "
                + "  join PrecoPeso pp on pp.idPrecoPeso = c.idPrecoPeso "
                + "  where idPedido_Cli = ? and status =? ;");
        stmt.setInt(1, idPedido_cli);
        stmt.setString(2, status);
        rs = stmt.executeQuery();

        while (rs.next()) {
            Carga carga = new Carga();
            carga.setIdCarga(rs.getInt("c.idCarga"));
            carga.setDescricao(rs.getString("c.descricao"));
            carga.setQuantidade(rs.getInt("c.quantidade"));
            carga.setDimensaoCubica(rs.getDouble("c.dimensaoCubica"));
            carga.setIdPrecoPeso(rs.getInt("c.idPrecoPeso"));
            carga.setIdPrecoPeso(rs.getInt("c.idPrecoPeso"));
            carga.setPeso(rs.getString("pp.peso"));
            carga.setValor(rs.getDouble("pp.valor"));
            carga.setStatus(rs.getString("c.status"));
            listaCargas.add(carga);
        }

        con.close();
        return listaCargas;
    }

    /**
     *
     * @param obj
     * @throws Exception
     */
    @Override
    public void inserir(Object obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
