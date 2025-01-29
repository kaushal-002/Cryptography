/* RSA.java */
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

class RSA {
    private BigInteger p, q, n, phi, e, d;
    private int bitLength = 1024;
    private Random r;
    
    public RSA() {
        r = new Random();
        p = BigInteger.probablePrime(bitLength / 2, r);
        q = BigInteger.probablePrime(bitLength / 2, r);
        n = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bitLength / 2, r);
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e = e.add(BigInteger.ONE);
        }
        d = e.modInverse(phi);
    }
    
    public BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }
    
    public BigInteger decrypt(BigInteger encrypted) {
        return encrypted.modPow(d, n);
    }
    
    public static void main(String[] args) {
        RSA rsa = new RSA();
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number to encrypt: ");
        BigInteger message = scanner.nextBigInteger();
        
        BigInteger encrypted = rsa.encrypt(message);
        System.out.println("Encrypted: " + encrypted);
        
        BigInteger decrypted = rsa.decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}
