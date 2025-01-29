/* ElGamal.java */
import java.math.BigInteger;
import java.util.Scanner;

class ElGamal {
    private BigInteger p, e1, e2, d;
    
    public ElGamal(BigInteger p, BigInteger e1, BigInteger d) {
        this.p = p;
        this.e1 = e1;
        this.d = d;
        this.e2 = e1.modPow(d, p);
    }
    
    public BigInteger[] encrypt(BigInteger message, BigInteger r) {
        BigInteger c1 = e1.modPow(r, p);
        BigInteger c2 = message.multiply(e2.modPow(r, p)).mod(p);
        return new BigInteger[]{c1, c2};
    }
    
    public BigInteger decrypt(BigInteger c1, BigInteger c2) {
        BigInteger s = c1.modPow(d, p);
        BigInteger sInv = s.modInverse(p);
        return c2.multiply(sInv).mod(p);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter prime number (p): ");
        BigInteger p = scanner.nextBigInteger();
        System.out.print("Enter base (e1): ");
        BigInteger e1 = scanner.nextBigInteger();
        System.out.print("Enter private key (d): ");
        BigInteger d = scanner.nextBigInteger();
        
        ElGamal elGamal = new ElGamal(p, e1, d);
        
        System.out.print("Enter a number to encrypt: ");
        BigInteger message = scanner.nextBigInteger();
        System.out.print("Enter random number (r): ");
        BigInteger r = scanner.nextBigInteger();
        
        BigInteger[] encrypted = elGamal.encrypt(message, r);
        System.out.println("Encrypted: C1=" + encrypted[0] + " C2=" + encrypted[1]);
        
        BigInteger decrypted = elGamal.decrypt(encrypted[0], encrypted[1]);
        System.out.println("Decrypted: " + decrypted);
    }
}
