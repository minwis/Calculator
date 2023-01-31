import java.util.*;

public class MeanMedianMode {

    public static String mean(String[] a) {
        String output = String.valueOf(a[0]);
        for ( int i = 1; i < a.length; i++ ) {
            output = Test.RunAdd(output, a[i]);
        }
        output = Test.RunDivide(output, String.valueOf(a.length));
        return output;
    }

    public static String median(String[] a) {
        Arrays.sort(a);
        int i = a.length / 2;
        String output = a[i];
        if ( a.length % 2 == 0 ) {
            int i2 = (a.length + 1) / 2;
            String output2 = a[i2];
            output = Test.RunAdd(output, output2);
            output = Test.RunDivide(output, "2");
        }
        return output;
    }

    public static String mode(String[] a) {
        int[] count = new int[a.length];
        for ( int i = 0; i < a.length; i++ ) {
            for ( int j = 0; j < a.length; j++ ) {
                if ( a[i].equals(a[j]) ) {
                    count[i]++;
                }
            }
        }
        String output = "";
        int max = 0;
        for ( int i = 0; i < a.length; i++ ) {
            if ( max < count[i] ) {
                output = a[i];
                max = count[i];
            }
        }
        return output;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        double[] arr = new double[] {20,43,74,89,75,60,31,43,37,36,50,38,21,99,93,45,64,92,38,60};
        String[] a = new String[arr.length];
        for ( int i = 0; i < arr.length; i++ ) {
            a[i] = String.valueOf(arr[i]);
        }
        System.out.println("Mean: " + mean(a) + " Median: " + median(a) + " Mode: " + mode(a) );
    }
}
