import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static ArrayList<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args){
        ServerSocket serverSocket;
        Socket socket;

        try {
            serverSocket = new ServerSocket(8000);

            while (true){
                System.out.println("Waiting");
                socket = serverSocket.accept();
                System.out.println("Connected");
                ClientHandler clientHandler = new ClientHandler(socket,clients);
                clients.add(clientHandler);
                clientHandler.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
