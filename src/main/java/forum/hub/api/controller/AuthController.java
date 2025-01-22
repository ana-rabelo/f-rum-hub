package forum.hub.api.controller;

import jakarta.validation.Valid;
import forum.hub.api.domain.user.DataAuthentication;
import forum.hub.api.domain.user.User;
import forum.hub.api.infra.security.JWTTokenData;
import forum.hub.api.infra.security.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<JWTTokenData> login(@RequestBody @Valid DataAuthentication data){
        var authToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = manager.authenticate(authToken);

        var JWTToken = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new JWTTokenData(JWTToken));
    }
}
