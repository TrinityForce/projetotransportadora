package br.com.bomtransporte.dao;

import br.com.bomtransporte.modelo.Cliente;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andre
 */
public class ClienteDao extends Conexao implements Dao {

    @Override
    public void inserir(Object obj) throws Exception {

        Cliente cliente = (Cliente) obj;
        try {
            inicializarAtributos();
            con.setAutoCommit(false);

            con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            stmt = con.prepareStatement("INSERT INTO Pessoa (nome,dataCadastro) values  (?,? );", Statement.RETURN_GENERATED_KEYS);
            // con -> sql ("insert");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getDataCadastro());
            stmt.execute();

            rs = stmt.getGeneratedKeys();
            rs.next();
            int idPessoa = rs.getInt(1);
            rs = null;

            stmt = con.prepareStatement("INSERT INTO contato (telefone,telefone2,celular) values (?,?,?);", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, cliente.getTelefone());
            stmt.setString(2, cliente.getTelefone2());
            stmt.setString(3, cliente.getCelular());

            stmt.execute();
            rs = stmt.getGeneratedKeys();
            rs.next();
            int idContato = rs.getInt(1);
            rs = null;

            stmt = con.prepareStatement("INSERT INTO cliente (cpf,idPessoa,idContato)values (?," + idPessoa + "," + idContato + ");", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, cliente.getCpf());

            stmt.execute();
            rs = stmt.getGeneratedKeys();
            rs.next();
            int idCliente = rs.getInt(1);

            stmt = con.prepareStatement("INSERT INTO ClienteEndereco (idCliente, idEnderecoCorreios, numeroCasa, complemento) values(" + idCliente + " ,?,?,?);");
            stmt.setInt(1, cliente.getIdEndereco());
            stmt.setString(2, cliente.getNumeroCasa());
            stmt.setString(3, cliente.getComplemento());
            stmt.execute();

            con.commit();
            close();
        } catch (SQLException sqlex) {
            con.rollback();
        } finally {
            close();
        }
    }

    @Override
    public void alterar(Object obj) throws Exception {

        Cliente cliente = (Cliente) obj;

        try {
            inicializarAtributos();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            stmt = con.prepareStatement("UPDATE PESSOA SET NOME = ? WHERE IDPESSOA = ? ;");
            stmt.setString(1, cliente.getNome());
            stmt.setInt(2, cliente.getIdPessoa());
            stmt.execute();

            stmt = con.prepareStatement("UPDATE CONTATO SET TELEFONE = ?, TELEFONE2 = ?, CELULAR = ? WHERE IDCONTATO = ? ;");
            stmt.setString(1, cliente.getTelefone());
            stmt.setString(2, cliente.getTelefone2());
            stmt.setString(3, cliente.getCelular());
            stmt.setInt(4, cliente.getIdContato());
            stmt.execute();

            stmt = con.prepareStatement("UPDATE CLIENTEENDERECO SET IDENDERECOCORREIOS = ?, NUMEROCASA = ?, COMPLEMENTO = ? WHERE IDCLIENTE = ?;");
            stmt.setInt(1, cliente.getIdEndereco());
            stmt.setString(2, cliente.getNumeroCasa());
            stmt.setString(3, cliente.getComplemento());
            stmt.setInt(4, cliente.getIdCliente());
            stmt.execute();

            con.commit();
            close();
        } catch (SQLException sqlex) {
            con.rollback();
            System.out.println(sqlex.getMessage());
        } finally {
            con.close();
        }
    }

    @Override
    public void excluir(Object obj) throws Exception {

    }

    @Override
    public List<Object> listar() throws Exception {

        List<Object> cliente = new ArrayList<>();

        inicializarAtributos();

        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("select * from pessoa p  "
                + " inner join cliente c on p.idPessoa = c.idPessoa "
                + " inner join contato co on c.idContato = co.idContato  "
                + " inner join ClienteEndereco ce on c.idCliente = ce.idCliente"
                + " inner join EnderecoCorreios e on e.id = ce.idEnderecoCorreios"
                + " order by c.idCliente;");

        rs = stmt.executeQuery();

        while (rs.next()) {

            Cliente c = new Cliente();

            c.setCpf(rs.getString("c.cpf"));
            c.setDataCadastro(rs.getString("p.dataCadastro"));
            c.setNome(rs.getString("p.nome"));
            c.setNumeroCasa(rs.getString("ce.numeroCasa"));
            c.setComplemento(rs.getString("ce.complemento"));
            c.setIdCliente(rs.getInt("c.idCliente"));
            c.setIdEndereco(rs.getInt("e.id"));
            c.setTelefone(rs.getString("co.telefone"));
            c.setTelefone2(rs.getString("co.telefone2"));
            c.setCelular(rs.getString("co.celular"));
            c.setIdPessoa(rs.getInt("p.idPessoa"));
            c.setIdContato(rs.getInt("co.idContato"));

            cliente.add(c);
        }

        // testando e funcionou
        // System.out.println("Cpf: "+rs.getString("cpf")+"\n Data Cadastro: "+rs.getString("dataCadastro"));
        con.close();

        return cliente;

    }

    public List<Object> consultarCliente(String query) throws SQLException, Exception {

        List<Object> listaCliente = new ArrayList<>();

        inicializarAtributos();

        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("select * from pessoa p  "
                + " inner join cliente c on p.idPessoa = c.idPessoa "
                + " inner join contato co on c.idContato = co.idContato  "
                + " inner join ClienteEndereco ce on c.idCliente = ce.idCliente"
                + " inner join EnderecoCorreios e on e.id = ce.idEnderecoCorreios"
                + " where p.nome like ? or c.cpf = ?;");

        query += "%";

        stmt.setString(1, query);
        stmt.setString(2, query);

        rs = stmt.executeQuery();

        while (rs.next()) {

            Cliente c = new Cliente();

            c.setCpf(rs.getString("c.cpf"));
            c.setDataCadastro(rs.getString("p.dataCadastro"));
            c.setNome(rs.getString("p.nome"));
            c.setNumeroCasa(rs.getString("ce.numeroCasa"));
            c.setComplemento(rs.getString("ce.complemento"));
            c.setIdCliente(rs.getInt("c.idCliente"));
            c.setIdEndereco(rs.getInt("e.id"));
            c.setTelefone(rs.getString("co.telefone"));
            c.setTelefone2(rs.getString("co.telefone2"));
            c.setCelular(rs.getString("co.celular"));
            c.setIdPessoa(rs.getInt("p.idPessoa"));
            c.setIdContato(rs.getInt("co.idContato"));

            listaCliente.add(c);

        }
        con.close();
        return listaCliente;

    }

    public List<Object> consultarClientePeloCpf(String query) throws SQLException, Exception {

        List<Object> listaCliente = new ArrayList<>();

        inicializarAtributos();

        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("select * from pessoa p  "
                + " inner join cliente c on p.idPessoa = c.idPessoa "
                + " inner join contato co on c.idContato = co.idContato  "
                + " inner join ClienteEndereco ce on c.idCliente = ce.idCliente"
                + " inner join EnderecoCorreios e on e.id = ce.idEnderecoCorreios"
                + " where  c.cpf like ?;");

        query += "%";

        stmt.setString(1, query);
        rs = stmt.executeQuery();

        while (rs.next()) {

            Cliente c = new Cliente();

            c.setCpf(rs.getString("c.cpf"));
            c.setDataCadastro(rs.getString("p.dataCadastro"));
            c.setNome(rs.getString("p.nome"));
            c.setNumeroCasa(rs.getString("ce.numeroCasa"));
            c.setComplemento(rs.getString("ce.complemento"));
            c.setIdCliente(rs.getInt("c.idCliente"));
            c.setIdEndereco(rs.getInt("e.id"));
            c.setTelefone(rs.getString("co.telefone"));
            c.setTelefone2(rs.getString("co.telefone2"));
            c.setCelular(rs.getString("co.celular"));
            c.setIdPessoa(rs.getInt("p.idPessoa"));
            c.setIdContato(rs.getInt("co.idContato"));

            listaCliente.add(c);

        }
        con.close();
        return listaCliente;

    }

    public Cliente consultarPorId(Integer id) throws Exception {
        Cliente cliente = null;

        inicializarAtributos();

        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("select * from pessoa p  inner join cliente c on p.idPessoa = c.idPessoa inner join contato co on c.idContato = co.idContato  inner join ClienteEndereco ce on c.idCliente = ce.idCliente inner join EnderecoCorreios e on e.id = ce.idEnderecoCorreios where c.idCliente = " + id + " ;");

        rs = stmt.executeQuery();

        while (rs.next()) {

            cliente = new Cliente();

            cliente.setCpf(rs.getString("c.cpf"));
            cliente.setDataCadastro(rs.getString("p.dataCadastro"));
            cliente.setNome(rs.getString("p.nome"));
            cliente.setNumeroCasa(rs.getString("ce.numeroCasa"));
            cliente.setComplemento(rs.getString("ce.complemento"));
            cliente.setIdCliente(rs.getInt("c.idCliente"));
            cliente.setIdEndereco(rs.getInt("ce.idEnderecoCorreios"));
            cliente.setTelefone(rs.getString("co.telefone"));
            cliente.setTelefone2(rs.getString("co.telefone2"));
            cliente.setCelular(rs.getString("co.celular"));
            cliente.setIdPessoa(rs.getInt("p.idPessoa"));
            cliente.setIdContato(rs.getInt("co.idContato"));
        }

        con.close();
        return cliente;
    }

    public Integer verificarExiste(String nome) throws Exception {
        Integer idCliente = null;
        inicializarAtributos();
        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("select PE.nome, C.idCliente"
                + " FROM pessoa PE"
                + " INNER JOIN cliente C ON PE.idPessoa = C.idPessoa"
                + " WHERE  PE.nome like '" + nome + "';");
        rs = stmt.executeQuery();

        if (rs != null && rs.next()) {
            if ((nome).equals(rs.getString("PE.nome"))) {
                idCliente = rs.getInt(2);
            }
        }

        con.close();
        return idCliente;

    }

    public boolean verificarCPF(String cpf) throws Exception {
        inicializarAtributos();
        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("SELECT CPF FROM CLIENTE WHERE CPF = ?");
        stmt.setString(1, cpf);
        rs = stmt.executeQuery();
        if (rs != null && rs.next()) {
            return true;
        }
        con.close();
        return false;
    }

    public String ConsultarUF(Integer idCliente) throws Exception {
        String uf = null;

        inicializarAtributos();
        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("select CC.uf"
                + " from cliente C"
                + " Join ClienteEndereco CE ON C.idCliente = CE.idCliente"
                + " Join EnderecoCorreios EC ON CE.idEnderecoCorreios = EC.id"
                + " join CidadeCorreios CC on EC.idCidade = CC.id"
                + " where C.idCliente = ?;");
        stmt.setInt(1, idCliente);
        rs = stmt.executeQuery();

        if (rs != null && rs.next()) {
            uf = rs.getString("CC.uf");
        }
        con.close();
        return uf;

    }

}
