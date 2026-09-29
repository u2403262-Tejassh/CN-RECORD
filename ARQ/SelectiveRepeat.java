import java.util.Scanner;

public class SelectiveRepeat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total frames: ");
        int frames = sc.nextInt();

        System.out.print("Enter window size: ");
        int window = sc.nextInt();

        System.out.print("Enter lost frame (-1 for none): ");
        int lost = sc.nextInt();

        boolean[] ack = new boolean[frames];

        for (int i = 0; i < frames; i += window) {
            int end = Math.min(i + window, frames);

            System.out.println("\nSending Window:");

            for (int j = i; j < end; j++) {
                if (j == lost) {
                    System.out.println("Frame " + j + " lost.");
                } else {
                    System.out.println("Frame " + j + " acknowledged.");
                    ack[j] = true;
                }
            }

            if (lost >= i && lost < end) {
                System.out.println("Retransmitting Frame " + lost);
                ack[lost] = true;
                System.out.println("ACK received for Frame " + lost);
                lost = -1;
            }
        }

        System.out.println("\nAll Frames Successfully Received.");
        sc.close();
    }
}
