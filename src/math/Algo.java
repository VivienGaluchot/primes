
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
		for (int i = 1; i < 1000; i++) {
			System.out.println(PrimeArray.create(i, cache));
		}
	}
}
