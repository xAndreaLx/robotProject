import java.util.* ;

public class GotoInstruction implements Instruction {
    private String instruction  ;
    private String lab ;

    public GotoInstruction(String lab) {
        this.lab = lab ;
        this.instruction = "goto " + this.lab ;

    }
    
    public void executer(Memoire m) throws InstructionError {
        HashMap<String, Integer> labels = m.getLab() ;
        if (labels.containsKey(this.lab)) {
            int ni = labels.get(lab) ; // on récupére la ligne du label (ou de l'instruction qui suit) 
            m.setNextI(ni) ;
        } else {
        	throw new InstructionError("Problème de label sur l'instruction : " + this.instruction) ;
        }
         
    }
    
    public void afficheInstruction() {
        System.out.println(this.instruction);
    }
}