package mkkz7.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class HashMonster {
    private static final int cost = 12;

    public static String generateHash(String password){
        return BCrypt.withDefaults().hashToString(cost, password.toCharArray());
    }

    public static BCrypt.Result verifyPassword(String password, String storedPassword){
        return BCrypt.verifyer().verify(password.toCharArray(), storedPassword);
    }
}
