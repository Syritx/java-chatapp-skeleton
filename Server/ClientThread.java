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

        while (client.isConnected()) {
            try {
                InputStreamReader reader = new InputStreamReader(client.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(reader);

                for (Socket s : Server.clientSockets) {
                    if (s != client) {
                        try {
                            PrintWriter printWriter = new PrintWriter(s.getOutputStream());
                            printWriter.write(bufferedReader.readLine());
                        }
                        catch(Exception e) {}
                    }
                }
            }
            catch (IOException e) {}
        }
    }
}
