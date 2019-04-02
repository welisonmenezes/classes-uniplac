/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 * Modelo para pedido produto
 * @author Welison
 */
public class PedidoProduto {
    private Produto Produto;
    private Pedido Pedido;
    private int Id;
    private int Quantidade;
    private int QuantidadeAprovada;
    
    public PedidoProduto() {}
    
    public PedidoProduto(Produto produto, Pedido pedido, int quantidade) {
        Produto = produto;
        Pedido = pedido;
        Quantidade = quantidade;
        QuantidadeAprovada = quantidade;
    }

    public Produto getProduto() {
        return Produto;
    }

    public void setProduto(Produto Produto) {
        this.Produto = Produto;
    }

    public Pedido getPedido() {
        return Pedido;
    }

    public void setPedido(Pedido Pedido) {
        this.Pedido = Pedido;
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
    
}
