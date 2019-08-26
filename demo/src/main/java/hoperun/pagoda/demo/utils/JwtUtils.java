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

    public static final String ROLE_REFRESH_TOKEN = "ROLE_REFRESH_TOKEN";

    private static final String CLAIM_KEY_USER_ID = "user_id";
    private static final String CLAIM_KEY_AUTHORITIES = "scope";

    private Map<String, String> tokenMap = new ConcurrentHashMap<>(32);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long access_token_expiration;

    @Value("${jwt.expiration}")
    private Long refresh_token_expiration;

    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

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
            String username = claims.getSubject();
            userDetail = new UserDetail(userId, username, "");
        } catch (Exception e) {
            userDetail = null;
        }
        return userDetail;
    }

    /**
     * Get user id from token.
     *
     * @param token
     * @return long user id.
     */
    public int getUserIdFromToken(String token) {
        int userId;
        try {
            final Claims claims = getClaimsFromToken(token);
            userId = Integer.parseInt(String.valueOf(claims.get(CLAIM_KEY_USER_ID)));
        } catch (Exception e) {
            userId = 0;
        }
        return userId;
    }

    /**
     * Get user name from token.
     *
     * @param token
     *            token
     * @return String username
     */
    public final String getUsernameFromToken(final String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * Get created date from token
     * 
     * @param token
     * @return
     */
    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = claims.getIssuedAt();
        } catch (Exception e) {
            created = null;
        }
        return created;
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
        // claims.put(CLAIM_KEY_AUTHORITIES, authoritiesToArray(userDetail.getAuthorities()).get(0));
        return generateAccessToken(userDetail.getUsername(), claims);
    }

    /**
     * Get expiration date from token.
     *
     * @param token
     *            token
     * @return Date expiration date
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset) && (!isTokenExpired(token));
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            refreshedToken = generateAccessToken(claims.getSubject(), claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * validate token
     * 
     * @param token
     *            token
     * @param userDetails
     *            userDetails
     * @return
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        UserDetail userDetail = (UserDetail) userDetails;
        final long userId = getUserIdFromToken(token);
        final String username = getUsernameFromToken(token);
        // final Date created = getCreatedDateFromToken(token);
        return (userId == userDetail.getUser_id() && username.equals(userDetail.getUsername()) && !isTokenExpired(token)
        // && !isCreatedBeforeLastPasswordReset(created, userDetail.getLastPasswordResetDate())
        );
    }

    public String generateRefreshToken(UserDetail userDetail) {
        Map<String, Object> claims = generateClaims(userDetail);
        //
        // String roles[] = new String[]{JwtUtils.ROLE_REFRESH_TOKEN};
        // JSONArray jsonArray = (JSONArray) JSONArray.parse(roles.toString());
        // claims.put(CLAIM_KEY_AUTHORITIES, jsonArray.toJSONString());
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
    public void putToken(String userName, String token) {
        tokenMap.put(userName, token);
    }

    /**
     * Delete token.
     *
     * @param userName
     */
    public void deleteToken(String userName) {
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
        if (userName != null && tokenMap.containsKey(userName) && tokenMap.get(userName).equals(token)) {
            return true;
        }
        return false;
    }

    /**
     * Get Claims from Token.
     *
     * @param token
     *            token
     * @return Cliams cliams
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
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
    private Date generateExpirationDate(long expiration) {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * Jugle if the token expired.
     *
     * @param token
     *            token
     * @return true if expired othwise false.
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 
     * @param created
     * @param lastPasswordReset
     * @return
     */
    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
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
        claims.put(CLAIM_KEY_USER_ID, userDetail.getUser_id());
        return claims;
    }

    /**
     * Generate access token.
     *
     * @param subject
     * @param claims
     * @return
     */
    private String generateAccessToken(String subject, Map<String, Object> claims) {
        return generateToken(subject, claims, access_token_expiration);
    }

    private List<String> authoritiesToArray(Collection<? extends GrantedAuthority> authorities) {
        List<String> list = new ArrayList<>();
        for (GrantedAuthority ga : authorities) {
            list.add(ga.getAuthority());
        }
        return list;
    }

    private String generateRefreshToken(String subject, Map<String, Object> claims) {
        return generateToken(subject, claims, refresh_token_expiration);
    }

    /**
     * Generate token.
     *
     * @param subject
     * @param claims
     * @param expiration
     * @return
     */
    private String generateToken(String subject, Map<String, Object> claims, long expiration) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setId(UUID.randomUUID().toString()).setIssuedAt(new Date())
                .setExpiration(generateExpirationDate(expiration)).compressWith(CompressionCodecs.DEFLATE).signWith(SIGNATURE_ALGORITHM, secret)
                .compact();
    }

}
