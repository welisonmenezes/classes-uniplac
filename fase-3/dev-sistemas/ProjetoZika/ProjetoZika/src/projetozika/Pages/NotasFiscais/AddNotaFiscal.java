/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetozika.Pages.NotasFiscais;

import Config.Environment;
import CustomFields.FormataDecimal;
import CustomFields.MaxSize;
import DAO.FornecedorDAO;
import DAO.NotaFiscalDAO;
import Models.Fornecedor;
import Models.NotaFiscal;
import Models.NotaFiscalProduto;
import CustomFields.ComboItem;
import CustomFields.MaskFactory;
import CustomFields.SuggestionsBox;
import Utils.Dialogs;
import Utils.Methods;
import Utils.Navigation;
import Utils.Styles;
import Utils.Validator;
import com.toedter.calendar.JDateChooser;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 * Tela de add/ver/editar nota fiscal
 * @author Welison
 */
public class AddNotaFiscal extends Templates.BaseFrame {
    private JScrollPane bgScroll;
    private JPanel bg;
    private JLabel lnumero;
    private JTextField fnumero;
    private JLabel  enumero;
    private JLabel lserie;
    private JTextField fserie;
    private JLabel  eserie;
    private JLabel lcnpj;
    public static JFormattedTextField fcnpj;
    private JLabel  ecnpj;
    private JLabel lvalor;
    private JTextField fvalor;
    private JLabel  evalor;
    private JLabel ldata;
    private JDateChooser fdata;
    private JLabel  edata;
    private JButton bSave;
    private JLabel addFornecedor;
    private JPanel panelAddProduto;
    private ListarProdutos panelListarProdutos;
    private JPanel pSuggestions;
    public static JComboBox ccnpj;
    private JLabel linfo;
    private String id;
    private FornecedorDAO fornecedorDao;
    private ArrayList<Fornecedor> fornecedores;
    private NotaFiscalDAO notaFiscalDao;
    private NotaFiscal notaFiscal;
    private Fornecedor fornecedor;
    private ArrayList<NotaFiscalProduto> notaFiscalProdutos;
    
    /**
     * chamada para adição
     * @param params parâmetros de filtro e paginação
     */
    public AddNotaFiscal(Properties params) {
        this.self = this;
        this.mode = "add";
        this.params = params;
        
        initPage(Methods.getTranslation("AdicionarNotaFiscal"));
    }
    
    /**
     * chamada para visualização ou edição
     * @param id o id da nota fiscal
     * @param mode o modo (view|edit)
     * @param params parâmetros de filtro e paginação 
     */
    public AddNotaFiscal(String id, String mode, Properties params) {
        this.self = this;
        this.mode = mode;
        this.params = params;
        this.id = id;
        
        switch (this.mode) {
            case "view":
                initPage(Methods.getTranslation("VerNotaFiscal"));
                Methods.disabledFields(bg);
                panelAddProduto.setVisible(false);
                addFornecedor.setVisible(false);
                break;
            case "edit":
                initPage(Methods.getTranslation("EditarNotaFiscal"));
                break;
        }
        
        fillFields(id);
    }
    
    /**
     * Inicia a tela
     * @param title o título
     */
    private void initPage(String title) {
        
        // cria objetos para carregar dados posteriormente
        fornecedorDao = new FornecedorDAO();
        fornecedores = new ArrayList();
        notaFiscalDao = new NotaFiscalDAO();
        notaFiscal = new NotaFiscal();
        
        // carrega os elementos e o design da tela
        initComponents();
        Styles.internalFrame(this, 1000, 600);
        Methods.setAccessibility(this);
        createBaseLayout();
        addTopContent(title);
        addCenterContent();
        
        // seta a página pai como página corrente e add focus no campo numero
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                Navigation.currentPage = "notasFiscais";
            }
            @Override
            public void windowOpened( WindowEvent e){ 
              fnumero.requestFocusInWindow();
            } 
        });
    }
    
    /**
     * Adiciona o conteúdo da área central (o formulário em si)
     */
    private void addCenterContent() {
        bgScroll = new JScrollPane();
        Styles.resetScrollPanel(bgScroll);
        bgScroll.getVerticalScrollBar().setUnitIncrement(16);
        
        bg = new JPanel();
        bg.setLayout(new AbsoluteLayout());
        bg.setOpaque(false);
        
        lnumero = new JLabel(Methods.getTranslation("Numero"));
        Styles.defaultLabel(lnumero);
        bg.add(lnumero, new AbsoluteConstraints(0, 0, -1, -1));
        
        fnumero = new JTextField();
        Styles.defaultField(fnumero);
        bg.add(fnumero, new AbsoluteConstraints(0, 40, -1, -1));
        fnumero.setDocument(new FormataDecimal(20, 0));
        
        enumero = new JLabel("");
        Styles.errorLabel(enumero);
        bg.add(enumero, new AbsoluteConstraints(0, 75, -1, -1));
        
        lcnpj = new JLabel(Methods.getTranslation("CNPJ"));
        Styles.defaultLabel(lcnpj);
        bg.add(lcnpj, new AbsoluteConstraints(220, 0, -1, -1));
        
        // suggestion box
        pSuggestions = new JPanel();
        fcnpj = new JFormattedTextField();
        fcnpj.setDocument(new MaxSize(18));
        fcnpj.setFormatterFactory(MaskFactory.setMaskCnpj());
        ccnpj = new JComboBox();
        new SuggestionsBox(pSuggestions, fcnpj, ccnpj, 200) {
            @Override
            public ArrayList<ComboItem> addElements() {
                ArrayList<ComboItem> elements = new ArrayList<>();
                // atualiza sugestão de fornecedores
                fornecedores.clear();
                fornecedores = fornecedorDao.selecionarPorCnpj(fcnpj.getText());
                fornecedores.forEach(fornecedor -> {
                    elements.add(new ComboItem(fornecedor.getId(), fornecedor.getCnpj()));
                });
                return elements;
            }
            @Override
            public void afterSelectItem() {
                ComboItem selectedProd = (ComboItem)ccnpj.getSelectedItem();
                if (selectedProd != null) {
                    // seta o fornecedor selecionado
                    fornecedor = fornecedorDao.selecionarPorId(selectedProd.getId()+"");
                }
            }
        };
        bg.add(pSuggestions, new AbsoluteConstraints(220, 40, -1, -1));
        
        addFornecedor = new JLabel("<html><u>"+ Methods.getTranslation("AdicionarFornecedor") +"</u></html>");
        Styles.defaultLabel(addFornecedor);
        addFornecedor.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bg.add(addFornecedor, new AbsoluteConstraints(430, 40, -1, -1));
        // button click add fornecedor
        addFornecedor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (!addFornecedor.isEnabled()) return;
                Navigation.updateLayout("addFornecedorNota", params);
            }
        });
        
        ecnpj = new JLabel("");
        Styles.errorLabel(ecnpj);
        bg.add(ecnpj, new AbsoluteConstraints(220, 75, -1, -1));
        
        lserie = new JLabel(Methods.getTranslation("NumeroDeSerie"));
        Styles.defaultLabel(lserie);
        bg.add(lserie, new AbsoluteConstraints(0, 90, -1, -1));

        fserie = new JTextField();
        Styles.defaultField(fserie);
        bg.add(fserie, new AbsoluteConstraints(0, 130, -1, -1));
        fserie.setDocument(new FormataDecimal(11, 0));
        
        eserie = new JLabel("");
        Styles.errorLabel(eserie);
        bg.add(eserie, new AbsoluteConstraints(0, 165, -1, -1));
        
        lvalor = new JLabel(Methods.getTranslation("Valor"));
        Styles.defaultLabel(lvalor);
        bg.add(lvalor, new AbsoluteConstraints(220, 90, -1, -1));

        fvalor = new JTextField();
        Styles.defaultField(fvalor);
        bg.add(fvalor, new AbsoluteConstraints(220, 130, -1, -1));
        fvalor.setDocument(new FormataDecimal(10, 2));
        
        evalor = new JLabel("");
        Styles.errorLabel(evalor);
        bg.add(evalor, new AbsoluteConstraints(220, 165, -1, -1));
        
        ldata = new JLabel(Methods.getTranslation("Data"));
        Styles.defaultLabel(ldata);
        bg.add(ldata, new AbsoluteConstraints(0, 180, -1, -1));

        fdata = new JDateChooser();
        Styles.defaultDateChooser(fdata);
        Methods.setDateChooserFormat(fdata);
        bg.add(fdata, new AbsoluteConstraints(0, 220, -1, -1));
        
        edata = new JLabel("");
        Styles.errorLabel(edata);
        bg.add(edata, new AbsoluteConstraints(0, 255, -1, -1));
        
        linfo = new JLabel("");
        Styles.errorLabel(linfo);
        linfo.setPreferredSize( new Dimension( 220, 20 ) );
        bg.add(linfo, new AbsoluteConstraints(220, 255, -1, -1));
        
        bSave = new JButton(Methods.getTranslation("Salvar"));
        Styles.defaultButton(bSave);
        bSave.setPreferredSize(new Dimension(196, 34));
        bg.add(bSave, new AbsoluteConstraints(220, 222, -1, -1));
        bSave.addActionListener((ActionEvent e) -> {
            
            // limpa erros
            clearErrors();
            
            // validação
            boolean isValid = true;
            if (! Validator.validaData(fdata, edata)) isValid = false;
            if (! Validator.validaNumero(fnumero, enumero)) isValid = false;
            if (! Validator.validaNumero(fserie, eserie)) isValid = false;
            if (! Validator.validaCnpj(fcnpj, ecnpj)) isValid = false;
            if (! Validator.validaComboBox(ccnpj, ecnpj)) isValid = false;
            if (! Validator.validaValor(fvalor, evalor)) isValid = false;
            if (panelListarProdutos.notaProdutos.size() < 1) {
                linfo.setText(Methods.getTranslation("AdicioneUmProduto"));
                isValid = false;
            }
            if (isValid) {
                
                // seta os valores do formulário à nota fiscal corrente
                notaFiscal.setNumero(Long.parseLong(fnumero.getText()));
                notaFiscal.setSerie(Integer.parseInt(fserie.getText()));
                notaFiscal.setValor(Float.parseFloat(fvalor.getText()));
                
                java.util.Date pega = fdata.getDate();
                SimpleDateFormat sdf;
                if (Environment.getCurrentLang().equals("en")) {
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                } else {
                    sdf = new SimpleDateFormat("dd/MM/yyyy");
                }
                String data = sdf.format(pega);
                notaFiscal.setData(Methods.getSqlDateTime(data));
                
                notaFiscal.setFornecedor(fornecedor);
                
                Dialogs.showLoadPopup(bg);
                timerTest();
            }
        });
        
        panelAddProduto = new SelecionarProduto(self);
        panelAddProduto.setVisible(true);
        panelAddProduto.setPreferredSize(new Dimension(375, 360));
        bg.add(panelAddProduto, new AbsoluteConstraints(570, 10, -1, -1));
        
        if (this.mode.equals("edit") || this.mode.equals("view")) {
            panelListarProdutos = new ListarProdutos(this.id, this.mode);
        } else {
            panelListarProdutos = new ListarProdutos(this.mode);
        }
        panelListarProdutos.setVisible(true);
        panelListarProdutos.setPreferredSize(new Dimension(945, 300));
        bg.add(panelListarProdutos, new AbsoluteConstraints(0, 400, -1, -1));
        
        bgScroll.setViewportView(bg);
        pCenter.add(bgScroll);
    }
    
    /**
     * adiciona produto à nota
     * @param notaProduto o objeto NotaFiscalProduto a ser adicionado
     * @return true se produto foi adicionado, false se não
     */
    public boolean addProduto(NotaFiscalProduto notaProduto) {
        return panelListarProdutos.addProduto(notaProduto);
    }
    
    /**
     * preenche os campos do formulário com o usuário cujo cpf é correspondente na base de dados
     * @param id o id do produto
     */
    private void fillFields(String id) {
        notaFiscal = notaFiscalDao.selecionarPorId(id);
        fnumero.setText(notaFiscal.getNumero()+"");
        fserie.setText(notaFiscal.getSerie()+"");
        ccnpj.addItem(new ComboItem(notaFiscal.getFornecedor().getId(), notaFiscal.getFornecedor().getCnpj()));
        fcnpj.setText(notaFiscal.getFornecedor().getCnpj());
        fvalor.setText(notaFiscal.getValor()+"");
        Methods.setDateToDateChooser(fdata, notaFiscal.getData());
        
        notaFiscalProdutos = notaFiscalDao.selecionarProdutos(id);
        if (notaFiscalProdutos != null && notaFiscalProdutos.size() > 0) {
            notaFiscalProdutos.forEach(nfp -> {
                panelListarProdutos.addProduto(nfp);
            });
        }
    }
    
    /**
     * Limpa as mensagens de erro
     */
    private void clearErrors() {
        enumero.setText("");
        ecnpj.setText("");
        eserie.setText("");
        evalor.setText("");
        edata.setText("");
        linfo.setText("");
    }
    
    private Timer t;
    private void timerTest() {
        
        t = new Timer(500, (ActionEvent e) -> {
            Dialogs.hideLoadPopup(bg);
            
            switch (mode) {
                case "add":
                    self.dispose();
                    try {
                        // adiciona nota fiscal
                        int notaId = notaFiscalDao.inserir(notaFiscal);
                        if (notaId > 0) {
                            if (panelListarProdutos.notaProdutos.size() > 0) {
                                panelListarProdutos.notaProdutos.forEach(notaProduto -> {
                                    NotaFiscal nf = notaFiscalDao.selecionarPorId(notaId+"");
                                    if (nf != null && nf.getId() > 0) {
                                        notaProduto.setNotaFiscal(nf);
                                        // adiciona produto à nota fiscal
                                        notaFiscalDao.inserirProduto(notaProduto);
                                    }
                                });
                            }
                        }
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("AdicionadoComSucesso"));
                    } catch(Exception error) {
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("ErroAoTentarAdicionar"));
                        throw new RuntimeException("AddNotaFiscal.add: " + error);
                    }
                    // recarrega a tela pai
                    Navigation.updateLayout("", new Properties());
                    Navigation.updateLayout("notasFiscais", params);
                    break;
                case "edit":
                    self.dispose();
                    try {
                        // edita nota fiscal
                        notaFiscalDao.alterar(notaFiscal);
                        if (notaFiscal.getId() > 0) {
                            // deleta lista de produtos antiga
                            notaFiscalDao.deletarProdutos(notaFiscal.getId());
                            if (panelListarProdutos.notaProdutos.size() > 0) {
                                panelListarProdutos.notaProdutos.forEach(notaProduto -> {
                                    NotaFiscal nf = notaFiscalDao.selecionarPorId(notaFiscal.getId()+"");
                                    if (nf != null && nf.getId() > 0) {
                                        notaProduto.setNotaFiscal(nf);
                                        // adiciona nova lista de produtos
                                        notaFiscalDao.inserirProduto(notaProduto);
                                    }
                                });
                            }
                        }
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("EditadoComSucesso"));
                    } catch(Exception error) {
                        JOptionPane.showMessageDialog(null, Methods.getTranslation("ErroAoTentarEditar"));
                        throw new RuntimeException("AddNotaFiscal.edit: " + error);
                    }
                    // recarrega a tela pai
                    Navigation.updateLayout("", new Properties());
                    Navigation.updateLayout("notasFiscais", params);
                    break;
                default:
                    self.dispose();
                    Navigation.updateLayout("", new Properties());
                    Navigation.updateLayout("notasFiscais", params);
                    break;
            }
            
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
