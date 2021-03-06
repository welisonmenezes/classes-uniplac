package projetozika.Pages.Relatorios;

import Config.Environment;
import CustomFields.ComboItem;
import CustomFields.SuggestionsBox;
import DAO.PedidoDAO;
import DAO.ProdutoDAO;
import DAO.UsuarioDAO;
import Models.Produto;
import Models.RelatorioPedido;
import Models.ReportModel;
import Models.Usuario;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 * Tela para gerar relatórios de pedidos
 * @author welison
 */
public class RelatorioPedidos extends JPanel {
    private JLabel lusuario;
    private JPanel susuario;
    private JTextField fusuario;
    private JComboBox cusuario;
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
    private JPanel saprover;
    private JTextField faprover;
    private JComboBox caprover;
    private JLabel laprover;
    private JComboBox fStatus;
    private JLabel lstatus;
    private final ProdutoDAO produtoDao;
    private ArrayList<Produto> produtos;
    private final UsuarioDAO usuarioDao;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Usuario> aprovadores;
    private final Properties params;
    private String dataDe;
    private String dataAte;
    private ComboItem usuarioSelecionado;
    private ComboItem aprovadorSelecionado;
    private ComboItem produtoSelecionado;
    private String statusSelecionado;
    private final PedidoDAO pedidoDao;

    /**
     * Creates new form RelatorioPedidos
     */
    public RelatorioPedidos() {
        initComponents();
        this.self = getInstance();
        this.params = new Properties();
        produtoDao = new ProdutoDAO();
        produtos = new ArrayList<>();
        usuarioDao = new UsuarioDAO();
        usuarios = new ArrayList<>();
        aprovadores = new ArrayList<>();
        pedidoDao = new PedidoDAO();
        
        addCamposPedidos();
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
    private void addCamposPedidos() {
        setBackground(new Color(27, 28, 29));
        setLayout(new AbsoluteLayout());
        
        title = new JLabel();
        title.setFont(new java.awt.Font("Tahoma", 1, 24));
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText(Methods.getTranslation("RelatorioDePedidos"));
        add(title, new AbsoluteConstraints(20, 20, -1, -1));
        
        lusuario = new JLabel(Methods.getTranslation("Usuario"));
        Styles.defaultLabel(lusuario);
        add(lusuario, new AbsoluteConstraints(20, 60, -1, -1));
        
        // suggestion box
        susuario = new JPanel();
        fusuario = new JTextField();
        cusuario = new JComboBox();
        new SuggestionsBox(susuario, fusuario, cusuario, 300) {
            @Override
            public ArrayList<ComboItem> addElements() {
                // sugestão de produtos
                ArrayList<ComboItem> elements = new ArrayList<>();
                usuarios.clear();
                usuarios = usuarioDao.selecionarPorNome(fusuario.getText().trim());
                usuarios.forEach(usuario -> {
                    elements.add(new ComboItem(usuario.getId(), usuario.getNome()));
                });
                return elements;
            }
        };
        add(susuario, new AbsoluteConstraints(20, 90, -1, -1));
        
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
        
        laprover = new JLabel(Methods.getTranslation("Aprovador"));
        Styles.defaultLabel(laprover);
        add(laprover, new AbsoluteConstraints(20, 140, -1, -1));
        
        // suggestion box
        saprover = new JPanel();
        faprover = new JTextField();
        caprover = new JComboBox();
        new SuggestionsBox(saprover, faprover, caprover, 300) {
            @Override
            public ArrayList<ComboItem> addElements() {
                // sugestão de produtos
                ArrayList<ComboItem> elements = new ArrayList<>();
                aprovadores.clear();
                aprovadores = usuarioDao.selecionarPorNomeEPermissaoExcluida(faprover.getText().trim(), Methods.getTranslation("Usuario"));
                aprovadores.forEach(usuario -> {
                    elements.add(new ComboItem(usuario.getId(), usuario.getNome()));
                });
                return elements;
            }
        };
        add(saprover, new AbsoluteConstraints(20, 170, -1, -1));
        
        lstatus = new JLabel(Methods.getTranslation("Status"));
        Styles.defaultLabel(lstatus);
        add(lstatus, new AbsoluteConstraints(340, 140, -1, -1));
        
        fStatus = new JComboBox();
        fStatus.setModel(new DefaultComboBoxModel(Environment.STATUS));
        Styles.defaultComboBox(fStatus, 300, 39);
        add(fStatus, new AbsoluteConstraints(340, 170, -1, -1));
        
        ldatafrom = new JLabel(Methods.getTranslation("De"));
        Styles.defaultLabel(ldatafrom);
        add(ldatafrom, new AbsoluteConstraints(20, 220, -1, -1));
        
        fdatafrom = new JDateChooser();
        Styles.defaultDateChooser(fdatafrom, 300);
        DateHandler.setDateChooserFormat(fdatafrom);
        add(fdatafrom, new AbsoluteConstraints(20, 250, -1, -1));
        
        edatafrom = new JLabel("");
        Styles.errorLabel(edatafrom);
        add(edatafrom, new AbsoluteConstraints(20, 300, -1, -1));
        
        ldatato = new JLabel(Methods.getTranslation("Ate"));
        Styles.defaultLabel(ldatato);
        add(ldatato, new AbsoluteConstraints(340, 220, -1, -1));
        
        fdatato = new JDateChooser();
        Styles.defaultDateChooser(fdatato, 300);
        DateHandler.setDateChooserFormat(fdatato);
        add(fdatato, new AbsoluteConstraints(340, 250, -1, -1));
        
        edatato = new JLabel("");
        Styles.errorLabel(edatato);
        edatato.setPreferredSize( new Dimension( 300, 20 ) );
        add(edatato, new AbsoluteConstraints(340, 300, -1, -1));
        
        // action para gerar relatório
        btnRelatorioPedido = new JButton(Methods.getTranslation("GerarRelatorio"));
        Styles.defaultButton(btnRelatorioPedido, 300);
        add(btnRelatorioPedido, new AbsoluteConstraints(340, 360, -1, -1));
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
                usuarioSelecionado = (ComboItem)cusuario.getSelectedItem();
                aprovadorSelecionado = (ComboItem)caprover.getSelectedItem();
                produtoSelecionado = (ComboItem)cproduto.getSelectedItem();
                statusSelecionado = fStatus.getSelectedItem().toString();
                
                // atualiza os parametros a serem enviados apra a consulta sql
                this.updateParams();
                
                // meta infos a serem exibidos no pdf
                String infoUsuario = (usuarioSelecionado == null) ? Methods.getTranslation("Todos") : usuarioSelecionado.getDescription();
                String infoAprovador = (aprovadorSelecionado == null) ? Methods.getTranslation("Todos") : aprovadorSelecionado.getDescription();
                String infoProduto = (produtoSelecionado == null) ? Methods.getTranslation("Todos") : produtoSelecionado.getDescription();
                String infoStatus = (statusSelecionado.equals("")) ? Methods.getTranslation("Todos") : statusSelecionado;
                String filename = "ProjetoZika-Pedidos-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".pdf";
                String tituloRelatorio = "ProjetoZika - " + Methods.getTranslation("Pedidos");
                String header[] = {
                    Methods.getTranslation("Codigo"),
                    Methods.getTranslation("Solicitante"),
                    Methods.getTranslation("Aprovador"),
                    Methods.getTranslation("Status"),
                    Methods.getTranslation("Data"),
                    Methods.getTranslation("Produtos"),
                    Methods.getTranslation("Total")
                };
                String infos[] = {
                    Methods.getTranslation("Usuario") + ": " + infoUsuario,
                    Methods.getTranslation("Aprovador") + ": " + infoAprovador,
                    Methods.getTranslation("Produto") + ": " + infoProduto,
                    Methods.getTranslation("Status") + ": " + infoStatus,
                    Methods.getTranslation("Periodo") + ": "
                        + Methods.getTranslation("De") + " "+ params.getProperty("dataDe", "") +" "
                        + Methods.getTranslation("Ate") + " " + params.getProperty("dataAte", "")
                };
                
                // carrega dados da base para serem exibidos no pdf
                ArrayList<RelatorioPedido> relatorioPedidos = pedidoDao.relatorioPedido(params);
                if (relatorioPedidos.size() > 0) {
                    ArrayList<String[]> data = new ArrayList();
                    relatorioPedidos.forEach(item -> {
                        String row[] = {
                            item.getCodigo()+"",
                            item.getSoliciante(),
                            item.getAprovador(),
                            item.getStatus(),
                            DateHandler.getFriendlyBirthday(item.getData()),
                            item.getProdutos(),
                            item.getTotal()+""
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
        if (usuarioSelecionado != null) {
            if (usuarioSelecionado.getId() > 0) {
                params.setProperty("usuarioId", usuarioSelecionado.getId() + "");
            } else {
                params.setProperty("usuarioId", "");
            }
        } else {
            params.setProperty("usuarioId", "");
        }
        if (aprovadorSelecionado != null) {
            if (aprovadorSelecionado.getId() > 0) {
                params.setProperty("aprovadorId", aprovadorSelecionado.getId() + "");
            } else {
                params.setProperty("aprovadorId", "");
            }
        } else {
            params.setProperty("aprovadorId", "");
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
        params.setProperty("status", statusSelecionado);
        
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
