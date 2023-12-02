package br.unip.tcc.tccapi.controller;

import br.unip.tcc.tccapi.model.Member;
import br.unip.tcc.tccapi.model.Seller;
import br.unip.tcc.tccapi.service.SellerService;
import lombok.CustomLog;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.slf4j.LoggerFactory;
import org.slf4j.LoggerFactoryFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.logging.Logger;

@Controller
@RequestMapping("/member/seller")

public class SellerController {

    @Autowired
    private SellerService sellerService;

    /**
     * <h1>Create new seller</h1>
     * <p>Creates the end-point responsible for accessing the new seller creation service.</p>
     * @param seller Member
     * @return Request response
     */
    @PostMapping("/create-seller")
    public ResponseEntity<Member> createNewSeller(@RequestBody Member seller) {

      return ResponseEntity.ok(this.sellerService.save(seller));
    }


    @GetMapping("/find-by-id/{sellerId}")
    public Member findById(@PathVariable Long sellerId) {
        return sellerService.findById(sellerId, true);
    }

    @GetMapping("find-by-term")
    public ResponseEntity findByTerm (
            @RequestParam(required = false) String taxId,
            @RequestParam(required = false) String cellPhone,
            @RequestParam(required = false) String email
    ) {

        if (Objects.nonNull(taxId) || Objects.nonNull(email))
            return ResponseEntity.ok(this.sellerService.findByTerm(taxId, email, cellPhone));
        else
            return ResponseEntity.ok("error !");
    }


    @PutMapping
    public ResponseEntity<String> updateSellerinformations(@RequestBody final Member seller){
        this.sellerService.save(seller);
        return ResponseEntity.ok("ok");
    }





}
