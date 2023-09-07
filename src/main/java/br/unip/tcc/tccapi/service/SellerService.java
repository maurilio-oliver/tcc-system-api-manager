package br.unip.tcc.tccapi.service;

import br.unip.tcc.tccapi.model.Member;
import br.unip.tcc.tccapi.model.Seller;
import br.unip.tcc.tccapi.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class SellerService {
    @Autowired
    private MemberRepository memberRepository;

    public Member save(Member member){
        if (Objects.isNull(member.getId()) || Objects.isNull(member.getSeller())){
            member.setSeller(new Seller());
            member.getSeller().setInitializedAt(LocalDateTime.now());
        }

        member.setUpdatedAt(LocalDateTime.now());
        this.memberRepository.save(member);
        return member;
    }

    public Member findById(final Long memberId, final boolean isSeller) {
        if (memberId != null ) {
            Member member = null;
            if (isSeller){
                member = this.memberRepository.findSellerById(memberId);
            } else {
                member = this.memberRepository.findById(memberId).get();
            }
            return member;
        }
        return null;

    }

    public Member findByTerm(String taxId, String email) {return null;}





}
