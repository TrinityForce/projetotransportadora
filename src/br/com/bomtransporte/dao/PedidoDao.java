package br.com.bomtransporte.dao;

import br.com.bomtransporte.modelo.Pedido;
import br.com.bomtransporte.util.Datas;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Gustavo Carvalho
 */
public class PedidoDao extends Conexao implements Dao {

    /**
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public Integer insertGetKey(Object obj) throws Exception {
        Pedido pedido = (Pedido) obj;

        try {
            inicializarAtributos();
            con.setAutoCommit(false);

            con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            //insere um novo pedido
            stmt = con.prepareStatement(" insert into Pedido"
                    + " (protocolo, dataVenda, statusPedido)"
                    + " values (?, ?, ? );", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, pedido.getProtocolo());
            stmt.setDate(2, new java.sql.Date(pedido.getDataVenda().getTime()));
            stmt.setString(3, pedido.getStatusPedido());
            stmt.execute();

            rs = stmt.getGeneratedKeys();
            rs.next();
            pedido.setIdPedido(rs.getInt(1));

            //insere o endereco do pedido
            stmt = con.prepareStatement(" insert into Pedido_Endereco"
                    + " (idEnderecoCorreios, idPedido, numero, complemento) values"
                    + " (?, ?, ?,?);", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, pedido.getIdEnderecoCorreios());
            stmt.setInt(2, pedido.getIdPedido());
            stmt.setString(3, pedido.getNumero());
            stmt.setString(4, pedido.getComplemento());
            stmt.execute();

            con.commit();
            System.err.println("pedido inserir foi");
            return pedido.getIdPedido();

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
     * @param idCliente
     * @param idPedido
     * @param idPrecoDistancia
     * @return
     * @throws Exception
     */
    public Integer inserirPedidoCli(Integer idCliente, Integer idPedido,
            Integer idPrecoDistancia) throws Exception {
        Integer pedidoKey = null;
        try {
            inicializarAtributos();

            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            stmt = con.prepareStatement(" Insert into Pedido_Cli"
                    + " (idCliente,iDPedido, idPrecoDistancia) values (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, idCliente);
            stmt.setInt(2, idPedido);
            stmt.setInt(3, idPrecoDistancia);

            stmt.execute();

            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                pedidoKey = rs.getInt(1);
            }

            con.commit();

            return pedidoKey;

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
        return pedidoKey;
    }

    /**
     *
     * @param obj
     * @throws Exception
     */
    @Override
    public void alterar(Object obj) throws Exception {
        Pedido pedido = (Pedido) obj;
        inicializarAtributos();
        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("UPDATE pedido_cli pc"
                + " JOIN Pedido p on p.idPedido = pc.idPedido"
                + " JOIN Pedido_Endereco pe ON pe.idPedido = p.idPedido"
                + " SET pc.idPrecoDistancia = ?, p.desconto = ?,"
                + " p.statusPedido = ?, pe.numero = ?,pe.complemento = ?"
                + " WHERE p.idPedido = ?; ");
        stmt.setInt(1, pedido.getIdPrecoDistancia());
        stmt.setInt(2, pedido.getDesconto());
        stmt.setString(3, pedido.getStatusPedido());
        stmt.setString(4, pedido.getNumero());
        stmt.setString(5, pedido.getComplemento());
        stmt.setInt(6, pedido.getIdPedido());
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
    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public List<Object> listar() throws Exception {
        List<Object> listaPedidos = new ArrayList<>();

        inicializarAtributos();

        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("SELECT * FROM Pedido P"
                + "  inner join Pedido_Cli  PC on P.idPedido = PC.idPedido"
                + "  join PrecoDistancia pd on pd.idPrecoDistancia = pc.idPrecoDistancia ;");

        rs = stmt.executeQuery();

        while (rs.next()) {
            Pedido pedido = new Pedido();
            pedido.setIdPedido(rs.getInt("P.idPedido"));
            pedido.setProtocolo(rs.getString("P.protocolo"));
            pedido.setDataVenda(rs.getDate("P.dataVenda"));
            pedido.setDesconto(rs.getInt("P.desconto"));
            pedido.setStatusPedido(rs.getString("P.statusPedido"));
            pedido.setIdPedido_Cli(rs.getInt("PC.idPedido_Cli"));
            pedido.setDestinoUF(rs.getString("pd.origemDestinoUF"));
            listaPedidos.add(pedido);
        }

        close();
        return listaPedidos;
    }

    /**
     *
     * @param obj
     * @throws Exception
     */
    @Override
    public void inserir(Object obj) throws Exception {
    }

    /**
     *
     * @param idCliente
     * @return
     * @throws Exception
     */
    public List<Object> listarPedidos(Integer idCliente) throws Exception {
        List<Object> listaPedidos = new ArrayList<>();

        inicializarAtributos();

        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("SELECT * FROM Pedido P "
                + " inner join Pedido_Cli  PC on P.idPedido = PC.idPedido"
                + " join PrecoDistancia pd on pd.idPrecoDistancia = pc.idPrecoDistancia "
                + " where idCliente = ?;");
        stmt.setInt(1, idCliente);

        rs = stmt.executeQuery();

        while (rs.next()) {
            Pedido pedido = new Pedido();
            pedido.setIdPedido(rs.getInt("P.idPedido"));
            pedido.setProtocolo(rs.getString("P.protocolo"));
            pedido.setDataVenda(rs.getDate("P.dataVenda"));
            pedido.setDesconto(rs.getInt("P.desconto"));
            pedido.setStatusPedido(rs.getString("P.statusPedido"));
            pedido.setIdPedido_Cli(rs.getInt("PC.idPedido_Cli"));
            pedido.setIdPrecoDistancia(rs.getInt("PC.idPrecoDistancia"));
            pedido.setDestinoUF(rs.getString("pd.origemDestinoUF"));
            listaPedidos.add(pedido);
        }

        close();
        return listaPedidos;
    }

    /**
     *
     * @param idPedido
     * @return
     * @throws Exception
     */
    public Object buscarPedido(Integer idPedido) throws Exception {
        Pedido pedido = null;

        inicializarAtributos();
        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement(" Select * From Pedido_Cli pc"
                + "	join Pedido p on p.idPedido = pc.idPedido"
                + "	join Pedido_Endereco pe on pe.idPedido = p.idPedido"
                + "     join EnderecoCorreios ec on ec.id = pe.idEnderecoCorreios "
                + "     where p.idPedido = ?;");
        stmt.setInt(1, idPedido);
        rs = stmt.executeQuery();

        while (rs.next()) {
            pedido = new Pedido();
            pedido.setIdPedido(rs.getInt("p.idPedido"));
            pedido.setProtocolo(rs.getString("p.protocolo"));
            pedido.setDataVenda(rs.getDate("p.dataVenda"));
            pedido.setDesconto(rs.getInt("p.desconto"));
            pedido.setStatusPedido(rs.getString("p.statusPedido"));
            pedido.setNumero(rs.getString("pe.numero"));
            pedido.setIdPedido_Cli(rs.getInt("pc.idPedido_Cli"));
            pedido.setComplemento(rs.getString("pe.complemento"));
            pedido.setIdPrecoDistancia(rs.getInt("pc.idPrecoDistancia"));

            return pedido;
        }

        close();

        return pedido;
    }

    /**
     *
     * @param idCliente
     * @param dataInicial
     * @param dataFinal
     * @return
     * @throws Exception
     */
    public List<Object> buscarPedidoPelaData(Integer idCliente, Date dataInicial, Date dataFinal) throws Exception {
        List<Object> listaPedidos = new ArrayList<>();
        System.out.println("data init " + Datas.convertFromJAVADateToSQLDate(dataInicial));
        System.out.println("data fin " + Datas.convertFromJAVADateToSQLDate(dataFinal));
        System.err.println("idcliente " + idCliente);

        inicializarAtributos();

        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        stmt = con.prepareStatement(" SELECT * FROM Pedido p"
                + "  inner join Pedido_Cli  pc on p.idPedido = pc.idPedido"
                + "  where pc.idCliente =?"
                + "  and p.dataVenda between ? and ?;");
        stmt.setInt(1, idCliente);
        stmt.setDate(2, Datas.convertFromJAVADateToSQLDate(dataInicial));
        stmt.setDate(3, Datas.convertFromJAVADateToSQLDate(dataFinal));

        rs = stmt.executeQuery();

        while (rs.next()) {
            Pedido pedido = new Pedido();
            pedido.setIdPedido(rs.getInt("p.idPedido"));
            pedido.setProtocolo(rs.getString("p.protocolo"));
            pedido.setDataVenda(rs.getDate("p.dataVenda"));
            pedido.setDesconto(rs.getInt("p.desconto"));
            pedido.setStatusPedido(rs.getString("p.statusPedido"));
            pedido.setIdPedido_Cli(rs.getInt("pc.idPedido_Cli"));
            listaPedidos.add(pedido);

        }

        close();

        return listaPedidos;
    }

    /**
     *
     * @param idPedido
     * @param statusPedido
     * @throws Exception
     */
    public void update(Integer idPedido, String statusPedido) throws Exception {

        inicializarAtributos();
        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("UPDATE Pedido "
                + " SET statusPedido = ? WHERE idPedido = ?;");
        stmt.setString(1, statusPedido);
        stmt.setInt(2, idPedido);

        stmt.execute();

        con.close();

    }

    /**
     *
     * @param status
     * @return
     * @throws Exception
     */
    public List<Object> listarPedidos(String status) throws Exception {
        List<Object> listaPedidos = new ArrayList<>();

        inicializarAtributos();

        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("SELECT * FROM Pedido P "
                + "  join Pedido_Cli  PC on P.idPedido = PC.idPedido "
                + "  join PrecoDistancia pd on pd.idPrecoDistancia = PC.idPrecoDistancia "
                + "  where P.statusPedido = ?;");
        stmt.setString(1, status);

        rs = stmt.executeQuery();

        while (rs.next()) {
            Pedido pedido = new Pedido();
            pedido.setIdPedido(rs.getInt("P.idPedido"));
            pedido.setProtocolo(rs.getString("P.protocolo"));
            pedido.setDataVenda(rs.getDate("P.dataVenda"));
            pedido.setDesconto(rs.getInt("P.desconto"));
            pedido.setStatusPedido(rs.getString("P.statusPedido"));
            pedido.setIdPedido_Cli(rs.getInt("PC.idPedido_Cli"));
            pedido.setIdPrecoDistancia(rs.getInt("PC.idPrecoDistancia"));
            pedido.setDestinoUF(rs.getString("pd.origemDestinoUF"));
            listaPedidos.add(pedido);
        }

        close();
        return listaPedidos;
    }

    /**
     *
     * @param idPedido
     * @return
     * @throws Exception
     */
    public Double retornarDimensaoTotal(Integer idPedido) throws Exception {
        Double dimensaoTotal = 0.0;

        inicializarAtributos();

        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("SELECT SUM(dimensaoCubica) as dimensaoCubica FROM Carga INNER JOIN Pedido_Cli on Carga.idPedido_Cli = pedido_cli.idPedido_Cli WHERE idPedido_Cli = ?;");
        stmt.setInt(1, idPedido);
        rs = stmt.executeQuery();

        if (rs != null && rs.next()) {
            dimensaoTotal = rs.getDouble("dimensaoCubica");
        }
        return dimensaoTotal;
    }

    public void updateIdPedidoCli(Integer idPedido_Cli, String statusPedido) throws Exception {

        inicializarAtributos();
        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("UPDATE Pedido  "
                + " join Pedido_Cli pc on p.idPedido= pc.idPedido"
                + " SET statusPedido = ? WHERE pc.idPedido_Cli = ?;");
        stmt.setString(1, statusPedido);
        stmt.setInt(2, idPedido_Cli);

        stmt.execute();

        con.close();

    }

}
