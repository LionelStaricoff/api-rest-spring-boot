package ar.com.codoacodo.spring.security;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
	

	public boolean validateToken(String jwtToken) {
		return true;
	}
	
	public String getUsernameFromToken (String jwtToken) {
		return "aduit";
	}
	
	public String generateToken(Authentication auth) {
		return "jwtdementiraporquedespueslocambiamos";
	}
}
