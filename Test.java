
public class Test {
    public static int tc = 0;

    public static void PrintBigValue(BigValue v) {
        System.out.println("minus:" + v.isMinus() + " int:" + v.getIntLen() + " pt:" + v.getPointLen());
    }

    public static void CheckBigValue(BigValue v, boolean is_minus, int i, int p) {
        System.out.print("#" + (++tc) + " ");
        if (v.isMinus() == is_minus && v.getIntLen() == i && v.getPointLen() == p) {
            System.out.println("Pass");
            return;
        }
        System.out.print("Fail : ");
        PrintBigValue(v);
    }
    public static void main(String args[]) {
        /*
        //Parsing Test
        System.out.println("Parsing Test");
        CheckBigValue(new BigValue("12345"), false, 5, 0);
        CheckBigValue(new BigValue("-22"), true, 2, 0);
        CheckBigValue(new BigValue("+3.456"), false, 1, 3);
        CheckBigValue(new BigValue("32.359"), false, 2, 3);
        CheckBigValue(new BigValue("-123.456"), true, 3, 3);

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

        System.out.println("\n" + "Addition");
        TestAdd(tc++,"12345","56789","69134");
        TestAdd(tc++,"88","1000","1088");
        TestAdd(tc++,"0","123.45","123.45");
        TestAdd(tc++,"123.45","56.789","180.239");
        TestAdd(tc++,"9999999999.9","0.9","10000000000.8");

        System.out.println("\n" + "Subtraction");
        TestSubtract(tc++,"5678","1239","4439");
        TestSubtract(tc++,"1234","5678","-4444");
        TestSubtract(tc++,"314.1592","56.78","257.3792");
        TestSubtract(tc++,"143.3108","3808.19","-3664.8792");
        TestSubtract(tc++,"1234","56.78","1177.22");
        TestSubtract(tc++, "1353", "1353.98", "-0.98");

        System.out.println("\n" + "Multiplication");
        TestMultiply(tc++,"314.89","9.126","2873.68614");
        TestMultiply(tc++,"9","6","54");
        TestMultiply(tc++,"9","0","0");

        System.out.println("\n" + "Division");
        TestDivide(tc++,"9.126","314.89","0.03"); //0.02898
        TestDivide(tc++,"314.89","9.126","34.5"); //34.5047118
        TestDivide(tc++, "3", "4", "0.75");
        TestDivide(tc++, "1", "3", "0.33");
        TestDivide(tc++, "9", "9", "1");
        TestDivide(tc++, "9", "3", "3");
        TestDivide(tc++, "0.9", "1.7", "0.53");
        TestDivide(tc++, "9", "0", "(any number)");*/



        System.out.print(RunDivide("22", "7"));
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

    public static String RunAdd(String s1, String s2) {
        BigValue v1 = new BigValue(s1);
        BigValue v2 = new BigValue(s2);
        BigValue v3 = v1.Add(v2);
        return v3.getString();
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

    public static String RunSubtract(String s1, String s2) {
        BigValue v1 = new BigValue(s1);
        BigValue v2 = new BigValue(s2);
        BigValue v3 = v1.Subtract(v2);
        return v3.getString();
    }

    private static void TestMultiply(int tc, String s1, String s2, String answer) {
        BigValue v1 = new BigValue(s1);
        BigValue v2 = new BigValue(s2);
        BigValue v3 = v1.Multiply(v2);
        if (v3.getString().equals(answer)) {
            System.out.println("#" + (tc) + " Pass");
        } else {
            System.out.println("#" + (tc) + " Fail " + answer + " != " + v3.getString());
        }
    }

    public static String RunMultiply(String s1, String s2) {
        BigValue v1 = new BigValue(s1);
        BigValue v2 = new BigValue(s2);
        BigValue v3 = v1.Multiply(v2);
        return v3.getString();
    }

    private static void TestDivide(int tc, String s1, String s2, String answer) {
        if ( s2.equals("0") ) {
            System.out.println("#" + (tc) + " You cannot divide a number with 0");
            return;
        }
        BigValue v1 = new BigValue(s1);
        BigValue v2 = new BigValue(s2);
        BigValue v3 = v1.Divide(v2);
        if (v3.getString().equals(answer)) {
            System.out.println("#" + (tc) + " Pass");
        } else {
            System.out.println("#" + (tc) + " Fail " + answer + " != " + v3.getString());
        }
    }

    public static String RunDivide(String s1, String s2) {
        BigValue v1 = new BigValue(s1);
        BigValue v2 = new BigValue(s2);
        BigValue v3 = v1.Divide(v2);
        return v3.getString();
    }

}