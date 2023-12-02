package br.unip.tcc.tccapi.controller;

import br.unip.tcc.tccapi.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.spi.LoginModule;
import java.util.Map;

//@RestController
//@RequestMapping("user/auth/")
public class AuthenticationController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    TokenService tokenService;
//
//    @PostMapping()
//    public String login(@RequestBody Map<String, String > login) {
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                new UsernamePasswordAuthenticationToken(login.get("login"), login.get("password"));
//
//        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//
//        Member member = (Member) authentication.getPrincipal();
//
//
//        return tokenService.generateToken(member);
   // }
}
