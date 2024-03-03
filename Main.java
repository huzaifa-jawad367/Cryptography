public class Main {
    public static void main(String[] args) {
        String message = "Believe you can and you are halfway there";
        String key = "GREAT";
        cryptography.ColumnarCipher cc = new cryptography.ColumnarCipher(message, key);
        cc.encrypt(message, key);
    }
}
