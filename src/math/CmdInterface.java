package math;

import java.util.Scanner;

public class CmdInterface {
	public static void main(String[] args) {
		PrimeCache cache = new PrimeCache(1000000);
		Scanner scanner = new Scanner(System.in);

		PrimeArray C;
		boolean c;
		try {
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
				System.out.println();

				int com = A.compareTo(B);
				if (com > 0)
					System.out.println("a > b");
				else if (com < 0)
					System.out.println("a < b");
				else
					System.out.println("a = b");
				System.out.println();

				System.out.print("a * b " + "\t");
				C = A.multiply(B);
				System.out.println(C);
				if (C.getValue() == a * b)
					System.out.println("OK");
				System.out.println();

				System.out.print("b | a " + "\t");
				c = B.isDividing(A);
				System.out.println(c);
				if ((a % b == 0) == c)
					System.out.println("OK");
				System.out.println();

				System.out.print("a / b " + "\t");
				C = A.divideBy(B);
				System.out.println(C);
				if ((C == null && a / b == 0) || (C.getValue() == a / b))
					System.out.println("OK");
				System.out.println();

				System.out.print("a + b " + "\t");
				C = PrimeArray.create(a + b, cache);
				System.out.println(C);
				if (C.getValue() == a + b)
					System.out.println("OK");
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
			scanner.close();
		}
	}
}
