/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bomtransporte.dao;

import br.com.bomtransporte.modelo.Carga;
import br.com.bomtransporte.modelo.Cliente;
import br.com.bomtransporte.modelo.Pedido;
import br.com.bomtransporte.modelo.PrecoDistancia;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Gustavo Carvalho <gustavo.carvalho.costa@outlook.com>
 */
public class PedidoDao extends Conexao implements Dao {

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
            stmt.setString(2, pedido.getDataVenda());
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
            e.printStackTrace();

        } finally {
            if (con != null && !con.isClosed()) {
                con.close();

            }
        }
        return null;
    }

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
            e.printStackTrace();

        } finally {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        }
        return pedidoKey;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void inserir(Object obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
