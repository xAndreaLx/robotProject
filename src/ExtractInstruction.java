import java.util.* ;

public class ExtractInstruction implements Instruction {
    private String instruction = "extract" ;
    private Terrain t ;
    
    public ExtractInstruction(Terrain t) {
        this.t = t ;
    }
    
    public void executer(Memoire m) throws InterruptedException {
        this.t.getRobots().get(0).extract() ;
        t.afficherTerrain() ;
        
        
        m.setNextI(m.getNextI() + 1) ;
    }
    
    public void afficheInstruction() {
        System.out.println(this.instruction);
    }
    
}