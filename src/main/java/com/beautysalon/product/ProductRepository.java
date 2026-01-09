package com.beautysalon.product;


import com.beautysalon.category.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
            select p from Product  p join p.category c where c.id=:categoryId
            """)
    List<Product> findByCategoryId(@Param("categoryId") Long categoryId);

    @Query("""
            select p from Product p join p.category c where c.subCategory=:subCategory
            """)
    List<Product> findProductsBySubCategory(@Param("subCategory") SubCategory subCategory);
}
