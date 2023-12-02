package br.unip.tcc.tccapi.service;

import br.unip.tcc.tccapi.model.Financial;
import br.unip.tcc.tccapi.model.Member;
import br.unip.tcc.tccapi.model.Personal;
import br.unip.tcc.tccapi.model.Seller;
import br.unip.tcc.tccapi.repository.MemberRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Random;


@SpringBootTest
@ActiveProfiles("test")
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void memberServiceTestBasicSave(){
        // create a new person.
        Personal personal = new Personal();
        personal.setMobilePhone("11958110688");
        personal.setTaxId("49545803827");
        personal.setName("maurilio de paulo viana de oliveira");
        personal.setEmail("maurilio@gmail.com.br");


        // set financial data.
        Financial financial = new Financial();
        financial.setBankNumber("0066");
        financial.setBankCode("42809");
        financial.setDigit("5");

        // configure member test.
        Member member = new Member();
        member.setPersonal(personal);
        member.setFinancial(financial);

        // salve data.
        this.memberService.save(member);

        // get data if saved successfully.
        Member updated = this.memberService.findByEmailOrMobilePhone("maurilio@gmail.com.br", null, null);

        // check if the saved data is the same as the recovered data.
        Assertions.assertEquals(member.getPersonal(), updated.getPersonal());
        Assertions.assertEquals(member.getFinancial(), updated.getFinancial());

        // check if automatic dates were instantiated
        Assertions.assertNotNull(updated.getUpdatedAt());
        Assertions.assertNotNull(updated.getCreatedAt());

        // check if the member has not been saved as a seller.
        Assertions.assertFalse(Objects.nonNull(member.getSeller()));
        Assertions.assertTrue(Objects.nonNull(updated.getId()));
    }

    @Test void memberUpdateTest() {
        var member = new Member();
        var personal = new Personal();
        personal.setEmail("member.test@gmail.com.br");
        personal.setMobilePhone("11958110688");
        personal.setTaxId("49545803827");
        personal.setName("test");
        member.setPersonal(personal);

       var updatedMember = this.memberService.save(member);

       Assertions.assertTrue(Objects.nonNull(updatedMember.getUpdatedAt()));
       Assertions.assertTrue(Objects.nonNull(updatedMember.getCreatedAt()));

       updatedMember.getPersonal().setName("test member");
       var updated2 = this.memberService.save(updatedMember);

       Assertions.assertEquals(updatedMember.getCreatedAt(), updated2.getCreatedAt());
       Assertions.assertEquals(updatedMember.getPersonal().getName(), updated2.getPersonal().getName());
       Assertions.assertEquals(updatedMember.getUpdatedAt(), updated2.getUpdatedAt());
    }

    @Test void memberFindByTermTest() {
        Member member = new Member();
        Personal personal = new Personal();
        personal.setEmail("member.test@gmail.com.br");
        personal.setMobilePhone("11958110688");
        personal.setTaxId("49545803827");
        personal.setName("test");
        member.setPersonal(personal);
        member.setTest(List.of(1,2,3));

        member = this.memberService.save(member);
        Member salvedMember =  this.memberService.findByEmailOrMobilePhone(personal.getEmail(), personal.getMobilePhone(), personal.getTaxId());

        Assertions.assertEquals(member.getId(), salvedMember.getId());
        Assertions.assertEquals(member.getPersonal().getMobilePhone(), personal.getMobilePhone());
        Assertions.assertEquals(member.getPersonal().getEmail(), personal.getEmail());
        Assertions.assertEquals(member.getPersonal().getTaxId(), personal.getTaxId());

    }





}
