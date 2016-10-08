import java.util.* ;

public class RandInstruction implements Instruction {
    private String instruction ;
    private String var ;
    private String val ;
    
    public RandInstruction(String var, String val) {
        this.var = var ;
        this.val = val ;
        this.instruction = "random " + this.var + " " + this.val ;
    }
    
    public void executer(Memoire m) throws InstructionError {
    	HashMap<String, Integer> variables = m.getVar() ;
    	
    	if (NumberUtils.isNumeric(this.val)) {
    		m.setVar(var, (int)(Math.random() * ((Integer.parseInt(this.val) - 1) + 1)) + 1) ; 
    	} else {
    		if (variables.containsKey(this.val)) {
    			m.setVar(var, (int)(Math.random() * ((variables.get(this.val) - 1) + 1)) + 1) ; 
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