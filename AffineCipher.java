/* AffineCipher.java */
import java.util.Scanner;
class AffineCipher {
    static int modInverse(int a, int m) {
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) return x;
        }
        return -1;
    }
    static String encrypt(String text, int a, int b) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                result.append((char) (((a * (c - base) + b) % 26) + base));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
    static String decrypt(String text, int a, int b) {
        int a_inv = modInverse(a, 26);
        if (a_inv == -1) return "Invalid key";
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                result.append((char) (((a_inv * ((c - base - b + 26)) % 26)) + base));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = scanner.nextLine();
        System.out.print("Enter key a (must be coprime with 26): ");
        int a = scanner.nextInt();
        System.out.print("Enter key b: ");
        int b = scanner.nextInt();
        
        String encrypted = encrypt(text, a, b);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypt(encrypted, a, b));
    }
}
