
public class Exercise_3 {
    public static int forMax(int [] m){
        int SIZE = m.length;
        int max = m[0];

        for(int i = 1; i < SIZE; ++i){
            if(max < m[i]){
                max = m[i];
            }
        }
        return max;
    }

    public static void main (String[] args){
        int [] numbers = new int[] {9, 200, 15, 2, 25, 10, 6};
        int maxValue = forMax(numbers);

        System.out.println(maxValue);
    }
}
