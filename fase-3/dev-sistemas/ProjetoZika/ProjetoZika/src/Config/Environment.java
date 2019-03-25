/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import Models.Usuario;
import Utils.Methods;
import java.util.Locale;

/**
 *
 * @author Welison
 */
public class Environment {
    
    private static Usuario user;
    
    public static void setLoggedUser(Usuario u) {
        user = u;
    }
    
    public static Usuario getLoggedUser() {
        return user;
    }
    
    public static String getCurrentLang() {
        Locale currentLocale = Locale.getDefault();
        return currentLocale.getLanguage();
    }
    
    public static final String[] UNIDADES = {
        "",
        Methods.getTranslation("Quilo"),
        Methods.getTranslation("Litro"),
        Methods.getTranslation("Caixa"),
        Methods.getTranslation("Pacote"),
        Methods.getTranslation("Unidade")
    };
    
    public static final String[] PERMISSOES =  {
        "",
        Methods.getTranslation("Usuario"),
        Methods.getTranslation("Almoxarife"),
        Methods.getTranslation("Administrador")
    };
    
    public static final String[] SETORES = {
        "",
        Methods.getTranslation("RecursosHumanos"),
        Methods.getTranslation("Contabilidade"),
        Methods.getTranslation("Manutencao"),
        Methods.getTranslation("Administracao"),
        Methods.getTranslation("Almoxarifado"),
    };
    
    public static final String [] STATUS = {
        "",
        Methods.getTranslation("Pendente"),
        Methods.getTranslation("AguardandoEntrega"),
        Methods.getTranslation("Finalizado"),
        Methods.getTranslation("Negado")
    };
}