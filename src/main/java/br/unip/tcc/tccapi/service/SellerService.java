package br.unip.tcc.tccapi.service;

import br.unip.tcc.tccapi.model.Member;
import br.unip.tcc.tccapi.model.Seller;
import br.unip.tcc.tccapi.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <h1>Seller service.</h1>
 * <p>Class that manages the member (seller) data flow.</p>
 * @see br.unip.tcc.tccapi.controller.SellerController
 */
@Service
public class SellerService {

    @Autowired
    private MemberRepository memberRepository;


    /**
     *  <h1>Save (Seller)</h1>
     *  saves and updates member (seller) data
     * @param member for save or update
     * @return member updated
     */
    public Member save(Member member) {

        // Initialize the seller module.
        member.setSeller(member.getSeller() == null ? new Seller() : member.getSeller());
        // Set register date.
        member.getSeller().setInitializedAt(
                // If initialized_at is null, now set initializer date.
                member.getSeller().getInitializedAt() == null ?
                        LocalDateTime.now() : member.getSeller().getInitializedAt());
        // Set updatedAt.
        member.setUpdatedAt(LocalDateTime.now());
        // If creation date is null, set now as creation date.
        member.setCreatedAt(
                member.getCreatedAt() == null ?
                        LocalDateTime.now() : member.getCreatedAt());
        // Save data
        return this.memberRepository.save(member);
    }

    /**
     * <h1> Find by id (Seller) </h1>
     * <p>looking for a pro id seller</p>
     *
     * @param memberId Long member id
     * @param isSeller boolean true if is seller
     * @return Member if seller exist
     */
    public Member findById(final Long memberId, final boolean isSeller) {
        // If member id is not null and is seller.
        if (memberId != null && isSeller) {
            // Find seller by member id.
            Member member = this.memberRepository.findById(memberId).get();
            // Return seller data.
            return member;
        }
        // Else return null.
        else
            return null;

    }

    /**
     *
     * @param taxId String tax id with member
     * @param email String email with member
     * @return Member
     */
    public Member findByTerm(String taxId, String email) {
        return null;
    }


}
