/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.controls;

import frontend.interfaces.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class RunBtnController implements ActionListener {

    private Menu guiObject;
    private QueryController qControl;
    private JButton runBtn;

    public RunBtnController(JButton run, Menu view, QueryController controller) {
        this.runBtn = run;
        this.guiObject = view;
        this.qControl = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        
        int currentQuestion = guiObject.getCurrentQuestion();
        int currentInputCombination = guiObject.getCurrentCombination();

        javax.swing.JTextField[] inputsToParse
                = guiObject.getInputs(currentInputCombination);

        /**
         * fazer validações (...)
         *
         */
        
        String[] args = new String[inputsToParse.length];
        for (int i = 0; i < inputsToParse.length; i++) {
            args[i] = inputsToParse[i].getText();
        }

        try {
            Object response = qControl.runUserSelectedQuery(currentQuestion, args);
            if (response != null) {
                guiObject.setAnswerText(response.toString());
            } else {
                guiObject.showInputError("Erro na execução, verifique os inputs submetidos");
            }
        } catch (Exception ex) {
                guiObject.showInputError("Exception, verifique os inputs submetidos");
                ex.printStackTrace();
        }
    }

}
