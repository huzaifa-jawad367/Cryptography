package Client;

import java.io.*;
import java.net.*;

import cryptography.BifidCipher;

public class BifidClient {
    public static void main(String[] args) throws IOException {
        int port = 1234; // Same port as the server
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address, port);
        System.out.println("Connected to server.");

        // Receive file from server
        byte[] byteArray = new byte[1024];
        InputStream is = socket.getInputStream();
        FileOutputStream fos = new FileOutputStream("receivedEncryptedMessage.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        int bytesRead = is.read(byteArray, 0, byteArray.length);
        bos.write(byteArray, 0, bytesRead);
        bos.close();
        socket.close();
        System.out.println("File received.");

        // Read encrypted message from file and decrypt
        BufferedReader br = new BufferedReader(new FileReader("receivedEncryptedMessage.txt"));
        String encryptedMessage = br.readLine();
        br.close();

        BifidCipher cipher = new BifidCipher();
        String decryptedMessage = cipher.decrypt(encryptedMessage);
        System.out.println("Encrypted Message: " + encryptedMessage);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}

