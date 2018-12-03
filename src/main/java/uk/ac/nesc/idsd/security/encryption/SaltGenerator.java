package uk.ac.nesc.idsd.security.encryption;

import java.security.SecureRandom;

public final class SaltGenerator {

    private static SaltGenerator instance;

    private SaltGenerator() {

    }

    public static synchronized SaltGenerator getInstance() {
        if (instance == null) {
            instance = new SaltGenerator();
        }
        return instance;
    }

    public byte[] nextSalt() {
        byte[] ba = new byte[4];
        SecureRandom random = new SecureRandom();
        random.nextBytes(ba);
        return ba;
    }
}