import java.math.BigInteger;
import java.util.Scanner;

public class RSA {

    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
 
    public static int modInverse(int a, int m) {
        int m0 = m, t, q;
        int x0 = 0, x1 = 1;

        if (m == 1)
            return 0;

        while (a > 1) {
            q = a / m;
            t = m;

            m = a % m;
            a = t;

            t = x0;
            x0 = x1 - q * x0;
            x1 = t;
        }

        if (x1 < 0)
            x1 += m0;

        return x1;
    }

    public static long powerMod(long a, long b, long n) {
        long result = 1;
        a = a % n;

        while (b > 0) {
            if (b % 2 == 1) {
                result = (result * a) % n;
            }
            b = b >> 1;
            a = (a * a) % n;
        }

        return result;
    }

   
    public static String encrypt(String message, int e, int n) {
        StringBuilder encryptedMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            long encryptedChar = powerMod(c, e, n); 
            encryptedMessage.append(encryptedChar).append(" ");  
        }
        return encryptedMessage.toString().trim();
    }

    
    public static String decrypt(String encryptedMessage, int d, int n) {
        StringBuilder decryptedMessage = new StringBuilder();
        String[] encryptedChars = encryptedMessage.split(" ");
        
        for (String encryptedChar : encryptedChars) {
            long c = Long.parseLong(encryptedChar);  
            char decryptedChar = (char) powerMod(c, d, n);
            decryptedMessage.append(decryptedChar); 
        }
        
        return decryptedMessage.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int p, q, n, phi_n, e, d;
        String message, encryptedMessage, decryptedMessage;

        // Input two prime numbers p and q
        System.out.print("Enter two prime numbers p and q: ");
        p = scanner.nextInt();
        q = scanner.nextInt();

       
        n = p * q;
        phi_n = (p - 1) * (q - 1);

        e = 3;
        while (gcd(e, phi_n) != 1) {
            e += 2;
        }

        d = modInverse(e, phi_n);

        System.out.println("Public key: (e = " + e + ", n = " + n + ")");
        System.out.println("Private key: (d = " + d + ", n = " + n + ")");
        System.out.print("Enter a message (word): ");
        scanner.nextLine();  
        message = scanner.nextLine();
        encryptedMessage = encrypt(message, e, n);
        System.out.println("Encrypted message: " + encryptedMessage); 
        decryptedMessage = decrypt(encryptedMessage, d, n);
        System.out.println("Decrypted message: " + decryptedMessage);

        scanner.close();
    }
}
