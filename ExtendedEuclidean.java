/* ExtendedEuclidean.java */
import java.util.Scanner;

class ExtendedEuclidean {
    static int gcdExtended(int a, int b, int[] xy) {
        if (a == 0) {
            xy[0] = 0;
            xy[1] = 1;
            return b;
        }
        int[] temp = new int[2];
        int gcd = gcdExtended(b % a, a, temp);
        xy[0] = temp[1] - (b / a) * temp[0];
        xy[1] = temp[0];
        return gcd;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first number (a): ");
        int a = scanner.nextInt();
        System.out.print("Enter second number (b): ");
        int b = scanner.nextInt();
        
        int[] xy = new int[2];
        int gcd = gcdExtended(a, b, xy);
        
        System.out.println("GCD: " + gcd);
        System.out.println("x: " + xy[0] + ", y: " + xy[1]);
    }
}
