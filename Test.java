import javax.swing.*;

public class Test {
    public static int tc = 0;

    public static void PrintBigVaule(BigValue v) {
        System.out.println("minus:" + v.isMinus()
                + " int:" + v.getIntLen()
                + " pt:" + v.getPointLen());
    }

    public static void CheckBigVaule(BigValue v, boolean is_minus, int i, int p) {
        System.out.print("#" + (++tc) + " ");
        if (v.isMinus() == is_minus && v.getIntLen() == i && v.getPointLen() == p) {
            System.out.println("Pass");
            return;
        }
        System.out.print("Fail : ");
        PrintBigVaule(v);
    }
    public static void main(String args[]) {
        /*Scanner sc = new Scanner(System.in);
        String S1 = sc.nextLine();
        String S2 = sc.nextLine();*/

        //Parsing Test
        System.out.println("Parsing Test");
        CheckBigVaule(new BigValue("12345"), false, 5, 0);
        CheckBigVaule(new BigValue("-22"), true, 2, 0);
        CheckBigVaule(new BigValue("+3.456"), false, 1, 3);
        CheckBigVaule(new BigValue("-44.000"), true, 2, 3);
        CheckBigVaule(new BigValue("-123.456"), true, 3, 3);

        // get
        System.out.println("Get");
        BigValue v = new BigValue("-123.456");
        if (v.getDigit(100) == 0) {
            System.out.println("#" + (++tc) + " Pass");
        }
        if (v.getDigit(0) == 3) {
            System.out.println("#" + (++tc) + " Pass");
        }
        if (v.getDigit(-1) == 4) {
            System.out.println("#" + (++tc) + " Pass");
        }
        if (v.getDigit(-4) == 0) {
            System.out.println("#" + (++tc) + " Pass");
        }

        // set
        int len_int = 10;
        int len_point = 5;
        v = new BigValue(len_int, len_point);
        v.setMinus(true);
        v.setDigit(-3,1);
        v.setDigit(-2,2);
        v.setDigit(-1,3);
        v.setDigit(0,4);
        v.setDigit(1,5);
        String s = "-54.321";
        System.out.println("Set");
        for ( int i = 0; i < s.length(); i++ ) {
            if ( v.getString().charAt(i) != s.charAt(i) ) {
                System.out.print("#" + (++tc) + " Fail : " + v.getString());
            }
        }
        System.out.println("#" + (++tc) + " Pass");
    }

}