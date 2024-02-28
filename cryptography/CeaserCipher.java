package cryptography;
public class CeaserCipher {
    public static String encrypt(String message, int shift) {
        StringBuilder encryptedMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char ch = message.charAt(i);
            if (Character.isLetter(ch)) {
                if (Character.isUpperCase(ch)) {
                    char encryptedChar = (char) ((ch - 'A' + shift) % 26 + 'A');
                    encryptedMessage.append(encryptedChar);
                } else {
                    char encryptedChar = (char) ((ch - 'a' + shift) % 26 + 'a');
                    encryptedMessage.append(encryptedChar);
                }
            } else {
                encryptedMessage.append(ch);
            }
        }
        return encryptedMessage.toString();
    }

    public static String decrypt(String encryptedMessage, int shift) {
        return encrypt(encryptedMessage, 26 - shift);
    }

    public static void main(String[] args) {
        String message = "Hello, World!";
        int shift = 3;
        String encryptedMessage = encrypt(message, shift);
        System.out.println("Encrypted Message: " + encryptedMessage);
        String decryptedMessage = decrypt(encryptedMessage, shift);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}
