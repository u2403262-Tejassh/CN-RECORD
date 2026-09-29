import java.util.Scanner;

public class StopAndWait {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of frames: ");
        int n = sc.nextInt();

        System.out.print("Enter frame to lose (-1 for none): ");
        int lost = sc.nextInt();

        for (int i = 0; i < n; i++) {
            while (true) {
                System.out.println("Sending Frame " + i);

                if (i == lost) {
                    System.out.println("Frame " + i + " lost.");
                    System.out.println("Timeout... Retransmitting Frame " + i);
                    lost = -1;
                } else {
                    System.out.println("ACK received for Frame " + i);
                    break;
                }
            }
        }

        System.out.println("Transmission Complete.");
        sc.close();
    }
}
