/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import static Utils.Methods.getJBody;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import projetozika.Main;
import projetozika.Pages.Dashboard;
import projetozika.Pages.Fornecedores.AddFornecedor;
import projetozika.Pages.Fornecedores.Fornecedores;
import projetozika.Pages.NotFound;
import projetozika.Pages.NotasFiscais.AddNotaFiscal;
import projetozika.Pages.NotasFiscais.NotasFiscais;
import projetozika.Pages.Pedidos.EditarPedido;
import projetozika.Pages.Pedidos.EntregarPedido;
import projetozika.Pages.Pedidos.Pedidos;
import projetozika.Pages.Perfil.Perfil;
import projetozika.Pages.Produtos.AddProduto;
import projetozika.Pages.Produtos.Produtos;
import projetozika.Pages.Relatorios.Relatorios;
import projetozika.Pages.SeusPedidos.FazerPedido;
import projetozika.Pages.SeusPedidos.SeusPedidos;
import projetozika.Pages.Usuarios.AddUsuario;
import projetozika.Pages.Usuarios.Usuarios;

/**
 * Gerencia a navegação da aplicação
 * 
 * @author Welison
 */
public class Navigation {
    
    private static JFrame tmpFrame; // o jframe corrente (se houver)
    private static JPanel tmpPanel; // o jpanel corrent
    public static String currentPage = ""; // a página corrente
    
    /**
     * Atualiza o status do menu, indicando qual modulo está ativo.
     * Importante indicar um 'name' aos botões do menu na criação dos mesmos.
     * 
     * @param name o nome do menu que deverá ficar ativo
     */
    public static void updateSatusMenu(String name) {
        Component[] comps = Main.menu.getComponents();
        for (Component comp : comps) {
            String nameComp = comp.getName();
            if (nameComp != null && (comp instanceof javax.swing.JButton)) {
                if (nameComp.equals(name)) {
                    Styles.setMenuButtonActive((JButton)comp);
                } else {
                    Styles.setMenuButtonInactive((JButton)comp);
                }
            }
        }
    }
    
    /**
     * Atualiza o JBody, area onde o conteúdo de cada menu será exibido
     * Este metodo cuida das ediçoes e/ou visualizações de detalhe
     * 
     * @param pageName o nome do modulo desejado
     * @param id o id para consulta ao banco de dados
     * @param params Parâmetros para filtro e paginação
     */
    public static void updateLayout(String pageName, String id, Properties params) {
        if (currentPage.equals(pageName)) return;
        resetLayout(pageName);
        switch (pageName) {
            case "editarFornecedor":
                updateSatusMenu("fornecedores");
                tmpFrame = new AddFornecedor(id, "edit", params);
                tmpFrame.setVisible(true);
                break;
            case "verFornecedor":
                updateSatusMenu("fornecedores");
                tmpFrame = new AddFornecedor(id, "view", params);
                tmpFrame.setVisible(true);
                break;
            case "editarNotaFiscal":
                updateSatusMenu("notasFiscais");
                tmpFrame = new AddNotaFiscal(id, "edit", params);
                tmpFrame.setVisible(true);
                break;
            case "verNotaFiscal":
                updateSatusMenu("notasFiscais");
                tmpFrame = new AddNotaFiscal(id, "view", params);
                tmpFrame.setVisible(true);
                break;
            case "editarProduto":
                updateSatusMenu("produtos");
                tmpFrame = new AddProduto(id, "edit", params);
                tmpFrame.setVisible(true);
                break;
            case "verProduto":
                updateSatusMenu("produtos");
                tmpFrame = new AddProduto(id, "view", params);
                tmpFrame.setVisible(true);
                break;
            case "editarUsuario":
                updateSatusMenu("usuarios");
                tmpFrame = new AddUsuario(id, "edit", params);
                tmpFrame.setVisible(true);
                break;
            case "editarPerfil":
                updateSatusMenu("perfil");
                tmpFrame = new AddUsuario(id, "perfil", params);
                tmpFrame.setVisible(true);
                break;
            case "verUsuario":
                updateSatusMenu("usuarios");
                tmpFrame = new AddUsuario(id, "view", params);
                tmpFrame.setVisible(true);
                break;
            case "verPedido":
                updateSatusMenu("pedidos");
                tmpFrame = new EditarPedido(id, "view", params);
                tmpFrame.setVisible(true);
                break;
            case "editarPedido":
                updateSatusMenu("pedidos");
                tmpFrame = new EditarPedido(id, "edit", params);
                tmpFrame.setVisible(true);
                break;
            case "entregarPedido":
                updateSatusMenu("pedidos");
                tmpFrame = new EntregarPedido(id, "edit", params);
                tmpFrame.setVisible(true);
                break;
            case "verSeuPedido":
                updateSatusMenu("seusPedidos");
                tmpFrame = new FazerPedido(id, "view", params);
                tmpFrame.setVisible(true);
                break;
            case "editarSeuPedido":
                updateSatusMenu("seusPedidos");
                tmpFrame = new FazerPedido(id, "edit", params);
                tmpFrame.setVisible(true);
                break;
            default:
                setDefaultPage();
        }
        addNewPage();
    }
    
    /**
     * Atualiza o JBody, area onde o conteúdo de cada menu será exibido
     * Este metodo cuida das listagens e adições
     * 
     * @param pageName o nome do modulo desejado
     * @param params Parâmetros para filtro e paginação
     */
    public static void updateLayout(String pageName, Properties params) {
        if (currentPage.equals(pageName)) return;
        resetLayout(pageName);
        switch (pageName) {
            case "dashboard":
                updateSatusMenu("dashboard");
                tmpPanel = new Dashboard(params);
                break;
            case "fornecedores":
                updateSatusMenu("fornecedores");
                tmpPanel = new Fornecedores(params);
                break;
            case "addFornecedor":
                updateSatusMenu("fornecedores");
                tmpFrame = new AddFornecedor(params);
                tmpFrame.setVisible(true);
                break;
            case "notasFiscais":
                updateSatusMenu("notasFiscais");
                tmpPanel = new NotasFiscais(params);
                break;
            case "addNotaFiscal":
                updateSatusMenu("notasFiscais");
                tmpFrame = new AddNotaFiscal(params);
                tmpFrame.setVisible(true);
                break;
            case "addFornecedorNota":
                updateSatusMenu("notasFiscais");
                tmpFrame = new AddFornecedor(tmpPanel, params);
                tmpFrame.setVisible(true);
                break;
            case "produtos":
                updateSatusMenu("produtos");
                tmpPanel = new Produtos(params);
                break;
            case "addProduto":
                updateSatusMenu("produtos");
                tmpFrame = new AddProduto(params);
                tmpFrame.setVisible(true);
                break;
            case "addProdutoNota":
                updateSatusMenu("notasFiscais");
                tmpFrame = new AddProduto(tmpPanel, params);
                tmpFrame.setVisible(true);
                break;
            case "usuarios":
                updateSatusMenu("usuarios");
                tmpPanel = new Usuarios(params);
                break;
            case "addUsuario":
                updateSatusMenu("usuarios");
                tmpFrame = new AddUsuario(params);
                tmpFrame.setVisible(true);
                break;
            case "pedidos":
                updateSatusMenu("pedidos");
                tmpPanel = new Pedidos(params);
                break;
            case "seusPedidos":
                updateSatusMenu("seusPedidos");
                tmpPanel = new SeusPedidos(params);
                break;
            case "fazerPedido":
                updateSatusMenu("seusPedidos");
                tmpFrame = new FazerPedido(params);
                tmpFrame.setVisible(true);
                break;
            case "perfil":
                updateSatusMenu("perfil");
                tmpPanel = new Perfil(params);
                break;
            case "relatorios":
                updateSatusMenu("relatorios");
                tmpPanel = new Relatorios(params);
                break;
            default:
                setDefaultPage();
        }
        addNewPage();
    }
    
    /**
     * Reseta o JBody
     * @param pageName 
     */
    private static void resetLayout(String pageName) {
        if (!pageName.equals("addFornecedorNota")
            && !pageName.equals("addProdutoNota")){
            if (tmpFrame != null) {
                tmpFrame.dispose();
                tmpFrame = null;
            }
        }
        currentPage = pageName;
        Methods.clearStage(Methods.getJBody());
    }
    
    /**
     * Adiciona o jpanel corrente ao JBody
     */
    private static void addNewPage() {
        getJBody().add(tmpPanel, BorderLayout.CENTER);
        tmpPanel.setVisible(true);
    }
    
    /**
     * Seta um valor default para links não encontrados
     */
    private static void setDefaultPage() {
        updateSatusMenu("");
        currentPage = "";
        tmpPanel = new NotFound();
    }
}
