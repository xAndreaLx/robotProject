import java.util.ArrayList;

public class LexAndParse{
    private static boolean isToken(String s){
        return(s != null && s.length() > 0 && s.charAt(0) != ' ');
    }
    public static ArrayList<String> lexer(String ls){
        if(ls == null) throw new IllegalArgumentException();
        String[] t = ls.split(" ");
        ArrayList<String> tokens = new ArrayList<String>();
        for(int i=0;i<t.length;i++){
            t[i]=t[i].trim();
            if(isToken (t[i])) 
            	tokens.add(t[i]);
        }
        return tokens;
    }
    public static boolean isIdentificateur(String s){
        return Character.isLetter(s.charAt(0)) ;
    }
}
