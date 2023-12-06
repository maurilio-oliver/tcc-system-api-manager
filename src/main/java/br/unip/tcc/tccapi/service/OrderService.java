package br.unip.tcc.tccapi.service;

import br.unip.tcc.tccapi.model.Orders;
import br.unip.tcc.tccapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class OrderService {

   @Autowired
    private OrderRepository orderRepository;

    public Orders save(final Orders order) {
        order.setCreatedAt(order.getCreatedAt() == null ? LocalDateTime.now() : order.getCreatedAt());
        order.setUpdatedAt(LocalDateTime.now());

          return this.orderRepository.save(order);
    }

    public void cancelById(final Long id){
        Orders order = this.orderRepository.findById(id).get();
        order.setCancelledAt(LocalDateTime.now());
        this.save(order);
    }

    public Orders findById(final Long orderId) {
        return this.orderRepository.findById(orderId).orElse(null);
    }

    public List<Orders> findByMemberId(Long memberId) {
        if (Objects.nonNull(memberId)) {
            return this.orderRepository.findAllByMemberId(memberId);
        }
        return null;
    }

    public List<Orders> findBySellerId(Long sellerId) {
        if (Objects.nonNull(sellerId)) {
            return this.orderRepository.findAllBySellerId(sellerId);
        }
        return null;
    }
}
