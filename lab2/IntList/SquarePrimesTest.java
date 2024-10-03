package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     * The bug is that the squarePrimes method break the loop after squaring just one prime number
     * So if the list has 4 prime numbers, it will square only the first one
     */
    @Test
    public void testSquarePrimes1() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimes2() {
        IntList lst = IntList.of(4, 13, 8, 10, 5);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4 -> 169 -> 8 -> 10 -> 25", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimes3() {
        IntList lst = IntList.of(5, 13, 20);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("25 -> 169 -> 20", lst.toString());
        assertTrue(changed);
    }

}
