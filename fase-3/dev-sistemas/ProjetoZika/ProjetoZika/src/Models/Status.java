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
public class Status {
    public int Index;
    public String Nome;
    
    public Status(int i, String nome) {
        Index = i;
        Nome = nome;
    }
    
    public String toString() {
        return Nome;
    }
}