import java.util.* ;

public class JumpNEqualInstruction implements Instruction {
    private String instruction ;
    private String exp1 ;
    private String exp2 ;
    private String lab ; 

    public JumpNEqualInstruction(String exp1, String exp2, String lab) {
        this.exp1 = exp1 ;
        this.exp2 = exp2 ;
        this.lab = lab ;
        this.instruction  = "jumpNEqual " + this.exp1 + " " + this.exp2 + " " + this.lab ; 
    }
    
    public void executer(Memoire m) throws InstructionError {
        HashMap<String, Integer> labels = m.getLab() ;
        HashMap<String, Integer> variables = m.getVar() ;
        boolean exp1B = false ;
        boolean exp2B = false ;
        boolean res = false ;
        
        if (NumberUtils.isNumeric(this.exp1)) {
            exp1B = true ;
        } 
        if (NumberUtils.isNumeric(this.exp2)) {
            exp2B = true ; 
        }
        
        if ((exp1B) && (exp2B)) {
            if (Integer.parseInt(this.exp1) == Integer.parseInt(this.exp2)) {
                res = true ;
            }
        } else if ((exp1B) && (!exp2B)) {
            if (variables.containsKey(this.exp2)) {
                if(Integer.parseInt(this.exp1) == variables.get(this.exp2)) {
                    res = true ;
                }
            } else {
            	throw new InstructionError("Problème de variable(s) sur l'instruction : " + this.instruction) ;
            }
        } else if ((!exp1B) && (exp2B)) {
            if (variables.containsKey(this.exp1)) {
                if(Integer.parseInt(this.exp2) == variables.get(this.exp1)) {
                    res = true ;
                }
            } else {
            	throw new InstructionError("Problème de variable(s) sur l'instruction : " + this.instruction) ;
            }
        } else {
            if (variables.containsKey(this.exp1) && variables.containsKey(this.exp2)) {
                if (variables.get(this.exp1) == variables.get(this.exp2)) {
                    res = true ;
                }
            } else {
            	throw new InstructionError("Problème de variable(s) sur l'instruction : " + this.instruction) ;
            }
        }
        
        if (!res) {
            if (labels.containsKey(this.lab)) {
                int ni = labels.get(lab) ;
                m.setNextI(ni) ;
            } else {
            	throw new InstructionError("Problème de label sur l'instruction : " + this.instruction) ;
            }
        } else {
            m.setNextI(m.getNextI() + 1) ;
        }
    }
    
    public void afficheInstruction() {
        System.out.println(this.instruction);
    }
}