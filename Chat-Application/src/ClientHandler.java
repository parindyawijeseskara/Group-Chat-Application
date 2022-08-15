import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {


    //arraylist of every ClientHandler object allowing to communicate or send messages to multiple clients
    public ArrayList<ClientHandler> clientHandlers;
    private final Socket socket; //Socket which is pass from server (used to establish connection between client and server)
    private final BufferedReader bufferedReader; //read data/messages send from the clients
    private final PrintWriter printWriter; //send data specifically messages to client


    //accept the socket which were passed in ClientHandler in Server class & pass it to the constructor
    public ClientHandler(Socket socket, ArrayList<ClientHandler> clientHandlers) throws IOException {
        //to handle input and outPut exceptions
        this.clientHandlers = clientHandlers;
        this.socket = socket;
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.printWriter = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = bufferedReader.readLine()) != null) {

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
                for (ClientHandler clientHandler : clientHandlers) {
                    clientHandler.printWriter.println(message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
                printWriter.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
