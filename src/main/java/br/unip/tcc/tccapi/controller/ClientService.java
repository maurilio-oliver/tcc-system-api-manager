package br.unip.tcc.tccapi.controller;

import br.unip.tcc.tccapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/seller")
public class ClientService {
    @Autowired
    private UserRepository userRepository;

}
