package br.com.bomtransporte.dao;

import java.util.List;

/**
 *
 * @author JhonattanSouza_
 */
public interface Dao {

    public void inserir(Object obj) throws Exception;

    public void alterar(Object obj) throws Exception;

    public void excluir(Object obj) throws Exception;

    public List<Object> listar() throws Exception;
}
