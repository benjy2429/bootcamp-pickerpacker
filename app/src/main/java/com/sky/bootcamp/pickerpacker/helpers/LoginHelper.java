package com.sky.bootcamp.pickerpacker.helpers;

import java.io.UnsupportedEncodingException;

import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.util.encoders.Base64;

/**
 * Created by bca23 on 27/07/15.
 */
public class LoginHelper {

    public static final Integer DEFAULT_ITERATIONS = 20000;

    public LoginHelper() {
    }

    public static boolean passwordCorrect(String enteredPassword, String storedPassword) throws UnsupportedEncodingException {
        String[] parts = storedPassword.split("\\$");
        String salt = parts[2];
        String storedHash = parts[3];
        String calculatedHash = getEncodedHash(enteredPassword, salt);

        return storedHash.equals(calculatedHash);
    }

    public static String getEncodedHash(String password, String salt) throws UnsupportedEncodingException {
        PKCS5S2ParametersGenerator gen = new PKCS5S2ParametersGenerator(new SHA256Digest());

        gen.init(password.getBytes(), salt.getBytes(), DEFAULT_ITERATIONS);
        byte[] dk = ((KeyParameter) gen.generateDerivedParameters(256)).getKey();

        return Base64.toBase64String(dk);
    }
}
