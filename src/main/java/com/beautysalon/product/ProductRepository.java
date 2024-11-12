package com.beautysalon.product;


import com.beautysalon.product.dto.ProductResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(@NotBlank @Size(min = 3, message = "Product name must contain at least 3 characters") String name);


    @Query(
            """
                    select p from Product p where p.category.id=:categoryId
                    """
    )
    List<Product> findProductByCategoryId(@Param("categoryId") Long categoryId);
}
