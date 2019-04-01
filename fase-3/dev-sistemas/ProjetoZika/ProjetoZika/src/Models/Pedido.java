/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;

/**
 *
 * @author Welison
 */
public class Pedido {
    private int Id;
    private String Created;
    private String Status;
    private Usuario Solicitante;
    private ArrayList<PedidoProduto> Produtos;
    
    public Pedido() {}
    
    public Pedido(String created, String status, Usuario solicitante) {
        Created = created;
        Status  = status;
        Solicitante = solicitante;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Codigo) {
        this.Id = Codigo;
    }
    
    public String getCreated() {
        return Created;
    }

    public void setCreated(String Created) {
        this.Created = Created;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public Usuario getSolicitante() {
        return Solicitante;
    }

    public void setSolicitante(Usuario Solicitante) {
        this.Solicitante = Solicitante;
    }

    public ArrayList<PedidoProduto> getProdutos() {
        return Produtos;
    }

    public void setProdutos(ArrayList<PedidoProduto> Produtos) {
        this.Produtos = Produtos;
    }
    
}
