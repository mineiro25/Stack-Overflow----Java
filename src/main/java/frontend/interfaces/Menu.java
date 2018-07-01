/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.interfaces;


import frontendModel.ComboModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import li3.TADCommunity;

/**
 *
 * @author Nuno
 */
public class Menu extends javax.swing.JFrame {
    
    
    private int currentQuestion;
    private String currentQuestionString;
    private int currentCombination;

    /**
     * Creates new form Menu
     
     */
    public Menu() {
        initComponents();
        
        this.setComboBoxModel();
        this.disableAllInputs();
      
    }
    
    public int getCurrentCombination() {
        return this.currentCombination;
    }
    
    public int getCurrentQuestion() {
        return this.currentQuestion;
    }
    
    public void setCurrentQuestion(int question) {
        this.currentQuestion = question;
    }
    
    public void setComboBoxModel() {
        /**
         * definir modelo do dropdown
         */
        ComboModel model = new ComboModel();
        this.jQuestionCombo.setModel(model);
    }
    
    public JComboBox getComboSelector() {
        return this.jQuestionCombo;
    }
    
    public JTextArea getQuestionArea() {
        return this.questionInfoTextArea;
    }
    
    public JButton getRunBtn() {
        return this.runButton;
    }
    
    public String getCurrentQuestionAsText() {
        return this.currentQuestionString;
    }
    public void setCurrentQuestionString(String name) {
        this.currentQuestionString = name;
    }
    
    private void clearQuestionArea() {
        this.questionInfoTextArea.setText("");
    }
    
    private void clearAnswerArea() {
        this.jTextRespostas.setText("");
    }
    
    public void setAnswerText(String text) {
        this.jTextRespostas.setText(text);
    }
    
    public void setQuestionAreaText(String text) {
        this.clearQuestionArea();
        this.questionInfoTextArea.setText(text);
    }
   
    
    public void showInputError(String error){
        this.jTextRespostas.append("\n" + error);
    }
    
    public void clearGui() {
        clearQuestionArea();
        clearAnswerArea();
        setComboBoxModel();
        disableAllInputs();
    }
    
    public void disableAllInputs() {
        this.input1.setEnabled(false);
        this.input2.setEnabled(false);
        this.input3.setEnabled(false);
        this.dateEnd.setEnabled(false);
        this.dateStart.setEnabled(false);
    }
    
    public void enableInput(javax.swing.JTextField[] inputs) {
        for(javax.swing.JTextField field: inputs) {
            field.setEnabled(true);
        }
    }
    
    public void unlockInputs(int combination) {
             /*
    1 -> 1 input
    2 -> 2 input
    3 -> 1 input, 3 dates
    4 -> 2 inputs, 2 dates
    5 -> 3 inputs
    6 -> 3 inputs, 2 dates
    7 -> 2 dates
*/
             disableAllInputs();
             
             switch(combination){
                 case 1: 
                     enableInput(new javax.swing.JTextField[]{input1}); 
                     break;
                 case 2:
                     enableInput(new javax.swing.JTextField[]
                     {input1, input2});
                     break;
                 case 3:
                     enableInput(new javax.swing.JTextField[]
                     {input1, dateStart, dateEnd});
                     break;
                 case 4:
                     enableInput(new javax.swing.JTextField[]{
                         input1, input2, dateStart, dateEnd});
                     break;
                 case 5:
                     enableInput(new javax.swing.JTextField[]{
                         input1, input2, input3});
                     break;
                 case 6:
                     enableInput(new javax.swing.JTextField[]{
                         input1, input2, input3,
                     dateStart, dateEnd});
                     break;
                 case 7:
                     enableInput(new javax.swing.JTextField[]
                     {
                     dateStart, dateEnd
                     });
             }
             this.currentCombination = combination;
    }
    
    public javax.swing.JTextField[] getInputs(int combination) {
        
        switch(combination){
                 case 1: 
                     return new javax.swing.JTextField[]{input1};
                     
                 case 2:
                     return new javax.swing.JTextField[]
                     {input1, input2};
                   
                 case 3:
                    return new javax.swing.JTextField[]
                     {input1, dateStart, dateEnd};
                     
                 case 4:
                     return new javax.swing.JTextField[]{
                         input1, input2, dateStart, dateEnd};
                     
                 case 5:
                      return new javax.swing.JTextField[]{
                         input1, input2, input3};
                     
                 case 6:
                     return new javax.swing.JTextField[]{
                         input1, input2, input3,
                         dateStart, dateEnd};
                 case 7: 
                     return new javax.swing.JTextField[]{
                     dateStart, dateEnd};
             }
        return null;
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
        runButton = new javax.swing.JButton();
        cleanButton = new javax.swing.JButton();
        returnPanel = new javax.swing.JScrollPane();
        jTextRespostas = new javax.swing.JTextArea();
        enuciadoQuerie = new javax.swing.JScrollPane();
        questionInfoTextArea = new javax.swing.JTextArea();
        input1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        input2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dateStart = new javax.swing.JTextField();
        questionLabel = new javax.swing.JLabel();
        jQuestionCombo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        input3 = new javax.swing.JTextField();
        dateEnd = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Stack Overflow - Laboratórios Informática 3");

        runButton.setText("Correr");
        runButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cleanButton.setText("Limpar");
        cleanButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cleanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanButtonActionPerformed(evt);
            }
        });

        jTextRespostas.setColumns(20);
        jTextRespostas.setRows(5);
        returnPanel.setViewportView(jTextRespostas);

        questionInfoTextArea.setColumns(20);
        questionInfoTextArea.setRows(5);
        enuciadoQuerie.setViewportView(questionInfoTextArea);

        input1.setText("arg 1");
        input1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                input1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Argumento 1");

        jLabel2.setText("Argumento 2");

        input2.setText("arg 2");

        jLabel3.setText("Data Inicial");

        jLabel4.setText("Data final");

        dateStart.setText("aaaa-mm-dd");
        dateStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateStartActionPerformed(evt);
            }
        });

        questionLabel.setText("Selecionar Questão");

        jQuestionCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Argumento 3");

        input3.setText("arg 3");

        dateEnd.setText("aaaa-mm-dd");
        dateEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateEndActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(enuciadoQuerie, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(returnPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(runButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cleanButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(questionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jQuestionCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dateStart, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(9, 9, 9)
                                                .addComponent(input1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(input2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(input3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(42, 68, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(questionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jQuestionCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(input1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(input2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(input3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateStart, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(dateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(enuciadoQuerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(returnPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(runButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cleanButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dateEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateEndActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateEndActionPerformed

    private void dateStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateStartActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateStartActionPerformed

    private void input1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_input1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_input1ActionPerformed

    private void cleanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanButtonActionPerformed
        // TODO add your handling code here:
    this.disableAllInputs();
    this.clearAnswerArea();
    this.clearQuestionArea();
    }//GEN-LAST:event_cleanButtonActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cleanButton;
    private javax.swing.JTextField dateEnd;
    private javax.swing.JTextField dateStart;
    private javax.swing.JScrollPane enuciadoQuerie;
    private javax.swing.JTextField input1;
    private javax.swing.JTextField input2;
    private javax.swing.JTextField input3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> jQuestionCombo;
    private javax.swing.JTextArea jTextRespostas;
    private javax.swing.JTextArea questionInfoTextArea;
    private javax.swing.JLabel questionLabel;
    private javax.swing.JScrollPane returnPanel;
    private javax.swing.JButton runButton;
    // End of variables declaration//GEN-END:variables
}
