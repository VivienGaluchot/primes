package math;

public class PrimeArray {
	private int[] factors;
	private int size;
	private PrimeCache cache;

	public PrimeArray(int[] factors, int size, PrimeCache cache) {
		this.size = size;
		this.factors = new int[size];
		for (int i = 0; i < size; i++) {
			this.factors[i] = factors[i];
		}
		this.cache = cache;
	}

	public int getSize() {
		return size;
	}

	public int getValue() {
		int r = 1;
		for (int i = 0; i < size; i++)
			r = r * Algo.pow(cache.getPrime(i), factors[i]);
		return r;
	}

	public boolean isPrime() {
		int sum = 0;
		for (int i : factors) {
			sum += i;
			if (sum > 1)
				break;
		}
		return sum == 1 || size == 1;
	}

	public PrimeArray multiply(PrimeArray b) {
		int size = Math.max(this.size, b.size);
		int factors[] = new int[size];
		for (int i = 0; i < size; i++) {
			if (i < this.size)
				factors[i] += this.factors[i];
			if (i < b.size)
				factors[i] += b.factors[i];
		}
		return new PrimeArray(factors, size, cache);
	}

	public String getExpr() {
		StringBuffer str = new StringBuffer();
		boolean first = true;
		for (int i = 0; i < size; i++) {
			if (factors[i] > 0) {
				if (!first)
					str.append(" * ");
				else
					first = false;
				str.append(cache.getPrime(i));
				if (factors[i] > 1)
					str.append("^" + factors[i]);
			}
		}
		return str.toString();
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < size; i++) {
			str.append(factors[i]);
			str.append(' ');
		}
		return str.toString();
	}
}
