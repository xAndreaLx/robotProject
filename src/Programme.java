import java.util.* ;
import java.io.* ;

public class Programme {
    private ArrayList<Instruction> programme = new ArrayList<Instruction>() ;
//    private ArrayList<String> all = new ArrayList<String>() ;
    private Memoire m = new Memoire() ;
    private Terrain t ;

    private Scanner sc = new Scanner(System.in) ;

    public Programme(Terrain t) {
        this.t = t ; 
    }
    
    public boolean lireProg() throws IOException {
    	boolean error = false ;
    	FileWriter w = new FileWriter("erreursLecture.txt");
//        String nm = "testProg2.txt" ;
//        ArrayList<String> cont = LectureFichier.litFichier(nm) ;
    	ArrayList<String> cont = IOUtils.lectureNomFichier("du programme : ") ;

//        for (int i = 0 ; i < cont.size() ; i++) {
//      		all.addAll(LexAndParse.lexer(cont.get(i))) ; 
//        }
        
        for (int i = 0 ; i < cont.size() ; i++) {
        	if (cont.get(i).length() == 0) {
        		cont.remove(i) ;
        	}
            System.out.println(cont.get(i)); 
        }
        System.out.println();
        
        for (int i = 0 ; i < cont.size(); i++) {
        	
        	try {
		        switch (LexAndParse.lexer(cont.get(i)).get(0).toLowerCase()) {
			        case "set" :
			            lireSet(cont.get(i)) ;
			            break ;
			        case "add" :
			            lireAdd(cont.get(i)) ;
			            break ;
			        case "sub" : 
			            lireSub(cont.get(i)) ;
			            break ;
			        case "mult" :
			            lireMult(cont.get(i)) ;
			            break ;
			        case "div" : 
			            lireDiv(cont.get(i)) ;
			            break ;
			        case "random" : 
			            lireRandom(cont.get(i)) ;
			            break ;
			        case "label" : 
			            lireLabel(cont.get(i)) ;
			            break ;
			        case "goto" :
			            lireGoto(cont.get(i)) ;
			            break ;
			        case "jumpequal" : 
			            lireJumpEqual(cont.get(i)) ;
			            break ;
			        case "jumpnequal" : 
			            lireJumpNEqual(cont.get(i)) ;
			            break ;
			        case "jumpless" : 
			            lireJumpLess(cont.get(i)) ;
			            break ;
			        case "jumplessequal" : 
			            lireJumpLessEqual(cont.get(i)) ;
			            break ;
			        case "advance" :
			            lireAdvance(cont.get(i)) ;
			            break ;
			        case "extract" :
			            lireExtract(cont.get(i)) ;
			            break ;
			        case "detect" : 
			            lireDetect(cont.get(i)) ;
			            break ;
			        default :
			        	w.write("InstructionError: " + LexAndParse.lexer(cont.get(i)).get(0) + " ne fait pas parti des instructions prises en charge par l'interpréteur\n");
			            break;
		        }
        	} catch (InstructionError e) {
        		w.write(e.toString() + "\n");
        		error = true ;
        	}
        }
        
        w.close();
        return error ;
        
    }
    
    public void lireSet(String ligne) throws InstructionError {
    	ArrayList<String> mot = LexAndParse.lexer(ligne) ;
    	if (mot.size() != 3) {
    		throw new InstructionError("Nombre d'argument pour la commande SET invalide") ;
    	}
        String var = mot.get(1) ;
        String val = mot.get(2); 
        SetInstruction set = new SetInstruction(var, val) ;
        programme.add(set) ;
    }
    
    public void lireAdd(String ligne) throws InstructionError {
    	ArrayList<String> mot = LexAndParse.lexer(ligne) ;
    	if (mot.size() != 3) {
    		throw new InstructionError("Nombre d'argument pour la commande ADD invalide") ;
    	}
        String var = mot.get(1) ; 
        String val = mot.get(2) ;
        AddInstruction add = new AddInstruction(var, val) ;
        programme.add(add) ; 
    }
    
    public void lireMult(String ligne) throws InstructionError {
    	ArrayList<String> mot = LexAndParse.lexer(ligne) ;
    	if (mot.size() != 3) {
    		throw new InstructionError("Nombre d'argument pour la commande MULT invalide") ;
    	}
        String var = mot.get(1) ; 
        String val = mot.get(2) ;
        MultInstruction mult = new MultInstruction(var, val) ;
        programme.add(mult) ;
    }
    
    public void lireSub(String ligne) throws InstructionError {
    	ArrayList<String> mot = LexAndParse.lexer(ligne) ;
    	if (mot.size() != 3) {
    		throw new InstructionError("Nombre d'argument pour la commande SUB invalide") ;
    	}
        String var = mot.get(1) ; 
        String val = mot.get(2) ;
        SubInstruction sub = new SubInstruction(var, val) ;
        programme.add(sub) ; 
    }
    
    public void lireDiv(String ligne) throws InstructionError {
    	ArrayList<String> mot = LexAndParse.lexer(ligne) ;
    	if (mot.size() != 3) {
    		throw new InstructionError("Nombre d'argument pour la commande DIV invalide") ;
    	}
        String var = mot.get(1) ; 
        String val = mot.get(2) ;
        DivInstruction div = new DivInstruction(var, val) ;
        programme.add(div) ; 
    }
    
    public void lireRandom(String ligne) throws InstructionError {
    	ArrayList<String> mot = LexAndParse.lexer(ligne) ;
    	if (mot.size() != 3) {
    		throw new InstructionError("Nombre d'argument pour la commande RANDOM invalide") ;
    	}
        String var = mot.get(1) ;
        String val = mot.get(2) ;
//        int val = Integer.parseInt(all.get(++i)) ;
        RandInstruction rand = new RandInstruction(var, val) ; 
        programme.add(rand) ;
    }
    
    public void lireLabel(String ligne) throws InstructionError {
    	ArrayList<String> mot = LexAndParse.lexer(ligne) ;
    	if (mot.size() != 2) {
    		throw new InstructionError("Nombre d'argument pour la commande LABEL invalide") ;
    	}
        String var = mot.get(1) ;
        LabelInstruction lab = new LabelInstruction(var, programme.size(), m) ; //!!!!+1 ou pas???
        programme.add(lab) ; 
    }
    
    public void lireGoto(String ligne) throws InstructionError {
    	ArrayList<String> mot = LexAndParse.lexer(ligne) ;
    	if (mot.size() != 2) {
    		throw new InstructionError("Nombre d'argument pour la commande GOTO invalide") ;
    	}
        String var = mot.get(1) ; 
        GotoInstruction gt = new GotoInstruction(var) ; 
        programme.add(gt) ;
    }
    
    public void lireJumpEqual(String ligne) throws InstructionError {
    	ArrayList<String> mot = LexAndParse.lexer(ligne) ;
    	if (mot.size() != 4) {
    		throw new InstructionError("Nombre d'argument pour la commande JumpEqual invalide") ;
    	}
        String exp1 = mot.get(1) ; 
        String exp2 = mot.get(2) ;
        String lab = mot.get(3) ;
        JumpEqualInstruction je = new JumpEqualInstruction(exp1, exp2, lab) ; 
        programme.add(je) ;        
    }
    
    public void lireJumpNEqual(String ligne) throws InstructionError {
    	ArrayList<String> mot = LexAndParse.lexer(ligne) ;
    	if (mot.size() != 4) {
    		throw new InstructionError("Nombre d'argument pour la commande JumpNEqual invalide") ;
    	}
        String exp1 = mot.get(1) ; 
        String exp2 = mot.get(2) ;
        String lab = mot.get(3) ;
        JumpNEqualInstruction jne = new JumpNEqualInstruction(exp1, exp2, lab) ; 
        programme.add(jne) ;        
    }
    
    public void lireJumpLess(String ligne) throws InstructionError {
    	ArrayList<String> mot = LexAndParse.lexer(ligne) ;
    	if (mot.size() != 4) {
    		throw new InstructionError("Nombre d'argument pour la commande JumpLess invalide") ;
    	}
        String exp1 = mot.get(1) ; 
        String exp2 = mot.get(2) ;
        String lab = mot.get(3) ;
        JumpLessInstruction jl = new JumpLessInstruction(exp1, exp2, lab) ; 
        programme.add(jl) ;        
    }
    
    public void lireJumpLessEqual(String ligne) throws InstructionError {
    	ArrayList<String> mot = LexAndParse.lexer(ligne) ;
    	if (mot.size() != 4) {
    		throw new InstructionError("Nombre d'argument pour la commande JumpLessEqual invalide") ;
    	}
        String exp1 = mot.get(1) ; 
        String exp2 = mot.get(2) ;
        String lab = mot.get(3) ;
        JumpLessEqualInstruction jle = new JumpLessEqualInstruction(exp1, exp2, lab) ; 
        programme.add(jle) ;        
    }
    
    public void lireAdvance(String ligne) throws InstructionError {
    	ArrayList<String> mot = LexAndParse.lexer(ligne) ;
    	if (mot.size() != 2) {
    		throw new InstructionError("Nombre d'argument pour la commande ADVANCE invalide") ;
    	}
        String dir = mot.get(1) ;
        AdvanceInstruction adv = new AdvanceInstruction(dir, t) ;
        programme.add(adv) ;
    }
    
    public void lireExtract(String ligne) throws InstructionError {
    	ArrayList<String> mot = LexAndParse.lexer(ligne) ;
    	if (mot.size() != 1) {
    		throw new InstructionError("Nombre d'argument pour la commande EXTRACT invalide") ;
    	}
        ExtractInstruction ext = new ExtractInstruction(t) ; 
        programme.add(ext) ;
    }
    
    public void lireDetect(String ligne) throws InstructionError {
    	ArrayList<String> mot = LexAndParse.lexer(ligne) ;
    	if (mot.size() != 4) {
    		throw new InstructionError("Nombre d'argument pour la commande DETECT invalide") ;
    	}
        String var = mot.get(1) ; 
        String dir = mot.get(2) ;
        String port = mot.get(3) ;
        DetectInstruction det = new DetectInstruction(var, dir, port, t) ;
        programme.add(det) ;
    }
    
    public boolean lancerProg(boolean inter) throws InterruptedException, IOException {
 //       programme.add(new AfficheInstruction()) ;
//        Memoire m = new Memoire() ;
               
    	boolean error = false ; 
    	FileWriter w = new FileWriter("erreursExecution.txt") ;
    	    	
        while (m.getNextI() < programme.size()) {
            if (inter) {
                System.out.print("Prochaine instruction (ENTER pour executer) : ");
                programme.get(m.getNextI()).afficheInstruction() ;
                sc.nextLine() ;
            }
            
            try {
            	programme.get(m.getNextI()).executer(m) ;
            } catch (InstructionError e) {
            	w.write(e.toString() + "\n");
            	m.setNextI(m.getNextI() + 1) ;
            	error = true ;
            }
            if (inter && m.getNextI() != programme.size()) {
                m.afficheMemoire() ;
            }
//            System.out.println(m.getNextI());
        }
        
        
        m.afficheMemoire();
        
        w.close();
        return error ;

    /*   for (Instruction i : programme) {
            i.executer(m) ;
        }*/
    }
}