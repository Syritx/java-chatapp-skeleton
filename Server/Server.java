import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    static List<Thread> maxClients = new ArrayList<Thread>();
    static List<Socket> clientSockets = new ArrayList<Socket>();

    static ServerSocket server;
    static int clientID = 1;

    public void runServer() {
        try {
            server = new ServerSocket(5050);
        }
        catch (IOException e) {}

        do {
            try {
                Socket client = server.accept();
                System.out.println("new client has joined the server");

                clientSockets.add(client);
                PrintStream stream = new PrintStream(client.getOutputStream());
                stream.println("<USER: SERVER> welcome to the chat");

                Thread thread = new ClientThread(client);
                thread.start();
            }
            catch (IOException e) {}
        }
        while (true);
    }

    public static void sendMessagesToAllClients(Socket clientToIgnore, String message) {
        System.out.println("sending");
        for (Socket s : clientSockets) {
            if (s != clientToIgnore) {
                PrintStream stream;
                try {
                    stream = new PrintStream(s.getOutputStream());
                    stream.println(message);
                } 
                catch (IOException e) {}
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Server().runServer();
    }
}
