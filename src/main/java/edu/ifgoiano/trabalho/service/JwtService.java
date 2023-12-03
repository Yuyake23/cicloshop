package edu.ifgoiano.trabalho.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JwtService {
  private static final String CHAVE =
      "37054716c8eff1e7feb554fc7801eac4f35538047770d3ab2352ced7d2b6725f";

  public String extrairUsername(String token) {
    return extrairClaim(token, Claims::getSubject);
  }

  private <T> T extrairClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extrairTodosOsClaims(token);
    return claimsResolver.apply(claims);
  }

  public String gerarToken(UserDetails userDetails) {

    return gerarToken(new HashMap<>(), userDetails);
  }

  public String gerarToken(Map<String, Object> claimsExtras, UserDetails userDetails) {

    log.info("Token gerado para \"" + userDetails.getUsername() + "\".");

    return Jwts.builder()
        .setClaims(claimsExtras)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 10000 * 60))
        .signWith(getChaveAssinatura(), SignatureAlgorithm.HS256)
        .compact();
  }

  public boolean tokenEstaValido(String token, UserDetails userDetails) {
    final String username = extrairUsername(token);
    return (username.equals(userDetails.getUsername())) && !tokenEstaVencido(token);
  }

  private boolean tokenEstaVencido(String token) {
    return extrairDataVencimento(token).before(new Date());
  }

  private Date extrairDataVencimento(String token) {
    return extrairClaim(token, Claims::getExpiration);
  }

  private Claims extrairTodosOsClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getChaveAssinatura())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  private Key getChaveAssinatura() {
    byte[] keyBytes = Decoders.BASE64.decode(CHAVE);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
