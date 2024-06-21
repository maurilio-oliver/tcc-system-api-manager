package br.unip.tcc.tccapi.service;

import br.unip.tcc.tccapi.model.Member;
import br.unip.tcc.tccapi.model.Seller;
import br.unip.tcc.tccapi.model.User;
import br.unip.tcc.tccapi.repository.MemberRepository;
import br.unip.tcc.tccapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    private UserRepository userRepository;

    //@Autowired
   // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
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
        member.setUsername(member.getPersonal().getEmail());
        member = this.memberRepository.save(member);

        if (member.getId() != null && member.getPersonal() != null && (member.getPersonal().getEmail() != null)) {
            User user = new User();
            user.setId(member.getId());
            user.setUsername(member.getPersonal().getEmail());
            //user.setPassword(encoder.encode(member.getPassword()));
            user.setRole("ROLE_SELLER");
            this.userRepository.save(user);
        }

        return member;
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
    public Member findByTerm(String email, String taxId, String mobilePhone) {
        return this.memberRepository.findByTerms(email, taxId, mobilePhone);
    }


}
