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
public class Setor {
    public int Index;
    public String Nome;
    
    public Setor(int i, String nome) {
        Index = i;
        Nome = nome;
    }
    
    public String toString() {
        return Nome;
    }
}
