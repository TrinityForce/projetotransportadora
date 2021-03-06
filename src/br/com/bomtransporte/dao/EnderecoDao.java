package br.com.bomtransporte.dao;

import br.com.bomtransporte.modelo.Cidade;
import br.com.bomtransporte.modelo.Endereco;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JhonattanSouza_
 */
public class EnderecoDao extends Conexao implements Dao {

    /**
     *
     * @param cep
     * @return
     * @throws Exception
     */
    public Endereco retornarEndereco(String cep) throws Exception {

        Endereco endereco = null;
        Cidade cidade = null;

        inicializarAtributos();

        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("SELECT * FROM EnderecoCorreios EC"
                + " JOIN CidadeCorreios CC on EC.idCIdade = CC.id"
                + " WHERE cep = ?;");
        stmt.setString(1, cep);

        rs = stmt.executeQuery();

        while (rs.next()) {
            if (endereco == null) {
                endereco = new Endereco();
            }
            endereco.setId(rs.getInt("EC.id"));
            endereco.setLogradouro(rs.getString("EC.logradouro"));
            endereco.setCep(rs.getString("EC.cep"));
            endereco.setBairro(rs.getString("EC.bairro"));
            endereco.setCidade_id(rs.getInt("EC.idCidade"));
            endereco.setNomeCidade(rs.getString("CC.nome"));
            endereco.setUf(rs.getString("CC.uf"));

//            endereco.setId(rs.getInt("e.id"));
//            endereco.setUf(rs.getString("e.uf"));
//            endereco.setCidade_id(rs.getInt("e.cidade_id"));
//            endereco.setNomeslog(rs.getString("e.nomeslog"));
//            endereco.setNomeclog(rs.getString("e.nomeclog"));
//            endereco.setBairro_id(rs.getInt("e.bairro_id"));
//            endereco.setLogradouro(rs.getString("e.logradouro"));
//            endereco.setCep(rs.getString("e.cep"));
//            endereco.setUf_cod(rs.getInt("e.uf_cod"));
//            endereco.setLogracompl(rs.getString("e.logracompl"));
//            endereco.setBairro(rs.getString("b.nome"));
        }

        con.close();

        return endereco;
    }

    /**
     *
     * @param idCliente
     * @return
     * @throws Exception
     */
    public Endereco retornarEnderecoPorId(Integer idCliente) throws Exception {

        Endereco endereco = null;
        Cidade cidade = null;

        inicializarAtributos();

        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("select * from ClienteEndereco CE inner join enderecocorreios ec on ec.id = ce.idEnderecoCorreios inner join CidadeCorreios cc on ec.idCidade = cc.id where ce.idCliente = ? ;");
        stmt.setInt(1, idCliente);

        rs = stmt.executeQuery();

        while (rs.next()) {
            if (endereco == null) {
                endereco = new Endereco();
            }
            endereco.setId(rs.getInt("EC.id"));
            endereco.setLogradouro(rs.getString("EC.logradouro"));
            endereco.setCep(rs.getString("EC.cep"));
            endereco.setBairro(rs.getString("EC.bairro"));
            endereco.setCidade_id(rs.getInt("EC.idCidade"));
            endereco.setNomeCidade(rs.getString("CC.nome"));
            endereco.setUf(rs.getString("CC.uf"));
        }

        con.close();
        return endereco;
    }

    /**
     *
     * @param idPedido
     * @return
     * @throws Exception
     */
    public Endereco retornarEnderecoPorIdPedido(Integer idPedido) throws Exception {
        Endereco endereco = null;

        inicializarAtributos();
        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("select * from pedido_endereco pe"
                + " join enderecocorreios ec on ec.id = pe.idEnderecoCorreios"
                + " join cidadecorreios cc on cc.id = ec.idCidade"
                + " where pe.idPedido = ?;");
        stmt.setInt(1, idPedido);
        rs = stmt.executeQuery();

        while (rs.next()) {
            if (endereco == null) {
                endereco = new Endereco();
            }
            endereco.setId(rs.getInt("ec.id"));
            endereco.setLogradouro(rs.getString("ec.logradouro"));
            endereco.setCep(rs.getString("ec.cep"));
            endereco.setBairro(rs.getString("ec.bairro"));
            endereco.setCidade_id(rs.getInt("ec.idCidade"));
            endereco.setNomeCidade(rs.getString("cc.nome"));
            endereco.setUf(rs.getString("cc.uf"));
            endereco.setLogradouro(rs.getString("ec.logradouro"));

            con.close();
            return endereco;
        }

        con.close();

        return endereco;
    }

    /**
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public Integer insertReturnId(Object obj) throws Exception {
        Integer idEndereco = null;
        Endereco endereco = (Endereco) obj;
        inicializarAtributos();

        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        stmt = con.prepareStatement("insert into EnderecoCorreios (logradouro,"
                + " cep, bairro, idCidade) values ( ?, ?, ?, 1);", Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, endereco.getLogradouro());
        stmt.setString(2, endereco.getCep());
        stmt.setString(3, endereco.getBairro());
        stmt.execute();

        rs = stmt.getGeneratedKeys();
        rs.next();
        idEndereco = rs.getInt(1);

        return idEndereco;
    }

    /**
     *
     * @param obj
     * @throws Exception
     */
    @Override
    public void inserir(Object obj) throws Exception {
        Endereco endereco = (Endereco) obj;
        try {
            inicializarAtributos();
            con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            con.setAutoCommit(false);

            stmt = con.prepareStatement("select id from cidadeCorreios where uf = ? and nome =?",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, endereco.getUf());
            stmt.setString(2, endereco.getNomeCidade());

            rs = stmt.executeQuery();

            while (rs.next()) {
                endereco.setCidade_id(rs.getInt("id"));
            }

            stmt = con.prepareStatement("insert into EnderecoCorreios"
                    + " (logradouro, cep, bairro, idCidade)"
                    + " values"
                    + " (?, ?, ?, ?);");
            stmt.setString(1, endereco.getLogradouro());
            stmt.setString(2, endereco.getCep());
            stmt.setString(3, endereco.getBairro());
            stmt.setInt(4, endereco.getCidade_id());
            stmt.execute();

            con.commit();
            close();
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
        List<Object> listaEndereco = new ArrayList();
        Endereco endereco = null;
        Integer row = 0;

        inicializarAtributos();

        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("select * from enderecos e inner join bairros b on e.bairro_id = b.id;");

        rs = stmt.executeQuery();

        while (rs.next()) {
            row++;
            endereco = new Endereco();

            endereco.setId(rs.getInt("e.id"));
            endereco.setUf(rs.getString("e.uf"));
            endereco.setCidade_id(rs.getInt("e.cidade_id"));
            endereco.setNomeslog(rs.getString("e.nomeslog"));
            endereco.setNomeclog(rs.getString("e.nomeclog"));
            endereco.setBairro_id(rs.getInt("e.bairro_id"));
            endereco.setLogradouro(rs.getString("e.logradouro"));
            endereco.setCep(rs.getString("e.cep"));
            endereco.setUf_cod(rs.getInt("e.uf_cod"));
            endereco.setLogracompl(rs.getString("e.logracompl"));
            endereco.setBairro(rs.getString("b.nome"));

            listaEndereco.add(endereco);

        }

        close();
        System.out.println("numero de rows: " + row);
        return listaEndereco;
    }

}
