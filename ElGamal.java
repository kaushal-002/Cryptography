/* ElGamal.java */
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

class ElGamal {
    private BigInteger p, g, x, y;
    private SecureRandom random;
    
    public ElGamal(int bitLength) {
        random = new SecureRandom();
        p = BigInteger.probablePrime(bitLength, random);
        g = new BigInteger(bitLength, random).mod(p);
        x = new BigInteger(bitLength, random).mod(p);
        y = g.modPow(x, p);
    }
    
    public BigInteger[] encrypt(BigInteger message) {
        BigInteger k = new BigInteger(p.bitLength(), random).mod(p);
        BigInteger c1 = g.modPow(k, p);
        BigInteger c2 = message.multiply(y.modPow(k, p)).mod(p);
        return new BigInteger[]{c1, c2};
    }
    
    public BigInteger decrypt(BigInteger c1, BigInteger c2) {
        BigInteger s = c1.modPow(x, p);
        BigInteger sInv = s.modInverse(p);
        return c2.multiply(sInv).mod(p);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ElGamal elGamal = new ElGamal(512);
        
        System.out.print("Enter a number to encrypt: ");
        BigInteger message = scanner.nextBigInteger();
        
        BigInteger[] encrypted = elGamal.encrypt(message);
        System.out.println("Encrypted: C1=" + encrypted[0] + " C2=" + encrypted[1]);
        
        BigInteger decrypted = elGamal.decrypt(encrypted[0], encrypted[1]);
        System.out.println("Decrypted: " + decrypted);
    }
}
