package br.unip.tcc.tccapi.service;

import br.unip.tcc.tccapi.controller.MemberController;
import br.unip.tcc.tccapi.model.Member;
import br.unip.tcc.tccapi.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/member/*")
public class MemberService {

    @Autowired
    private MemberController memberController;

    @GetMapping("test/get/{memberId}")
    public Member getById(@PathVariable final Long memberId) {
        if (!Objects.isNull(memberId))
            return this.memberController.findMemberById(memberId);
        else return null;
    }

    @GetMapping("/*/save")
    public Member createNewUser(@RequestBody final Member member) {
        return this.memberController.save(member);
    }










}
