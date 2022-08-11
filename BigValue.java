
public class BigValue {

    public static boolean Switch = false;

    public boolean plus_minus = false;
    public int a_len;
    public int b_len;
    public int[] a;
    public int[] b = null;
    public int max = 0;
    public int min = 0;

    public boolean isMinus() {return plus_minus;}
    public int getIntLen() {
        return a_len;
    }
    public int getPointLen() {
        return b_len;
    }



    //Used for saving digits in arrays and determine the number is negative or positive.
    public BigValue(String s) {
        int start = 0;
        if ( s.charAt(0) == '-' ) {
            plus_minus = true;
            start = 1;
        }
        else if ( s.charAt(0) == '+' ) {
            start = 1;
        }

        int i = start;
        String IntPart = "";
        while ( i < s.length() && s.charAt(i) != '.' ) {
            IntPart += String.valueOf(s.charAt(i) - '0');
            i++;
        }
        a_len = i - start;
        a = new int[a_len];
        for ( int I = 0; I < a_len; I++ ) {
            a[I] = IntPart.charAt(I) - '0';
        }

        if ( i - start == s.length() - start ) {
            b_len = 0;
            return;
        }
        else {
            b_len = s.length() - i - 1;
            b = new int[b_len];
        }

        int j = 0;
        i++;
        while ( j < b.length ) {
            b[j] = s.charAt(i) - '0';
            i++;
            j++;
        }
    }



    //Used for determining digits if the number is given.
    public int getDigit(int i) {
        if ( 0 <= i && i < a.length ) {
            return a[a.length - i - 1];
        }
        else if ( i < 0 && b != null && -i <= b.length ) {
            return b[-i - 1];
        }
        else {
            return 0;
        }
    }



    //Used for making a number by combining digits.
    public BigValue(int len_int, int len_point) {
        a_len = len_int;
        b_len = len_point;
        a = new int[len_int];
        b = new int[len_point];
    }


    public void setMinus(boolean b) {
        if ( b == true ) {
            plus_minus = true;
        }
    }


    public void setDigit(int position, int n) {
        if ( position < 0 ) {
            b[-position - 1] = n;
        }
        else {
            a[a.length - position - 1] = n;
        }
    }


    public String getString() {
        String output = "";
        if ( plus_minus == true ) {
            output = "-";
        }
        if ( Switch ) {
            output = "-";
        }

        int i = 0;
        while ( a[i] == 0 && i < a.length - 1 ) {
            i++;
        }
        while ( i < a_len ) {
            output += a[i];
            i++;
        }

        if ( b_len == 0 ) {
        }
        else {
            i = b_len - 1;
            while ( b[i] == 0 ) {
                i--;
            }
            output += ".";
            int j = 0;
            while ( j <= i ) {
                output += b[j];
                j++;
            }
        }
        return output;
    }

    public int[] min_max(int a_len1, int a_len2, int b_len1, int b_len2) {
        if ( a_len2 < a_len1 ) {
            max = a_len1;
        }
        else {
            max = a_len2;
        }

        if ( b_len2 < b_len1 ) {
            min = b_len1;
        }
        else {
            min = b_len2;
        }

        return new int[] {min, max};
    }

    public int[] big_small(BigValue v1, BigValue v2) {
        Switch = false;
        int max = v1.a_len;
        int min = v1.b_len;;
        int same = 0;

        if ( v1.a_len < v2.a_len ) {
            max = v2.a_len;
            Switch = true;
        }
        else if ( v1.a_len > v2.a_len ) {
        }
        else {
            for ( int i = 0; i < max; i++ ) {
                if ( v1.a[i] < v2.a[i] ) {
                    max = v2.a_len;
                    Switch = true;
                    break;
                }
                else if ( v1.a[i] > v2.a[i] ) {
                    break;
                }
                else {
                    same++;
                }
            }
        }

        if ( v1.b_len < v2.b_len ) {
            min = v2.b_len;
            if (same == max) {
                Switch = true;
            }
        }
        else if ( v1.b_len > v2.b_len ) {
        }
        else {
            for ( int i = 0; i < min; i++ ) {
                if ( v1.b[i] < v2.b[i] ) {
                    max = v2.b_len;
                    if ( same == (max - 1) ) {
                        Switch = true;
                    }
                    break;
                }
                else if ( v1.b[i] > v2.b[i] ) {
                    break;
                }
            }
        }


        return new int[] {min,max};
    }


    public BigValue Add(BigValue v) {

        int min = min_max(v.a_len, a_len, v.b_len, b_len) [0];
        int max = min_max(v.a_len, a_len, v.b_len, b_len) [1];

        BigValue v3 = new BigValue(max, min);
        int ten = 0;

        for (int i = -min; i < max; i++) {
            int d1 = getDigit(i);
            int d2 = v.getDigit(i);
            int n = d1+d2 + ten;
            if ( i == max - 1 ) {
                v3.setDigit(i, n);
            }
            else {
                v3.setDigit(i, n % 10);
                ten = n / 10;
            }
        }
        return v3;
    }


    public BigValue Subtract(BigValue v) {


        int min = big_small( this ,v) [0];
        int max = big_small( this ,v ) [1];

        BigValue v3 = new BigValue(max, min);
        int ten = 0;

        for (int i = -min; i < max; i++) {
            int d1 = getDigit(i);
            int d2 = v.getDigit(i);
            int n = 0;
            if ( Switch ) {
                n = d2 - d1;
            }
            else {
                n = d1 - d2;
            }

            if ( ten == 1 ) {
                n -= ten;
                ten = 0;
            }

            if ( n < 0 && i != max - 1 ) {
                n += 10;
                ten = 1;
            }
            v3.setDigit(i, n);
        }
        return v3;
    }
}
