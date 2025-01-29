/* VigenereCipher.java */
import java.util.Scanner;

class VigenereCipher {
    static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = key.toLowerCase();
        int keyIndex = 0;
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                result.append((char) ((c + key.charAt(keyIndex % key.length()) - 2 * base) % 26 + base));
                keyIndex++;
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
    
    static String decrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = key.toLowerCase();
        int keyIndex = 0;
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                result.append((char) ((c - key.charAt(keyIndex % key.length()) + 26) % 26 + base));
                keyIndex++;
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
        System.out.print("Enter key: ");
        String key = scanner.nextLine();
        
        String encrypted = encrypt(text, key);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypt(encrypted, key));
    }
}
