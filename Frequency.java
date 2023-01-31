import java.util.*;

public class Frequency {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine(); //ex: 25,40,27,30,27,29,38,42,47,38,28,50,29,31,39,29,42,52,55,34
        String number = "";
        List<String> list=new ArrayList<String>();
        for ( int i = 0; i < s.length(); i++ ) {
            if ( '0' <= s.charAt(i) && s.charAt(i) <= '9' ) {
                number += String.valueOf(s.charAt(i) - '0');
            }
            else if ( s.charAt(i) == '.' ) {
                number += ".";
            }
            else {
                list.add(number);
                number = "";
            }
        }
        list.add(number);
        String[] arr = list.toArray(new String[0]);
        double[] classes = new double[arr.length];
        for ( int i = 0; i < arr.length; i++ ) {
            classes[i] = Double.parseDouble(arr[i]);
        }

        System.out.print("Width of a Class: ");
        double width_class = sc.nextInt(); //ex: 10
        System.out.print("Lowest Class Limit: ");
        double min = sc.nextInt(); //ex: 20
        System.out.print("Highest Class Limit: ");
        double max = sc.nextInt(); //ex: 60

        int[] frequencies = new int[(int)Math.round((max - min) / width_class)];
        int index = 0;
        for ( double i = min; i < max; i += width_class ) {
            for ( int j = 0; j < classes.length; j++ ) {
                if ( i <= classes[j] && classes[j] < i+width_class ) {
                    frequencies[index]++;
                }
            }
            System.out.println(i + " ~ " + (i + width_class) + " : " + frequencies[index]);
            index++;
        }
        System.out.println("Range: " + arr.length);
    }
}
