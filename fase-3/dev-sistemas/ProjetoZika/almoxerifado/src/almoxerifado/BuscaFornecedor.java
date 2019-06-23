package almoxerifado;


import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.apache.logging.log4j.LogManager;

public class BuscaFornecedor extends javax.swing.JDialog {

    private JTextField id, nome, cnpj;
    private Conexao bd;

    public BuscaFornecedor(java.awt.Frame parent, boolean modal, JTextField id, JTextField nome, JTextField cnpj, Conexao bd) {
        super(parent, modal);
        initComponents();

        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.bd = bd;

        try {

            jTableFornecedores.setModel(populaJTable());

        } catch (Exception cnfex) {
            System.err.println("Falha ao carregar os dados");
            cnfex.printStackTrace();
            System.exit(0);
            
                LogManager.getLogger().error("fornecedor encontrado!");
        }
        

    }

    public DefaultTableModel populaJTable() throws Exception {
        DefaultTableModel dtm = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String sql = "select * from fornecedor where nome like '%" + jTextFieldBusca.getText() + "%'";
        bd.fazerConsulta(sql);

        //adiciona as colunas
        dtm.addColumn("ID");
        dtm.addColumn("CNPJ");
        dtm.addColumn("NOME");
        while (bd.resultado.next()) {
            //pega os valores do bd para popular tabela
            dtm.addRow(new String[]{bd.resultado.getString("idfornecedor"), bd.resultado.getString("cnpj"), bd.resultado.getString("nome")});
        }
        return dtm;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFornecedores = new javax.swing.JTable();
        jTextFieldBusca = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTableFornecedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableFornecedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableFornecedoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableFornecedores);

        jTextFieldBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBuscaKeyReleased(evt);
            }
        });

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("almoxerifado/Bundle"); // NOI18N
        jLabel1.setText(bundle.getString("BuscaFornecedor.jLabel1.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldBusca))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldBuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscaKeyReleased
        try {
            jTableFornecedores.setModel(populaJTable());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jTextFieldBuscaKeyReleased

    private void jTableFornecedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFornecedoresMouseClicked
        if (evt.getClickCount() == 2) {
            id.setText("" + jTableFornecedores.getValueAt(jTableFornecedores.getSelectedRow(), 0));
            nome.setText("" + jTableFornecedores.getValueAt(jTableFornecedores.getSelectedRow(), 2));
            cnpj.setText("" + jTableFornecedores.getValueAt(jTableFornecedores.getSelectedRow(), 1));
            this.setVisible(false);

        }
    }//GEN-LAST:event_jTableFornecedoresMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableFornecedores;
    private javax.swing.JTextField jTextFieldBusca;
    // End of variables declaration//GEN-END:variables
}
