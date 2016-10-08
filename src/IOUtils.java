import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.* ;

public class IOUtils {
	
	private static Scanner sc = new Scanner(System.in) ;

	public static ArrayList<String> lectureNomFichier(String s) throws FileNotFoundException, IOException {
		boolean again = true ; 
		String shadow = "" ;
		String nm = "" ;
		
    	while (again) {
    		System.out.print(shadow + "Nom du fichier " + s);
            nm = Terminal.lireString();
        	File f = new File(nm) ; 
    		if (f.exists()) {
    			again = false ;
    		} else {
    			shadow = "== Ce fichier n'existe pas ==\n" ;
    		}
    	}
        return LectureFichier.litFichier(nm) ;
	}
	
    public static boolean isInteractif() {
        boolean again = true ;
        String shadow = "" ;
        
        while (again) {
            System.out.println(shadow + "Mode intéractif ? (tapez O pour Oui et N pour Non)");
            String c = sc.nextLine() ;
            if (c.length() == 1 && c.toLowerCase().charAt(0) == 'o') {
                return true ;
            } else if (c.length() == 1 && c.toLowerCase().charAt(0) == 'n') {
                return false ;
            }
            shadow = "== Saisie incorrect ==\n" ;
        }
        
        return false ;

    }
    
    public static void testErreurs(boolean errLec, boolean errExec) throws FileNotFoundException, IOException {
    	 if (errLec) {
         	System.out.println("*******************************************************************");
         	System.out.println("Erreurs lors de la lecture du fichier programme : ");
         	IOUtils.afficheErreur(new FileReader("erreursLecture.txt")) ;
         	System.out.println("*******************************************************************");
         }
         if (errExec) {
         	System.out.println("*******************************************************************");
         	System.out.println("Erreurs lors de l'execution du fichier programme : ");
         	IOUtils.afficheErreur(new FileReader("erreursExecution.txt")) ;
         	System.out.println("*******************************************************************");
         }
    }
    
    public static void afficheErreur(Reader r) throws IOException {
    	BufferedReader br = new BufferedReader(r) ;
    	String l = br.readLine() ;
    	while (l != null) {
    		System.out.println(l);
    		l = br.readLine() ;
    	}
    }
    
    
	
}
