package math;

import static org.junit.Assert.*;

import org.junit.Test;

public class AlgoTest {

	@Test
	public void testPow() {
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++)
				if (Algo.pow(j, i) != Math.pow(j, i))
					fail("Fail with values " + j + "^" + i);
	}

}
