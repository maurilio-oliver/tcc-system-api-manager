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

    public Member save(final Member member) {
        if (Objects.nonNull(member.getPersonal()) &&
                (Objects.nonNull(member.getPersonal().getEmail())
                        && Objects.nonNull(member.getPersonal().getTaxId())
                        && Objects.nonNull(member.getPersonal().getMobilePhone()))) {
            member.setUpdatedAt(LocalDateTime.now());
            member.setCreatedAt(Objects.nonNull(member.getCreatedAt()) ? member.getCreatedAt() : LocalDateTime.now());
           return this.memberRepository.save(member);
        }
        return member;
    }

    public Member findById(final Long memberId) {
        return this.memberRepository.findById(memberId).orElse(new Member());
    }

    public Member findByEmailOrMobilePhone(final String email, final String mobilePhone, final String taxId) {
        return this.memberRepository.findByTerms(email, mobilePhone, taxId);
    }

    public Member updateMember(Member member){
        member.setUpdatedAt(LocalDateTime.now());
        return this.memberRepository.save(member);
    }
}
