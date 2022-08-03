public class BigValue {

    public boolean plus_minus = false;
    public int a_len = 0;
    public int b_len = 0;
    public int[] a = null;
    public int[] b = null;

    public boolean isMinus() {return plus_minus;}
    public int getIntLen() {
        return a_len;
    }
    public int getPointLen() {
        return b_len;
    }

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

        if ( i == s.length() ) {
            b_len = 0;
        }
        else {
            b_len = s.length() - i - start;
        }

        b = new int[b_len];

        int j = 0;
        while ( j < b_len ) {
            i++;
            b[j] = s.charAt(i) - '0';
            j++;
        }
    }

    public int getDigit(int i) {
        if ( 0 <= i && i < a.length  ) {
            return a[a_len - i - 1];
        }
        else if ( i < 0 && -i < b.length ) {
            return b[-i - 1];
        }
        else {
            return 0;
        }
    }







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
            a[a_len - position - 1] = n;
        }
    }

    public String getString() {
        String output = "";
        if ( plus_minus == true ) {
            output = "-";
        }

        int i = 0;
        while ( a[i] == 0 ) {
            i++;
        }
        while ( i < a_len ) {
            output += a[i];
            i++;
        }

        i = b_len - 1;
        while ( b[i] == 0 ) {
            i--;
        }
        if ( i != 0 ) {
            output += ".";
        }
        int j = 0;
        while ( 0 <= j && j <= i ) {
            output += b[j];
            j++;
        }

        return output;
    }




}
