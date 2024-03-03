package cryptanalysis;

import utils.PolybiusSquare;

public class BifidCipher {

    private PolybiusSquare square;

    public BifidCipher() {
        square = new PolybiusSquare();
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
