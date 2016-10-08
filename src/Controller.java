import java.io.* ;

public class Controller  {
    
    public static void main(String[] args) throws IOException, InterruptedException {
    	boolean erreurLecture = false ; 
    	boolean erreurExec = false ;
    	boolean interactif ;
    	
        Terrain t = new Terrain() ; 
        t.afficherTerrain() ;
        
        
        Programme p = new Programme(t) ;
        erreurLecture = p.lireProg() ;

        interactif = IOUtils.isInteractif() ;

        erreurExec = p.lancerProg(interactif) ;
        
        IOUtils.testErreurs(erreurLecture, erreurExec) ;
        
        System.exit(0);
        

    }
    

}