package utils;

public class PolybiusSquare {
    private char[][] square;

    public PolybiusSquare() {
        initializeSquare();
    }

    private void initializeSquare() {
        square = new char[5][5];
        String alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ";

        int alphabetIndex = 0;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                square[row][col] = alphabet.charAt(alphabetIndex++);
            }
        }
    }

    public int[] getCoordinates(char letter) {
        // J will be equated to I in terms of coordinates
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
        return null;
    }

    public char getLetter(int row, int col) {
        return square[row][col];
    }
}