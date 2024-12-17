package com.beautysalon.cartitem;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem , Long> {


    @Query("""
select c from CartItem c where c.cart.id=:cartId
""")
    List<CartItem> findByCartId(@Param("cartId") Long cartId);
}
