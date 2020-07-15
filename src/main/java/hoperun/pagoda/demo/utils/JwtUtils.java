package hoperun.pagoda.demo.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.entity.UserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Generate token.
 *
 * @author zhangxiqin
 *
 */
@Component
public class JwtUtils {

    /**
     * 
     */
    public static final String ROLE_REFRESH_TOKEN = "ROLE_REFRESH_TOKEN";

    /**
     * user id.
     */
    private static final String CLAIM_KEY_USER_ID = "userId";

    /**
     * token map.
     */
    private Map<String, String> tokenMap = new ConcurrentHashMap<>(Constant.NUMBER_32);

    /**
     * token generate key.
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * token access expiration.
     */
    @Value("${jwt.expiration}")
    private Long accessTokenExpiration;

    /**
     * token refresh expiration.
     */
    @Value("${jwt.expiration}")
    private Long refreshTokenExpiration;

    /**
     * signature algorithm.
     */
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    /**
     * Get user info from the token.
     *
     * @param token
     *            token
     * @return UserDetail userdetail
     */
    public UserDetail getUserFromToken(final String token) {
        UserDetail userDetail;
        try {
            final Claims claims = getClaimsFromToken(token);
            int userId = getUserIdFromToken(token);
            String username = "";
            if (null != claims) {
                username = claims.getSubject();
            }
            userDetail = new UserDetail(userId, username, "");
        } catch (Exception e) {
            userDetail = null;
        }
        return userDetail;
    }

    /**
     * Get user id from token.
     *
     * @param token token
     * @return long user id.
     */
    public int getUserIdFromToken(final String token) {
        final Claims claims = getClaimsFromToken(token);
        return null != claims ? Integer.parseInt(String.valueOf(claims.get(CLAIM_KEY_USER_ID))) : 0;
    }

    /**
     * Get user name from token.
     *
     * @param token token
     * @return String username
     */
    public final String getUsernameFromToken(final String token) {
        final Claims claims = getClaimsFromToken(token);
        return null != claims ? claims.getSubject() : "";
    }

    /**
     * Get created date from token.
     * 
     * @param token token
     * @return token generated date
     */
    public Date getCreatedDateFromToken(final String token) {
        final Claims claims = getClaimsFromToken(token);
        return null != claims ? claims.getIssuedAt() : null;
    }

    /**
     * Generate access token.
     *
     * @param userDetail
     *            userDetail
     * @return token token
     */
    public String generateAccessToken(final UserDetail userDetail) {
        Map<String, Object> claims = generateClaims(userDetail);
        return generateAccessToken(userDetail.getUsername(), claims);
    }

    /**
     * Get expiration date from token.
     *
     * @param token
     *            token
     * @return Date expiration date
     */
    public Date getExpirationDateFromToken(final String token) {
        final Claims claims = getClaimsFromToken(token);
        return null != claims ? claims.getExpiration() : null;

    }

    /**
     * juge if can refresh token.
     * @param token token
     * @param lastPasswordReset last password reset date
     * @return true if token is not expired and is created before password reset
     */
    public Boolean canTokenBeRefreshed(final String token, final Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset) && (!isTokenExpired(token));
    }

    /**
     * get refresh token.
     * @param token token
     * @return token
     */
    public String refreshToken(final String token) {
        final Claims claims = getClaimsFromToken(token);
        return null != claims ? generateAccessToken(claims.getSubject(), claims) : "";

    }

    /**
     * validate token.
     * 
     * @param token
     *            token
     * @param userDetails
     *            userDetails
     * @return true id token is valida otherwise false.
     */
    public Boolean validateToken(final String token, final UserDetails userDetails) {
        UserDetail userDetail = (UserDetail) userDetails;
        final long userId = getUserIdFromToken(token);
        final String username = getUsernameFromToken(token);
        return (userId == userDetail.getUserId() && username.equals(userDetail.getUsername()) && !isTokenExpired(token));
    }

    /**
     * generate refresh token.
     * @param userDetail userDetail
     * @return refresh token
     */
    public String generateRefreshToken(final UserDetail userDetail) {
        Map<String, Object> claims = generateClaims(userDetail);
        return generateRefreshToken(userDetail.getUsername(), claims);
    }

    /**
     * Put token.
     *
     * @param userName
     *            userName
     * @param token
     *            token
     */
    public void putToken(final String userName, final String token) {
        tokenMap.put(userName, token);
    }

    /**
     * Delete token.
     *
     * @param userName userName
     */
    public void deleteToken(final String userName) {
        tokenMap.remove(userName);
    }

    /**
     * Determine if the username is included in the token.
     * 
     * @param userName
     *            user name
     * @param token
     *            token
     * @return true: if included otherwise false
     */
    public boolean containToken(final String userName, final String token) {
        return userName != null && tokenMap.containsKey(userName) && tokenMap.get(userName).equals(token);

    }

    /**
     * Get Claims from Token.
     *
     * @param token
     *            token
     * @return Cliams cliams
     */
    private Claims getClaimsFromToken(final String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * Generate expiration date.
     *
     * @param expiration
     *            expiration time
     * @return Date expiration date
     */
    private Date generateExpirationDate(final long expiration) {
        return new Date(System.currentTimeMillis() + expiration * Constant.NUMBER_1000);
    }

    /**
     * Jugle if the token expired.
     *
     * @param token
     *            token
     * @return true if expired othwise false.
     */
    private Boolean isTokenExpired(final String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    /**
     * juge if token created before password reset.
     * @param created create date,last password reset date.
     * @param lastPasswordReset lastPasswordReset
     * @return true if created before last word set before.
     */
    private Boolean isCreatedBeforeLastPasswordReset(final Date created, final Date lastPasswordReset) {
        return lastPasswordReset != null && created.before(lastPasswordReset);
    }

    /**
     * Generater claims.
     *
     * @param userDetail
     *            userDetail.
     * @return claims
     */
    private Map<String, Object> generateClaims(final UserDetail userDetail) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USER_ID, userDetail.getUserId());
        return claims;
    }

    /**
     * Generate access token.
     *
     * @param subject subject
     * @param claims claims
     * @return access token
     */
    private String generateAccessToken(final String subject, final Map<String, Object> claims) {
        return generateToken(subject, claims, accessTokenExpiration);
    }

    /**
     * parse authorities.
     * @param authorities authorities
     * @return authorities
     */
    @SuppressWarnings("unused")
    private List<String> authoritiesToArray(final Collection<? extends GrantedAuthority> authorities) {
        List<String> list = new ArrayList<>();
        for (GrantedAuthority ga : authorities) {
            list.add(ga.getAuthority());
        }
        return list;
    }

    /**
     * generate refresh token.
     * @param subject subject
     * @param claims claims
     * @return refresh token
     */
    private String generateRefreshToken(final String subject, final Map<String, Object> claims) {
        return generateToken(subject, claims, refreshTokenExpiration);
    }

    /**
     * Generate token.
     *
     * @param subject subject
     * @param claims claims
     * @param expiration expiration
     * @return token
     */
    private String generateToken(final String subject, final Map<String, Object> claims, final long expiration) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setId(UUID.randomUUID().toString()).setIssuedAt(new Date())
                .setExpiration(generateExpirationDate(expiration)).compressWith(CompressionCodecs.DEFLATE).signWith(SIGNATURE_ALGORITHM, secret)
                .compact();
    }

}
