import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class SendMessageThread extends Thread {
    
    public void run() {
        System.out.println("send messages loaded");

        while (true) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                Client.sendMessage(reader.readLine());
            } 
            catch (IOException e) {}
        }
    }
}
