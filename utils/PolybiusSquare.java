package utils;

public class PolybiusSquare {
    private char[][] square;

    public PolybiusSquare() {
        initializeSquare();
    }

    private void initializeSquare() {
        square = new char[5][5];
        String alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ"; // Note: 'J' is omitted

        int alphabetIndex = 0;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                square[row][col] = alphabet.charAt(alphabetIndex++);
            }
        }
    }

    public int[] getCoordinates(char letter) {
        // Handling 'J' as 'I'
        if (letter == 'J') {
            letter = 'I';
        }

        int[] coordinates = new int[2];
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (square[row][col] == letter) {
                    coordinates[0] = row;
                    coordinates[1] = col;
                    return coordinates;
                }
            }
        }
        return null; // Letter not found (should not happen with a valid input)
    }

    public char getLetter(int row, int col) {
        return square[row][col];
    }
}