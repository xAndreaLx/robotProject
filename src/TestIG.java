import java.io.*;
import java.util.ArrayList;
public class TestIG{
  public static void main(String[] args)throws Exception{
    char[][] ter;
    ArrayList<String> terrain;
    terrain = LectureFichier.litFichier("petit_obst.txt");
    ter=new char[terrain.size()][];
    for (int i=0; i<ter.length; i++){
      ter[i]=terrain.get(i).toCharArray();
    }
    Terrain t = new Terrain() ;
    IGRobot igr = new IGRobot();
    igr.associerCharImage('A',"r-robot-ter.png");
    igr.initialiser(t.terrain);
    igr.afficher(); 
    temporise();
    // exemple de modification "normale"
    igr.changerCase(5,2,' ');
    igr.changerCase(4,2,'A');
    igr.afficher();
    temporise();
    // exemples de modification correcte pour l'interface
    // mais ne respectant pas la logique du jeu
    // le robot saute des cases
    igr.changerCase(4,2,' ');
    igr.changerCase(1,1,'A');
    igr.afficher();
    temporise();
    // le minerai disparait
    igr.changerCase(6,1,' ');
    igr.afficher();
    temporise();
    // une montagne devient de l'eau
    igr.changerCase(0,3,'#');
    igr.afficher();
    temporise();
    // un deuxieme robot arrive
    igr.changerCase(2,3,'A');
    igr.afficher();
    temporise();
    igr.terminer();
  }
  static void temporise(){
    Terminal.ecrireStringln("Appuyer sur la touche \"entree\"");
    Terminal.lireString();
  }
}
