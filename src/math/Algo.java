
package math;

import java.util.Scanner;

public class Algo {
	static public int pow(int x, int n) {
		if (n < 0)
			throw new IllegalArgumentException("power can't be negative");
		int r = 1;
		while (n > 0) {
			r *= x;
			n--;
		}
		return r;
	}

	public static void main(String[] args) {
		PrimeCache cache = new PrimeCache(1000000);
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("-------------");
			System.out.println("Enter a and b");
			System.out.print("a " + "\t");
			int a = scanner.nextInt();
			PrimeArray A = PrimeArray.create(a, cache);
			System.out.println(A);

			System.out.print("b " + "\t");
			int b = scanner.nextInt();
			PrimeArray B = PrimeArray.create(b, cache);
			System.out.println(B);

			int com = A.compareTo(B);
			if (com > 0)
				System.out.println("a > b");
			else if (com < 0)
				System.out.println("a < b");
			else
				System.out.println("a = b");

			System.out.println("a * b " + "\t" + a * b);
			System.out.println(A.multiply(B));

			System.out.println("a | b " + "\t" + (a % b == 0));
			System.out.println(A.divideBy(B));

			System.out.println("a / b " + "\t" + a / b);
			System.out.println(A.divideBy(B));

			System.out.println("a + b " + "\t" + a + b);
			System.out.println(PrimeArray.create(a + b, cache));
		}
	}
}
