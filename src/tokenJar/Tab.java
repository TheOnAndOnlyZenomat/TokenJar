/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokenJar;

import burp.IBurpExtenderCallbacks;
import burp.ITab;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.table.DefaultTableModel;
import java.net.URI;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


/**
 *
 * @author DanNegrea
 */
public class Tab extends javax.swing.JPanel implements ITab, TableModelListener{    
    private final DefaultTableModel tableModel;
    private final DataModel dataModel;
    final IBurpExtenderCallbacks callbacks;
    private final PersistSettings persistSettings;

    
    /**
     * Creates new form Panel
     */
    public Tab(IBurpExtenderCallbacks callbacks) {
        initComponents();
        this.callbacks = callbacks;
        
        persistSettings = new PersistSettings(callbacks, 50);
        
        tableModel = (DefaultTableModel) tokenTable.getModel();        
        dataModel = new DataModel(tableModel, callbacks);
       
        //Restore table or put demo data
        this.restoreTableData(persistSettings.restore());       
        this.setStatusColor();
        
        //tokenTable.getColumnModel().removeColumn(null);  //in case I want to hide some columns
        
        //(re)Initialize dataModel
        dataModel.init();

        tokenTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        
        tableModel.addTableModelListener(this);
    }
    
    public DataModel getDataModel() {
        return dataModel;
    }
    public DefaultTableModel getTableModel(){
        return tableModel;
    }
    
    public PersistSettings getPersistSettings(){
        return persistSettings;
    }
    
    @Override
    public String getTabCaption() {
        return "Token Jar";
    }

    @Override
    public Component getUiComponent() {
        return this;
    }
    
    @Override
    public void tableChanged(TableModelEvent e) {
        //*DEBUG*/callbacks.printOutput("TableChanged() e.getType="+e.getType()+"  getFirstRow=: "+e.getFirstRow()+" getLastRow="+e.getLastRow()+"");
       
        int type = e.getType(); 
        int rowId = e.getFirstRow();
        int column = e.getColumn();
        
        //No line was updated or the table was dumped
        if (rowId==-1)
            return;
        
        //Just 'value' field has changed. Expected to be the only change during running
        if (column==6 || column==10)
            return;
            
        Object enable = tableModel.getValueAt(rowId, 0);
         
        //If UPDATE and not valid row then uncheck 'Enable'
        //w/o checking 'enable!=null && (boolean)enable' next time will run the function body again and again
        if (enable!=null && (boolean)enable && type==TableModelEvent.UPDATE && !dataModel.checkRow(rowId, true)){
            //*DEBUG*/callbacks.printOutput("row updated, but not valid");
            tableModel.setValueAt(false, rowId, 0);
        }
        
        //Any change triggers unchecking of Master Enable
        //masterEnable.setSelected(false);
        //dataModel.setMasterEnable(false);
        //this.setStatusColor();
                
        // Replacing Save button functionality with unchecking Enable
        /*
        if (type==TableModelEvent.UPDATE || type==TableModelEvent.DELETE ){
            //Don't call init, just make save button Red
            SaveTable.setForeground(Color.red);
        }
        */
    }
    /*
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tokenTable = new javax.swing.JTable();
        addToken = new javax.swing.JButton();
        removeToken = new javax.swing.JButton();
        openRegexWindow = new javax.swing.JButton();
        masterEnable = new javax.swing.JCheckBox();
        masterDebug = new javax.swing.JCheckBox();
        importConf = new javax.swing.JButton();
        exportConf = new javax.swing.JButton();
        statusColor = new javax.swing.JTextField();
        goToSite1 = new javax.swing.JLabel();
        masterRepeater = new javax.swing.JCheckBox();
        masterIntruder = new javax.swing.JCheckBox();
        masterProxy = new javax.swing.JCheckBox();

        tokenTable.setAutoCreateRowSorter(true);
        tokenTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Enable", "Name", "url", "body", "cookie", "other", "Value", "Eval", "Regex", "Path"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tokenTable.setColumnSelectionAllowed(true);
        tokenTable.setDragEnabled(true);
        jScrollPane2.setViewportView(tokenTable);
        tokenTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tokenTable.getColumnModel().getColumnCount() > 0) {
            tokenTable.getColumnModel().getColumn(0).setMaxWidth(55);
            tokenTable.getColumnModel().getColumn(2).setMaxWidth(37);
            tokenTable.getColumnModel().getColumn(3).setMaxWidth(40);
            tokenTable.getColumnModel().getColumn(4).setMaxWidth(50);
            tokenTable.getColumnModel().getColumn(5).setMaxWidth(40);
        }

        addToken.setText("Add");
        addToken.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTokenActionPerformed(evt);
            }
        });

        removeToken.setText("Remove");
        removeToken.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeTokenActionPerformed(evt);
            }
        });

        openRegexWindow.setText("Regex");
        openRegexWindow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openRegexWindowActionPerformed(evt);
            }
        });

        masterEnable.setText("Enable");
        masterEnable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masterEnableActionPerformed(evt);
            }
        });

        masterDebug.setText("Debug");
        masterDebug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masterDebugActionPerformed(evt);
            }
        });

        importConf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/import-25px.png"))); // NOI18N
        importConf.setToolTipText("Import configuration");
        importConf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importConfActionPerformed(evt);
            }
        });

        exportConf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/export-25px.png"))); // NOI18N
        exportConf.setToolTipText("Export configuration");
        exportConf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportConfActionPerformed(evt);
            }
        });

        statusColor.setEditable(false);
        statusColor.setBorder(null);

        goToSite1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        goToSite1.setForeground(new java.awt.Color(51, 0, 255));
        goToSite1.setText("Getting Started");
        goToSite1.setToolTipText("<html>\n<i>Start from right to left</i><br/><br/>\n<b># Extracting the token</b><br/>\nPath: extract the token from this URL<br/>\nRegex: extract a certain value from response<br/>\nEval: transform the value<br/>\n<br/>\n<b>#Storing the token</b><br/>\nValue: initial value (first run)<br/>\n<br/>\n<b>#Apply the token</b><br/>\nName: token name in request<br/>\nurl, body,...: token position in request<br/>\n</html>");
        goToSite1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                goToSite1MouseClicked(evt);
            }
        });

        masterRepeater.setSelected(true);
        masterRepeater.setText("Repeater");
        masterRepeater.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masterRepeaterActionPerformed(evt);
            }
        });

        masterIntruder.setSelected(true);
        masterIntruder.setText("Intruder");
        masterIntruder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masterIntruderActionPerformed(evt);
            }
        });

        masterProxy.setSelected(true);
        masterProxy.setText("Proxy");
        masterProxy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masterProxyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(masterEnable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusColor, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(masterProxy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(masterIntruder)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(masterRepeater)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(masterDebug)
                        .addGap(165, 165, 165))
                    .addComponent(jScrollPane2))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(importConf, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(21, 21, 21)
                            .addComponent(exportConf, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(removeToken, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addToken, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(openRegexWindow, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(goToSite1)))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addToken, openRegexWindow, removeToken});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(masterEnable)
                        .addComponent(statusColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(masterDebug)
                        .addComponent(goToSite1)
                        .addComponent(masterRepeater)
                        .addComponent(masterIntruder)
                        .addComponent(masterProxy)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(addToken)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeToken)
                        .addGap(49, 49, 49)
                        .addComponent(openRegexWindow)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(exportConf, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(importConf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE))
                .addContainerGap())
        );

        goToSite1.getAccessibleContext().setAccessibleName("Getting Started");
        goToSite1.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 920, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addTokenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTokenActionPerformed
        //tableModel.addRow(new Object[]{ enable, name, url, body, cookie, other, value, eval, regex, path, debug });
        tableModel.addRow(new Object[]{ false, "", false, false, false, false, "", "grp[1]", "", "*", false });
        //no need to (re)init for an empty row
    }//GEN-LAST:event_addTokenActionPerformed

    private void removeTokenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeTokenActionPerformed
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove the selected line(s)?", "Warning", dialogButton);
        if(dialogResult == 0) { /*0 > Yes   1 > No */  
            // BUG: No success in fixing the freeze when deleting the last row of the table after a sort.
            SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                    int[] selectedRows = tokenTable.getSelectedRows();
                    for (int i = 0; i < selectedRows.length; i++){
                        int selectedRow = tokenTable.convertRowIndexToModel(selectedRows[i]);
                        /*DEBUG*/callbacks.printOutput("selectedRow =  " + selectedRow );
                        /*DEBUG*/callbacks.printOutput("selectedRow - i =  " + (selectedRow - i) );
                        tableModel.removeRow(selectedRow - i);
                    }
                }
            });
        }
    }//GEN-LAST:event_removeTokenActionPerformed

    private void openRegexWindowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openRegexWindowActionPerformed
        int selectedRow = tokenTable.getSelectedRow();                        
        selectedRow = tokenTable.convertRowIndexToModel(selectedRow);
        RegexWindow window = new RegexWindow(this, selectedRow, callbacks);                
        window.setVisible(true);        
    }//GEN-LAST:event_openRegexWindowActionPerformed

    Object getCell(int row, int column){
        return tableModel.getValueAt(row, column);
    }
    boolean setCell(int row, int column, Object value){
        if (value==null) return false;        
        tableModel.setValueAt(value, row, column);
        return true;
    }
    
    private void masterEnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masterEnableActionPerformed
        //*DEBUG*/callbacks.printOutput("masterEnable..() | "+dataModel.getMasterEnable());         
        if(masterEnable.isSelected()){            
            Vector dataInTable = tableModel.getDataVector();
            persistSettings.save(dataInTable);        
            dataModel.init();
            
            dataModel.setMasterEnable(true);
        }else{
            dataModel.setMasterEnable(false);
        }
        this.setStatusColor();   
         //*DEBUG*/callbacks.printOutput("end masterEnable..() | "+dataModel.getMasterEnable());
    }//GEN-LAST:event_masterEnableActionPerformed

    private void masterDebugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masterDebugActionPerformed
        dataModel.setMasterDebug(masterDebug.isSelected());        
        this.setStatusColor();
    }//GEN-LAST:event_masterDebugActionPerformed
    
    private void setStatusColor(){
        if(masterEnable.isSelected()){
            if(masterDebug.isSelected())
                statusColor.setBackground(Color.yellow);
            else
                statusColor.setBackground(Color.green);
        }else{
            statusColor.setBackground(Color.red);
        }    
        
    }
    
    private void exportConfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportConfActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);

        switch (result) {
        case JFileChooser.APPROVE_OPTION:
            File file = fileChooser.getSelectedFile();
            try (
                FileOutputStream fileOut = new FileOutputStream(file);
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            ){
                Vector dataInTable = tableModel.getDataVector();
                objectOut.writeObject(dataInTable);
            } catch (IOException ex) {
                PrintWriter stderr = new PrintWriter(callbacks.getStderr());
                ex.printStackTrace(stderr);
            }
            break;
        case JFileChooser.CANCEL_OPTION:
          break;
        case JFileChooser.ERROR_OPTION:
          callbacks.printError("Error export error");
          break;
        }
    }//GEN-LAST:event_exportConfActionPerformed

    private void importConfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importConfActionPerformed
        //*DEBUG*/callbacks.printOutput("importConfActionPerformed()");
        
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        /*DEBUG*/callbacks.printOutput("1");
        switch (result) {
        case JFileChooser.APPROVE_OPTION:
            File file = fileChooser.getSelectedFile();
            try (
                FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn); 
            ){    
                //*DEBUG*/callbacks.printOutput("2");
                restoreTableData((Vector) objectIn.readObject());
                //*DEBUG*/callbacks.printOutput("3");
                //dataModel.init(); //the Save buttons turns red, the init() will be called when the user saves
            } catch (IOException | ClassNotFoundException ex) {
                PrintWriter stderr = new PrintWriter(callbacks.getStderr());
                ex.printStackTrace(stderr);
            }
            break;
        case JFileChooser.CANCEL_OPTION:
          break;
        case JFileChooser.ERROR_OPTION:
          callbacks.printError("Error import error");
          break;
        }
        //*DEBUG*/callbacks.printOutput("end.");
    }//GEN-LAST:event_importConfActionPerformed

    private void goToSite1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goToSite1MouseClicked
        URI uri = URI.create("https://dannegrea.github.io/TokenJar");
        if (uri != null && Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(uri);
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
    }//GEN-LAST:event_goToSite1MouseClicked

    private void masterProxyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masterProxyActionPerformed
        dataModel.setMasterProxy(masterProxy.isSelected());   
    }//GEN-LAST:event_masterProxyActionPerformed

    private void masterIntruderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masterIntruderActionPerformed
        dataModel.setMasterIntruder(masterIntruder.isSelected());
    }//GEN-LAST:event_masterIntruderActionPerformed

    private void masterRepeaterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masterRepeaterActionPerformed
        dataModel.setMasterRepeater(masterRepeater.isSelected());
    }//GEN-LAST:event_masterRepeaterActionPerformed

    private void restoreTableData(Vector dataInTable) {
        if (dataInTable==null) return;
        
         //Get the column names
        Vector<String> columnsInTable = new Vector<>(tableModel.getColumnCount());
        for (int i=0; i<tableModel.getColumnCount(); i++){
            columnsInTable.add(tableModel.getColumnName(i));
        }

        //restore the DataVector
        tableModel.setDataVector(dataInTable, columnsInTable);
        
        //setDataVector distroys these settings, I make them again
        tokenTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tokenTable.getColumnModel().getColumnCount() > 0) {
            tokenTable.getColumnModel().getColumn(0).setMaxWidth(55);
            tokenTable.getColumnModel().getColumn(2).setMaxWidth(37);
            tokenTable.getColumnModel().getColumn(3).setMaxWidth(40);
            tokenTable.getColumnModel().getColumn(4).setMaxWidth(50);
            tokenTable.getColumnModel().getColumn(5).setMaxWidth(40);
        }
    }
       
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToken;
    private javax.swing.JButton exportConf;
    private javax.swing.JLabel goToSite1;
    private javax.swing.JButton importConf;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JCheckBox masterDebug;
    private javax.swing.JCheckBox masterEnable;
    private javax.swing.JCheckBox masterIntruder;
    private javax.swing.JCheckBox masterProxy;
    private javax.swing.JCheckBox masterRepeater;
    private javax.swing.JButton openRegexWindow;
    private javax.swing.JButton removeToken;
    private javax.swing.JTextField statusColor;
    private javax.swing.JTable tokenTable;
    // End of variables declaration//GEN-END:variables

}
