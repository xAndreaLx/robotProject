public class Case {
    int posX ;
    int posY ;
    Nature nature ;
    char rep ; //représentation pour afficher
    private int qttM = 0 ; //quantité minerai
    private IGRobot igR ;
    
    public Case(char c, int x, int y, IGRobot igR) {
        
        this.posX = x ;
        this.posY = y ;
        this.igR = igR ;
        
        switch (c) {
            case '$' : 
                nature = Nature.MINERAI ;
                rep = '$' ;
                qttM = 3 ;
                break ;
            case '*' : 
                nature = Nature.ROCHE ;
                rep = '*' ;
                break ;
            case 'A' :
                nature = Nature.ROBOT ;
                rep = 'A' ; 
                break ;
            case '#' : 
                nature = Nature.EAU ;
                rep = '#' ;
                break ;
            case ' ' : 
                nature = Nature.VIDE ;
                rep = ' ' ;
                break ;
            default :
                System.out.println("soucis");
                break ;
        }
    }
    
    public char getRep() {
        return rep ;
    }
    
    public void vider() {
        this.nature = Nature.VIDE ;
        this.rep = ' ' ;
        igR.changerCase(this.posX, this.posY, this.rep);
    }
    
    public boolean estVide() {
        if (this.nature == Nature.ROCHE || this.nature == Nature.MINERAI) {
            return false ;
        }
        return true ;
    }
    
    public boolean deplaceRobot() {
        if (this.nature != Nature.EAU) {
            this.nature = Nature.ROBOT ;
            this.rep = 'A' ;
            igR.changerCase(this.posX, this.posY, this.rep);
            return true ; 
        } else {
            System.out.println("PLOUF ! GAME OVER !");
            return false ;
        }
        
    }
    
    public boolean extraireMinerai() throws InterruptedException {
        if (this.nature == Nature.MINERAI) {
            // this.nature = Nature.VIDE ;
            // this.rep = ' ' ;
        	System.out.print("Work in progress");
        	for (int i = 0 ; i < 10 ; i++) {
        		Thread.sleep(1000);
        		System.out.print(".");
        	}
        	System.out.println();
//            Thread.sleep(10000);
            this.vider() ;
            return true ; 
        } else {
            return false ;
        }
    }
    
 /*   public boolean dansLimite() {
        if (posX > t.getDimX() || posY > t.getDimY() || posX < 0 || posY < 0) {
            return false ;
        }
        return true ;
    } */
    
    public int getPosX() {
        return posX ;
    }
    
    public int getPosY() {
        return posY ;
    }
    
    public Nature getNature() {
        return nature ;
    }
    
}