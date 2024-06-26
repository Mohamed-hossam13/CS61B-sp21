import java.util.jar.JarEntry;

public class Exercise_4 {

    public static void windowPosSum(int[] a, int n) {
        int SIZE = a.length;

        for (int i = 0; i < SIZE; ++i) {
            //Sum the numbers from the current index to the nth element in the array
            if(a[i] < 0){
                continue;
            }

            int sum = 0;
            for(int j = 0; j <= n; ++j){
                if(i + j >= SIZE)
                    break;

                sum += a[i + j];
            }
            a[i] = sum;
        }
    }

    public static void main (String[] args) {
        int[] a = {1, 2, -3, 4, 5, 4};
        int n = 3;
        windowPosSum(a, n);

        // Should print 4, 8, -3, 13, 9, 4
        System.out.println(java.util.Arrays.toString(a));
    }
}
