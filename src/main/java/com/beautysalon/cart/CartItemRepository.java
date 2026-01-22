package com.beautysalon.cart;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {


    @Query("""
            select i from CartItem i join i.product p where i.username=:username and p.id=:productId
            """)
    CartItem findCartItemByUsernameAndProductId(@Param("username") String username, @Param("productId") Long productId);


    @Query("""
            select i from CartItem  i where  i.username=:username
            """)
    List<CartItem> findByUsername(@Param("username") String username);

    @Query("""
            select i from CartItem i join i.product p where  p.id=:id
            """)
    CartItem existsByProductId( @Param("id") Long id);

    void deleteCartItemByUsernameAndProductId(String name, Long productId);
}
