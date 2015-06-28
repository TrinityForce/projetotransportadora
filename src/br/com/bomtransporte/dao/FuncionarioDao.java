package br.com.bomtransporte.dao;

import br.com.bomtransporte.modelo.Funcionario;
import br.com.bomtransporte.modelo.Usuario;
import br.com.bomtransporte.regrasnegocio.FuncionarioRN;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JhonattanSouza_
 */
public class FuncionarioDao extends Conexao implements Dao {

    /**
     *
     * @param obj
     * @throws Exception
     */
    @Override
    public void inserir(Object obj) throws Exception {
        try {
            Funcionario funcionario = (Funcionario) obj;

            inicializarAtributos();

            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            con.setAutoCommit(false);

            stmt = con.prepareStatement("INSERT INTO Pessoa(nome,dataCadastro) values(?,?);", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getDataCadastro());
            stmt.execute();

            rs = stmt.getGeneratedKeys();
            rs.next();
            Integer idPessoa = rs.getInt(1);
            rs = null;

            stmt = con.prepareStatement("INSERT INTO Usuario(login,senha,idPerfil,isAlterarSenha,email) values( ? , ? , ? , ? ,?);", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, funcionario.getUsuario().getLogin());
            stmt.setString(2, FuncionarioRN.criptografarMd5(funcionario.getUsuario().getSenha()));
            stmt.setInt(3, funcionario.getUsuario().getIdPerfil());
            stmt.setBoolean(4, funcionario.getUsuario().getAlterarSenha());
            stmt.setString(5, funcionario.getUsuario().getEmail());
            stmt.execute();

            rs = stmt.getGeneratedKeys();
            rs.next();
            int idUsuario = rs.getInt(1);

            stmt = con.prepareStatement("INSERT INTO Funcionario(cargo,idPessoa,idUsuario) values(?," + idPessoa + "," + idUsuario + ")");
            stmt.setString(1, funcionario.getCargo());
            stmt.execute();

            con.commit();
            close();
        } catch (SQLException sqlex) {
            con.rollback();
        }finally{
            close();
        }
    }

    /**
     *
     * @param obj
     * @throws Exception
     */
    @Override
    public void alterar(Object obj) throws Exception {
        try {
            Funcionario funcionario = (Funcionario) obj;

            inicializarAtributos();

            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            con.setAutoCommit(false);

            stmt = con.prepareStatement("UPDATE Usuario set login = ? ,idPerfil = ?, email = ? where idUsuario = ?;");
            stmt.setString(1, funcionario.getUsuario().getLogin());
            stmt.setInt(2, funcionario.getUsuario().getIdPerfil());
            stmt.setString(3, funcionario.getUsuario().getEmail());
            stmt.setInt(4, funcionario.getUsuario().getIdUsuario());
            stmt.execute();

            stmt = con.prepareStatement("Update Pessoa set nome = ? where idPessoa = ?;");
            stmt.setString(1, funcionario.getNome());
            stmt.setInt(2, funcionario.getIdPessoa());
            stmt.execute();

            stmt = con.prepareStatement("Update Funcionario set cargo = ? where idFuncionario = ?;");
            stmt.setString(1, funcionario.getCargo());
            stmt.setInt(2, funcionario.getIdFuncionario());
            stmt.execute();

            con.commit();
            close();
        } catch (SQLException sqlex) {
            con.rollback();
        }finally{
            close();
        }
    }

    /**
     *
     * @param obj
     * @throws Exception
     */
    @Override
    public void excluir(Object obj) throws Exception {
        Funcionario funcionario = (Funcionario) obj;

        inicializarAtributos();

        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("delete pessoa , funcionario, usuario from pessoa inner join funcionario on funcionario.idpessoa = pessoa.idpessoa inner join usuario on usuario.idusuario = funcionario.idusuario where funcionario.idFuncionario = ?;");
        stmt.setInt(1, funcionario.getIdFuncionario());
        stmt.execute();

        close();
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Object> listar() throws Exception {
        List<Object> listaFuncionarios = new ArrayList();

        //Inicializa os atributos responsáveis por manipulação de SQL e abre a conexão.
        inicializarAtributos();

        //Query (SQL)
        stmt = con.prepareStatement("select * from Pessoa P inner join Funcionario F on F.idPessoa = P.idPessoa inner join Usuario U on F.idUsuario = U.idUsuario inner join Perfil Pe on U.idPerfil = Pe.idPerfil order by idFuncionario");

        //Executa s Query (SQL)
        rs = stmt.executeQuery();

        //Se houver retorno de registros, percorre o objeto ResultSet(rs)
        //para extrair os dados consultados na Base de Dados
        while (rs.next()) {

            //Preenche o objeto que retornou do banco de dados
            Funcionario funcionario = new Funcionario();
            Usuario u = new Usuario();
            funcionario.setUsuario(u);
            funcionario.setIdPessoa(rs.getInt("P.idPessoa"));
            funcionario.setNome(rs.getString("P.nome"));
            funcionario.setDataCadastro(rs.getString("P.dataCadastro"));
            funcionario.setIdFuncionario(rs.getInt("F.idFuncionario"));
            funcionario.setCargo(rs.getString("F.cargo"));
            funcionario.getUsuario().setEmail(rs.getString("U.email"));
            funcionario.getUsuario().setLogin(rs.getString("U.login"));
            funcionario.getUsuario().setIdUsuario(rs.getInt("U.idUsuario"));
            funcionario.getUsuario().setIdPerfil(rs.getInt("Pe.idPerfil"));

            //Adiciona o objeto na lista
            listaFuncionarios.add(funcionario);
        }

        //Fecha a conexão
        close();

        return listaFuncionarios;
    }

    /**
     *
     * @param email
     * @return
     * @throws Exception
     */
    public Funcionario consultarEmail(String email) throws Exception {
        Funcionario funcionario = null;

        inicializarAtributos();

        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("SELECT P.idPessoa, P.Nome, F.Cargo, F.idFuncionario, F.idUsuario From Pessoa P INNER JOIN Funcionario F ON P.idPessoa = F.idPessoa INNER JOIN Usuario U ON F.idUsuario = U.idUsuario WHERE U.Email = ?");
        stmt.setString(1, email);
        
        rs = stmt.executeQuery();

        while (rs.next()) {

            if (funcionario == null) {
                funcionario = new Funcionario();
                Usuario u = new Usuario();
                funcionario.setUsuario(u);
            }
            funcionario.getUsuario().setIdUsuario(rs.getInt("F.idUsuario"));
            funcionario.setNome(rs.getString("P.nome"));
            funcionario.setIdPessoa(rs.getInt("P.idPessoa"));
            funcionario.setIdFuncionario(rs.getInt("F.idFuncionario"));
            funcionario.setCargo(rs.getString("F.cargo"));
        }
        close();
        return funcionario;
    }

    /**
     *
     * @param obj
     * @param novaSenha
     * @param flag
     * @throws Exception
     */
    public void alterarSenha(Object obj, String novaSenha, Boolean flag) throws Exception {
        Funcionario funcionario = (Funcionario) obj;

        inicializarAtributos();

        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("UPDATE Usuario set senha = ?, isAlterarSenha = ? where idUsuario = ?");
        stmt.setString(1, FuncionarioRN.criptografarMd5(novaSenha));
        stmt.setBoolean(2, flag);
        stmt.setInt(3, funcionario.getUsuario().getIdUsuario());
        stmt.execute();

        close();
    }

    /**
     *
     * @param query
     * @return
     * @throws Exception
     */
    public List<Object> consultarFuncionarioPorNome(String query) throws Exception {
        List<Object> listaFuncionarios = new ArrayList();

        inicializarAtributos();

        stmt = con.prepareStatement("select * from Pessoa p inner join Funcionario F on P.idPessoa = F.idPessoa inner join usuario u on u.idUsuario = f.idUsuario inner join perfil pe on pe.idPerfil = u.idPerfil where p.nome like ?;");
        query += "%";
        stmt.setString(1, query);

        rs = stmt.executeQuery();

        while (rs.next()) {
            Funcionario funcionario = new Funcionario();
            Usuario u = new Usuario();
            funcionario.setUsuario(u);
            funcionario.setIdPessoa(rs.getInt("P.idPessoa"));
            funcionario.setNome(rs.getString("P.nome"));
            funcionario.setDataCadastro(rs.getString("P.dataCadastro"));
            funcionario.setIdFuncionario(rs.getInt("F.idFuncionario"));
            funcionario.setCargo(rs.getString("F.cargo"));
            funcionario.getUsuario().setEmail(rs.getString("U.email"));
            funcionario.getUsuario().setLogin(rs.getString("U.login"));
            funcionario.getUsuario().setIdUsuario(rs.getInt("U.idUsuario"));
            funcionario.getUsuario().setIdPerfil(rs.getInt("Pe.idPerfil"));

            listaFuncionarios.add(funcionario);
        }

        close();

        return listaFuncionarios;
    }
    
    /**
     *
     * @param obj
     * @throws Exception
     */
    public void alterarSenha(Object obj) throws Exception {
        Funcionario funcionario = (Funcionario) obj;
        inicializarAtributos();
        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        stmt = con.prepareStatement("UPDATE Usuario set senha = ? where idUsuario = ?");
        stmt.setString(1, FuncionarioRN.criptografarMd5(funcionario.getUsuario().getSenha()));
        stmt.setInt(2, funcionario.getUsuario().getIdUsuario());
        stmt.execute();
        close();
    }

    /**
     *
     * @param login
     * @param senha
     * @return
     * @throws Exception
     */
    public Funcionario autenticar(String login, String senha) throws Exception {
        Funcionario funcionario = null;

        inicializarAtributos();

        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("select * from Pessoa p inner join Funcionario F on P.idPessoa = F.idPessoa inner join usuario u on u.idUsuario = f.idUsuario inner join perfil pe on pe.idPerfil = u.idPerfil where U.login = ? and U.senha = ?;");

        stmt.setString(1, login);
        stmt.setString(2, FuncionarioRN.criptografarMd5(senha));

        rs = stmt.executeQuery();
        while (rs.next()) {
            funcionario = new Funcionario();
            Usuario u = new Usuario();
            funcionario.setUsuario(u);
            funcionario.setIdPessoa(rs.getInt("P.idPessoa"));
            funcionario.setNome(rs.getString("P.nome"));
            funcionario.setDataCadastro(rs.getString("P.dataCadastro"));
            funcionario.setIdFuncionario(rs.getInt("F.idFuncionario"));
            funcionario.setCargo(rs.getString("F.cargo"));
            funcionario.getUsuario().setEmail(rs.getString("U.email"));
            funcionario.getUsuario().setLogin(rs.getString("U.login"));
            funcionario.getUsuario().setIdUsuario(rs.getInt("U.idUsuario"));
            funcionario.getUsuario().setSenha(rs.getString("u.senha"));
            funcionario.getUsuario().setIdPerfil(rs.getInt("Pe.idPerfil"));
            funcionario.getUsuario().setAlterarSenha(rs.getBoolean("U.isAlterarSenha"));
        }

        close();

        return funcionario;
    }
    
    public boolean verificarUsuario(String usuario) throws Exception {
        inicializarAtributos();
        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("SELECT LOGIN FROM USUARIO WHERE LOGIN = ?");
        stmt.setString(1, usuario);
        rs = stmt.executeQuery();
        if (rs != null && rs.next()) {
            return true;
        }
        con.close();
        return false;
    }
    
    public boolean verificarEmail(String email) throws Exception {
        inicializarAtributos();
        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt = con.prepareStatement("SELECT EMAIL FROM USUARIO WHERE EMAIL = ?");
        stmt.setString(1, email);
        rs = stmt.executeQuery();
        if (rs != null && rs.next()) {
            return true;
        }
        con.close();
        return false;
    }
}
