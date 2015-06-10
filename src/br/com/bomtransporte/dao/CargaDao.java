package br.com.bomtransporte.dao;

import br.com.bomtransporte.modelo.Carga;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CargaDao extends Conexao implements Dao{

    @Override
    public void inserir(Object obj) throws Exception {
        Carga carga = (Carga) obj;
        
        inicializarAtributos();
        
        con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        
        stmt = con.prepareStatement("INSERT INTO CARGA(descricao,peso,quantidade) VALUES(?,?,?)");
        stmt.setString(1, carga.getDescricao());
        stmt.setDouble(2, carga.getPeso());
        stmt.setInt(3, carga.getQuantidade());
        
        stmt.execute();
        
        con.close();
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
        
        while(rs.next()){
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
    
}
