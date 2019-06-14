package projetozika.Pages.Relatorios;

import CustomFields.ComboItem;
import CustomFields.SuggestionsBox;
import DAO.FornecedorDAO;
import DAO.NotaFiscalDAO;
import DAO.ProdutoDAO;
import Models.Fornecedor;
import Models.Produto;
import Models.RelatorioNota;
import Models.ReportModel;
import Utils.DateHandler;
import Utils.Methods;
import Utils.PDFGenerator;
import Utils.Styles;
import Utils.Validator;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 * Tela para gerar relatório de notas fiscais
 * @author welison
 */
public class RelatorioNotas extends JPanel {
    private JLabel lfornecedor;
    private JPanel sfornecedor;
    private JTextField ffornecedor;
    private JComboBox cfornecedor;
    private JLabel lproduto;
    private JPanel sproduto;
    private JTextField fproduto;
    private JComboBox cproduto;
    private JLabel ldatafrom;
    private JDateChooser fdatafrom;
    private JLabel edatafrom;
    private JLabel ldatato;
    private JDateChooser fdatato;
    private JLabel edatato;
    private JButton btnRelatorioPedido;
    private final JPanel self;
    private JLabel title;
    private final ProdutoDAO produtoDao;
    private final NotaFiscalDAO notaDao;
    private ArrayList<Produto> produtos;
    private final FornecedorDAO fornecedorDao;
    private ArrayList<Fornecedor> fornecedores;
    private String dataDe;
    private String dataAte;
    private ComboItem fornecedorSelecionado;
    private ComboItem produtoSelecionado;
    private final Properties params;

    /**
     * Creates new form RelatorioPedidos
     */
    public RelatorioNotas() {
        initComponents();
        this.self = getInstance();
        this.params = new Properties();
        produtoDao = new ProdutoDAO();
        notaDao = new NotaFiscalDAO();
        produtos = new ArrayList<>();
        fornecedorDao = new FornecedorDAO();
        fornecedores = new ArrayList<>();
        
        addCamposProdutos();
    }
    
    /**
     * retorna a instância atual
     * @return a instância atual do JPanel
     */
    private JPanel getInstance() {
        return this;
    }
    
    /**
     * Cria e estiliza os elementos da tela
     */
    private void addCamposProdutos() {
        setBackground(new Color(27, 28, 29));
        setLayout(new AbsoluteLayout());
        
        title = new JLabel();
        title.setFont(new java.awt.Font("Tahoma", 1, 24));
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText(Methods.getTranslation("RelatorioDeNotas"));
        add(title, new AbsoluteConstraints(20, 20, -1, -1));
        
        lfornecedor = new JLabel(Methods.getTranslation("Fornecedor"));
        Styles.defaultLabel(lfornecedor);
        add(lfornecedor, new AbsoluteConstraints(20, 60, -1, -1));
        
        // suggestion box
        sfornecedor = new JPanel();
        ffornecedor = new JTextField();
        cfornecedor = new JComboBox();
        new SuggestionsBox(sfornecedor, ffornecedor, cfornecedor, 300) {
            @Override
            public ArrayList<ComboItem> addElements() {
                // sugestão de produtos
                ArrayList<ComboItem> elements = new ArrayList<>();
                fornecedores.clear();
                fornecedores = fornecedorDao.selecionarPorNome(ffornecedor.getText().trim());
                fornecedores.forEach(fornecedor -> {
                    elements.add(new ComboItem(fornecedor.getId(), fornecedor.getNome()));
                });
                return elements;
            }
        };
        add(sfornecedor, new AbsoluteConstraints(20, 90, -1, -1));
        
        lproduto = new JLabel(Methods.getTranslation("Produto"));
        Styles.defaultLabel(lproduto);
        add(lproduto, new AbsoluteConstraints(340, 60, -1, -1));
        
        // suggestion box
        sproduto = new JPanel();
        fproduto = new JTextField();
        cproduto = new JComboBox();
        new SuggestionsBox(sproduto, fproduto, cproduto, 300) {
            @Override
            public ArrayList<ComboItem> addElements() {
                // sugestão de produtos
                ArrayList<ComboItem> elements = new ArrayList<>();
                produtos.clear();
                produtos = produtoDao.selecionarPorNome(fproduto.getText().trim());
                produtos.forEach(produto -> {
                    elements.add(new ComboItem(produto.getId(), produto.getNome() + " - " + produto.getUnidade()));
                });
                return elements;
            }
        };
        add(sproduto, new AbsoluteConstraints(340, 90, -1, -1));
        
        ldatafrom = new JLabel(Methods.getTranslation("De"));
        Styles.defaultLabel(ldatafrom);
        add(ldatafrom, new AbsoluteConstraints(20, 140, -1, -1));
        
        fdatafrom = new JDateChooser();
        Styles.defaultDateChooser(fdatafrom, 300);
        DateHandler.setDateChooserFormat(fdatafrom);
        add(fdatafrom, new AbsoluteConstraints(20, 170, -1, -1));
        
        edatafrom = new JLabel("");
        Styles.errorLabel(edatafrom);
        add(edatafrom, new AbsoluteConstraints(20, 210, -1, -1));
        
        ldatato = new JLabel(Methods.getTranslation("Ate"));
        Styles.defaultLabel(ldatato);
        add(ldatato, new AbsoluteConstraints(340, 140, -1, -1));
        
        fdatato = new JDateChooser();
        Styles.defaultDateChooser(fdatato, 300);
        DateHandler.setDateChooserFormat(fdatato);
        add(fdatato, new AbsoluteConstraints(340, 170, -1, -1));
        
        edatato = new JLabel("");
        Styles.errorLabel(edatato);
        edatato.setPreferredSize( new Dimension( 300, 20 ) );
        add(edatato, new AbsoluteConstraints(340, 210, -1, -1));
        
        // action para gerar relatório
        btnRelatorioPedido = new JButton(Methods.getTranslation("GerarRelatorio"));
        Styles.defaultButton(btnRelatorioPedido, 300);
        add(btnRelatorioPedido, new AbsoluteConstraints(340, 250, -1, -1));
        btnRelatorioPedido.addActionListener((ActionEvent e) -> {

            // limpa erros
            clearErrors();
            
            // validação
            boolean isValid = true;
            if (! Validator.validaData(fdatafrom, edatafrom)) isValid = false;
            if (! Validator.validaData(fdatato, edatato)) isValid = false;
            // verifica se data é maior ou menor
            if (!Validator.isDateBeforeThen(fdatafrom, fdatato, edatato)) isValid = false;
            if (isValid) {
                
                // pega dados do formulário
                dataDe = ((JTextField)fdatafrom.getDateEditor().getUiComponent()).getText();
                dataAte = ((JTextField)fdatato.getDateEditor().getUiComponent()).getText();
                fornecedorSelecionado = (ComboItem)cfornecedor.getSelectedItem();
                produtoSelecionado = (ComboItem)cproduto.getSelectedItem();
                
                // atualiza os parametros a serem enviados apra a consulta sql
                this.updateParams();
                
                // meta infos a serem exibidos no pdf
                String infoFornecedor = (fornecedorSelecionado == null) ? Methods.getTranslation("Todos") : fornecedorSelecionado.getDescription();
                String infoProduto = (produtoSelecionado == null) ? Methods.getTranslation("Todos") : produtoSelecionado.getDescription();
                String tituloRelatorio = "ProjetoZika - " + Methods.getTranslation("NotasFiscais");
                String filename = "ProjetoZika-NotasFiscais-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".pdf";
                String header[] = {
                    Methods.getTranslation("Codigo"),
                    Methods.getTranslation("Numero"),
                    Methods.getTranslation("Data"),
                    Methods.getTranslation("Fornecedor"),
                    Methods.getTranslation("Produtos"),
                    Methods.getTranslation("Valor")
                };
                String infos[] = {
                    Methods.getTranslation("Fornecedor") + ": " + infoFornecedor,
                    Methods.getTranslation("Produto") + ": " + infoProduto,
                    Methods.getTranslation("Periodo") + ": "
                        + Methods.getTranslation("De") + " "+ params.getProperty("dataDe", "") +" "
                        + Methods.getTranslation("Ate") + " " + params.getProperty("dataAte", "")
                };
                
                ArrayList<RelatorioNota> relatorioNotas = notaDao.relatorioNota(params);
                
                if (relatorioNotas.size() > 0) {
                    ArrayList<String[]> data = new ArrayList();
                    relatorioNotas.forEach(item -> {
                        String row[] = {
                            item.getCodigo()+"",
                            item.getNumero()+"",
                            item.getEntrada(),
                            item.getFornecedore(),
                            item.getProdutos(),
                            item.getValor()+""
                        };
                        data.add(row);
                    });
                    ReportModel relatorio = new ReportModel(filename, tituloRelatorio, header, infos, data);

                    // gera o pdf
                    PDFGenerator pdfGenerator = new PDFGenerator(relatorio, this);
                } else {
                    // mensagem de erro
                    JOptionPane.showMessageDialog(null, Methods.getTranslation("NenhumDadoEncontrado"));
                }
                
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        });
    }
    
    /**
     * Atualiza os parâmetros de filtro pra o relatório
     */
    private void updateParams() {
        params.setProperty("dataDe", dataDe);
        params.setProperty("dataAte", dataAte);
        if (fornecedorSelecionado != null) {
            if (fornecedorSelecionado.getId() > 0) {
                params.setProperty("fornecedorId", fornecedorSelecionado.getId() + "");
            } else {
                params.setProperty("fornecedorId", "");
            }
        } else {
            params.setProperty("fornecedorId", "");
        }
        if (produtoSelecionado != null) {
            if (produtoSelecionado.getId() > 0) {
                params.setProperty("produtoId", produtoSelecionado.getId() + "");
            } else {
                params.setProperty("produtoId", "");
            }
        } else {
            params.setProperty("produtoId", "");
        }
    }
    
    /**
     * Limpa as mensagens de erro
     */
    private void clearErrors() {
        edatafrom.setText("");
        edatato.setText("");
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
