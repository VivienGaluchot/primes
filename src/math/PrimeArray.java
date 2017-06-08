package math;

public class PrimeArray implements Comparable<PrimeArray> {
	private int[] factors;
	private int size;
	private PrimeCache cache;

	static public PrimeArray create(int x, PrimeCache cache) {
		return cache.decompose(x);
	}

	protected PrimeArray(int[] factors, int size, PrimeCache cache) {
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
	
	public int getFactor(int i){
		return factors[i];
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

	public boolean isDividing(PrimeArray b) {
		if (this.size > b.size)
			return false;
		for (int i = 0; i < this.size; i++) {
			if (this.factors[i] > b.factors[i])
				return false;
		}
		return true;
	}

	public PrimeArray divideBy(PrimeArray b) {
		if (b.size > this.size)
			return null;
		int size = this.size;
		int factors[] = new int[size];
		int i;
		for (i = 0; i < b.size; i++) {
			if (b.factors[i] > this.factors[i])
				return null;
			else
				factors[i] = this.factors[i] - b.factors[i];
		}
		for (; i < this.size; i++)
			factors[i] = this.factors[i];
		while (size > 0 && factors[size - 1] <= 0)
			size--;
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
	public int compareTo(PrimeArray b) {
		int size = Math.max(this.size, b.size);
		int A = 1;
		int B = 1;
		for (int i = 0; i < size; i++) {
			int currentExp = 0;
			if (i < this.size)
				currentExp += this.factors[i];
			if (i < b.size)
				currentExp -= b.factors[i];
			if (currentExp > 0)
				A = A * Algo.pow(cache.getPrime(i), currentExp);
			else if (currentExp < 0)
				B = B * Algo.pow(cache.getPrime(i), -currentExp);
		}
		return A - B;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		boolean first = true;
		str.append("(" + getValue() + ")");
		str.append("[");
		for (int i = 0; i < size; i++) {
			if (!first)
				str.append(", ");
			else
				first = false;
			str.append(factors[i]);
		}
		str.append("]");
		return str.toString();
	}
}
