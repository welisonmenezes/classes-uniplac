/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author welis
 */
public class RelatorioProduto {
    private int codigo;
    private String produto;
    private int entrada;
    private int saida;
    private int estoqueAtual;
    private String fornecedores;
    
    public RelatorioProduto() {}
    
    public RelatorioProduto(int codigo, String produto, int entrada, int saida, int estoqueAtual, String fornecedores) {
        this.codigo = codigo;
        this.produto = produto;
        this.entrada = entrada;
        this.saida = saida;
        this.estoqueAtual = estoqueAtual;
        this.fornecedores = fornecedores;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getEntrada() {
        return entrada;
    }

    public void setEntrada(int entrada) {
        this.entrada = entrada;
    }

    public int getSaida() {
        return saida;
    }

    public void setSaida(int saida) {
        this.saida = saida;
    }

    public int getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(int estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }

    public String getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(String fornecedores) {
        this.fornecedores = fornecedores;
    }
    
    
}
