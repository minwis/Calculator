
public class BigValue {

    public boolean plus_minus = false;
    public int a_len;
    public int b_len;
    public int[] a;
    public int[] b = null;
    public String output = "";
    public int max = 0;
    public int min = 0;

    public boolean isMinus() {
        return plus_minus;
    }

    public int getIntLen() {
        return a_len;
    }

    public int getPointLen() {
        return b_len;
    }


    //Used for saving digits in arrays and determine the number is negative or positive.
    public BigValue(String s) {
        int start = 0;
        if (s.charAt(0) == '-') {
            plus_minus = true;
            start = 1;
        } else if (s.charAt(0) == '+') {
            start = 1;
        }

        int i = start;
        String IntPart = "";
        while (i < s.length() && s.charAt(i) != '.') {
            IntPart += String.valueOf(s.charAt(i) - '0');
            i++;
        }
        a_len = i - start;
        a = new int[a_len];
        for (int I = 0; I < a_len; I++) {
            a[I] = IntPart.charAt(I) - '0';
        }

        if (i - start == s.length() - start) {
            b_len = 0;
            return;
        } else {
            b_len = s.length() - i - 1;
            b = new int[b_len];
        }

        int j = 0;
        i++;
        while (j < b.length) {
            b[j] = s.charAt(i) - '0';
            i++;
            j++;
        }
    }

    public BigValue invert(BigValue v) {
        String output = "";
        for ( int i = 0; i < v.a_len; i++ ) {
            output += String.valueOf(v.a[i]);
        }
        for ( int i = 0; i < v.b_len; i++ ) {
            output += String.valueOf(v.b[i]);
        }

        v.output = output;
        v.a_len += v.b_len;
        v.a = new int[v.a_len];
        v.b_len = 0;
        v.b = null;

        for ( int i = 0;  i < v.a_len; i++ ) {
            v.a[i] = output.charAt(i) - '0';
        }

        return v;
    }

    public void invert(BigValue v, int add2) {
        String output = "";
        for ( int i = 0; i < v.a_len; i++ ) {
            output += String.valueOf(v.a[i]);
        }
        for ( int i = 0; i < v.b_len; i++ ) {
            output += String.valueOf(v.b[i]);
        }
        for ( int i = 0; i < add2; i++ ) {
            output += "0";
        }

        v.output = output;
        v.a_len += (v.b_len + add2);
        v.a = new int[v.a_len];
        v.b_len = 0;
        v.b = null;

        for ( int i = 0;  i < v.a_len; i++ ) {
            v.a[i] = output.charAt(i) - '0';
        }

    }

    public BigValue Round(BigValue v, int len_point) {
        if ( v.b_len != 0 ) {
            if (v.b[len_point - 1] >= 5) {
                String add = "0.";
                for (int i = 0; i < len_point - 1; i++) {
                    add += "0";
                }
                add += String.valueOf(10 - v.b[len_point - 1]);
                BigValue add_v = new BigValue(add);
                v = v.Add(add_v);
            } else {
                v.b[len_point - 1] = 0;
            }
        }
        return v;
    }


    public int getDigit(int i) {
        if (0 <= i && i < a.length) {
            return a[a.length - i - 1];
        } else if (i < 0 && b != null && -i <= b.length) {
            return b[-i - 1];
        } else {
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
        if (b) {
            plus_minus = true;
        }
    }


    public void setDigit(int position, int n) {
        if (position < 0) {
            b[-position - 1] = n;
        } else {
            a[a.length - position - 1] = n;
        }
    }


    public String getString() {
        String output = "";
        if (plus_minus) {
            output = "-";
        }

        int i = 0;
        while (a[i] == 0 && i < a.length - 1) {
            i++;
        }
        while (i < a_len) {
            output += a[i];
            i++;
        }

        if ( b_len != 0 ) {
            i = b_len - 1;
            while (i >= 0 && b[i] == 0) {
                i--;
            }
            if ( i == -1 ) {
            }
            else {
                output += ".";
                int j = 0;
                while (j <= i) {
                    output += b[j];
                    j++;
                }
            }
        }
        return output;
    }

    public int[] min_max(int a_len1, int a_len2, int b_len1, int b_len2) {
        if (a_len2 < a_len1) {
            max = a_len1;
        } else {
            max = a_len2;
        }

        if (b_len2 < b_len1) {
            min = b_len1;
        } else {
            min = b_len2;
        }

        return new int[]{min, max};
    }

    public int Compare(BigValue v1, BigValue v2) {
        if (v1.a_len < v2.a_len) {
            return -1;
        } else if (v1.a_len > v2.a_len) {
            return 1;
        }
        int max = v1.a_len;
        int min = -Math.max(v1.b_len, v2.b_len);
        for (int i = max - 1; i >= min; i--) {
            if (v1.getDigit(i) < v2.getDigit(i)) {
                return -1;
            } else if (v1.getDigit(i) > v2.getDigit(i)) {
                return 1;
            }
        }
        return 0;
    }


    public BigValue Add(BigValue v) {

        int min = min_max(v.a_len, a_len, v.b_len, b_len)[0];
        int max = min_max(v.a_len, a_len, v.b_len, b_len)[1];

        BigValue v3 = new BigValue(max, min);
        int ten = 0;

        for (int i = -min; i < max; i++) {
            int d1 = getDigit(i);
            int d2 = v.getDigit(i);
            int n = d1 + d2 + ten;
            if (i == max - 1) {
                v3.setDigit(i, n);
            } else {
                v3.setDigit(i, n % 10);
                ten = n / 10;
            }
        }
        return v3;
    }


    public BigValue Subtract(BigValue v) {

        BigValue big, small;
        boolean is_minus;
        if ( Compare(this, v) < 0 ){
            big = v;
            small = this;
            is_minus = true;
        } else {
            big = this;
            small = v;
            is_minus = false;
        }
        min = Math.max(v.b_len, b_len);
        max = Math.max(v.a_len, a_len);

        BigValue v3 = new BigValue(max, min);
        int ten = 0;

        for (int i = -min; i < max; i++) {
            int n = big.getDigit(i) - small.getDigit(i) - ten;

            if (n < 0 && i != max - 1) {
                n += 10;
                ten = 1;
            } else {
                ten = 0;
            }
            v3.setDigit(i, n);
        }
        v3.setMinus(is_minus);
        return v3;
    }


    public BigValue Multiply(BigValue v) {
        int array_len = b_len + v.b_len + a_len + v.a_len - 1;
        int[] array = new int[array_len];

        int I = -1;
        for (int i = -v.b_len; i < v.a_len; i++) {
            int up = 0;
            int J = 0;
            I++;
            for (int j = -b_len; j < a_len; j++) {
                J++;
                int a = getDigit(j);
                int b = v.getDigit(i);
                int n = a * b + up + array[array_len - J - I];
                if (10 <= n && j != a_len - 1) {
                    up = n / 10;
                    n %= 10;
                } else {
                    up = 0;
                }
                array[array_len - J - I] = n;
            }
            //array[array_len - J - I - 1] += up;
        }

        String output = "";
        for ( int i = 0; i < array_len; i++ ) {
            if ( i == a_len + v.a_len - 1 ) {
                output += ".";
            }
            output += array[i];
        }

        return new BigValue(output);
    }


    public BigValue Divide(BigValue v) {

        int add1 = 0;
        int add2 = 0;
        if ( b_len < v.b_len ) { //this에 0이 더해져야 할 경우
            add1 = v.b_len - b_len;
        }
        else if ( b_len > v.b_len ) { //v에 0이 더해져야 할 경우
            add2 = b_len - v.b_len;
        }
        int len_point = 2; //이 정수를 바꾸면 몇 번째 자리까지 계산할지 정할 수 있음.
        String divided = "";
        invert(v, add2);
        int position = a_len - 1; //(this는 나누는 수.) divided가 this의 어떤 자리 숫자가 더해져야 하는지 그 위치.
        BigValue value = new BigValue(a_len + b_len + add1, len_point + 1);

        for (int i = 0; i < a_len + b_len + add1 + len_point + 1; i++) {
            divided += String.valueOf(getDigit(position));
            BigValue Divided = new BigValue(divided);
            int digit = 0;

            if ( Compare(Divided, v) >= 0 ) { //만약 Divided가 클 경우.
                for ( int j = 1; j <= 9; j++ ) {
                    BigValue J = new BigValue(String.valueOf(j));
                    BigValue multiply = v.Multiply(J);
                    BigValue Dividing = invert(multiply); //나누는 수 * j
                    BigValue v_Dividing = invert(Divided.Subtract(Dividing)); //나눠지는 수 - (나누는 수*j)
                    String b = v_Dividing.getString(); //쓸 때 없는 0이 붙어있을 경우를 처리. ex) 000009 => 9
                    BigValue v_Dividing2 = new BigValue(b);

                    if ( Compare(v_Dividing2, v) < 0 ) {
                        digit = j;
                        divided = v_Dividing.getString();
                        break;
                    }
                }
            }

            //저장 파트; setDigit을 어떻게 쓸 수 있을까요?
            if ( i < a_len + b_len + add1 ) {
                value.a[i] = digit;
            }
            else {
                value.b[i - a_len - b_len - add1 ] = digit;
            }
            position--;
        }
        return Round(value, len_point + 1);
    }
}
