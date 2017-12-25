//Create a program that can add and subtract using strings

import java.util.*; //Scanner
import static java.lang.System.out;

public class OperationsWithNaturalNumbersGivenAsStrings {
    public  static  void  main (String [] args){
        out.println ("OPERATIONS  ON  NATURAL  NUMBERS " +"IN  CHARACTER  STRINGS");
        //enter two natural numbers
        Scanner in = new Scanner (System.in);
        out.println ("two natural numbers, the first one greater than the second one: ");
        String tal1 = in.next ();
        String tal2 = in.next ();
        out.println ();

        //add the numbers and show the result
        String sum = add (tal1, tal2);
        show (tal1, tal2, sum, '+');

        //subtract the numbers and show the result
        String diff = subtract (tal1, tal2);
        show (tal1, tal2, diff, '-');
    }
    //The add method accepts two natural numbers represented
    //as character strings and returns their sum, as a
    //character string
    public static String add (String num1, String num2) {

        //fills out the shortest string with zeros
        if (num1.length() < num2.length()){
            num1 = setZeros(num1, num2.length()-num1.length());
        }
        else if (num2.length() < num1.length()){
            num2 = setZeros(num2, num1.length()-num2.length());
        }
        int c = 0;
        StringBuilder s = new StringBuilder();

        //turns the characters into integers
        //adds them togheter column by column with the carry
        //returns them in the right order
        for (int i = num1.length() - 1; i >= 0; i--) {
            char c1 = num1.charAt(i);
            int n1 = Character.getNumericValue(c1);
            char c2 = num2.charAt(i);
            int n2 = Character.getNumericValue(c2);

            int m = n1 + n2 + c;

            c = m / 10;
            m = m % 10;
            s.insert(0, m);
        }
        if (c == 1)
            s.insert(0, c);

        return s.toString();
    }
    //The subtract method accepts two natural numbers
    //represented as character strings and returns their
    //difference as a character string
    //The first number is not smaller than the second
    public static String subtract (String num1, String num2) {
        //fills out the shortest string with zeros
        if (num1.length() < num2.length()) {
            num1 = setZeros(num1, num2.length() - num1.length());
        } else if (num2.length() < num1.length()) {
            num2 = setZeros(num2, num1.length() - num2.length());
        }
        int c = 0;
        StringBuilder s = new StringBuilder();

        //turns the characters into integers
        //subtracts them column by column
        //returns them in the right order
        for (int i = num1.length() - 1; i >= 0; i--) {
            char c1 = num1.charAt(i);
            int n1 = Character.getNumericValue(c1);
            char c2 = num2.charAt(i);
            int n2 = Character.getNumericValue(c2);

            int diff = n1 - n2 - c;

            if (diff < 0) {
                c = 1;
                diff += 10;
            } else
                c = 0;
            s.insert(0, diff);
        }
        return s.toString();
    }

    //The show method presents two natural numbers, an
    //operator and he result string.
    public static void show (String num1, String num2, String result, char operator)
    {
        //set an appropriate length on numbers and result
        int len1 = num1.length ();
        int len2 = num2.length();
        int len = result.length();
        int maxLen = Math.max (Math.max (len1, len2), len);
        num1 = setLen (num1, maxLen - len1);
        num2 = setLen (num2, maxLen - len2);
        result = setLen (result, maxLen - len);

        //show the expression
        out.println (" " + num1);
        out.println("" + operator + "" + num2);
        for (int i = 0; i < maxLen + 2; i++)
            out.print ("=");
        out.println();
        out.println(" " + result + "\n");
    }

    //The setLen method prepends the supplied number of
    //spaces at the beginning of a string
    public static String setLen (String s, int nofSpaces)
    {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < nofSpaces; i++)
            sb.insert (0, " ");
        return sb.toString();
    }
    public static String setZeros (String s, int nofZeros)
    {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < nofZeros; i++)
            sb.insert (0, "0");
        return sb.toString();
    }
}
