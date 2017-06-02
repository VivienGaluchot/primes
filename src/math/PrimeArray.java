package math;

public class PrimeArray {
	private int[] factors;
	private int size;

	public PrimeArray(int[] factors, int size) {
		this.size = size;
		this.factors = new int[size];
		for (int i = 0; i < size; i++) {
			this.factors[i] = factors[i];
		}
	}

	public int getSize() {
		return size;
	}

	public int getValue() {
		int r = 1;
		for (int i = 0; i < size; i++)
			r = r * Algo.pow(Algo.getPrime(i), factors[i]);
		return r;
	}

	public PrimeArray multiply(PrimeArray b) {
		int size = Math.max(this.size, b.size);
		int factors[] = new int[size];
		for (int i = 1; i < size; i++) {
			if (i < this.size)
				factors[i] += this.factors[i];
			if (i < b.size)
				factors[i] += b.factors[i];
		}
		if (size > 1)
			factors[0] = 0;
		return new PrimeArray(factors, size);
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
