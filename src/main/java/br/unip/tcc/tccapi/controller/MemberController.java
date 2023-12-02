package br.unip.tcc.tccapi.controller;

import br.unip.tcc.tccapi.model.*;
import br.unip.tcc.tccapi.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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

    // create a new member common
    @PostMapping("/create-new")
    public ResponseEntity<String> createNewMember(@RequestBody Member member) {
            this.memberService.save(member);
            return ResponseEntity.ok("{ok}");
    }

    // find a member by id
    @GetMapping("/{memberId}")
    public ResponseEntity<Member> find(@PathVariable final Long memberId) {
        ResponseEntity response = ResponseEntity.ok(this.memberService.findById(memberId));
        return response;
    }

    // update member information
    @PostMapping("/{memberId}/update")
    public void update(@RequestBody final Member member, @PathVariable Long memberId) {
        this.memberService.save(member);
    }

    // delete by id
    @DeleteMapping("/{memberId}/delete-temporary/")
    public void deleteById(@PathVariable final Long memberId) {
        Member member = this.memberService.findById(memberId);
        member.setDeletedAt(LocalDateTime.now());
        this.memberService.save(member);
    }

    @GetMapping("/all/")
    public Member getMemberByTerm() {
       return null;// this.memberService.findByEmailOrMobilePhone();
    }

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


}
