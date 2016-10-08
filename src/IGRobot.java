import java.awt.*;
import javax.swing.*;
import java.util.*;

class SpecialPanel extends JPanel{
    char[][] jeu;
    Color blanc = Color.white;
    Color noir = new Color(150,120,120);
    private HashMap<Character,ImageIcon>  images;
    SpecialPanel(char[][]  je, HashMap<Character,ImageIcon> im){
	jeu = je;
	images = im;
    }
    public void paintComponent(Graphics g) {
	super.paintComponent(g);  
	for (int i=0; i<jeu.length; i++){
	    for (int j=0; j<jeu[0].length; j++){
		images.get(jeu[i][j]).paintIcon(this,g,j*48,i*48);
	    }
	}
    }
}


public class IGRobot extends JFrame implements AfficheRobot{
    private char[][] jeu;
    private HashMap<Character,ImageIcon>  images;
    private SpecialPanel jpane;
    public IGRobot(){
		images = new  HashMap<Character,ImageIcon>();
		this.associerCharImage(' ',"r-sand.png");
		this.associerCharImage('*',"r-rock.png");
		this.associerCharImage('#',"r-water.png");
		this.associerCharImage('$',"r-coal.png");
		this.associerCharImage('A',"r-robot.png");
    }
    
    public void initialiser(ArrayList<ArrayList<Case>>  je){
		if (jeu != null)
		    throw new FenetreExisteDeja();
		jeu = new char[je.size()][je.get(0).size()];
		for (int i = 0; i<je.size(); i++){
		    for (int j=0; j<je.get(0).size(); j++){
			jeu[i][j]=je.get(i).get(j).getRep();
		    }
		}
		this.setTitle("Terrain");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jpane = new SpecialPanel(jeu, images);
		this.setContentPane(jpane);
		jpane.setPreferredSize(new Dimension(jeu[0].length*48,jeu.length*48));
		jpane.setBackground(Color.black);
		this.pack();
		this.setVisible(true);
    }
    
//    public void initialiser(char[][]  je){
//		if (jeu != null)
//		    throw new FenetreExisteDeja();
//		jeu = new char[je.length][je[0].length];
//		for (int i = 0; i<je.length; i++){
//		    for (int j=0; j<je[0].length; j++){
//			jeu[i][j]=je[i][j];
//		    }
//		}
//		this.setTitle("Terrain");
//		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		jpane = new SpecialPanel(jeu, images);
//		this.setContentPane(jpane);
//		jpane.setPreferredSize(new Dimension(jeu[0].length*48,jeu.length*48));
//		jpane.setBackground(Color.black);
//		this.pack();
//		this.setVisible(true);
//    }
    
    public void associerCharImage(char c, String s){
		images.put(c,new ImageIcon("images/" + s));
    }
    
    public void changerCase(int x, int y, char c){
		jeu[x][y]=c;
    }
    
    public void afficher(){
		jpane.repaint();
    }
    
    public void terminer(){
		this.dispose();
    }
}
class FenetreExisteDeja extends RuntimeException{}

