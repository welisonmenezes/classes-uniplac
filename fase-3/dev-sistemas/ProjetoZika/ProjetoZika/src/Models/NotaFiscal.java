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
public class NotaFiscal {
    private int Id;
    private long Numero;
    private int Serie;
    private String Cnpj;
    private float Valor;
    private String Data;
    
    public NotaFiscal(long numero, int serie, String cnpj, float valor, String data) {
        Numero = numero;
        Serie = serie;
        Cnpj = cnpj;
        Valor = valor;
        Data = data;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public long getNumero() {
        return Numero;
    }

    public void setNumero(long Numero) {
        this.Numero = Numero;
    }

    public int getSerie() {
        return Serie;
    }

    public void setSerie(int Serie) {
        this.Serie = Serie;
    }

    public String getCnpj() {
        return Cnpj;
    }

    public void setCnpj(String Cnpj) {
        this.Cnpj = Cnpj;
    }

    public float getValor() {
        return Valor;
    }

    public void setValor(float Valor) {
        this.Valor = Valor;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }
    
}
