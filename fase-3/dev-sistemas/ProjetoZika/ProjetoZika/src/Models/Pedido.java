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
    private int Codigo;
    private String Data;
    private String Status;
    private Usuario Solicitante;
    private ArrayList<PedidoProduto> Produtos;
    
    public Pedido(String data, String status, Usuario solicitante) {
        Data = data;
        Status  = status;
        Solicitante = solicitante;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }
    
    

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
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
