import java.util.*;

public class Frequency {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine(); //25,40,27,30,27,29,38,42,47,38,28,50,29,31,39,29,42,52,55,34
        String number = "";
        List<String> list=new ArrayList<String>();
        for ( int i = 0; i < s.length(); i++ ) {
            if ( '0' <= s.charAt(i) && s.charAt(i) <= '9' ) {
                number += String.valueOf(s.charAt(i) - '0');
            }
            else {
                list.add(number);
                number = "";
            }
        }
        String[] arr = list.toArray(new String[0]);
        int[] classes = new int[arr.length];
        for ( int i = 0; i < arr.length; i++ ) {
            classes[i] = Integer.parseInt(arr[i]);
        }

        System.out.print("Width of a Class: ");
        int width_class = sc.nextInt();
        System.out.print("Minimum: ");
        int min = sc.nextInt();
        System.out.print("Maximum: ");
        int max = sc.nextInt();
        int[] frequencies = new int[(max - min) / width_class];
        int index = 0;
        for ( int i = min; i < max; i += width_class ) {
            for ( int j = 0; j < classes.length; j++ ) {
                if ( i <= classes[j] && classes[j] < i+width_class ) {
                    frequencies[index]++;
                }
            }
            System.out.println(i + " ~ " + (i + width_class) + " : " + frequencies[index]);
            index++;
        }
    }
}
