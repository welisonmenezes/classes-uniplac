/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almoxerifado;

/**
 *
 * @author sanvingla
 */
public class UsuarioLogado {
    
    private static int id;
    private static String permissao;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        UsuarioLogado.id = id;
    }

    public static String getPermissao() {
        return permissao;
    }

    public static void setPermissao(String permissao) {
        UsuarioLogado.permissao = permissao;
    }
    
    
    
}
