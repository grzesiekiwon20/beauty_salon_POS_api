package com.beautysalon.cart;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
