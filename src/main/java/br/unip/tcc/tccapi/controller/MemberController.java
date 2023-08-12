package br.unip.tcc.tccapi.controller;

import br.unip.tcc.tccapi.model.Member;
import br.unip.tcc.tccapi.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    public Member findMemberById(final long memberId){
        return this.memberRepository.findById(memberId).get();
    }

    public Member save(final Member member){
        final Member update = this.memberRepository.save(member);
        return member;}
}
