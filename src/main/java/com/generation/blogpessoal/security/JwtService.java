package com.generation.blogpessoal.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

	public static final String SECRET = "0c0a3671d2dd864fb7d41d9c38c260ba7bf2473bdbf3b3e263e0511d4707d898";

	//token expira 
	
	//assinatura
	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSignKey()).build()
				.parseClaimsJws(token).getBody();
	}

	// pega a assinatura extraida e trata ela para tornar ela entendivel 
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	// recuperar os dados da parte sub do claim onde encontramos o email(usuario)
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	// data q o token expira
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	//valida se a data que o token expira esta dentro da validade ou seja data atual ainda não atingiu essa data
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	// validar se o usuario que foi extraido do token condiz com o usuario que a userDetails tem e se esta dentro da data de validade do token
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	// objetivo é calcular o tempo de validade do tokne, formar o claim com as informações do token
	private String createToken(Map<String, Object> claims, String userName) {
		return Jwts.builder()
					.setClaims(claims)
					.setSubject(userName)
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
					.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	
	// gerar o token puxando os claims formados no metodo anterior 
	public String generateToken(String userName) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userName);
	}

}
