public class Main {
    public static void main(String[] args) {
        String message = "Believe you can and you are halfway there";
        String key = "GREAT";
        cryptography.ColumnarCipher cc = new cryptography.ColumnarCipher(message, key);
        String[][] grid = cc.GridFormation(message);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
