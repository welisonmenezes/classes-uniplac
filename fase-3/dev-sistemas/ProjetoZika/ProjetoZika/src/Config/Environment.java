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
 * Guarda usuário logado e opções de combobox
 * @author Welison
 */
public class Environment {
    
    private static Usuario user;
    
    /**
     * seta o usuario logado no sistema
     * @param u O usuário logado
     */
    public static void setLoggedUser(Usuario u) {
        user = u;
    }
    
    /**
     * retorna o usuário logado no sistema
     * @return  o usuário logado no sistema
     */
    public static Usuario getLoggedUser() {
        return user;
    }
    
    /**
     * retorna o idioma corrente da aplicação
     * @return o idioma corrente
     */
    public static String getCurrentLang() {
        Locale currentLocale = Locale.getDefault();
        return currentLocale.getLanguage();
    }
    
    /**
     * constante com as unidades usadas na aplicação
     */
    public static final String[] UNIDADES = {
        "",
        Methods.getTranslation("Quilo"),
        Methods.getTranslation("Litro"),
        Methods.getTranslation("Caixa"),
        Methods.getTranslation("Pacote"),
        Methods.getTranslation("Unidade")
    };
    
    /**
     * constante com as permissões usadas na aplicação
     */
    public static final String[] PERMISSOES =  {
        "",
        Methods.getTranslation("Usuario"),
        Methods.getTranslation("Almoxarife"),
        Methods.getTranslation("Administrador")
    };
    
    /**
     * constante com os setores usados na aplicação
     */
    public static final String[] SETORES = {
        "",
        Methods.getTranslation("RecursosHumanos"),
        Methods.getTranslation("Contabilidade"),
        Methods.getTranslation("Manutencao"),
        Methods.getTranslation("Administracao"),
        Methods.getTranslation("Almoxarifado"),
    };
    
    /**
     * constante com os status usados na aplicação
     */
    public static final String [] STATUS = {
        "",
        Methods.getTranslation("Pendente"),
        Methods.getTranslation("AguardandoEntrega"),
        Methods.getTranslation("Finalizado"),
        Methods.getTranslation("Negado")
    };
    
    /**
     * constante com os meses traduzíveis
     */
    public static final String [] MONTHS = {
        Methods.getTranslation("Janeiro"),
        Methods.getTranslation("Fevereiro"),
        Methods.getTranslation("Marco"),
        Methods.getTranslation("Abril"),
        Methods.getTranslation("Maio"),
        Methods.getTranslation("Junho"),
        Methods.getTranslation("Julho"),
        Methods.getTranslation("Agosto"),
        Methods.getTranslation("Setembro"),
        Methods.getTranslation("Outubro"),
        Methods.getTranslation("Novembro"),
        Methods.getTranslation("Dezembro")
    };
}