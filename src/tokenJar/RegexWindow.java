/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokenJar;

import burp.IBurpExtenderCallbacks;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
/**
 *
 * @author DanNegrea
 */
public class RegexWindow extends javax.swing.JFrame {

    private final Tab parent;
    private int selectedRow;
    private PersistSettings persistSettings;

    private IBurpExtenderCallbacks callbacks;
    /**
     * Creates new form RegexTestWindow
     * @param parent
     * @param selectedRow
     */
    public RegexWindow(Tab parent, int selectedRow, IBurpExtenderCallbacks callbacks){
        initComponents();

        this.callbacks = callbacks;

        this.parent = parent;
        this.selectedRow = selectedRow;
        this.persistSettings = parent.getPersistSettings();

        String name = parent.getCell(selectedRow, 1).toString();
        String evalJS = parent.getCell(selectedRow, 13).toString();
        Object regex = parent.getCell(selectedRow, 14);
        String path = parent.getCell(selectedRow, 15).toString();

        //Indicate in UI the name of the parameter and the path
        nameField.setText(name);
        pathField.setText(path);

        try{
            ;
        }
        catch(Exception ex){
            PrintWriter stderr = new PrintWriter(callbacks.getStderr());
            ex.printStackTrace(stderr);
        }

        //Restore Eval field from persistent settings
        Object[] exprs = persistSettings.getEval();
        for(int i=0; i<exprs.length; i++){
            if (exprs[i]!=null)
                evalField.addItem( (String) exprs[i]);
        }
        evalField.setSelectedItem( evalJS );

        //Restore Regex field from persistent settings
        exprs = persistSettings.getRegex();
        for(int i=0; i<exprs.length; i++){
            if (exprs[i]!=null)
                regexField.addItem( (String) exprs[i]);
        }
        regexField.setSelectedItem( regex );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pathField = new javax.swing.JTextField();
        testRegex = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        regexField = new javax.swing.JComboBox();
        evalField = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        responseSnippet = new javax.swing.JTextArea();
        valueField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        saveOptions = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        groupDebug = new javax.swing.JTextArea();

        jLabel2.setText("Regex");

        jLabel3.setText("Name");

        nameField.setEditable(false);
        nameField.setBackground(new java.awt.Color(239, 239, 239));

        jLabel4.setText("Path");

        pathField.setEditable(false);
        pathField.setBackground(new java.awt.Color(239, 239, 239));
        pathField.setToolTipText("");
        pathField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pathFieldActionPerformed(evt);
            }
        });

        testRegex.setText("Test");
        testRegex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testRegexActionPerformed(evt);
            }
        });

        jLabel5.setText("Insert the regular expresion in order to obtain the Value. It needs to have one group.");

        regexField.setEditable(true);
        regexField.setToolTipText("");

        evalField.setEditable(true);
        evalField.setToolTipText("");

        jLabel8.setText("Eval");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(pathField, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel8))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(evalField, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(regexField, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(testRegex, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(8, 8, 8))
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(10, 10, 10))
                                );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(pathField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(regexField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addComponent(evalField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(testRegex, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
                    );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {evalField, regexField});

        jLabel6.setText("Insert the response snippet from where the token is extracted.");

        responseSnippet.setColumns(20);
        responseSnippet.setRows(5);
        jScrollPane1.setViewportView(responseSnippet);

        valueField.setEditable(false);

        jLabel1.setText("Value");

        saveOptions.setText("Save");
        saveOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveOptionsActionPerformed(evt);
            }
        });

        jLabel7.setText("Click Test and check if you obtain the desired Value");

        groupDebug.setEditable(false);
        groupDebug.setBackground(new java.awt.Color(239, 239, 239));
        groupDebug.setColumns(20);
        groupDebug.setRows(3);
        jScrollPane2.setViewportView(groupDebug);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(valueField, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(saveOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addContainerGap())
                );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addComponent(jLabel6)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel7)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(valueField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(saveOptions))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveOptionsActionPerformed

        groupDebug.setText(groupDebug.getText() +"\n Saved");

        //save Eval and Extract to parent window
        parent.setCell(selectedRow, 13, (Object) evalField.getSelectedItem()); //evelCol
        parent.setCell(selectedRow, 14, (Object) regexField.getSelectedItem()); //regexCol

        //save Eval and Extract in persistant object
        String evalJS = evalField.getSelectedItem().toString();
        String regex = regexField.getSelectedItem().toString();

        persistSettings.pushEval(evalJS);
        persistSettings.pushRegex(regex);

        //Save the settings to Burp
        persistSettings.save(parent.getTableModel().getDataVector());
    }//GEN-LAST:event_saveOptionsActionPerformed

    private void testRegexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testRegexActionPerformed
        Highlighter hl = responseSnippet.getHighlighter();
        hl.removeAllHighlights(); /*Delete all higlights*/
        valueField.setText(""); /*Set empty value*/
        groupDebug.setText(""); /*Set empty value*/

        StringBuilder debug = new StringBuilder();

        String evalJS = evalField.getSelectedItem().toString();
        String regex = regexField.getSelectedItem().toString();//.replace("\\","\\\\"); // no need to escape \ from user input
        String snippet = responseSnippet.getText();

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(snippet);

        if(matcher.find())
        {
            /*Highlight what was found*/
            String responseText = responseSnippet.getText();
            String foundText = matcher.group(0);
            int foundTextLen= 0;
            int foundTextIndex = -1;
            if (foundText!=null) //bellow cannot be null
                foundTextLen = foundText.length();
            if (foundTextLen>0)  //below cannot be empty
                foundTextIndex = responseText.indexOf(foundText);
            if(foundTextIndex >= 0){ //beloew it was find in the snippet
                try {
                    hl.addHighlight(foundTextIndex, foundTextIndex +foundTextLen, DefaultHighlighter.DefaultPainter);
                    //foundTextIndex = responseText.indexOf(responseText, foundTextIndex + foundTextLen); //not needed anymore; I changed while to if
                } catch (BadLocationException ex) {
                    /*DEBUG*/debug.append("! Exception: ").append(ex.toString()).append("\n");
                    PrintWriter stderr = new PrintWriter(callbacks.getStderr());
                    ex.printStackTrace(stderr);
                }
            }
            /*end Highlight*/

            /*Eval the value*/
            int grpCount = matcher.groupCount()+1; //if I have only group 0 it must be 1
            String[] grpValues = new String[grpCount];
            debug.append("grpCount=").append(grpCount).append("\n");
            for (int i=0; i<grpCount; i++){
                grpValues[i]= matcher.group(i);
                debug.append("grp["+i+"]=").append(grpValues[i]).append("\n");
            }

            Context cx = Context.enter();
            try {
                Scriptable scope = cx.initStandardObjects();

                //inject in JavaScript context the captured groups
                Object jsGrpValues = Context.javaToJS(grpValues, scope);
                ScriptableObject.putProperty(scope, "grp", jsGrpValues);

                //compute the value by evaluating JavaScript
                Object result = cx.evaluateString(scope, evalJS, "<evalJS>", 1, null);
                String value = Context.toString(result);


                valueField.setText(value); //set the actual value

            } catch (Exception ex) {
                /*DEBUG*/debug.append("! Exception: ").append(ex.toString()).append("\n");
                PrintWriter stderr = new PrintWriter(callbacks.getStderr());
                ex.printStackTrace(stderr);
            } finally {
                Context.exit();
            }
            groupDebug.setText(debug.toString());
            /*end Eval the value*/

            /*Extract - Add only once the value to the ComboBox*/
            int noIntems = regexField.getItemCount();
            boolean found = false;

            for (int i=0; i<noIntems; i++){
                if (regexField.getItemAt(i).toString().equals(regex)){
                    found = true;
                    break;
                }
            }
            if (!found)
                regexField.addItem((Object) regex);
            /*end Extract - Add*/

            /*Eval - Add only once the value to the ComboBox*/
            noIntems = evalField.getItemCount();
            found = false;

            for (int i=0; i<noIntems; i++){
                if (evalField.getItemAt(i).toString().equals(evalJS)){
                    found = true;
                    break;
                }
            }
            if (!found)
                evalField.addItem((Object) evalJS);
            /*end Eval - Add*/
        }
    }//GEN-LAST:event_testRegexActionPerformed

    private void pathFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pathFieldActionPerformed
                                                                           // TODO add your handling code here:
    }//GEN-LAST:event_pathFieldActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox evalField;
    private javax.swing.JTextArea groupDebug;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField pathField;
    private javax.swing.JComboBox regexField;
    private javax.swing.JTextArea responseSnippet;
    private javax.swing.JButton saveOptions;
    private javax.swing.JButton testRegex;
    private javax.swing.JTextField valueField;
    // End of variables declaration//GEN-END:variables
}
