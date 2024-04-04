
/** A program to simulate a race between Achilles and Tortoise */
public class AchillesVsTortoise {
    public static void main(String [] args) {
        String a = "Achilles";
        String t = "Tortoise";

        double aPos = 0;
        double tPos = 100;
        double aSpeed = 20;
        double tSpeed = 10;

        double totalTime = 0;

        while (aPos < tPos) {
            System.out.println("At time: " + totalTime);
            System.out.println("    " + a + " is at position " + aPos);
            System.out.println("    " + t + " is at position " + tPos);

            double timeToReach = (tPos - aPos) / aSpeed;
            totalTime += timeToReach;
            aPos += timeToReach * aSpeed;
            tPos += timeToReach * tSpeed;
        }
    }
}
