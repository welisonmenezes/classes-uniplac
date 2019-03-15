/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import Models.Permissao;
import Models.Setor;
import Models.Unidade;
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
        UNIDADES.add(new Unidade(1, "Quilo"));
        UNIDADES.add(new Unidade(2, "Litro"));
        UNIDADES.add(new Unidade(3, "Caixa"));
        UNIDADES.add(new Unidade(4, "Unidade"));
        
    };
    
    public static final ArrayList<Permissao> PERMISSOES = new ArrayList<Permissao>();
    static {
        PERMISSOES.add(new Permissao(0, ""));
        PERMISSOES.add(new Permissao(1, "Usuário"));
        PERMISSOES.add(new Permissao(2, "Administrador"));
        PERMISSOES.add(new Permissao(3, "Almoxerife"));
    };
    
    public static final ArrayList<Setor> SETORES = new ArrayList<Setor>();
    static {
        SETORES.add(new Setor(0, ""));
        SETORES.add(new Setor(1, "Recursos Humanos"));
        SETORES.add(new Setor(2, "Contabilidade"));
        SETORES.add(new Setor(3, "Manutenção"));
        SETORES.add(new Setor(4, "Administração"));
        SETORES.add(new Setor(5, "Almixerifado"));
    };
}