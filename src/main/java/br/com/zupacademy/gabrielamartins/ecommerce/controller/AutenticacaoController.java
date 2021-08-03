package br.com.zupacademy.gabrielamartins.ecommerce.controller;

import br.com.zupacademy.gabrielamartins.ecommerce.config.security.LoginRequest;
import br.com.zupacademy.gabrielamartins.ecommerce.config.security.TokenResponse;
import br.com.zupacademy.gabrielamartins.ecommerce.config.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authManager;

    @PostMapping
    public ResponseEntity<TokenResponse> autenticar(@Valid @RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken dadosLogin = request.toUsernamePasswordAuthenticationToken();
        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenResponse(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
