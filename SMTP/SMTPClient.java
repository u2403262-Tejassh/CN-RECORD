import java.io.*;
import java.net.*;

class SMTPClient {
    public static void main(String args[]) {
        try {
            Socket s = new Socket("localhost", 2525);

            BufferedReader in = new BufferedReader(
                new InputStreamReader(s.getInputStream()));

            PrintWriter out = new PrintWriter(
                s.getOutputStream(), true);

            System.out.println("Server: " + in.readLine());

            out.println("HELO localhost");
            System.out.println("Server: " + in.readLine());

            out.println("MAIL FROM:<sender@example.com>");
            System.out.println("Server: " + in.readLine());

            out.println("RCPT TO:<receiver@example.com>");
            System.out.println("Server: " + in.readLine());

            out.println("DATA");
            System.out.println("Server: " + in.readLine());

            out.println("Subject: Test Mail");
            out.println("From: sender@example.com");
            out.println("To: receiver@example.com");
            out.println();

            out.println("This is a test email.");
            out.println(".");

            System.out.println("Server: " + in.readLine());

            out.println("QUIT");
            System.out.println("Server: " + in.readLine());

            s.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
