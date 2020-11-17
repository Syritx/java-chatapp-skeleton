import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiveMessages extends Thread {
    Socket client;

    public ReceiveMessages(Socket client) {
        System.out.println("receive messages loaded");
        this.client = client;
    }

    public void run() {
        while (true) {
            try {
                InputStreamReader reader = new InputStreamReader(client.getInputStream());
                BufferedReader r = new BufferedReader(reader);

                Client.getReceivedMessageFromServer(r.readLine());
            }
            catch (IOException e) {}
        }
    }
}
