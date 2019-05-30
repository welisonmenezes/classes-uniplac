package projetozika.Pages.NotasFiscais;

import Models.NotaFiscalProduto;
import CustomFields.ButtonEditor;
import CustomFields.ButtonRenderer;
import CustomFields.FormataDecimal;
import Utils.Methods;
import Utils.Styles;
import java.awt.BorderLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 * panel de listar produtos da nota
 * @author Welison
 */
public class ListarProdutos extends JPanel {
    
    private DefaultTableModel tableModel;
    private JTable tabela;
    private final String mode;
    private JScrollPane barraRolagem;
    public ArrayList<NotaFiscalProduto> notaProdutos;
    private static DecimalFormat df2 = new DecimalFormat(".##");

    /**
     * Creates new form ListarProdutos
     * @param mode Mostra como o painel foi chamado, se como edição ou como visualização
     */
    public ListarProdutos(String mode) {
        this.mode = mode;
        this.notaProdutos = new ArrayList<>();
        
        initPage();
    }
    
    /**
     * Creates new form ListarProdutos
     * @param id id da nota fiscal
     * @param mode Mostra como o painel foi chamado, se como edição ou como visualização
     */
    public ListarProdutos(String id, String mode) {
        this.mode = mode;
        this.notaProdutos = new ArrayList<>();

        initPage();
    }
    
    /**
     * Inicia a tela
     * @param title o título
     */
    private void initPage() {
        
        initComponents();
        Styles.setBorderTitle(this, Methods.getTranslation("ProdutosDaNotaFiscal"));
        setLayout(new BorderLayout());
        
        barraRolagem = new JScrollPane();
        Styles.defaultScroll(barraRolagem);
        updateCenterContent();
        add(barraRolagem, BorderLayout.CENTER);
    }
    
    /**
     * Atualiza o conteúdo da área central
     */
    private void updateCenterContent() {
        makeTable();
        barraRolagem.getViewport().setView(tabela);
    }
    
    /**
     * Cria e popula a tabela
     */
    private void makeTable() {
        tabela = new JTable();
        tabela.setRowHeight(35);
        // seta colunas
        String[] colunas = {
            Methods.getTranslation("Codigo"),
            Methods.getTranslation("Nome"),
            Methods.getTranslation("Unidade"),
            Methods.getTranslation("Valor"),
            Methods.getTranslation("Quantidade"),
            ""
        };
        
       // seta modelo
        tableModel = new DefaultTableModel(null, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (mode.equals("view")) {
                    return false;
                } else {
                    if(column != 3 && column != 4 && column != 5){
                        return false;
                    }
                }
               return true;
            }
        };
       
        // change cell event
        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = tabela.getSelectedRow();
                if (row != -1) {
                    double valor = Double.parseDouble(tabela.getValueAt(row, 3).toString().replace(",", "."));
                    int quantidade = Integer.parseInt(tabela.getValueAt(row, 4).toString());
                    notaProdutos.get(row).setValor(valor);
                    notaProdutos.get(row).setQuantidade(quantidade);
                    //notaProdutos.get(row);
                    //System.out.println(notaProdutos.get(row).getValor());
                }
            }
        });
        
        // adiciona linhas
        notaProdutos.forEach(np -> {
            Object[] data = {np.getProduto().getId(),np.getProduto().getNome(),np.getProduto().getUnidade(),df2.format(np.getValor()),np.getQuantidade(),""};
            if (! mode.equals("view")) {
                data[5] = Methods.getTranslation("Excluir");
            }
            tableModel.addRow(data);
        });
        // inicializa
        tabela.setModel(tableModel);
        
        if (! mode.equals("view")) {
            TableColumn colExcluir = tabela.getColumnModel().getColumn(5);
            colExcluir.setMaxWidth(40);
            colExcluir.setCellRenderer(new ButtonRenderer());
            colExcluir.setCellEditor(new ButtonEditor(new JCheckBox()){
                @Override
                public void buttonAction() {
                    String idTabel = Methods.selectedTableItemId(tabela);
                    // remove o produto da lista de produtos da nota fiscal
                    for (int i = 0; i < notaProdutos.size(); i++) {
                        NotaFiscalProduto np = notaProdutos.get(i);
                        if (idTabel.equals(""+np.getProduto().getId())) {
                            notaProdutos.remove(np);
                        }
                    }
                    updateCenterContent();
                }
            });
        }
        
        // set formata decimal para o campo valor na tabela
        JTextField jtValor = new JTextField();
        jtValor.setDocument(new FormataDecimal(10, 2));
        tabela.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(jtValor));
        
        // set formata decimal para o campo quantidade na tabela
        JTextField jtQuantidade = new JTextField();
        jtQuantidade.setDocument(new FormataDecimal(10, 0));
        tabela.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(jtQuantidade));
    }
    
    /**
     * Adiciona o produto a lista de produtos da nota fiscal
     * @param notaFiscalProduto a NotaFiscalProduto a ser adicionada
     * @return true se adicionado com sucesso, false se não
     */
    public boolean addProduto(NotaFiscalProduto notaFiscalProduto) {
        if (! hasProduct(notaFiscalProduto.getProduto().getId())) {
            notaProdutos.add(notaFiscalProduto);
            updateCenterContent();
            return true;
        }
        return false;
    }
    
    /**
     * Verifica se produto já existe na lista de produtos da nota fiscal
     * @param id o id do produto a ser verificado
     * @return true se existe, false se não
     */
    private boolean hasProduct(int id) {
        return notaProdutos.stream().anyMatch((nfp) -> (id == nfp.getProduto().getId()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
