import java.io.*;
import java.net.*;

class SMTPServer {
    public static void main(String args[]) {
        try {
            ServerSocket ss = new ServerSocket(2525);

            System.out.println("SMTP Server started...");
            Socket s = ss.accept();

            BufferedReader in = new BufferedReader(
                new InputStreamReader(s.getInputStream()));

            PrintWriter out = new PrintWriter(
                s.getOutputStream(), true);

            out.println("220 SMTP Server Ready");

            String msg;

            while ((msg = in.readLine()) != null) {
                System.out.println("Client: " + msg);

                if (msg.startsWith("HELO")) {
                    out.println("250 Hello");
                }
                else if (msg.startsWith("MAIL FROM")) {
                    out.println("250 Sender OK");
                }
                else if (msg.startsWith("RCPT TO")) {
                    out.println("250 Receiver OK");
                }
                else if (msg.equals("DATA")) {
                    out.println("354 Start mail input");

                    while ((msg = in.readLine()) != null) {
                        if (msg.equals(".")) {
                            break;
                        }

                        System.out.println("Mail: " + msg);
                    }

                    out.println("250 Mail received");
                }
                else if (msg.equals("QUIT")) {
                    out.println("221 Bye");
                    break;
                }
            }

            s.close();
            ss.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}

