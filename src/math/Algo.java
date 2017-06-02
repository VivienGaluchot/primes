
package math;

public class Algo {
	static private int maxNbPrimes = 1000000;
	static private int[] primes = new int[maxNbPrimes];
	static private int computedPrimes = 0;

	static private void computeOneMorePrime() {
		int p = 0;
		if (computedPrimes > 0)
			p = primes[computedPrimes - 1];
		boolean found = false;
		while (!found && p < Integer.MAX_VALUE - 1) {
			p++;
			found = true;
			for (int i = 1; i < computedPrimes; i++) {
				if (p % primes[i] == 0) {
					found = false;
					break;
				}
			}
		}
		if (found == false)
			throw new IllegalArgumentException("Can't find any more prime in int");
		primes[computedPrimes] = p;
		computedPrimes++;
	}

	static public int getPrime(int i) {
		if (i > maxNbPrimes || i < 0)
			throw new IllegalArgumentException("Out of bounds prime");
		while (i >= computedPrimes)
			computeOneMorePrime();
		return primes[i];
	}

	static int primeId(int x) {
		int i = 0;
		while (getPrime(i) < x)
			i++;
		if (x == getPrime(i))
			return i;
		else
			return -1;
	}

	static boolean isTruePrime(int x) {
		int i = 0;
		while (getPrime(i) < x)
			i++;
		return x == getPrime(i);
	}

	static int nextTruePrime(int x) {
		int i = 0;
		while (getPrime(i) <= x)
			i++;
		return getPrime(i);
	}

	static PrimeArray decompose(int n) {
		if (n <= 0)
			throw new IllegalArgumentException("power can't be negative");

		int[] primesFactors = new int[maxNbPrimes];
		int size = 0;

		if (n == 1) {
			primesFactors[0] = 1;
			return new PrimeArray(primesFactors, 1);
		}

		// for each prime, test if his powers divide n
		for (int i = 1; getPrime(i) <= n; i++) {
			primesFactors[i] = 0;
			while (n % pow(getPrime(i), primesFactors[i] + 1) == 0) {
				primesFactors[i] = primesFactors[i] + 1;
				size = i + 1;
			}
		}
		return new PrimeArray(primesFactors, size);
	}

	static int pow(int x, int n) {
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
		System.out.println("pow test");
		for (int i = 0; i < 5; i++)
			System.out.println(i + " : " + pow(5, i));

		System.out.println("getPrime test");
		for (int i = 0; i < 10; i++)
			System.out.println(i + " : " + getPrime(i));

		System.out.println("isTruePrime test");
		for (int i = -5; i < 15; i++)
			System.out.println(i + " : " + isTruePrime(i));

		System.out.println("nextTruePrime test");
		for (int i = -5; i < 15; i++)
			System.out.println(i + " : " + nextTruePrime(i));

		System.out.println("decompose test");
		for (int i = 1; i < 15; i++) {
			PrimeArray a = decompose(i);
			System.out.println(i + " : " + a + " = " + a.getValue());
		}

		System.out.println("multiply test");
		for (int i = 1; i < 10; i++) {
			PrimeArray a = decompose(i);
			for (int j = i; j < 10; j++) {
				PrimeArray b = decompose(j);
				PrimeArray r = a.multiply(b);
				System.out.println(i + "*" + j + " " + (i * j) + " : " + r + " = " + r.getValue());
			}
		}
	}

}
