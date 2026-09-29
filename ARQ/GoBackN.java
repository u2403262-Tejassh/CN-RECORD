import java.util.Scanner;

public class GoBackN {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total frames: ");
        int frames = sc.nextInt();

        System.out.print("Enter window size: ");
        int window = sc.nextInt();

        System.out.print("Enter lost frame (-1 for none): ");
        int lost = sc.nextInt();

        int i = 0;

        while (i < frames) {
            int end = Math.min(i + window, frames);

            System.out.println("\nSending Window:");

            for (int j = i; j < end; j++) {
                System.out.println("Frame " + j);

                if (j == lost) {
                    System.out.println("Frame " + j + " lost.");
                    System.out.println("Go Back to Frame " + j);
                    i = j;
                    lost = -1;
                    break;
                }

                if (j == end - 1) {
                    i = end;
                }
            }
        }

        System.out.println("\nAll Frames Sent Successfully.");
        sc.close();
    }
}
