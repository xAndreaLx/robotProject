import java.util.* ;

public class DetectInstruction implements Instruction {
    private Terrain t ;
    private String instruction ;
    private String var ;
    private String dir ;
    private String port ;
    
    public DetectInstruction(String var, String dir, String port, Terrain t) {
        this.var = var ;
        this.dir = dir ;
        this.port = port ;
        this.t = t ;
        this.instruction = "detect " + this.var + " " + this.dir + " " + this.port ;
    }
    
    public void executer(Memoire m) throws InstructionError {
        HashMap<String, Integer> variables = m.getVar() ;
        boolean dirB = false ;
        boolean portB = false ;
        int res = -1 ; 
        
        if (NumberUtils.isNumeric(this.dir)) {
            dirB = true ; 
        }
        if (NumberUtils.isNumeric(this.port)) {
            portB = true ; 
        }
        
        if ((dirB) && (portB)) {
            res = this.t.getRobots().get(0).detect(Integer.parseInt(this.dir), Integer.parseInt(this.port)) ;
        } else if ((!dirB) && (portB)) {
            if (variables.containsKey(this.dir)) {
                res = this.t.getRobots().get(0).detect(variables.get(this.dir), Integer.parseInt(this.port)) ;
            } else {
            	throw new InstructionError("Problème de variable de direction sur l'instruction : " + this.instruction) ;
            }
        } else if ((dirB) && (!portB)) {
            if (variables.containsKey(this.port)) {
                res = this.t.getRobots().get(0).detect(Integer.parseInt(this.dir), variables.get(this.port)) ;
            } else {
            	throw new InstructionError("Problème de variable de portée sur l'instruction : " + this.instruction) ;
            }
        } else {
            if (variables.containsKey(this.dir) && variables.containsKey(this.port)) {
                res = this.t.getRobots().get(0).detect(variables.get(this.dir), variables.get(this.port)) ;
            } else {
            	throw new InstructionError("Problème de variable(s) sur l'instruction : " + this.instruction) ;
            }
        }
        
        variables.put(this.var, res) ;
        t.afficherTerrain() ;
        
        m.setNextI(m.getNextI() + 1) ;
    }
    
    public void afficheInstruction() {
        System.out.println(this.instruction);
    }
}