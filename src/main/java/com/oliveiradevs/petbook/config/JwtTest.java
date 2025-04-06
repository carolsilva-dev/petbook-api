package com.oliveiradevs.petbook.config;

public class JwtTest {
    public static void main(String[] args) {
        Jwt jwtUtil = new Jwt();
        jwtUtil.init();

        String token = jwtUtil.generateToken("testeUser");
        System.out.println("Token gerado: " + token);

        boolean isValid = jwtUtil.validateToken(token, "testeUser");
        System.out.println("Token válido? " + isValid);

        String username = jwtUtil.extractUsername(token);
        System.out.println("Usuário extraído: " + username);
    }
}
