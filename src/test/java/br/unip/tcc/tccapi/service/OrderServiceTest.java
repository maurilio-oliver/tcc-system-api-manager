package br.unip.tcc.tccapi.service;

import br.unip.tcc.tccapi.controller.OrderController;
import br.unip.tcc.tccapi.controller.SellerController;
import br.unip.tcc.tccapi.model.Member;
import br.unip.tcc.tccapi.model.Orders;
import br.unip.tcc.tccapi.model.Product;
import br.unip.tcc.tccapi.model.bussines.BussinesException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestClientTest
@ActiveProfiles("test")
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ProductService productService;

    @Test
    public void orderSaveTest() throws BussinesException {
        Orders order = new Orders(), savedOrder;
        Member seller = new Member(), member = new Member(), savedSeller, savedMember;
        Product product1 = new Product(), product2 = new Product();
        List<Orders> savedSellerOrder, savedMemberOrder;

        savedSeller = sellerService.save(seller);
        savedMember = memberService.save(member);
        Assertions.assertNotNull(savedSeller.getSeller().getInitializedAt());
        Assertions.assertNotNull(savedMember.getId());

        productService.save(product1);
        productService.save(product2);

        savedOrder = orderService.save(order);

        savedSellerOrder = orderService.findBySellerId(savedSeller.getId());
        savedMemberOrder = orderService.findByMemberId(savedMember.getId());

        Assertions.assertTrue((Objects.equals(savedOrder.getId(), savedSellerOrder.get(0).getId()))
                && (Objects.equals(savedSellerOrder.get(0).getId(), savedMemberOrder.get(0).getId())));
    }


    @Test
    public void orderTotalValueTest() throws BussinesException {
        Orders order = new Orders(), savedOrder;
        Member seller = new Member(), member = new Member(), savedSeller, savedMember;
        Product product1 = new Product(), product2 = new Product();

        savedSeller = sellerService.save(seller);
        savedMember = memberService.save(member);

        product1.setPrice(new BigDecimal(150.50));
        product2.setPrice(new BigDecimal(20.10));

        product1.setSellerId(savedSeller.getId());
        product2.setSellerId(savedSeller.getId());

        productService.save(product1);
        productService.save(product2);

        List<Orders> savedMemberOrder;


        Assertions.assertNotNull(savedSeller.getSeller().getInitializedAt());
        Assertions.assertNotNull(savedMember.getId());

        savedMemberOrder = orderService.findByMemberId(savedMember.getId());

        Assertions.assertEquals(savedMemberOrder.get(0).getTotal(), product1.getPrice().add(product2.getPrice()));
    }

    @Test
    public void test() {
        Orders orders = new Orders();

        orders.setSellerId(1L);
        orders.setMemberId(2L);

        this.orderService.save(orders);
        System.out.printf("test");
    }

}

