import java.io.*;
import java.util.ArrayList;
public class TestLectureFichier{
  public static void main(String[] args){
    String nm;
    ArrayList<String> cont;
    System.out.print("Nom du fichier: ");
    nm = Terminal.lireString();
    try{
      cont = LectureFichier.litFichier(nm);
      System.out.println("Nombre de lignes: " + cont.size());
      for (int i=0; i<cont.size(); i++){
        System.out.println("Ligne numéro " + i + ": ");
        System.out.println(cont.get(i));
        System.out.println("------------------------");   
        
        
      }
      
   //  for (int i = 0 ; i < cont.size() ; i++) {
      	/*	ArrayList<String> test = LexAndParse.lexer(cont.get(1)) ; 
      		for (int i = 0 ; i < test.size() ; i++) {
      			System.out.println(test.get(i));
      		} */
      		//   }
     
     char[] t = new char[cont.get(0).length()] ;
     for (int i = 0 ; i < cont.get(1).length() ; i++) {
       t[i] = cont.get(1).charAt(i) ;
     }
//      char[] t = cont.get(0).split("");
      System.out.println("tindin :");
      for (int i = 0 ; i < t.length ; i++) {
    	  System.out.println(t[i]);
       }
      		
    }catch(FileNotFoundException e){
      System.out.println("Le fichier n'existe pas");
    }catch(IOException e){
      System.out.println("IOException");
    }
    
    
    
  }
}
