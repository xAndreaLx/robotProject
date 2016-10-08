import java.util.* ;

public class MultInstruction implements Instruction {
    private String instruction ;
    private String var ;
    private String val ;
    
    public MultInstruction(String var, String val) {
        this.var = var ;
        this.val = val ;
        this.instruction = "mult " + this.var + " " + this.val ;

    }
    
    public void executer(Memoire m) throws InstructionError {
        HashMap<String, Integer> variables = m.getVar() ;

        
        if (!NumberUtils.isNumeric(val)) {
        	if (variables.containsKey(this.var) && variables.containsKey(this.val)) {
        		m.setVar(this.var, variables.get(this.var) * variables.get(val)) ;   
        	} else {
        		throw new InstructionError("Problème de variable(s) sur l'instruction : " + this.instruction) ;
        	}
        } else {
        	if (variables.containsKey(this.var)) {
        		m.setVar(this.var, variables.get(this.var) * Integer.parseInt(this.val)) ; 
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