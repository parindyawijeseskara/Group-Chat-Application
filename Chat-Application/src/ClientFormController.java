import javafx.event.ActionEvent;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientFormController extends Thread {
    public TextArea clientTextArea;
    public TextField clientMessage;
    Socket socket;
    BufferedReader bufferedReader;
    PrintWriter printWriter;

    public void initialize() {
        System.out.println(LoginFormController.name);

        connectSocket();


    }

    private void connectSocket() {
        try {
            socket = new Socket("localhost", 8000);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            while (true) {
                String message = bufferedReader.readLine();
                String[] split = message.split(" ");
                String userName = split[0];
                StringBuilder msg = new StringBuilder();
                for (int i = 1; i < split.length; i++) {
                    msg.append(split[i]);
                }
                if(userName.equalsIgnoreCase(LoginFormController.name+":")){
                    continue;
                }else if(msg.toString().equalsIgnoreCase("bye")){
                    break;
                }
                clientTextArea.appendText(message+"\n");
            }
            bufferedReader.close();
            printWriter.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clientSendOnAction(ActionEvent actionEvent) {
        sends();
    }

    private void sends() {
        String text = clientMessage.getText();
        printWriter.println(LoginFormController.name+": "+text);
        clientTextArea.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        clientTextArea.appendText("Me: "+text+"\n");
        clientMessage.setText("");
        if (text.equalsIgnoreCase("bye") || text.equalsIgnoreCase("Log Out")){
            System.exit(0);
        }

    }
}
