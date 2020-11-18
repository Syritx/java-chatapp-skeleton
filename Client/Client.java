import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    static ReceiveMessages messagesThread;
    static SendMessageThread sendMessageThread;
    static Socket client;
    public static void main(String[] args) {
        try {
            client = new Socket(InetAddress.getLocalHost(), 5050);
            messagesThread = new ReceiveMessages(client);
            messagesThread.start();

            sendMessageThread = new SendMessageThread();
            sendMessageThread.start();
        }
        catch(IOException e) {}
    }

    public static void getReceivedMessageFromServer(String message) {
        System.out.println(message);
    }

    public static void sendMessage(String message) {
        try {
            PrintStream stream = new PrintStream(client.getOutputStream());
            stream.println(message);
            stream.flush();
            stream.close();
        }
        catch (IOException e) {}
    }
}