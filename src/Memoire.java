import java.util.* ;

public class Memoire {
    private HashMap<String, Integer> variables = new HashMap<String, Integer>() ;
    private HashMap<String, Integer> labels = new HashMap<String, Integer>() ;
    private int nextInstruction ; 
    

    public void setVar(String nom, int val) {
        variables.put(nom, val) ; 
    }
    
    public HashMap<String, Integer> getVar() {
        return variables ;
    }
    
    public void setLab(String nom, int ind) {
        labels.put(nom, ind) ;
    }
    
    public HashMap<String, Integer> getLab() {
        return labels ;
    }
    
    public void setNextI(int n) {
        this.nextInstruction = n ;
    }
    
    public int getNextI() {
        return this.nextInstruction ;
    }
    
    public void afficheMemoire() {
        System.out.println("-----------------------");
        System.out.println("Mémoire de variables : ");
        for (Map.Entry<String, Integer> entry : variables.entrySet()) {
            System.out.println(entry.getKey()+ " : " +entry.getValue());
        }
        System.out.println("-----------------------");
    }
}