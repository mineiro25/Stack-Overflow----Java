/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontendModel;

import frontend.interfaces.Constants;
import li3.TADCommunity;

/**
 *
 * @author Nuno
 */
public class QueryModel {
    
    private TADCommunity sis;
    private int currentQuestion;
    private String currentQuestionString;
    private int currentCombination;
    
    public QueryModel(TADCommunity sistema) {
        this.sis = sistema;
    }
    
    public TADCommunity getSistemaObject() {
        return this.sis;
    }
    
     public int getArgumentsForQuery(int queryNumber) {
        return Constants.QUERY_INPUTS_NEEDED[queryNumber];
    }
     
     public String getQueryInputsSignature(int queryNumber) {
         return Constants.QUERY_INPUT_SIGNATURE[queryNumber];
     }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
        this.currentQuestionString = Constants.numberNames[currentQuestion];
    }

    public String getCurrentQuestionString() {
        return currentQuestionString;
    }

    public void setCurrentQuestionString(String currentQuestionString) {
        this.currentQuestionString = currentQuestionString;
    }

    public int getCurrentCombination() {
        return currentCombination;
    }

    public void setCurrentCombination(int currentCombination) {
        this.currentCombination = currentCombination;
    }
     
     
    
}
