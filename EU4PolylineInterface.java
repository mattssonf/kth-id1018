// Create the interface polyline
public interface Polyline extends java.lang.Iterable<Point> {

    Point[] getVertices();

    String getColour();

    int getWidth();

    double length();

    void setColour (String colour);

    void setWidth (int width);

    void add (Point vertex);

    void insertBefore (Point vertex, String vertexName);

    void remove (String vertexName);

    java.util.Iterator<Point> iterator();
}

// Create a new class NPolyline, using class Point from OU5
// The points in the polyline are connected with linked nodes
import java.util.ArrayList;
import java.util.Iterator;

public class NPolyline implements Polyline {

    private static class Node{
        public Point vertex;
        public Node nextNode;

        public Node (Point vertex){
            this.vertex = vertex;
            nextNode = null;
        }
    }
    private Node vertices;
    private  String colour = "black";
    private int width = 1; // pixels

    public NPolyline (){
        this.vertices = null;
    }
    public NPolyline (Point[] vertices){
        if(vertices.length > 0){
            Node node = new Node (new Point(vertices[0]));
            this.vertices = node;
            int pos = 1;
            while(pos < vertices.length){
                node.nextNode = new Node (new Point (vertices[pos++]));
                node = node.nextNode;
            }
        }
    }

    public Point[] getVertices(){
        NPolyline.NPolylineIterator nIterator = this.new NPolylineIterator();
        ArrayList<Point> verticesList = new ArrayList<Point>();

        while(nIterator.hasNext()) {
            verticesList.add(nIterator.next());
        }
        Point[] answer = verticesList.toArray(new Point[verticesList.size()]);

        return answer;
    }

    public String getColour(){
        return this.colour;
    }
    public int getWidth(){
        return this.width;
    }
    public void setColour (String colour){
        this.colour = colour;
    }
    public void setWidth (int width){
        this.width = width;
    }

    public double length(){
        NPolyline.NPolylineIterator nIterator = this.new NPolylineIterator();
        Point previous = nIterator.next();
        Point current;
        double distance = 0;

        while(nIterator.hasNext()){
            current = nIterator.next();
            distance += previous.distance(current);
            previous = current;
        }
        return distance;
    }

    public void add(Point vertex){
        NPolyline.NPolylineIterator nIterator = this.new NPolylineIterator();
        Node lastNode = null;
        Node addNode = new Node(vertex);
        if(vertices == null){
            vertices = new Node(vertex);
        }
        else {
            while (nIterator.hasNext()) {
                lastNode = nIterator.nextN();
            }
            lastNode.nextNode = addNode;
        }
    }

    public void insertBefore(Point vertex, String vertexName){
        NPolyline.NPolylineIterator nIterator = this.new NPolylineIterator();
        Node addNode = new Node(vertex);
        Node nextNd;
        Node current;
        boolean found = false;

        if(vertices == null) {
            vertices = new Node(vertex);
        }
        else{
            current = nIterator.nextN();

            while(nIterator.hasNext() && !found){
                nextNd = nIterator.nextN();

                if(vertices.vertex.getName().equals(vertexName)){
                    addNode.nextNode = vertices;
                    vertices = addNode;
                    found = true;
                }
                else if(nextNd.vertex.getName().equals(vertexName)){
                    current.nextNode = addNode;
                    addNode.nextNode = nextNd;
                    found = true;
                }
                else
                    current = nextNd;
            }
        }
    }

    public void remove(String vertexName){
        NPolyline.NPolylineIterator nIterator = this.new NPolylineIterator();
        Node previous;
        Node current;
        boolean found = false;

        if(vertices == null) {
            System.out.print("The polyline is empty");
        }
        else {
            previous = nIterator.nextN();
            while (nIterator.hasNext() && !found) {
                current = nIterator.nextN();

                if (previous.vertex.getName().equals(vertexName)) {
                        vertices = vertices.nextNode;
                    found = true;
                }
                else if (current.vertex.getName().equals(vertexName)) {
                    if (nIterator.hasNext()) {
                        previous.nextNode = nIterator.nextN();
                        found = true;
                    } else {
                        previous.nextNode = null;
                        found = true;
                    }
                }
                else
                    previous = current;
            }
        }
    }

    public String toString(){
        NPolyline.NPolylineIterator nIterator = this.new NPolylineIterator();
        String s = "";

        while(nIterator.hasNext())
            s += nIterator.next().toString() + " ";

        return s;
    }

    public Iterator<Point> iterator(){
        return new NPolylineIterator();
    }
    public class NPolylineIterator implements Iterator<Point>{

        private Node firstNode = new Node (new Point("O", 0, 0));
        private Node currentNode;

        public NPolylineIterator (){
            firstNode.nextNode = vertices;
            currentNode = firstNode;
        }

        public boolean hasNext (){
            boolean x = false;
            if(currentNode.nextNode != null)
                x = true;

            return x;
        }

        public Point next ()
                throws java.util.NoSuchElementException{
            Point vertex = null;
            if (!this.hasNext())
                throw new java.util.NoSuchElementException("end of iteration");
            else
              currentNode = currentNode.nextNode;

            return currentNode.vertex;
        }

        public Node nextN ()
                throws java.util.NoSuchElementException{
            Node n = null;
            if (!this.hasNext())
                throw new java.util.NoSuchElementException("end of iteration");
            else
                currentNode = currentNode.nextNode;

            return currentNode;
        }
    }
}

//Create a test program
public class TestPolylines {

    public static void main(String[] args){

        Polyline polyline = null;
        polyline = new NPolyline();

        polyline.add(new Point("A",1,1));
        polyline.add(new Point("B",2,2));
        polyline.add(new Point("C",3,3));
        polyline.add(new Point("D",4,4));

        System.out.println("Polyline: " + polyline);

        polyline.insertBefore(new Point("N",99,99),"A");
        System.out.println("Insert N: " + polyline);

        polyline.remove("C");
        System.out.println("Remove C: " + polyline);

        System.out.println("\nLenght: " + polyline.length());
        polyline.setColour("Red");
        polyline.setWidth(5);
        System.out.println("Colour: " + polyline.getColour());
        System.out.println("Width: " + polyline.getWidth());
        System.out.println();

        for (Point vertex : polyline)
            System.out.println (vertex );
    }
}
