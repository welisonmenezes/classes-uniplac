/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 * Modelo para o gráfico de total/mês
 * @author welison
 */
public class GraphModel {
    private int Quantidade;
    private int Month;
    
    public GraphModel() {}
    
    public GraphModel(int quantidade, int month) {
        this.Quantidade = quantidade;
        this.Month = month;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }

    public int getMonth() {
        return Month;
    }

    public void setMonth(int Month) {
        this.Month = Month;
    }
    
}
