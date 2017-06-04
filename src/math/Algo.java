
package math;

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
		PrimeCache cache = new PrimeCache(100000);

		System.out.println("pow test");
		for (int i = 0; i < 5; i++)
			System.out.println(i + " : " + pow(5, i));

		System.out.println("cache.getPrime test");
		for (int i = 0; i < 10; i++)
			System.out.println(i + " : " + cache.getPrime(i));

		System.out.println("cache.isTruePrime test");
		for (int i = -5; i < 15; i++)
			System.out.println(i + " : " + cache.isTruePrime(i));

		System.out.println("cache.getNextTruePrime test");
		for (int i = -5; i < 15; i++)
			System.out.println(i + " : " + cache.getNextTruePrime(i));

		System.out.println("cache.decompose test");
		for (int i = 2; i < 15; i++) {
			PrimeArray a = cache.decompose(i);
			System.out.println(i + " : " + a);
			if (a.getValue() != i)
				System.out.println("ERROR : value = " + a.getValue());
		}
		for (int i = 2; i < 15; i++) {
			PrimeArray a = cache.decompose(i);
			System.out.println(i + " : " + a.getExpr());
		}

		System.out.println("multiply test");
		for (int i = 2; i < 10; i++) {
			PrimeArray a = cache.decompose(i);
			for (int j = i; j < 10; j++) {
				PrimeArray b = cache.decompose(j);
				PrimeArray r = a.multiply(b);
				System.out.println(i + "*" + j + " : " + r);
				if (r.getValue() != i * j)
					System.out.println("ERROR : value = " + r.getValue() + " !=" + (i * j));
			}
		}

	}

}
