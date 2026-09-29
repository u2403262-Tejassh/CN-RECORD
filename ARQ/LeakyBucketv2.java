import java.util.Scanner;

public class LeakyBucketv2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== LEAKY BUCKET CONFIGURATION ===");
        System.out.print("Enter Bucket Size: ");
        int bucketSize = sc.nextInt();

        System.out.print("Enter Output Rate: ");
        int outputRate = sc.nextInt();

        System.out.print("Enter Number of Packet Batches: ");
        int n = sc.nextInt();
        System.out.println("===================================\n");

        int stored = 0, resend = 0;

        for (int i = 1; i <= n; i++) {
            System.out.println("--- BATCH " + i + " ---");
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
                System.out.println("[ALERT] Overflow! " + resend + " packets overflowed and must be resent.");
            }

            int transmitted;
            if (stored >= outputRate) {
                transmitted = outputRate;
            } else {
                transmitted = stored;
            }
            
            stored = stored - transmitted;
            System.out.println("-> Transmitted       : " + transmitted + " packets");
            System.out.println("-> Remaining in Bucket: " + stored + "/" + bucketSize);

            if (resend > 0) {
                System.out.println("-> Waiting for Resend : " + resend + " packets");
            }
            System.out.println();
        }

        if (resend > 0) {
            System.out.println("=== PROCESSING OVERFLOWED PACKETS ===");
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
            System.out.println("-> Resent Transmitted : " + transmitted + " packets");
            System.out.println("-> Remaining in Bucket: " + stored + "/" + bucketSize);
            System.out.println();
        }
        
        System.out.println("=== PROCESS COMPLETED ===");
        sc.close();
    }
}

