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
public class Constants {
    
    public static Integer[] COMBO_VALUES = new Integer[]{1,2,3,4,5,6,7,8,9,10,11};
    
    public static String[] QUERY_INPUT_SIGNATURE = new String[]
                    {"","Long - Identificador de um post",
                    "int - top N utilizadores","intervalo de tempo - datas",
                    "String - tag; intervalo de tempo - datas",
                    "Long - ID Utilizador", "int - N respostas com mais votos; intervalo de tempo - datas",
                    "int - N perguntas com mais respotas; intervalo de tempo - datas",
                     "int - N perguntas cujo título contém : String - palavra a procurar",
                    "int - N perguntas; Long - utilizador A; Long - Utilizador B",
                    "Long - id de resposta", "int - N; intervalo de tempo - datas"};
    
    /*
    1 -> 1 input
    2 -> 2 input
    3 -> 1 input, 2 dates
    4 -> 2 inputs, 2 dates
    5 -> 3 inputs
    6 -> 3 inputs, 
    7 -> 2 dates
*/
    
    public static Integer[] QUERY_INPUTS_NEEDED = new Integer[] 
                            {0,1,1,7,3,1,3,3,2,5,1,3};
    
     public static final String[] numberNames = {
    "Zero",
    "One",
    "Two",
    "Three",
    "Four",
    "Five",
    "Six",
    "Seven",
    "ight",
    "Nine",
    "Ten",
    "Eleven",
  };
}
