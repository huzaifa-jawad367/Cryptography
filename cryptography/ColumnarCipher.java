package cryptography;

public class ColumnarCipher {

    private String key = "GREAT";
    private String message = "Believe you can and you are halfway there";
    private String cipher;

    public ColumnarCipher(String message, String key) {
        this.key = key;
        this.message = message;
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

    public static void encrypt() {
        
    }
}
