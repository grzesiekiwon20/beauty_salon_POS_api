package com.beautysalon.product;


import com.beautysalon.category.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query(
            """
                    select p from Product p where p.category.id=:categoryId
                    """
    )
    List<Product> findProductByCategoryId(@Param("categoryId") Long categoryId);


}
