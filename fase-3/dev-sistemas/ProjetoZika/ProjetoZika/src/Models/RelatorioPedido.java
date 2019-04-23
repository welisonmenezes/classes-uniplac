/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 * Modelo para cada item do Relatório de pedidos
 * @author welison
 */
public class RelatorioPedido {
    private int codigo;
    private String soliciante;
    private String aprovador;
    private String status;
    private String data;
    private int total;
    private String produtos;
    
    public RelatorioPedido() {}
    
    public RelatorioPedido(int codigo, String solicitante, String aprovador, String status, String data, int total, String produtos) {
        this.codigo = codigo;
        this.soliciante = solicitante;
        this.aprovador = aprovador;
        this.status = status;
        this.data = data;
        this.total = total;
        this.produtos = produtos;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getSoliciante() {
        return soliciante;
    }

    public void setSoliciante(String soliciante) {
        this.soliciante = soliciante;
    }

    public String getAprovador() {
        return aprovador;
    }

    public void setAprovador(String aprovador) {
        this.aprovador = aprovador;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getProdutos() {
        return produtos;
    }

    public void setProdutos(String produtos) {
        this.produtos = produtos;
    }
    
}
