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
public class Fornecedor {
    private String ID;
    private String Nome;
    private String Cnpj;
    private String Telefone;
    private String Data;
    
    //public void Fornecedor(){};
    
    public Fornecedor(String id) {
        
    }
    
    public Fornecedor(String id, String nome, String cnpj, String telefone, String data){
        this.ID = id;
        this.Nome = nome;
        this.Cnpj = cnpj;
        this.Telefone = telefone;
        this.Data = data;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getCnpj() {
        return Cnpj;
    }

    public void setCnpj(String Cnpj) {
        this.Cnpj = Cnpj;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }
}
