/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bomtransporte.modelo;

import java.util.Date;

/**
 *
 * @author Gustavo Carvalho <gustavo.carvalho.costa@outlook.com>
 */
public class Pedido {

    private Integer idPedido;
    private String protocolo;
    private Date dataVenda;
    private Integer desconto;
    private String statusPedido;
    private String numero;
    private String complemento;
    private Integer idEnderecoCorreios;
    private Integer idPedido_Cli;
    private Integer idPrecoDistancia;
    private String destinoUF;

    /**
     *
     */
    public Pedido() {
    }

    /**
     *
     * @param idPedido
     * @param protocolo
     * @param dataVenda
     * @param desconto
     * @param statusPedido
     * @param numero
     * @param complemento
     * @param idEnderecoCorreios
     * @param idPedido_Cli
     * @param idPrecoDistania
     * @param destinoUF
     */
    public Pedido(Integer idPedido, String protocolo, Date dataVenda, Integer desconto, String statusPedido, String numero, String complemento, Integer idEnderecoCorreios, Integer idPedido_Cli, Integer idPrecoDistancia, String destinoUF) {
        this.idPedido = idPedido;
        this.protocolo = protocolo;
        this.dataVenda = dataVenda;
        this.desconto = desconto;
        this.statusPedido = statusPedido;
        this.numero = numero;
        this.complemento = complemento;
        this.idEnderecoCorreios = idEnderecoCorreios;
        this.idPedido_Cli = idPedido_Cli;
        this.idPrecoDistancia = idPrecoDistancia;
        this.destinoUF = destinoUF;
    }

    /**
     *
     * @return
     */
    public Integer getIdPedido() {
        return idPedido;
    }

    /**
     *
     * @param idPedido
     */
    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    /**
     *
     * @return
     */
    public String getProtocolo() {
        return protocolo;
    }

    /**
     *
     * @param protocolo
     */
    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    /**
     *
     * @return
     */
    public Integer getDesconto() {
        return desconto;
    }

    /**
     *
     * @param desconto
     */
    public void setDesconto(Integer desconto) {
        this.desconto = desconto;
    }

    /**
     *
     * @return
     */
    public String getStatusPedido() {
        return statusPedido;
    }

    /**
     *
     * @param statusPedido
     */
    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }

    /**
     *
     * @return
     */
    public String getNumero() {
        return numero;
    }

    /**
     *
     * @param numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     *
     * @return
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     *
     * @param complemento
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     *
     * @return
     */
    public Integer getIdEnderecoCorreios() {
        return idEnderecoCorreios;
    }

    /**
     *
     * @param idEnderecoCorreios
     */
    public void setIdEnderecoCorreios(Integer idEnderecoCorreios) {
        this.idEnderecoCorreios = idEnderecoCorreios;
    }

    /**
     * @return the idPedido_Cli
     */
    public Integer getIdPedido_Cli() {
        return idPedido_Cli;
    }

    /**
     * @param idPedido_Cli the idPedido_Cli to set
     */
    public void setIdPedido_Cli(Integer idPedido_Cli) {
        this.idPedido_Cli = idPedido_Cli;
    }

    /**
     *
     * @return
     */
    public Integer getIdPrecoDistancia() {
        return idPrecoDistancia;
    }

    /**
     *
     * @param idPrecoDistancia
     */
    public void setIdPrecoDistancia(Integer idPrecoDistancia) {
        this.idPrecoDistancia = idPrecoDistancia;
    }

    /**
     *
     * @return
     */
    public Date getDataVenda() {
        return dataVenda;
    }

    /**
     *
     * @param dataVenda
     */
    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getDestinoUF() {
        return destinoUF;
    }

    public void setDestinoUF(String destinoUF) {
        this.destinoUF = destinoUF;
    }

}
