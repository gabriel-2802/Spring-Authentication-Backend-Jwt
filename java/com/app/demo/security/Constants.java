package com.app.demo.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class Constants {
    public static final long JWT_EXPIRATION = 604800000L; // 7 days
    public static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
}
