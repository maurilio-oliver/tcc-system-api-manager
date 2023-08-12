package br.unip.tcc.tccapi.controller;

import br.unip.tcc.tccapi.model.Member;
import br.unip.tcc.tccapi.repository.MemberRepository;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class SellerController {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberController memberController;




}
