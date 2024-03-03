package cryptanalysis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class ColumnarCipher {

    private String key;
    private String message;

    public ColumnarCipher(String message, String key) {
        this.key = key;
        this.message = message;
    }

    public void writeCipherToFile(String cipherText, String filePath) {
        File file = new File(filePath);
        try (FileWriter fr = new FileWriter(file, true); // 'true': append to file 'false': overwrites the file
             BufferedWriter br = new BufferedWriter(fr)) {
            System.out.println("Writing cipher to file");
            br.write(cipherText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This method separates the decryption logic into steps
    public String decrypt(String cipherText) {
        Integer[] keyOrder = getKeyOrder();
        String[][] grid = populateGridWithCipherText(cipherText, keyOrder);
        return readPlaintextFromGrid(grid);
    }

    // This method determines the order of columns based on the key
    private Integer[] getKeyOrder() {
        Integer[] order = new Integer[key.length()];
        char[] keyArray = key.toCharArray();
        Arrays.sort(keyArray);
        for (int i = 0; i < key.length(); i++) {
            for (int j = 0; j < key.length(); j++) {
                if (keyArray[i] == key.charAt(j) && !contains(order, j)) {
                    order[i] = j;
                    break;
                }
            }
        }
        return order;
    }

    // Populate grid with cipher text according to key order
    private String[][] populateGridWithCipherText(String cipherText, Integer[] keyOrder) {
        int cols = key.length();
        int rows = cipherText.length() / cols;
        String[][] grid = new String[rows][cols];
        int index = 0;

        for (int orderIndex : keyOrder) {
            for (int row = 0; row < rows; row++) {
                if (index < cipherText.length()) {
                    grid[row][orderIndex] = String.valueOf(cipherText.charAt(index++));
                }
            }
        }
        return grid;
    }

    // Read the grid row by row to get the plaintext
    private String readPlaintextFromGrid(String[][] grid) {
        StringBuilder plaintext = new StringBuilder();
        for (String[] strings : grid) {
            for (String string : strings) {
                plaintext.append(string);
            }
        }
        return plaintext.toString();
    }

    private boolean contains(final Integer[] array, final int key) {
        return Arrays.asList(array).contains(key);
    }
}