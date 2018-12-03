package uk.ac.nesc.idsd.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigInteger;
import java.security.SecureRandom;

public final class RandomStringGenerator {
    private static final Log log = LogFactory.getLog(RandomStringGenerator.class);

    private static SecureRandom random = new SecureRandom();

    public static String generateRandomString() {
        return new BigInteger(48, random).toString(32);
    }

    public static void main(String[] args) {
        log.info(generateRandomString());
    }

    /*
      Another way to generate Radom String
     */
    @SuppressWarnings("deprecation")
    public static String randomstring(int lo, int hi) {
        int n = rand(lo, hi);
        byte b[] = new byte[n];
        for (int i = 0; i < n; i++) {
            b[i] = (byte) rand('a', 'z');
        }
        return new String(b, 0);
    }

    private static int rand(int lo, int hi) {
        java.util.Random rn = new java.util.Random();
        int n = hi - lo + 1;
        int i = rn.nextInt(n);
        if (i < 0) {
            i = -i;
        }
        return lo + i;
    }

    public static String randomstring() {
        return randomstring(2, 256);
    }

}