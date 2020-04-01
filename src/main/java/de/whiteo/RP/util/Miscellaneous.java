package de.whiteo.rp.util;

import java.math.BigInteger;
import java.util.Random;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class Miscellaneous {

  private static final BigInteger MIN_FOR_RANDOM = new BigInteger(
      "1000000000000000000000000000000");
  private static final BigInteger MAX_FOR_RANDOM = new BigInteger(
      "9000000000000000000000000000000");

  public static String getRandomVerId() {
    BigInteger bigInteger = MAX_FOR_RANDOM.subtract(MIN_FOR_RANDOM);
    Random randNum = new Random();
    int len = MAX_FOR_RANDOM.bitLength();
    BigInteger res = new BigInteger(len, randNum);
    if (res.compareTo(MIN_FOR_RANDOM) < 0) {
      res = res.add(MIN_FOR_RANDOM);
    }
    if (res.compareTo(bigInteger) >= 0) {
      res = res.mod(bigInteger).add(MIN_FOR_RANDOM);
    }
    String INIT_VER_ID = "a00000000";
    return res.toString() + INIT_VER_ID;
  }
}