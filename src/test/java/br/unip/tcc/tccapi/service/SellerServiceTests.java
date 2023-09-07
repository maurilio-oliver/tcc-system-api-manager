package br.unip.tcc.tccapi.service;

import br.unip.tcc.tccapi.model.Member;
import br.unip.tcc.tccapi.model.Personal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDate;

@SpringBootTest
public class SellerServiceTests {
    @Autowired
    private SellerService sellerService;


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
}
