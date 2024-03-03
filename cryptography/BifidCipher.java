package cryptography;
import utils.PolybiusSquare;

public class BifidCipher {
    private PolybiusSquare square;

    public BifidCipher() {
        square = new PolybiusSquare();
    }

    public String encrypt(String plaintext) {
        StringBuilder firstPart = new StringBuilder();
        StringBuilder secondPart = new StringBuilder();

        // char[] split_plaintext = plaintext.toUpperCase().replaceAll("[^A-Z]", "").toCharArray();
        // Lets convert the plaintext to coordinates
        for (char c : plaintext.toUpperCase().replaceAll("[^A-Z]", "").toCharArray()) {
            int[] coords = square.getCoordinates(c == 'J' ? 'I' : c);
            firstPart.append(coords[0] + 1);
            secondPart.append(coords[1] + 1);
        }

        // Get the coordinates and convert back to text
        String combined = firstPart.toString() + secondPart.toString();
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < combined.length(); i += 2) {
            int row = Character.getNumericValue(combined.charAt(i)) - 1;
            int col = Character.getNumericValue(combined.charAt(i + 1)) - 1;
            ciphertext.append(square.getLetter(row, col));
        }

        return ciphertext.toString();
    }

    public String decrypt(String ciphertext) {
        StringBuilder combined = new StringBuilder();
        for (char c : ciphertext.toUpperCase().toCharArray()) {
            int[] coords = square.getCoordinates(c);
            combined.append(coords[0] + 1).append(coords[1] + 1);
        }

        String firstPart = combined.substring(0, combined.length() / 2);
        String secondPart = combined.substring(combined.length() / 2);

        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < firstPart.length(); i++) {
            int row = Character.getNumericValue(firstPart.charAt(i)) - 1;
            int col = Character.getNumericValue(secondPart.charAt(i)) - 1;
            plaintext.append(square.getLetter(row, col));
        }

        return plaintext.toString();
    }
}