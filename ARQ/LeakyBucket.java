import java.util.Scanner;

public class LeakyBucket {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int bucketSize, outputRate;
        int stored = 0, resend = 0;

        System.out.print("Bucket size: ");
        bucketSize = sc.nextInt();

        System.out.print("Output rate: ");
        outputRate = sc.nextInt();

        System.out.print("Number of packet batches: ");
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            System.out.print("Incoming packets: ");
            int packets = sc.nextInt();
            
            packets = packets + resend;
            resend = 0;
            
            int space = bucketSize - stored;
            if (packets <= space) {
                stored = stored + packets;
            } else {
                stored = bucketSize;
                resend = packets - space;
                System.out.println("Overflow packets: " + resend);
                System.out.println("Overflow packets will be resent.");
            }

            int transmitted;
            if (stored >= outputRate) {
                transmitted = outputRate;
            } else {
                transmitted = stored;
            }
            
            stored = stored - transmitted;
            System.out.println("Packets transmitted: " + transmitted);
            System.out.println("Packets in bucket: " + stored);

            if (resend > 0) {
                System.out.println("Packets waiting for resend: " + resend);
            }
            System.out.println();
        }

        while (resend > 0) {
            int space = bucketSize - stored;
            if (resend <= space) {
                stored = stored + resend;
                resend = 0;
            } else {
                stored = bucketSize;
                resend = resend - space;
            }

            int transmitted;
            if (stored >= outputRate) {
                transmitted = outputRate;
            } else {
                transmitted = stored;
            }
            
            stored = stored - transmitted;
            System.out.println("Resent packets transmitted: " + transmitted);
        }
        
        sc.close();
    }
}
