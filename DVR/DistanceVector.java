import java.util.Scanner;

public class DistanceVector {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int routers;

        System.out.print("Enter the number of routers: ");
        routers = sc.nextInt();

        int[][] cost = new int[routers][routers];
        int[][] distance = new int[routers][routers];
        int[][] nextHop = new int[routers][routers];

        System.out.println("Enter the Cost Matrix (Use 999 for Infinity):");

        for (int i = 0; i < routers; i++) {
            for (int j = 0; j < routers; j++) {

                cost[i][j] = sc.nextInt();
                distance[i][j] = cost[i][j];

                if (i == j)
                    nextHop[i][j] = i;
                else
                    nextHop[i][j] = j;
            }
        }

        // Distance Vector Algorithm
        for (int k = 0; k < routers; k++) {

            for (int i = 0; i < routers; i++) {

                for (int j = 0; j < routers; j++) {

                    if (distance[i][k] + distance[k][j] < distance[i][j]) {

                        distance[i][j] = distance[i][k] + distance[k][j];
                        nextHop[i][j] = nextHop[i][k];
                    }
                }
            }
        }

        // Display Routing Tables
        for (int i = 0; i < routers; i++) {

            System.out.println("\nRouting Table for Router " + i);

            System.out.println("----------------------------------------");
            System.out.println("Destination\tNext Hop\tCost");

            for (int j = 0; j < routers; j++) {

                System.out.println(j + "\t\t" + nextHop[i][j] + "\t\t" +
                        distance[i][j]);
            }
        }

        sc.close();
    }
}
