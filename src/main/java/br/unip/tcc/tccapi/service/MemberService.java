package br.unip.tcc.tccapi.service;

import br.unip.tcc.tccapi.controller.BussinesExceptionAdvance;
import br.unip.tcc.tccapi.model.Member;
import br.unip.tcc.tccapi.model.Personal;
import br.unip.tcc.tccapi.model.bussines.BussinesException;
import br.unip.tcc.tccapi.repository.MemberRepository;
import lombok.NonNull;
import lombok.val;
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
    public MessageSource messageSource;



    public Member save( final Member member) throws BussinesException {
        if (Objects.nonNull(member)) {
            return this.memberRepository.save(member);
        } else {
            throw new BussinesException(messageSource.getMessage("",null,"member object is null",Locale.getDefault()));
        }
    }






    public Member findById(final Long memberId) {
                if (Objects.isNull(memberId)) {
                   return this.memberRepository.findById(memberId).orElse(null);
                } else {
                    return null;
                }
    }

    public Member findByEmailOrMobilePhone(final String email, final String mobilePhone, final String taxId) {
        return this.memberRepository.findByTerms(email, mobilePhone, taxId);
    }


    public Member update( Member member) {
        if (Objects.nonNull(member.getId())) {
            member.setUpdatedAt(LocalDateTime.now());
            return this.memberRepository.save(member);
        }
        else {
            return null;
        }
    }

}
