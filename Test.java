
public class Test {
    public static int tc = 0;

    public static void PrintBigVaule(BigValue v) {
        System.out.println("minus:" + v.isMinus() + " int:" + v.getIntLen() + " pt:" + v.getPointLen());
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
        CheckBigVaule(new BigValue("32.359"), false, 2, 3);
        CheckBigVaule(new BigValue("-123.456"), true, 3, 3);

        // get
        System.out.println();
        System.out.println("Get");
        BigValue v = new BigValue("-431.4992");
        if (v.getDigit(100) == 0) {
            System.out.println("#" + (++tc) + " Pass");
        }
        if (v.getDigit(0) == 1) {
            System.out.println("#" + (++tc) + " Pass");
        }
        if (v.getDigit(2) == 4) {
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
        System.out.println();
        System.out.println("Set");
        for ( int i = 0; i < s.length(); i++ ) {
            if ( v.getString().charAt(i) != s.charAt(i) ) {
                System.out.print("#" + (++tc) + " Fail : " + v.getString());
            }
        }
        System.out.println("#" + (++tc) + " Pass");

        System.out.println();
        System.out.println("Addition");
        TestAdd(tc++,"12345","56789","69134");
        TestAdd(tc++,"88","1000","1088");
        TestAdd(tc++,"0","123.45","123.45");
        TestAdd(tc++,"123.45","56.789","180.239");
        TestAdd(tc++,"9999999999.9","0.9","10000000000.8");

        System.out.println();
        System.out.println("Subtraction");
        TestSubtract(tc++,"5678","1239","4439");
        TestSubtract(tc++,"1234","5678","-4444");
        TestSubtract(tc++,"314.1592","56.78","257.3792");
        TestSubtract(tc++,"143.3108","3808.19","-3664.8792");
        TestSubtract(tc++,"1234","56.78","1177.22");
        TestSubtract(tc++, "1353", "1353.98", "-0.98");

        System.out.println();
        System.out.println("Multiplication");
        TestMultiply(tc++,"314.89","9.126","2873.68614");
        TestMultiply(tc++,"9","6","54");


    }

    private static void TestAdd(int tc, String s1, String s2, String answer) {
        BigValue v1 = new BigValue(s1);
        BigValue v2 = new BigValue(s2);
        BigValue v3 = v1.Add(v2);
        if (v3.getString().equals(answer)) {
            System.out.println("#" + (tc) + " Pass");
        } else {
            System.out.println("#" + (tc) + " Fail " + answer + " != " + v3.getString());
        }
    }

    private static void TestSubtract(int tc, String s1, String s2, String answer) {
        BigValue v1 = new BigValue(s1);
        BigValue v2 = new BigValue(s2);
        BigValue v3 = v1.Subtract(v2);
        if (v3.getString().equals(answer)) {
            System.out.println("#" + (tc) + " Pass");
        } else {
            System.out.println("#" + (tc) + " Fail " + answer + " != " + v3.getString());
        }
    }

    private static void TestMultiply(int tc, String s1, String s2, String answer) {
        BigValue v1 = new BigValue(s1);
        BigValue v2 = new BigValue(s2);
        if (v1.Multiply(v2).equals(answer)) {
            System.out.println("#" + (tc) + " Pass");
        } else {
            System.out.println("#" + (tc) + " Fail " + answer + " != " + v1.Multiply(v2));
        }
    }

}