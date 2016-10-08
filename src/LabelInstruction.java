import java.util.* ;

public class LabelInstruction implements Instruction {
    private String instruction ;
    private String var ;
    private int ind ;
    
    public LabelInstruction(String var, int ind, Memoire m) {
        this.var = var ;
        this.ind = ind ;
        this.instruction = "label " + this.var ;
        m.setLab(this.var, this.ind) ;
    }
    
    public void executer(Memoire m) {
        m.setNextI(m.getNextI() + 1) ;
    }
    
    public void afficheInstruction() {
        System.out.println(this.instruction);
    }
}