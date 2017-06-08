package math;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class PrimeCacheTest {

	static PrimeCache cache;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cache = new PrimeCache(100000);
	}

	@Test
	public void testGetPrime() {
		if (cache.getPrime(0) != 2)
			fail("--");
		if (cache.getPrime(1) != 3)
			fail("--");
		if (cache.getPrime(2) != 5)
			fail("--");
		if (cache.getPrime(3) != 7)
			fail("--");
		if (cache.getPrime(167) != 997)
			fail("--");
	}

	@Test
	public void testGetNextTruePrime() {
		if (cache.getNextTruePrime(0) != 2)
			fail("--");
		if (cache.getNextTruePrime(2) != 3)
			fail("--");
		if (cache.getNextTruePrime(3) != 5)
			fail("--");
		if (cache.getNextTruePrime(6) != 7)
			fail("--");
		if (cache.getNextTruePrime(991) != 997)
			fail("--");
	}

	@Test
	public void testIsTruePrime() {
		if (cache.isTruePrime(-1))
			fail("--");
		if (cache.isTruePrime(0))
			fail("--");
		if (cache.isTruePrime(4))
			fail("--");
		if (!cache.isTruePrime(2))
			fail("--");
		if (!cache.isTruePrime(3))
			fail("--");
		if (!cache.isTruePrime(17))
			fail("--");
	}

	@Test
	public void testGetIdNextOrEqualPrime() {
		if (cache.getIdNextOrEqualPrime(0) != 0)
			fail("--");
		if (cache.getIdNextOrEqualPrime(2) != 0)
			fail("--");
		if (cache.getIdNextOrEqualPrime(3) != 1)
			fail("--");
		if (cache.getIdNextOrEqualPrime(6) != 3)
			fail("--");
		if (cache.getIdNextOrEqualPrime(997) != 167)
			fail("--");
	}

	@Test
	public void testDecompose() {
		if (cache.decompose(2).getValue() != 2)
			fail("--");
		if (cache.decompose(3).getValue() != 3)
			fail("--");
		if (cache.decompose(4).getValue() != 4)
			fail("--");
		if (cache.decompose(5).getValue() != 5)
			fail("--");
		if (cache.decompose(70).getValue() != 70)
			fail("--");
	}

}
