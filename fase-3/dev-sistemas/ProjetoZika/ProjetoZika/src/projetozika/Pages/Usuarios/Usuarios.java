/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.Usuarios;

import Config.Environment;
import DAO.UsuarioDAO;
import Models.Usuario;
import CustomFields.ButtonEditor;
import CustomFields.ButtonRenderer;
import Utils.Dialogs;
import Utils.Methods;
import Utils.Navigation;
import Utils.Pagination;
import Utils.Styles;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 * Tela de listagem de usuários
 * @author Welison
 */
public class Usuarios extends Templates.BaseLayout {
    
    private JButton addMore;
    private JTextField fEmail;
    private JTextField fNome;
    private JComboBox<String> fSetor;
    private JLabel lNome;
    private JLabel lEmail;
    private JLabel lSetor;
    private JButton bSearch;
    private JLabel hideL;
    private ArrayList<Usuario> usuarios;
    private UsuarioDAO usuarioDao;
    private int totalUsuarios;

    /**
     * Cria a tela de fornecedores
     * @param params Parâmetros para filtro e paginação
     */
    public Usuarios(Properties params) {
        super();
        this.self = this;
        this.params = params;
        
        initPage();
    }
    
    /**
     * Inicializa a tela
     */
    private void initPage() {
        
        // carrega os dados
        usuarioDao = new UsuarioDAO();
        usuarios = usuarioDao.selecionar(params);
        totalUsuarios = usuarioDao.total(params);
        
        // constroi o layout
        initComponents();
        createBaseLayout();
        addTopContent(Methods.getTranslation("Usuarios"));
        addCenterContent();
        addBottomContent();
        addFilterContent();
        
        // seta os parâmetros
        updateParams();
    }
    
    /**
     * Seta os parâmetros a serem usados na paginação e no filtro
     */
    private void updateParams() {
        params.setProperty("offset", "0");
        params.setProperty("orderby", "Id");
        params.setProperty("orderkey", "0");
        params.setProperty("page", "1");
        params.setProperty("nome", fNome.getText());
        params.setProperty("email", fEmail.getText());
        params.setProperty("setor", fSetor.getSelectedItem().toString());
    }
    
    /**
     * Adiciona conteúdo ao centro da area de conteúdo
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
        String[] colunas = {
            Methods.getTranslation("Codigo"),
            Methods.getTranslation("CPF"), 
            Methods.getTranslation("Nome"), 
            Methods.getTranslation("Email"), 
            Methods.getTranslation("Setor"), 
            Methods.getTranslation("Editar"), 
            Methods.getTranslation("Excluir"), 
            Methods.getTranslation("Ver")
        };
        // informando os tipos das colunas para auxiliar na ordenação
        final Class<?>[] columnClasses = new Class<?>[] {
            Integer.class,
            String.class, 
            String.class, 
            String.class, 
            String.class, 
            String.class, 
            String.class, 
            String.class
        };
       // seta modelo
        tableModel = new DefaultTableModel(null, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
               if (column != 5 && column != 6 && column != 7) {
                   return false;
               }
               return true;
            }
            @Override
            public Class<?> getColumnClass(int column) {
                return columnClasses[column];
            }
        };
        // adiciona linhas
        usuarios.forEach(u -> {
            Object[] data = {
                u.getId(),
                u.getCpf(),
                u.getNome(),
                u.getEmail(),
                u.getSetor(),
                Methods.getTranslation("Editar"),
                Methods.getTranslation("Excluir"),
                Methods.getTranslation("Ver")
            };
            tableModel.addRow(data);
        });
        // inicializa
        tabela.setModel(tableModel);
        
        // add action para os botões da tabela
        actionsTable();
        
        // add funcionalidade de ordenação na tabela
        sortTable();
    }
    
    /**
     * adiciona ações para os botões da tabela
     */
    private void actionsTable() {
        TableColumn colEditar = tabela.getColumn(Methods.getTranslation("Editar"));
        colEditar.setMaxWidth(40);
        colEditar.setCellRenderer(new ButtonRenderer());
        colEditar.setCellEditor(new ButtonEditor(new JCheckBox()){
            @Override
            public void buttonAction() {
                String id = Methods.selectedTableItemId(tabela);
                Navigation.updateLayout("editarUsuario", id, params);
            }
        });
        
        TableColumn colExcluir = tabela.getColumn(Methods.getTranslation("Excluir"));
        colExcluir.setMaxWidth(40);
        colExcluir.setCellRenderer(new ButtonRenderer());
        colExcluir.setCellEditor(new ButtonEditor(new JCheckBox()){
            @Override
            public void buttonAction() {
                String idTabel = Methods.selectedTableItemId(tabela);

                int opcion = JOptionPane.showConfirmDialog(null, Methods.getTranslation("DesejaRealmenteExcluir?"), "Aviso", JOptionPane.YES_NO_OPTION);
                if (opcion == 0) {
                    
                    // deleta o usuário da base
                    try {
                        usuarioDao.deletar(idTabel);
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("DeletadoComSucesso"));
                    } catch(Exception error) {
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("ErroAoTentarDeletar"));
                        throw new RuntimeException("Usuarios.delete: " + error);
                    }
                    // 'recarrega a tela'
                    Navigation.updateLayout("", new Properties());
                    Navigation.updateLayout("usuarios", params);
                    
                }
            }
        });
        
        TableColumn colVer = tabela.getColumn(Methods.getTranslation("Ver"));
        colVer.setMaxWidth(40);
        colVer.setCellRenderer(new ButtonRenderer());
        colVer.setCellEditor(new ButtonEditor(new JCheckBox()){
            @Override
            public void buttonAction() {
                String id = Methods.selectedTableItemId(tabela);
                Navigation.updateLayout("verUsuario", id, params);
            }
        });
    }
    
    /**
     * torna a tabela ordenável
     */
    private void sortTable() {
        
        // torna a tabela ordenável
        Methods.makeTableSortable(tabela, 4, params);
        
        // ouve o evento de click no header da tabela
        tabela.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = tabela.columnAtPoint(e.getPoint());
                
                if (col <= 4) {
                    Dialogs.showLoadPopup(self);
                    updateParams();

                    if (params.getProperty("order", "DESC").equals("DESC")) {
                        params.setProperty("order", "ASC");
                    } else {
                        params.setProperty("order", "DESC");
                    }

                    switch (col) {
                        case 0 : 
                            params.setProperty("orderby", "Id");
                            params.setProperty("orderkey", "0");
                            break;
                        case 1 :
                            params.setProperty("orderby", "Cpf");
                            params.setProperty("orderkey", "1");
                            break;
                        case 2 :
                            params.setProperty("orderby", "Nome");
                            params.setProperty("orderkey", "2");
                            break;
                        case 3 :
                            params.setProperty("orderby", "Email");
                            params.setProperty("orderkey", "3");
                            break;
                        case 4 :
                            params.setProperty("orderby", "Setor");
                            params.setProperty("orderkey", "4");
                            break;
                    }

                    timerTest();
                }
            }
        });
    }
    
    /**
     * Adiciona o conteúdo à area de filtro da tela de conteúdo
     */
    private void addFilterContent() {
        
        fNome = new JTextField();
        Styles.defaultField(fNome, 150);
        fNome.setText(params.getProperty("nome", ""));
        
        lNome = new JLabel(Methods.getTranslation("Nome"));
        Styles.defaultLabel(lNome, false);
        
        fEmail = new JTextField();
        Styles.defaultField(fEmail, 150);
        fEmail.setText(params.getProperty("email", ""));
        
        lEmail = new JLabel(Methods.getTranslation("Email"));
        Styles.defaultLabel(lEmail, false);
        
        fSetor = new JComboBox();
        fSetor.setModel(new DefaultComboBoxModel(Environment.SETORES));
        fSetor.setSelectedItem(params.getProperty("setor", ""));
        Styles.defaultComboBox(fSetor);
        
        lSetor = new JLabel(Methods.getTranslation("Setor"));
        Styles.defaultLabel(lSetor, false);
        
        bSearch = new JButton("");
        Styles.searchButton(bSearch);
        
        hideL = new JLabel();
        hideL.setPreferredSize(new Dimension(50, 35));
        
        addMore = new JButton(Methods.getTranslation("CriarNovo"));
        Styles.defaultButton(addMore);
        
        pFilter.add(lNome);
        pFilter.add(fNome);
        pFilter.add(lEmail);
        pFilter.add(fEmail);
        pFilter.add(lSetor);
        pFilter.add(fSetor);
        pFilter.add(bSearch);
        pFilter.add(hideL);
        pFilter.add(addMore);
        
        // click do adicionar novo
        addMore.addActionListener((ActionEvent e) -> {
            Navigation.updateLayout("addUsuario", params);
        });
        
        // click do buscar
        bSearch.addActionListener((ActionEvent e) -> {
            Dialogs.showLoadPopup(self);
            // atualiza os parâmetros com os dados do form de busca
            updateParams();
            timerTest();
        });
    }
    
    /**
     * Adiciona o conteúdo à area de footer do conteúdo
     */
    private void addBottomContent() {
        this.pagination(totalUsuarios);
    }
    
    /**
     * Gera a paginação com base no total de páginas
     * @param total o total de páginas
     */
    private void pagination(int total) {
        new Pagination(pBottom, total, params){
            @Override
            public void callbackPagination() {
                Dialogs.showLoadPopup(self);
                timerTest();
            }
        };
    }
    
    private Timer t;
    private void timerTest() {
        
        t = new Timer(250, (ActionEvent e) -> {
            Dialogs.hideLoadPopup(self);
            
            // reseta tabela e recarrega os dados
            usuarios.clear();
            usuarios = usuarioDao.selecionar(params);
            totalUsuarios = usuarioDao.total(params);
            updateCenterContent();
            pagination(totalUsuarios);
            
            t.stop();
        });
        t.start();
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
