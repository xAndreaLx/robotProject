import java.util.* ;

public class Robot {
    int posX ;
    int posY ;
    Terrain t ;
    
    public Robot(int x, int y, Terrain t) {
        this.posX = x ;
        this.posY = y ;
        this.t = t ;
    }
    
    public void advance(int x, int y) throws InterruptedException {
//        System.out.println("hello je suis en x : " + posX + " y : " + posY);
        int nPosX = posX + x ;
        int nPosY = posY + y ;
//        System.out.println("et je vais en x : " + nPosX + " y : " + nPosY);
        boolean alive = true ;
        
        if (t.dansLimite(nPosX, nPosY) && t.getCase(nPosX, nPosY).estVide()) {
            Thread.sleep(1000);
            t.getCase(posX, posY).vider() ;
            alive = t.getCase(nPosX, nPosY).deplaceRobot() ;
            if (!alive) {
                t.afficherTerrain() ;
                System.exit(0);
            }
            this.posX = nPosX ;
            this.posY = nPosY ;
//            System.out.println("Me voilà en x : " + this.posX + " y : " + this.posY);
        } else {
            System.out.println("Impossible d'aller par là!");
        }
        
    }
    
    public void extract() throws InterruptedException {
        ArrayList<Case> casesAdj = new ArrayList<Case>() ;
        casesAdj.add(t.getCase(this.posX - 1, this.posY)) ;
        casesAdj.add(t.getCase(this.posX, this.posY + 1)) ;
        casesAdj.add(t.getCase(this.posX + 1, this.posY)) ;
        casesAdj.add(t.getCase(this.posX, this.posY - 1)) ;
        
        for (Case c : casesAdj) {
            if (t.dansLimite(c.getPosX(), c.getPosY())) {
                if (c.extraireMinerai()) {
                    t.setNbMin(t.getNbMin() - 1) ;
                    if (t.getNbMin() == 0) {
                        t.afficherTerrain() ;
                        System.out.println("Plus de minerai ! Je suis blindé ! ");
                        System.exit(0);
                    }
                }
            }
        }
    }
    
    public int detect(int dir, int port) throws InstructionError {
        int res = -1 ;
        switch (dir) {
            case 0 :
                res = this.detectRec(-1, 0, 0, 0, port) ;
                break ;
            case 1 : 
                res = this.detectRec(0, 1, 0, 0, port) ;
                break ;
            case 2 : 
                res = this.detectRec(1, 0, 0, 0, port) ;
                break ;
            case 3 : 
                res = this.detectRec(0, -1, 0, 0, port) ;
                break ; 
            default :
            	throw new InstructionError("Problème de direction de detect") ;
//                System.out.println("Direction de detect() non valide");
//                break ;
        }
        
//        System.out.println("detect : " + res) ;
        return res ;
        
    }
    
    public int detectRec(int x, int y, int sx, int sy, int port) {
        sx += x ;
        sy += y ;
        if (!t.dansLimite(posX + sx, posY + sy) || port <= 0) {
            return 0 ;
        }
    
//        System.out.println("regarde la case x : " + (posX+sx) + " y : " + (posY+sy));
    
        if (t.getCase(posX + sx, posY + sy).getNature() == Nature.MINERAI) {
            return 1 ;
        } else if (t.getCase(posX + sx, posY + sy).getNature() == Nature.ROCHE) {
            return 2 ;
        } else if (t.getCase(posX + sx, posY + sy).getNature() == Nature.EAU) {
            return 3 ;
        }
        return detectRec(x, y, sx, sy, port-1) ;
    }
}