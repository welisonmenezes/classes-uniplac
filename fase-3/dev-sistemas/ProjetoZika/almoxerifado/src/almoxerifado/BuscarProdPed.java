package almoxerifado;


import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class BuscarProdPed extends javax.swing.JFrame {

    private JTextField id, nome, unidade, Status;
    private Conexao bd;
    private Requisitar janelaPai;
    
    public BuscarProdPed( Conexao bd, Requisitar pai) {
        
        initComponents();
        this.janelaPai = pai;
        this.bd = bd;
        

        try {
            this.bd.conectarBanco();

            jTableBuscarProd.setModel(populaJTable());

        } catch (Exception cnfex) {
            System.err.println("Falha ao carregar os dados");
            cnfex.printStackTrace();
            System.exit(0);
        }

    }

    public DefaultTableModel populaJTable() throws Exception {
        DefaultTableModel dtm = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String sql = "select * from  produto where nome like '%" + jTextFieldBusca.getText() + "%'";
        bd.fazerConsulta(sql);

        //adiciona as colunas
        dtm.addColumn("Id");
        dtm.addColumn("Nome");
        dtm.addColumn("unidade");
        while (bd.resultado.next()) {
            //pega os valores do bd para popular tabela
            dtm.addRow(new String[]{bd.resultado.getString("idproduto"), bd.resultado.getString("nome"), bd.resultado.getString("unidade")});
        }
        return dtm;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableBuscarProd = new javax.swing.JTable();
        jTextFieldBusca = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTableBuscarProd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableBuscarProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableBuscarProdMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableBuscarProd);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("almoxerifado/Bundle"); // NOI18N
        if (jTableBuscarProd.getColumnModel().getColumnCount() > 0) {
            jTableBuscarProd.getColumnModel().getColumn(0).setHeaderValue(bundle.getString("BuscarProdPed.jTableBuscarProd.columnModel.title0")); // NOI18N
            jTableBuscarProd.getColumnModel().getColumn(1).setHeaderValue(bundle.getString("BuscarProdPed.jTableBuscarProd.columnModel.title1")); // NOI18N
            jTableBuscarProd.getColumnModel().getColumn(2).setHeaderValue(bundle.getString("BuscarProdPed.jTableBuscarProd.columnModel.title2")); // NOI18N
            jTableBuscarProd.getColumnModel().getColumn(3).setHeaderValue(bundle.getString("BuscarProdPed.jTableBuscarProd.columnModel.title3")); // NOI18N
        }

        jTextFieldBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBuscaKeyReleased(evt);
            }
        });

        jLabel1.setText(bundle.getString("BuscarProdPed.jLabel1.text")); // NOI18N

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
            jTableBuscarProd.setModel(populaJTable());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jTextFieldBuscaKeyReleased

    private void jTableBuscarProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableBuscarProdMouseClicked

        //int row = janelaPai.jTableRequisitar.getSelectedRow();
        JTable source = (JTable)evt.getSource();
            int row = source.rowAtPoint( evt.getPoint() );
            //System.out.println(row);
        if(row != -1){
        String id = source.getValueAt(row, 0).toString();
        String Nome = source.getValueAt(row, 1).toString();
        String unidade = source.getValueAt(row, 2).toString();
        
        if (evt.getClickCount() == 2) {
            DefaultTableModel model = (DefaultTableModel) janelaPai.jTableRequisitar.getModel();
            //model.setRowCount(0);
            String campo[] = {id, Nome, unidade,""};
            model.addRow(campo);
            this.setVisible(false);

          }
       }
    }//GEN-LAST:event_jTableBuscarProdMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableBuscarProd;
    private javax.swing.JTextField jTextFieldBusca;
    // End of variables declaration//GEN-END:variables
}
