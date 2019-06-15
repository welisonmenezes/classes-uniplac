package Models;

import java.util.ArrayList;

/**
 * Modelo para pedido
 * @author Welison
 */
public class Pedido {
    private int Id;
    private String Created;
    private String Status;
    private Usuario Solicitante;
    private int AlmoxarifeId;
    private ArrayList<PedidoProduto> Produtos;
    private String Done;
    private String Aproved;
    
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

    public int getAlmoxarifeId() {
        return AlmoxarifeId;
    }

    public void setAlmoxarifeId(int AlmoxarifeId) {
        this.AlmoxarifeId = AlmoxarifeId;
    }

    public String getDone() {
        return Done;
    }

    public void setDone(String Done) {
        this.Done = Done;
    }

    public String getAproved() {
        return Aproved;
    }

    public void setAproved(String Aproved) {
        this.Aproved = Aproved;
    }
    
    
}
