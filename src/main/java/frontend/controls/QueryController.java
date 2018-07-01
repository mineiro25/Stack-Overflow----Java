/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.controls;

import common.Pair;
import frontend.interfaces.QueryView;
import frontendModel.QueryModel;
import java.time.LocalDate;
import java.util.List;
import li3.TADCommunity;


public class QueryController{
    
    private TADCommunity model;
    private QueryView view;
    private QueryModel qModel;

    
    public QueryController(QueryModel model, QueryView view) {
        this.qModel = model;
        this.model = qModel.getSistemaObject();
        this.view = view;
    }
    
    public QueryModel getQueryModel() {
        return this.qModel;
    }
    
    public QueryView getQueryView() {
        return this.view;
    }
    
    public void setCurrentQuery(int selectQuery) {
        qModel.setCurrentQuestion(selectQuery);
    }
    
    public int getInputsNeededForQuery(int queryNumber) {
        return qModel.getArgumentsForQuery(queryNumber);
    }
    
    public Pair<String, String> executeQueryOne(String[] args) {
        Pair<String, String> q1 = model.infoFromPost(
                Long.parseLong(args[0], 10));
        return q1;
    }
    
    public Pair<String, String> executeQueryOne(int argument) {
        Pair<String, String> q1 = model.infoFromPost(argument);
        return q1;
    }
    
    public List<Long> executeQueryTwo(String[] limit) {
        List<Long> q2 = model.topMostActive(
        Integer.parseInt(limit[0]));
        return q2;
    }
    
    public List<Long> executeQueryTwo(int limit) {
        List<Long> q2 = model.topMostActive(100);
        return q2;
    }
    
    public Pair<Long,Long> executeQueryThree(
            String[] dates) {
        Pair<Long, Long> q3 = model.totalPosts(
                LocalDate.parse(dates[0]),
                LocalDate.parse(dates[1]));
        return q3;
    }
    
    
    public Pair<Long,Long> executeQueryThree(LocalDate lower, LocalDate upper) {
        Pair<Long, Long> q3 = model.totalPosts(
                lower,
                upper);
        return q3;
    }
    
     public List<Long> executeQueryFour(
             String[] args){
        List<Long> query4 = model.questionsWithTag(
                args[0], 
                LocalDate.parse(args[1]),
                LocalDate.parse(args[2]));
        return query4;
    }
     
    public List<Long> executeQueryFour(String tag, LocalDate lower, LocalDate upper){
        List<Long> query4 = model.questionsWithTag(
                tag, lower, upper);
        return query4;
    }
    
    public Pair<String, List<Long>> executeQueryFive(String[] idUser){
        Pair<String, List<Long>> q5 = model.getUserInfo(
        Long.parseLong(idUser[0],10));
        return q5;
    }
    
    public Pair<String, List<Long>> executeQueryFive(Long idUser){
        Pair<String, List<Long>> q5 = model.getUserInfo(idUser);
        return q5;
    }
    
    public List<Long> executeQuerySix(String[] args) {
        
        List<Long> q6 = model.mostVotedAnswers(
                Integer.parseInt(args[0]), 
                LocalDate.parse(args[1]),
                LocalDate.parse(args[2]));
        
        return q6;
    }
    
    public List<Long> executeQuerySix(int numberAnswers, 
            LocalDate lower, LocalDate upper) {
        
        List<Long> q6 = model.mostVotedAnswers(numberAnswers, 
                lower, upper);
        
        return q6;
    }
    
    public List<Long> executeQuerySeven(String[] args) {
        List<Long> q7 = model.mostAnsweredQuestions(
               Integer.parseInt(args[0]), 
                LocalDate.parse(args[1]),
                LocalDate.parse(args[2]));
        return q7;
    }
    
    public List<Long> executeQuerySeven(int numberQuestions, LocalDate lower, 
            LocalDate upper) {
        List<Long> q7 = model.mostAnsweredQuestions(
                numberQuestions, lower, upper);
        return q7;
    }
    
    public List<Long> executeQueryEight(String[] args) {
        List<Long> q8 = model.containsWord(
                Integer.parseInt(args[0]), args[1]);
        return q8;
    }
    
    public List<Long> executeQueryEight(int numberQuestions, String needle) {
        List<Long> q8 = model.containsWord(numberQuestions, needle);
        return q8;
    }
    
    public List<Long> executeQueryNine(String[] args){
            List<Long> q9 = model.bothParticipated(
                    Integer.parseInt(args[0]), 
                    Long.parseLong(args[1], 10), 
                    Long.parseLong(args[2], 10));
            return q9;
    }
    
    public List<Long> executeQueryNine(int numberQuestions, 
            long idUserA, long idUserB){
            List<Long> q9 = model.bothParticipated(numberQuestions, 
                    idUserA, idUserB);
            return q9;
    }
    
    public Long executeQueryTen(String[] args) {
         long q10 = model.betterAnswer(Long.parseLong(args[0], 10));
         return q10;
    }
    
    public Long executeQueryTen(Long idQuestion) {
         long q10 = model.betterAnswer(idQuestion);
         return q10;
    }
    public List<Long> executeQueryEleven(
            String[] args) {
            List<Long> q11 = model.mostUsedBestRep(
                    Integer.parseInt(args[0]), 
                    LocalDate.parse(args[1]),
                    LocalDate.parse(args[2]));
            return q11;
    }
    
    public List<Long> executeQueryEleven(int numberTags,
            LocalDate lower, LocalDate upper) {
            List<Long> q11 = model.mostUsedBestRep(
                    numberTags, 
                    lower,
                    upper);
            return q11;
    }
   
    public Object runUserSelectedQuery(int currentQuestion, String[] args) {
        Object response = null;
            switch(currentQuestion){
            
            case 1: 
                response = this.executeQueryOne(args);
                break;
            case 2:
                response = this.executeQueryTwo(args);
                break;
            case 3:
                response = this.executeQueryThree(args);
                break;
            case 4:
                response = this.executeQueryFour(args);
                break;
            case 5:
                response = this.executeQueryFive(args);
                break;
            case 6:
                response = this.executeQuerySix(args);
                break;
            case 7:
                response = this.executeQuerySeven(args);
                break;
            case 8:
                response = this.executeQueryEight(args);
                break;
            case 9:
                response = this.executeQueryNine(args);
                break;
            case 10:
                response = this.executeQueryTen(args);
                break;
            case 11:
                response = this.executeQueryEleven(args);
                break;

        }
            return response;
    }
}
