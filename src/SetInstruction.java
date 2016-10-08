import java.util.* ;

public class SetInstruction implements Instruction {
    private String var ;
    private String val ;
    private String instruction ;
    
    public SetInstruction(String var, String val) {
        this.var = var ;
        this.val = val ;
        this.instruction = "set " + this.var + " " + this.val ;

    }
    
    public void executer(Memoire m) throws InstructionError {
        
        if (NumberUtils.isNumeric(this.val)) {
            m.setVar(this.var, Integer.parseInt(this.val)) ;
        } else {
            HashMap<String, Integer> variables = m.getVar() ;
            if (variables.containsKey(this.val)) {
            	m.setVar(this.var, variables.get(this.val)) ;
            } else {
            	throw new InstructionError("Problème de variable sur l'instruction : " + this.instruction) ;
            }
        }
        
        m.setNextI(m.getNextI() + 1) ;
    }
    
    public void afficheInstruction() {
        System.out.println(this.instruction);
    }
}