package br.unip.tcc.tccapi.controller;

import br.unip.tcc.tccapi.model.Member;
import br.unip.tcc.tccapi.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member/")
public class MemberController {
    @Autowired
    MemberService memberService;

    @PostMapping("/create-new")
    public void createNewMember(@RequestBody Member member) {
        this.memberService.save(member);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<Member> find(@PathVariable final Long memberId) {
        ResponseEntity response = ResponseEntity.ok(this.memberService.findById(memberId));

        return response;
    }


}
