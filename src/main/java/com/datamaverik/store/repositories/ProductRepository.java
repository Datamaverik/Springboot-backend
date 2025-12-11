package com.datamaverik.store.repositories;

import com.datamaverik.store.dtos.ProductSummary;
import com.datamaverik.store.dtos.ProductSummaryDTO;
import com.datamaverik.store.entities.Category;
import com.datamaverik.store.entities.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer>, ProductCriteriaRepository {
    //  String
    List<Product> findByName(String name);
    List<Product> findByNameLike(String name);
    List<Product> findByNameNotLike(String name, Limit limit);
    List<Product> findByNameContaining(String name);
    List<Product> findByNameStartingWith(String name);
    List<Product> findByNameEndingWith(String name);
    List<Product> findByNameEndingWithIgnoreCase(String name);

    //  Numbers
    List<Product> findByPrice(BigDecimal price);
    List<Product> findByPriceGreaterThan(BigDecimal priceIsGreaterThan);
    List<Product> findByPriceGreaterThanEqual(BigDecimal priceIsGreaterThan);
    List<Product> findByPriceLessThan(BigDecimal priceIsLessThan);
    List<Product> findByPriceLessThanEqual(BigDecimal priceIsLessThan);
    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);

    //  Null
    List<Product> findByDescriptionNull();
    List<Product> findByDescriptionNotNull();

    //  Multiple conditions
    List<Product> findByDescriptionNullAndNameNull();

    //  Sort (OrderBy)
    List<Product> findByNameOrderByPrice(String name);

    //  Limit (Top/First)
    List<Product> findTop5ByNameOrderByPrice(String name);
    List<Product> findFirst5ByNameLikeOrderByPrice(String name);

    //  Find products whose prices are in a given range and sort by name
    //  SQL or JPQL
    @Procedure("findProductsByPrice")
    List<Product> findProducts(BigDecimal min, BigDecimal max);

    @Query("select count(*) from Product p where p.price between :min and :max")
    long countProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    @Modifying
    @Query("update Product p set p.price = :newPrice where p.category.id = :categoryId")
    void updatePriceByCategory(@Param("newPrice") BigDecimal newPrice, @Param("categoryId") Byte categoryId);

    @Query("select p.id, p.name from Product p where p.category = :category")
    List<ProductSummary> findByCategory(@Param("category") Category category);
}