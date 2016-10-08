/*import java.util.* ;

public class AfficheInstruction implements Instruction {


    
    public void executer(Memoire m) {
        m.setNextI(m.getNextI() + 1) ;
        
        HashMap<String, Integer> variables = m.getVar() ;
        
        for (String key : variables.keySet()) {
            System.out.println("Key = " + key);
        }
        for (Integer value : variables.values()) {
            System.out.println("Value = " + value);
        }
        
        HashMap<String, Integer> labels = m.getLab() ;
        
        for (String key : labels.keySet()) {
            System.out.println("Key = " + key);
        }
        for (Integer value : labels.values()) {
            System.out.println("Value = " + value);
        }
    }
}*/