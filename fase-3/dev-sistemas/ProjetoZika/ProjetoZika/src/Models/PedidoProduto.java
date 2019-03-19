/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Welison
 */
public class PedidoProduto {
    private Produto Produto;
    private int Id;
    private int Quantidade;
    private int QuantidadeAprovada;
    private String Status;
    
    public PedidoProduto() {}
    
    public PedidoProduto(Produto produto, int quantidade, String status) {
        Produto = produto;
        Quantidade = quantidade;
        QuantidadeAprovada = quantidade;
        Status = status;
    }

    public Produto getProduto() {
        return Produto;
    }

    public void setProduto(Produto Produto) {
        this.Produto = Produto;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }

    public int getQuantidadeAprovada() {
        return QuantidadeAprovada;
    }

    public void setQuantidadeAprovada(int QuantidadeAprovada) {
        this.QuantidadeAprovada = QuantidadeAprovada;
    }
    
    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
    
}
