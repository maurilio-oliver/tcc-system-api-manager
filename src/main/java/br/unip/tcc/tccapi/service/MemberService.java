package br.unip.tcc.tccapi.service;

import br.unip.tcc.tccapi.model.Member;
import br.unip.tcc.tccapi.model.bussines.BussinesException;
import br.unip.tcc.tccapi.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;


@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MessageSource messageSource;



    public Member save(final Member member) throws BussinesException {
        if (Objects.nonNull(member.getPersonal()) &&
                (Objects.nonNull(member.getPersonal().getEmail())
                        && Objects.nonNull(member.getPersonal().getTaxId())
                        && Objects.nonNull(member.getPersonal().getMobilePhone()))) {
            member.setUpdatedAt(LocalDateTime.now());
            member.setCreatedAt(Objects.nonNull(member.getCreatedAt()) ? member.getCreatedAt() : LocalDateTime.now());
            Member candidates = this.findByEmailOrMobilePhone(member.getPersonal().getEmail(), member.getPersonal().getMobilePhone(), member.getPersonal().getTaxId());
            if (Objects.nonNull(candidates)) {
                String message = "usuario ja cadastrado, por favor usar outro:";
                message = (member.getPersonal().getEmail()).equals(candidates.getPersonal().getEmail()) ? message +"email," : "";
                message = (member.getPersonal().getTaxId()).equals(candidates.getPersonal().getTaxId()) ? message + "cpf," : "";
                message = (member.getPersonal().getMobilePhone()).equals(candidates.getPersonal().getMobilePhone()) ? message + "telefone," : "";

                throw new BussinesException(message + " diferente");
            }
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

    public Member update(Member member) {
        return this.memberRepository.save(member);
    }
}
