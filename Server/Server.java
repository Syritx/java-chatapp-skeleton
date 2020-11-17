import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    static List<ClientThread> maxClients = new ArrayList<ClientThread>();
    static List<Socket> clientSockets = new ArrayList<Socket>();

    static ServerSocket server;
    public static void main(String[] args) throws IOException {
        server = new ServerSocket(5050);
        do {
            Socket client = server.accept();

            clientSockets.add(client);
            PrintStream stream = new PrintStream(client.getOutputStream());
            stream.println("<USER: SERVER> welcome to the chat");

            ClientThread thread = new ClientThread(client);
            maxClients.add(thread);
            thread.start();
        }
        while (true);
    }
}
