import java.util.*;

public class AllAdd {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String sum = "0";
        while ( true ) {
            String s = sc.nextLine();
            if ( s.isEmpty() ) {
                System.out.print(sum);
                return;
            }
            else {
                sum = Test.RunAdd(sum, s);
            }
        }
    }
}
