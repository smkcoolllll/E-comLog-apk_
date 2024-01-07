package com.EcomcataLog_.EcomcataLog_.repository;

import com.EcomcataLog_.EcomcataLog_.entity.Catagory;
import com.EcomcataLog_.EcomcataLog_.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByCategory_Id(Long categoryId);

    void deleteAllByCategory(Catagory category);
}
