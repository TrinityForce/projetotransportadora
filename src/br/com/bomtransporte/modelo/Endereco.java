package br.com.bomtransporte.modelo;

/**
 *
 * @author JhonattanSouza_
 */
public class Endereco {
    private Integer id;
    private String uf;
    private Integer cidade_id;
    private String nomeCidade;
    private String nomeslog;
    private String nomeclog;
    private Integer bairro_id;
    private String logradouro;
    private String cep;
    private Integer uf_cod;
    private String logracompl;
    private String bairro;

    public Endereco() {
    }


    public Endereco(Integer id, String uf, Integer cidade_id, String nomeCidade, String nomeslog, String nomeclog, Integer bairro_id, String logradouro, String cep, Integer uf_cod, String logracompl, String bairro) {
        this.id = id;
        this.uf = uf;
        this.cidade_id = cidade_id;
        this.nomeCidade = nomeCidade;
        this.nomeslog = nomeslog;
        this.nomeclog = nomeclog;
        this.bairro_id = bairro_id;
        this.logradouro = logradouro;
        this.cep = cep;
        this.uf_cod = uf_cod;
        this.logracompl = logracompl;
        this.bairro = bairro;
    }



    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the uf
     */
    public String getUf() {
        return uf;
    }

    /**
     * @param uf the uf to set
     */
    public void setUf(String uf) {
        this.uf = uf;
    }

    /**
     * @return the cidade_id
     */
    public Integer getCidade_id() {
        return cidade_id;
    }

    /**
     * @param cidade_id the cidade_id to set
     */
    public void setCidade_id(Integer cidade_id) {
        this.cidade_id = cidade_id;
    }

    /**
     * @return the nomeslog
     */
    public String getNomeslog() {
        return nomeslog;
    }

    /**
     * @param nomeslog the nomeslog to set
     */
    public void setNomeslog(String nomeslog) {
        this.nomeslog = nomeslog;
    }

    /**
     * @return the nomeclog
     */
    public String getNomeclog() {
        return nomeclog;
    }

    /**
     * @param nomeclog the nomeclog to set
     */
    public void setNomeclog(String nomeclog) {
        this.nomeclog = nomeclog;
    }

    /**
     * @return the bairro_id
     */
    public Integer getBairro_id() {
        return bairro_id;
    }

    /**
     * @param bairro_id the bairro_id to set
     */
    public void setBairro_id(Integer bairro_id) {
        this.bairro_id = bairro_id;
    }

    /**
     * @return the logradouro
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * @param logradouro the logradouro to set
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @return the uf_cod
     */
    public Integer getUf_cod() {
        return uf_cod;
    }

    /**
     * @param uf_cod the uf_cod to set
     */
    public void setUf_cod(Integer uf_cod) {
        this.uf_cod = uf_cod;
    }

    /**
     * @return the logracompl
     */
    public String getLogracompl() {
        return logracompl;
    }

    /**
     * @param logracompl the logracompl to set
     */
    public void setLogracompl(String logracompl) {
        this.logracompl = logracompl;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }
    
    
    
}
