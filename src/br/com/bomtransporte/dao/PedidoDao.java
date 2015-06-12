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
import java.util.ArrayList;
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
       return null;
    }

    @Override
    public void inserir(Object obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Object> listarPedidos(Integer idCliente) throws Exception{
        List<Object> listaPedidos = new ArrayList<>();
        
        inicializarAtributos();
        
        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        
        stmt = con.prepareStatement("SELECT * FROM Pedido P inner join Pedido_Cli  PC on P.idPedido = PC.idPedido where idCliente = ?;");
        stmt.setInt(1,idCliente);
        
        rs = stmt.executeQuery();
        
        while(rs.next()){
            Pedido pedido = new Pedido();
            pedido.setIdPedido(rs.getInt("P.idPedido"));
            pedido.setProtocolo(rs.getString("P.protocolo"));
            pedido.setDataVenda(rs.getString("P.dataVenda"));
            pedido.setDesconto(rs.getInt("P.desconto"));
            pedido.setStatusPedido(rs.getString("P.statusPedido"));
            pedido.setIdPedido_Cli(rs.getInt("PC.idPedido_Cli"));
            listaPedidos.add(pedido);
        }
        
        close();
        return listaPedidos;
    }
    
    public Object buscarPedido (Integer idPedido) throws Exception {
        Pedido pedido = null;
        
        inicializarAtributos();
        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        
        stmt = con.prepareStatement(" Select * From Pedido p" +
    " join Pedido_Endereco pe on pe.idPedido = p.idPedido" +
    " join EnderecoCorreios ec on ec.id = pe.idEnderecoCorreios" +
    " where p.idPedido =?;");
        stmt.setInt(1, idPedido);
        rs = stmt.executeQuery();
        
        while(rs.next()){
           pedido = new Pedido();
            pedido.setIdPedido(rs.getInt("P.idPedido"));
            pedido.setProtocolo(rs.getString("P.protocolo"));
            pedido.setDataVenda(rs.getString("P.dataVenda"));
            pedido.setDesconto(rs.getInt("P.desconto"));
            pedido.setStatusPedido(rs.getString("P.statusPedido"));
            pedido.setIdPedido_Cli(rs.getInt("PC.idPedido_Cli"));
            
        }
        
        close();
        
        return pedido;
    }
    
    public void update(Integer idPedido, String statusPedido) throws Exception{
        
        inicializarAtributos();
        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        
        stmt = con.prepareStatement("UPDATE Pedido " +
            " SET statusPedido = ? WHERE idPedido = ?;");
        stmt.setString(1, statusPedido);
        stmt.setInt(2, idPedido);
        
        stmt.execute();
        
        con.close();
        
    }
}
