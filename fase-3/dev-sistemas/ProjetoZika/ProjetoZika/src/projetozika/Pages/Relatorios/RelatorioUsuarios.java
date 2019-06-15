package projetozika.Pages.Relatorios;

import Config.Environment;
import DAO.UsuarioDAO;
import Models.RelatorioUsuario;
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
 * Tela para gerar relatório de produtos
 * @author welison
 */
public class RelatorioUsuarios extends JPanel {
    private JLabel lfornecedor;
    private JLabel lproduto;
    private JLabel ldatafrom;
    private JDateChooser fdatafrom;
    private JLabel edatafrom;
    private JLabel ldatato;
    private JDateChooser fdatato;
    private JLabel edatato;
    private JButton btnRelatorioPedido;
    private final JPanel self;
    private JLabel title;
    private final UsuarioDAO usuarioDao;
    private String dataDe;
    private String dataAte;
    private final Properties params;
    private JComboBox fStatus;
    private JComboBox fPermissao;
    private String permissaoSelecionada;
    private String statusSelecionado;

    /**
     * Creates new form RelatorioPedidos
     */
    public RelatorioUsuarios() {
        initComponents();
        this.self = getInstance();
        this.params = new Properties();
        usuarioDao = new UsuarioDAO();
        
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
        title.setText(Methods.getTranslation("RelatorioDeUsuarios"));
        add(title, new AbsoluteConstraints(20, 20, -1, -1));
        
        lfornecedor = new JLabel(Methods.getTranslation("Setor"));
        Styles.defaultLabel(lfornecedor);
        add(lfornecedor, new AbsoluteConstraints(20, 60, -1, -1));
        
        fStatus = new JComboBox();
        fStatus.setModel(new DefaultComboBoxModel(Environment.SETORES));
        Styles.defaultComboBox(fStatus, 300, 39);
        add(fStatus, new AbsoluteConstraints(20, 90, -1, -1));
        
        lproduto = new JLabel(Methods.getTranslation("Permissao"));
        Styles.defaultLabel(lproduto);
        add(lproduto, new AbsoluteConstraints(340, 60, -1, -1));
        
        fPermissao = new JComboBox();
        fPermissao.setModel(new DefaultComboBoxModel(Environment.PERMISSOES));
        Styles.defaultComboBox(fPermissao, 300, 39);
        add(fPermissao, new AbsoluteConstraints(340, 90, -1, -1));
        
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
                permissaoSelecionada = fPermissao.getSelectedItem().toString();
                statusSelecionado = fStatus.getSelectedItem().toString();
                
                // atualiza os parametros a serem enviados apra a consulta sql
                this.updateParams();
                
                // meta infos a serem exibidos no pdf
                String infoPermissao = (permissaoSelecionada == null) ? Methods.getTranslation("Todos") : permissaoSelecionada;
                String infoStatus = (statusSelecionado == null) ? Methods.getTranslation("Todos") : statusSelecionado;
                String tituloRelatorio = "ProjetoZika - " + Methods.getTranslation("Usuarios");
                String filename = "ProjetoZika-Usuarios-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".pdf";
                String header[] = {
                    Methods.getTranslation("Codigo"),
                    Methods.getTranslation("Nome"),
                    Methods.getTranslation("Data"),
                    Methods.getTranslation("Permissao"),
                    Methods.getTranslation("Setor")
                };
                String infos[] = {
                    Methods.getTranslation("Permissao") + ": " + infoPermissao,
                    Methods.getTranslation("Status") + ": " + infoStatus,
                    Methods.getTranslation("Periodo") + ": "
                        + Methods.getTranslation("De") + " "+ params.getProperty("dataDe", "") +" "
                        + Methods.getTranslation("Ate") + " " + params.getProperty("dataAte", "")
                };
                
                ArrayList<RelatorioUsuario> relatorioUsuarios = usuarioDao.relatorioUsuario(params);
                
                if (relatorioUsuarios.size() > 0) {
                    ArrayList<String[]> data = new ArrayList();
                    relatorioUsuarios.forEach(item -> {
                        String row[] = {
                            item.getCodigo()+"",
                            item.getNome(),
                            item.getData(),
                            item.getPermissao(),
                            item.getSetor()
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
        if (statusSelecionado != null) {
            params.setProperty("statusSelecionado", statusSelecionado + "");
        } else {
            params.setProperty("statusSelecionado", "");
        }
        if (permissaoSelecionada != null) {
            params.setProperty("permissaoSelecionada", permissaoSelecionada + "");
        } else {
            params.setProperty("permissaoSelecionada", "");
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
