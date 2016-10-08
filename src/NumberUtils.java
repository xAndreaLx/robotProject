public class NumberUtils {
    
    public static boolean isNumeric(String s) {
        try {
            int i = Integer.parseInt(s) ;
        } catch (NumberFormatException nfe) {
            return false ;
        }
        return true ;
    }
}