package org.helpers.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.helpers.common.utils.DateUtils.asDate;

/**
 * Simple set of utils for basic JWT-tokens operations.
 */
@Slf4j
public class JwtUtils {

    private static final String CLAIM_KEY_USERNAME = "username";
    private static final String CLAIM_KEY_CREATED = "created";

    private final String secret;

    /**
     * Creates JWT utils context with specified secret.
     *
     * @param secret - JWT signing secret.
     */
    public JwtUtils(String secret) {
        this.secret = secret;
    }

    /**
     * Generates JWT token with specified login.
     *
     * @param login - user login (can be any string, like simply login or e-mail).
     * @return - encoded JWT token.
     */
    public String generate(String login) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, login);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generate(claims);
    }

    /**
     * Checks that specified token is valid (has valid data, sign and non-expired).
     *
     * @param token - encoded JWT token.
     * @return - true if token valid, false - otherwise.
     */
    public Boolean validate(String token) {
        return !isTokenExpired(token);
    }

    /**
     * Returns username that encoded in JWT token.
     *
     * @param token - encoded JWT token.
     * @return - optional with value if token is valid & contains username claim, empty otherwise.
     */
    public Optional<String> getUsername(String token) {
        return getClaimValue(token, CLAIM_KEY_USERNAME, String.class);
    }

    private <T> Optional<T> getClaimValue(String token, String field, Class<T> clazz) {
        return getClaimsFromToken(token).map(claims -> claims.get(field, clazz));
    }

    private Optional<Claims> getClaimsFromToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            return Optional.ofNullable(claims);
        } catch (Exception e) {
            log.error("Error occurred while parsing JWT: {}", e.getMessage());
            return Optional.empty();
        }
    }

    private Boolean isTokenExpired(String token) {
        Date expiration = getClaimValue(token, Claims.EXPIRATION, Date.class).orElse(null);
        return expiration != null && expiration.before(new Date());
    }

    private String generate(Map<String, Object> claims) {
        Date expire = asDate(LocalDateTime.now().plusDays(7L));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expire)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
