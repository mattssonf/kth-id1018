/**
* Code given by teacher to debug
* and correct the mistakes.
* This is the corrected version.
*/
public class Test {

    // The  min  method  returns  the  least  element  in a sequential
    //  collection. If the  collection  is empty  an
    //  IllegalArgumentException  is  thrown.
    public static int min1 (int[] elements)
        throws IllegalArgumentException{
        if (elements.length == 0)
            throw new IllegalArgumentException("empty collection");

        // Is used in trace printing 2:
        // int nofIters = 1;

        int[] sequence = elements;
        int nofPairs = sequence.length / 2;
        int nofUnpairedElements = sequence.length % 2;
        int nofPossibleElements = nofPairs + nofUnpairedElements;
        int[] partialSeq = new int [nofPossibleElements];
        int i = 0;
        int j = 0;
        while (nofPairs > 0){
            //extract a partial sequence of possible elements
            i = 0;
            j = 0;
            while (j < nofPairs){
                partialSeq[j++] = (sequence[i] < sequence[i + 1]) ?
                                    sequence[i] : sequence[i + 1];
                i += 2;
            }
            if (nofUnpairedElements == 1)
                partialSeq[j] = sequence[i];

            //now turn to the partial sequence
            sequence = partialSeq;
            nofPairs = nofPossibleElements / 2;
            nofUnpairedElements = nofPossibleElements % 2;
            nofPossibleElements = nofPairs + nofUnpairedElements;

            // Trace printing 1 - to follow the sequence
            System.out.println(java.util.Arrays.toString (sequence));

            // Trace printing 2 - to terminate the loop preemptively
            // (to be able to see what happens initially)
            // if (nofIters++ == 10)
            //   System.exit (0);
        }

        // sequence[0] is the only remaining possible element
        // - it is the least element
        return sequence[0];
    }
    public static int min (int[] elements){

        int[] sequence = elements;
        int minElement = sequence[0];

        for(int i = 1; i < elements.length; i++)
            if(sequence[i] < minElement)
                minElement = sequence[i];

        return minElement;
    }
}

// Test program
public class TestMin {
    public static void main(String[] args){

        int[] elements = {3,5,8,4,4,2,7,6,8,1,4,3,5,9,12,3,8,0,5};
        System.out.print("Elements: ");
        for(int i = 0; i < elements.length; i++)
            System.out.print(elements[i] + " ");
        System.out.println();
        int min = Test.min(elements);
        System.out.print("\nThe least element: " + min);
    }
}
