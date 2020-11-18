import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {

    Socket client;
    public ClientThread(Socket client) throws IOException {
        this.client = client;
    }

    public void run() {
        // Reads messages from the client
        while (true) {
            try {
                InputStreamReader reader = new InputStreamReader(client.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(reader);
                System.out.println(bufferedReader.readLine());
                Server.sendMessagesToAllClients(client, bufferedReader.readLine());
            }
            catch (IOException e) {}
        }
    }
}
