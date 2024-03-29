package br.unip.tcc.tccapi.service;

import br.unip.tcc.tccapi.model.Financial;
import br.unip.tcc.tccapi.model.Member;
import br.unip.tcc.tccapi.model.Personal;
import br.unip.tcc.tccapi.model.Seller;
import br.unip.tcc.tccapi.model.bussines.BussinesException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@SpringBootTest
@ActiveProfiles("test")
public class SellerServiceTests {
    @Autowired
    private SellerService sellerService;

    @Autowired
    private MemberService memberService;


    @Test
    public void saveWithoutRegistrationStartedTest(){
        Member member = new Member();
        Personal personal = new Personal();
        personal.setName("teste vendendor sem cadastro");
        personal.setEmail("seller.test@gmail.com");
        personal.setTaxId("72022813090");
        personal.setBirthDate(LocalDate.of(2000,3,23));
        personal.setMobilePhone("11958110688");
        member.setPersonal(personal);

        final Member updatedMember = this.sellerService.save(member);
        boolean check = (updatedMember.getSeller() != null && updatedMember.getSeller().getInitializedAt() != null);
        Assert.isTrue(check, "id não pode ser nullo");

    }

    @Test
    public void saveWithResistrationStartedTest() throws BussinesException {
        Member member = new Member();

        Personal personal = new Personal();
        personal.setName("seller test");
        personal.setTaxId("23605382096");
        personal.setEmail("seller.test@gmail.com");
        personal.setMobilePhone("11940028922");

        member.setPersonal(personal);

        member = this.memberService.save(member);

        Assertions.assertNull(member.getSeller());

        Member seller  = this.sellerService.save(member);

        Assertions.assertNotNull(seller.getSeller());
        Assertions.assertNotNull(seller.getSeller().getInitializedAt());
    }

}
