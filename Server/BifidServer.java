package Server;

import java.io.*;
import java.net.*;

import cryptography.BifidCipher;

public class BifidServer {
    public static void main(String[] args) throws IOException {
        int port = 1234;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started. Waiting for connection...");
        Socket socket = serverSocket.accept();
        System.out.println("Connected to client.");

        // Encrypt message and write to file
        BifidCipher cipher = new BifidCipher();
        String message = "In cryptography the smallest mistakes can lead to the biggest secrets";
        String encryptedMessage = cipher.encrypt(message);
        System.out.println("Encrypted Message: " + encryptedMessage);

        PrintWriter out = new PrintWriter("encryptedMessage.txt");
        out.println(encryptedMessage);
        out.close();

        // Send file to client
        File file = new File("encryptedMessage.txt");
        byte[] byteArray = new byte[(int) file.length()];
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        bis.read(byteArray, 0, byteArray.length);
        OutputStream os = socket.getOutputStream();
        System.out.println("Sending file...");
        os.write(byteArray, 0, byteArray.length);
        os.flush();
        socket.close();
        System.out.println("File sent. Server will shut down.");
        serverSocket.close();
    }
}
