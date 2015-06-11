/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.bomtransporte.modelo;

/**
 * 
 * @author Gustavo Carvalho <gustavo.carvalho.costa@outlook.com>
 */
public class Pedido {
    
    
    private Integer idPedido;
    private String protocolo;
    private String dataVenda;
    private String desconto;
    private String statusPedido;
    private String numero;
    private String complemento;
    private Integer idEnderecoCorreios;

    public Pedido() {
    }

    
    
    public Pedido(Integer idPedido, String protocolo, String dataVenda, String desconto, String statusPedido, String numero, String complemento, Integer idEnderecoCorreios) {
        this.idPedido = idPedido;
        this.protocolo = protocolo;
        this.dataVenda = dataVenda;
        this.desconto = desconto;
        this.statusPedido = statusPedido;
        this.numero = numero;
        this.complemento = complemento;
        this.idEnderecoCorreios = idEnderecoCorreios;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getDesconto() {
        return desconto;
    }

    public void setDesconto(String desconto) {
        this.desconto = desconto;
    }

    public String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Integer getIdEnderecoCorreios() {
        return idEnderecoCorreios;
    }

    public void setIdEnderecoCorreios(Integer idEnderecoCorreios) {
        this.idEnderecoCorreios = idEnderecoCorreios;
    }
    
    
    
    
    

}
