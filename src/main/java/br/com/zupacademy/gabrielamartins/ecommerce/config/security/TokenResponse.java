package br.com.zupacademy.gabrielamartins.ecommerce.config.security;

public class TokenResponse {


    private String token;
    private String tipo;

    public TokenResponse(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getToken() {
        return token;
    }
}
