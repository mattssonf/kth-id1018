//Create object Point
public class Point {

    private String name;
    private int x;
    private int y;

    public Point (Point p){
        this.name = p.getName();
        this.x = p.getX();
        this.y = p.getY();
    }
    public Point (String name, int x, int y){
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName (){
        return name;
    }
    public int getX (){
        return x;
    }
    public int getY (){
        return y;
    }
    public double distance (Point p){
        double distance = Math.sqrt(Math.pow(this.x - p.x, 2)+Math.pow(this.y - p.y, 2));
        return distance;
    }
    public void setX (int x){
        this.x = x;
    }
    public void setY (int y){
        this.y = y;
    }
    public boolean equals (Point p){
        return ((this.name).equals(p.name) && this.x == p.x && this.y == p.y);
    }
    public String toString(){
        String s =  this.name + "(" + this.x + "." + this.y + ")";
        return s;
    }
}

// Class Point text program
import java.io.*;   //PrintWriter

public class PointTest {
    public  static  void  main (String [] args)
    {
        PrintWriter     out = new  PrintWriter (System.out , true);

        // test a constructor  and a transformer
        Point     p1 = new  Point ("A", 3, 4);
        Point     p2 = new  Point ("B", 5, 6);
        out.println (p1 + "     " + p2);

        //test inspectors
        String     n = p1.getName  ();
        int     x = p1.getX  ();
        int     y = p1.getY  ();
        out.println (n + " " + x + " " + y);

        //test a combiner and a comparator
        double     d = p1.distance (p2);
        out.println (d);
        boolean     b = p1.equals (p2);
        out.println (b);

        //test mutators
        p2.setX  (1);
        p2.setY  (2);
        out.println (p2);

        //test another constructor
        Point   p = new Point (p1);
        out.println (p);
    }
}

// Create object polyline
public class Polyline {

    private Point[] vertices;
    private String  colour = "black";
    private int width = 1;

    public Polyline (){
        this.vertices = new Point[0];
    }
    public Polyline (Point[] vertices){
        this.vertices = new Point[vertices.length];
        for (int i = 0; i < vertices.length; i++)
            this.vertices[i] = new Point (vertices[i]);
    }

    public String toString (){
        int i = 0;
        String s = "";

        for (i = 0; i < this.vertices.length; i++) {
            s = s + this.vertices[i] + " ";
        }
        return s;
    }

    public Point[] getVertices () {
        return this.vertices;
    }

    public String getColour () {
        return this.colour;
    }

    public int getWidth () {
        return this.width;
    }

    public void setColour (String colour) {
        this.colour = colour;
    }

    public void setWidth (int width) {
        this.width = width;
    }
    //calculates the length between all the points in an array and adds them
    public double length () {
        double distance = 0;

        for (int i = 1; i < this.vertices.length; i++) {
            distance += this.vertices[i-1].distance(this.vertices[i]);
        }
        return distance;
    }
    //adds a new point to the last position of an array
    public void addLast (Point vertex){
        Point[] h = new Point[this.vertices.length + 1];
        int i = 0;
        for (i = 0; i < this.vertices.length; i++)
            h[i] = this.vertices[i];
        h[i] = new Point (vertex);

        this.vertices = h;
    }

    //first finds the position where we want to put the nex point
    //then copies the points from the array into a new one
    //inserts the new point and copies the rest
    public void addBefore (Point vertex, String vertexName) {
        Point[] h = new Point[this.vertices.length + 1];
        int i = 0;
        int vertexPosition = this.vertices.length;

        for (i = 0; i < this.vertices.length; i++)
            if (this.vertices[i].getName().equals(vertexName)) {
                vertexPosition = i;
            }

        for (i = 0; i < vertexPosition; i++)
            h[i] = this.vertices[i];
        h[i] = new Point(vertex);

        for (i = vertexPosition + 1; i < h.length; i++)
            h[i] = this.vertices[i - 1];

        this.vertices = h;
    }

    //finds the point to remove, then copies the points into a new array
    //skips the point and copies the rest
    public void remove (String vertexName) {
        Point[] h = new Point[this.vertices.length - 1];
        int i = 0;
        int vertexPosition = this.vertices.length;

        for (i = 0; i < this.vertices.length; i++)
            if (this.vertices[i].getName().equals(vertexName)) {
                vertexPosition = i;
            }

        for (i = 0; i < vertexPosition; i++)
            h[i] = this.vertices[i];

        for (i = vertexPosition; i < h.length; i++)
            h[i] = this.vertices[i + 1];

        this.vertices = h;
    }

    public class PolylineIterator {

        private int current = -1;

        public PolylineIterator (){
            if (Polyline.this.vertices.length > 0)
                current = 0;
        }
        public boolean hasVertex (){
            return current != -1;
        }
        public Point vertex ()
                throws java.util.NoSuchElementException{
            if (!this.hasVertex())
                throw new java.util.NoSuchElementException("end of iteration");
            Point vertex = Polyline.this.vertices[current];

            return vertex;
        }
        public void advance (){
            if (current >= 0 && current < Polyline.this.vertices.length - 1)
                current++;
            else
                current = -1;
        }
    }
}

// Class polyline test program
import java.io.*;   //PrintWriter

public class PolylineTest {
    public  static  void  main (String [] args)
    {
        PrintWriter     out = new  PrintWriter (System.out , true);

        // test a constructor  and a transformer
        Point     p1 = new  Point ("A", 5, 6);
        Point     p2 = new  Point ("B", 3, 7);
        Point     p3 = new  Point ("C", 4, 1);
        Point     p4 = new  Point ("D", -5, 9);
        Point     p5 = new  Point ("E", 1, -6);
        Point[] polyline = {p1, p2, p3, p4, p5};
        Polyline pol = new Polyline(polyline);

        out.println (pol);

        //test inspectors
        String     c = pol.getColour();
        int     w = pol.getWidth();
        Point[] v = pol.getVertices();

        for (int i = 0; i < polyline.length; i++) {
            System.out.print(polyline[i] + " ");
        }
        out.println(" ");
        out.println ( c + " " + w + " ");

        //test mutators
        pol.setColour  ("Red");
        pol.setWidth  (5);
        out.println (pol.getColour() + " " + pol.getWidth());

        //test length
        double     len = pol.length();
        System.out.println(len);

        //test addLast
        Point lastPoint = new Point("F", 99, 99);
        pol.addLast(lastPoint);
        System.out.println(pol);

        //test addBefore
        Point somePoint = new Point("G", 100, 100);
        pol.addBefore(somePoint, "D");
        System.out.println(pol);

        //test remove
        pol.remove("D");
        System.out.println(pol);

        //test iterator
        Polyline.PolylineIterator iterator = pol.new PolylineIterator();

        int i = 1;
        while (iterator.hasVertex()){
            System.out.print("Iteration " + i + ": ");
            System.out.println(iterator.vertex());
            iterator.advance();
            i++;
        }
    }
}

// Create a program that selects the shortest yellow polyline
// from  a randomly created collection of polylines
import java.util.Random;

public class SelectPolyline {

    public static final Random rand = new Random();
    public static final int NOF_POLYLINES = 10;

    public static void main (String[] args){

        //create a random number of polylines
        Polyline[] polylines = new Polyline[NOF_POLYLINES];
        for (int i = 0; i < NOF_POLYLINES; i++)
            polylines[i] = randomPolyline();

        //show the polyline
        for(int i = 0; i < polylines.length; i++)
            System.out.println("Polyline " + (i+1) + ": " + polylines[i] + polylines[i].getColour());
        System.out.println();

        //determine the shortest yellow polyline
        int i = 0;
        boolean yellow = false;
        int nofYellow = 0;
        int n = 0;
        Polyline minYellow = null;

        for (i = 0; i < polylines.length; i++)
            if (polylines[i].getColour().equals("Yellow")) {
                yellow = true;
                break;
            }
        if (yellow){
            for (i = 0; i < polylines.length; i++)
                if (polylines[i].getColour().equals("Yellow"))
                    nofYellow += 1;

            Polyline[] yellowPolylines = new Polyline[nofYellow];
            for (i = 0; i < polylines.length; i++)
                if (polylines[i].getColour().equals("Yellow")) {
                    yellowPolylines[n] = polylines[i];
                    n++;
                }
            minYellow = yellowPolylines[0];
            for (i = 1; i < yellowPolylines.length; i++)
                if (yellowPolylines[i].length() < minYellow.length())
                    minYellow = yellowPolylines[i];

            //show the selected polyline
            System.out.println("The shortest yellow polyline: " + minYellow );
            System.out.println("Colour: " + minYellow.getColour());
            System.out.println("Length: " + minYellow.length());
        }
        else {
            System.out.println("There is no yellow polyline");
        }

    }

    //the randomPoint method returns a new Point with a name
    //randomly chosen from the single letters A-Z. Coordinates
    //are random.
    public static Point randomPoint (){

        String n = "" + (char)(65 + rand.nextInt (26));
        int x = rand.nextInt (11);
        int y = rand.nextInt (11);

        return new Point (n, x, y);
    }

    //the method randomPolyline returns a random polyline,
    //with a colour either blue, red or yellow. The names
    //of the vertices are single letters from the set A--Z
    //two vertices can not have the same name
    public static Polyline randomPolyline(){

        //Create and empty polyline and add vertices
        Polyline polyline = new Polyline();
        int nofVertices = 2 + rand.nextInt (7);
        int nofSelectedVertices = 0;
        boolean[] selectedNames = new boolean[26];

        //two vertices can not have the same name
        Point chosenPoint = null;
        char chosenChar = 0;
        while (nofSelectedVertices < nofVertices){
            chosenPoint = randomPoint();
            chosenChar = chosenPoint.getName().charAt(0);
            if(!selectedNames[chosenChar - 65]) {
                polyline.addLast(chosenPoint);
                selectedNames[chosenChar-65] = true;
                nofSelectedVertices++;
            }
        }
        //assign a colour
        String c = randomColour();
        polyline.setColour(c);

        return polyline;
    }

    public static String randomColour (){
        String [] colour = {"Red", "Blue", "Yellow"};
        String s = colour[rand.nextInt(3)];
        return s;
    }
}
