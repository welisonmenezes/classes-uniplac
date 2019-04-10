/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.Perfil;

import Config.Environment;
import Models.Usuario;
import Utils.Methods;
import Utils.Navigation;
import Utils.Styles;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Tela do perfil do usuário logado
 * @author Welison
 */
public class Perfil extends Templates.BaseLayout {
    
    private JButton btnEditar;
    private Usuario usuario;

    /**
     * Cria a tela de fornecedores
     */
    public Perfil(Properties params) {
        super();
        this.self = this;
        this.params = params;
        
        initPage();
    }
    
    /**
     * Inicia a tela
     */
    private void initPage() {
        
        // cria objetos para carregar dados
        usuario = Environment.getLoggedUser();
        
        // carrega os elementos e o design da tela
        initComponents();
        createBaseLayout();
        addTopContent(Methods.getTranslation("Perfil"));
        addCenterContent();
        addBottomContent();
    }
    
    /**
     * Adiciona o conteúdo da área central (o formulário em si)
     */
    private void addCenterContent() {
        barraRolagem = new JScrollPane();
        Styles.defaultScroll(barraRolagem);
        updateCenterContent();
        pCenter.add(barraRolagem, BorderLayout.CENTER);
    }
    
    /**
     * Atualiza o conteúdo do centro da area de conteúdo
     */
    private void updateCenterContent() {
        makeTable();
        barraRolagem.getViewport().setView(tabela);
    }
    
    /**
     * Gera a tabela com os dados
     */
    private void makeTable() {
        // cria tabela
        tabela = new JTable();
        tabela.setRowHeight(35);
        // seta colunas
        String[] colunas = {"", Methods.getTranslation("SeusDados")};
       // seta modelo
        Object[][] data = {
            {Methods.getTranslation("CPF"),usuario.getCpf()},
            {Methods.getTranslation("Nome"), usuario.getNome()},
            {Methods.getTranslation("Email"), usuario.getEmail()},
            {Methods.getTranslation("Celular"), usuario.getCelular()},
            {Methods.getTranslation("Telefone"), usuario.getTelefone()},
            {Methods.getTranslation("Setor"), usuario.getSetor()},
            {Methods.getTranslation("Sexo"), usuario.getSexo()},
            {Methods.getTranslation("Permissao"), usuario.getPermissao()},
            {Methods.getTranslation("DataDeNascimento"), Methods.getFriendlyBirthday((usuario.getDataNascimento()))},
            {Methods.getTranslation("Login"), usuario.getLogin()},
            {Methods.getTranslation("Senha"), usuario.getSenha()}
        };
        tableModel = new DefaultTableModel(data, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };
        // cria tabela
        tabela = new JTable(tableModel);
        tabela.setRowHeight(35);
    }
    
    /**
     * Adiciona o conteúdo à area de footer do conteúdo
     */
    private void addBottomContent() {
        // se administrador, add botão de editar perfil
        //if (Environment.getLoggedUser().getPermissao().equals(Methods.getTranslation("Administrador"))) {
            btnEditar = new JButton(Methods.getTranslation("EditarPerfil"));
            Styles.defaultButton(btnEditar);
            pBottom.add(btnEditar);
            btnEditar.addActionListener((ActionEvent e) -> {
                Navigation.updateLayout("editarPerfil", usuario.getCpf(), params);
            });
        //}
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBorder(javax.swing.BorderFactory.createEmptyBorder(50, 25, 50, 25));
        setMinimumSize(new java.awt.Dimension(1, 1));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
