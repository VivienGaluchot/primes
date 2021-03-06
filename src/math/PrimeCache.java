package math;

public class PrimeCache {
	private int maxNbPrimes;
	private int[] primes;
	private int computedPrimes;

	public PrimeCache(int maxNbPrimes) {
		this.maxNbPrimes = maxNbPrimes;
		this.primes = new int[maxNbPrimes];
		this.computedPrimes = 0;
	}

	private void computeOneMorePrime() {
		if (computedPrimes == maxNbPrimes)
			throw new IllegalArgumentException("maxNbPrimes reached");

		int p = 0;
		if (computedPrimes > 0)
			p = primes[computedPrimes - 1];
		else
			p = 1;

		boolean found = false;
		while (!found && p < Integer.MAX_VALUE - 1) {
			p++;
			found = true;
			for (int i = 0; i < computedPrimes; i++) {
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

	public int getPrime(int i) {
		if (i > maxNbPrimes || i < 0)
			throw new IllegalArgumentException("Out of bounds prime");
		while (i >= computedPrimes)
			computeOneMorePrime();
		return primes[i];
	}

	int getNextTruePrime(int x) {
		int i = getIdNextOrEqualPrime(x);
		return x < getPrime(i) ? getPrime(i) : getPrime(i + 1);
	}

	boolean isTruePrime(int x) {
		if (x < getPrime(0))
			return false;
		for (int i = 0; getPrime(i) <= x / 2; i++) {
			if (x % getPrime(i) == 0)
				return false;
		}
		return true;
	}

	int getIdNextOrEqualPrime(int x) {
		int i = 0;
		int j = computedPrimes - 1;

		if (j < 0 || getPrime(j) < x) {
			while (j < 0 || getPrime(j) < x)
				j++;
			return j;
		} else {
			while (j > i + 1) {
				int moy = (i + j) / 2;
				if (getPrime(moy) < x) {
					i = moy;
				} else {
					j = moy;
				}
			}
			return x <= getPrime(i) ? i : j;
		}
	}

	PrimeArray decompose(int n) {
		if (n < 1)
			throw new IllegalArgumentException("can't decompose number < " + 1);

		int[] primesFactors = new int[maxNbPrimes];
		int size = 1;
		primesFactors[0] = 0;

		// for each prime, test if his powers divide n
		for (int i = 0; getPrime(i) <= n; i++) {
			primesFactors[i] = 0;
			int divider = getPrime(i);
			while (n % divider == 0) {
				divider = divider * getPrime(i);
				primesFactors[i] = primesFactors[i] + 1;
				size = i + 1;
			}
		}
		return new PrimeArray(primesFactors, size, this);
	}
}
