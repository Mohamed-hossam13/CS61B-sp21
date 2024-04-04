
/** A program to draw a triangle */
public class Exercise_1a {
    public static void main(String[] args) {
        int SIZE = 5;
        int rows = 0;

        while (rows < SIZE) {
            int cols = 0;
            while (cols <= rows) {
                System.out.print("*");
                cols += 1;
            }
            rows += 1;
            System.out.println();
        }
    }
}
