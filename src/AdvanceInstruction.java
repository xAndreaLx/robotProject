import java.util.* ;

public class AdvanceInstruction implements Instruction {
    private String instruction ;
    private String dir ;
    private Terrain t ;
    
    public AdvanceInstruction(String dir, Terrain t) {
        this.dir = dir ;
        this.t = t ;
        this.instruction = "advance " + this.dir ;
    }
    
    public void executer(Memoire m) throws InterruptedException, InstructionError {
        HashMap<String, Integer> variables = m.getVar() ;
        
        int direction = -1 ; 
        
        if  (NumberUtils.isNumeric(dir)) {
            direction = Integer.parseInt(this.dir) ; 
        } else {
            if (variables.containsKey(this.dir)) {
                direction = variables.get(this.dir) ;
            } else {
            	throw new InstructionError("Problème de variable sur l'instruction : " + this.instruction) ;
            }
        }
        
        switch (direction) {
            case 0 :
                this.t.getRobots().get(0).advance(-1, 0) ;
                break ;
            case 1 :
                this.t.getRobots().get(0).advance(0, 1) ;
                break ;
            case 2 :
                this.t.getRobots().get(0).advance(1, 0) ;
                break ;
            case 3 :
                this.t.getRobots().get(0).advance(0, -1) ;
                break ;
            default : 
                System.out.println("Soucis de direction");
                break ;
        }
        t.afficherTerrain() ;
        m.setNextI(m.getNextI() + 1) ;

    }
    
    public void afficheInstruction() {
        System.out.println(this.instruction);
    }
    
}