public class TheShortestPath {
    // The  method  intermediateStations  returns a vector  of the
    // intermediate  stations  that  are on the  shortest  path.
    // The  ordinal  number  of the  first  station  is  located  at
    // index 0 of the  array , and  the  second  station  at index 2.
    double a[] = {2, 4, 6};
    double b[][] = {{1, 3, 2, 1}, {5, 2, 1, 3}, {3, 4, 1, 2}};
    double c[] = {2, 3, 2, 1};

    // returns an array of the intermediate stations on the shortest path
    public static double[] intermediateStations(double[] a, double[][] b, double[] c) {
        
        double currentMinDistance = a[0] + b[0][0] + c[0];
        double currentDistance = 0;
        double[] station = new double[2];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < c.length; j++) {
                currentDistance = a[i] + b[i][j] + c[j];

                if (currentMinDistance > currentDistance) {
                    currentMinDistance = currentDistance;
                    station[0] = i;
                    station[1] = j;
                }
            }
        }
        return station;
    }

    // The  method  length  returns  the  length  of the  shortest  path.
    public static double length(double[] a, double[][] b, double[] c) {

        double[] d = new double[a.length * b.length];
        double minLength = a[0] + b[0][0] + c[0];
        double currentLength = 0;

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < c.length; j++) {
                currentLength = a[i] + b[i][j] + c[j];

                if (minLength > currentLength) {
                    minLength = currentLength;
                }
            }
        }
        return minLength;
    }
}

// Test program to determine the shortest path
import java.util.Scanner;

public class DetermineTheShortestPath {
    public static void main(String[] args){

        Scanner input = new Scanner(System.in);

        System.out.print("Enter number of U stations: ");
        int m = input.nextInt();
        double [] a = new double[m];

        System.out.print("Enter number of V stations: ");
        int n = input.nextInt();
        double [] c = new double [n];
        double [][] b = new double[m][n];

        System.out.println("Enter the path lengths from X to stations U: ");
        for(int i = 0; i < m; i++){
            a[i] = input.nextDouble();
        }
        System.out.println("Enter the path lengths from stations U to V: ");
        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++){
            b[i][j] = input.nextDouble();
            }
        System.out.println("Enter the path lengths from stations V to Y");
        for(int j = 0; j < n; j++){
            c[j] = input.nextDouble();
        }

        double [] shortestPath = TheShortestPath.intermediateStations(a, b, c);
        double pathLength = TheShortestPath.length(a, b, c);

        System.out.println("\nThe shortest path goes through station U"+shortestPath[0]+" and V"+shortestPath[1]+".");
        System.out.println("The length of the shortest path is "+pathLength);
    }
}
