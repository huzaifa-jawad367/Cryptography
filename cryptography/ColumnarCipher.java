package cryptography;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ColumnarCipher {

    private String key = "GREAT";
    private String message;
    private String cipher;

    public ColumnarCipher(String message, String key) {
        this.key = key;
        this.message = message;
    }

    public void writeCipherToFile(String cipherText, String filePath) {
        File file = new File(filePath);
        FileWriter fr = null;
        BufferedWriter br = null;
        try {
            fr = new FileWriter(file, true);  // 'true' will append to file, 'false' (default) overwrites the file
            br = new BufferedWriter(fr);
            br.write(cipherText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String[][] GridFormation(String message) {
        int cols = key.length();
        int rows = message.length() / key.length() + 1;
        String[][] grid = new String[rows][cols];
        int index = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (index < message.length()) {
                    grid[i][j] = message.charAt(index) + "";
                    index++;
                } else {
                    grid[i][j] = " ";
                
                }
            }
        }

        return grid;
    } 

    public String[][] sortGrid(String[][] grid) {
        String[][] sortedGrid = new String[grid.length][grid[0].length];
        int[] keyOrder = new int[key.length()];
        for (int i = 0; i < key.length(); i++) {
            keyOrder[i] = key.charAt(i);
        }
        for (int i = 0; i < keyOrder.length; i++) {
            for (int j = i + 1; j < keyOrder.length; j++) {
                if (keyOrder[i] > keyOrder[j]) {
                    int temp = keyOrder[i];
                    keyOrder[i] = keyOrder[j];
                    keyOrder[j] = temp;
                }
            }
        }
        for (int i = 0; i < key.length(); i++) {
            for (int j = 0; j < key.length(); j++) {
                if (keyOrder[i] == key.charAt(j)) {
                    for (int k = 0; k < grid.length; k++) {
                        sortedGrid[k][i] = grid[k][j];
                    }
                }
            }
        }
        return sortedGrid;
    }

    public String toCipherText(String[][] sortedGrid) {
        String cipher = "";
        for (int i = 0; i < sortedGrid.length; i++) {
            for (int j = 0; j < sortedGrid[i].length; j++) {
                cipher += sortedGrid[j][i];
            }
        }

        return cipher;
    }


}
