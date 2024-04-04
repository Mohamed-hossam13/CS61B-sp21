public class Exercise_1b {

    public static void drawTriangle(int N){
        int rows = 0;

        while (rows < N) {
            int cols = 0;
            while (cols <= rows) {
                System.out.print("*");
                cols += 1;
            }
            rows += 1;
            System.out.println();
        }
    }

    public static void main(String[] args) {
        drawTriangle(10);
    }
}
