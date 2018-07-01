/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.interfaces;

/**
 *
 * @author Nuno
 */
public class QueryView {
   
    
    public QueryView() {
    }
    
    public String getQueryQuestion(int queryNumber, String queryArgs) 
    {
        
        return String.format("Questão %s, argumentos: %s \n"
                + "Os argumentos devem ser colocados pela ordem indicada", 
                queryNumber, queryArgs);
    }    
    
}
