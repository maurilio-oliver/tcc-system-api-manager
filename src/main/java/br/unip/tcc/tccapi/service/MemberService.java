package br.unip.tcc.tccapi.service;

import br.unip.tcc.tccapi.controller.BussinesExceptionAdvance;
import br.unip.tcc.tccapi.model.Member;
import br.unip.tcc.tccapi.model.Personal;
import br.unip.tcc.tccapi.model.bussines.BussinesException;
import br.unip.tcc.tccapi.model.user.User;
import br.unip.tcc.tccapi.repository.MemberRepository;
import br.unip.tcc.tccapi.repository.UserRepository;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.NonNull;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private UserRepository userRepository;
    @Autowired
    public MessageSource messageSource;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();



    public Member save( Member member) throws BussinesException {
        if (Objects.nonNull(member)) {
            member.setUsername(member.getPersonal().getEmail());
            member = this.memberRepository.save(member);
            if (member.getId() != null && member.getPersonal() != null && (member.getPersonal().getEmail() != null)) {
                User user = new User();
                user.setId(member.getId());
                user.setUsername(member.getPersonal().getEmail());

                user.setPassword(encoder.encode(member.getPassword()));
                user.setRole("ROLE_MEMBER");
                this.userRepository.save(user);
            }

            return member;
        } else {
            throw new BussinesException(messageSource.getMessage("",null,"member object is null",Locale.getDefault()));
        }
    }






    @NonNull
    public Member findById(final Long memberId) {
                   return this.memberRepository.findById(memberId).orElse(new Member());
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
