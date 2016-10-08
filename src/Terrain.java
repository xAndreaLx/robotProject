import java.util.* ;
import java.io.* ;

public class Terrain {
    
    ArrayList<ArrayList<Case>> terrain = new ArrayList<ArrayList<Case>>() ;
    IGRobot igR = new IGRobot();
    int dimX, dimY ; //dimensions du terrain
    ArrayList<Robot> robotAL = new ArrayList<Robot>() ;
    ArrayList<Integer[]> posM = new ArrayList<Integer[]>() ;
    int nbMin = 0 ; 
    
    public Terrain() throws IOException {
        ArrayList<String> t = IOUtils.lectureNomFichier("du terrain : ") ;
        int ligne = t.size() ;
        int colonne = t.get(0).length() ;
        dimY = colonne ; 
        dimX = ligne ;
        for (int i = 0 ; i < ligne ; i++) {
            ArrayList<Case> row = new ArrayList<Case>() ;
            for (int j = 0 ; j < colonne ; j++) {
                Case c = new Case(t.get(i).charAt(j), i, j, igR) ;
                row.add(c) ;
                if (t.get(i).charAt(j) == 'A') {
                    robotAL.add(new Robot(i, j, this)) ;
                } else if (t.get(i).charAt(j) == '$') {
                    Integer[] m = new Integer[2] ; 
                    m[0] = i ;
                    m[1] = j ;
                    posM.add(m) ;
                    nbMin++ ; 
                }
            }
            this.terrain.add(row) ;
        }
        igR.initialiser(this.terrain);
        igR.afficher();
    }
    
    public void afficherTerrain() {
        for (int i = 0 ; i < terrain.size() ; i++) {
            for (int j = 0 ; j < terrain.get(i).size() ; j++) {
                System.out.print(terrain.get(i).get(j).getRep());
            }
            System.out.println();
        }
        igR.afficher();
    }
    
    private ArrayList<String> lectureTerrain() throws IOException {
        System.out.print("Nom du fichier: ");
        String nm = Terminal.lireString();
        return LectureFichier.litFichier(nm) ;
//        return LectureFichier.litFichier("petit_obst.txt");
    }
    
    public ArrayList<Robot> getRobots() {
        return robotAL ;
    }
    
    public Case getCase(int x, int y) {
        return terrain.get(x).get(y) ;
    }
    
    public boolean dansLimite(int x, int y) {
 //       System.out.println("taille : x " +  this.dimX + " y : " + this.dimY);
        if (x > this.dimX || y > this.dimY || x < 0 || y < 0) {
            return false ;
        }
        return true ;
    }
      
    public void setNbMin(int n) {
        this.nbMin = n ; 
    }
    
    public int getNbMin() {
        return this.nbMin ;
    }
    
    
    /*   
    char[][] terrain ; 
    int[] posR = new int[2]; 
    
    public Terrain(ArrayList<String> t) {
        int ligne = t.size() ;
        int colonne = t.get(0).length() ;
        terrain = new char[t.size()][t.get(0).length()] ;
        for (int i = 0 ; i < ligne ; i++) {
            for (int j = 0 ; j < colonne ; j++) {
                terrain[i][j] = t.get(i).charAt(j) ;
                if (t.get(i).charAt(j) == 'A') {
                    posR[0] = i ;
                    posR[1] = j ;
                }
            }
        }
    }
    
    public void afficherTerrain() {
        for (int i = 0 ; i < terrain.length ; i++) {
            for (int j = 0 ; j < terrain[i].length ; j++) {
                System.out.print(terrain[i][j]);
            }
            System.out.println();
        }
    }
    */
}