package br.unip.tcc.tccapi.service;

import br.unip.tcc.tccapi.model.Member;
import br.unip.tcc.tccapi.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Member save(Member member) {
        member.setUpdatedAt(LocalDateTime.now());
        Objects.nonNull(member.getCreatedAt());
        this.memberRepository.save(member);
        return member;
    }

    public Member findById(Long memberId) {
        return this.memberRepository.findById(memberId).orElse(new Member());
    }
}
