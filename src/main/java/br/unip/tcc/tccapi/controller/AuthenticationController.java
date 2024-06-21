package br.unip.tcc.tccapi.controller;



import br.unip.tcc.tccapi.model.Token;
import br.unip.tcc.tccapi.model.User;
import br.unip.tcc.tccapi.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;



@Controller
@RequestMapping("/auth/")
public class AuthenticationController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("v1")
    public ResponseEntity<Object> login(@RequestBody @Validated User user) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());


        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        return ResponseEntity.ok(Map.of("token", new Token(tokenService.generateToken((User) authentication.getPrincipal())),
                "user", (User) authentication.getPrincipal()));
    }
}
