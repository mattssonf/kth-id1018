/**
* The class must contain several static methods
* that perform various computations on the 
* properties of a triangle.
*/
public class Triangle {

    // The  bisector  method  accepts  two  sides  and  the  angle (in
    //  radians) between  these  sides. The  method  returns  the
    //  length  of the  corresponding  bisector  - the  one  which
    //  splits  the  given  angle  in two  equals  halves.
    public  static  double  bisector(double b, double c, double  alpha){
       double p = 2 * b * c * Math.cos (alpha / 2);
       double  bis = p / (b + c);
       return  bis;
   }
   //computes the radius of the incircle
    public static double incircleRadius(double x, double y, double z){
        double p = (x*y*z)/(x+y+z);
        double root = Math.sqrt(p);
        return root;
    }
    //computes the radius of the circumcircle
    public static double circumcircleRadius(double x, double y, double z){
        double p = (x*y*z)/(2*(x+y+z));
        return p;
    }
    //computes the area of a triangle
    public static double areaTriangle (double b, double h){
        double area = (b*h)/2;
        return area;
    }
}
/**
* The program shall read the lengths of a triangle’s
* three sides and then determine the radius of 
* the circumcircle and the radius of the incircle.
*/
import java.util.Scanner;

public class TriangleAndItsCircles {
    public static void main(String[] args){

        Scanner input = new Scanner(System.in);

        System.out.print("Enter first side of the triangle: ");
        double x = input.nextDouble();
        System.out.print("Enter second side of the triangle: ");
        double y = input.nextDouble();
        System.out.print("Enter third side of the triangle: ");
        double z = input.nextDouble();

        double resultIn = Triangle.incircleRadius(x, y, z);
        double resultCircum = Triangle.circumcircleRadius(x, y, z);
        System.out.println("\nIncircle radius: " +resultIn);
        System.out.println("Circumcircle radius: " +resultCircum);
    }
}
// Test program
import java.util.Scanner;
import static java.lang.System.out;
class TriangleExercises
{
    public static void main (String[] args)
    {
        Scanner in = new Scanner (System.in);
        out.print ("triangle with acute angles - " +
                "length for two sides: ");
        double a = in.nextDouble ();
        double b = in.nextDouble ();
        out.print ("the angle (degrees) between the sides: ");
        double gamma = in.nextDouble ();
        out.println ();
        double c = Math.sqrt (
                Math.pow (a, 2) + Math.pow (b, 2)
                        - 2 * a * b * Math.cos (Math.toRadians (gamma)));
        out.println ("length of the third side: " + c);
        out.println ("perimeter: " + (a + b + c));
        double h = a * Math.sin (Math.toRadians (gamma));
        double area = b * h / 2;
        out.println ("area: " + area);
    }
}
