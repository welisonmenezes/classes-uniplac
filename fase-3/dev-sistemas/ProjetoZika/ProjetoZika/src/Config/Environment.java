/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import Models.Permissao;
import Models.Setor;
import Models.Status;
import Models.Unidade;
import Utils.Methods;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Welison
 */
public class Environment {
    
    public static final ArrayList<Unidade> UNIDADES = new ArrayList<Unidade>();
    static {
        UNIDADES.add(new Unidade(0, ""));
        UNIDADES.add(new Unidade(1, Methods.getTranslation("Quilo")));
        UNIDADES.add(new Unidade(2, Methods.getTranslation("Litro")));
        UNIDADES.add(new Unidade(3, Methods.getTranslation("Caixa")));
        UNIDADES.add(new Unidade(4, Methods.getTranslation("Pacote")));
        UNIDADES.add(new Unidade(5, Methods.getTranslation("Unidade")));
    };
    
    public static final ArrayList<Permissao> PERMISSOES = new ArrayList<Permissao>();
    static {
        PERMISSOES.add(new Permissao(0, ""));
        PERMISSOES.add(new Permissao(1, Methods.getTranslation("Usuario")));
        PERMISSOES.add(new Permissao(2, Methods.getTranslation("Almoxarife")));
        PERMISSOES.add(new Permissao(3, Methods.getTranslation("Administrador")));
    };
    
    public static final ArrayList<Setor> SETORES = new ArrayList<Setor>();
    static {
        SETORES.add(new Setor(0, ""));
        SETORES.add(new Setor(1, Methods.getTranslation("RecursosHumanos")));
        SETORES.add(new Setor(2, Methods.getTranslation("Contabilidade")));
        SETORES.add(new Setor(3, Methods.getTranslation("Manutencao")));
        SETORES.add(new Setor(4, Methods.getTranslation("Administracao")));
        SETORES.add(new Setor(5, Methods.getTranslation("Almoxarifado")));
    };
    
    public static final ArrayList<Status> STATUS = new ArrayList<Status>();
    static {
        STATUS.add(new Status(0, ""));
        STATUS.add(new Status(1, Methods.getTranslation("Pendente")));
        STATUS.add(new Status(2, Methods.getTranslation("AguardandoEntrega")));
        STATUS.add(new Status(3, Methods.getTranslation("Finalizado")));
        STATUS.add(new Status(4, Methods.getTranslation("Negado")));
    };
}