package br.unip.tcc.tccapi.controller;

import br.unip.tcc.tccapi.model.*;
import br.unip.tcc.tccapi.model.bussines.BussinesException;
import br.unip.tcc.tccapi.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Objects;


@RestController
@RequestMapping("/member/")
public class MemberController {

    @Autowired
    MemberService memberService;

    /**
     * <h1>Create a common member client</h1>
     * @param member
     * @return Response entity http created with member class
     * @throws Exception if member already exist or persist is false
     */
    @PostMapping("/create-new")
    public ResponseEntity<?> createNewMember(@RequestBody Member member) throws Exception {
        if (member.hasPersistir()) {
            member.setUpdatedAt(LocalDateTime.now());
            member.setCreatedAt(Objects.nonNull(member.getCreatedAt()) ? member.getCreatedAt() : LocalDateTime.now());
            Member candidates = memberService.findByEmailOrMobilePhone(member.getPersonal().getEmail(), member.getPersonal().getMobilePhone(), member.getPersonal().getTaxId());
            if (Objects.nonNull(candidates)) {
                String message = "usuario ja cadastrado, por favor usar outro:";
                message = (member.getPersonal().getEmail()).equals(candidates.getPersonal().getEmail()) ? message + "email," : "";
                message = (member.getPersonal().getTaxId()).equals(candidates.getPersonal().getTaxId()) ? message + "cpf," : "";
                message = (member.getPersonal().getMobilePhone()).equals(candidates.getPersonal().getMobilePhone()) ? message + "telefone," : "";
                throw new BussinesException(message + " diferente");
            }
            return ResponseEntity.status(201).body(this.memberService.save(member));
        } else return null;
    }

    /**
     * <h1>Find member by Id</h1>
     * @param memberId
     * @return response entity http 200 if member exist or 401 not found if not exist
     */
    @GetMapping("/{memberId}")
    public ResponseEntity<?> findById(@PathVariable final Long memberId) {
        Member member = this.memberService.findById(memberId);
        if (Objects.nonNull(member)) {
            return ResponseEntity.ok(member);
        }
        return ResponseEntity.status(401).body("not found");
    }

    /**
     * <h1>Update member information's</h1>
     * @param member
     * @param memberId
     * @throws BussinesException if it is not possible to persist a member in the request.
     */
    @PostMapping("/{memberId}/update")
    public void update(@RequestBody final Member member, @PathVariable Long memberId) throws BussinesException {
        this.memberService.save(member);
    }

    /**
     * <h1>delete member by id</h1>
     * <span style="color:red;">temporary</span>
     * @param memberId
     * @throws BussinesException
     */
    @DeleteMapping("/{memberId}/delete-temporary/")
    public void deleteById(@PathVariable final Long memberId) throws BussinesException {
        Member member = this.memberService.findById(memberId);
        member.setDeletedAt(LocalDateTime.now());
        this.memberService.save(member);
    }

    /*<Alert> Test zone </Alert>*/

    @GetMapping("/test/message")
    public ResponseEntity getTest() {

        Member m = new Member();
        m.setFinancial(new Financial());
        m.setPersonal(new Personal());
        m.setSeller(new Seller());
        try {
            long l = m.getId() + 1L;
        } catch (Exception e) {
            return ResponseEntity.status(500).body(MessageLog.simpleMessageError(e));
        }
        return ResponseEntity.ok(m);
    }

    @GetMapping("/get-test")
    public ResponseEntity<Member> test(){
        Member member = new Member();
        member.setFinancial(new Financial());
        member.setPersonal(new Personal());
        member.setResidential(new Residential());

        return ResponseEntity.ok(member);
    }


}
