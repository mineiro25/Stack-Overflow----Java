package engine;
import common.MyLog;
import common.Pair;
import li3.TADCommunity;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDate;
import static java.lang.Math.toIntExact; // converter long para int
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TCDExample implements TADCommunity {

    private MyLog qelog;
    HashMap<Long,Posts> hMapPosts;
    HashMap<Long,Usuarios> hMapUsers;
    HashMap<Long,Comentarios> hMapComments;
    HashMap<Long,Tags> hMapTags;
    
    
    /** Inicia a estrutura */
    public void init() {
        hMapPosts = new HashMap<Long,Posts>();
        hMapUsers = new HashMap<Long,Usuarios>();
        hMapComments = new HashMap<Long,Comentarios>();
        hMapTags = new HashMap<Long,Tags>();
    }
    
/**Carrega a estrutura */
public void load(String dumpPath) {
        Parser teste = new Parser();
        try {
            hMapPosts = teste.postsParser(dumpPath);
            hMapUsers = teste.usersParser(dumpPath);
            hMapComments = teste.commentsParser(dumpPath);
            hMapTags = teste.tagsParser(dumpPath);
            
        } catch (ParserConfigurationException | SAXException ex) {
            Logger.getLogger(TCDExample.class.getName()).log(Level.SEVERE, null, ex);       
        }
}

    /** Query 1 - Dado o identificador de um post, devolver o título de post e o nome
     * @param id.
     * @return */
    @Override
public Pair<String,String> infoFromPost(long id) {

        String nomeUser;
        String tituloPost;
        long postTypeID = hMapPosts.get(id).getTipo(); // vai buscar o postTypeID do id que foi dado
        long idUser; // vai buscar o id do dono do post
        long parentID; 
    
        if (postTypeID == 2)  {
            parentID = hMapPosts.get(id).getParentID();
            tituloPost = hMapPosts.get(parentID).getTitle();
            idUser = hMapPosts.get(parentID).getUserID();
            nomeUser = hMapUsers.get(idUser).getNome();
        }
        else {  
            idUser = hMapPosts.get(id).getUserID();
            tituloPost = hMapPosts.get(id).getTitle();
            nomeUser = hMapUsers.get(idUser).getNome();
        }
        

        return new Pair<>(tituloPost,nomeUser);
    }

    /** Função auxiliar para descobrir o ultimo ID, assim o array vai ter o tamanho do ultimo usuario
     * @param users.
     * @return int*/
    public int ultimoID (HashMap<Long,Usuarios> users) {
        long ultimo = 0;
        int conta = 0; String bio = null;
        for (Map.Entry<Long,Usuarios> entry : hMapUsers.entrySet() ) {
            if (entry.getValue().getID() > ultimo) 
                ultimo = entry.getValue().getID(); 
        }
 
        return toIntExact (ultimo);
    }
    /** Query 2 - Obter o top N utilizadores com maior nº de posts de semp
     * @param N
     * @return r*/
    @Override
    public List<Long> topMostActive(int N) {
        
        int numUsers = ultimoID(hMapUsers); //nº total de users

        long[] nUser = new long[N]; // criado um array do tamanho do nº de retornos
        int[] nUser_NPosts = new int[N];
        int[] nUser_conta = new int[numUsers]; //criado um array do tamanho do nº de users
        int cont = 0;
        
        for(int i = 0; i<numUsers;i++){
           nUser_conta[i]=0; 
        }
        
        for(int j = 0; j<N;j++){
            nUser[j]=0;
            nUser_NPosts[j]=0;
        }
        
        for(Map.Entry<Long,Posts> entry : hMapPosts.entrySet()) {
            if (entry.getValue().getUserID() >= 0 && entry.getValue()!=null && (entry.getValue().getTipo()==1 || entry.getValue().getTipo()==2)) {
                int teste = toIntExact (entry.getValue().getUserID());
                nUser_conta [  teste  ]++;
            }
        }
        int up,upAnt;
        long u, uAnt;
        for (int i=0;i<numUsers;i++){
            u=i;
            up=nUser_conta[i];
            for(int j=0 ;j< N;j++){
                if (up>=nUser_NPosts[j]){
                    uAnt=nUser[j];
                    upAnt=nUser_NPosts[j];
                    nUser[j]=(int) u;
                    nUser_NPosts[j]=up;
                    u=uAnt;
                    up=upAnt;
                }
            }
        }
        List <Long> novo = new ArrayList <Long> ();
         
        for (long n : nUser) novo.add(n);
        
        return novo; // alterei aqui antes tava Arrays.asList(nUser), mas estava a dar erro
      
    }

    /** Query 3 - Dado um intervalo de tempo, obter o número total de posts, identificando as perguntas e respostas
     * @param begin
     * @param end
     * @return Pair  */
    @Override
    public Pair<Long,Long> totalPosts(LocalDate begin, LocalDate end) {
        long perguntas = 0;
        long respostas = 0;
        int i = 0;
        
        for (Map.Entry<Long,Posts> entry : hMapPosts.entrySet()) {
            if ( i!=0) {
                LocalDate data = LocalDate.parse(entry.getValue().getData());
          
                if ( data.isAfter(begin) && data.isBefore(end) || data.isEqual(begin) || data.isEqual(end) ) {
                    
                    if (entry.getValue().getTipo()==1) 
                        perguntas++;
                    
                    if (entry.getValue().getTipo()==2) 
                        respostas++;
                }   
            }
            i++;
        }
        
        
        return new Pair<>(perguntas,respostas);
    }

    /** Query 4 -Dado um intervalo de tempo, obter todas as perguntas com a tag usad
     * @param tag
     * @param begin
     * @param end
     * @param taga
     * @return List */
    @Override
    public List<Long> questionsWithTag(String tag, LocalDate begin, LocalDate end) {
        int i = 0; int x = 0;
        List<Long> ids = new ArrayList<>();
        
        for(Map.Entry<Long,Posts> entry : hMapPosts.entrySet()){
            if(i!=0){
                LocalDate data = LocalDate.parse(entry.getValue().getData());
                
                if(data.isAfter(begin) && data.isBefore(end) || ( data.isEqual(begin) || data.isEqual(end))){
                    if(entry.getValue().getTag().contains(tag)){
                       ids.add(x, entry.getKey());
                       x++;
                    }
                }
            }
            i++;
        }

        Collections.reverse(ids); //faz um reverse a arraylist pois ela ta ordenada dos ids mais antigos para os mais recentes
        
        List listaIDS = Arrays.asList(ids); //ta a converter para uma lista

        return listaIDS;
    }

    /** Query 5 - Dado um id de utilizador, devolve a informação do seu perfil e os ultimos dez posts.
     * @param id.
     * @return Pair */
    @Override
    public Pair<String, List<Long>> getUserInfo(long id) {
        String shortBio = hMapUsers.get(id).getBio();//obter a bio do user
        List<Long> postList = new ArrayList<>(); //arraylist que vai conter os ids dos posts
        
        for(Map.Entry<Long,Posts> entry : hMapPosts.entrySet()){
            if(entry.getValue().getUserID() == id && (entry.getValue().getTipo() == 1 ||entry.getValue().getTipo() == 2)){
                postList.add(entry.getValue().getID()); //adiciona todos os ids dos post desse userID que foi dado
            }
        }
        
        Collections.reverse(postList); //reverter a arraylist ou seja os posts mais recentes aparecem nos primeiros indices
        
        List<Long> subList; //cria uma lista apartir de uma sublista de postList
        if(postList.size() > 10){
            subList = postList.subList(0, 10);
        }else{
            subList = postList.subList(0, postList.size());
        }
     
        return new Pair<>(shortBio,subList);
    }

    /** Query 6 -Dado um intervalo de tempo, devolver as N respostas mais votadas por ordem crescent
     * @param N
     * @param begin
     * @param Ne
     * @param end
     * @return List */
    @Override
    public List<Long> mostVotedAnswers(int N, LocalDate begin, LocalDate end) {
       
        long[] topscore = new long[N];
        long[] ids = new long[N];
        
        for (int i =0; i <N; i++) {
            topscore[i]=0;
            ids[i]=0;
        }
        int j =0;
        for(Map.Entry<Long,Posts> entry : hMapPosts.entrySet()){
            if (j!=0) {
                if (entry.getValue().getTipo()==2) {
                    LocalDate data = LocalDate.parse(entry.getValue().getData());
                    if (data.isAfter(begin) && data.isBefore(end) || (data.isEqual(begin) || data.isEqual(end))) {
                        long id = entry.getValue().getID();
                        long score = entry.getValue().getScore();
                        for (int i = 0; i<N ; i++) {
                            if (score >= topscore[i] ) {
                                long auxID = ids[i];
                                long auxScore = topscore[i];
                                ids[i] = id;
                                topscore[i] = score;
                                score = auxScore;
                                id = auxID;
                            }
                        }
                    } 
                }
           }
           j++;
        }
        
        List <Long> novo = new ArrayList <Long> (); 
        for (long n : ids) novo.add(n);
        
        
        return novo;
    }

    /** Query 6 -Dado um intervalo de tempo, devolver as N perguntas mais respondidas por ordem crescent
     * @param N
     * @param begin
     * @param end
     * @param Ne
     * @return List */
    @Override
    public List<Long> mostAnsweredQuestions(int N, LocalDate begin, LocalDate end) {
       
        long[] respostas = new long[N];
        long[] ids = new long[N];
        
        for (int i =0; i <N; i++) {
            respostas[i]=0;
            ids[i]=0;
        }
        int j =0;
        
        for(Map.Entry<Long,Posts> entry : hMapPosts.entrySet()){
            
            if (j!=0) {
                LocalDate data = LocalDate.parse(entry.getValue().getData());
                
                if (data.isAfter(begin) && data.isBefore(end) || (data.isEqual(begin) || data.isEqual(end)) && entry.getValue().getTipo()==1 ) {
                    
                    long id = entry.getValue().getID();
                    long resposta = entry.getValue().getNumAnswers();
                    
                    for (int i = 0; i<N ; i++) {
                    
                        if (resposta >= respostas[i] ) { 
                            long auxID = ids[i]; //
                            long auxScore = respostas[i];
                            ids[i] = id;
                            respostas[i] = resposta;
                            resposta = auxScore;
                            id = auxID;
                        }
                    }
                } 
           }
           j++;
        }
        
        List <Long> novo = new ArrayList <Long> (); 
        for (long n : ids) novo.add(n);
        
        
        return novo; //alterei aqui para nao dar erro! antes tava Arrays.asList()
    
    }

    /** Query 8 -Dado uma palavra, devolver os IDs das N perguntas cujo títulos a contenha
     * @param N
     * @param word
     * @param Nm
     * @return List */
    @Override
    public List<Long> containsWord(int N, String word) {
        
        List<Long> ids = new ArrayList<>();
        
        for(Map.Entry<Long,Posts> entry : hMapPosts.entrySet()){
            if(entry.getValue().getTipo()==1){
                if(!"0".equals(entry.getValue().getTitle()) && entry.getValue().getTitle().contains(word)){ //ira verificar se existe a palavra no titulo do post
                    ids.add(entry.getValue().getID()); //vai adicionar a arraylist os ids das pergutnas que contenham a palavra dada
                }
            }
        }
        
        Collections.reverse(ids); //da reverse a arraylist para estar por ordem cronologica inversa, ou seja do mais recente para o mais antigo
        
        List<Long> subList; //cria uma lista apartir de uma sublista de ids
        if(ids.size() >= N){ //se o tamanho da arraylist for maior ou igual a N, entao ids vai ser "apartido" e ira guardar os ids do indice 0 a 10
            subList = ids.subList(0, N); //, senao vai ser guardado os ids dos indices 0 ate ao tamanho do arraylist
        }else{
            subList = ids.subList(0, ids.size());
        }
        
        
        
        return subList;
    }

    /** Query 9 -Dado os IDs de dois utilizadores, devolver as ultimas N perguntas em que ambos participaram via pergunta ou resposta
     * @param N
     * @param id1
     * @param id2
     * @return List  */
    @Override
    public List<Long> bothParticipated(int N, long id1, long id2) {
        List<Long> idsP = new ArrayList<>(); //arraylist dos ids
        int i=0;
        // ler posts do id1
        for(Map.Entry<Long,Posts> entry : hMapPosts.entrySet()){
            if(entry.getValue().getUserID()==id1){
                if(entry.getValue().getTipo()==2){
                    idsP.add(entry.getValue().getParentID());
                }else{
                    idsP.add(entry.getValue().getID());
                }
                i++;
            }
        }
        
        long post = 0;
        int achou = 0;
        int k =0;
        long resultado[] = new long[N];
        // ler posts do id2
        for(Map.Entry<Long,Posts> entry : hMapPosts.entrySet()){
            if(entry.getValue().getUserID()==id2){
                if(entry.getValue().getTipo()==2){
                    post = entry.getValue().getParentID();
                } else{
                    post = entry.getValue().getID();
                }
            }
            // existe em id1?
            for(int j = 0; j<i; j++){
                if(idsP.get(j)==post){
                    achou=0;
                    for(j=0;j<i;j++){
                        if(idsP.get(j)==post){
                            achou=0;
                            for(i=0;i<k;i++){
                                if(resultado[i]==post){
                                    achou=1;
                                    break;
                                }
                            }
                            
                            if(achou==0){
                                resultado[k]=idsP.get(j);
                                k++;
                                break;
                            }
                        }
                    }
                }
            }
        }
        Long [] array = new Long[k];
        resultado[k]='\0';
        for (int x = 0 ; x<k; x++) array[x] = resultado[x];
        List <Long> novo = new ArrayList <Long> (); 
        for (long n : array) novo.add(n);
        
        
        return novo ;
    }

    
    
    
    /** Query 10 Dado o ID de uma pergunta, obter a melhor resposta usando a formula fornecida pela equipa docent
     * @param id
     * @param ide
     * @return long */
    @Override
    public long betterAnswer(long id) {
        float maior =0;
        long idBestAnswer = -1;
        
        for(Map.Entry<Long,Posts> entry : hMapPosts.entrySet()){   
                if(entry.getValue().getParentID() == id && entry.getValue().getTipo()==2){
                    int reputacao = (int) hMapUsers.get(entry.getValue().getUserID()).getReputacao();
                    float resultado = (float) ((entry.getValue().getScore() * 0.45) + (reputacao *0.25) + (entry.getValue().getScore() * 0.2) + (entry.getValue().getNumAnswers()*0.1));
                    long idPost = entry.getValue().getID();
                    
                    if(resultado > maior){
                        maior = resultado;
                        idBestAnswer = idPost;
                    }
                }
            
        }
        
        
        return idBestAnswer;
    }

    /** Query 11 -Dado um intervalo de tempo, devolver as N tags mais usadas pelos N utilizadores com maior reputação de sempr
     * @param N
     * @param begin
     * @param end
     * @param Ne
     * @return List */
    @Override
    public List<Long> mostUsedBestRep(int N, LocalDate begin, LocalDate end) {
        long[] reputacaoUser = new long[N];
        long[] idUser = new long[N]; 
       
        
        for(int i=0; i<N; i++){
            reputacaoUser[i]=0;
            idUser[i]=0;
        }
        
        int j=0;
        
        //obter um array dos N users com melhor reputacao
        for(Map.Entry<Long,Usuarios> entry : hMapUsers.entrySet()){
            if(j!=0){
                long id = entry.getValue().getID();
                long reputacao = entry.getValue().getReputacao();
                
                for(int i =0; i<N; i++){
                    if(reputacao > reputacaoUser[i]){
                        long auxID = idUser[i];
                        long auxReputacao = reputacaoUser[i];
                        idUser[i]=id;
                        reputacaoUser[i]=reputacao;
                        reputacao=auxReputacao;
                        id=auxID;
                    }
                }
            }
            j++;
        }
        
         
        j = 0;
        
        
        List <String[]> arrayTags = new ArrayList <String[]> ();
        
        for (Map.Entry<Long,Posts> entry : hMapPosts.entrySet()) {
            if (j!=0) {
                LocalDate data = LocalDate.parse(entry.getValue().getData());
                if(entry.getValue().getTipo()==1 &&  ( ( data.isAfter(begin) && data.isBefore(end) ) ||  ( data.isEqual(begin) || data.isEqual(end) )    ) ){
                    int chave = 0;
                    for (int x =0 ; x<N && chave==0; x++) {
                        if (entry.getValue().getUserID() == idUser[x]) {
                            chave=1;
                            //if ( entry.get1Value().getTag().contains("<apt>")) System.out.println ( "post = " + entry.getValue().getID() );
                        }
                    }
                    
                    if (chave==1) {
                        String[] tagencontrado = entry.getValue().getTag().split(">");
                        arrayTags.add(tagencontrado);
                    }
                
                }
            }
            j++;
        }
        

        
        long codTag;
      
        Map <String, Long> contaTags = new HashMap<String, Long>() ;
        // array de tags
        for (String[] entry : arrayTags) {
        
                for (int i = 0; i<entry.length; i++) {
                    int achou = 0;
                    String tag = entry[i].substring (1, entry[i].length() );
                    // mapa contador por Tag
                    for (Map.Entry <String,Long> entryTag : contaTags.entrySet()) {
                        
                        if (tag.equals(entryTag.getKey())) {
                            contaTags.put(entryTag.getKey(), entryTag.getValue()+1); 
                            achou =1;
                            
                        }    
                    }
                    if (achou==0) {
                        contaTags.put(tag, (long) 1);
                    }
                    
                }
        
        } 

     // obtem codigo tag
        long topTags [] = new long[N];
        long topTNum []=new long[N];
        
        
        
        for (Map.Entry <String,Long> TagPosts : contaTags.entrySet()) { 
  //           System.out.println ("Tag = " + TagPosts.getKey() + " conta= " + TagPosts.getValue());
                
            for (Map.Entry<Long,Tags> entryTags : hMapTags.entrySet()) {
                if (entryTags.getValue().getTag().equals(TagPosts.getKey())) {
                    codTag=entryTags.getKey();
    //                System.out.println ("Codigo da TAG = " + codTag);
                    
                    for (j = 0; j<N;j++){
//                        System.out.println ("Conta do TAG = " + TagPosts.getValue() + " conta do TOP = " + topTNum[j]);
                        if (topTNum[j]==0){
                            topTags[j]=codTag;
                            topTNum[j]=TagPosts.getValue();
                            break;
                        }
                        // se contagem igual e código menor -> também coloca
                        if (TagPosts.getValue()>topTNum[j] || (TagPosts.getValue()==topTNum[j] && codTag<topTags[j])){
                            // desce uma posição , depois da posição j 
                            for (int k=N-1;k>j;k--){
                                topTNum[k]=topTNum[k-1];
                                topTags[k]=topTags[k-1];
                            }
                            topTags[j]=codTag;
                            topTNum[j]=TagPosts.getValue();
                            break;
                        }
                    } 
                } 
            }   
        }
        List <Long> novo = new ArrayList <Long> (); 
        for (long n : topTags) novo.add(n);
        
       
       return novo;
       
    }

    @Override
    /** Limpa as estruturas */
    public void clear(){ //limpar as estruturas
        hMapPosts.clear();
        hMapUsers.clear();
        hMapComments.clear();
        hMapTags.clear();
    }
}
