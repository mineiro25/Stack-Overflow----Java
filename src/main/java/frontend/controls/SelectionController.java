/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.controls;

import frontend.interfaces.Constants;
import frontend.interfaces.Menu;
import frontend.interfaces.QueryView;
import frontendModel.QueryModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author Admin
 */
public class SelectionController implements ActionListener {
    
    private JComboBox comboSelection;
    private Menu guiObject;
    private QueryView queryInfo;
    private QueryModel queryModel;
    private QueryController queryActions;
    

    public SelectionController(
            JComboBox dropdown, 
            Menu gui) {
        this.comboSelection = dropdown;
        this.guiObject = gui;
       
    }
    
    public void addQueryView(QueryView view) {
        this.queryInfo = view;
    }
    
    public void addQueryModel(QueryModel mode) {
        this.queryModel = mode;
    }
    
    public void addQueryControl(QueryController control) {
        this.queryActions = control;
        this.addQueryView(control.getQueryView());
        this.addQueryModel(control.getQueryModel());
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int index = comboSelection.getSelectedIndex(); 
        //index [0,10] -> questões [1,11]
        index++;
        String queryArgs = queryModel.getQueryInputsSignature(index);
        
        String questionText = queryInfo.getQueryQuestion(index, queryArgs);
        
        //definir query atual
       
        guiObject.setQuestionAreaText(questionText);
        guiObject.setCurrentQuestion(index);
        guiObject.setCurrentQuestionString(Constants.numberNames[index]);
        //prepare gui inputs needed
        int nrInputs = queryActions.getInputsNeededForQuery(index);
        //desbloquear inputs necessários
        guiObject.unlockInputs(nrInputs);
        
    }
    
    
    
    
}
