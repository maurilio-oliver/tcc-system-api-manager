package br.unip.tcc.tccapi.service;

import br.unip.tcc.tccapi.model.Financial;
import br.unip.tcc.tccapi.model.Member;
import br.unip.tcc.tccapi.model.Personal;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;


@SpringBootTest
@ActiveProfiles("test")
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void memberTestSave(){
        // create a new person.
        Personal personal = new Personal();
        personal.setMobilePhone("11958110688");
        personal.setTaxId("49545803827");
        personal.setName("maurilio de paulo viana de oliveira");
        personal.setEmail("maurilio@gmail.com.br");
        personal.setBirthDate(LocalDate.of(2000,3,23));

        // set financial data.
        Financial financial = new Financial();
        financial.setBankNumber("0066");
        financial.setBankCode("42809");
        financial.setDigit("5");
        financial.setBanckKind("0");

        // configure member test.
        Member member = new Member();
        member.setPersonal(personal);
        member.setFinancial(financial);

        // salve data.
        this.memberService.save(member);

        // get data if saved successfully.
        Member updated = this.memberService.findByEmailOrMobilePhone("maurilio@gmail.com.br", null);

        // check if the saved data is the same as the recovered data.
        Assertions.assertEquals(member.getPersonal(), updated.getPersonal());
        Assertions.assertEquals(member.getFinancial(), updated.getFinancial());

        // check if automatic dates were instantiated
        Assertions.assertNotNull(updated.getUpdatedAt());
        Assertions.assertNotNull(updated.getCreatedAt());

        Assertions.assertTrue(Objects.nonNull(updated.getId()));

    }
}
