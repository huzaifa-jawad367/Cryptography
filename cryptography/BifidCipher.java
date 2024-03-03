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

        // Convert plaintext to coordinates
        for (char c : plaintext.toUpperCase().replaceAll("[^A-Z]", "").toCharArray()) {
            int[] coords = square.getCoordinates(c == 'J' ? 'I' : c);
            firstPart.append(coords[0] + 1);
            secondPart.append(coords[1] + 1);
        }

        // Combine the coordinates and convert back to text
        String combined = firstPart.toString() + secondPart.toString();
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < combined.length(); i += 2) {
            int row = Character.getNumericValue(combined.charAt(i)) - 1;
            int col = Character.getNumericValue(combined.charAt(i + 1)) - 1;
            ciphertext.append(square.getLetter(row, col));
        }

        return ciphertext.toString();
    }
}