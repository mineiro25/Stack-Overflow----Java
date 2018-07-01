/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.interfaces;

import engine.TCDExample;
import frontend.controls.JFrameController;
import frontend.controls.RunBtnController;
import frontend.controls.QueryController;
import frontend.controls.SelectionController;
import frontend.interfaces.Menu;
import frontendModel.QueryModel;
import java.awt.TextArea;
import li3.TADCommunity;

public class Gui {
   

    public static void clearTextArea(TextArea target) {
        target.setText("");
    }

    public static void updateQuestionAreaText(int question, TextArea target) {

        clearTextArea(target);
        target.setText("" + question);
    }

    public void defineMVC(TADCommunity sistemModel) {
        QueryView qview = new QueryView();
        QueryModel qModel = new QueryModel(sistemModel);
        
        //interface gráfico
        Menu menu = new Menu();
        //jframe windowListener
        menu.addWindowListener(new JFrameController(sistemModel));
        
        //controlo da execução das queries
        QueryController qcontroller = new 
            QueryController(qModel, qview);
        
        /*
        controlo do combobox // query escolhida
        */
        SelectionController controller = new 
        SelectionController(
                menu.getComboSelector(), 
                menu);
        //adicionar actionListener do comboBox
        menu.getComboSelector().addActionListener(controller);
        //controller.addQueryView(qcontroller.getQueryView());
        controller.addQueryControl(qcontroller);
        
        //controlo do botão de execução da query
        RunBtnController runController = 
                new RunBtnController(menu.getRunBtn(), 
                        menu, 
                        qcontroller);
        menu.getRunBtn().addActionListener(runController);
               
         
        /* Criar e executar o formulário visual */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                menu.setVisible(true);
            }
        });
    } 
    
    
    public void startGui(TADCommunity sistema) {
        
        defineMVC(sistema);
       
    }
}
